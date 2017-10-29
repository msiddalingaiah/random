
package com.madhu.asn1;

import java.io.IOException;
import java.io.InputStream;

public class Header {
	private int encodingClass;
	private boolean primitive;
	private int tag;
	private int contentLength;
	private int headerLength;

	private static final String[] TAG_NAMES = {
		"[0]",
		"BOOLEAN",
		"INTEGER",
		"BITSTRING",
		"OCTETSTRING",
		"NULLTAG",
		"OIDTAG",
		"OBJDESCRIPTOR",
		"EXTERNAL",
		"REAL",
		"ENUMERATED",
		"EMBEDDED_PDV",
		"UTF8STRING",
		"[13]",
		"[14]",
		"[15]",
		"SEQUENCE",
		"SET",
		"NUMERICSTRING",
		"PRINTABLESTRING",
		"T61STRING",
		"VIDEOTEXSTRING",
		"IA5STRING",
		"UTCTIME",
		"GENERALIZEDTIME",
		"GRAPHICSTRING",
		"VISIBLESTRING",
		"GENERALSTRING",
		"UNIVERSALSTRING",
		"[29]",
		"BMPSTRING",
		"[31]",
	};

	public Header(InputStream is) throws IOException {
		headerLength = 1;
		int id = read(is) & 0xff;
		encodingClass = id >> 6;
		primitive = (id & 0x20) == 0;
		tag = id & 0x1f;
		if (tag == 0x1f) {
			tag = 0;
			int b = read(is);
			headerLength += 1;
			while ((b & 0x80) != 0) {
				tag <<= 7;
				tag |= b & 0x7f;
				b = read(is);
				headerLength += 1;
			}
		}
		contentLength = read(is) & 0xff;
		headerLength += 1;
		if (contentLength == 0x80) {
			contentLength = -1;
		} else if (contentLength > 0x80) {
			int n = contentLength & 0x7f;
			contentLength = 0;
			while (n-- > 0) {
				contentLength <<= 8;
				contentLength |= read(is) & 0xff;
				headerLength += 1;
			}
		}
		if (contentLength > 1024 * 1024) {
			throw new AssertionError("Excessive content length: " + contentLength);
		}
	}
	
	private int read(InputStream is) throws IOException {
		int c = is.read();
		if (c == -1) {
			throw new AssertionError("Unexpected end of stream");
		}
		return c;
	}
	
	public boolean isDefiniteLength() {
		return contentLength >= 0;
	}

	public int getEncodingClass() {
		return encodingClass;
	}

	public int getContentLength() {
		return contentLength;
	}
	
	public int getHeaderLength() {
		return headerLength;
	}

	public int getCombinedLength() {
		return headerLength + contentLength;
	}

	public boolean isPrimitive() {
		return primitive;
	}

	public int getTag() {
		return tag;
	}

	public boolean isEndOfContent() {
		return tag == 0 && primitive && contentLength == 0;
	}
	
	public String getTagName() {
		return TAG_NAMES[tag];
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(32);
//		sb.append("class: ");
//		sb.append(encodingClass);
//		sb.append(primitive ? " primitive " : " constructed ");
		sb.append("length: ");
		sb.append(contentLength);
		sb.append(" ");
		sb.append(getTagName());
		sb.append(" | ");
		return sb.toString();
	}
}
