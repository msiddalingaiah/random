
package com.madhu.minc.s3;

import java.util.ArrayList;

public class Tree {
	private Symbol symbol;
	private ArrayList childNodes;
	
	public Tree(Symbol symbol) {
		this.symbol = symbol;
		childNodes = new ArrayList();
	}
	
	public Tree(Symbol symbol, Tree left, Tree right) {
		this(symbol);
		addChild(left);
		addChild(right);
	}
	
	public Tree() {
		this(null);
	}
	
	public void addChild(Tree t) {
		childNodes.add(t);
	}
	
	public Tree getChild(int i) {
		return (Tree) childNodes.get(i);
	}
	
	public boolean isLeaf() {
		return getChildCount() == 0;
	}

	public int getChildCount() {
		return childNodes.size();
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol data) {
		this.symbol = data;
	}
	
	public String toString() {
		return symbol.toString();
	}
}
