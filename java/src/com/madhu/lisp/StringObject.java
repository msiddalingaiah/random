
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
public class StringObject extends Primitive {
	private String string;

	public StringObject(String s) {
		string = s;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return string;
	}

	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#emitCode(java.io.PrintWriter)
	 */
	public void emitCode(PrintWriter out) {
		out.print("aload\t'");
		out.print(string);
		out.println("'");
	}
	/**
	 * @return
	 */
	public String getString() {
		return string;
	}
}
