
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 24, 2006
 */

package com.madhu.minc.s2;

public class Token {
	public static final int EOF = -1;

	public static final int PLUS = 10;
	public static final int MINUS = 11;
	public static final int STAR = 12;
	public static final int SLASH = 13;
	public static final int PERCENT = 14;

	public static final int AND = 20;
	public static final int OR = 21;
	public static final int LEFT_SHIFT = 22;
	public static final int RIGHT_SHIFT = 23;

	public static final int ANDAND = 30;
	public static final int OROR = 31;
	public static final int LT = 32;
	public static final int GT = 33;
	public static final int LE = 34;
	public static final int GE = 35;
	public static final int EQ = 36;
	public static final int NE = 37;
	public static final int EQEQ = 38;
	public static final int NOT = 39;

	public static final int LEFT_PAREN = 50;
	public static final int RIGHT_PAREN = 51;

	public static final int ID = 60;
	public static final int COMMA = 61;
	public static final int DOT = 62;
	public static final int QUESTION = 63;
	public static final int COLON = 64;

	public static final int INT_LITERAL = 70;
	public static final int STRING_LITERAL = 71;

	private int type;
	private String text;
	private int lineNumber;
	private int charPosition;

	public Token(int type, String text, int lineNumber, int charPosition) {
		this.type = type;
		this.text = text;
		this.lineNumber = lineNumber;
		this.charPosition = charPosition;
	}
	/**
	 * @return
	 */
	public int getCharPosition() {
		return charPosition;
	}

	/**
	 * @return
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param i
	 */
	public void setCharPosition(int i) {
		charPosition = i;
	}

	/**
	 * @param i
	 */
	public void setLineNumber(int i) {
		lineNumber = i;
	}

	/**
	 * @param string
	 */
	public void setText(String string) {
		text = string;
	}

	/**
	 * @return
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param i
	 */
	public void setType(int i) {
		type = i;
	}
	
	public String toString() {
		return text + " - line " + lineNumber + ":" + charPosition;
	}
}
