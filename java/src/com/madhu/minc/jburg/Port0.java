
package com.madhu.minc.jburg;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

/*
 * Created on Jul 13, 2007 at 2:25:01 PM
 */
public class Port0 {
	public static final int MAX_COST = Short.MAX_VALUE;

	private String prefix = "burm";
	private boolean iFlag = true;
	private boolean tFlag = true;
	private int ntNumber;
	private Symbol start;
	private Symbol terminals;
	private Symbol nonTerminals;
	private Rule rules;
	private int nRules;
	private HashMap<String,Symbol> table;
	private PrintWriter out;

	public static void main(String[] args) throws IOException {
		Port0 p = new Port0();
		p.generateMatcher();
	}

	public void addSymbol(String name) {
		addSymbol(name, 0);
	}

	public void addSymbol(String name, int number) {
		Symbol s = new Symbol(name, number);
		table.put(name, s);
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

	private class Symbol {		/* terminals: */
		String name;		/* terminal name */
		int arity;		/* operator arity */
		Symbol next;		/* next terminal in esn order */
		Rule rules;		/* rules whose pattern starts with term */
		int number;		/* identifying number */
		int lhsCount;		/* # times nt appears in a rule lhs */
		boolean reached;		/* 1 iff reached from start non-terminal */
		Rule chain;		/* chain rules w/non-terminal on rhs */
		
		public Symbol(String name) {
			this(name, 0);
		}
		
		public Symbol(String name, int number) {
			this.name = name;
			this.number = number;
		}

		public boolean isTerminal() {
			return Character.isUpperCase(name.charAt(0));
		}

		public String toString() {
			return name;
		}
	}

	private class Tree {		/* tree patterns: */
		Symbol op;		/* a terminal or non-terminal */
		Tree left, right;	/* operands */
		int nTerminals;		/* number of terminal nodes in this tree */
		
		public String toString() {
			String result = op.toString();
			if (left != null) {
				result += "(" + left;
			}
			if (right != null) {
				result += "," + right;
			}
			if (left != null) {
				return result + ')';
			}
			return result;
		}
	}

	private class Rule {		/* rules: */
		Symbol lhs;		/* lefthand side non-terminal */
		Tree pattern;		/* rule pattern */
		int ern;		/* external rule number */
		int packedErn;		/* packed external rule number */
		int cost;		/* associated cost */
		Rule link;		/* next rule in ern order */
		Rule next;		/* next rule with same pattern root */
		Rule chain;		/* next chain rule with same rhs */
		Rule decode;		/* next rule with same lhs */
		Rule kids;		/* next rule with same burm_kids pattern */
		
		public String toString() {
			return lhs + ": " + pattern;
		}
	}

	private void yyparse() {
		addSymbol("CNSTI", 21);
		addSymbol("ASGNI", 53);
		addSymbol("INDIRC", 67);
		addSymbol("CVCI", 85);
		addSymbol("ADDRLP", 295);
		addSymbol("ADDI", 309);
		addSymbol("I0I", 661);
		
		addSymbol("stmt");
		addSymbol("disp");
		addSymbol("reg");
		addSymbol("rc");
		addSymbol("con");

		addRule("stmt", tree("ASGNI", tree("disp"), tree("reg")), 4, 1);
		addRule("stmt", tree("reg"), 5);
		addRule("reg", tree("ADDI", tree("reg"), tree("rc")), 6, 1);
		addRule("reg", tree("CVCI", tree("INDIRC", tree("disp"))), 7, 1);
		addRule("reg", tree("I0I"), 8);
		addRule("reg", tree("disp"), 9, 1);
		addRule("disp", tree("ADDI", tree("reg"), tree("con")), 10);
		addRule("disp", tree("ADDRLP"), 11);
		addRule("rc", tree("con"), 12);
		addRule("rc", tree("reg"), 13);
		addRule("con", tree("CNSTI"), 14);
		addRule("con", tree("I0I"), 15);
	}

	public void generateMatcher() throws IOException {
		table = new HashMap<String,Symbol>();

		StringWriter stringWriter = new StringWriter(30000);
		out = new PrintWriter(stringWriter);
		yyparse();
		if (start != null) {
			ckreach(start);
		}
		for (Symbol p = nonTerminals; p != null; p = p.next) {
			if (!p.reached) {
				throw new AssertionError("can't reach non-terminal " + p.name);
			}
		}
		emitheader();
		emitdefs(nonTerminals, ntNumber);
		emitstruct(nonTerminals, ntNumber);
		emitnts(rules, nRules);
		emitterms(terminals);
		if (iFlag)
			emitstring(rules);
		emitrule(nonTerminals);
		emitclosure(nonTerminals);
		if (start != null)
			emitstate(terminals, start, ntNumber);
		print("#ifdef STATE_LABEL\n");
		if (start != null)
			emitlabel(start);
		emitkids(rules, nRules);
		emitfuncs();
		print("#endif\n");
		
		File f = new File("sample4.txt");
		char[] cbuf = new char[(int) f.length()];
		FileReader fr = new FileReader(f);
		fr.read(cbuf);
		fr.close();
		if (stringWriter.toString().equals(new String(cbuf))) {
			System.out.println("Output is valid");
		} else {
			System.out.println("Output is NOT valid");
		}
	}

	public Tree tree(String id) {
		return tree(id, null);
	}

	public Tree tree(String id, Tree left) {
		return tree(id, left, null);
	}

	/* tree - create & initialize a tree node with the given fields */
	public Tree tree(String id, Tree left, Tree right) {
		Tree t = new Tree();
		Symbol p = (Symbol) table.get(id);
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
			p = table.get(id);
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

	public Rule addRule(String id, Tree pattern, int ern) {
		return addRule(id, pattern, ern, 0);
	}

	/* rule - create & initialize a rule with the given fields */
	public Rule addRule(String id, Tree pattern, int ern, int cost) {
		Rule r = new Rule(), q;

		nRules++;
		r.lhs = table.get(id);
		r.packedErn = ++r.lhs.lhsCount;
		if (r.lhs.rules == null) {
			r.lhs.rules = r;
		} else {
			for (q = r.lhs.rules; q.decode != null; q = q.decode)
				;
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
		for (q = rules; q.link != null; q = q.link)
			;
		q.link = r;
		return r;
	}

	/* print - formatted output */
	private void print(String fmt, Object ... ap) {
		int api = 0;

		int index = 0;
		for ( ; index < fmt.length(); index++)
			if (fmt.charAt(index) == '%')
				switch (fmt.charAt(++index)) {
				case 'd': out.printf("%d", ap[api++]); break;
				case 's': out.print(ap[api++]); break;
				case 'P': out.printf("%s_", prefix); break;
				case 'T': {
					Tree t = (Tree) ap[api++];
					print("%S", t.op);
					if (t.left != null && t.right != null)
						print("(%T,%T)", t.left, t.right);
					else if (t.left != null)
						print("(%T)", t.left);
					break;
					}
				case 'R': {
					Rule r = (Rule) ap[api++];
					print("%S: %T", r.lhs, r.pattern);
					break;
					}
				case 'S': out.print(((Symbol)ap[api++]).name); break;
				case '1': case '2': case '3': case '4': case '5': {
					int n = fmt.charAt(index) - '0';
					while (n-- > 0)
						out.print('\t');
					break;
					}
				default: out.print(fmt.charAt(index)); break;			
				}
			else
				out.print(fmt.charAt(index));
	}

	/* reach - mark all non-terminals in tree t as reachable */
	private void reach(Tree t) {
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

	/* emitcase - emit one case in function state */
	private void emitcase(Symbol p, int ntnumber) {
		print("%1case %d: /* %S */\n", p.number, p);
		switch (p.arity) {
		case 0: case -1:
			if (!tFlag) {
				emitleaf(p, ntnumber);
				return;
			}
			break;
		case 1: print("%2assert(l);\n"); break;
		case 2: print("%2assert(l && r);\n"); break;
		default: throw new AssertionError("bad arity " + p.arity);
		}
		Rule r;
		for (r = p.rules; r != null; r = r.next) {
			switch (p.arity) {
			case 0: case -1:
				print("%2{%1/* %R */\n%3c = ", r);
				break;
			case 1:
				if (r.pattern.nTerminals > 1) {
					print("%2if (%1/* %R */\n", r);
					emittest(r.pattern.left, "l", " ");
					print("%2) {\n%3c = ");
				} else
					print("%2{%1/* %R */\n%3c = ", r);
				emitcost(r.pattern.left, "l");
				break;
			case 2:
				if (r.pattern.nTerminals > 1) {
					print("%2if (%1/* %R */\n", r);
					emittest(r.pattern.left,  "l",
						r.pattern.right.nTerminals != 0 ? " && " : " ");
					emittest(r.pattern.right, "r", " ");
					print("%2) {\n%3c = ");
				} else
					print("%2{%1/* %R */\n%3c = ", r);
				emitcost(r.pattern.left,  "l");
				emitcost(r.pattern.right, "r");
				break;
			default: throw new AssertionError("bad arity " + p.arity);
			}
			print("%d;\n", r.cost);
			emitrecord("\t\t\t", r, 0);
			print("%2}\n");
		}
		print("%2break;\n");
	}

	/* emitclosure - emit the closure functions */
	private void emitclosure(Symbol nts) {
		Symbol p;

		for (p = nts; p != null; p = p.next)
			if (p.chain != null)
				print("static void %Pclosure_%S(struct %Pstate *, int);\n", p);
		print("\n");
		for (p = nts; p != null; p = p.next)
			if (p.chain != null) {
				Rule r;
				print("static void %Pclosure_%S(struct %Pstate *p, int c) {\n", p);
				for (r = p.chain; r != null; r = r.chain)
					emitrecord("\t", r, r.cost);
				print("}\n\n");
			}
	}

	/* emitcost - emit cost computation for tree t */
	private void emitcost(Tree t, String v) {
		Symbol p = (Symbol) t.op;

		if (p.isTerminal()) {
			if (t.left != null)
				emitcost(t.left,  String.format("%s->left",  v));
			if (t.right != null)
				emitcost(t.right, String.format("%s->right", v));
		} else
			print("%s->cost[%P%S_NT] + ", v, p);
	}

	/* emitdefs - emit non-terminal defines and data structures */
	private void emitdefs(Symbol nts, int ntnumber) {
		Symbol p;

		for (p = nts; p != null; p = p.next)
			print("#define %P%S_NT %d\n", p, p.number);
		print("int %Pmax_nt = %d;\n\n", ntnumber);
		if (iFlag) {
			print("char *%Pntname[] = {\n%10,\n");
			for (p = nts; p != null; p = p.next)
				print("%1\"%S\",\n", p);
			print("%10\n};\n\n");
		}
	}

	/* emitfuncs - emit functions to access node fields */
	private void emitfuncs() {
		print("int %Pop_label(NODEPTR_TYPE p) {\n" +
	"%1%Passert(p, PANIC(\"NULL tree in %Pop_label\\n\"));\n" +
	"%1return OP_LABEL(p);\n}\n\n");
		print("STATE_TYPE %Pstate_label(NODEPTR_TYPE p) {\n" +
	"%1%Passert(p, PANIC(\"NULL tree in %Pstate_label\\n\"));\n" +
	"%1return STATE_LABEL(p);\n}\n\n");
		print("NODEPTR_TYPE %Pchild(NODEPTR_TYPE p, int index) {\n" +
	"%1%Passert(p, PANIC(\"NULL tree in %Pchild\\n\"));\n" +
	"%1switch (index) {\n%1case 0:%1return LEFT_CHILD(p);\n" +
	"%1case 1:%1return RIGHT_CHILD(p);\n%1}\n" +
	"%1%Passert(0, PANIC(\"Bad index %%d in %Pchild\\n\", index));\n%1return 0;\n}\n\n");
	}

	/* emitheader - emit initial definitions */
	private void emitheader() {
		print("#include <limits.h>\n#include <stdlib.h>\n");
		print("#ifndef STATE_TYPE\n#define STATE_TYPE int\n#endif\n");
		print("#ifndef ALLOC\n#define ALLOC(n) malloc(n)\n#endif\n" +
	"#ifndef %Passert\n#define %Passert(x,y) if (!(x)) { y; abort(); }\n#endif\n\n");
		if (tFlag)
			print("static NODEPTR_TYPE %Pnp;\n\n");
	}

	/* computekids - compute paths to kids in tree t */
	private void computekids(Tree t, String v, StringBuffer bp, int[] ip) {
		Symbol p = (Symbol) t.op;

		if (!p.isTerminal()) {
			bp.append(String.format("\t\tkids[%d] = %s;\n", ip[0]++, v));
		} else if (p.arity > 0) {
			computekids(t.left, String.format("LEFT_CHILD(%s)", v), bp, ip);
			if (p.arity == 2)
				computekids(t.right, String.format("RIGHT_CHILD(%s)", v), bp, ip);
		}
	}

	/* emitkids - emit burm_kids */
	private void emitkids(Rule rules, int nrules) {
		int i;
		Rule r, rc[] = new Rule[nrules + 1];
		String str[]  = new String[nrules + 1];

		for (i = 0, r = rules; r != null; r = r.link) {
			StringBuffer buf = new StringBuffer(1024);
			int[] ipj = new int[1];
			computekids(r.pattern, "p", buf, ipj);
			int j = ipj[0];
			for (j = 0; str[j] != null && !str[j].equals(buf.toString()); j++)
				;
			if (str[j] == null)
				str[j] = buf.toString();
			r.kids = rc[j];
			rc[j] = r;
		}
		print("NODEPTR_TYPE *%Pkids(NODEPTR_TYPE p, int eruleno, NODEPTR_TYPE kids[]) {\n" +
	"%1%Passert(p, PANIC(\"NULL tree in %Pkids\\n\"));\n" +
	"%1%Passert(kids, PANIC(\"NULL kids in %Pkids\\n\"));\n" +
	"%1switch (eruleno) {\n");
		for (i = 0; (r = rc[i]) != null; i++) {
			for ( ; r != null; r = r.kids)
				print("%1case %d: /* %R */\n", r.ern, r);
			print("%s%2break;\n", str[i]);
		}
		print("%1default:\n%2%Passert(0, PANIC(\"Bad external rule number %%d in %Pkids\\n\", eruleno));\n%1}\n%1return kids;\n}\n\n");
	}

	/* computents - fill in bp with burm_nts vector for tree t */
	private void computents(Tree t, StringBuffer bp) {
		if (t != null) {
			Symbol p = (Symbol) t.op;
			if (!p.isTerminal()) {
				bp.append(String.format("%s_%s_NT, ", prefix, p.name));
			} else {
				computents(t.left,  bp);
				computents(t.right, bp);
			}
		}
	}

	/* emitnts - emit burm_nts ragged array */
	private void emitnts(Rule rules, int nrules) {
		Rule r;
		int i, j, nts[] = new int[nrules];
		String[] str = new String[nrules];

		for (i = 0, r = rules; r != null; r = r.link) {
			StringBuffer buf = new StringBuffer(1024);
			computents(r.pattern, buf);
			for (j = 0; str[j] != null && !str[j].equals(buf.toString()); j++);
			if (str[j] == null) {
				print("static short %Pnts_%d[] = { %s0 };\n", j, buf);
				str[j] = buf.toString();
			}
			nts[i++] = j;
		}
		print("\nshort *%Pnts[] = {\n");
		for (i = j = 0, r = rules; r != null; r = r.link) {
			for ( ; j < r.ern; j++)
				print("%10,%1/* %d */\n", j);
			print("%1%Pnts_%d,%1/* %d */\n", nts[i++], j++);
		}
		print("};\n\n");
	}

	/* emitlabel - emit the labelling functions */
	private void emitlabel(Symbol start) {
		print("static void %Plabel1(NODEPTR_TYPE p) {\n" +
	"%1%Passert(p, PANIC(\"NULL tree in %Plabel\\n\"));\n" +
	"%1switch (%Parity[OP_LABEL(p)]) {\n" +
	"%1case 0:\n");
		if (tFlag)
			print("%2%Pnp = p;\n");
		print("%2STATE_LABEL(p) = %Pstate(OP_LABEL(p), 0, 0);\n%2break;\n" +
	"%1case 1:\n%2%Plabel1(LEFT_CHILD(p));\n");
		if (tFlag)
			print("%2%Pnp = p;\n");
		print("%2STATE_LABEL(p) = %Pstate(OP_LABEL(p),\n" +
	"%3STATE_LABEL(LEFT_CHILD(p)), 0);\n%2break;\n" +
	"%1case 2:\n%2%Plabel1(LEFT_CHILD(p));\n%2%Plabel1(RIGHT_CHILD(p));\n");
		if (tFlag)
			print("%2%Pnp = p;\n");
		print("%2STATE_LABEL(p) = %Pstate(OP_LABEL(p),\n" +
	"%3STATE_LABEL(LEFT_CHILD(p)),\n%3STATE_LABEL(RIGHT_CHILD(p)));\n%2break;\n" +
	"%1}\n}\n\n");
		print(
	"STATE_TYPE %Plabel(NODEPTR_TYPE p) {\n%1%Plabel1(p);\n" +
	"%1return ((struct %Pstate *)STATE_LABEL(p))->rule.%P%S ? STATE_LABEL(p) : 0;\n" +
	"}\n\n", start);
	}

	/* closure - fill in cost & rule with results of chain rules w/p as rhs */
	private void closure(int cost[], Rule rule[], Symbol p, int c) {
		for (Rule r = p.chain; r != null; r = r.chain)
			if (c + r.cost < cost[r.lhs.number]) {
				cost[r.lhs.number] = c + r.cost;
				rule[r.lhs.number] = r;
				closure(cost, rule, r.lhs, c + r.cost);
			}
	}

	private int[] emitleaf_cost;
	private Rule[] emitleaf_rule;

	/* emitleaf - emit state code for a leaf */
	private void emitleaf(Symbol p, int ntnumber) {
		int i;

		if (emitleaf_cost == null) {
			emitleaf_cost = new int[ntnumber + 1];
			emitleaf_rule = new Rule[ntnumber + 1];
		}
		for (i = 0; i <= ntnumber; i++) {
			emitleaf_cost[i] = MAX_COST;
			emitleaf_rule[i] = null;
		}
		for (Rule r = p.rules; r != null; r = r.next) {
			if (r.pattern.left == null && r.pattern.right == null) {
				emitleaf_cost[r.lhs.number] = r.cost;
				emitleaf_rule[r.lhs.number] = r;
				closure(emitleaf_cost, emitleaf_rule, r.lhs, r.cost);
			}
		}
		print("%2{\n%3static struct %Pstate z = { %d, 0, 0,\n%4{%10,\n", p.number);
		for (i = 1; i <= ntnumber; i++) {
			if (emitleaf_cost[i] < MAX_COST) {
				print("%5%d,%1/* %R */\n", emitleaf_cost[i], emitleaf_rule[i]);
			} else {
				print("%5%d,\n", emitleaf_cost[i]);
			}
		}
		print("%4},{\n");
		for (i = 1; i <= ntnumber; i++) {
			if (emitleaf_rule[i] != null) {
				print("%5%d,%1/* %R */\n", emitleaf_rule[i].packedErn, emitleaf_rule[i]);
			} else {
				print("%50,\n");
			}
		}
		print("%4}\n%3};\n%3return (STATE_TYPE)&z;\n%2}\n");
	}

	/* emitrecord - emit code that tests for a winning match of rule r */
	private void emitrecord(String pre, Rule r, int cost) {
		print("%sif (", pre);
		if (tFlag)
			print("%Ptrace(%Pnp, %d, c + %d, p->cost[%P%S_NT]), ",
				r.ern, cost, r.lhs);
		print("c + %d < p->cost[%P%S_NT]) {\n" +
	"%s%1p->cost[%P%S_NT] = c + %d;\n%s%1p->rule.%P%S = %d;\n",
			cost, r.lhs, pre, r.lhs, cost, pre, r.lhs,
			r.packedErn);
		if (r.lhs.chain != null)
			print("%s%1%Pclosure_%S(p, c + %d);\n", pre, r.lhs, cost);
		print("%s}\n", pre);
	}

	/* emitrule - emit decoding vectors and burm_rule */
	private void emitrule(Symbol nts) {
		for (Symbol p = nts; p != null; p = p.next) {
			Rule r;
			print("static short %Pdecode_%S[] = {\n%10,\n", p);
			for (r = p.rules; r != null; r = r.decode)
				print("%1%d,\n", r.ern);
			print("};\n\n");
		}
		print("int %Prule(STATE_TYPE state, int goalnt) {\n" +
	"%1%Passert(goalnt >= 1 && goalnt <= %d, PANIC(\"Bad goal nonterminal %%d in %Prule\\n\", goalnt));\n" +
	"%1if (!state)\n%2return 0;\n%1switch (goalnt) {\n", ntNumber);
		for (Symbol p = nts; p != null; p = p.next)
			print("%1case %P%S_NT:" +
	"%1return %Pdecode_%S[((struct %Pstate *)state)->rule.%P%S];\n", p, p, p);
		print("%1default:\n%2%Passert(0, PANIC(\"Bad goal nonterminal %%d in %Prule\\n\", goalnt));\n%1}\n%1return 0;\n}\n\n");
	}

	/* emitstate - emit state function */
	private void emitstate(Symbol terms, Symbol start, int ntnumber) {
		int i;
		Symbol p;

		print("STATE_TYPE %Pstate(int op, STATE_TYPE left, STATE_TYPE right) {\n%1int c;\n" +
	"%1struct %Pstate *p, *l = (struct %Pstate *)left,\n" +
	"%2*r = (struct %Pstate *)right;\n\n%1assert(sizeof (STATE_TYPE) >= sizeof (void *));\n%1");
		if (!tFlag)
			print("if (%Parity[op] > 0) ");
		print("{\n%2p = ALLOC(sizeof *p);\n" +
	"%2%Passert(p, PANIC(\"ALLOC returned NULL in %Pstate\\n\"));\n" +
	"%2p->op = op;\n%2p->left = l;\n%2p->right = r;\n%2p->rule.%P%S = 0;\n", start);
		for (i = 1; i <= ntnumber; i++)
			print("%2p->cost[%d] =\n", i);
		print("%3%d;\n%1}\n%1switch (op) {\n", MAX_COST);
		for (p = terms; p != null; p = p.next)
			emitcase(p, ntnumber);
		print("%1default:\n" +
	"%2%Passert(0, PANIC(\"Bad operator %%d in %Pstate\\n\", op));\n%1}\n" +
	"%1return (STATE_TYPE)p;\n}\n\n");
	}

	/* emitstring - emit array of rules and costs */
	private void emitstring(Rule rules) {
		Rule r;
		int k;

		print("short %Pcost[][4] = {\n");
		for (k = 0, r = rules; r != null; r = r.link) {
			for ( ; k < r.ern; k++) {
				print("%1{ 0 },%1/* %d */\n", k);
			}
			print("%1{ %d },%1/* %d = %R */\n", r.cost, k++, r);
		}
		print("};\n\nchar *%Pstring[] = {\n");
		for (k = 0, r = rules; r != null; r = r.link) {
			for ( ; k < r.ern; k++) {
				print("%1/* %d */%10,\n", k);
			}
			print("%1/* %d */%1\"%R\",\n", k++, r);
		}
		print("};\n\n");
	}

	/* emitstruct - emit the definition of the state structure */
	private void emitstruct(Symbol nts, int ntnumber) {
		print("struct %Pstate {\n%1int op;\n%1struct %Pstate *left, *right;\n" +
	"%1short cost[%d];\n%1struct {\n", ntnumber + 1);
		for ( ; nts != null; nts = nts.next) {
			int n = 1, m = nts.lhsCount;
			while ((m >>= 1) != 0)
				n++;
			print("%2unsigned %P%S:%d;\n", nts, n);
		}
		print("%1} rule;\n};\n\n");
	}

	/* emitterms - emit terminal data structures */
	private void emitterms(Symbol terms) {
		Symbol p;
		int k;

		print("char %Parity[] = {\n");
		for (k = 0, p = terms; p != null; p = p.next) {
			for ( ; k < p.number; k++)
				print("%10,%1/* %d */\n", k);
			print("%1%d,%1/* %d=%S */\n", p.arity < 0 ? 0 : p.arity, k++, p);
		}
		print("};\n\n");
		if (iFlag) {
			print("char *%Popname[] = {\n");
			for (k = 0, p = terms; p != null; p = p.next) {
				for ( ; k < p.number; k++)
					print("%1/* %d */%10,\n", k);
				print("%1/* %d */%1\"%S\",\n", k++, p);
			}
			print("};\n\n");
		}
	}

	/* emittest - emit clause for testing a match */
	private void emittest(Tree t, String v, String suffix) {
		Symbol p = (Symbol) t.op;

		if (p.isTerminal()) {
			print("%3%s->op == %d%s/* %S */\n", v, p.number,
				t.nTerminals > 1 ? " && " : suffix, p);
			if (t.left != null)
				emittest(t.left, String.format("%s->left",  v),
					t.right != null && t.right.nTerminals != 0 ? " && " : suffix);
			if (t.right != null)
				emittest(t.right, String.format("%s->right", v), suffix);
		}
	}
}
