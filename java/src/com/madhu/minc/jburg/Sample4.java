
package com.madhu.minc.jburg;

import com.madhu.minc.jburg.State;
import com.madhu.minc.jburg.STree;
import com.madhu.minc.jburg.PatternMatcher;

public class Sample4 extends PatternMatcher {
	private static final int burm_stmt_NT = 1;
	private static final int burm_disp_NT = 2;
	private static final int burm_reg_NT = 3;
	private static final int burm_rc_NT = 4;
	private static final int burm_con_NT = 5;

	private static short burm_nts_0[] = { burm_disp_NT, burm_reg_NT, 0 };
	private static short burm_nts_1[] = { burm_reg_NT, 0 };
	private static short burm_nts_2[] = { burm_reg_NT, burm_rc_NT, 0 };
	private static short burm_nts_3[] = { burm_disp_NT, 0 };
	private static short burm_nts_4[] = { 0 };
	private static short burm_nts_5[] = { burm_reg_NT, burm_con_NT, 0 };
	private static short burm_nts_6[] = { burm_con_NT, 0 };

	private short burm_nts[][] = {
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

	public short[] getNonTerminals(int eruleno) {
		return burm_nts[eruleno];
	}

	private static final int CNSTI = 21;
	private static final int ASGNI = 53;
	private static final int INDIRC = 67;
	private static final int CVCI = 85;
	private static final int ADDRLP = 295;
	private static final int ADDI = 309;
	private static final int I0I = 661;

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

	private static String[] burm_string = {
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

	public String getRuleString(int eruleno) {
		return burm_string[eruleno];
	}

	private static short burm_decode_stmt[] = {
		0,
		4,
		5,
	};

	private static short burm_decode_disp[] = {
		0,
		10,
		11,
	};

	private static short burm_decode_reg[] = {
		0,
		6,
		7,
		8,
		9,
	};

	private static short burm_decode_rc[] = {
		0,
		12,
		13,
	};

	private static short burm_decode_con[] = {
		0,
		14,
		15,
	};

	public int getRule(State state, int goalnt) {
		if (state == null) {
			return 0;
		}
		switch (goalnt) {
			case burm_stmt_NT: return burm_decode_stmt[state.getRule(goalnt)];
			case burm_disp_NT: return burm_decode_disp[state.getRule(goalnt)];
			case burm_reg_NT: return burm_decode_reg[state.getRule(goalnt)];
			case burm_rc_NT: return burm_decode_rc[state.getRule(goalnt)];
			case burm_con_NT: return burm_decode_con[state.getRule(goalnt)];
		}
		throw new AssertionError("Bad goal nonterminal in getRule(): " + goalnt);
	}

	private void doClosure_disp(State p, int c) {
		if (c + 1 < p.getCost(burm_reg_NT)) {
			p.setCostRule(burm_reg_NT, c + 1, 4);
			doClosure_reg(p, c + 1);
		}
	}

	private void doClosure_reg(State p, int c) {
		if (c + 0 < p.getCost(burm_rc_NT)) {
			p.setCostRule(burm_rc_NT, c + 0, 2);
		}
		if (c + 0 < p.getCost(burm_stmt_NT)) {
			p.setCostRule(burm_stmt_NT, c + 0, 2);
		}
	}

	private void doClosure_con(State p, int c) {
		if (c + 0 < p.getCost(burm_rc_NT)) {
			p.setCostRule(burm_rc_NT, c + 0, 1);
		}
	}

	public State getNextState(int op, State left, State right) {
		int c;
		State p = null;
		State l = left;
		State r = right;

		p = new State(op, left, right);
		p.setRule(burm_stmt_NT, 0);

		switch (op) {
		case 21: /* CNSTI */
		{	/* con: CNSTI */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 1);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 53: /* ASGNI */
		{	/* stmt: ASGNI(disp,reg) */
			c = l.getCost(burm_disp_NT) + r.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 1);
			}
		}
		break;

		case 67: /* INDIRC */
		break;

		case 85: /* CVCI */
		if (	/* reg: CVCI(INDIRC(disp)) */
			l.getOperator() == 67 /* INDIRC */
		) {
			c = l.getLeft().getCost(burm_disp_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 2);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 295: /* ADDRLP */
		{	/* disp: ADDRLP */
			c = 0;
			if (c + 0 < p.getCost(burm_disp_NT)) {
				p.setCostRule(burm_disp_NT, c + 0, 2);
				doClosure_disp(p, c + 0);
			}
		}
		break;

		case 309: /* ADDI */
		{	/* disp: ADDI(reg,con) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con_NT) + 0;
			if (c + 0 < p.getCost(burm_disp_NT)) {
				p.setCostRule(burm_disp_NT, c + 0, 1);
				doClosure_disp(p, c + 0);
			}
		}
		{	/* reg: ADDI(reg,rc) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_rc_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 1);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 661: /* I0I */
		{	/* con: I0I */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 2);
				doClosure_con(p, c + 0);
			}
		}
		{	/* reg: I0I */
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 3);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		default:
			throw new AssertionError();
		}
		return p;
	}

	public void burm_kids(STree p, int eruleno, STree kids[]) {
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
			throw new AssertionError("Bad external rule number");
		}
	}
}
