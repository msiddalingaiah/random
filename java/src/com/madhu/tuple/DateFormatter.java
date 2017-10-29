
package com.madhu.tuple;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter extends Formatter {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

	@Override
	public Comparable parse(String text) throws ParseException {
		return new Date(sdf.parse(text).getTime());
	}

	@Override
	public String format(Object value) {
		return sdf.format(value);
	}
}
