
package com.madhu.dsp;

public class FMDemodulator implements Source {
	private DownConverter mixer;
	
	public FMDemodulator(Source input) {
		mixer = new DownConverter(input);
	}

	public double getValue() throws Exception {
		Complex sample = mixer.nextSample();

		return Math.atan2(sample.getReal(), sample.getImaginary());
	}

	public void reset() {
		mixer.reset();
	}
}
