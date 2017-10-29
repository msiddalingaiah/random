
/*
 * Created on Nov 20, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

import java.util.Stack;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class SSAStack {
	private Stack stack;
	private int count;
	private String name;

	/**
	 * 
	 */
	public SSAStack(String name) {
		this.name = name;
		count = 0;
		stack = new Stack();
	}

	public void addMethodArgument(MethodArgument arg) {
		stack.push(arg);
	}

	public Variable peek() {
		if (stack.isEmpty()) {
			return null;
		}
		return (Variable) stack.peek();
	}
	
	public Variable getNewVariable() {
		count += 1;
		Variable var = new Variable(name, count);
		stack.push(var);
		return var;
	}
	
	public Variable pop() {
		return (Variable) stack.pop();
	}
}
