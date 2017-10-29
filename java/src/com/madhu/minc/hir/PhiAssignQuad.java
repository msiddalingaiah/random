
/*
 * Created on Nov 20, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

import java.util.HashMap;

import com.madhu.minc.lir.CodeGenerator;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class PhiAssignQuad extends AssignQuad {
	private PhiOperand phiOperand;

	/**
	 * @param lhs
	 * @param rhs
	 */
	public PhiAssignQuad(Variable var) {
		super(var);
		phiOperand = new PhiOperand();
	}

	public PhiOperand getPhiOperand() {
		return phiOperand;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getRefs()
	 */
	public Operand[] getRefs() {
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj instanceof PhiAssignQuad) {
			PhiAssignQuad paq = (PhiAssignQuad) obj;
			return getLHS().equals(paq.getLHS());
		}
		return false;
	}

	public String toString() {
		return getLHS() + " = " + phiOperand;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.AssignQuad#propagateRHS(com.madhu.minc.hir.Variable)
	 */
	public Operand propagateRHS(Variable variable) {
		return variable;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#simplifyOperands()
	 */
	public void simplifyOperands() {
		phiOperand.simplify();
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#mapVariables(java.util.HashMap)
	 */
	public void mapVariables(HashMap variableMap) {
		// no need to do anything here...
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#generateCode(com.madhu.minc.lir.CodeGenerator)
	 */
	public void generateCode(CodeGenerator cg) {
		cg.generateCode(this);
	}
}
