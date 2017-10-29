
/*
 * Created on Apr 7, 2005
 *
 * Copyright (c) 2005 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.lisp;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class LispReader {
	private Reader input;
	private int[] buffer;
	private int readIndex;
	private int writeIndex;
	private int count;

	public LispReader(Reader in) {
		input = in;
		buffer = new int[10];
		readIndex = 0;
		writeIndex = 0;
		count = 0;
	}

	public int read() throws IOException {
		if (count > 0) {
			int c = buffer[readIndex++];
			readIndex %= buffer.length;
			count -= 1;
			return c;
		}
		return input.read();
	}

	public int readNonWhiteSpace() throws IOException {
		int c = read();
		while (Character.isWhitespace((char) c)) {
			c = read();
		}
		return c;
	}

	public void unread(int c) {
		count += 1;
		buffer[writeIndex++] = c;
		writeIndex %= buffer.length;
	}

	public LispObject readObject() throws IOException {
		int c = readNonWhiteSpace();
		if (c == '(') {
			ListObject head = new ListObject();
			ListObject tail = head;
			c = readNonWhiteSpace();
			unread(c);
			while (c != ')' && c != -1) {
				tail.cons(new ListObject(readObject()));
				tail = tail.cdr();
				c = readNonWhiteSpace();
				unread(c);
			}
			if (c != ')') {
				throw new AssertionError(") expected");
			}
			ListObject object = head.cdr();
			if (object == null) {
				object = head;
			}
			System.out.println("ListObject(): " + object);
			return object;
		}
		if (c >= '0' && c <= '9') {
			StringBuffer sb = new StringBuffer();
			while (c >= '0' && c <= '9') {
				sb.append((char) c);
				c = read();
			}
			unread(c);
			IntegerObject object = new IntegerObject(sb.toString());
			System.out.println("ListObject#: " + object);
			return object;
		}
		throw new IllegalArgumentException("unexpected character: " + c);
	}

	public static void main(String[] args) throws IOException {
		StringReader sr = new StringReader("(1 2 ())");
		LispReader reader = new LispReader(sr);
		LispObject object = reader.readObject();
		System.out.println(object);
	}
}
