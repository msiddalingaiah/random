
package com.madhu.tuple;

import java.text.ParseException;

public class DoubleFormatter extends Formatter {

	@Override
	public Comparable parse(String text) throws ParseException {
		return new Double(text);
	}

	@Override
	public String format(Object value) {
		return String.format("%6.2f", value);
	}
}
