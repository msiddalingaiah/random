
package com.madhu.picolr.burg;

import java.lang.reflect.Method;

/*
 * Created on Apr 22, 2008 at 6:47:30 PM
 */
public class Rule {
	private Symbol nonTerminal;
	private BURGTree rhs;
	private int cost;
	private Method action;

	public Rule(Symbol nonTerminal, BURGTree rhs, int cost, Method action) {
		this.nonTerminal = nonTerminal;
		this.rhs = rhs;
		this.cost = cost;
		this.action = action;
	}

	public Rule(String nonTerminal, BURGTree rhs, int cost, Method action) {
		this(new Symbol(nonTerminal), rhs, cost, action);
	}

	public Symbol getNonTerminal() {
		return nonTerminal;
	}

	public BURGTree getRHS() {
		return rhs;
	}

	public int getCost() {
		return cost;
	}
	
	public String toString() {
		return String.format("%s: %s", nonTerminal, rhs);
	}

	public boolean isChain() {
		return rhs.getValue().isNonTerminal();
	}

	public Method getAction() {
		return action;
	}
}
