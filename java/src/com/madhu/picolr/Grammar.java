
/*
 * Created on May 19, 2005 10:59:01 AM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr;

import java.util.HashMap;

import com.madhu.picolr.nfa.Scanner;
import com.madhu.picolr.nfa.TerminalFactory;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Grammar<T extends Tree> {
	public static final String EOF = "EOF";
	public static final String WHITESPACE = "WHITESPACE";

	private NonTerminal startSymbol;
	private HashMap<String,GrammarSymbol> symbols;
	private Scanner<Terminal> scanner;
	private int symbolNumber;
	private int productionNumber;

	public Grammar() {
		symbolNumber = 1;
		productionNumber = 1;
		symbols = new HashMap<String,GrammarSymbol>();
		scanner = new Scanner<Terminal>(new TerminalFactory<Terminal>() {
			@Override
			public Terminal create(String name, String text, int line, int column) {
				Terminal t = (Terminal) symbols.get(name);
				t = t.resolve(text);
				t.setLineNumber(line);
				t.setColumnNumber(column);
				return t;
			}
		});
		scanner.setEOFObject(addTerminal(EOF, null));
	}

	public Terminal addTerminal(String name, String pattern) {
		if (symbols.get(name) != null) {
			throw new IllegalArgumentException("Duplicate terminal definition: " + name);
		}
		Terminal t = createTerminal(name);
		if (pattern != null) {
			t.setNFAState(scanner.compile(pattern, name));
		}
		symbols.put(name, t);
		return t;
	}

	public void addTerminal(String name, String rootName, String text) {
		Terminal root = (Terminal) symbols.get(rootName);
		if (root == null) {
			throw new IllegalArgumentException("No such terminal: " + rootName);
		}
		Terminal t = createTerminal();
		t.setName(name);
		t.setText(text);
		t.setNFAState(root.getNFAState());
		root.addChild(t);
		symbols.put(name, t);
	}

	public NonTerminal addProduction(String nonTerminal, String... ruleActions) {
		NonTerminal nt = getNonTerminal(nonTerminal);
		if (startSymbol == null) {
			startSymbol = nt;
		}
		int n = ruleActions.length;
		if ((n & 1) != 0) {
			throw new IllegalArgumentException("odd number of rule/action pairs");
		}
		Production[] prods = new Production[n >> 1];
		for (int i = 0; i < n; i+=2) {
			prods[i>>1] = parse(nt, ruleActions[i], ruleActions[i+1]);
		}
		nt.setProductions(prods);
		return nt;
	}

	/**
	 * @param string
	 */
	public Terminal getTerminal(String name) {
		return (Terminal) symbols.get(name);
	}

	/**
	 * @param nonTerminal
	 * @return
	 */
	public NonTerminal getNonTerminal(String nonTerminal) {
		NonTerminal nt = (NonTerminal) symbols.get(nonTerminal);
		if (nt == null) {
			nt = createNonTerminal(nonTerminal);
			symbols.put(nonTerminal, nt);
		}
		return nt;
	}

	/**
	 * @param sentence
	 * @param rewrite ws delimited list of symbols, first one is tree value
	 * @return
	 */
	private Production parse(NonTerminal lhs, String sentence, String rewrite) {
		if (sentence.trim().length() == 0) {
			return createProduction(lhs, new GrammarSymbol[0], "", new int[0]);
		}
		String[] strings = sentence.split("[ ]+");
		int n = strings.length;
		GrammarSymbol[] rhs = new GrammarSymbol[n];
		HashMap<String,Integer> indexMap = new HashMap<String, Integer>();
		for (int i = 0; i < n; i += 1) {
			String name = strings[i];
			String key = name;
			int eq = name.indexOf('=');
			if (eq > 0 && name.length() > 3) {
				key = name.substring(0, eq);
				name = name.substring(eq+1);
			}
			GrammarSymbol symbol = (GrammarSymbol) symbols.get(name);
			if (symbol == null) {
				symbol = getNonTerminal(name);
			}
			rhs[i] = symbol;
			if (indexMap.put(key, i) != null) {
				throw new IllegalArgumentException("duplicate key/symbol name: " + key);
			}
		}
		strings = rewrite.split("[ ]+");
		Object astValue = indexMap.get(strings[0]);
		if (astValue == null) {
			astValue = strings[0];
		}
		n = strings.length;
		int[] rewriteRule = new int[n-1];
		for (int i = 1; i < n; i += 1) {
			Integer index = indexMap.get(strings[i]);
			if (index == null) {
				throw new IllegalArgumentException(String.format(
					"Unknown rewrite symbol '%s' in rule '%s -> %s'", strings[i], lhs, sentence));
			}
			rewriteRule[i-1] = index;
		}
		return createProduction(lhs, rhs, astValue, rewriteRule);
	}

	private Production createProduction(NonTerminal lhs, GrammarSymbol[] rhs,
			Object astValue, int[] rewriteRule) {
		return new Production(productionNumber++, lhs, rhs, astValue, rewriteRule);
	}

	private NonTerminal createNonTerminal(String nonTerminal) {
		return new NonTerminal(symbolNumber++, nonTerminal);
	}

	public Terminal createTerminal(String name) {
		return new Terminal(symbolNumber++, name);
	}

	public Terminal createTerminal() {
		return new Terminal(symbolNumber++);
	}

	public NonTerminal setStartSymbol(String nonTerminal) {
		return startSymbol = getNonTerminal(nonTerminal);
	}

	public Scanner<Terminal> getScanner() {
		return scanner;
	}

	public Parser<T> getParser() {
		String sname = startSymbol.getName();
		NonTerminal augStart = addProduction("S'",
			new String[] {sname, sname});
		Item ki = new Item(augStart.getProductions()[0]);

		Terminal whitespace = (Terminal) symbols.get(WHITESPACE);
		StateList stateList = new StateList(whitespace);

		State startState = new State(this, stateList, ki, ki);
		scanner.clear();
		return new Parser<T>(this, startState, augStart, whitespace);
	}

	public int getSymbolCount() {
		return symbolNumber;
	}
}
