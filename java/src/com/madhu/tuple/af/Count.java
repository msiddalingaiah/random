
package com.madhu.tuple.af;

import java.util.ArrayList;

import com.madhu.tuple.AggregateFunction;
import com.madhu.tuple.Tuple;

/*
 * Created on Sep 22, 2008 at 4:17:22 PM
 */
public class Count extends AggregateFunction {
	public Count(int index, String name) throws Exception {
		super(index, name);
	}

	@Override
	public Comparable compute(ArrayList<Tuple> rows) throws Exception {
		return rows.size();
	}
}
