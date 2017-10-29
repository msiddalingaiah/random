
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jul 19, 2006
 */

package com.madhu.minc.s3;

public class Terminal extends Symbol {
	private Object value;

	public Terminal(String name, Object value) {
		super(name);
		this.value = value;
	}

	public Terminal(String name) {
		this(name, null);
	}

	public Object getValue() {
		return value;
	}
}
