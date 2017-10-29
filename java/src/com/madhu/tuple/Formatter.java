
package com.madhu.tuple;

import java.text.ParseException;

public abstract class Formatter {
	public abstract Comparable parse(String text) throws ParseException;
	public abstract String format(Object value);
}
