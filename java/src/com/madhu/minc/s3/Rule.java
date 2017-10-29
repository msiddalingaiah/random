
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 22, 2006
 */

package com.madhu.minc.s3;

public class Rule {
	private NonTerminal nonTerminal;
	private Tree production;
	private int cost;
	private int matchCost;

	public Rule(NonTerminal nonTerminal, Tree production, int cost) {
		this.nonTerminal = nonTerminal;
		this.production = production;
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	public NonTerminal getNonTerminal() {
		return nonTerminal;
	}

	public Tree getProduction() {
		return production;
	}
	
	private boolean matches(Tree production, StateTree subject) {
		if (!production.getSymbol().equals(subject.getSymbol())) {
			return false;
		}
		if (production.getChildCount() != subject.getChildCount()) {
			return false;
		}
		int n = production.getChildCount();
		for (int i = 0; i < n; i++) {
			Tree pc = production.getChild(i);
			StateTree sc = (StateTree) subject.getChild(i);
			Symbol symbol = pc.getSymbol();
			if (!symbol.isTerminal()) {
				matchCost += sc.getState().getCost(symbol);
			} else if (!matches(pc, sc)) {
				return false;
			}
		}
		return true;
	}

	public boolean matches(StateTree subject) {
		matchCost = 0;
		return matches(production, subject);
	}
	
	public int getMatchCost() {
		return matchCost;
	}

	public boolean isChainRule() {
		return production.isLeaf() && !((Symbol) production.getSymbol()).isTerminal();
	}
	
	public String toString() {
		return nonTerminal + ": " + production;
	}
}
