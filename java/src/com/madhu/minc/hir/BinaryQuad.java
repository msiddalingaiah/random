
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
public class BinaryQuad extends AssignQuad {
	private int operator;
	private Operand[] operands;
	
	public BinaryQuad(Operand left, int op, Operand right) {
		super(new Temporary());
		operator = op;
		operands = new Operand[] { left, right };
	}

	/**
	 * @return
	 */
	public Operand getLeftOperand() {
		return operands[0];
	}

	/**
	 * @return
	 */
	public int getOperator() {
		return operator;
	}

	/**
	 * @return
	 */
	public Operand getRightOperand() {
		return operands[1];
	}

	/**
	 * @param operand
	 */
	public void setLeftOperand(Operand operand) {
		operands[0] = operand;
	}

	/**
	 * @param operand
	 */
	public void setRightOperand(Operand operand) {
		operands[1] = operand;
	}

	public String toString() {
		return getAddress() + ": " + getLHS() + " = " + operands[0] + " " +
			Token.toString(operator) + " " + operands[1];
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getRefs()
	 */
	public Operand[] getRefs() {
		return operands;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#simplifyOperands()
	 */
	public void simplifyOperands() {
		operands[0] = operands[0].simplify();
		operands[1] = operands[1].simplify();
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.AssignQuad#propagateRHS(com.madhu.minc.hir.Variable)
	 */
	public Operand propagateRHS(Variable variable) {
		Operand op = variable;
		if (operands[0] instanceof Constant &&
			operands[1] instanceof Constant) {

			op = foldIntConstants();
			setDeadCode(true);
		}
		return op;
	}

	/**
	 * @return
	 */
	private Operand foldIntConstants() {
		int op0 = ((Constant) operands[0]).getValue();
		int op1 = ((Constant) operands[1]).getValue();
		
		int value;
		switch (operator) {
			case Token.MINUS: value = op0 - op1; break;
			case Token.MOD: value = op0 % op1; break;
			case Token.NOT_EQUAL: value = op0 != op1 ? 1 : 0; break;
			case Token.PLUS: value = op0 + op1; break;
			case Token.SLASH: value = op0 / op1; break;
			case Token.STAR: value = op0 * op1; break;

			case Token.AND: value = op0 & op1; break;
			case Token.OR: value = op0 | op1; break;
			case Token.XOR: value = op0 ^ op1; break;

			case Token.AND_AND: value = (op0 & op1) != 0 ? 1 : 0; break;
			case Token.OR_OR: value = (op0 | op1) != 0 ? 1 : 0; break;

			case Token.EQUAL_EQUAL: value = op0 == op1 ? 1 : 0; break;
			case Token.GREATER_THAN: value = op0 > op1 ? 1 : 0; break;
			case Token.GREATER_THAN_EQUAL: value = op0 >= op1 ? 1 : 0; break;
			case Token.LESS_THAN: value = op0 < op1 ? 1 : 0; break;
			case Token.LESS_THAN_EQUAL: value = op0 <= op1 ? 1 : 0; break;
			default:
			throw new AssertionError("can't fold " + Token.toString(operator));
		}
		return new Constant(value);
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
		v = (Variable) variableMap.get(operands[1]);
		if (v != null) {
			operands[1] = v;
		}
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#generateCode(com.madhu.minc.lir.CodeGenerator)
	 */
	public void generateCode(CodeGenerator cg) {
		cg.generateCode(this);
	}
}
