
/*
 * Created on May 19, 2005 1:45:29 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr;

import java.io.IOException;
import java.io.Reader;
import java.util.Stack;

import com.madhu.picolr.nfa.NFAState;
import com.madhu.picolr.nfa.Scanner;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Parser<T extends Tree> {
	private Terminal whitespace;
	private NonTerminal startSymbol;
	private Grammar<T> grammar;
	private State startState;
	private Stack<State> stateStack;
	private Stack<T> astStack;
	private TreeFactory<T> treeFactory;

	/**
	 * @param startSymbol
	 * @param startState
	 */
	public Parser(Grammar<T> grammar, State startState, NonTerminal startSymbol,
		Terminal whitespace) {

		this.startState = startState;
		this.startSymbol = startSymbol;
		this.grammar = grammar;
		this.whitespace = whitespace;
		stateStack = new Stack<State>();
		astStack = new Stack<T>();
	}

	public void setTreeFactory(TreeFactory<T> tf) {
		treeFactory = tf;
	}

	public T parse(Reader in) throws IOException {
		if (treeFactory == null) {
			throw new AssertionError("TreeFactory is not set");
		}
		Scanner<Terminal> scanner = grammar.getScanner();
		scanner.setReader(in);
		scanner.clear();
		return doParse();
	}

	private T doParse() throws IOException {
		State state = startState;
		stateStack.push(startState);
		
		parseLoop:
		while (true) {
			Terminal t = getNextTerminal(state);
			while (t == null) {
				state = reduce(state);
				if (state == null) {
					break parseLoop;
				}
				t = getNextTerminal(state);
			}

			State next = state.getNextState(t);

			while (next == null) {
				state = reduce(state);
				if (state == null) {
					break parseLoop;
				}
				next = state.getNextState(t);
			}
		
			// shift
			stateStack.push(next);
			astStack.push(treeFactory.create(t));

			state = next;
		}
		return astStack.pop();
	}

	private Terminal getNextTerminal(State state) throws IOException {
		NFAState nfa = state.getNFA();
		Scanner<Terminal> scanner = grammar.getScanner();
		scanner.setStart(nfa);
		Terminal t = scanner.nextToken();
		if (t != null && t.getName().equals(whitespace.getName())) {
			t = scanner.nextToken();
		}
		return t;
	}

	/**
	 * 
	 */
	private State reduce(State state) {
		Production red = state.getReduction();
		if (red == null) {
			throw new IllegalArgumentException(getErrorMessage(state));
		}
		T result = doAction(red);
		GrammarSymbol[] symbols = red.getRHS();
		for (int i = 0; i < symbols.length; i++) {
			stateStack.pop();	// State
			astStack.pop();		// value
		}
		NonTerminal nt = red.getLHS();
		state = stateStack.peek();
		if (state == startState && nt.equals(startSymbol)) {
			// accept
			astStack.push(result);
			return null;
		}
		State next = state.getNextState(nt);

		stateStack.push(next);
		astStack.push(result);
		return next;
	}

	private String getErrorMessage(State state) {
		Scanner<Terminal> scanner = grammar.getScanner();
		StringBuffer sb = new StringBuffer();
		sb.append("Unexpected input at line ");
		sb.append(Integer.toString(scanner.getLineNumber()));
		sb.append(':');
		sb.append(Integer.toString(scanner.getColumnNumber()));
		sb.append(" in state ");
		sb.append(state.toString());
		return sb.toString();
	}

	/**
	 * @param red
	 * @return
	 */
	private T doAction(Production red) {
		GrammarSymbol[] rhs = red.getRHS();
		int n = rhs.length;
		int offset = astStack.size() - n;
		T result;
		Object astValue = red.getASTValue();
		if (astValue instanceof String) {
			String sval = (String) astValue;
			if (sval.equals("null")) {
				result = treeFactory.create(null);
			} else {
				result = treeFactory.create(grammar.createTerminal((String) astValue));
			}
		} else if (astValue instanceof Integer) {
			int i = (Integer) astValue;
			result = astStack.get(offset + i);
		} else {
			throw new AssertionError("unexpected astValue: " + astValue);
		}

		int[] include = red.getRewriteRule();
		for (int i : include) {
			T child = astStack.get(offset + i);
			if (child.getValue() == null) {
				n = child.size();
				for (int j=0; j<n; j+=1) {
					result.add(child.get(j));
				}
			} else {
				result.add(child);
			}
		}
		return result;
	}
}
