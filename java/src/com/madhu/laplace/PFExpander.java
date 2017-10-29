
package com.madhu.laplace;

public class PFExpander {
	private double numerator[], denominator[];
	private Complex poles[], simpleResidue[], doubleResidue[];

	public PFExpander(double numerator[], double denominator[], Complex poles[]) {
		this.numerator = numerator;
		this.denominator = denominator;
		this.poles = poles;
		simpleResidue = new Complex[poles.length];
		for (int i = 0; i < simpleResidue.length; i++) {
			simpleResidue[i] = new Complex();
		}
		doubleResidue = new Complex[poles.length];
	}

	public Complex[] getSimpleResidue() {
		return simpleResidue;
	}

	public Complex[] getDoubleResidue() {
		return doubleResidue;
	}

	private double[] differentiatePolynomial(double a[]) {
		double b[] = new double[a.length - 1];
		for (int i = 0; i < b.length; i += 1) {
			b[i] = a[i+1] * (i+1);
		}
		return b;
	}

	private Complex evaluatePolynomial(double a[], Complex p) {
		Complex value = new Complex(a[a.length - 1]);
		for (int i = a.length - 2; i >= 0; i -= 1) {
			value = value.multiply(p).add(a[i]);
		}
		return value;
	}

	public void expand() {
		double[] cd = differentiatePolynomial(denominator);
		for (int k = 0; k < denominator.length - 1; k += 1) {
			Complex cv = evaluatePolynomial(denominator, poles[k]);
			if (cv.abs() <= 0.001) {
				Complex av = evaluatePolynomial(numerator, poles[k]);
				Complex cdv = evaluatePolynomial(cd, poles[k]);
				if (cdv.abs() >= 0.001) {
					simpleResidue[k] = av.divide(cdv);
					doubleResidue[k] = new Complex(0.0, 0.0);
					continue;
				} /* 25 */
				double[] cdd = differentiatePolynomial(cd);
				Complex cddv = evaluatePolynomial(cdd, poles[k]);
				if (cddv.abs() < 0.001) {
					System.out.print("Pole of F(s) is greater ");
					System.out.println("than 2nd order: " + poles[k]);
					continue;
				}
				Complex t1 = av.divide(cddv);
				doubleResidue[k] = new Complex(2.0 * t1.re, 2.0 * t1.im);
				double prk = poles[k].re;
				double pik = poles[k].im;
				Complex sa = new Complex(0., 0.);
				if (numerator.length - 1 != 0) {
					RootFinder rf1 = new RootFinder(numerator);
					rf1.findRoots();
					Complex[] pa = rf1.getRoots();
					for (int i = 0; i < numerator.length - 1; i += 1) {
						Complex t2 = new Complex(1., 0.);
						t2 = t2.divide(poles[k].subtract(pa[i]));
						sa = sa.add(t2);
					}
				} /* 34 */
				Complex sb = new Complex(0., 0.);
				for (int i = 0; i < denominator.length - 1; i += 1) {
					if (Math.abs(prk - poles[i].re) > 0.001
							|| Math.abs(pik - poles[i].im) >= 0.001) {
						Complex t2 = new Complex(1., 0.);
						t2 = t2.divide(poles[k].subtract(poles[i]));
						sb = sb.add(t2);
					}
				} /* 39 */
				simpleResidue[k] = doubleResidue[k].multiply(sa.subtract(sb));
			} /* 42 */
		}
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
		RootFinder rf = new RootFinder(c);
		rf.findRoots();
		Complex p[] = rf.getRoots();
		System.out.println("Pole location\tResidue (1st deg term)\tResidue (2nd deg term)");
		PFExpander pfe = new PFExpander(a, c, p);
		pfe.expand();
		Complex r1[], r2[];
		r1 = pfe.getSimpleResidue();
		r2 = pfe.getDoubleResidue();
		for (int i = 0; i < r1.length; i += 1) {
			System.out.println("" + p[i] + "\t" + r1[i] + "\t" + r2[i]);
		}
	}
}
