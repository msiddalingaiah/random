
/*
 * Created on Nov 11, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

import java.util.HashMap;

import com.madhu.minc.Token;
import com.madhu.minc.lir.CodeGenerator;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class UnaryQuad extends AssignQuad {
	private int operator;
	private Operand[] operands;

	public UnaryQuad(int op, Operand operand) {
		super(new Temporary());
		operator = op;
		operands = new Operand[] { operand };
	}

	/**
	 * @return
	 */
	public Operand getOperand() {
		return operands[0];
	}

	/**
	 * @return
	 */
	public int getOperator() {
		return operator;
	}

	/**
	 * @param operand
	 */
	public void setOperand(Operand operand) {
		operands[0] = operand;
	}

	public String toString() {
		return getAddress() + ": " + getLHS() + " = " +
			Token.toString(operator) + " " + operands[0];
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getRefs()
	 */
	public Operand[] getRefs() {
		return operands;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.AssignQuad#propagateRHS(com.madhu.minc.hir.Variable)
	 */
	public Operand propagateRHS(Variable variable) {
		Operand ret = variable;
		if (operands[0] instanceof Constant) {
			int op = ((Constant) operands[0]).getValue();
			int value;
			switch (operator) {
				case Token.BANG: value = op == 0 ? 1 : 0; break;
				case Token.MINUS: value = -op; break;
				case Token.PLUS: value = +op; break;
				case Token.TILDE: value = ~op; break;
				default:
					throw new IllegalArgumentException(
						"can't fold " + Token.toString(operator));
			}
			ret = new Constant(value);
			setDeadCode(true);
		}
		return ret;
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
