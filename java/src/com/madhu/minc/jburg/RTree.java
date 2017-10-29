
package com.madhu.minc.jburg;

/*
 * Created on Jul 19, 2007 at 7:18:16 PM
 */
public class RTree {		/* tree patterns: */
	Symbol op;		/* a terminal or non-terminal */
	RTree left, right;	/* operands */
	int nTerminals;		/* number of terminal nodes in this tree */
	
	public String toString() {
		String result = op.toString();
		if (left != null) {
			result += "(" + left;
		}
		if (right != null) {
			result += "," + right;
		}
		if (left != null) {
			return result + ')';
		}
		return result;
	}
}
