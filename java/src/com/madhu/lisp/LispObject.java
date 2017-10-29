
/*
 * Created on Apr 5, 2005
 *
 * Copyright (c) 2005 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.lisp;

import java.io.PrintWriter;

/**
 * @author Madhu Siddalingaiah
 *
 */
public abstract class LispObject {
	/**
	 * 
	 */
	public LispObject() {
	}

	public abstract LispObject eval();
	public abstract String toString();
	public abstract void emitCode(PrintWriter out);
}
