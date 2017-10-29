
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

public class ConstantValueAttribute extends AttributeInfo {
	private ConstantEntry constant;
	private int constantIndex;

	public ConstantValueAttribute(ByteArray byteArray,
		ConstantPool constants, int aNameIndex, int length) {

		super("ConstantValueAttribute", aNameIndex, length);
		int n = byteArray.readShort();
		constantIndex = n;
		constant = constants.getEntry(n);
	}

	public ConstantEntry getConstant() {
		return constant;
	}

	public void writeDetail(DataOutputStream dsOut) throws IOException {
		dsOut.writeShort(constantIndex);
	}
}
