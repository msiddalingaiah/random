
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

public class Utf8Entry extends ConstantEntry {
	private String value;

	public Utf8Entry(String aValue) {
		value = aValue;
	}

	public String getValue() {
		return value;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeByte(ConstantEntry.CP_Utf8);
		dsOut.writeShort(value.length());
		// This is bogus, need to handle Unicode properly
		dsOut.writeBytes(value);
	}

	public boolean equals(String aValue) {
		 return aValue.equals(value);
	}

	public String toString() {
		return "Utf8: " + value;
	}
}

