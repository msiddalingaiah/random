
package com.madhu.fft;

public abstract class AbstractFFT {
	public static final int RECTANGLE = 0;
	public static final int HAMMING = 1;
	public static final int HANNING = 2;
	public static final int BARTLETT = 3;
	public static final int BLACKMAN = 4;
	public static final int WELCH = 5;
	private int size;
	private int numberOfBits;
	private int windowFunction;
	private int averageCount;
	private int frameIndex;
	private double[] magnitude;
	private double[] sum;
	protected double[] real;
	protected double[] imag;

	public static final String[] WINDOW_NAMES = {
		"Rectangle", "Hamming", "Hanning", "Bartlett", "Blackman", "Welch"
	};

	public AbstractFFT(int size, int averageCount, int windowFunction) {
		if (Integer.bitCount(size) != 1) {
			throw new IllegalArgumentException("size is not a power of two");
		}
		this.size = size;
		this.averageCount = averageCount;
		this.windowFunction = windowFunction;
		this.numberOfBits = Integer.numberOfTrailingZeros(size);
		this.magnitude = new double[size];
		this.sum = new double[size];
		this.real = new double[size];
		this.imag = new double[size];
	}

	public AbstractFFT(int fftSize) {
		this(fftSize, 1, HANNING);
	}

	protected abstract void doTransform();

	/**
	 * @return
	 */
	public double[] getMagnitude() {
		return magnitude;
	}

	public boolean isAverageDone() {
		return frameIndex == 0;
	}

	protected double getWindowValue(int i, int n) {
		double temp;
	
		switch (windowFunction) {
			default:
			case RECTANGLE:	return 1.0;
			case HAMMING:	return 0.54 - 0.46 * Math.cos (6.28318530717959 * i / (n-1));
			case BARTLETT:	if (i <= ((n-1)/2)) return 2.0 * i / (n - 1);
							return 2.0 - 2.0 * i / (n - 1);
			case BLACKMAN:	return 0.42 - 0.5 * Math.cos (6.28318530717959 * i / (n-1))
									+ 0.08 * Math.cos (12.56637061 * i / (n-1));
			case HANNING:	return 0.5 - 0.5 * Math.cos (6.28318530717959 * i / (n-1));
			case WELCH:	temp = (2.0 * i / (n+1)) - ((n-1.0)/(n+1.0));
							return 1.0 - temp * temp;
		}
	}

	/**
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return
	 */
	public int getNumberOfBits() {
		return numberOfBits;
	}

	/**
	 * @return
	 */
	public int getWindowFunction() {
		return windowFunction;
	}

	/**
	 * @param i
	 */
	public void setWindowFunction(int i) {
		windowFunction = i;
	}

	/**
	 * @return
	 */
	public int getAverageCount() {
		return averageCount;
	}

	/**
	 * @param i
	 */
	public void setAverageCount(int i) {
		averageCount = i;
	}

	public void transform(double[] realData) {
		int n = size;
		double avg = 0.0;
		for (int i=0; i<n; i+=1) {
			avg += realData[i];
		}
		avg /= n;
		for (int i=0; i<n; i+=1) {
			double wf = getWindowValue(i, n);
			real[i] = wf * (realData[i] - avg);
			imag[i] = 0.0;
		}
		doTransform();
		double normal = 1.0 / n;
		for (int i = 0; i < n; i += 1) {
			double ar = real[i];
			double ai = imag[i];
			sum[i] += 2.0 * normal * Math.sqrt(ar * ar + ai * ai);
		}
		frameIndex += 1;
		if (frameIndex >= averageCount) {
			double factor = 1.0 / averageCount;
			for (int i=0; i<n; i+=1) {
				magnitude[i] = factor * sum[i];
				sum[i] = 0.0;
			}
			frameIndex = 0;
		}
	}
}
