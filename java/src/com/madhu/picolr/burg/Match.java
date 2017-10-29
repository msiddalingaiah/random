
package com.madhu.picolr.burg;

/*
 * Created on Apr 22, 2008 at 6:50:07 PM
 */
public class Match {
	private AST ast;
	private Rule rule;
	private int cost;

	public Match(AST ast, Rule rule, int cost) {
		this.ast = ast;
		this.rule = rule;
		this.cost = cost;
	}

	public Rule getRule() {
		return rule;
	}

	public int getCost() {
		return cost;
	}

	public AST getAST() {
		return ast;
	}

	public String toString() {
		return String.format("%s - %s (%d)", ast.toString(), rule.toString(), cost);
	}
}
