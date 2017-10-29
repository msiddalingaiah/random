
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 24, 2006
 */

package com.madhu.minc.s2;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Scanner {
	private Reader reader;
	private Token[] tokenBuffer;
	private int readIndex;
	private int writeIndex;
	private int lastChar;
	private boolean useLastChar;
	private int lineNumber;
	private int charPosition;

	public Scanner(Reader reader) {
		this.reader = reader;
		this.lineNumber = 1;
		this.charPosition = 1;
		tokenBuffer = new Token[8];
	}

	public Token getNextToken() throws IOException {
		if (readIndex != writeIndex) {
			Token t = tokenBuffer[readIndex++];
			readIndex %= tokenBuffer.length;
			return t;
		}
		Token t = doGetNextToken();
		tokenBuffer[writeIndex++] = t;
		writeIndex %= tokenBuffer.length;
		readIndex = writeIndex;
		return t;
	}
	
	public void pushbackToken() {
		readIndex -= 1;
		if (readIndex < 0) {
			readIndex = tokenBuffer.length - 1;
		}
	}

	private Token doGetNextToken() throws IOException {
		// skip whitespace characters (e.g. blanks, tabs, newlines etc.)
		int c = read();
		while (Character.isWhitespace((char) c)) {
			c = read();
		}
		switch (c) {
			case -1: return new Token(Token.EOF, "<EOF>", lineNumber, charPosition);
			case '+': return new Token(Token.PLUS, "+", lineNumber, charPosition);
			case '-': return new Token(Token.MINUS, "-", lineNumber, charPosition);
			case '*': return new Token(Token.STAR, "*", lineNumber, charPosition);
			case '/': return new Token(Token.SLASH, "/", lineNumber, charPosition);
			case '%': return new Token(Token.PERCENT, "%", lineNumber, charPosition);
			case '(': return new Token(Token.LEFT_PAREN, "(", lineNumber, charPosition);
			case ')': return new Token(Token.RIGHT_PAREN, ")", lineNumber, charPosition);
			case ',': return new Token(Token.COMMA, ",", lineNumber, charPosition);
			case '&': return doubleChar('&', Token.AND, Token.ANDAND, "&", "&&");
			case '|': return doubleChar('|', Token.OR, Token.OROR, "&", "&&");
			case '=': return doubleChar('=', Token.EQ, Token.EQEQ, "=", "==");
			case '>':
				c = read();
				if (c == '=') {
					return new Token(Token.GE, ">=", lineNumber, charPosition);
				}
				if (c == '>') {
					return new Token(Token.RIGHT_SHIFT, ">>", lineNumber, charPosition);
				}
				unread();
				return new Token(Token.GT, ">", lineNumber, charPosition);
			case '<':
				c = read();
				if (c == '=') {
					return new Token(Token.LT, "<=", lineNumber, charPosition);
				}
				if (c == '<') {
					return new Token(Token.LEFT_SHIFT, "<<", lineNumber, charPosition);
				}
				unread();
				return new Token(Token.LE, "<", lineNumber, charPosition);
		}
		if (c == '\'') {
			c = read();
			StringBuffer sb = new StringBuffer();
			while (c != '\'' && c >= ' ') {
				sb.append((char) c);
				c = read();
			}
			if (c != '\'') {
				throw new IllegalArgumentException("Unterminated string at " +
					lineNumber + ":" + charPosition);
			}
			String text = sb.toString();
			return new Token(Token.STRING_LITERAL, text, lineNumber, charPosition);
		}
		if (Character.isDigit((char) c)) {
			StringBuffer sb = new StringBuffer();
			while (Character.isDigit((char) c)) {
				sb.append((char) c);
				c = read();
			}
			unread();
			return new Token(Token.INT_LITERAL, sb.toString(), lineNumber, charPosition);
		}
		if (Character.isJavaIdentifierStart((char) c)) {
			StringBuffer sb = new StringBuffer();
			while (Character.isJavaIdentifierPart((char) c) || c == '.') {
				sb.append((char) c);
				c = read();
			}
			unread();
			String id = sb.toString();
			return new Token(Token.ID, id, lineNumber, charPosition);
		}
		throw new IllegalArgumentException("Unexpected character: " + (char) c +
			" at " + lineNumber + ":" + charPosition);
	}

	private Token doubleChar(char c2, int t1, int t2, String s1, String s2) throws IOException {
		int c = read();
		if (c == c2) {
			return new Token(t2, s2, lineNumber, charPosition);
		}
		unread();
		return new Token(t1, "" + s1, lineNumber, charPosition);
	}

	/**
	 * @param c
	 */
	private void unread() {
		useLastChar = true;
	}

	/**
	 * @return
	 */
	private int read() throws IOException {
		if (useLastChar) {
			useLastChar = false;
			return lastChar;
		}
		lastChar = reader.read();
		charPosition += 1;
		if (lastChar == '\n') {
			lineNumber += 1;
			charPosition = 1;
		}
		return lastChar;
	}

	// This is for testing...
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("test.in");
		Scanner sc = new Scanner(fr);
		Token t = sc.getNextToken();
		while (t.getType() != Token.EOF) {
			System.out.println(t.getType() + ": " + t);
			t = sc.getNextToken();
		}
		fr.close();
	}
}
