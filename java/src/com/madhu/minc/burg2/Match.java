
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 22, 2006
 */

package com.madhu.minc.burg2;

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
