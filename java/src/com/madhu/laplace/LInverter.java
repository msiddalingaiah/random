
package com.madhu.laplace;

public class LInverter {
	private static final double SMALL_NUMBER = 1.0e-5;
	private double numerator[], denominator[];
	private double results[];

	/**
	 * Inverse Laplace tranform based on FORTRAN routines described
	 * in "Basic Circuit Theory with Digital Computations" by L. P. Huelsman
	 * 
	 * @param numerator - coefficients of the numerator polynomial
	 * @param denominator - coefficients of the denominator polynomial
	 */
	public LInverter(double numerator[], double denominator[]) {
		if (numerator[numerator.length-1] == 0) {
			throw new IllegalArgumentException(
					String.format("Numerator coefficient a%d cannot be zero", numerator.length-1));
		}
		if (denominator[denominator.length-1] == 0) {
			throw new IllegalArgumentException(
					String.format("Denominator coefficient a%d cannot be zero", denominator.length-1));
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public void invert(double tmax, int nt) {
		PolyDivider pd = new PolyDivider(numerator, denominator);
		pd.divide();
		double a[] = pd.getRemainder();
		RootFinder rf = new RootFinder(denominator);
		rf.findRoots();
		Complex p[] = rf.getRoots();
		PFExpander pfe = new PFExpander(a, denominator, p);
		pfe.expand();
		Complex r1[] = pfe.getSimpleResidue();
		Complex r2[] = pfe.getDoubleResidue();

		double t = 0.0;
		double dt = tmax / (double) nt;
		int n = p.length;
		results = new double[nt];
		for (int i = 0; i < nt; i += 1) {
			boolean flag = false;
			double f = 0.0;
			Complex tc = new Complex(t, 0.0);
			for (int j = 0; j < n; j += 1) {
				double pi = p[j].im;
				double pr = p[j].re;
				if (j != 0) {
					flag = false;
					for (int k = 0; k < j; k += 1) {
						double pki = p[k].im;
						double pkr = p[k].re;
						if (Math.abs(pr - pkr) > SMALL_NUMBER) {
							continue;
						}
						if (Math.abs(pi - pki) < SMALL_NUMBER) {
							flag = true;
							break;
						}
					}
					if (flag) {
						continue;
					}
				} /* 17 */
				if (Math.abs(pi) >= SMALL_NUMBER) {
					Complex ept = (tc.multiply(p[j])).exp();
					double fr = (r1[j].multiply(ept)).re;
					f += 2. * fr;
				} /* 26 */
				double er = Math.exp(t * p[j].re);
				f += er * r1[j].re;
				if (r2[j].abs() >= SMALL_NUMBER) {
					f += t * er * r2[j].re;
				}
			} /* 30 */
			results[i] = f;
			t += dt;
		} /* 32 */
	}

	public double[] getResults() {
		return results;
	}

	public static void main(String args[]) throws Exception {
		double[] b = new double[] { 500 };
		double[] c = new double[] { 100, 2, 1 };

		double tmax = 2.5;
		int nt = 61;

		LInverter li = new LInverter(b, c);
		li.invert(tmax, nt);
		double results[] = li.getResults();

		double dt = tmax/nt;
		double t = 0;
		for (int i = 0; i < results.length; i += 1) {
			System.out.printf("%5.2f %10.2f%n", t, results[i]);
			t += dt;
		}
	}
}
