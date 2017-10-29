
/*
 * Created on Nov 11, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

import java.util.HashMap;

/**
 * @author Madhu Siddalingaiah
 *
 * Superclass of all assignments. For variable assignment, see
 * VarAssignQuad.
 */
public abstract class AssignQuad extends Quad {
	private Variable lhs;
	
	public AssignQuad(Variable lhs) {
		setLHS(lhs);
	}

	/**
	 * @param variable
	 * @return
	 */
	public abstract Operand propagateRHS(Variable variable);

	/**
	 * @return
	 */
	public Variable getLHS() {
		return lhs;
	}

	/**
	 * @param operand
	 */
	public void setLHS(Variable var) {
		lhs = var;
		var.setAssignQuad(this);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getDef()
	 */
	public Variable getDef() {
		return lhs;
	}

	public void mapLHS(HashMap variableMap) {
		Variable v = (Variable) variableMap.get(lhs);
		if (v != null) {
			lhs = v;
		}
	}
}
