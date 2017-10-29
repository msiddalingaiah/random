
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
public abstract class Function extends LispObject {
	private String name;

	public Function(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Function " + name;
	}

	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#emitCode(java.io.PrintWriter)
	 */
	public void emitCode(PrintWriter out) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param object
	 * @return
	 */
	public abstract LispObject apply(ListObject args);
	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#eval()
	 */
	public LispObject eval() {
		throw new AssertionError("Can't eval " + toString());
	}
}
