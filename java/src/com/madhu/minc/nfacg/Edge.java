
/*
 * Created on Apr 23, 2005 12:26:30 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.nfacg;

import com.madhu.minc.lir.Instruction;

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
	
	public abstract boolean accepts(Instruction inst);
}
