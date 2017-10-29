
package com.madhu.tuple;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;

public class Field {
	private static HashMap<Class<?>, Class<?>> typeMap;
	private String name;
	private Class type;
	private Formatter formatter;
	
	static {
		typeMap = new HashMap<Class<?>, Class<?>>();
		typeMap.put(Integer.class, IntegerFormatter.class);
		typeMap.put(Date.class, DateFormatter.class);
		typeMap.put(Double.class, DoubleFormatter.class);
		typeMap.put(String.class, StringFormatter.class);
	}

	public Field() {
	}

	public Field(String name, Class type) throws InstantiationException, IllegalAccessException {
		this.name = name;
		setType(type);
	}

	public Field(String name) throws InstantiationException, IllegalAccessException {
		this(name, null);
	}

	public Comparable parse(String text) throws ParseException {
		return formatter.parse(text);
	}

	public String format(Object value) {
		return formatter.format(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) throws InstantiationException, IllegalAccessException {
		this.type = type;
		if (type == null) {
			return;
		}
		Class<?> clazz = typeMap.get(type);
		if (clazz == null) {
			throw new IllegalArgumentException("No support for column type " + type);
		}
		formatter = (Formatter) clazz.newInstance();
	}
}
