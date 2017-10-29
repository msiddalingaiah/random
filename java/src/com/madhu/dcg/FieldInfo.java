
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

public class FieldInfo {
	private int accessFlags;
	private String name;
	private int nameIndex;
	private String descriptor;
	private int descriptorIndex;
	private AttributeInfo attributes[];
	private int size;

	public FieldInfo(int flags, String aName, int aNameIndex,
		String aDescriptor, int aDescIndex, AttributeInfo atts[]) {
		accessFlags = flags;
		name = aName;
		nameIndex = aNameIndex;
		descriptor = aDescriptor;
		descriptorIndex = aDescIndex;
		attributes = atts;
	}

	public FieldInfo(ByteArray byteArray, ConstantPool constants) {
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
		}
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
				throw new InternalError("Unknown data type " + type);
		}
	}

	public String getName() {
		return name;
	}

	public String getDescriptor() {
		return descriptor;
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

	public String toString() {
		return "Field: " + name + " " + descriptor;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public int getSize() {
		return size;
	}
}
