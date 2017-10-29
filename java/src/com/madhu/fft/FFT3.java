
package com.madhu.fft;

/*
 * Created on Mar 11, 2008 at 5:09:28 PM
 */
public class FFT3 extends AbstractFFT {
	private double[] wRe;
	private double[] wIm;
	private int[] reverseMap;

	public FFT3(int fftSize, int averageCount, int windowFunction) {
		super(fftSize, averageCount, windowFunction);
		int size2 = fftSize >> 1;
		wRe = new double[size2];
		wIm = new double[size2];
		reverseMap = new int[fftSize];
		int bits = getNumberOfBits();
		for (int i = 0; i < fftSize; i += 1) {
			reverseMap[i] = reverseBits(i, bits);
		}
		for (int i = 0; i < size2; i++) {
			int br = reverseBits(i, bits - 1);
			wRe[br] = Math.cos(2.0 * Math.PI * i / fftSize);
			wIm[br] = Math.sin(2.0 * Math.PI * i / fftSize);
		}
	}

	public FFT3(int fftSize) {
		this(fftSize, 1, HANNING);
	}

	public void printMagnitude() {
		for (int i = 0; i < getSize()>>1; i += 1) {
			double mag = Math.sqrt(real[i]*real[i] + imag[i]*imag[i]);
			System.out.printf("%f\n", mag);
		}
	}

	private int reverseBits(int inp, int numbits) {
		int rev = 0;
		for (int i = 0; i < numbits; i++) {
			rev = (rev << 1) | (inp & 1);
			inp >>= 1;
		}
		return rev;
	}

	@Override
	protected void doTransform() {
		int size = getSize();
		for (int i = size ; i >= 2; i = i >> 1) {
			int mt = i >> 1;

			int w = 0;
			for (int j = 0; j < size; j += i) {
				double w_re = wRe[w];
				double w_im = wIm[w];

				for (int k = j; k < (j + mt); k++) {
					double t_re = w_re * real[k + mt] - w_im * imag[k + mt];
					double t_im = w_re * imag[k + mt] + w_im * real[k + mt];

					double u_re = real[k];
					double u_im = imag[k];
					real[k] = u_re + t_re;
					imag[k] = u_im + t_im;
					real[k + mt] = u_re - t_re;
					imag[k + mt] = u_im - t_im;
				}
				w += 1;
			}
		}
		// Reverse data
		for (int i = 0; i < size; i++) {
			int bri = reverseMap[i];
			// skip already swapped elements
			if (bri > i) {
				double t_re = real[i];
				double t_im = imag[i];
				real[i] = real[bri];
				imag[i] = imag[bri];
				real[bri] = t_re;
				imag[bri] = t_im;
			}
		}
	}
}
