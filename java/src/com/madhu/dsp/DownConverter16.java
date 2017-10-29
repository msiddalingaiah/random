
package com.madhu.dsp;

public class DownConverter16 {
	private Source input;
	private byte wn1I;
	private byte wn1Q;
	
	public DownConverter16(Source input) {
		this.input = input;
	}

	private int countBits(int bits) {
		int n = 0;
		for (int i = 0; i < 8; i++) {
			if ((bits & 0x80) != 0) {
				n += 1;
			}
			bits <<= 1;
		}
		return n;
	}
	
	public Complex nextSample() throws Exception {
		byte x8 = 0;
		for (int i=0; i<8; i+=1) {
			byte xin = (byte) (input.getValue() > 0 ? 1 : 0);
			x8 <<= 1;
			x8 |= xin;
		}
		byte x16 = 0;
		for (int i=0; i<8; i+=1) {
			byte xin = (byte) (input.getValue() > 0 ? 1 : 0);
			x16 <<= 1;
			x16 |= xin;
		}
//		byte uI = lut256[(x8 ^ 0xcc) & 0xff];
//		byte uQ = lut256[(x8 ^ 0x99) & 0xff];
		byte uI = (byte) (-113 + 15 * (countBits(x8 ^ 0xcc) + countBits(x16 ^ 0xcc)));
		byte uQ = (byte) (-113 + 15 * (countBits(x8 ^ 0x99) + countBits(x16 ^ 0x99)));

		return filter(uI, uQ);
	}

	public Complex filter(byte uI, byte uQ) {
		byte wI = (byte) (wn1I + ((uI - wn1I) >> 4));
		wn1I = wI;
		byte wQ = (byte) (wn1Q + ((uQ - wn1Q) >> 4));
		wn1Q = wQ;
		return new Complex(wI, wQ);
	}

	public void reset() {
		wn1I = 0;
		wn1Q = 0;
	}
}
