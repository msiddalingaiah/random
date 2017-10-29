
package com.madhu.dsp;

public class Correlator implements Source {
	private DownConverter mixer;
	
	public Correlator(Source input) {
		mixer = new DownConverter(input);
	}

	public double getValue() throws Exception {
		Complex sample = mixer.nextRawSample();
		double i = sample.getReal();
		double q = sample.getImaginary();

		return i == 0 && q == 0x55 ? 1 : 0;
	}

	public void reset() {
		mixer.reset();
	}
}
