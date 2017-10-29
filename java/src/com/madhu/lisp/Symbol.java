
/*
 * Created on Apr 7, 2005
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
public class Symbol extends LispObject {
	private String name;
	private LispObject value;

	public Symbol(String name, LispObject value) {
		this.name = name;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#eval()
	 */
	public LispObject eval() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return value.toString();
	}

	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#emitCode(java.io.PrintWriter)
	 */
	public void emitCode(PrintWriter out) {
		// TODO Auto-generated method stub
	}
}
