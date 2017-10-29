
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 26, 2006
 */

package com.madhu.minc.s3;

public class StateTree extends Tree {
	private State state;

	public StateTree(Symbol symbol) {
		super(symbol);
		state = new State();
	}

	public StateTree(Symbol symbol, Tree left, Tree right) {
		super(symbol, left, right);
		state = new State();
	}

	public StateTree() {
		super();
		state = new State();
	}

	public State getState() {
		return state;
	}
	
	public String toString() {
		return getSymbol() + " " + state;
	}
}
