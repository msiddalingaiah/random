
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 21, 2006
 */

package com.madhu.minc.burg;

public class Tree {
	private int op;
	private Tree[] kids;
	private int val;
	private State state;
	
	public Tree() {
		kids = new Tree[2];
	}
	
	public Tree(int op, Tree left, Tree right) {
		this();
		this.op = op;
		kids[0] = left;
		kids[1] = right;
	}

	public Tree(int op) {
		this(op, null, null);
	}

	public Tree(int op, Tree left) {
		this();
		this.op = op;
		kids[0] = left;
	}

	public Tree(int op, int val) {
		this(op);
		this.val = val;
	}

	public Tree getLeft() {
		return kids[0];
	}
	
	public Tree getRight() {
		return kids[1];
	}
	
	public int getOp() {
		return op == Main.CNSTI && val == 0 ? Main.I0I : op;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
}
