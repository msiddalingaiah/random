
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

public class DoubleEntry extends ConstantEntry {
	private int MSW, LSW;

	public DoubleEntry(int anMSW, int anLSW) {
		MSW = anMSW;
		LSW = anLSW;
	}

	public int getMSW() {
		return MSW;
	}

	public int getLSW() {
		return LSW;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeByte(ConstantEntry.CP_Double);
		dsOut.writeInt(MSW);
		dsOut.writeInt(LSW);
	}

	public String toString() {
		return "double MSW, LSW: " + MSW + ", " + LSW;
	}
}
