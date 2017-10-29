
package com.madhu.tuple;

import java.util.ArrayList;

public class Schema {
	private String tableName;
	// can be null
	private Class javaClass;
	private ArrayList<Field> fields;

	public Schema() {
		fields = new ArrayList<Field>();
	}

	public ArrayList<Field> getFields() {
		return fields;
	}

	public void addField(Field field) {
		fields.add(field);
	}

	public boolean equalTypes(Schema schema) {
		ArrayList<Field> f1 = fields;
		ArrayList<Field> f2 = schema.getFields();
		if (f1.size() != f2.size()) {
			return false;
		}
		for (int i = 0; i < f1.size(); i += 1) {
			if (f1.get(i).getType() != f2.get(i).getType()) {
				return false;
			}
		}
		return true;
	}
}
