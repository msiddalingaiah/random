
package com.madhu.minc.jburg;

/*
 * Created on Jul 18, 2007 at 9:10:33 PM
 */
public class Sample4Test {
	private PatternMatcher s4;

	static int trace;

	public Sample4Test() {
		s4 = new Sample4();
	}

	/* burm_trace - print trace message for matching p; decrement trace */
	void burm_trace(STree p, int eruleno, int cost, int bestcost) {
		if (trace < 0) {
			System.out.println("0x%p matched %s = %d with cost %d vs. %d");
			//fprintf(stderr, "0x%p matched %s = %d with cost %d vs. %d\n", p,
			//	burm_string[eruleno], eruleno, cost, bestcost);
		} else if (trace > 0 && cost < bestcost) {
			--trace;
			System.out.println("0x%p matched %s = %d with cost %d");
			//fprintf(stderr, "0x%p matched %s = %d with cost %d\n", p,
			//	burm_string[eruleno], eruleno, cost);
		}
	}

	/* dumpCover - print the matched cover for p */
	void dumpCover(STree p, int goalnt, int indent) {
		int eruleno = s4.getRule(p.getState(), goalnt);
		short[] nts = s4.getNonTerminals(eruleno);
		STree[] kids = new STree[10];
		int i;

		for (i = 0; i < indent; i++) {
			System.out.print(" ");
		}
		System.out.println(s4.getRuleString(eruleno));
		s4.burm_kids(p, eruleno, kids);
		for (i = 0; nts[i] != 0; i++) {
			dumpCover(kids[i], nts[i], indent + 1);
		}
	}

	void doLabel(STree p) {
		if (p == null) {
			throw new AssertionError("NULL tree in burm_label");
		}
		switch (s4.getArity(p.getOp())) {
		case 0:
			p.setState(s4.getNextState(p.getOp()));
			break;
		case 1:
			doLabel(p.getLeft());
			p.setState(s4.getNextState(p.getOp(),
				p.getLeft().getState()));
			break;
		case 2:
			doLabel(p.getLeft());
			doLabel(p.getRight());
			p.setState(s4.getNextState(p.getOp(),
				p.getLeft().getState(),
				p.getRight().getState()));
			break;
		}
	}

	State label(STree p) {
		doLabel(p);
		return p.getState().getRule(PatternMatcher.GOAL_NT) != 0 ? p.getState() : null;
	}

	void gen(STree p) {
		if (label(p) == null) {
			System.out.println("no cover");
		} else {
			dumpCover(p, PatternMatcher.GOAL_NT, 0);
		}
	}

	private static final int CNSTI = 21;
	private static final int ASGNI = 53;
	private static final int INDIRC = 67;
	private static final int CVCI = 85;
	private static final int ADDRLP = 295;
	private static final int ADDI = 309;
	private static final int I0I = 661;

	public static void main(String[] args) {
		System.out.println("jburg: i = c + 4;");
		STree c4 = new STree(CNSTI, 4);
		STree cv = new STree(CVCI,
			new STree(INDIRC,
				new STree(ADDRLP)));
		STree t = new STree(ASGNI,
			new STree(ADDRLP),
			new STree(ADDI, cv, c4));
		Sample4Test m = new Sample4Test();
		m.gen(t);
	}
}
