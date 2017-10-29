
/*
 * $Id: SimpleScanner.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class SimpleScanner extends Scanner {
	private Reader reader;
	private int lineNumber;
	private int charIndex;
	private static final String[] keywords = {
		"if", "else", "while", "return"
	};
	private int[] inputArray;
	private int inputIndex;
	private int available;

	public SimpleScanner(Reader in) {
		reader = in;
		lineNumber = 1;
		charIndex = 1;
		inputArray = new int[8];
		inputIndex = 0;
		available = 0;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.Scanner#nextToken()
	 */
	public Token nextToken() throws IOException {
		Token token = null;
		int tv = Token.UNKNOWN;
		int c = getChar();
		while (Character.isWhitespace((char) c)) {
			c = getChar();
		}
		switch (c) {
			case '{': tv = Token.OPEN_BRACE; break;
			case '}': tv = Token.CLOSE_BRACE; break;
			case '(': tv = Token.OPEN_PAREN; break;
			case ')': tv = Token.CLOSE_PAREN; break;
			case '+': tv = Token.PLUS; break;
			case '-': tv = Token.MINUS; break;
			case '*': tv = Token.STAR; break;
			case '/': tv = Token.SLASH; break;
			case ';': tv = Token.SEMI_COLON; break;
			case ',': tv = Token.COMMA; break;
			case '%': tv = Token.MOD; break;
			case '^': tv = Token.XOR; break;
			case '~': tv = Token.TILDE; break;
			case -1: tv = Token.EOF; break;
			case '<': tv = scanTwoChars('=', Token.LESS_THAN, Token.LESS_THAN_EQUAL); break;
			case '>': tv = scanTwoChars('=', Token.GREATER_THAN, Token.GREATER_THAN_EQUAL); break;
			case '=': tv = scanTwoChars('=', Token.EQUAL, Token.EQUAL_EQUAL); break;
			case '&': tv = scanTwoChars('&', Token.AND, Token.AND_AND); break;
			case '|': tv = scanTwoChars('|', Token.OR, Token.OR_OR); break;
			case '!': tv = scanTwoChars('=', Token.BANG, Token.NOT_EQUAL); break;
		}
		if (tv != Token.UNKNOWN) {
			String text = Token.toString(tv);
			if (c == -1) {
				text = "<<EOF>>";
			}
			if (text == null) {
				text = Character.toString((char) c);
			}
			return new Token(tv, text, lineNumber, charIndex);
		}
		if (Character.isJavaIdentifierStart((char) c)) {
			StringBuffer sb = new StringBuffer();
			while (Character.isJavaIdentifierPart((char) c)) {
				sb.append((char) c);
				c = getChar();
			}
			pushBackChar();
			String text = sb.toString();
			int n = keywords.length;
			for (int i=0; i<n; i+=1) {
				if (text.equals(keywords[i])) {
					return new Token(Token.KEYWORD_START+i, text,
						lineNumber, charIndex);
				}
			}
			return new Token(Token.ID, text, lineNumber, charIndex);
		}
		if (Character.isDigit((char) c)) {
			StringBuffer sb = new StringBuffer();
			while (Character.isDigit((char) c)) {
				sb.append((char) c);
				c = getChar();
			}
			pushBackChar();
			String text = sb.toString();
			return new Token(Token.INT_LITERAL, text, lineNumber, charIndex);
		}
		return new Token(Token.UNKNOWN, "" + (char) c, lineNumber, charIndex);
	}
	
	/**
	 * @param c
	 * @param i
	 * @param j
	 * @return
	 */
	private int scanTwoChars(char c2, int t1, int t2) throws IOException {
		if (getChar() == c2) {
			return t2;
		}
		pushBackChar();
		return t1;
	}

	private int getChar() throws IOException {
		int c;
		if (available < 0) {
			throw new AssertionError("");
		}
		if (available > 0) {
			c = inputArray[inputIndex++];
			inputIndex %= inputArray.length;
			available -= 1;
		} else {
			c = reader.read();
			inputArray[inputIndex++] = c;
			inputIndex %= inputArray.length;
		}
		charIndex += 1;
		// I'm not going to deal with the case of '\r' only for newline
		if (c == '\n') {
			charIndex = 1;
			lineNumber += 1;
		}
		return c;
	}
	
	private void pushBackChar() throws IOException {
		charIndex -= 1;
		inputIndex -= 1;
		if (inputIndex < 0) {
			inputIndex = inputArray.length - 1;
		}
		available += 1;
		int c = inputArray[inputIndex];
		if (c == '\n') {
			lineNumber -= 1;
			charIndex = 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader(args[0]);
		SimpleScanner scanner = new SimpleScanner(fr);
		Token token = scanner.nextToken();
		while (token.getType() != Token.EOF) {
			System.out.println(token);
			token = scanner.nextToken();
		}
		System.out.println(token);
		fr.close();
	}
}
