
/*
 * Created on Nov 9, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class SyntaxError extends IllegalArgumentException {
	private Token token;
	
	public SyntaxError(String message, Token t) {
		super(message + ", found " + t.toString());
		token = t;
	}
	
	public Token getToken() {
		return token;
	}
}
