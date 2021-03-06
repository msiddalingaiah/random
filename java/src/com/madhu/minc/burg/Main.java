
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 21, 2006
 */

package com.madhu.minc.burg;

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
public class Main {
	public static final int ADDI=309;
	public static final int ADDRLP=295;
	public static final int ASGNI=53;
	public static final int CNSTI=21;
	public static final int CVCI=85;
	public static final int I0I=661;
	public static final int INDIRC=67;

	private static final int burm_stmt_NT = 1;
	private static final int burm_disp_NT = 2;
	private static final int burm_reg_NT = 3;
	private static final int burm_rc_NT = 4;
	private static final int burm_con_NT = 5;
	//private static final int burm_max_nt = 5;

/*
	private static final String burm_ntname[] = {
		null,
		"stmt",
		"disp",
		"reg",
		"rc",
		"con",
		null
	};
*/
	
	// These are the nonterminals on the RHS of each rule
	// The list is distinct, e.g. duplicates are not listed (see below)
	static short burm_nts_0[] = { burm_disp_NT, burm_reg_NT, 0 };
	static short burm_nts_1[] = { burm_reg_NT, 0 };
	static short burm_nts_2[] = { burm_reg_NT, burm_rc_NT, 0 };
	static short burm_nts_3[] = { burm_disp_NT, 0 };
	static short burm_nts_4[] = { 0 };
	static short burm_nts_5[] = { burm_reg_NT, burm_con_NT, 0 };
	static short burm_nts_6[] = { burm_con_NT, 0 };

	// This is a mapping from a rule to nonterminals on the RHS
	short burm_nts[][] = {
		null,	/* 0 */
		null,	/* 1 */
		null,	/* 2 */
		null,	/* 3 */
		burm_nts_0,	/* 4 */
		burm_nts_1,	/* 5 */
		burm_nts_2,	/* 6 */
		burm_nts_3,	/* 7 */
		burm_nts_4,	/* 8 */
		burm_nts_3,	/* 9 */
		burm_nts_5,	/* 10 */
		burm_nts_4,	/* 11 */
		burm_nts_6,	/* 12 */
		burm_nts_1,	/* 13 */
		burm_nts_4,	/* 14 */
		burm_nts_4,	/* 15 */
	};

	public int getArity(int op) {
		switch(op) {
		case CNSTI: return 0;
		case ASGNI: return 2;
		case INDIRC: return 1;
		case CVCI: return 1;
		case ADDRLP: return 0; 
		case ADDI: return 2;
		case I0I: return 0;
		}
		return 0;
	}

//	char burm_arity[] = {
//		0,	/* 21=CNSTI */
//		2,	/* 53=ASGNI */
//		1,	/* 67=INDIRC */
//		1,	/* 85=CVCI */
//		0,	/* 295=ADDRLP */
//		2,	/* 309=ADDI */
//		0,	/* 661=I0I */
//	};

//	String burm_opname[] = {
//		/* 21 */	"CNSTI",
//		/* 53 */	"ASGNI",
//		/* 67 */	"INDIRC",
//		/* 85 */	"CVCI",
//		/* 295 */	"ADDRLP",
//		/* 309 */	"ADDI",
//		/* 661 */	"I0I",
//	};

	static String burm_string[] = {
		/* 0 */	null,
		/* 1 */	null,
		/* 2 */	null,
		/* 3 */	null,
		/* 4 */	"stmt: ASGNI(disp,reg)",
		/* 5 */	"stmt: reg",
		/* 6 */	"reg: ADDI(reg,rc)",
		/* 7 */	"reg: CVCI(INDIRC(disp))",
		/* 8 */	"reg: I0I",
		/* 9 */	"reg: disp",
		/* 10 */	"disp: ADDI(reg,con)",
		/* 11 */	"disp: ADDRLP",
		/* 12 */	"rc: con",
		/* 13 */	"rc: reg",
		/* 14 */	"con: CNSTI",
		/* 15 */	"con: I0I",
	};

	// Mappings from a LHS nonterminal to the corresponding rule
	static short burm_decode_stmt[] = {
		0,
		4,
		5,
	};

	static short burm_decode_disp[] = {
		0,
		10,
		11,
	};

	static short burm_decode_reg[] = {
		0,
		6,
		7,
		8,
		9,
	};

	static short burm_decode_rc[] = {
		0,
		12,
		13,
	};

	static short burm_decode_con[] = {
		0,
		14,
		15,
	};

	void doClosureDisp(State p, int c) {
		if (c + 1 < p.cost[burm_reg_NT]) {
			p.cost[burm_reg_NT] = c + 1;
			p.burm_reg = 4;
			doClosureReg(p, c + 1);
		}
	}

	void doClosureReg(State p, int c) {
		if (c + 0 < p.cost[burm_rc_NT]) {
			p.cost[burm_rc_NT] = c + 0;
			p.burm_rc = 2;
		}
		if (c + 0 < p.cost[burm_stmt_NT]) {
			p.cost[burm_stmt_NT] = c + 0;
			p.burm_stmt = 2;
		}
	}

	void doClosureCon(State p, int c) {
		if (c + 0 < p.cost[burm_rc_NT]) {
			p.cost[burm_rc_NT] = c + 0;
			p.burm_rc = 1;
		}
	}

	int getRule(State state, int goalnt) {
		if (goalnt < 1 || goalnt > 5) {
			throw new AssertionError("Bad goal nonterminal in getRule " + goalnt);
		}
		if (state == null) {
			return 0;
		}
		switch (goalnt) {
			case burm_stmt_NT:	return burm_decode_stmt[state.burm_stmt];
			case burm_disp_NT:	return burm_decode_disp[state.burm_disp];
			case burm_reg_NT:	return burm_decode_reg[state.burm_reg];
			case burm_rc_NT:	return burm_decode_rc[state.burm_rc];
			case burm_con_NT:	return burm_decode_con[state.burm_con];
			default:
				throw new AssertionError("Bad goal nonterminal in getRule" + goalnt);
		}
	}

	void doLabel(Tree p) {
		if (p == null) {
			throw new AssertionError("NULL tree in burm_label");
		}
		switch (getArity(p.getOp())) {
		case 0:
			p.setState(getNextState(p.getOp()));
			break;
		case 1:
			doLabel(p.getLeft());
			p.setState(getNextState(p.getOp(),
				p.getLeft().getState()));
			break;
		case 2:
			doLabel(p.getLeft());
			doLabel(p.getRight());
			p.setState(getNextState(p.getOp(),
				p.getLeft().getState(),
				p.getRight().getState()));
			break;
		}
	}

	State label(Tree p) {
		doLabel(p);
		return p.getState().burm_stmt != 0 ? p.getState() : null;
	}

	State getNextState(int op) {
		return getNextState(op, null);
	}

	State getNextState(int op, State left) {
		return getNextState(op, left, null);
	}

	State getNextState(int op, State left, State right) {
		int c;
		State p = null;
		State l = left;
		State r = right;

		if (getArity(op) > 0) {
			p = new State();
			p.op = op;
			p.left = l;
			p.right = r;
			p.burm_stmt = 0;
			p.cost[1] =
			p.cost[2] =
			p.cost[3] =
			p.cost[4] =
			p.cost[5] =
				32767;
		}
		switch (op) {
		case 21: /* CNSTI */
			return new State(21, null, null,
					new int[] {
				0,
				32767,
				32767,
				32767,
				0,	/* rc: con */
				0},	/* con: CNSTI */

				0,
				0,
				0,
				1,	/* rc: con */
				1);
		case 53: /* ASGNI */
			{	/* stmt: ASGNI(disp,reg) */
				c = l.cost[burm_disp_NT] + r.cost[burm_reg_NT] + 1;
				if (c + 0 < p.cost[burm_stmt_NT]) {
					p.cost[burm_stmt_NT] = c + 0;
					p.burm_stmt = 1;
				}
			}
			break;
		case 67: /* INDIRC */
			break;
		case 85: /* CVCI */
			if (	/* reg: CVCI(INDIRC(disp)) */
				l.op == 67 /* INDIRC */
			) {
				c = l.left.cost[burm_disp_NT] + 1;
				if (c + 0 < p.cost[burm_reg_NT]) {
					p.cost[burm_reg_NT] = c + 0;
					p.burm_reg = 2;
					doClosureReg(p, c + 0);
				}
			}
			break;
		case 295: /* ADDRLP */
			return new State(295, null, null,
					new int[] {
				0,
				1,	/* stmt: reg */
				0,	/* disp: ADDRLP */
				1,	/* reg: disp */
				1,	/* rc: reg */
				32767},
				
				2,	/* stmt: reg */
				2,	/* disp: ADDRLP */
				4,	/* reg: disp */
				2,	/* rc: reg */
				0);
		case 309: /* ADDI */
			{	/* disp: ADDI(reg,con) */
				c = l.cost[burm_reg_NT] + r.cost[burm_con_NT] + 0;
				if (c + 0 < p.cost[burm_disp_NT]) {
					p.cost[burm_disp_NT] = c + 0;
					p.burm_disp = 1;
					doClosureDisp(p, c + 0);
				}
			}
			{	/* reg: ADDI(reg,rc) */
				c = l.cost[burm_reg_NT] + r.cost[burm_rc_NT] + 1;
				if (c + 0 < p.cost[burm_reg_NT]) {
					p.cost[burm_reg_NT] = c + 0;
					p.burm_reg = 1;
					doClosureReg(p, c + 0);
				}
			}
			break;
		case 661: /* I0I */
			return new State(661, null, null,
					new int[] {
				0,
				0,	/* stmt: reg */
				32767,
				0,	/* reg: I0I */
				0,	/* rc: con */
				0},	/* con: I0I */

				2,	/* stmt: reg */
				0,
				3,	/* reg: I0I */
				1,	/* rc: con */
				2);
		default:
			throw new AssertionError("Bad operator in State " + op);
		}
		return p;
	}

	private Tree[] burm_kids(Tree p, int eruleno, Tree kids[]) {
		switch (eruleno) {
		case 10: /* disp: ADDI(reg,con) */
		case 6: /* reg: ADDI(reg,rc) */
		case 4: /* stmt: ASGNI(disp,reg) */
			kids[0] = p.getLeft();
			kids[1] = p.getRight();
			break;
		case 13: /* rc: reg */
		case 12: /* rc: con */
		case 9: /* reg: disp */
		case 5: /* stmt: reg */
			kids[0] = p;
			break;
		case 7: /* reg: CVCI(INDIRC(disp)) */
			kids[0] = p.getLeft().getLeft();
			break;
		case 15: /* con: I0I */
		case 14: /* con: CNSTI */
		case 11: /* disp: ADDRLP */
		case 8: /* reg: I0I */
			break;
		default:
			throw new AssertionError("Bad external rule number in burm_kids " + eruleno);
		}
		return kids;
	}

	static int trace;

	/* burm_trace - print trace message for matching p; decrement trace */
	void burm_trace(Tree p, int eruleno, int cost, int bestcost) {
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
	void dumpCover(Tree p, int goalnt, int indent) {
		int eruleno = getRule(p.getState(), goalnt);
		short[] nts = burm_nts[eruleno];
		Tree[] kids = new Tree[10];
		int i;

		for (i = 0; i < indent; i++) {
			System.out.print(" ");
		}
		System.out.println(burm_string[eruleno]);
		burm_kids(p, eruleno, kids);
		for (i = 0; nts[i] != 0; i++) {
			dumpCover(kids[i], nts[i], indent + 1);
		}
	}

	void gen(Tree p) {
		if (label(p) == null) {
			System.out.println("no cover");
		} else {
			dumpCover(p, 1, 0);
		}
	}

	public static void main(String[] args) {
		System.out.println("i = c + 4;");
		Tree c4 = new Tree(CNSTI, 4);
		Tree cv = new Tree(CVCI, new Tree(INDIRC, new Tree(ADDRLP)));
		Tree t = new Tree(ASGNI,
			new Tree(ADDRLP),
			new Tree(ADDI, cv, c4));
		Main m = new Main();
		m.gen(t);
	}
}
