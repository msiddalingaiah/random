
/*
 * Created on Apr 16, 2005 12:31:57 PM
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
public class NFAState {
	private String name;
	private Edge[] edges;
	private int edgeCount;
	private boolean accept;
	private int stateNumber;

	public NFAState(int number) {
		this(number, null);
	}
	
	public NFAState(int number, String object) {
		this(number, object, false);
	}

	public NFAState(int number, boolean accept) {
		this(number, null, accept);
	}
	
	public NFAState(int number, String object, boolean accept) {
		this.accept = accept;
		edges = new Edge[16];
		stateNumber = number;
		this.name = object;
	}
	
	public void addEdge(Edge t) {
		if (edgeCount >= edges.length) {
			Edge[] temp = new Edge[edges.length << 1];
			System.arraycopy(edges, 0, temp, 0, edgeCount);
			edges = temp;
		}
		edges[edgeCount++] = t;
	}

	/**
	 * @param state
	 */
	public void addEpsilonEdge(NFAState next) {
		addEdge(new EpsilonEdge(next));
	}
	
	public void eClosure(DFAState dfa) {
		for (int i = 0; i < edgeCount; i++) {
			Edge t = edges[i];
			if (t instanceof EpsilonEdge) {
				NFAState nfa = t.getNextState();
				if (dfa.add(nfa)) {
					nfa.eClosure(dfa);
				}
			}
		}
	}

	public boolean advance(DFAState dfa, char c) {
		boolean accepts = false;

		for (int i = 0; i < edgeCount; i++) {
			Edge t = edges[i];
			if (t.accepts(c)) {
				NFAState nfa = t.getNextState();
				if (dfa.add(nfa)) {
					nfa.eClosure(dfa);
				}
				accepts = true;
			}
		}
		return accepts;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public boolean isAccept() {
		return accept;
	}

	/**
	 * @param b
	 */
	public void setAccept(boolean b) {
		accept = b;
	}
	
	public String toString() {
		return name;
	}

	/**
	 * 
	 */
	public void clear() {
		edgeCount = 0;
	}

	/**
	 * @return
	 */
	public int getStateNumber() {
		return stateNumber;
	}
}
