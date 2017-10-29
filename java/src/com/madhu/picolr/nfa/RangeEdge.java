
/*
 * Created on Apr 20, 2005 11:17:26 AM
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
public class RangeEdge extends Edge {
	private char first, last;
	
	public RangeEdge(char c, char d, NFAState next) {
		if (c <= d) {
			this.first = c;
			this.last = d;
		} else {
			this.last = c;
			this.first = d;
		}
		setNextState(next);
	}

	/* (non-Javadoc)
	 * @see com.madhu.nfa.Edge#accepts(char)
	 */
	public boolean accepts(char c) {
		return c >= first && c <= last;
	}
}
