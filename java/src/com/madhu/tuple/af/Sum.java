
package com.madhu.tuple.af;

import java.util.ArrayList;

import com.madhu.tuple.AggregateFunction;
import com.madhu.tuple.Tuple;

public class Sum extends AggregateFunction {
	public Sum(int index, String name) throws Exception {
		super(index, name);
	}

	public Sum(int index) throws Exception {
		super(index, String.format("sum%d", index));
	}

	@Override
	public Comparable compute(ArrayList<Tuple> rows) throws Exception {
		if (getType() == Integer.class) {
			int total = 0;
			int index = getIndex();
			for (Tuple row : rows) {
				Integer value = (Integer) row.getValue(index);
				if (value != null) {
					total += value.intValue();
				}
			}
			return total;
		} else if (getType() == Double.class) {
			double total = 0;
			int index = getIndex();
			for (Tuple row : rows) {
				Double value = (Double) row.getValue(index);
				if (value != null) {
					total += value.doubleValue();
				}
			}
			return total;
		}
		throw new IllegalArgumentException("Sum doesn't support type: " + getType());
	}
}
