
package com.madhu.minc.jburg;

/*
 * Created on Jul 19, 2007 at 7:00:55 PM
 */
public class Symbol {
	String name;		/* terminal name */
	int arity;		/* operator arity */
	Symbol next;		/* next terminal in esn order */
	Rule rules;		/* rules whose pattern starts with term */
	int number;		/* identifying number */
	int lhsCount;		/* # times nt appears in a rule lhs */
	boolean reached;		/* 1 iff reached from start non-terminal */
	Rule chain;		/* chain rules w/non-terminal on rhs */
	
	public Symbol(String name) {
		this(name, 0);
	}
	
	public Symbol(String name, int number) {
		this.name = name;
		this.number = number;
	}

	public boolean isTerminal() {
		return Character.isUpperCase(name.charAt(0));
	}

	public String toString() {
		return name;
	}
}
