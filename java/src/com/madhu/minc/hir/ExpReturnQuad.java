
/*
 * Created on Dec 8, 2004
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
public class ExpReturnQuad extends Quad {
	private Operand[] operands;

	/**
	 * 
	 */
	public ExpReturnQuad(Operand op) {
		operands = new Operand[] { op };
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getRefs()
	 */
	public Operand[] getRefs() {
		return operands;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getDef()
	 */
	public Variable getDef() {
		return null;
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
		Variable v = (Variable) variableMap.get(operands[0]);
		if (v != null) {
			operands[0] = v;
		}
	}
	
	public String toString() {
		return getAddress() + ": return " + operands[0];
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#generateCode(com.madhu.minc.lir.CodeGenerator)
	 */
	public void generateCode(CodeGenerator cg) {
		cg.generateCode(this);
	}
}
