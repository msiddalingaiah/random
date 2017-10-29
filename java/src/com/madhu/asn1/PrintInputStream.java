
package com.madhu.asn1;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * Created on Sep 20, 2007 at 8:13:23 PM
 */
public class PrintInputStream extends FilterInputStream {
	private int streamIndex;
	private boolean doPrint;

	protected PrintInputStream(InputStream in) {
		super(in);
		doPrint = false;
	}

	@Override
	public int read() throws IOException {
		if (!doPrint) {
			return super.read();
		}
		int value = in.read();
		System.out.print(Integer.toHexString(value));
		System.out.print(' ');
		streamIndex += 1;
		return value;
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		if (!doPrint) {
			return super.read(b);
		}
		int n = b.length;
		for (int i = 0; i < n; i += 1) {
			int value = read();
			if (value == -1) {
				return -1;
			}
			b[i] = (byte) value;
		}
		return n;
	}

	public String toString() {
		return Integer.toString(streamIndex) + " x" +
			Integer.toHexString(streamIndex);
	}
}
