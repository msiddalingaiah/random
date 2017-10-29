
package com.madhu.dsp;

public class Integrator implements Source {
	private Source input;
	private int count;

	public Integrator(Source input, int count) {
		this.input = input;
		this.count = count;
		reset();
	}

	public double getValue() throws Exception {
		double value = 0;
		for (int i = 0; i < count; i += 1) {
			value += input.getValue();
		}
		return value / count;
	}

	public void reset() {
		input.reset();
	}
}
