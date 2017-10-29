
package com.madhu.dsp;

public class ToneDecoder implements Source {
	private DownConverter mixer;
	
	public ToneDecoder(Source input) {
		mixer = new DownConverter(input);
	}

	public double getValue() throws Exception {
		Complex sample = mixer.nextSample();

		int aReal = Math.abs((int) sample.getReal());
		int aImag = Math.abs((int) sample.getImaginary());
//		int y = (Math.max(aReal, aImag) + Math.min(aReal, aImag)) >> 2;
		int y = Math.max(aReal, aImag) + (Math.min(aReal, aImag) >> 2);
		return y;
	}

	public void reset() {
		mixer.reset();
	}
}
