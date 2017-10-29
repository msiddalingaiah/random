
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 20, 2006
 */

package com.madhu.minc.s3;

public class Match {
	public static int NO_MATCH = 1 << 18;
	private Rule rule;
	private int cost;
	
	public Match() {
		this.cost = NO_MATCH;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
	
	public String toString() {
		if (rule == null) {
			return "";
		}
		return rule.toString() + " (" + cost + ")";
	}
}
