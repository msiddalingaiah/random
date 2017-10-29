
package com.madhu.laplace;

public class Complex {
	public double re, im;

	public Complex() {
		re = 0.;
		im = 0.;
	}

	public Complex(double r, double i) {
		re = r;
		im = i;
	}

	public Complex(double r) {
		this(r, 0);
	}

	public Complex multiply(Complex m) {
		double tre, tim;

		tre = re * m.re - im * m.im;
		tim = re * m.im + im * m.re;
		return new Complex(tre, tim);
	}

	public Complex divide(Complex m) {
		double tre, tim, bot;

		bot = m.re * m.re + m.im * m.im;
		tre = (re * m.re + im * m.im) / bot;
		tim = (im * m.re - re * m.im) / bot;
		return new Complex(tre, tim);
	}

	public Complex add(Complex m) {
		double tre, tim;

		tre = re + m.re;
		tim = im + m.im;
		return new Complex(tre, tim);
	}

	public Complex add(double r) {
		double tre, tim;

		tre = re + r;
		tim = im + 0;
		return new Complex(tre, tim);
	}

	public Complex subtract(Complex m) {
		double tre, tim;

		tre = re - m.re;
		tim = im - m.im;
		return new Complex(tre, tim);
	}

	double abs() {
		return Math.sqrt(re * re + im * im);
	}

	Complex exp() {
		double tre, tim, exps;

		exps = Math.exp(re);
		tre = exps * Math.cos(im);
		tim = exps * Math.sin(im);
		return new Complex(tre, tim);
	}

	public String toString() {
		return String.format("(%.2f, j%.2f)", re, im);
	}
}
