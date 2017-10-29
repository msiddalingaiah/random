
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

public class ExceptionsAttribute extends AttributeInfo {
	private int[] exceptions;

	public ExceptionsAttribute(ByteArray byteArray,
		ConstantPool constants, int aNameIndex, int length) {

		super("ExceptionsAttribute", aNameIndex, length);
		int n = byteArray.readShort();
		exceptions = new int[n];
		for (int i=0; i<n; i+=1) {
			exceptions[i] = byteArray.readShort();
		}
	}

	public ExceptionsAttribute(int length, int aNameIndex,
		int[] inExceptions) {

		super("Exceptions", aNameIndex, length);
		exceptions = inExceptions;
	}

	public int[] getExceptions() {
		return exceptions;
	}

	public void writeDetail(DataOutputStream dsOut) throws IOException {
		int n = exceptions.length;
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			dsOut.writeShort(exceptions[i]);
		}
	}
}
