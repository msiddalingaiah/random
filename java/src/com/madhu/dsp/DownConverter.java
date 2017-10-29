
package com.madhu.dsp;

public class DownConverter {
	private Source input;
	private byte lut256[];
	private byte lut9[] = {
		-111, -81, -52, -22,
		8, 38, 68, 97,
		127
	};
	private byte wn1I;
	private byte wn1Q;
	
	public DownConverter(Source input) {
		this.input = input;
		lut256 = new byte[256];
		for (int i = 0; i < lut256.length; i++) {
			lut256[i] = lut9[countBits(i)];
		}
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
//		byte uI = lut256[(x8 ^ 0xcc) & 0xff];
//		byte uQ = lut256[(x8 ^ 0x99) & 0xff];
		byte uI = (byte) (-113 + 30 * countBits(x8 ^ 0xcc));
		byte uQ = (byte) (-113 + 30 * countBits(x8 ^ 0x99));

		return filter(uI, uQ);
	}

	public Complex nextRawSample() throws Exception {
		byte x8 = 0;
		for (int i=0; i<8; i+=1) {
			byte xin = (byte) (input.getValue() > 0 ? 1 : 0);
			x8 <<= 1;
			x8 |= xin;
		}
		return new Complex((x8 ^ 0xcc) & 0xff, (x8 ^ 0x99) & 0xff);
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
