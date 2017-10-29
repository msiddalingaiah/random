
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 24, 2006
 */

package com.madhu.minc.s2;

import java.io.FileReader;
import java.io.IOException;

public class Parser {
	private Scanner scanner;
	
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void parse() throws IOException {
		parseExpression();
		Token t = scanner.getNextToken();
		if (t.getType() != Token.EOF) {
			error("Expected end-of-file", t);
		}
	}

	/**
	 * The "and" and "or" operations result in a boolean value.
	 * This could be represented as simply integer 0 or 1, in which
	 * case, Java &, | operators could be used at runtime.
	 * 
	 * expression -> level1 (("and" | "or") level0)*
	 */
	private void parseExpression() throws IOException {
		level1();
		Token t = scanner.getNextToken();
		if (t.getType() == Token.AND || t.getType() == Token.OR) {
			while (t.getType() == Token.AND || t.getType() == Token.OR) {
				level1();
				write(t.getText());
				t = scanner.getNextToken();
			}
		}
		scanner.pushbackToken();
	}

	/**
	 * level1 -> level2 (( "=" | "<" | ">" | "<=" | ">=" | "<>") level2)*
	 * level1 -> VARIABLE "like" STRING_LITERAL
	 * level1 -> VARIABLE "in" "(" STRING_LITERAL (, STRING_LITERAL)* ")"
	 * level1 -> VARIABLE "in" "(" INT_LITERAL (, INT_LITERAL)* ")"
	 * level1 -> VARIABLE "in" "(" DATE_LITERAL (, DATE_LITERAL)* ")"
	 * level1 -> VARIABLE "between" "(" STRING_LITERAL (, STRING_LITERAL)* ")"
	 * level1 -> VARIABLE "between" "(" INT_LITERAL (, INT_LITERAL)* ")"
	 * level1 -> VARIABLE "between" "(" DATE_LITERAL (, INT_LITERAL)* ")"
	 */
	private void level1() throws IOException {
		String leftType = level2();
		Token t = scanner.getNextToken();
		if (t.getType() == Token.EQ || t.getType() == Token.LT ||
			t.getType() == Token.GT || t.getType() == Token.LE ||
			t.getType() == Token.GE || t.getType() == Token.NE) {
			while (t.getType() == Token.EQ || t.getType() == Token.LT ||
				t.getType() == Token.GT || t.getType() == Token.LE ||
				t.getType() == Token.GE || t.getType() == Token.NE) {
				String rightType = level2();
				if (leftType.equals("<unknown>")) {
					write(rightType + " " + t.getText());
				} else if (rightType.equals("<unknown>")) {
					write(leftType + " " + t.getText());
				} else if (!leftType.equals(rightType)) {
					write(leftType + " " + t.getText());
				}
				t = scanner.getNextToken();
			}
		}
		scanner.pushbackToken();
	}

	/**
	 * level2 = STRING_LITERAL | INT_LITERAL | VARIABLE | "(" expression ")"
	 * level2 = "not" expression
	 * 
	 * @return the expression type, e.g. string, date, integer
	 */
	private String level2() throws IOException {
		Token t = scanner.getNextToken();
		switch (t.getType()) {
			case Token.STRING_LITERAL:
				write("load string '" + t.getText() + "'");
				return "string";
			case Token.INT_LITERAL:
				write("load integer " + t.getText());
				return "integer";
			case Token.ID:
				write("load variable " + t.getText());
				// We should get the variable type from the database column type, I
				// just don't have that info here...
				return "<unknown>";
			case Token.NOT:
				parseExpression();
				write("not");
				// This is really a boolean, but we can treat booleans as integer 0 or 1
				return "integer";
			case Token.LEFT_PAREN:
				parseExpression();
				t = scanner.getNextToken();
				if (t.getType() != Token.RIGHT_PAREN) {
					error("Missing )", t);
				}
				// This is really a boolean, but we can treat booleans as integer 0 or 1
				return "integer";
		}
		error("Syntax error", t);
		return null; // make the compiler happy...
	}

	/**
	 * Right now, this method prints out a human-readable operation.
	 * In production, the operation could be formatted more efficiently,
	 * e.g. operation codes that are easier to interpret.
	 * 
	 * TODO rewrite this method to insert operation into the "rule" database table
	 * 
	 * @param operation single operation to perform at runtime
	 */
	private void write(String operation) {
		System.out.println(operation);
	}

	/**
	 * @param string
	 * @param t
	 */
	private void error(String string, Token t) {
		throw new IllegalArgumentException(string + ", " + t);
	}

	public static void main(String[] args) throws IOException {
		String fileName = "test.in";
		if (args.length > 0) {
			fileName = args[0];
		}
		FileReader fr = new FileReader(fileName);
		Scanner sc = new Scanner(fr);
		Parser p = new Parser(sc);
		try {
			p.parse();
		} catch (IllegalArgumentException e) {
			System.out.println("Syntax error: " + e.getMessage());
		}
		fr.close();
	}
}
