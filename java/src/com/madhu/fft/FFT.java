
/*
 * Created on Jun 14, 2003
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
public class FFT extends AbstractFFT {
	protected double[][] workingData;

	public FFT(int size, int averageCount, int windowFunction) {
		super(size, averageCount, windowFunction);
		this.workingData = new double[size][2];
	}

	public FFT(int fftSize) {
		this(fftSize, 1, HANNING);
	}

	@Override
	protected void doTransform() {
		int n = getSize();
		double[][] wd = workingData;
		for (int i=0; i<n; i+=1) {
			wd[i][0] = real[i];
			wd[i][1] = imag[i];
		}
		fft_1d();
		for (int i=0; i<n; i+=1) {
			real[i] = wd[i][0];
			imag[i] = wd[i][1];
		}
	}

	private void fft_1d() {
		double  u_r,u_i, w_r,w_i, t_r,t_i;
		int     nv2, k, l, le, le1, j, ip, i;

		int fftSize = getSize();
		double[][] array = workingData;
		nv2 = fftSize / 2;
		j = 1;
		for (i = 1; i < fftSize; i++ ) {
			if (i < j) {
					t_r = array[i - 1][0];
					t_i = array[i - 1][1];
					array[i - 1][0] = array[j - 1][0];
					array[i - 1][1] = array[j - 1][1];
					array[j - 1][0] = t_r;
					array[j - 1][1] = t_i;
			}
			k = nv2;
			while (k < j) {
					j = j - k;
					k = k / 2;
			}
			j = j + k;
		}

		int numberOfBits = getNumberOfBits();
		// le = 2^l
		for (l = 1; l <= numberOfBits ; l++) { /* loops thru stages */
			le = (int)(Math.exp( (double)l * Math.log(2) ) + 0.5 );
			le1 = le / 2;
			u_r = 1.0;
			u_i = 0.0;
			w_r =  Math.cos( Math.PI / (double)le1 );
			w_i = -Math.sin( Math.PI / (double)le1 );
			for (j = 1; j <= le1; j++) { /* loops thru 1/2 twiddle values per stage */
				for (i = j; i <= fftSize; i += le) { /* loops thru points per 1/2 twiddle */
					ip = i + le1;
					t_r = array[ip - 1][0] * u_r - u_i * array[ip - 1][1];
					t_i = array[ip - 1][1] * u_r + u_i * array[ip - 1][0];

					array[ip - 1][0] = array[i - 1][0] - t_r;
					array[ip - 1][1] = array[i - 1][1] - t_i;

					array[i - 1][0] =  array[i - 1][0] + t_r;
					array[i - 1][1] =  array[i - 1][1] + t_i;
				}
				t_r = u_r * w_r - w_i * u_i;
				u_i = w_r * u_i + w_i * u_r;
				u_r = t_r;
			}
		}
	}
}
