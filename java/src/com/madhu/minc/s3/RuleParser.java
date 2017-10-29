
package com.madhu.minc.s3;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.madhu.minc.s2.Token;

public class RuleParser {
	private Scanner scanner;
	private HashMap rules;
	private String terminals;
	
	public RuleParser(Scanner scanner, String terminals) {
		this.scanner = scanner;
		rules = new HashMap();
		this.terminals = terminals;
	}
	
	public void parse(String input) throws IOException {
		parse(new StringReader(input));
	}
	
	public void parse(Reader input) throws IOException {
		scanner.setInput(input);
		Token t = scanner.getNextToken();
		if (t.getType() != Token.ID) {
			throw new IllegalArgumentException("Nonterminal expected " + t);
		}
		String nt = t.getText();
		if (isTerminal(nt)) {
			throw new IllegalArgumentException("Not a non-terminal " + t);
		}
		NonTerminal lhs = new NonTerminal(t.getText());
		ArrayList list = (ArrayList) rules.get(lhs);
		if (list == null) {
			list = new ArrayList();
			rules.put(lhs, list);
		}
		Tree sentence = parseTree();
		t = scanner.getNextToken();
		if (t.getType() != Token.COLON) {
			throw new IllegalArgumentException(": expected " + t);
		}
		t = scanner.getNextToken();
		if (t.getType() != Token.INT_LITERAL) {
			throw new IllegalArgumentException("number expected " + t);
		}
		int cost = Integer.parseInt(t.getText());
		list.add(new Rule(lhs, sentence, cost));
	}

	private boolean isTerminal(String name) {
		return terminals.indexOf(name) >= 0;
	}

	private Tree parseTree() throws IOException {
		Token t = scanner.getNextToken();
		if (t.getType() != Token.ID) {
			throw new IllegalArgumentException("name expected " + t);
		}
		String name = t.getText();
		Symbol s;
		if (isTerminal(name)) {
			s = new Terminal(name);
		} else {
			s = new NonTerminal(name);
		}
		Tree tree = new Tree(s);
		t = scanner.getNextToken();
		if (t.getType() == Token.COLON || t.getType() == Token.RIGHT_PAREN) {
			scanner.pushbackToken();
			return tree;
		}
		if (t.getType() != Token.LEFT_PAREN) {
			throw new IllegalArgumentException("( expected " + t);
		}
		do {
			tree.addChild(parseTree());
			t = scanner.getNextToken();
			scanner.pushbackToken();
		} while (t.getType() == Token.ID);
		return tree;
	}
	
	public HashMap getRules() {
		return rules;
	}
	
	// TODO this is broken
	public static void main(String[] args) throws IOException {
		RuleParser rp = new RuleParser(new Scanner(), "PLUS CONST");
		rp.parse("reg CONST : 0");
		rp.parse("reg reg : 0");
		rp.parse("reg mem : 1");
		rp.parse("reg PLUS(reg reg) : 0");
//		rp.parse("reg: PLUS(reg CONST) : 0");
//		rp.parse("reg: PLUS(CONST reg) : 0");
//		rp.parse("reg: PLUS(mem reg) : 1");
//		rp.parse("reg: PLUS(reg mem) : 1");
	}
}
