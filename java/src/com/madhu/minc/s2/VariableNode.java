
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 25, 2006
 */

package com.madhu.minc.s2;

public class VariableNode extends Node {
	private Variable variable;

	public VariableNode(Variable variable) {
		this.variable = variable;
	}

	/**
	 * @return Returns the variable.
	 */
	public Variable getVariable() {
		return variable;
	}

	/**
	 * @param variable The variable to set.
	 */
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
}
