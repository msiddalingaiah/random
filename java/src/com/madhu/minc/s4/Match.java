
package com.madhu.minc.s4;

/*
 * Created on Jul 1, 2007 at 10:00 AM
 */
public class Match {
	private Rule rule;
	private int cost;

	public Match(Rule rule, int cost) {
		this.rule = rule;
		this.cost = cost;
	}

	public Match(Rule rule) {
		this(rule, Integer.MAX_VALUE);
	}

	public Match() {
		this(null);
	}

	public Rule getRule() {
		return rule;
	}

	public int getCost() {
		return cost;
	}

	public void setRule(Rule r) {
		rule = r;
	}

	public void setCost(int c) {
		cost = c;
	}
	
	public String toString() {
		return rule + " (" + cost + ")";
	}
}
