
/*
 * Created on Apr 7, 2005
 *
 * Copyright (c) 2005 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.lisp;

/**
 * @author Madhu Siddalingaiah
 *
 */
public abstract class Primitive extends LispObject {
	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#eval()
	 */
	public LispObject eval() {
		return this;
	}
}
