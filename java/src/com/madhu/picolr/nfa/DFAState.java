
/*
 * Created on Apr 16, 2005 12:38:38 PM
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
public class DFAState {
	private NFAState[] nfaStates;
	private int nfaStateCount;
	private boolean accept;
	private String name;
	private int[] bitVector;
	
	/**
	 *
	 */
	public DFAState() {
		accept = false;
		bitVector = new int[2];
		nfaStates = new NFAState[64];
	}
	
	/**
	 * @param state
	 * @return true if the state was added
	 */
	public boolean add(NFAState state) {
		int number = state.getStateNumber();
		int index = number >>> 5;
		if (index >= bitVector.length) {
			int[] temp = new int[bitVector.length << 1];
			System.arraycopy(bitVector, 0, temp, 0, index);
			bitVector = temp;
		}
		int bit = 1 << (number & 0x1f);
		if ((bitVector[index] & bit) != 0) {
			return false;
		}
		if (nfaStateCount >= nfaStates.length) {
			NFAState[] temp = new NFAState[nfaStates.length << 1];
			System.arraycopy(nfaStates, 0, temp, 0, nfaStateCount);
			nfaStates = temp;
		}
		nfaStates[nfaStateCount++] = state;
		bitVector[index] |= bit;
		if (state.isAccept()) {
			name = state.getName();
			accept = true;
		}
		return true;
	}
	
	public void clear() {
		for (int i=0; i<bitVector.length; i+=1) {
			bitVector[i] = 0;
		}
		nfaStateCount = 0;
		accept = false;
	}

	/**
	 * 
	 * @param nextState next state
	 * @param c input symbol
	 * @return true if current state accepts c (no error)
	 */
	public boolean advance(DFAState nextState, char c) {
		boolean accepts = false;
		for (int i = 0; i < nfaStateCount; i++) {
			if (nfaStates[i].advance(nextState, c)) {
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
		int n = nfaStateCount;
		StringBuffer sb = new StringBuffer(5 + n*5);
		sb.append("[");
		for (int i=0; i<n; i+=1) {
			sb.append(nfaStates[i].toString());
			if (i < n-1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
