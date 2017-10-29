
package com.madhu.picolr.burg;

import java.util.ArrayList;
import java.util.HashMap;

import com.madhu.picolr.Tree;

/*
 * Created on Apr 22, 2008 at 6:35:24 PM
 */
public class AST extends Tree<Symbol, AST> {
	private HashMap<Symbol,Match> matches;
	private Object result;

	public AST(Symbol value) {
		super(value);
		matches = new HashMap<Symbol, Match>();
	}

	public AST(Symbol value, AST left) {
		this(value);
		add(left);
	}

	public AST(Symbol value, AST left, AST right) {
		this(value, left);
		add(right);
	}

	public void label(RuleFactory rf) {
		label(rf.getRules(), rf.getChainRules());
	}

	public void label(ArrayList<Rule> rules,
			HashMap<Symbol,ArrayList<Rule>> chainRules) {

		int n = size();
		for (int i = 0; i < n; i += 1) {
			get(i).label(rules, chainRules);
		}
		for (Rule r : rules) {
			int c = getCost(r);
			Symbol nt = r.getNonTerminal();
			if (c < getCost(nt)) {
				matches.put(nt, new Match(this, r, c));
				// closure
				ArrayList<Rule> cprods = chainRules.get(nt);
				if (cprods != null) {
					for (Rule cr : cprods) {
						matches.put(cr.getNonTerminal(), new Match(this, cr, c + cr.getCost()));
					}
				}
			}
		}
	}

	public int getCost(Symbol nt) {
		Match match = matches.get(nt);
		if (match != null) {
			return match.getCost();
		}
		return Integer.MAX_VALUE;
	}
	
	public int getCost(Rule p) {
		int c = getCost(p.getRHS());
		if (c == Integer.MAX_VALUE) {
			return c;
		}
		return c + p.getCost();
	}

	private int getCost(BURGTree rhs) {
		Symbol sym = rhs.getValue();
		if (sym.isNonTerminal()) {
			return getCost(sym);
		}
		int n = size();
		if (!getValue().equals(sym) || n != rhs.size()) {
			return Integer.MAX_VALUE;
		}
		int cost = 0;
		for (int i = 0; i < n; i += 1) {
			int c = get(i).getCost(rhs.get(i));
			if (c == Integer.MAX_VALUE) {
				return c;
			}
			cost += c;
		}
		return cost;
	}
	
	public Match getMatch(Symbol nt) {
		return matches.get(nt);
	}
	
	public void dumpCover(Symbol nt, int tab) {
		for (int i = 0; i < tab; i += 1) {
			System.out.print("    ");
		}
		Match match = matches.get(nt);
		Rule rule = match.getRule();
		System.out.printf("%s: %d\n", rule, match.getCost());
		if (rule.isChain()) {
			dumpCover(rule.getRHS().getValue(), tab);
		} else {
			findNonTerminal(rule.getRHS(), tab);
		}
	}

	private void findNonTerminal(BURGTree rhs, int tab) {
		int n = rhs.size();
		for (int i=0; i<n; i+=1) {
			BURGTree tree = rhs.get(i);
			AST ast = get(i);
			Symbol s = (Symbol) tree.getValue();
			if (s.isNonTerminal()) {
				ast.dumpCover(s, tab+1);
			} else {
				ast.findNonTerminal(tree, tab);
			}
		}
	}
	
	public ArrayList<Match> reduce(String start) {
		ArrayList<Match> mlist = new ArrayList<Match>();
		doReduce(new Symbol(start), mlist);
		return mlist;
	}

	private void doReduce(Symbol nt, ArrayList<Match> mlist) {
		Match match = matches.get(nt);
		mlist.add(match);
		Rule rule = match.getRule();
		if (rule.isChain()) {
			doReduce(rule.getRHS().getValue(), mlist);
		} else {
			findNonTerminal(rule.getRHS(), mlist);
		}
	}

	private void findNonTerminal(BURGTree rhs, ArrayList<Match> mlist) {
		int n = rhs.size();
		for (int i=0; i<n; i+=1) {
			BURGTree tree = rhs.get(i);
			AST ast = get(i);
			Symbol s = (Symbol) tree.getValue();
			if (s.isNonTerminal()) {
				ast.doReduce(s, mlist);
			} else {
				ast.findNonTerminal(tree, mlist);
			}
		}
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
