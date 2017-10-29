
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
public class Temporary extends Variable {
	private static int index = 1;

	public Temporary() {
		super("$" + index);
		index += 1;
	}
}
