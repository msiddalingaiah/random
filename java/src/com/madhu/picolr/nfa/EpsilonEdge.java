
/*
 * Created on Apr 16, 2005 12:41:26 PM
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
public class EpsilonEdge extends Edge {
	public EpsilonEdge() {
	}

	public EpsilonEdge(NFAState next) {
		setNextState(next);
	}

	/* (non-Javadoc)
	 * @see com.madhu.nfa.Transition#accepts(char)
	 */
	public boolean accepts(char c) {
		return false;
	}
}
