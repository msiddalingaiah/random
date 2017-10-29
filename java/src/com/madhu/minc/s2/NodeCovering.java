
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 25, 2006
 */

package com.madhu.minc.s2;

public class NodeCovering extends Node implements Covering {
	private Node expression;

	public NodeCovering(Node expression) {
		this.expression = expression;
	}

	public boolean covers(Node node) {
		return false;
	}

	public String toString() {
		return '[' + expression.toString() + ']';
	}
}
