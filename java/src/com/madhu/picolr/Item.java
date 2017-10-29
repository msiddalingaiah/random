
/*
 * Created on May 18, 2005 5:36:07 PM
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
public class Item {
	private Production production;
	private int dot;
	private Item next;
	
	public Item(Production prod) {
		this(prod, 0);
	}
	
	public Item(Production prod, int dot) {
		this.production = prod;
		this.dot = dot;
	}
	
	/**
	 * @return
	 */
	public int getDot() {
		return dot;
	}

	public GrammarSymbol getDotSymbol() {
		GrammarSymbol[] symbols = production.getRHS();
		if (dot >= symbols.length) {
			return null;
		}
		return symbols[dot];
	}

	public void advance() {
		if (dot < production.getRHS().length+1) {
			dot += 1;
		}
	}

	/**
	 * @return
	 */
	public Production getProduction() {
		return production;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Item)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (dot << 20) +
			production.hashCode();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(production.getLHS() + " -> ");
		GrammarSymbol[] rhs = production.getRHS();
		for (int i = 0; i < rhs.length; i++) {
			if (i == dot) {
				sb.append(". ");
			}
			sb.append(rhs[i]);
			sb.append(" ");
		}
		if (dot >= rhs.length) {
			sb.append(".");
		}
		sb.append(" (");
		sb.append(hashCode());
		sb.append(")");
		return sb.toString();
	}

	/**
	 * @return
	 */
	public Item getNext() {
		return next;
	}

	/**
	 * @param item
	 */
	public void setNext(Item item) {
		next = item;
	}
}
