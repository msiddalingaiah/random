
package com.madhu.dsp;

public class Comparator implements Source {
	private Source input;
	private double threshold;

	public Comparator(Source input, double threshold) {
		this.input = input;
		this.threshold = threshold;
	}

	public double getValue() throws Exception {
		return input.getValue() > threshold ? 1 : 0;
	}

	public void reset() {
		input.reset();
	}

}
