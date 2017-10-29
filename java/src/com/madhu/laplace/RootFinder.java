
package com.madhu.laplace;

public class RootFinder {
	int m, mp, n;
	double a[], c[], d[], e[];
	Complex p[];

	public RootFinder(double b[]) {
		a = new double[b.length];
		for (int i = 0; i < b.length; i += 1)
			a[i] = b[i];
		n = a.length - 1;
		m = n;
		mp = m + 1;
		p = new Complex[m];
		c = new double[mp];
		d = new double[mp];
		e = new double[mp];
	}

	public void findRoots() {
		int i, iter;
		double q1, q2, q1a, q2a;
		double disc, discr;
		double g11, g12, g21, g22;
		double dq1, dq2, det;

		double abs = a[0] < 0 ? -a[0] : a[0];
		while (abs <= 1.e-6) {
			for (i = 1; i <= m; i += 1)
				a[i - 1] = a[i];
			p[m - 1] = new Complex(0., 0.);
			m -= 1;
			if (m == 0)
				return;
			mp = m + 1;
			abs = a[0] < 0 ? -a[0] : a[0];
		}
		while (true) {
			if (m < 2) { /* 67 */
				p[0] = new Complex(-a[0] / a[1], 0.);
				return;
			}
			if (m == 2) { /* 64 */
				q1 = a[m - 2] / a[m];
				q2 = a[m - 1] / a[m];
				disc = q2 * q2 - 4. * q1;
				if (disc >= 0.) {
					discr = Math.sqrt(disc);
					p[m - 1] = new Complex((-q2 + discr) / 2., 0.);
					p[m - 2] = new Complex((-q2 - discr) / 2., 0.);
					m -= 2;
				} else {
					discr = Math.sqrt(-disc);
					p[m - 1] = new Complex(-q2 / 2., discr / 2.);
					p[m - 2] = new Complex(-q2 / 2., -discr / 2.);
					m -= 2;
				}
				if (m == 0)
					return;
				mp = m + 1;
				for (i = 1; i <= mp; i += 1)
					a[i - 1] = c[i + 1];
				continue;
			}
			if (m > 2) { /* 14 */
				iter = 0;
				q1 = a[0] / a[2];
				q2 = a[1] / a[2];
				while (true) { /* 17 */
					iter += 1;
					c[mp - 1] = a[mp - 1];
					d[mp - 1] = 0.;
					e[mp - 1] = 0.;
					c[m - 1] = a[m - 1] - q2 * c[mp - 1];
					d[m - 1] = -c[mp - 1];
					e[m - 1] = 0.;
					if (m >= 4) {
						int mm = m - 3;
						for (i = 1; i <= mm; i += 1) {
							int mi = m - i;
							int mi1 = mi + 1;
							c[mi - 1] = a[mi - 1] - q2 * c[mi] - q1 * c[mi1];
							d[mi - 1] = -c[mi] - q2 * d[mi] - q1 * d[mi1];
							e[mi - 1] = -c[mi1] - q2 * e[mi] - q1 * e[mi1];
						}
					}
					q1a = a[0] - q1 * c[2];
					q2a = a[1] - q1 * c[3] - q2 * c[2];
					g11 = -c[2] - q1 * d[3] - q2 * d[2];
					g12 = -c[3] - q1 * e[3] - q2 * e[2];
					g21 = -q1 * d[2];
					g22 = -c[2] - q1 * e[2];
					det = g11 * g22 - g12 * g21;
					dq1 = (-g11 * q1a + g21 * q2a) / det;
					dq2 = (g12 * q1a - g22 * q2a) / det;
					q1 = q1 + dq1;
					q2 = q2 + dq2;
					if (Math.abs(dq2) < 1.e-6 && Math.abs(dq1) < 1.e-6)
						break;
					if (iter > 30) {
						System.out.println("Root did not converge...");
						return;
					}
				}
				disc = q2 * q2 - 4. * q1;
				if (disc >= 0.) {
					discr = Math.sqrt(disc);
					p[m - 1] = new Complex((-q2 + discr) / 2., 0.);
					p[m - 2] = new Complex((-q2 - discr) / 2., 0.);
					m -= 2;
				} else {
					discr = Math.sqrt(-disc);
					p[m - 1] = new Complex(-q2 / 2., discr / 2.);
					p[m - 2] = new Complex(-q2 / 2., -discr / 2.);
					m -= 2;
				}
				if (m == 0)
					return;
				mp = m + 1;
				for (i = 1; i <= mp; i += 1)
					a[i - 1] = c[i + 1];
				continue;
			}
		}
	}

	public Complex[] getRoots() {
		return p;
	}

	public static void main(String args[]) throws Exception {
//		String[] strings = "2 19 60 82 64 30 8 1".split("\\s+");
		String[] strings = "100 0 1".split("\\s+");
		double[] b = new double[strings.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = Double.parseDouble(strings[i]);
			System.out.printf("Coefficient of s^%d: %.2f%n", i, b[i]);
		}

		RootFinder rf = new RootFinder(b);
		rf.findRoots();
		Complex a[] = rf.getRoots();

		System.out.println();
		for (int i = 0; i < a.length; i += 1) {
			System.out.println("A[" + i + "] = " + a[i]);
		}
	}
}
