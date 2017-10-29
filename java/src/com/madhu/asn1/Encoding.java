
package com.madhu.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class Encoding {
	public static final int BOOLEAN =			0x01;	/*  1: Boolean */
	public static final int INTEGER =			0x02;	/*  2: Integer */
	public static final int BITSTRING =			0x03;	/*  2: Bit string */
	public static final int OCTETSTRING =		0x04;	/*  4: Byte string */
	public static final int NULLTAG =			0x05;	/*  5: NULL */
	public static final int OIDTAG =			0x06;	/*  6: Object Identifier */
	public static final int OBJDESCRIPTOR =		0x07;	/*  7: Object Descriptor */
	public static final int EXTERNAL =			0x08;	/*  8: External */
	public static final int REAL =				0x09;	/*  9: Real */
	public static final int ENUMERATED =		0x0A;	/* 10: Enumerated */
	public static final int EMBEDDED_PDV =		0x0B;	/* 11: Embedded Presentation Data Value */
	public static final int UTF8STRING =		0x0C;	/* 12: UTF8 string */
	public static final int SEQUENCE =			0x10;	/* 16: Sequence/sequence of */
	public static final int SET =				0x11;	/* 17: Set/set of */
	public static final int NUMERICSTRING =		0x12;	/* 18: Numeric string */
	public static final int PRINTABLESTRING =	0x13;	/* 19: Printable string (ASCII subset) */
	public static final int T61STRING =			0x14;	/* 20: T61/Teletex string */
	public static final int VIDEOTEXSTRING =	0x15;	/* 21: Videotex string */
	public static final int IA5STRING =			0x16;	/* 22: IA5/ASCII string */
	public static final int UTCTIME =			0x17;	/* 23: UTC time */
	public static final int GENERALIZEDTIME =	0x18;	/* 24: Generalized time */
	public static final int GRAPHICSTRING =		0x19;	/* 25: Graphic string */
	public static final int VISIBLESTRING =		0x1A;	/* 26: Visible string (ASCII subset) */
	public static final int GENERALSTRING =		0x1B;	/* 27: General string */
	public static final int UNIVERSALSTRING =	0x1C;	/* 28: Universal string */
	public static final int BMPSTRING =			0x1E;	/* 30: Basic Multilingual Plane/Unicode string */

	private Header header;

	public Encoding(Header header) {
		this.header = header;
	}

	public Encoding() {
		setHeader(null);
	}
	
	public abstract Encoding select(String path);
	public abstract String findPrimitivePath(String contains);

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
	
	public static Encoding read(InputStream is) throws IOException {
//		System.out.print(is.toString() + " | ");
		Header header = new Header(is);
//		System.out.print(" | " + header);
		if (header.isPrimitive()) {
			PrimitiveEncoding prim = new PrimitiveEncoding(header, is);
			// Attempt to expose encapsulated primitive
//			if (header.getTag() == OCTETSTRING || header.getTag() == BITSTRING) {
//				try {
//					byte[] data = (byte[]) prim.getContent();
//					ByteArrayInputStream bis = new ByteArrayInputStream(data);
//					// not sure why, but rsaPublic key has an extra 0 in front...
//					bis.read();
//					Encoding encoding = Encoding.read(bis);
//					return new EncapsulatedEncoding(header, encoding);
//				} catch (Throwable e) {
//				}
//			}
			return prim;
//			System.out.println(" " + encoding);
		} else {
//			System.out.println();
			return new ConstructedEncoding(header, is);
		}
	}
}
