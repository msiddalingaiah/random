
/*
 * Created on May 18, 2005 5:32:09 PM
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
public class NonTerminal extends GrammarSymbol {
	private Production[] productions;

	public NonTerminal(int number) {
		super(number);
	}

	public NonTerminal(int number, String name) {
		this(number);
		setName(name);
	}

	public NonTerminal(NonTerminal nt) {
		super(nt.hashCode());
		setName(nt.getName());
	}

	/**
	 * @return
	 */
	public Production[] getProductions() {
		return productions;
	}

	/**
	 * @param productions
	 */
	public void setProductions(Production[] productions) {
		this.productions = productions;
	}
	
	public String toString() {
		return getName();
	}
}
