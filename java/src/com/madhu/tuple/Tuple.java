
package com.madhu.tuple;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Tuple {
	private Comparable[] data;
	private Schema schema;

	public Tuple(Schema schema, String[] values) throws ParseException {
		this.schema = schema;
		ArrayList<Field> fields = schema.getFields();
		data = new Comparable<?>[fields.size()];
		int n = fields.size() < values.length ? fields.size() : values.length;
		for (int i = 0; i < n; i++) {
			String text = values[i];
			if (text != null && text.trim().length() > 0) {
				data[i] = fields.get(i).parse(values[i]);
			} else {
				System.out.printf("line: %d, text: %s%n", i, text);
			}
		}
	}
	
	public Tuple(Schema schema, Comparable[] values) {
		this.schema = schema;
		this.data = values;
	}

	public Tuple(Tuple row, int... columns) {
		int n = columns.length;
		schema = new Schema();
		data = new Comparable<?>[n];
		for (int i = 0; i < data.length; i++) {
			schema.addField(row.getSchema().getFields().get(i));
			data[i] = row.getValue(i);
		}
	}

	public String toString() {
		ArrayList<Field> fields = schema.getFields();
		StringBuilder sb = new StringBuilder(128);
		for (int i = 0; i < fields.size(); i++) {
			if (data[i] != null) {
				sb.append(fields.get(i).format(data[i]));
			} else {
				sb.append("(null)");
			}
			sb.append('\t');
		}
		sb.substring(0, sb.length()-1);
		return sb.toString();
	}
	
	public Comparable getValue(int column) {
		return data[column];
	}
	
	public Schema getSchema() {
		return schema;
	}

	public int hashCode() {
		int hash = 0;
		for (Object value : data) {
			if (value != null) {
				hash += value.hashCode();
			}
		}
		return hash;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Tuple) {
			Tuple key = (Tuple) other;
			return Arrays.equals(data, key.data);
		}
		return false;
	}
}
