
/*
 * Created on Nov 23, 2004 7:33:26 PM
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
public class VarAssignQuad extends AssignQuad {
	private Operand[] operands;
	
	public VarAssignQuad(Variable lhs, Operand rhs) {
		super(lhs);
		operands = new Operand[] { rhs };
	}

	/**
	 * @return
	 */
	public Operand getRHS() {
		return operands[0];
	}

	/**
	 * @param operand
	 */
	public void setRHS(Operand operand) {
		operands[0] = operand;
	}

	public String toString() {
		return getAddress() + ": " + getLHS() + " = " + operands[0];
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getRefs()
	 */
	public Operand[] getRefs() {
		return operands;
	}

	/**
	 * 
	 */
	public void simplifyOperands() {
		operands[0] = operands[0].simplify();
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.AssignQuad#propagateRHS(com.madhu.minc.hir.Variable)
	 */
	public Operand propagateRHS(Variable variable) {
		if (operands[0] instanceof Variable || operands[0] instanceof Constant) {
			setDeadCode(true);
		}
		return operands[0].simplify();
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#mapVariables(java.util.HashMap)
	 */
	public void mapVariables(HashMap variableMap) {
		mapLHS(variableMap);
		Variable v;
		v = (Variable) variableMap.get(operands[0]);
		if (v != null) {
			operands[0] = v;
		}
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#generateCode(com.madhu.minc.lir.CodeGenerator)
	 */
	public void generateCode(CodeGenerator cg) {
		cg.generateCode(this);
	}
}
