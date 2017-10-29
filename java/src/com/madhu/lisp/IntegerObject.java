
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
public class IntegerObject extends Primitive {
	private int value;

	public IntegerObject(int i) {
		value = i;
	}
	
	public IntegerObject(String s) {
		this(Integer.parseInt(s));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return Integer.toString(value);
	}

	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#emitCode(java.io.PrintWriter)
	 */
	public void emitCode(PrintWriter out) {
		out.print("iload\t");
		out.println(value);
	}
	/**
	 * @return
	 */
	public int getValue() {
		return value;
	}

}
