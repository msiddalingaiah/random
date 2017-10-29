
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 25, 2006
 */

package com.madhu.minc.s2;

public class ConstantNode extends Node {
	private Object value;

	public ConstantNode() {
	}

	public ConstantNode(Object value) {
		this.value = value;
	}

	/**
	 * @return Returns the value.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	public String toString() {
		return value.toString();
	}
}
