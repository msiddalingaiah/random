
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

public class MethodInfo {
	private int accessFlags;
	private String name;
	private int nameIndex;
	private String mangledName;
	private String descriptor;
	private int descriptorIndex;
	private AttributeInfo attributes[];
	private CodeAttribute codeAttribute;
	private boolean izVirtual;
	private boolean izAbstract;

	public MethodInfo(int flags, String aName, int aNameIndex,
		String aDescriptor, int aDescIndex, AttributeInfo atts[]) {
		accessFlags = flags;
		name = aName;
		nameIndex = aNameIndex;
		descriptor = aDescriptor;
		descriptorIndex = aDescIndex;
		attributes = atts;
		int n = atts.length;
		for (int i=0; i<n; i+=1) {
			if (atts[i] instanceof CodeAttribute) {
				codeAttribute = (CodeAttribute) atts[i];
				break;
			}
		}
	}

	public MethodInfo(ByteArray byteArray, ConstantPool constants) {
		accessFlags = byteArray.readShort();
		int n = byteArray.readShort();
		nameIndex = n;
		Utf8Entry utf8 = (Utf8Entry) constants.getEntry(n);
		name = utf8.getValue();
		n = byteArray.readShort();
		descriptorIndex = n;
		utf8 = (Utf8Entry) constants.getEntry(n);
		descriptor = utf8.getValue();
		n = byteArray.readShort();
		attributes = new AttributeInfo[n];
		for (int i=0; i<n; i+=1) {
			attributes[i] = AttributeInfo.get(byteArray, constants);
			if (attributes[i] instanceof CodeAttribute) {
				codeAttribute = (CodeAttribute) attributes[i];
			}
		}
		mangledName = mangle(name, descriptor);
		if (((accessFlags & ClassFile.ACC_STATIC) == 0) &&
			!name.startsWith("<")) {
			izVirtual = true;
		} else {
			izVirtual = false;
		}
		if ((accessFlags & ClassFile.ACC_ABSTRACT) != 0) {
			izAbstract = true;
		} else {
			izAbstract = false;
		}
	}

	public String getName() {
		return name;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public CodeAttribute getCodeAttribute() {
		return codeAttribute;
	}

	public String toString() {
		return "Method: " + name + " " + descriptor;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public String getMangledName() {
		return mangledName;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeShort(accessFlags);
		dsOut.writeShort(nameIndex);
		dsOut.writeShort(descriptorIndex);
		int n = attributes.length;
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			attributes[i].write(dsOut);
		}
	}

	static String charMap =
		"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static String mangle(String name, String desc) {
		int length = desc.length();
		int i;
		int value = 0;
		for (i=0; i<length; i+=1) {
			value += desc.charAt(i);
			value *= 37;
		}
		length = name.length();
		char data[] = new char[length + 4];
		for (i=0; i<length; i+=1) {
			char c = name.charAt(i);
			if (c == '>' || c == '<') {
				c = '_';
			}
			data[i] = c;
		}
		int mod = charMap.length();
		length += 4;
		if (value < 0) {
			value = -value;
		}
		for (;i<length; i+=1) {
			data[i] = charMap.charAt(value % mod);
			value >>>= 8;
		}
		return new String(data);
	}

	public boolean isVirtual() {
		return izVirtual;
	}

	public boolean isAbstract() {
		return izAbstract;
	}

	public void printOut(PrintStream psOut) {
		psOut.println("Method " + name);
		codeAttribute.printOut(psOut);
	}
}
