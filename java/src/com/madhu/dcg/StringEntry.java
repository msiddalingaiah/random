
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

public class StringEntry extends ConstantEntry {
	private int value;
	private String string;

	public StringEntry(int aValue) {
		value = aValue;
		string = Integer.toString(value);
	}

	public void resolve(ConstantPool constants) {
		if (!(constants.getEntry(value) instanceof Utf8Entry)) throw
			new ClassFormatError ("ClassEntry does not point to a Utf8Entry!");
		Utf8Entry utf8 = (Utf8Entry) constants.getEntry(value);
		string = utf8.getValue();
	}

	public int getValue() {
		return value;
	}

	public String getString() {
		return string;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeByte(ConstantEntry.CP_String);
		dsOut.writeShort(value);
	}

	public String toString() {
		return "String: " + getString();
	}
}
