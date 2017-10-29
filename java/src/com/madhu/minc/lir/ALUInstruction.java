
/*
 * Created on Apr 23, 2005 12:09:15 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.lir;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class ALUInstruction extends Instruction {
	public ALUInstruction() {
	}

	/**
	 * @return
	 */
	public int getDestination() {
		return operands[0];
	}

	/**
	 * @return
	 */
	public int getSource1() {
		return operands[1];
	}

	/**
	 * @return
	 */
	public int getSource2() {
		return operands[2];
	}

	/**
	 * @param i
	 */
	public void setDestination(int i) {
		operands[0] = i;
	}

	/**
	 * @param i
	 */
	public void setSource1(int i) {
		operands[1] = i;
	}

	/**
	 * @param i
	 */
	public void setSource2(int i) {
		operands[2] = i;
	}
}
