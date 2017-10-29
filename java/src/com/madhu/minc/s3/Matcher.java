
package com.madhu.minc.s3;

import java.util.ArrayList;

public class Matcher {
	private ArrayList rules;
	private ArrayList chainRules;

	public Matcher() {
		rules = new ArrayList();
		chainRules = new ArrayList();
	}

	public void addRule(Rule c) {
		if (c.isChainRule()) {
			chainRules.add(c);
		} else {
			rules.add(c);
		}
	}
	
	public void label(StateTree subject) {
		int n = subject.getChildCount();
		for (int i = 0; i < n; i++) {
			label((StateTree) subject.getChild(i));
		}
		cover(subject);
		closure(subject);
	}

	private void cover(StateTree subject) {
		int n = rules.size();
		for (int i = 0; i < n; i++) {
			Rule rule = (Rule) rules.get(i);
			if (rule.matches(subject)) {
				State state = subject.getState();
				Match match = state.getMatch(rule.getNonTerminal());
				int cost = rule.getCost() + rule.getMatchCost();
				if (cost < match.getCost()) {
					match.setRule(rule);
					match.setCost(cost);
				}
			}
		}
	}

	private void closure(Tree subject) {
		// TODO finish...
	}
}
