
/*
 * Created on Dec 7, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.lir;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class Register extends Location {
	private String name;

	/**
	 * 
	 */
	public Register(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	public String toString() {
		return name;
	}
}
