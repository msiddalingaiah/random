
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
public class ListObject extends LispObject {
	private LispObject value;
	private ListObject next;

	public ListObject() {
	}

	/**
	 * @param reader
	 */
	public ListObject(LispObject value) {
		this.value = value;
	}

	public ListObject cons(ListObject next) {
		this.next = next;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#eval()
	 */
	public LispObject eval() {
		Function function = (Function) car();
		return function.apply(cdr());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (value == null) {
			return "nil";
		}
		StringBuffer sb = new StringBuffer(128);
		sb.append('(');
		ListObject list = this;
		while (list != null) {
			sb.append(list.car().toString());
			sb.append(' ');
			list = list.cdr();
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(')');
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.madhu.lisp.LispObject#emitCode(java.io.PrintWriter)
	 */
	public void emitCode(PrintWriter out) {
		// TODO Auto-generated method stub

	}
	
	public LispObject car() {
		return value;
	}
	
	public ListObject cdr() {
		return next;
	}
}
