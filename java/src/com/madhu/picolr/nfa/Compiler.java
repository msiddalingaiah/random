
/*
 * Created on Apr 18, 2005 11:32:11 AM
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
public class Compiler {
	private PushbackReader in;
	private Scanner<?> scanner;

	public Compiler(Scanner<?> scanner) {
		this.scanner = scanner;
	}

	/**
	 * @param string
	 * @param string2
	 * @return
	 */
	public NFAState compile(String pattern, String object) {
		try {
			return compile(new StringReader(pattern), object);
		} catch (IOException e) {
			throw new AssertionError(e);	// this should not normally happen
		}
	}

	public NFAState compile(Reader in, String object) throws IOException {
		this.in = new PushbackReader(in, true);
		Pattern p = parseAlternates();
		if (p == null) {
			throw new IllegalArgumentException("Missing pattern");
		}
		int c = in.read();
		if (c != -1) {
			throw new IllegalArgumentException("Unexpected char: " + (char) c);
		}
		NFAState end = p.getEndState();
		end.setAccept(true);
		end.setName(object);
		return p.getStartState();
	}

	/**
	 * p -> p ( '|' p)*
	 * 
	 * @return
	 */
	private Pattern parseAlternates() throws IOException {
		Pattern p = parseSequence();
		if (p == null) {
			return null;
		}
		int c = in.read();
		if (c != PushbackReader.OR) {
			in.unread();
			return p;
		}
		NFAState start = scanner.createNFAState();
		NFAState end = scanner.createNFAState();
		start.addEpsilonEdge(p.getStartState());
		p.getEndState().addEpsilonEdge(end);
		while (c == PushbackReader.OR) {
			p = parseSequence();
			if (p == null) {
				throw new IllegalArgumentException("missing pattern");
			}
			start.addEpsilonEdge(p.getStartState());
			p.getEndState().addEpsilonEdge(end);
			c = in.read();
		}
		in.unread();
		return new Pattern(start, end);
	}

	/**
	 * p -> p ( p )*
	 * 
	 * @return
	 */
	private Pattern parseSequence() throws IOException {
		Pattern p = parsePrimitive();
		if (p == null) {
			return null;
		}
		int c = in.read();
		in.unread();
		while (c != PushbackReader.RPAREN && c != PushbackReader.OR) {
			Pattern p1 = parsePrimitive();
			if (p1 == null) {
				break;
			}
			p.getEndState().addEpsilonEdge(p1.getStartState());
			p.setEndState(p1.getEndState());
			c = in.read();
			in.unread();
		}
		return p;
	}

//	p -> '(' e ')' | '(' e ')' '*' | '(' e ')' '?' | char | char-char

	 private Pattern parsePrimitive() throws IOException {
		int c = in.read();
		switch (c) {
			case PushbackReader.LPAREN:
				Pattern p = parseAlternates();
				if (p == null || in.read() != PushbackReader.RPAREN) {
					throw new IllegalArgumentException("missing )");
				}
				c = in.read();
				if (c == PushbackReader.STAR) {
					p.getEndState().addEpsilonEdge(p.getStartState());
					p.getStartState().addEpsilonEdge(p.getEndState());
				} else if (c == PushbackReader.PLUS) {
					p.getEndState().addEpsilonEdge(p.getStartState());
				} else if (c == PushbackReader.QUESTION) {
					p.getStartState().addEpsilonEdge(p.getEndState());
				} else {
					in.unread();
				}
				return p;

			case PushbackReader.OR:
			case PushbackReader.RPAREN:
				in.unread();

			case -1:
				return null;

			default:
				NFAState start = scanner.createNFAState();
				NFAState end = scanner.createNFAState();
				int dash = in.read();
				if (dash == PushbackReader.DASH) {
					int d = in.read();
					if (d == -1) {
						throw new IllegalArgumentException(
							"Missing char after -");
					}
					start.addEdge(new RangeEdge((char) c, (char) d, end));
				} else {
					in.unread();
					start.addEdge(new CharacterEdge((char) c, end));
				}
				return new Pattern(start, end);
		}
	}
}

class Pattern {
	private NFAState startState;
	private NFAState endState;

	/**
	 * @param start
	 * @param end
	 */
	public Pattern(NFAState start, NFAState end) {
		this.startState = start;
		this.endState = end;
	}

	/**
	 * @param state
	 */
	public void setEndState(NFAState state) {
		this.endState = state;
	}

	/**
	 * 
	 */
	public NFAState getEndState() {
		return endState;
	}

	/**
	 * @return
	 */
	public NFAState getStartState() {
		return startState;
	}
}
