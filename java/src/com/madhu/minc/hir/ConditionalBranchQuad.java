
/*
 * Created on Nov 14, 2004 3:13:48 PM
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
public class ConditionalBranchQuad extends BranchQuad {
	private Operand[] operands;
	private Block branchBlock;
	private boolean branchOnTrue;
	
	/**
	 * @param op
	 * @param ifTrueBlock
	 * @param afterIfBlock
	 */
	public ConditionalBranchQuad(Operand op, boolean branchOnTrue, Block branchBlock) {
		super(branchBlock);
		operands = new Operand[] { op };
		this.branchOnTrue = branchOnTrue;
	}

	/**
	 * @return
	 */
	public boolean isBranchOnTrue() {
		return branchOnTrue;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getRefs()
	 */
	public Operand[] getRefs() {
		return operands;
	}

	public String toString() {
		Block bb = getBranchBlock();
		if (branchOnTrue) {
			return getAddress() + ": " + "if " + operands[0] +
				" goto " + bb.getName() + "(" + bb.getAddress() + ")";
		} else {
			return getAddress() + ": " + "if not " + operands[0] +
				" goto " + bb.getName() + "(" + bb.getAddress() + ")";
		}
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#simplifyOperands()
	 */
	public void simplifyOperands() {
		operands[0] = operands[0].simplify();
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#mapVariables(java.util.HashMap)
	 */
	public void mapVariables(HashMap variableMap) {
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
