
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

public class FieldRefEntry extends MemberEntry {
	private int size;

	public FieldRefEntry(int aClassIndex, int aNameAndTypeIndex) {
		super(aClassIndex, aNameAndTypeIndex);
	}

	public void resolve(ConstantPool constants) {
		super.resolve(constants);
		String descriptor = getDescriptor();
		char type = descriptor.charAt(0);
		switch (type) {
			case 'B':
			case 'Z':
				size = 4;
				break;

			case 'C':
			case 'S':
				size = 4;
				break;

			case 'F':
			case 'I':
			case 'L':
			case '[':
				size = 4;
				break;

			case 'D':
			case 'J':
				size = 8;
				break;

			default:
				System.out.println(descriptor);
				throw new InternalError("Unknown data type " + type);
		}
	}

	public String getType() {
		return "FieldRef";
	}

	public int getSize() {
		return size;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeByte(ConstantEntry.CP_Fieldref);
		dsOut.writeShort(getClassIndex());
		dsOut.writeShort(getNameAndTypeIndex());
	}
}
