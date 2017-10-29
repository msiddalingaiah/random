
package com.madhu.minc.jburg;

import java.io.PrintWriter;

/*
 * Created on Jul 19, 2007 at 7:20:44 PM
 */
public class Emitter {
	private PrintWriter out;
	private String prefix = "burm";

	public Emitter(PrintWriter writer) {
		out = writer;
	}

	public void close() {
		out.close();
	}

	/* emitheader - emit initial definitions */
	public void emitHeader(String packageName, String className) {
		print("\npackage %s;\n\n", packageName);
		print("import com.madhu.minc.jburg.State;\n");
		print("import com.madhu.minc.jburg.STree;\n");
		print("import com.madhu.minc.jburg.PatternMatcher;\n\n");
		print("public class %s extends PatternMatcher {\n", className);
	}

	/* emitdefs - emit non-terminal defines and data structures */
	public void emitDefinitions(Symbol nts, int ntnumber) {
		for (Symbol p = nts; p != null; p = p.next) {
			print("%1private static final int %P%S_NT = %d;\n", p, p.number);
		}
		print("\n");
	}

	/* emitnts - emit burm_nts ragged array */
	public void emitNonTerminals(Rule rules, int nrules) {
		Rule r;
		int i, j, nts[] = new int[nrules];
		String[] str = new String[nrules];

		for (i = 0, r = rules; r != null; r = r.link) {
			StringBuffer buf = new StringBuffer(1024);
			computeNonTerminals(r.pattern, buf);
			for (j = 0; str[j] != null && !str[j].equals(buf.toString()); j++);
			if (str[j] == null) {
				print("%1private static short %Pnts_%d[] = { %s0 };\n", j, buf);
				str[j] = buf.toString();
			}
			nts[i++] = j;
		}
		print("\n%1private short %Pnts[][] = {\n");
		for (i = j = 0, r = rules; r != null; r = r.link) {
			for ( ; j < r.ern; j++) {
				print("%2null,%1/* %d */\n", j);
			}
			print("%2%Pnts_%d,%1/* %d */\n", nts[i++], j++);
		}
		print("%1};\n\n");

		print("%1public short[] getNonTerminals(int eruleno) {\n");
		print("%2return burm_nts[eruleno];\n");
		print("%1}\n\n");
	}

	/* emitterms - emit terminal data structures */
	public void emitTerminals(Symbol terms) {
		for (Symbol p = terms; p != null; p = p.next) {
			print("%1private static final int %S = %d;\n", p, p.number);
		}
		print("\n%1public int getArity(int op) {\n");
		print("%2switch(op) {\n");
		for (Symbol p = terms; p != null; p = p.next) {
			print("%3case %S: return %d;\n", p, p.arity < 0 ? 0 : p.arity);
		}
		print("%2}\n%2return 0;\n%1}\n\n");
	}

	/* emitstring - emit array of rules and costs */
	public void emitRules(Rule rules) {
		Rule r;
		int k;

		print("%1private static String[] %Pstring = {\n");
		for (k = 0, r = rules; r != null; r = r.link) {
			for ( ; k < r.ern; k++) {
				print("%1/* %d */%1null,\n", k);
			}
			print("%1/* %d */%1\"%R\",\n", k++, r);
		}
		print("%1};\n\n");

		print("%1public String getRuleString(int eruleno) {\n");
		print("%2return burm_string[eruleno];\n");
		print("%1}\n\n");
	}

	/* emitrule - emit decoding vectors and burm_rule */
	public void emitRule(Symbol nts, int ntNumber) {
		for (Symbol p = nts; p != null; p = p.next) {
			Rule r;
			print("%1private static short %Pdecode_%S[] = {\n%20,\n", p);
			for (r = p.rules; r != null; r = r.decode) {
				print("%2%d,\n", r.ern);
			}
			print("%1};\n\n");
		}

		print("%1public int getRule(State state, int goalnt) {\n");
		print("%2if (state == null) {\n");
		print("%3return 0;\n");
		print("%2}\n");
		print("%2switch (goalnt) {\n", ntNumber);
		for (Symbol p = nts; p != null; p = p.next) {
			print("%3case %P%S_NT: return %Pdecode_%S[state.getRule(goalnt)];\n", p, p);
		}
		print("%2}\n");
		print("%2throw new AssertionError(\"Bad goal nonterminal in getRule(): \" + goalnt);\n");
		print("%1}\n\n");
	}

	/* emitclosure - emit the closure functions */
	public void emitClosures(Symbol nts) {
		for (Symbol p = nts; p != null; p = p.next) {
			if (p.chain != null) {
				print("\tprivate void doClosure_%S(State p, int c) {\n", p);
				for (Rule r = p.chain; r != null; r = r.chain) {
					emitRecord("\t\t", r, r.cost);
				}
				print("\t}\n\n");
			}
		}
	}

	/* emitstate - emit state function */
	public void emitState(Symbol terms, Symbol start, int ntnumber) {
		print("%1public State getNextState(int op, State left, State right) {\n" +
			"%2int c;\n" +
			"%2State p = null;\n" +
			"%2State l = left;\n" +
			"%2State r = right;\n\n");
		print("%2p = new State(op, left, right);\n");
		print("%2p.setRule(%P%S_NT, 0);\n\n", start);
		print("%2switch (op) {\n");
		for (Symbol p = terms; p != null; p = p.next) {
			emitCase(p, ntnumber);
		}
		print("%2default:\n%3throw new AssertionError();\n%2}\n" +
			"%2return p;\n%1}\n\n");
	}

	/* emitkids - emit burm_kids */
	public void emitKids(Rule rules, int nrules) {
		Rule r, rc[] = new Rule[nrules + 1];
		String str[]  = new String[nrules + 1];

		int i;
		for (i = 0, r = rules; r != null; r = r.link) {
			StringBuffer buf = new StringBuffer(1024);
			int[] ipj = new int[1];
			computeKids(r.pattern, "p", buf, ipj);
			int j = ipj[0];
			for (j = 0; str[j] != null && !str[j].equals(buf.toString()); j++)
				;
			if (str[j] == null)
				str[j] = buf.toString();
			r.kids = rc[j];
			rc[j] = r;
		}
		print("%1public void %Pkids(STree p, int eruleno, STree kids[]) {\n" +
			"%2switch (eruleno) {\n");
		for (i = 0; (r = rc[i]) != null; i++) {
			for ( ; r != null; r = r.kids) {
				print("%2case %d: /* %R */\n", r.ern, r);
			}
			print("%s%3break;\n\n", str[i]);
		}
		print("%2default:\n" +
				"%3throw new AssertionError(\"Bad external rule number\");\n" +
				"%2}\n" +
				"%1}\n");
	}

	public void emitTrailer() {
		print("}\n");
	}

	/* computekids - compute paths to kids in tree t */
	private void computeKids(RTree t, String v, StringBuffer bp, int[] ip) {
		Symbol p = t.op;

		if (!p.isTerminal()) {
			bp.append(String.format("\t\t\tkids[%d] = %s;\n", ip[0]++, v));
		} else if (p.arity > 0) {
			computeKids(t.left, String.format("%s.getLeft()", v), bp, ip);
			if (p.arity == 2)
				computeKids(t.right, String.format("%s.getRight()", v), bp, ip);
		}
	}

	/* emitcost - emit cost computation for tree t */
	private void emitCost(RTree t, String v) {
		Symbol p = (Symbol) t.op;

		if (p.isTerminal()) {
			if (t.left != null)
				emitCost(t.left,  String.format("%s.getLeft()",  v));
			if (t.right != null)
				emitCost(t.right, String.format("%s.getRight()", v));
		} else {
			print("%s.getCost(%P%S_NT) + ", v, p);
		}
	}

	/* computents - fill in bp with burm_nts vector for tree t */
	private void computeNonTerminals(RTree t, StringBuffer bp) {
		if (t != null) {
			Symbol p = (Symbol) t.op;
			if (!p.isTerminal()) {
				bp.append(String.format("%s_%s_NT, ", prefix, p.name));
			} else {
				computeNonTerminals(t.left,  bp);
				computeNonTerminals(t.right, bp);
			}
		}
	}

	/* emitrecord - emit code that tests for a winning match of rule r */
	private void emitRecord(String pre, Rule r, int cost) {
		print("%sif (", pre);
		print("c + %d < p.getCost(%P%S_NT)) {\n" +
			"%s%1p.setCostRule(%P%S_NT, c + %d, %d);\n",
			cost, r.lhs, pre, r.lhs, cost, r.packedErn);
		if (r.lhs.chain != null) {
			print("%s%1doClosure_%S(p, c + %d);\n", pre, r.lhs, cost);
		}
		print("%s}\n", pre);
	}

	/* emitcase - emit one case in function state */
	private void emitCase(Symbol p, int ntnumber) {
		print("%2case %d: /* %S */\n", p.number, p);

		for (Rule r = p.rules; r != null; r = r.next) {
			switch (p.arity) {
			case 0: case -1:
				print("%2{%1/* %R */\n%3c = ", r);
				break;
			case 1:
				if (r.pattern.nTerminals > 1) {
					print("%2if (%1/* %R */\n", r);
					emitTest(r.pattern.left, "l", " ");
					print("%2) {\n%3c = ");
				} else {
					print("%2{%1/* %R */\n%3c = ", r);
				}
				emitCost(r.pattern.left, "l");
				break;
			case 2:
				if (r.pattern.nTerminals > 1) {
					print("%2if (%1/* %R */\n", r);
					emitTest(r.pattern.left,  "l",
						r.pattern.right.nTerminals != 0 ? " && " : " ");
					emitTest(r.pattern.right, "r", " ");
					print("%2) {\n%3c = ");
				} else
					print("%2{%1/* %R */\n%3c = ", r);
				emitCost(r.pattern.left,  "l");
				emitCost(r.pattern.right, "r");
				break;
			default: throw new AssertionError("bad arity " + p.arity);
			}
			print("%d;\n", r.cost);
			emitRecord("\t\t\t", r, 0);
			print("%2}\n");
		}
		print("%2break;\n\n");
	}

	/* emittest - emit clause for testing a match */
	private void emitTest(RTree t, String v, String suffix) {
		Symbol p = t.op;

		if (p.isTerminal()) {
			print("%3%s.getOperator() == %d%s/* %S */\n", v, p.number,
				t.nTerminals > 1 ? " && " : suffix, p);
			if (t.left != null)
				emitTest(t.left, String.format("%s.getLeft()",  v),
					t.right != null && t.right.nTerminals != 0 ? " && " : suffix);
			if (t.right != null)
				emitTest(t.right, String.format("%s.getRight()", v), suffix);
		}
	}

	/* print - formatted output */
	private void print(String fmt, Object ... ap) {
		int api = 0;

		int index = 0;
		for ( ; index < fmt.length(); index++) {
			if (fmt.charAt(index) == '%') {
				switch (fmt.charAt(++index)) {
				case 'd': out.printf("%d", ap[api++]); break;
				case 's': out.print(ap[api++]); break;
				case 'P': out.printf("%s_", prefix); break;
				case 'T': {
					RTree t = (RTree) ap[api++];
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
			} else {
				out.print(fmt.charAt(index));
			}
		}
	}
}
