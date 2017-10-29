
/*
 * Created on Apr 23, 2005 12:12:45 PM
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
public class BranchInstruction extends Instruction {
	public BranchInstruction() {
	}

	/**
	 * @return
	 */
	public int getAddress() {
		return operands[0];
	}

	/**
	 * @param i
	 */
	public void setAddress(int i) {
		operands[0] = i;
	}
}
