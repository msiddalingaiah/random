
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

public class FloatEntry extends ConstantEntry {
	private int floatBits;

	public FloatEntry(int aFloatBits) {
		floatBits = aFloatBits;
	}

	public int getFloatBits() {
		return floatBits;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeByte(ConstantEntry.CP_Float);
		dsOut.writeInt(floatBits);
	}

	public String toString() {
		return "float bits: " + floatBits;
	}
}

