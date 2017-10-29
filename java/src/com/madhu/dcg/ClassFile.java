
/*
 * Copyright 1996-1998 Madhu Siddalingaiah
 * All rights reserved.
 * mailto:madhu@madhu.com http://www.madhu.com
 *
 * This software is the confidential and proprietary information
 * of Madhu Siddalingaiah ("Confidential Information").
 * Unauthorized duplication, modification, or distribution of this
 * software, its algorithms, documentation, or related intellectual
 * property without expressed written consent by Madhu Siddalingaiah
 * is strictly prohibited.
 */
package com.madhu.dcg;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * number is a unique identifier for a class
 * bits 0-7: number of dimensions
 * bits 8-31: class number, values 0-16 are built-in types
 */
public class ClassFile {
	public static final int ACC_PUBLIC = 0x0001;
	public static final int ACC_PRIVATE = 0x0002;
	public static final int ACC_PROTECTED = 0x0004;
	public static final int ACC_STATIC = 0x0008;
	public static final int ACC_FINAL = 0x0010;
	public static final int ACC_SUPER = 0x0020;
	public static final int ACC_SYNCHRONIZED = 0x0020;
	public static final int ACC_VOLATILE = 0x0040;
	public static final int ACC_TRANSIENT = 0x0080;
	public static final int ACC_NATIVE = 0x0100;
	public static final int ACC_INTERFACE = 0x0200;
	public static final int ACC_ABSTRACT = 0x0400;

	public static final int T_BOOLEAN = 4;
	public static final int T_CHAR = 5;
	public static final int T_FLOAT = 6;
	public static final int T_DOUBLE = 7;
	public static final int T_BYTE = 8;
	public static final int T_SHORT = 9;
	public static final int T_INT = 10;
	public static final int T_LONG = 11;

	public static final int OBJECT_CLASSNUMBER = 16 << 8;

	private int magic;
	private int minor;
	private int major;
	private ConstantPool constants;
	private int accessFlags;
	private int thisIndex;
	private int superIndex;
	private String thisClassName;
	private String superClassName;
	private ArrayList interfaces;
	private ArrayList fields;
	private ArrayList methods;
	private ArrayList atts;
	private int codeAttributeNameIndex;
	private int exceptionsAttributeNameIndex;

	public ClassFile() {
		magic = 0xCAFEBABE;
		minor = 3;
		major = 45;
		constants = new ConstantPool();
		interfaces = new ArrayList();
		fields = new ArrayList();
		methods = new ArrayList();
		atts = new ArrayList();

		codeAttributeNameIndex = constants.addUtf8Entry("Code");
		exceptionsAttributeNameIndex = constants.addUtf8Entry("Exceptions");
	}

	public ClassFile(byte data[]) {
		int n;

		ByteArray byteArray = new ByteArray(data);
		magic = byteArray.readInt();
		minor = byteArray.readShort();
		major = byteArray.readShort();

		n = byteArray.readShort();
		constants = new ConstantPool();
		for (int i=1; i<n; i+=1) {
			ConstantEntry entry = ConstantEntry.get(byteArray);
			constants.add(entry);
			if ((entry instanceof LongEntry) ||
				(entry instanceof DoubleEntry)) {
				i += 1;
				constants.add(new NullEntry());
			}
		}
		resolveConstants();

		accessFlags = byteArray.readShort();
		thisIndex = byteArray.readShort();
		superIndex = byteArray.readShort();

		thisClassName = ((ClassEntry) constants.getEntry(thisIndex)).getClassName();
		if (superIndex != 0) {
			superClassName = ((ClassEntry) constants.getEntry(superIndex)).getClassName();
		} else {
			superClassName = null;
		}

		n = byteArray.readShort();
		interfaces = new ArrayList();
		for (int i=0; i<n; i+=1) {
			interfaces.add(new InterfaceInfo(byteArray, constants));
		}

		n = byteArray.readShort();
		fields = new ArrayList();
		for (int i=0; i<n; i+=1) {
			fields.add(new FieldInfo(byteArray, constants));
		}

		n = byteArray.readShort();
		methods = new ArrayList();
		for (int i=0; i<n; i+=1) {
			methods.add(new MethodInfo(byteArray, constants));
		}

		n = byteArray.readShort();
		atts = new ArrayList();
		for (int i=0; i<n; i+=1) {
			atts.add(AttributeInfo.get(byteArray, constants));
		}
	}

	public void resolveConstants() {
		int n = constants.size();
		for (int i=0; i<n; i+=1) {
			ConstantEntry entry = constants.getEntry(i);
			if (entry != null) {
				entry.resolve(constants);
			}
		}
	}

	public String getThisClassName() {
		return thisClassName;
	}

	public void setThisClassName(String name) {
		thisClassName = name;
		thisIndex = constants.addClassEntry(name);
	}

	public String getSuperClassName() {
		return superClassName;
	}

	public void setSuperClassName(String name) {
		superClassName = name;
		superIndex = constants.addClassEntry(name);
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public void setAccessFlags(int flags) {
		accessFlags = flags;
	}

	public int getCodeAttributeNameIndex() {
		return codeAttributeNameIndex;
	}

	public int addClassEntry(String name) {
		return constants.addClassEntry(name);
	}

	public void addInterface(String name) {
		int index = constants.addClassEntry(name);
		interfaces.add(new InterfaceInfo(name, index));
	}

	public void addField(int flags, String name, String desc) {
		int nameIndex = constants.addUtf8Entry(name);
		int descIndex = constants.addUtf8Entry(desc);

		// zero attributes for now...
		AttributeInfo atts[] = new AttributeInfo[0];
		fields.add(new FieldInfo(flags, name, nameIndex, desc, descIndex, atts));
	}

	public void addMethod(int flags, String name, String desc,
		CodeAttributeFactory codeAttr) {

		addMethod(flags, name, desc, codeAttr, new String[0]);
	}

	public void addMethod(int flags, String name, String desc,
		CodeAttributeFactory codeAttr, String exceptions[]) {
		int nameIndex = constants.addUtf8Entry(name);
		int descIndex = constants.addUtf8Entry(desc);
		AttributeInfo atts[] = new AttributeInfo[2];
		ExceptionEntry table[] = new ExceptionEntry[0];
		AttributeInfo attAtts[] = new AttributeInfo[0];
		atts[0] = codeAttr.createCodeAttribute(codeAttributeNameIndex);
		int exceptionsTable[] = new int[exceptions.length];
		for (int i=0; i<exceptions.length; i+=1) {
			exceptionsTable[i] = constants.addClassEntry(exceptions[i]);
		}
		int length = 2 + (2*exceptions.length);
		atts[1] = new ExceptionsAttribute(length,
			exceptionsAttributeNameIndex, exceptionsTable);
		methods.add(new MethodInfo(flags, name, nameIndex, desc, descIndex, atts));
	}

	public int addFieldRef(String className, String fieldName, String desc) {
		return constants.addFieldRefEntry(className, fieldName, desc);
	}

	public int addMethodRef(String className, String methodName, String desc) {
		return constants.addMethodRefEntry(className, methodName, desc);
	}

	public int addInterfaceMethodRef(String className, String methodName, String desc) {
		return constants.addInterfaceMethodRefEntry(className, methodName, desc);
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeInt(magic);
		dsOut.writeShort(minor);
		dsOut.writeShort(major);
		int n = constants.size();
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			ConstantEntry c = constants.getEntry(i);
			c.write(dsOut);
			if ((c instanceof LongEntry) ||
				(c instanceof DoubleEntry)) {
				i += 1;
			}
		}
		dsOut.writeShort(accessFlags);
		dsOut.writeShort(thisIndex);
		dsOut.writeShort(superIndex);

		n = interfaces.size();
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			InterfaceInfo iface = (InterfaceInfo) interfaces.get(i);
			iface.write(dsOut);
		}

		n = fields.size();
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			FieldInfo f = (FieldInfo) fields.get(i);
			f.write(dsOut);
		}

		n = methods.size();
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			MethodInfo m = (MethodInfo) methods.get(i);
			m.write(dsOut);
		}

		n = atts.size();
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			AttributeInfo a = (AttributeInfo) atts.get(i);
			a.write(dsOut);
		}
	}

	public void printOut(PrintStream psOut) {
		int n = constants.size();
		psOut.println(n + " constants");
		for (int i=0; i<n; i+=1) {
			ConstantEntry entry = constants.getEntry(i);
			psOut.println("Constant " + i + ": " + entry.toString());
		}

		n = methods.size();
		for (int i=0; i<n; i+=1) {
			MethodInfo m = (MethodInfo) methods.get(i);
			m.printOut(System.out);
		}
	}
}
