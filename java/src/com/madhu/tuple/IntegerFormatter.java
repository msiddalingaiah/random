
package com.madhu.tuple;

public class IntegerFormatter extends Formatter {
	@Override
	public Comparable parse(String text) {
		return new Integer(text);
	}

	@Override
	public String format(Object value) {
		return value.toString();
	}
}
