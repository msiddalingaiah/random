
/*
 * Created on Apr 16, 2005 1:22:06 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr.nfa;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Scanner<E> {
	private Reader in;
	private NFAState start;
	private DFAState currentState;
	private DFAState nextState;
	private StringBuffer sb;
	private Compiler compiler;
	private E eofObject;
	private int[] inputArray;
	private int inputIndex;
	private int available;
	private int lineNumber;
	private int columnNumber;
	private TerminalFactory<E> terminalFactory;
	private int stateCounter;

	public Scanner(TerminalFactory<E> tf) {
		compiler = new Compiler(this);
		start = createNFAState(null);
		inputArray = new int[16];
		terminalFactory = tf;
	}

	public NFAState createNFAState(String object) {
		return new NFAState(stateCounter++, object);
	}

	public NFAState createNFAState() {
		return createNFAState(null);
	}

	public void setReader(Reader reader) {
		in = reader;
		currentState = new DFAState();
		nextState = new DFAState();
		sb = new StringBuffer();
		lineNumber = 1;
		columnNumber = 0;
	}

	public void addRule(String pattern, String name) {
		start.addEpsilonEdge(compiler.compile(pattern, name));
	}

	public void clear() {
		inputIndex = 0;
		available = 0;
		lineNumber = 0;
		columnNumber = 0;
		start.clear();
	}

	public void addRule(NFAState pattern) {
		start.addEpsilonEdge(pattern);
	}

	public E nextToken() throws IOException {
		reset();
		int c = read();
		if (c == -1) {
			unread();
			return eofObject;
		}
		while (currentState.advance(nextState, (char) c)) {
			sb.append((char) c);
			swap();
			nextState.clear();
			c = read();
		}
		unread();
		if (currentState.isAccept()) {
			return terminalFactory.create(currentState.getName(), getString(), getLineNumber(), getColumnNumber());
		}
		return null;
	}

	private int read() throws IOException {
		int c;
		if (available > 0) {
			c = inputArray[inputIndex++];
			inputIndex %= inputArray.length;
			available -= 1;
		} else {
			c = in.read();
			columnNumber += 1;
			if (c == '\n') {
				lineNumber += 1;
				columnNumber = 0;
			}
			inputArray[inputIndex++] = c;
			inputIndex %= inputArray.length;
		}
		return c;
	}

	private void unread() {
		inputIndex -= 1;
		if (inputIndex < 0) {
			inputIndex = inputArray.length - 1;
		}
		available += 1;
	}

	public String getString() {
		return sb.toString();
	}

	/**
	 * Converts buffered input into byte array with no encoding, e.g.
	 * only the lower 8 bits of a char are used.
	 * 
	 * @return
	 */
	public byte[] getBytes() {
		String s = getString();
		int n = s.length();
		byte[] bytes = new byte[n];
		for (int i = 0; i < n; i++) {
			bytes[i] = (byte) s.charAt(i);
		}
		return bytes;
	}

	public void reset() {
		stateCounter = 0;
		currentState.clear();
		nextState.clear();
		currentState.add(start);
		start.eClosure(currentState);
		sb.delete(0, sb.length());
	}

	private void swap() {
		DFAState temp = currentState;
		currentState = nextState;
		nextState = temp;
	}

	public static void main(String[] args) throws IOException {
		testReader();
	}

	private static void testReader() throws IOException {
		Scanner<String> sc = new Scanner<String>(new TerminalFactory<String>() {
			@Override
			public String create(String name, String text, int line, int column) {
				return name;
			}
		});
		sc.setReader(new StringReader("public class Sca_nner {\n\t}"));
		sc.addRule("( |\t|\r|\n|\f)+", "<whitespace>");
		sc.addRule("{", "{");
		sc.addRule("}", "}");
		sc.addRule("(a-z|A-Z|_)(a-z|A-Z|0-9|_)*", "<ID>");
		sc.setEOFObject("EOF");
		Object name = sc.nextToken();
		while (name != null && !name.equals("EOF")) {
			String string = sc.getString();
			System.out.println(name + ": " + string);
			name = sc.nextToken();
		}
		if (name == null) {
			System.out.println("input did not match");
		}
	}

	/**
	 * Convenience method to parse a pattern
	 * 
	 * @param pattern
	 * @param t
	 * @return
	 */
	public NFAState compile(String pattern, String object) {
		return compiler.compile(pattern, object);
	}

	/**
	 * @return
	 */
	public NFAState getStart() {
		return start;
	}

	/**
	 * @param state
	 */
	public void setStart(NFAState state) {
		start = state;
	}
	
	public void setEOFObject(E eof) {
		this.eofObject = eof;
	}
	
	public Object getEOFObject() {
		return eofObject;
	}

	/**
	 * @return Returns the columnNumber.
	 */
	public int getColumnNumber() {
		return columnNumber;
	}

	/**
	 * @return Returns the lineNumber.
	 */
	public int getLineNumber() {
		return lineNumber;
	}
}
