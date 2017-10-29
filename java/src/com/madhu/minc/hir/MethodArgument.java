
/*
 * Created on Dec 9, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

import com.madhu.minc.lir.StackLocation;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class MethodArgument extends Variable {
	/**
	 * 
	 */
	public MethodArgument(String name, int offset) {
		super(name, new StackLocation(offset));
	}

	public Operand simplify() {
		return this;
	}
}
