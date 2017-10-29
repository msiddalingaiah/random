
package com.madhu.laplace;

public class PolyDivider {
	private double numerator[], denominator[]; /* C, B */
	private double quotient[], remainder[]; /* G, A */

	public PolyDivider(double numerator[], double denominator[]) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public void divide() {
		int ki = numerator.length - denominator.length;
		if (ki < 0) {
			quotient = new double[0];
			remainder = new double[numerator.length];
			for (int i = 0; i < numerator.length; i += 1) {
				remainder[i] = numerator[i];
			}
			return;
		}
		int mt = numerator.length - 1;
		int k = mt - denominator.length + 2;
		double[] d = new double[numerator.length];
		for (int i = 0; i < numerator.length; i += 1) {
			d[i] = numerator[i];
		}
		quotient = new double[ki + 1];
		while (k >= 1) {
			quotient[k - 1] = d[mt] / denominator[denominator.length - 1];
			int l = k;
			for (int i = 1; i <= denominator.length - 1; i += 1) {
				d[k - 1] -= quotient[l - 1] * denominator[i - 1];
				k += 1;
			}
			mt -= 1;
			k = mt - denominator.length + 2;
		}
		remainder = new double[denominator.length - 1];
		for (int i = 0; i < remainder.length; i += 1) {
			remainder[i] = d[i];
		}
	}

	public double[] getQuotient() {
		return quotient;
	}

	public double[] getRemainder() {
		return remainder;
	}

	public static void main(String args[]) throws Exception {
		double[] b = new double[] { 2, 19, 60, 82, 64, 30, 8, 1 };
		double[] c = new double[] { 8, 28, 44, 40, 22, 7, 1 };

		PolyDivider pd = new PolyDivider(b, c);
		pd.divide();
		double g[] = pd.getQuotient();
		double a[] = pd.getRemainder();

		System.out.println();
		for (int i = 0; i < g.length; i += 1) {
			System.out.println("G[" + i + "] = " + g[i]);
		}
		System.out.println();
		for (int i = 0; i < a.length; i += 1) {
			System.out.println("A[" + i + "] = " + a[i]);
		}
	}
}
