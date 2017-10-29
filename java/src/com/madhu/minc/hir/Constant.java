
/*
 * Created on Nov 11, 2004
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
public class Constant extends Operand {
	private int value;
	
	public Constant(int value) {
		this.value = value;
	}

	/**
	 * @return
	 */
	public int getValue() {
		return value;
	}

	public String toString() {
		return Integer.toString(value);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Operand#simplify()
	 */
	public Operand simplify() {
		return this;
	}
}
