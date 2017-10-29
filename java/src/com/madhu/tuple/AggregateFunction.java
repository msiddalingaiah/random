
package com.madhu.tuple;

import java.util.ArrayList;

public abstract class AggregateFunction {
	private Field field;
	private int index;

	public AggregateFunction(int index, String name) throws Exception {
		this.index = index;
		field = new Field(name);
	}

	public abstract Comparable compute(ArrayList<Tuple> rows) throws Exception;

	public Field getField() {
		return field;
	}
	
	public int getIndex() {
		return index;
	}

	public Class<?> getType() {
		return field.getType();
	}

	public void setType(Class type) throws InstantiationException, IllegalAccessException {
		field.setType(type);
	}
}
