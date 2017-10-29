
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

public class MethodRefEntry extends MemberEntry {
	public MethodRefEntry(int aClassIndex, int aNameAndTypeIndex) {
		super(aClassIndex, aNameAndTypeIndex);
	}

	public String getType() {
		return "MethodRef";
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeByte(ConstantEntry.CP_Methodref);
		dsOut.writeShort(getClassIndex());
		dsOut.writeShort(getNameAndTypeIndex());
	}
}
