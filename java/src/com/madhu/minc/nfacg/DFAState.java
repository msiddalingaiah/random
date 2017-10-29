
/*
 * Created on Apr 23, 2005 12:28:54 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.nfacg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.madhu.minc.lir.Instruction;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class DFAState {
	private List nfaStates;
	private boolean accept;
	private String name;
	
	public DFAState() {
		nfaStates = new ArrayList();
		accept = false;
	}
	
	/**
	 * @param state
	 * @return true if the state was added
	 */
	public boolean add(NFAState state) {
		if (nfaStates.contains(state)) {
			return false;
		}
		nfaStates.add(state);
		if (state.isAccept()) {
			name = state.getName();
			accept = true;
		}
		return true;
	}
	
	public void clear() {
		nfaStates.clear();
		accept = false;
	}

	/**
	 * 
	 * @param dfa next state
	 * @param c input symbol
	 * @return true if current state accepts inst (no error)
	 */
	public boolean advance(DFAState dfa, Instruction inst) {
		boolean accepts = false;

		Iterator it = nfaStates.iterator();
		while (it.hasNext()) {
			NFAState nfa = (NFAState) it.next();
			if (nfa.advance(dfa, inst)) {
				accepts = true;
			}
		}
		return accepts;
	}

	/**
	 * @return
	 */
	public boolean isAccept() {
		return accept;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	public String toString() {
		int n = nfaStates.size();
		StringBuffer sb = new StringBuffer(5 + n*5);
		sb.append("[");
		for (int i=0; i<n; i+=1) {
			sb.append(nfaStates.get(i));
			if (i < n-1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * @return
	 */
	public int getSize() {
		return nfaStates.size();
	}
}
