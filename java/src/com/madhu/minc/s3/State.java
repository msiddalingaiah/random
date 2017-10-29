
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 22, 2006
 */

package com.madhu.minc.s3;

import java.util.HashMap;

public class State {
	private HashMap matches;
	
	public State() {
		matches = new HashMap();
	}

	public int getCost(Symbol nonTerminal) {
		Match m = getMatch(nonTerminal);
		return m.getCost();
	}
	
	public Rule getRule(Symbol nonTerminal) {
		Match m = getMatch(nonTerminal);
		return m.getRule();
	}
	
	public void setCost(Symbol nonTerminal, int cost) {
		getMatch(nonTerminal).setCost(cost);
	}

	public void setRule(Symbol nonTerminal, Rule rule) {
		getMatch(nonTerminal).setRule(rule);
	}

	public Match getMatch(Symbol nonTerminal) {
		Match m = (Match) matches.get(nonTerminal);
		if (m == null) {
			m = new Match();
			matches.put(nonTerminal, m);
		}
		return m;
	}
	
	public String toString() {
		return matches.toString();
	}
}
