
/*
 * Created on Apr 16, 2005 1:11:04 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr.nfa;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class CharacterEdge extends Edge {
	private char c;

	public CharacterEdge(char c) {
		this.c = c;
	}

	public CharacterEdge(char c, NFAState next) {
		this.c = c;
		setNextState(next);
	}

	/* (non-Javadoc)
	 * @see com.madhu.nfa.Transition#accepts(char)
	 */
	public boolean accepts(char c) {
		return this.c == c;
	}
	
	public String toString() {
		return Character.toString(c);
	}
}
