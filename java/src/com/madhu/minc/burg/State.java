
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 21, 2006
 */

package com.madhu.minc.burg;

public class State {
	int op;
	State left;
	State right;
	// cost indexed by nonterminal
	int[] cost;
	// mapped rule for each nonterminal
	char burm_stmt;
	char burm_disp;
	char burm_reg;
	char burm_rc;
	char burm_con;
	
	public State() {
		cost = new int[6];
	}

	public State(int op) {
		this();
		this.op = op;
	}

	public State(int op, State left, State right, int[] cost,
			int burm_stmt, int burm_disp, int burm_reg, int burm_rc, int burm_con) {
		this(op);
		this.left = left;
		this.right = right;
		this.cost = cost;
		this.burm_stmt = (char) burm_stmt;
		this.burm_disp = (char) burm_disp;
		this.burm_reg = (char) burm_reg;
		this.burm_rc = (char) burm_rc;
		this.burm_con = (char) burm_con;
	}
}
