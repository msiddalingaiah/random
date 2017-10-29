
package com.madhu.minc.sgc;

/*
 * Created on Apr 24, 2008 at 9:50:18 PM
 */
public class Register {
	private int number;
	private Variable variable;
	
	public Register(int number) {
		this.number = number;
	}

	public boolean isAvailable() {
		return variable == null;
	}

	public Variable getVariable() {
		return variable;
	}

	public void allocate(Variable variable) {
		this.variable = variable;
		variable.setRegister(this);
	}

	public void release() {
		variable.setRegister(null);
		variable = null;
	}

	public void releaseTemp() {
		if (variable.isTemporary()) {
			release();
		}
	}

	public String toString() {
		return String.format("$%d", number);
	}
}
