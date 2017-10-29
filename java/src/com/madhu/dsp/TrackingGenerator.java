
package com.madhu.dsp;

public class TrackingGenerator implements Source {
	double f;
	double df;
	private double centerFreqency;
	private double span;
	private int npoints;
	private SignalGenerator gen;
	private Source input;

	public TrackingGenerator(SignalGenerator gen, Source input,
			double centerFreqency, double span, int npoints) {
		this.centerFreqency = centerFreqency;
		this.span = span;
		this.npoints = npoints;
		this.gen = gen;
		this.input = input;
		reset();
	}

	public double getValue() throws Exception {
		gen.setFrequency(f);
		f += df;
		input.reset();
		return input.getValue();
	}

	public void reset() {
		f = centerFreqency - (span/2);
		df = span / npoints;
		input.reset();
	}
}
