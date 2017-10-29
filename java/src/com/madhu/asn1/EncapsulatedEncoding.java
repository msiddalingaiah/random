
package com.madhu.asn1;

/*
 * Created on Sep 24, 2007 at 1:38:47 PM
 */
public class EncapsulatedEncoding extends Encoding {
	private Encoding encoding;

	public EncapsulatedEncoding(Header header, Encoding encoding) {
		super(header);
		this.encoding = encoding;
		int tag = encoding.getHeader().getTag();
		if (tag < 1) {
			throw new IllegalArgumentException("invalid encoding");
		}
	}

	@Override
	public Encoding select(String path) {
		return encoding.select(path);
	}
	
	public String toString() {
		return getHeader().getTagName() + " encapsulates " + encoding.toString();
	}

	@Override
	public String findPrimitivePath(String contains) {
		return encoding.findPrimitivePath(contains);
	}
}
