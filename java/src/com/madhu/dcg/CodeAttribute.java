
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
import java.io.PrintStream;

public class CodeAttribute extends AttributeInfo {
	private int maxStack;
	private int maxLocals;
	private byte code[];
	private ExceptionEntry exceptionTable[];
	private AttributeInfo atts[];

	public CodeAttribute(int length, int aNameIndex, int theMaxStack,
		int theMaxLocals, byte theCode[], ExceptionEntry table[],
		AttributeInfo theAtts[]) {

		super("Code", aNameIndex, length);
		maxStack = theMaxStack;
		maxLocals = theMaxLocals;
		code = theCode;
		exceptionTable = table;
		atts = theAtts;
	}

	public CodeAttribute(ByteArray byteArray,
		ConstantPool constants, int aNameIndex, int length) {

		super("CodeAttribute", aNameIndex, length);
		maxStack = byteArray.readShort();
		maxLocals = byteArray.readShort();
		int n = byteArray.readInt();
		code = new byte[n];
		for (int i=0; i<n; i+=1) {
			code[i] = (byte) byteArray.readByte();
		}
		n = byteArray.readShort();
		exceptionTable = new ExceptionEntry[n];
		for (int i=0; i<n; i+=1) {
			exceptionTable[i] = new ExceptionEntry(byteArray, constants);
		}
		n = byteArray.readShort();
		atts = new AttributeInfo[n];
		for (int i=0; i<n; i+=1) {
			atts[i] = AttributeInfo.get(byteArray, constants);
		}
	}

	public int getMaxStack() {
		return maxStack;
	}

	public int getMaxLocals() {
		return maxLocals;
	}

	public byte[] getCode() {
		return code;
	}

	public ExceptionEntry[] getExceptionTable() {
		return exceptionTable;
	}

	public void writeDetail(DataOutputStream dsOut) throws IOException {
		dsOut.writeShort(maxStack);
		dsOut.writeShort(maxLocals);
		int n = code.length;
		dsOut.writeInt(n);
		for (int i=0; i<n; i+=1) {
			dsOut.writeByte(code[i]);
		}
		n = exceptionTable.length;
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			exceptionTable[i].write(dsOut);
		}
		n = atts.length;
		dsOut.writeShort(n);
		for (int i=0; i<n; i+=1) {
			atts[i].write(dsOut);
		}
	}

	public void printOut(PrintStream psOut) {
		psOut.println("Attribute length " + getLength());
		psOut.println("Max stack " + maxStack);
		psOut.println("Max locals " + maxLocals);
		int n = code.length;
		psOut.println("Code length " + n);
		for (int i=0; i<n; i+=1) {
			if (i > 0 && i % 16 == 0) {
				psOut.println();
			}
			int c = code[i] & 0xff;
			psOut.print(Integer.toHexString(c) + " ");
		}
		psOut.println();
	}
}
