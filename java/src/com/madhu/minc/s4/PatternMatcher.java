
package com.madhu.minc.s4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Created on Jul 10, 2007 at 7:10:55 PM
 */
public class PatternMatcher {
	private ArrayList<Rule> rules;
	private HashMap<Symbol, ArrayList<Rule>> chains;
	private HashMap<Symbol, ArrayList<Rule>> ruleMap;

	public PatternMatcher() {
		rules = new ArrayList<Rule>();
		chains = new HashMap<Symbol, ArrayList<Rule>>();
		ruleMap = new HashMap<Symbol, ArrayList<Rule>>();
	}
	
	public void add(Rule rule) {
		rules.add(rule);
		Symbol head = rule.getRHS().getSymbol();
		if (head.isTerminal()) {
			ArrayList<Rule> rl = ruleMap.get(head );
			if (rl == null) {
				rl = new ArrayList<Rule>();
				ruleMap.put(head, rl);
			}
			rl.add(rule);
		}
		if (rule.isChain()) {
			Symbol rhs = rule.getRHS().getSymbol();
			ArrayList<Rule> ruleSet = chains.get(rhs);
			if (ruleSet == null) {
				ruleSet = new ArrayList<Rule>();
				chains.put(rhs, ruleSet);
			}
			ruleSet.add(rule);
		}
	}
	
	public void label(ST st) {
		closure();
		doLabel(st);
	}

	private void doLabel(ST subject) {
		int n = subject.getSize();
		for (int i = 0; i < n; i++) {
			doLabel(subject.getOperandST(i));
		}

		ArrayList<Rule> rl = ruleMap.get(subject.getSymbol());
		if (rl == null) {
			return;
		}
		Rule bestRule = null;
		n = rl.size();
		for (int i = 0; i < n; i += 1) {
			Rule r = rl.get(i);
			int cost = subject.computeCost(r.getRHS(), r.getCost());
			Match match = subject.getMatch(r.getLHS());
			if (cost < match.getCost()) {
				match.setRule(r);
				match.setCost(cost);
				bestRule = r;
			}
		}
		if (bestRule == null) {
			throw new AssertionError("No rule matching " + subject);
		}
		
		rl = chains.get(bestRule.getLHS());
		if (rl != null) {
			int cost = bestRule.getCost();
			n = rl.size();
			for (int i = 0; i < n; i += 1) {
				Rule r = rl.get(i);
				Match match = subject.getMatch(r.getLHS());
				match.setRule(r);
				cost += r.getCost();
				match.setCost(cost);
			}
		}
	}

	private void closure() {
		Iterator<Symbol> it = chains.keySet().iterator();
		while (it.hasNext()) {
			doClosure(chains.get(it.next()));
		}
	}

	private void doClosure(ArrayList<Rule> ruleSet) {
		boolean changed = true;
		while (changed) {
			changed = false;
			for (int i = 0; i < ruleSet.size(); i += 1) {
				ArrayList<Rule> cs = chains.get(ruleSet.get(i).getLHS());
				if (cs != null) {
					int m = cs.size();
					for (int j = 0; j < m; j += 1) {
						Rule r = cs.get(j);
						if (!ruleSet.contains(r)) {
							changed = true;
							ruleSet.add(r);
						}
					}
				}
			}
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(256);
		int n = rules.size();
		for (int i = 0; i < n; i += 1) {
			Rule r = rules.get(i);
			sb.append(r);
			sb.append('\n');
			ArrayList<Rule> set = chains.get(r.getLHS());
			//sb.append(set);
			if (set != null) {
				int m = set.size();
				for (int j = 0; j < m; j += 1) {
					sb.append('\t');
					sb.append(set.get(j));
					sb.append('\n');
				}
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	
	/*
stmt:	ASGNI(disp,reg) = 4 (1);
stmt:	reg = 5;
reg:	ADDI(reg,rc) = 6 (1);
reg:	CVCI(INDIRC(disp)) = 7 (1);
reg:	I0I = 8;
reg:	disp = 9 (1);
disp:	ADDI(reg,con) = 10;
disp:	ADDRLP = 11;
rc:	con = 12;
rc:	reg = 13;
con:	CNSTI = 14;
con:	I0I = 15;
	 */
	public static void main(String[] args) {
		PatternMatcher pm = new PatternMatcher();
		pm.add(new Rule("stmt", new E("ASGNI", new E("disp"), new E("reg")), 1));
		pm.add(new Rule("stmt", new E("reg")));
		pm.add(new Rule("reg", new E("ADDI", new E("reg"), new E("rc")), 1));
		pm.add(new Rule("reg", new E("CVCI", new E("INDIRC", new E("disp"))), 1));
		pm.add(new Rule("reg", new E("I0I")));
		pm.add(new Rule("reg", new E("disp"), 1));
		pm.add(new Rule("disp", new E("ADDI", new E("reg"), new E("con"))));
		pm.add(new Rule("disp", new E("ADDRLP")));
		pm.add(new Rule("rc", new E("con")));
		pm.add(new Rule("rc", new E("reg")));
		pm.add(new Rule("con", new E("CNSTI")));
		pm.add(new Rule("con", new E("I0I")));

		/*
	t = tree(ASGNI,
		tree(ADDRLP, 0, 0),
		tree(ADDI,
			tree(CVCI, tree(INDIRC, tree(ADDRLP, 0, 0), 0), 0),
			(t = tree(CNSTI, 0, 0), t->val = 4, t)
		)
	);
		 */

		ST st = new ST("ASGNI",
			new ST("ADDRLP"),
			new ST("ADDI",
				new ST("CVCI",
					new ST("INDIRC",
						new ST("ADDRLP")
					)
				),
				new ST("CNSTI")
			)
		);
		pm.label(st);
		System.out.println(st);
	}
}

class E extends Expression {
	public E(Symbol symbol) {
		super(symbol);
	}

	public E(String name) {
		super(name);
	}
	
	public E(String name, E e1) {
		this(name);
		add(e1);
	}
	
	public E(String name, E e1, E e2) {
		this(name);
		add(e1);
		add(e2);
	}
}
