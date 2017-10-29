
package com.madhu.tuple;

import java.text.ParseException;

/*
 * Created on Sep 22, 2008 at 4:00:34 PM
 */
public class StringFormatter extends Formatter {
	@Override
	public String format(Object value) {
		return value.toString();
	}

	@Override
	public Comparable parse(String text) throws ParseException {
		return text;
	}
}
