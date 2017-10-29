
/*
 * Created on Nov 10, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

/**
 * @author Madhu Siddalingaiah
 *
 * This class represents an expression node.
 */
public abstract class Operand {
	public abstract Operand simplify();
}
