
package com.madhu.ws;

/**
 * Base64 encode/decode routines. Converts bytes to base 64 encoded
 * Strings and base 64 encoded Strings to bytes.
 *
 * @author Madhu Siddalingaiah
 */
public class Base64 {
	private static final char intToBase64[] = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	};

	private static final byte base64ToInt[] = {
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		-1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
		55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
		5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
		24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34,
		35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
	};

	public static String bytesToBase64(byte[] a) {
		int length = a.length;
		int lengthDiv3 = length/3;
		int remainingBytes = length - 3*lengthDiv3;
		int resultLen = 4*((length + 2)/3);
		StringBuffer result = new StringBuffer(resultLen);

		int index = 0;
		for (int i=0; i<lengthDiv3; i++) {
			int byte0 = a[index++] & 0xff;
			int byte1 = a[index++] & 0xff;
			int byte2 = a[index++] & 0xff;
			result.append(intToBase64[byte0 >> 2]);
			result.append(intToBase64[(byte0 << 4)&0x3f | (byte1 >> 4)]);
			result.append(intToBase64[(byte1 << 2)&0x3f | (byte2 >> 6)]);
			result.append(intToBase64[byte2 & 0x3f]);
		}

		if (remainingBytes != 0) {
			int byte0 = a[index++] & 0xff;
			result.append(intToBase64[byte0 >> 2]);
			if (remainingBytes == 1) {
				result.append(intToBase64[(byte0 << 4) & 0x3f]);
				result.append("==");
			} else {
				int byte1 = a[index++] & 0xff;
				result.append(intToBase64[(byte0 << 4)&0x3f | (byte1 >> 4)]);
				result.append(intToBase64[(byte1 << 2)&0x3f]);
				result.append('=');
			}
		}
		return result.toString();
	}

	public static byte[] base64ToBytes(String s) {
		int length = s.length();
		int numGroups = length / 4;
		if (4*numGroups != length) {
			throw new IllegalArgumentException(
				"String length must be a multiple of four.");
		}
		int nPadBytes = 0;
		int numFullGroups = numGroups;
		if (length != 0) {
			if (s.charAt(length-1) == '=') {
				nPadBytes++;
				numFullGroups--;
			}
			if (s.charAt(length-2) == '=')
				nPadBytes++;
		}
		byte[] result = new byte[3*numGroups - nPadBytes];

		int index = 0, outCursor = 0;
		for (int i=0; i<numFullGroups; i++) {
			int ch0 = base64toInt(s.charAt(index++));
			int ch1 = base64toInt(s.charAt(index++));
			int ch2 = base64toInt(s.charAt(index++));
			int ch3 = base64toInt(s.charAt(index++));
			result[outCursor++] = (byte) ((ch0 << 2) | (ch1 >> 4));
			result[outCursor++] = (byte) ((ch1 << 4) | (ch2 >> 2));
			result[outCursor++] = (byte) ((ch2 << 6) | ch3);
		}

		if (nPadBytes != 0) {
			int ch0 = base64toInt(s.charAt(index++));
			int ch1 = base64toInt(s.charAt(index++));
			result[outCursor++] = (byte) ((ch0 << 2) | (ch1 >> 4));

			if (nPadBytes == 1) {
				int ch2 = base64toInt(s.charAt(index++));
				result[outCursor++] = (byte) ((ch1 << 4) | (ch2 >> 2));
			}
		}
		return result;
	}

	private static int base64toInt(char c) {
		int result = base64ToInt[c];
		if (result < 0) {
			throw new IllegalArgumentException("Illegal character " + c);
		}
		return result;
	}
}
