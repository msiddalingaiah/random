
/*
 * Created on Nov 20, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.sgc;

import java.util.Stack;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class SSAStack {
	private Stack<String> stack;
	private int count;
	private String name;

	/**
	 * 
	 */
	public SSAStack(String name) {
		this.name = name;
		count = 0;
		stack = new Stack<String>();
	}

//	public void addMethodArgument(MethodArgument arg) {
//		stack.push(arg);
//	}

	public String peek() {
		if (stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}
	
	public String getNewVariable() {
		count += 1;
		String var = String.format("%s~%d", name, count);
		stack.push(var);
		return var;
	}
	
	public String pop() {
		return stack.pop();
	}
}
