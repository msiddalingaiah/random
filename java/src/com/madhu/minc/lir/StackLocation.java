
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
public class StackLocation extends Location {
	private static int index = 0;
	private int offset;

	/**
	 * 
	 */
	public StackLocation(int offset) {
		this.offset = offset;
	}
	
	public String toString() {
		return "s" + offset;
	}

	/**
	 * @return
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param i
	 */
	public void setOffset(int off) {
		offset = off;
	}
}
