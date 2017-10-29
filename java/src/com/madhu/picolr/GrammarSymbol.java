
/*
 * Created on May 18, 2005 5:31:09 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class GrammarSymbol {
	private String name;
	private int number;

	/**
	 * @param incrementCount
	 */
	public GrammarSymbol(int number) {
		this.number = number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof GrammarSymbol)) {
			return false;
		}
		return number == obj.hashCode();
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}
}
