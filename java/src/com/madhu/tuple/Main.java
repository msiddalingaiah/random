
package com.madhu.tuple;

import java.io.FileReader;

import com.madhu.tuple.af.Count;
import com.madhu.tuple.af.Sum;

// 0 COMMODITIES_ID
// 1 BUSINESS_OBJECTIVE_ID
// 2 BA_ID_INTERNAL
// 3 FASB_TYPE_ID
// 4 PORTFOLIO_DATE
// 5 PRICE_DATE
// 6 DEAL_ID
// 7 DEAL_TYPE_ID
// 8 MTM_DOLLARS
// 9 CREATE_USER
// 10 CREATE_DATE
// 11 MODIFY_USER
// 12 MODIFY_DATE

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("companies.txt");
		Schema schema = new Schema();
//		schema.addField(new Field("COMMODITIES_ID", Integer.class));
//		schema.addField(new Field("BUSINESS_OBJECTIVE_ID", Integer.class));
//		schema.addField(new Field("BA_ID_INTERNAL", Integer.class));
//		schema.addField(new Field("FASB_TYPE_ID", Integer.class));
//		schema.addField(new Field("PORTFOLIO_DATE", Date.class));
//		schema.addField(new Field("PRICE_DATE", Date.class));
//		schema.addField(new Field("DEAL_ID", Integer.class));
//		schema.addField(new Field("DEAL_TYPE_ID", Integer.class));
//		schema.addField(new Field("MTM_DOLLARS", Double.class));
//		schema.addField(new Field("CREATE_USER", Integer.class));
//		schema.addField(new Field("CREATE_DATE", Date.class));
//		schema.addField(new Field("MODIFY_USER", Integer.class));
//		schema.addField(new Field("MODIFY_DATE", Date.class));
		schema.addField(new Field("Company", String.class));
		schema.addField(new Field("Amount", Integer.class));
		TupleList t0 = new TupleList(fr, schema);
		System.out.printf("%d rows read%n", t0.getRowCount());
		t0.orderBy(0);
		for (int i = 0; i < t0.getRowCount(); i++) {
			System.out.println(t0.getRow(i));
		}
		TupleList t = t0.groupBy(new int[] {0}, new Count(0, "Count"), new Sum(1, "Total")).orderBy(1);
		System.out.println("\nGrouped rows: " + t.getRowCount());
		for (int i = 0; i < t.getRowCount(); i++) {
			System.out.println(t.getRow(i));
		}
	}
}
