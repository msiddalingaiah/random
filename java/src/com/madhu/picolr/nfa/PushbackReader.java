
/*
 * Created on Apr 18, 2005 11:35:43 AM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr.nfa;

import java.io.IOException;
import java.io.Reader;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class PushbackReader {
	public static final int LPAREN = -101;
	public static final int RPAREN = -102;
	public static final int DASH = -103;
	public static final int OR = -104;
	public static final int STAR = -105;
	public static final int PLUS = -106;
	public static final int QUESTION = -107;

	private int[] inputArray;
	private int inputIndex;
	private int available;
	private Reader in;
	private boolean specialChars;

	public PushbackReader(Reader in) {
		this(in, false);
	}
	
	public PushbackReader(Reader in, boolean specialChars) {
		this.in = in;
		this.specialChars = specialChars;
		inputArray = new int[16];
	}
	
	public int read() throws IOException {
		int c;
		if (available > 0) {
			c = inputArray[inputIndex++];
			inputIndex %= inputArray.length;
			available -= 1;
		} else {
			c = in.read();
			if (specialChars) {
				switch (c) {
					case '(': c = LPAREN; break;
					case ')': c = RPAREN; break;
					case '-': c = DASH; break;
					case '|': c = OR; break;
					case '*': c = STAR; break;
					case '+': c = PLUS; break;
					case '?': c = QUESTION; break;
					case '\\': c = escape(); break;
				}
			}
			inputArray[inputIndex++] = c;
			inputIndex %= inputArray.length;
		}
		return c;
	}

	private int escape() throws IOException {
		int c;
		c = in.read();
		switch (c) {
			case 'n': c = '\n'; break;
			case 't': c = '\t'; break;
			case 'b': c = '\b'; break;
			case 'r': c = '\r'; break;
			case 'f': c = '\f'; break;
			case 'u':
				c = 0;
				for (int i=0; i<4; i+=1) {
					int d = in.read();
					if (d >= '0' && d <= '9') {
						d -= '0';
					} else if (d >= 'a' && d <= 'f') {
						d -= 'a' - 10;
					} else if (d >= 'A' && d <= 'F') {
						d -= 'A' - 10;
					} else {
						throw new IllegalArgumentException(
							"Unicode escape must have 4 hex digits");
					}
					c <<= 4;
					c |= d;
				}
				break;
			case -1:
				throw new IllegalArgumentException(
					"Unterminated escape sequence " + c);
		}
		return c;
	}

	public void unread() {
		inputIndex -= 1;
		if (inputIndex < 0) {
			inputIndex = inputArray.length - 1;
		}
		available += 1;
	}
	
	public void close() throws IOException {
		in.close();
	}
}
