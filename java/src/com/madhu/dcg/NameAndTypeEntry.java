
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

public class NameAndTypeEntry extends ConstantEntry {
	private int nameIndex;
	private int descriptorIndex;
	private String name;
	private String descriptor;

	public NameAndTypeEntry(int aNameIndex, int aDescIndex) {
		nameIndex = aNameIndex;
		descriptorIndex = aDescIndex;
		name = Integer.toString(aNameIndex);
		descriptor = Integer.toString(aDescIndex);
	}

	public void resolve(ConstantPool constants) {
		if (!(constants.getEntry(nameIndex) instanceof Utf8Entry)) throw
			new ClassFormatError ("NameAndTypeEntry name does not point to a Utf8Entry!");
		if (!(constants.getEntry(descriptorIndex) instanceof Utf8Entry)) throw
			new ClassFormatError ("NameAndTypeEntry descriptor does not point to a Utf8Entry!");
		Utf8Entry utf8;
		utf8 = (Utf8Entry) constants.getEntry(nameIndex);
		name = utf8.getValue();
		utf8 = (Utf8Entry) constants.getEntry(descriptorIndex);
		descriptor = utf8.getValue();
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public String getName() {
		return name;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeByte(ConstantEntry.CP_NameAndType);
		dsOut.writeShort(nameIndex);
		dsOut.writeShort(descriptorIndex);
	}

	public String toString() {
		return "NameAndType: " + name + ", " + descriptor;
	}
}
