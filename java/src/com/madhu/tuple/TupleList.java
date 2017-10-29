
package com.madhu.tuple;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TupleList {
	private Schema schema;
	private ArrayList<Tuple> rows;
	private TupleComparator rowComparator;

	public TupleList(Reader r, Schema schema) throws Exception {
		this.schema = schema;
		rowComparator = new TupleComparator();
		BufferedReader br = new BufferedReader(r);
		String line = br.readLine();
		if (line == null) {
			throw new IllegalArgumentException("No column header row");
		}
		String[] names = line.split("\\t");
		if (names.length != schema.getFields().size()) {
			throw new IllegalArgumentException("Number of columns don't match");
		}
		rows = new ArrayList<Tuple>(10000);
		line = br.readLine();
		while (line != null) {
			rows.add(new Tuple(schema, line.split("\\t")));
			line = br.readLine();
		}
	}
	
	public TupleList(Schema schema) {
		this.schema = schema;
		rows = new ArrayList<Tuple>(10000);
		rowComparator = new TupleComparator();
	}

	public void add(Tuple row) {
		rows.add(row);
	}

	public int getRowCount() {
		return rows.size();
	}
	
	public int getColumnCount() {
		return schema.getFields().size();
	}
	
	public Tuple getRow(int index) {
		return rows.get(index);
	}
	
	public TupleList orderBy(int... columns) {
		rowComparator.setColumns(columns);
		Collections.sort(rows, rowComparator);
		return this;
	}
	
	public TupleList groupBy(int[] columns, AggregateFunction... af) throws Exception {
		HashMap<Tuple,ArrayList<Tuple>> groups = new HashMap<Tuple, ArrayList<Tuple>>();
		int n = getRowCount();
		int listSize = n >> 5;
		if (listSize < 10) {
			listSize = 10;
		}
		for (int i = 0; i < n; i++) {
			Tuple row = rows.get(i);
			Tuple key = new Tuple(row, columns);
			ArrayList<Tuple> list = groups.get(key);
			if (list == null) {
				list = new ArrayList<Tuple>(listSize);
				groups.put(key, list);
			}
			list.add(row);
		}
		Schema ns = new Schema();
		Field[] md = new Field[columns.length + af.length];
		ArrayList<Field> fields = schema.getFields();
		for (int i = 0; i < columns.length; i++) {
			ns.addField(fields.get(columns[i]));
		}
		for (int i = 0; i < af.length; i++) {
			af[i].setType(fields.get(af[i].getIndex()).getType());
			ns.addField(af[i].getField());
		}
		TupleList gb = new TupleList(ns);
		for (Tuple key : groups.keySet()) {
			ArrayList<Tuple> list = groups.get(key);
			Comparable[] values = new Comparable<?>[md.length];
			for (int i = 0; i < columns.length; i++) {
				values[i] = key.getValue(i);
			}
			for (int i = 0; i < af.length; i++) {
				values[columns.length + i] = af[i].compute(list);
			}
			gb.add(new Tuple(ns, values));
		}
		return gb;
	}
	
	public TupleList unionAll(TupleList t) {
		if (!schema.equalTypes(t.getSchema())) {
			throw new IllegalArgumentException("Schemas are not compatible");
		}
		int n = t.getRowCount();
		for (int i = 0; i < n; i += 1) {
			add(t.getRow(i));
		}
		return t;
	}

	public Schema getSchema() {
		return schema;
	}
}
