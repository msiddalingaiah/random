
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

public class ByteArray {
	public final static boolean isBigEndian = false;

	private byte byteArray[];
	private int index;
	private int length;

	public ByteArray(byte aByteArray[]) {
		byteArray = aByteArray;
		index = 0;
		length = aByteArray.length;
	}

	public int readByte() {
		if (index >= length)
			throw new ClassFormatError("readByte: unexpected end of data");
		return byteArray[index++] & 0x0ff;
	}

	public int readShort() {
		int value = 0;

		for (int i = 0; i < 2; i += 1) {
			value <<= 8;
			value |= readByte();
		}
		return value;
	}

	public int readInt() {
		int value = 0;

		for (int i = 0; i < 4; i += 1) {
			value <<= 8;
			value |= readByte();
		}
		return value;
	}

	public long readLong() {
		long value = 0;

		for (int i = 0; i < 8; i += 1) {
			value <<= 8;
			value |= readByte();
		}
		return value;
	}

	public float readFloat() {
		return Float.intBitsToFloat(readInt());
	}

	public double readDouble() {
		return Double.longBitsToDouble(readLong());
	}
}
