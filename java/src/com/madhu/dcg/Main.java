
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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Main {
	public static void main(String args[]) throws Exception {
		System.out.println("Creating class data");
		ClassFile cf = new ClassFile();
		String className = "Test";
		cf.setThisClassName(className);
		cf.setSuperClassName("java/lang/Object");
		cf.setAccessFlags(ClassFile.ACC_PUBLIC | ClassFile.ACC_SUPER);
		cf.addInterface("com/madhu/dcg/TestInterface");

		CodeAttributeFactory caf = new CodeAttributeFactory(10, 1, 1);
		caf.addByte((byte) OPCodes._aload_0);
		caf.addByte((byte) OPCodes._invokenonvirtual);
		int m = cf.addMethodRef("java/lang/Object", "<init>", "()V");
		caf.addShort((short) m);
		caf.addByte((byte) OPCodes._return);

		cf.addMethod(ClassFile.ACC_PUBLIC, "<init>", "()V", caf);

// add with abs function

		caf = new CodeAttributeFactory(10, 2, 3);
		caf.addByte((byte) OPCodes._iload_1);
		caf.addByte((byte) OPCodes._iload_2);
		caf.addByte((byte) OPCodes._iadd);
		caf.addByte((byte) OPCodes._dup);
		caf.addShortBranch((byte) OPCodes._ifgt, 1);
		caf.addByte((byte) OPCodes._ineg);
		caf.addLabelDef(1);
		caf.addByte((byte) OPCodes._ireturn);

		cf.addMethod(ClassFile.ACC_PUBLIC, "add", "(II)I", caf);

		ByteArrayOutputStream baOut = new ByteArrayOutputStream(1024);
		DataOutputStream dsOut = new DataOutputStream(baOut);
		cf.write(dsOut);
		byte code[] = baOut.toByteArray();

		System.out.println("Introducing class data into VM");
		ByteArrayClassLoader loader = new ByteArrayClassLoader();
		loader.addClassData(className, code);
		Class c = loader.loadClass(className);
		TestInterface t = (TestInterface) c.newInstance();
		System.out.println("Result is " + t.add(-73, 34));
		System.out.println("Result is " + t.add(-1, -1));
		System.out.println("Result is " + t.add(1, 1));
	}
}
