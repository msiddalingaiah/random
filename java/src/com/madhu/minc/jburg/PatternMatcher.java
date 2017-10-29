
package com.madhu.minc.jburg;

/*
 * Created on Jul 18, 2007 at 10:20:52 PM
 */
public abstract class PatternMatcher {
	public static final int GOAL_NT = 1;

	public State getNextState(int op) {
		return getNextState(op, null);
	}

	public State getNextState(int op, State left) {
		return getNextState(op, left, null);
	}

	public abstract State getNextState(int op, State left, State right);
	public abstract int getArity(int op);
	public abstract int getRule(State state, int goalnt);
	public abstract short[] getNonTerminals(int eruleno);
	public abstract void burm_kids(STree p, int eruleno, STree[] kids);
	public abstract String getRuleString(int eruleno);
}
