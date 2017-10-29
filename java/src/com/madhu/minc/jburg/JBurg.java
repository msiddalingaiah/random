
package com.madhu.minc.jburg;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/*
 * Created on Jul 13, 2007 at 2:25:01 PM
 */
public class JBurg {
	public static final int MAX_COST = Short.MAX_VALUE;

	private int ntNumber;
	private Symbol start;
	private Symbol terminals;
	private Symbol nonTerminals;
	private Rule rules;
	private int nRules;
	private HashMap<String,Symbol> symbolTable;
	private Emitter emitter;

	public static void main(String[] args) throws IOException {
		JBurg p = new JBurg();
		p.addSymbol("CNSTI", 21);
		p.addSymbol("ASGNI", 53);
		p.addSymbol("INDIRC", 67);
		p.addSymbol("CVCI", 85);
		p.addSymbol("ADDRLP", 295);
		p.addSymbol("ADDI", 309);
		p.addSymbol("I0I", 661);
		
		p.addSymbol("stmt");
		p.addSymbol("disp");
		p.addSymbol("reg");
		p.addSymbol("rc");
		p.addSymbol("con");

		p.addRule("stmt", p.tree("ASGNI", p.tree("disp"), p.tree("reg")), 4, 1);
		p.addRule("stmt", p.tree("reg"), 5);
		p.addRule("reg", p.tree("ADDI", p.tree("reg"), p.tree("rc")), 6, 1);
		p.addRule("reg", p.tree("CVCI", p.tree("INDIRC", p.tree("disp"))), 7, 1);
		p.addRule("reg", p.tree("I0I"), 8);
		p.addRule("reg", p.tree("disp"), 9, 1);
		p.addRule("disp", p.tree("ADDI", p.tree("reg"), p.tree("con")), 10);
		p.addRule("disp", p.tree("ADDRLP"), 11);
		p.addRule("rc", p.tree("con"), 12);
		p.addRule("rc", p.tree("reg"), 13);
		p.addRule("con", p.tree("CNSTI"), 14);
		p.addRule("con", p.tree("I0I"), 15);

		p.generateMatcher("src", ".com.madhu.minc.jburg", "Sample4");
	}

	public JBurg() {
		symbolTable = new HashMap<String,Symbol>();
	}

	public void addSymbol(String name) {
		addSymbol(name, 0);
	}

	public void addSymbol(String name, int number) {
		Symbol s = new Symbol(name, number);
		symbolTable.put(name, s);
		if (s.isTerminal()) {
			s.arity = -1;
			if (terminals == null) {
				terminals = s;
				return;
			}
			Symbol q = terminals;
			while (q.next != null) {
				q = q.next;
			}
			q.next = s;
		} else {
			s.number = ++ntNumber;
			if (s.number == 1) {
				start = s;
			}
			if (nonTerminals == null) {
				nonTerminals = s;
				return;
			}
			Symbol q = nonTerminals;
			while (q.next != null) {
				q = q.next;
			}
			q.next = s;
		}
	}

	public void generateMatcher(String dest, String packageName, String className) throws IOException {
		String fileName = dest + "/" + packageName.replace('.', '/') + "/" + className + ".java";
		emitter = new Emitter(
			new PrintWriter(
				new FileWriter(fileName)));
		if (start != null) {
			ckreach(start);
		}
		for (Symbol p = nonTerminals; p != null; p = p.next) {
			if (!p.reached) {
				throw new AssertionError("can't reach non-terminal " + p.name);
			}
		}
		emitter.emitHeader(packageName, className);
		emitter.emitDefinitions(nonTerminals, ntNumber);
		emitter.emitNonTerminals(rules, nRules);
		emitter.emitTerminals(terminals);
		emitter.emitRules(rules);
		emitter.emitRule(nonTerminals, ntNumber);
		emitter.emitClosures(nonTerminals);
		if (start != null) {
			emitter.emitState(terminals, start, ntNumber);
		}
		emitter.emitKids(rules, nRules);
		emitter.emitTrailer();

		emitter.close();
		System.out.println("All done!");
	}

	public RTree tree(String id) {
		return tree(id, null);
	}

	public RTree tree(String id, RTree left) {
		return tree(id, left, null);
	}

	/* tree - create & initialize a tree node with the given fields */
	public RTree tree(String id, RTree left, RTree right) {
		RTree t = new RTree();
		Symbol p = (Symbol) symbolTable.get(id);
		int arity = 0;

		if (left != null && right != null) {
			arity = 2;
		} else if (left != null) {
			arity = 1;
		}

		if (p == null && arity > 0) {
			p = new Symbol(id, -1);
			throw new AssertionError(String.format("undefined terminal `%s'\n", id));
		} else if (p == null && arity == 0) {
			p = symbolTable.get(id);
			throw new AssertionError("bogus case: " + id);
		}
		else if (p != null && !p.isTerminal() && arity > 0) {
			p = new Symbol(id, -1);
			throw new AssertionError(String.format("`%s' is a non-terminal\n", id));
		}

		if (p.isTerminal() && p.arity == -1) {
			p.arity = arity;
		}
		if (p.isTerminal() && arity != p.arity) {
			throw new AssertionError(String.format("inconsistent arity for terminal `%s'\n", id));
		}

		t.op = p;
		t.nTerminals = t.op.isTerminal() ? 1 : 0;
		if ((t.left = left) != null)
			t.nTerminals += left.nTerminals;
		if ((t.right = right) != null)
			t.nTerminals += right.nTerminals;
		return t;
	}

	private int defaultRuleNumber = 1;
	
	public Rule addRule(String id, RTree pattern) {
		return addRule(id, pattern, "");
	}
	
	public Rule addRule(String id, RTree pattern, String template) {
		return addRule(id, pattern, template, 0);
	}
	
	public Rule addRule(String id, RTree pattern, String template, int cost) {
		Rule r = addRule(id, pattern, defaultRuleNumber++, cost);
		r.template = template;
		return r;
	}

	public Rule addRule(String id, RTree pattern, int ern) {
		return addRule(id, pattern, ern, 0);
	}

	/* rule - create & initialize a rule with the given fields */
	public Rule addRule(String id, RTree pattern, int ern, int cost) {
		Rule r = new Rule(), q;

		nRules++;
		r.lhs = symbolTable.get(id);
		r.packedErn = ++r.lhs.lhsCount;
		if (r.lhs.rules == null) {
			r.lhs.rules = r;
		} else {
			for (q = r.lhs.rules; q.decode != null; q = q.decode);
			q.decode = r;
		}
		r.pattern = pattern;
		r.ern = ern;
		r.cost = cost;
		Symbol p = (Symbol) pattern.op;
		if (p.isTerminal()) {
			r.next = p.rules;
			p.rules = r;
		} else if (pattern.left == null && pattern.right == null) {
			Symbol p1 = (Symbol) pattern.op;
			r.chain = p1.chain;
		        p1.chain = r;
		}
		if (rules == null) {
			rules = r;
			return r;
		}
		for (q = rules; q.link != null; q = q.link);
		q.link = r;
		return r;
	}

	/* reach - mark all non-terminals in tree t as reachable */
	private void reach(RTree t) {
		Symbol p = (Symbol) t.op;

		if (!p.isTerminal()) {
			Symbol q = (Symbol) p;
			if (!q.reached)
				ckreach(q);
		}
		if (t.left != null)
			reach(t.left);
		if (t.right != null)
			reach(t.right);
	}

	/* ckreach - mark all non-terminals reachable from p */
	private void ckreach(Symbol p) {
        p.reached = true;
		for (Rule r = p.rules; r != null; r = r.decode) {
			reach(r.pattern);
		}
	}
}
