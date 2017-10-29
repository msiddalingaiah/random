
/*
 * Created on Apr 23, 2005 12:28:21 PM
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
public class EpsilonEdge extends Edge {
	public EpsilonEdge() {
	}

	public EpsilonEdge(NFAState next) {
		setNextState(next);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.nfacg.Edge#accepts(com.madhu.minc.lir.Instruction)
	 */
	public boolean accepts(Instruction inst) {
		return false;
	}
}
