
/*
 * Created on Nov 30, 2004 8:56:04 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class PhiMoveQuad extends VarAssignQuad {
	/**
	 * @param lhs
	 * @param rhs
	 */
	public PhiMoveQuad(Variable lhs, Operand rhs) {
		super(lhs, rhs);
		simplifyOperands();
	}

	public String toString() {
		return getAddress() + ": " + getLHS() + " = " + getRHS() + " (phi move)";
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.AssignQuad#propagateRHS(com.madhu.minc.hir.Variable)
	 */
	public Operand propagateRHS(Variable variable) {
		return variable;
	}
}
