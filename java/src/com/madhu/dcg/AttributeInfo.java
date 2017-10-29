
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

public abstract class AttributeInfo {
	private String name;
	private int nameIndex;
	private int length;

	public AttributeInfo(String aName, int aNameIndex, int aLength) {
		name = aName;
		nameIndex = aNameIndex;
		length = aLength;
	}

	public static AttributeInfo get(ByteArray byteArray,
		ConstantPool constants) {

		int n = byteArray.readShort();
		Utf8Entry utf8 = (Utf8Entry) constants.getEntry(n);
		String name = utf8.getValue();
		int length = byteArray.readInt();
		if (name.equals("ConstantValue")) {
			return new ConstantValueAttribute(byteArray, constants, n, length);
		}
		if (name.equals("Code")) {
			return new CodeAttribute(byteArray, constants, n, length);
		}
		if (name.equals("Exceptions")) {
			return new ExceptionsAttribute(byteArray, constants, n, length);
		}
		return new UnknownAttribute(byteArray, n, length);
	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeShort(nameIndex);
		dsOut.writeInt(length);
		writeDetail(dsOut);
	}

	public abstract void writeDetail(DataOutputStream dsOut) throws IOException;

	public String toString() {
		return name + ", " + length + " bytes";
	}
	/**
	 * @return
	 */
	public int getNameIndex() {
		return nameIndex;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}
}
