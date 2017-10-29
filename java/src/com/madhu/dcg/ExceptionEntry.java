
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

public class ExceptionEntry {
	private int startPC;
	private int endPC;
	private int handlerPC;
	private int catchType;
	private ClassEntry exception;

	public ExceptionEntry(ByteArray byteArray,
		ConstantPool constants) {

		startPC = byteArray.readShort();
		endPC = byteArray.readShort();
		handlerPC = byteArray.readShort();
		catchType = byteArray.readShort();
		if (catchType != 0) {
			exception = (ClassEntry) constants.getEntry(catchType);
		} else {
			exception = null;
		}
	}

	public int getStartPC() {
		return startPC;
	}

	public int getEndPC() {
		return endPC;
	}

	public int getHandlerPC() {
		return handlerPC;
	}

	public int getCatchType() {
		return catchType;
	}

	public ClassEntry getException() {
		return exception;
	}

	public void write(DataOutputStream dsOut) throws IOException {
		dsOut.writeShort(startPC);
		dsOut.writeShort(endPC);
		dsOut.writeShort(handlerPC);
		dsOut.writeShort(catchType);
	}

	public String toString() {
		if (exception != null) {
			return "ExceptionEntry: " + exception.getClassName();
		} else {
			return "ExceptionEntry: (all exceptions)";
		}
	}
}
