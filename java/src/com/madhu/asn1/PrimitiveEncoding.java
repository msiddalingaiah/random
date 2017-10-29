
package com.madhu.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class PrimitiveEncoding extends Encoding {
	private Object content;

	public PrimitiveEncoding(Header header, InputStream is) throws IOException {
		super(header);
		readContent(is);
	}

	public Object getContent() {
		return content;
	}

	public Encoding select(String path) {
		if (path.length() > 0) {
			throw new AssertionError("Unexpected extra path: " + path);
		}
		return this;
	}
	
	private void readContent(InputStream is) throws IOException {
		Header header = getHeader();
		int length = header.getContentLength();
		byte[] buf = new byte[length];
		int nRead = is.read(buf);
		if (nRead == -1 || nRead != length) {
			throw new IOException("Unexpected end of stream");
		}
		switch (header.getTag()) {
		case INTEGER:
			readInteger(buf);
			break;
		case OIDTAG:
			content = OID.getOID(buf);
			break;
		case NULLTAG:
			if (buf.length != 0) {
				throw new AssertionError("Null primitive has non-zero length: " +
						buf.length);
			}
			content = null;
			break;
		case PRINTABLESTRING: // fall through
		case UTF8STRING: // fall through
		case UTCTIME:
		case IA5STRING:
			content = new String(buf);
			break;
		case BITSTRING: // fall through
		case OCTETSTRING:
		default:
			content = buf;
			break;
		}
	}

	private void readInteger(byte[] buf) {
		int length = buf.length;
		if (length > 4) {
			content = new BigInteger(buf);
			return;
		}
		int value = 0;
		for (int i=0; i<length; i+=1) {
			value <<= 8;
			value |= buf[i] & 0xff;
		}
		content = new Integer(value);
	}

	public String toString() {
		Header header = getHeader();
		switch (header.getTag()) {
		case INTEGER:
		case OIDTAG:
		case PRINTABLESTRING:
		case UTCTIME:
		case UTF8STRING:
		case IA5STRING:
			return content.toString();
		case NULLTAG:
			return "null";
		case BITSTRING:
			byte[] data = (byte[]) content;
			return "byte[" + data.length + "] " +
				Integer.toHexString(data[0]);
		case OCTETSTRING:
			data = (byte[]) content;
			return String.format("%s %d bytes", header.getTagName(), data.length);
		}
		return String.format("%s %d bytes", header.getTagName(), header.getContentLength());
	}

	@Override
	public String findPrimitivePath(String contains) {
		if (toString().indexOf(contains) >= 0) {
			return "";
		}
		return null;
	}
}
