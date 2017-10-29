
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

public class UnknownAttribute extends AttributeInfo {
	private byte data[];

	public UnknownAttribute(ByteArray byteArray, int aNameIndex, int length) {
		super("Unknown", aNameIndex, length);
		data = new byte[length];
		for (int i=0; i<length; i+=1) {
			data[i] = (byte) byteArray.readByte();
		}
	}

	public byte[] getData() {
		return data;
	}

	public void writeDetail(DataOutputStream dsOut) throws IOException {
		int n = data.length;
		for (int i=0; i<n; i+=1) {
			dsOut.writeByte(data[i]);
		}
	}
}
