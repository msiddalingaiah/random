
package com.madhu.dsp;

public class FM implements Source {
	private DownConverter mixer;
	private double i0;
	private double q0;
	
	public FM(Source input) {
		mixer = new DownConverter(input);
	}

	public double getValue() throws Exception {
		Complex sample = mixer.nextSample();
		double i = sample.getReal();
		double q = sample.getImaginary();
		double value = i * (q - q0) - q * (i - i0);
		i0 = i;
		q0 = q;
		return value;
	}

	public void reset() {
		mixer.reset();
		i0 = 0;
		q0 = 0;
	}
}
