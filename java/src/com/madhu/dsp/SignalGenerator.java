
package com.madhu.dsp;

import java.util.Random;

public class SignalGenerator implements Source {
	private double amplitude;
	private double sampleRate;
	private double frequency;
	private double phase;
	private double theta;
	private double dTheta;
	private Random random;
	private double noise;

	public SignalGenerator(double amplitude, double sampleRate,
			double frequency, double phase, double noise) {
		this.amplitude = amplitude;
		this.sampleRate = sampleRate;
		setFrequency(frequency);
		this.phase = phase;
		this.noise = noise;
		random = new Random();
		reset();
	}

	public SignalGenerator(double amplitude, double sampleRate,
			double frequency, double phase) {
		this(amplitude, sampleRate, frequency, phase, 0.0);
	}

	public double getValue() {
		double value = amplitude * Math.sin(theta) + noise * random.nextGaussian();
		theta += dTheta;
		return value;
	}

	public void reset() {
		theta = phase;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
		dTheta = 2 * Math.PI * frequency / sampleRate;
	}
}
