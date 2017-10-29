
package com.madhu.dsp;

public class Differentiator implements Source {
	private Source input;
	private double y0;

	public Differentiator(Source input) {
		this.input = input;
	}

	public double getValue() throws Exception {
		double y1 = input.getValue();
		double value = y1 - y0;
		y0 = y1;
		return value;
	}

	public void reset() {
		y0 = 0;
		input.reset();
	}
}
