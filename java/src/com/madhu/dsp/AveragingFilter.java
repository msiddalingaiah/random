
package com.madhu.dsp;

/*
 * Created on Feb 18, 2008 at 7:41:39 PM
 */
public class AveragingFilter implements Source {
	private Source input;
	private byte wn1;

	public AveragingFilter(Source input) {
		this.input = input;
	}

	public double getValue() throws Exception {
		byte uI = (byte) input.getValue();
		byte wI = (byte) (wn1 + ((uI - wn1) >> 4));
		wn1 = wI;
		return wI;
	}

	public void reset() {
		wn1 = 0;
	}
}
