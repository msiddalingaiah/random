
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 25, 2006
 */

package com.madhu.minc.s2;

import java.util.ArrayList;

public class Tree extends Node {
	private Object operator;
	private ArrayList childNodes;

	public Tree() {
		childNodes = new ArrayList();		
	}

	public Tree(Object operator) {
		this();
		this.operator = operator;
	}

	public void addChild(Node child) {
		childNodes.add(child);
	}

	public Object getOperator() {
		return operator;
	}

	public int getChildCount() {
		return childNodes.size();
	}

	public Node getChildNode(int i) {
		return (Node) childNodes.get(i);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return operator.toString();
	}
}
