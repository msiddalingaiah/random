
/*
 * Created on Jun 20, 2003
 *
 * Copyright (c) 2003 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.fft;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Goertzel {
	protected double doubleN;
	protected double sampleRate;
	protected double[] complex;

	public Goertzel(int N, double sampleRate) {
		this.doubleN = N;
		this.sampleRate = sampleRate;
		this.complex = new double[2];
	}

	public double getMagnitude(double f, double[] samples) {
		double q0, q1, q2;

		double k = (int) (0.5 + ((doubleN * f) / sampleRate));
		double omega = (2.0 * Math.PI * k) / doubleN;
		double cosine = Math.cos(omega);
		double coeff = 2.0 * cosine;

		q2 = q1 = 0.0;
		int n = (int) doubleN;
		for (int i = 0; i < n; i += 1) {
			q0 = coeff * q1 - q2 + samples[i];
			q2 = q1;
			q1 = q0;
		}
		return Math.sqrt(q1*q1 + q2*q2 - q1*q2*coeff);
	}

	public double[] getComplex(double f, double[] samples) {
		double q0, q1, q2;

		double k = (int) (0.5 + ((doubleN * f) / sampleRate));
		double omega = (2.0 * Math.PI * k) / doubleN;
		double sine = Math.sin(omega);
		double cosine = Math.cos(omega);
		double coeff = 2.0 * cosine;

		q2 = q1 = 0.0;
		int n = (int) doubleN;
		for (int i = 0; i < n; i += 1) {
			q0 = coeff * q1 - q2 + samples[i];
			q2 = q1;
			q1 = q0;
		}
		double realPart = (q1 - q2 * cosine);
		double imagPart = (q2 * sine);
		complex[0] = realPart;
		complex[1] = imagPart;
		return complex;
	}
}
