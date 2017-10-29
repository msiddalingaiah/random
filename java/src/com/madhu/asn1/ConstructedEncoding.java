
package com.madhu.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ConstructedEncoding extends Encoding {
	private ArrayList<Encoding> contents;

	public ConstructedEncoding(Header header, InputStream is) throws IOException {
		super(header);
		readContent(is);
	}
	
	public int getSize() {
		return contents.size();
	}

	public Encoding select(String path) {
		if (path.length() == 0) {
			return this;
		}
		int slash = path.indexOf('/');
		String remaining;
		if (slash == -1) {
			slash = path.length();
			remaining = "";
		} else {
			remaining = path.substring(slash+1);
		}
		int index = Integer.parseInt(path.substring(0, slash));
		return contents.get(index).select(remaining);
	}
	
	public String findPrimitivePath(String contains) {
		int n = contents.size();
		for (int i=0; i<n; i+=1) {
			String path = contents.get(i).findPrimitivePath(contains);
			if (path != null) {
				if (path.length() > 0) {
					return String.format("%d/%s", i, path);
				} else {
					return String.format("%d", i);
				}
			}
		}
		return null;
	}
	
	public Encoding getContent(int index) {
		return (Encoding) contents.get(index);
	}

	private void readContent(InputStream is) throws IOException {
		Header header = getHeader();
		if (header.isDefiniteLength()) {
			int length = header.getContentLength();
			contents = new ArrayList<Encoding>(length);
			while (length > 0) {
				Encoding encoding = Encoding.read(is);
				contents.add(encoding);
				length -= encoding.getHeader().getCombinedLength();
			}
		} else {
			contents = new ArrayList<Encoding>(10);
			Encoding encoding = Encoding.read(is);
			while (!encoding.getHeader().isEndOfContent()) {
				contents.add(encoding);
				encoding = Encoding.read(is);
			}
		}
	}
	
	private void indent(StringBuffer sb, int indent) {
		for (int i = 0; i < indent; i += 1) {
			sb.append('\t');
		}
	}

	private static int indent;

	public String toString() {
		int n = contents.size();
		StringBuffer sb = new StringBuffer(n  * 20);
		sb.append(getHeader().getTagName());
		indent += 1;
		for (int i = 0; i < n; i += 1) {
			sb.append("\n");
			indent(sb, indent);
			Encoding encoding = contents.get(i);
			sb.append(encoding.toString());
		}
		indent -= 1;
		return sb.toString();
	}
}
