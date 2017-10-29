
package com.madhu.tuple;

import java.util.Comparator;

public class TupleComparator implements Comparator<Tuple> {
	private int[] columns;

	public void setColumns(int[] columns) {
		this.columns = columns;
	}

	public int compare(Tuple o1, Tuple o2) {
		int d = 0;
		for (int i = 0; i < columns.length; i++) {
			int column = columns[i];
			int asc = 1;
			if (column < 0) {
				asc = -1;
				column = -column;
			}
			Comparable value1 = o1.getValue(column);
			Comparable value2 = o2.getValue(column);
			if (value1 != null && value2 != null) {
				d = value1.compareTo(value2);
			} else  if (value1 == value2) {
				d = 0;
			} else if (value1 == null) {
				d = -1;
			} else {
				d = 1;
			}
			d *= asc;
			if (d != 0) {
				return d;
			}
		}
		return d;
	}
}
