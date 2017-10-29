
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 25, 2006
 */

package com.madhu.minc.s2;

/**
 * () define RuleTree
 * [] define RuleLeaf
 * (+ [reg eax] [1])
 */
public class TreeCovering extends Tree implements Covering {
	public TreeCovering(String operator) {
		super(operator);
	}

	public boolean covers(Node node) {
		if (!(node instanceof Tree)) {
			return false;
		}
		Tree tree = (Tree) node;
		if (!getOperator().equals(tree.getOperator())) {
			return false;
		}
		if (getChildCount() != tree.getChildCount()) {
			return false;
		}
		int n = getChildCount();
		for (int i = 0; i < n; i++) {
			Covering child = (Covering) getChildNode(i);
			if (!child.covers(tree.getChildNode(i))) {
				return false;
			}
		}
		return true;
	}
}
