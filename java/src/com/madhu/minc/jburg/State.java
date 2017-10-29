
package com.madhu.minc.jburg;

import java.util.HashMap;

/*
 * Created on Jul 18, 2007 at 10:13:59 PM
 */
public class State {
	int op;
	State left;
	State right;
	HashMap matches;
	
	public State() {
		matches = new HashMap();
	}

	public State(int op) {
		this();
		this.op = op;
	}

	public State(int op, State left, State right) {
		this(op);
		this.left = left;
		this.right = right;
	}
	
	public void setOperator(int op) {
		this.op = op;
	}

	public int getOperator() {
		return op;
	}

	public int getCost(int nonTerminal) {
		Match m = getMatch(nonTerminal);
		return m.cost;
	}
	
	public int getRule(int nonTerminal) {
		Match m = getMatch(nonTerminal);
		return m.rule;
	}
	
	public void setCost(int nonTerminal, int cost) {
		getMatch(nonTerminal).cost = cost;
	}

	public void setRule(int nonTerminal, int rule) {
		getMatch(nonTerminal).rule = rule;
	}

	public void setCostRule(int nonTerminal, int cost, int rule) {
		Match m = getMatch(nonTerminal);
		m.cost = cost;
		m.rule = rule;
	}

	public Match getMatch(int nonTerminal) {
		Integer key = new Integer(nonTerminal);
		Match m = (Match) matches.get(key);
		if (m == null) {
			m = new Match(nonTerminal, 32767, 0);
			matches.put(key, m);
		}
		return m;
	}
	
	public State getLeft() {
		return left;
	}
	
	public State getRight() {
		return right;
	}
}
