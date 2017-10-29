
/*
 * Created on Apr 23, 2005 12:26:49 PM
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
public class NFAState {
	private static int stateNumber;
	private String name;
	private List edges;
	private boolean accept;

	public NFAState() {
		this("s" + (stateNumber++));
	}
	
	public NFAState(String name) {
		this(name, false);
	}

	public NFAState(boolean accept) {
		this("s" + (stateNumber++), accept);
	}
	
	public NFAState(String name, boolean accept) {
		this.name = name;
		this.accept = accept;
		edges = new ArrayList();
	}
	
	public void addEdge(Edge t) {
		edges.add(t);
	}

	/**
	 * @param state
	 */
	public void addEpsilonEdge(NFAState next) {
		addEdge(new EpsilonEdge(next));
	}
	
	public void eClosure(DFAState dfa) {
		Iterator it = edges.iterator();
		while (it.hasNext()) {
			Edge t = (Edge) it.next();
			if (t instanceof EpsilonEdge) {
				NFAState nfa = t.getNextState();
				if (dfa.add(nfa)) {
					nfa.eClosure(dfa);
				}
			}
		}
	}

	public boolean advance(DFAState dfa, Instruction inst) {
		boolean accepts = false;

		Iterator it = edges.iterator();
		while (it.hasNext()) {
			Edge t = (Edge) it.next();
			if (t.accepts(inst)) {
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
	public void setName(String string) {
		name = string;
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
}
