
/*
 * Created on May 18, 2005 5:37:36 PM
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
public class Production {
	private NonTerminal lhs;
	private GrammarSymbol[] rhs;
	private int number;
	/*
	 * FIXME This is a real hack!
	 * This is the value in the resulting AST
	 * Right now it can be a String or Integer depending on the LHS of the rewrite rule
	 * There's a hideous instanceof in Parser::doAction to deal with it
	 */
	private Object astValue;
	private int[] rewriteRule;

	public Production(int number) {
		this.number = number;
	}
	
	public Production(int number, NonTerminal lhs, GrammarSymbol[] rhs, Object astValue, int[] rewriteRule) {
		this(number);
		this.lhs = lhs;
		this.rhs = rhs;
		this.astValue = astValue;
		this.rewriteRule = rewriteRule;
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
		if (!(obj instanceof Production)) {
			return false;
		}
		return number == obj.hashCode();
	}

	/**
	 * @return
	 */
	public NonTerminal getLHS() {
		return lhs;
	}

	/**
	 * @return
	 */
	public GrammarSymbol[] getRHS() {
		return rhs;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(lhs + " -> ");
		for (int i = 0; i < rhs.length; i++) {
			sb.append(rhs[i]);
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * @return
	 */
	public Object getASTValue() {
		return astValue;
	}

	/**
	 * @return
	 */
	public int[] getRewriteRule() {
		return rewriteRule;
	}
}
