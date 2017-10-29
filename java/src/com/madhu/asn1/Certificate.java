
package com.madhu.asn1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * Created on Sep 20, 2007 at 10:42:21 PM
 */
public class Certificate {
	private Encoding encoding;
	
	public Certificate(InputStream is) throws IOException {
		encoding = Encoding.read(is);
		System.out.println(encoding);
	}
	
	public String getAttribute(String name) {
		ConstructedEncoding attrs = (ConstructedEncoding) encoding.select("0/3");
		int n = attrs.getSize();
		for (int i = 0; i < n; i += 1) {
			String label = attrs.getContent(i).select("0/0").toString();
			if (label.startsWith(name)) {
				return attrs.getContent(i).select("0/1").toString();
			}
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		InputStream fis = new PrintInputStream(new FileInputStream("asn1/export.pfx"));
		try {
			Certificate cert = new Certificate(fis);
			String[] names = new String[] {
				"countryName",
				"stateOrProvinceName",
				"localityName",
				"organizationName",
				"organizationalUnitName",
				"commonName",
			};
			for (int i = 0; i < names.length; i++) {
				String name = names[i];
				System.out.println(name + ": " + cert.getAttribute(name));
			}
		} catch (Throwable e) {
			System.out.println();
			System.out.println(e.toString());
		} finally {
			fis.close();
		}
	}
}
