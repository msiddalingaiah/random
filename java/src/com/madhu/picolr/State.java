
/*
 * Created on May 18, 2005 5:32:48 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr;

import java.util.HashMap;

import com.madhu.picolr.nfa.NFAState;
import com.madhu.picolr.nfa.Scanner;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class State {
	private int number;
	/**
	 * The first kernel item uniquely identifies this state
	 */
	private Item firstKernelItem;
	/**
	 * Each bit represents a grammar symbol that this state accepts
	 */
	private int[] gotoBitVector;
	/**
	 * Used to track closure of non-terminals
	 */
	private int[] nonTerminalBitVector;
	/**
	 * firstItem through lastItem are the items in this state
	 */
	private Item firstItem;
	private Item lastItem;
	private Production reduction;
	/**
	 * Map of next states keyed by GrammarSymbol
	 */
	private HashMap<GrammarSymbol,State> nextState;
	private StateList stateList;
	/**
	 * NFA Scanner state with transition on all terminals accepted by this state
	 */
	private NFAState nfa;
	private Grammar grammar;
	
	public State(Grammar grammar, StateList stateList, Item kernelItems, Item lastItem) {
		this.grammar = grammar;
		this.stateList = stateList;
		this.firstKernelItem = new Item(kernelItems.getProduction(),
			kernelItems.getDot());
		this.firstItem = kernelItems;
		this.lastItem = lastItem;
		number = stateList.getCount();
		stateList.add(this);
		nextState = new HashMap<GrammarSymbol,State>();
		int symCount = grammar.getSymbolCount();
		gotoBitVector = new int[1 + (symCount >>> 5)];
		nonTerminalBitVector = new int[1 + (symCount >>> 5)];
		nfa = grammar.getScanner().createNFAState();
		closure();
		Terminal ws = stateList.getWhitespace();
		if (ws != null) {
			nfa.addEpsilonEdge(ws.getNFAState());
		}
	}

	/**
	 * 
	 */
	private void closure() {
		Item item = firstItem;
		while (item != null) {
			GrammarSymbol symbol = item.getDotSymbol();
			if (symbol == null) {
				if (reduction != null) {
					throw new IllegalArgumentException("Reduce/reduce conflict in " +
						"state " + number + ": " + reduction + " and " +
						item.getProduction());
				}
				reduction = item.getProduction();
			} else {
				closure(item);
				int n = symbol.hashCode();
				int bit = 1 << (n & 0x1f);
				gotoBitVector[n >>> 5] |= bit;
				if (symbol instanceof Terminal) {
					Terminal t = (Terminal) symbol;
					if (t.getNFAState() != null) {
						nfa.addEpsilonEdge(t.getNFAState());
					}
				}
			}
			item = item.getNext();
		}
	}

	/**
	 * @param item
	 */
	private void closure(Item item) {
		GrammarSymbol symbol = item.getDotSymbol();
		if (symbol instanceof NonTerminal) {
			NonTerminal nt = (NonTerminal) symbol;
			int sn = symbol.hashCode();
			int sbit = 1 << (sn & 0x1f);
			if ((nonTerminalBitVector[sn >>> 5] & sbit) == 0) {
				nonTerminalBitVector[sn >>> 5] |= sbit;
				Production[] prods = nt.getProductions();
				for (int i = 0; i < prods.length; i++) {
					Item newItem = new Item(prods[i]);
					lastItem.setNext(newItem);
					lastItem = newItem;
				}
			}
		}
	}

	public State getNextState(GrammarSymbol symbol) {
		if (!hasNextState(symbol)) {
			return null;	// reduce, accept, or error
		}
		State next = nextState.get(symbol);
		if (next != null) {
			return next;
		}
		next = advance(symbol);
		nextState.put(symbol, next);
		return next;
	}

	/**
	 * @return
	 */
	private State advance(GrammarSymbol symbol) {
		Item firstNewItem = null;
		Item lastNewItem = null;
		Item prevItem = null;
		Item item = firstItem;
		while (item != null) {
			Item nextItem = item.getNext();
			if (symbol.equals(item.getDotSymbol())) {
				item.advance();
				// add to the new items
				if (firstNewItem == null) {
					firstNewItem = item;
				} else {
					lastNewItem.setNext(item);
				}
				lastNewItem = item;
				lastNewItem.setNext(null);

				// remove item from this state
				if (prevItem == null) {
					firstItem = nextItem;
				} else {
					prevItem.setNext(nextItem);
				}
			} else {
				prevItem = item;
			}
			item = nextItem;
		}
		
		State next = stateList.find(firstNewItem);
		if (next == null) {
			next = new State(grammar, stateList, firstNewItem, lastNewItem);
		}
		return next;
	}

	/**
	 * @param symbol
	 * @return
	 */
	private boolean hasNextState(GrammarSymbol symbol) {
		int n = symbol.hashCode();
		int bit = 1 << (n & 0x1f);
		return (gotoBitVector[n >>> 5] & bit) != 0;
	}
	
	public String toString() {
		return "s" + Integer.toString(number);
	}

	/**
	 * @param count
	 */
	public void setNumber(int count) {
		number = count;
	}

	/**
	 * @return
	 */
	public NFAState getNFA() {
		return nfa;
	}

	/**
	 * 
	 */
	public Production getReduction() {
		return reduction;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return firstKernelItem.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		return this.hashCode() == obj.hashCode();
	}

	/**
	 * @return
	 */
	public Item getFirstKernelItem() {
		return firstKernelItem;
	}
}
