
package com.madhu.minc.jburg;

/*
 * Created on Jul 18, 2007 at 10:15:20 PM
 */
public class STree {
	private int op;
	private STree[] kids;
	private int val;
	private State state;
	
	public STree() {
		kids = new STree[2];
	}
	
	public STree(int op, STree left, STree right) {
		this();
		this.op = op;
		kids[0] = left;
		kids[1] = right;
	}

	public STree(int op) {
		this(op, null, null);
	}

	public STree(int op, STree left) {
		this();
		this.op = op;
		kids[0] = left;
	}

	public STree(int op, int val) {
		this(op);
		this.val = val;
	}

	public STree getLeft() {
		return kids[0];
	}
	
	public STree getRight() {
		return kids[1];
	}
	
	public int getOp() {
		return op;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
}
