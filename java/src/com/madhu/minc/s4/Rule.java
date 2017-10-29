
package com.madhu.minc.s4;

/*
 * Created on Jul 1, 2007 at 10:00 AM
 */
public class Rule {
	private Symbol lhs;
	private Expression rhs;
	private int cost;

	public Rule(Symbol lhs, Expression rhs, int cost) {
		if (lhs.isTerminal()) {
			throw new IllegalArgumentException(lhs + " cannot be a terminal");
		}
		this.lhs = lhs;
		this.rhs = rhs;
		this.cost = cost;
	}

	public Rule(String lhs, Expression rhs, int cost) {
		this(new Symbol(lhs), rhs, cost);
	}

	public Rule(String lhs, Expression rhs) {
		this(new Symbol(lhs), rhs, 0);
	}

	public Symbol getLHS() {
		return lhs;
	}
	
	public Expression getRHS() {
		return rhs;
	}
	
	public int getCost() {
		return cost;
	}

	public String toString() {
		return lhs + ": " + rhs;
	}

	public boolean isChain() {
		return rhs.isLeaf() && !rhs.getSymbol().isTerminal();
	}
}
