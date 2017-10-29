
/*
 * Created on Apr 16, 2005 12:33:44 PM
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
public abstract class Edge {
	private NFAState nextState;

	public Edge() {
	}

	/**
	 * @return
	 */
	public NFAState getNextState() {
		return nextState;
	}

	/**
	 * @param state
	 */
	public void setNextState(NFAState state) {
		nextState = state;
	}
	
	public abstract boolean accepts(char c);
}
