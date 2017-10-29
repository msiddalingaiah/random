
package com.madhu.picolr.burg;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.madhu.picolr.Grammar;
import com.madhu.picolr.Parser;
import com.madhu.picolr.Terminal;
import com.madhu.picolr.TreeFactory;
import com.madhu.picolr.gui.TreeFrame;

/*
 * Created on May 13, 2008 at 7:30:03 PM
 */
public class RuleFactory {
	private Parser<BURGTree> parser;
	private ArrayList<Rule> rules;
	private HashMap<Symbol, ArrayList<Rule>> chainRules;

	public RuleFactory() {
		Grammar<BURGTree> grammar = new Grammar<BURGTree>();
		grammar.addTerminal(Grammar.WHITESPACE, "( |\t|\r|\n|\f)+");
		grammar.addTerminal("(", "\\(");
		grammar.addTerminal(")", "\\)");
		grammar.addTerminal("NUMBER", "(0-9)+");
		grammar.addTerminal("TERMINAL", "(A-Z|_)(A-Z|0-9|_)*");
		grammar.addTerminal("NONTERMINAL", "(a-z)(a-z|0-9)*");
		grammar.addTerminal(":", "\\:");
		grammar.addTerminal(",", "\\,");

		grammar.addProduction("rule",
			"NONTERMINAL : tree EOF", "rule NONTERMINAL tree"
		);
		grammar.addProduction("tree",
			"NONTERMINAL", "NONTERMINAL",
			"TERMINAL", "TERMINAL",
			"TERMINAL ( list )", "TERMINAL list"
		);
		grammar.addProduction("list",
			"list , tree", "list tree",
			"tree", "null tree"
		);
		parser = grammar.getParser();
		parser.setTreeFactory(new TreeFactory<BURGTree>() {
			@Override
			public BURGTree create(Terminal t) {
				if (t == null) {
					return new BURGTree(null);
				}
				return new BURGTree(new Symbol(t.getText()));
			}
		});
		rules = new ArrayList<Rule>();
		chainRules = new HashMap<Symbol, ArrayList<Rule>>();
	}

	public Rule addRule(String ruleString, Method action) throws IOException {
		return addRule(ruleString, 0, action);
	}

	public Rule addRule(String ruleString, int cost, Method action) throws IOException {
		Rule rule = parseRule(ruleString, cost, action);
		if (!rule.isChain()) {
			rules.add(rule);
			return rule;
		}
		Symbol symbol = rule.getRHS().getValue();
		ArrayList<Rule> list = chainRules.get(symbol);
		if (list == null) {
			list = new ArrayList<Rule>();
			chainRules.put(symbol, list);
		}
		list.add(rule);
		return rule;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public HashMap<Symbol, ArrayList<Rule>> getChainRules() {
		return chainRules;
	}
	
	public Rule parseRule(String input, Method action) throws IOException {
		return parseRule(input, 0, action);
	}

	public Rule parseRule(String input, int cost, Method action) throws IOException {
		BURGTree tree = parse(input);
		Symbol nt = tree.get(0).getValue();
		BURGTree rhs = tree.get(1);
		return new Rule(nt, rhs, cost, action);
	}
	
	private BURGTree parse(String input) throws IOException {
		return parser.parse(new StringReader(input));
	}

	public static void main(String[] args) throws IOException {
		RuleFactory rf = new RuleFactory();

		rf.addRule("con: I0I", 0, null);
		rf.addRule("con: CNSTI", 0, null);
		rf.addRule("disp: ADDRLP", 0, null);
		rf.addRule("disp: ADDI(reg,con)", 0, null);
		rf.addRule("reg: I0I", 0, null);
		rf.addRule("reg: CVCI(INDIRC(disp))", 1, null);
		rf.addRule("reg: ADDI(reg,rc)", 1, null);
		rf.addRule("stmt: ASGNI(disp,reg)", 1, null);

		rf.addRule("rc: reg", 0, null);
		rf.addRule("stmt: reg", 0, null);
		rf.addRule("rc: con", 0, null);
		rf.addRule("reg: disp", 1, null);

		System.out.println("Rules:");
		ArrayList<Rule> rules = rf.getRules();
		for (Rule rule : rules) {
			System.out.println(rule);
		}

		System.out.println("\nChains:");
		HashMap<Symbol, ArrayList<Rule>> chains = rf.getChainRules();
		Set<Symbol> nts = chains.keySet();
		for (Symbol nt : nts) {
			System.out.printf("%s: %s%n", nt, chains.get(nt));
		}

//		BURGTree rule = rp.parse("reg:	CVCI(INDIRC(disp,a,b,c))");
//		System.out.println(rule);
//		TreeFrame tf = new TreeFrame("Parse Tree", rule);
//		tf.setSize(400, 300);
//		tf.setVisible(true);
	}

	public static String toString(BURGTree tree) {
		if (tree.isLeaf()) {
			return tree.getValue().toString();
		}
		StringBuffer sb = new StringBuffer("(");
		sb.append(tree.getValue().toString());
		int n = tree.size();
		for (int i = 0; i < n; i += 1) {
			sb.append(' ');
			sb.append(tree.get(i).toString());
		}
		sb.append(")");
		return sb.toString();
	}

	public void create(Object gen) throws IOException {
		Method[] methods = gen.getClass().getDeclaredMethods();
		for (Method method : methods) {
			BURGRule br = method.getAnnotation(BURGRule.class);
			if (br != null) {
				addRule(br.rule(), br.cost(), method);
			}
		}
	}
}
