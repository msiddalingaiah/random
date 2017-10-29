
/*
 * $Id: Token.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Token {
	public static final int UNKNOWN = -1;

	public static final int KEYWORD_START = 1;
	public static final int IF = 1;
	public static final int ELSE = 2;
	public static final int WHILE = 3;
	public static final int RETURN = 4;

	public static final int INT_LITERAL = 51;

	public static final int OPEN_BRACE = 101;
	public static final int CLOSE_BRACE = 102;
	public static final int OPEN_PAREN = 103;
	public static final int CLOSE_PAREN = 104;

	public static final int EQUAL = 145;
	public static final int SEMI_COLON = 150;
	public static final int COMMA = 151;

	public static final int ID = 200;
	public static final int EOF = 201;

	public static final int OPERATOR_OFFSET = 305;
	public static final int PLUS = 305;
	public static final int MINUS = 306;
	public static final int STAR = 307;
	public static final int SLASH = 308;
	public static final int LESS_THAN = 309;
	public static final int GREATER_THAN = 310;
	public static final int LESS_THAN_EQUAL = 311;
	public static final int GREATER_THAN_EQUAL = 312;
	public static final int AND = 313;
	public static final int OR = 314;
	public static final int AND_AND = 315;
	public static final int OR_OR = 316;
	public static final int EQUAL_EQUAL = 317;
	public static final int MOD = 318;
	public static final int XOR = 319;
	public static final int BANG = 320;
	public static final int TILDE = 321;
	public static final int NOT_EQUAL = 322;

	private static String[] OP_MAP = {
		"+", "-", "*", "/", "<", ">", "<=", ">=",
		"&", "|", "&&", "||", "==", "%", "^", "!",
		"~", "!="
	};

	private int type;
	private String text;
	
	/**
	 * The starting line number (1 based)
	 */
	private int lineNumber;
	
	/**
	 * The starting character index (1 based)
	 */
	private int charIndex;
	
	public Token(int value, String content, int lineNumber, int charIndex) {
		this.type = value;
		this.text = content;
		this.lineNumber = lineNumber;
		this.charIndex = charIndex;
	}
	/**
	 * @return
	 */
	public int getCharIndex() {
		return charIndex;
	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
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
	public int getType() {
		return type;
	}
	
	public String toString() {
		String t1 = text;
		switch (type) {
			case UNKNOWN: t1 = "<<UNKNOWN>>"; break;
			case EOF: t1 = "<<EOF>>"; break;
			case ID: t1 = text + " (ID)"; break;
			case INT_LITERAL: t1 = text + " (int)"; break;
		}
		return t1 + ", line " + lineNumber + ":" + charIndex;
	}
	/**
	 * @param operator
	 * @return
	 */
	public static String toString(int type) {
		try {
			return OP_MAP[type-OPERATOR_OFFSET];
		} catch (RuntimeException e) {
			return null;
		}
	}
}
