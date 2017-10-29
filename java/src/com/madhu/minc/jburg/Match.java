
package com.madhu.minc.jburg;

/*
 * Created on Jul 18, 2007 at 10:14:54 PM
 */
public class Match {
	int nonTerminal;
	int cost;
	int rule;

	public Match(int nonTerminal, int cost, int rule) {
		super();
		this.nonTerminal = nonTerminal;
		this.cost = cost;
		this.rule = rule;
	}
}
