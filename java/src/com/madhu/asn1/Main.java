
package com.madhu.asn1;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;

public class Main {
	public static final String VERSION = "0/0/0";
	public static final String SERIAL = "0/1";
	public static final String ISSUER = "0/3";
	public static final String SIGNATURE_ALG = "0/2/0";
	public static final String NOT_VALID_BEFORE = "0/4/0";
	public static final String NOT_VALID_AFTER = "0/4/1";
	public static final String SUBJECT = "0/5";
	public static final String PUBLIC_KEY_ALG = "0/6/0/0";

	public static final String RSA_PUBLIC_KEY = "0/6/1";
	public static final String RSA_PUBLIC_KEY_E = "0/6/1/0";
	public static final String RSA_PUBLIC_KEY_N = "0/6/1/1";

	public static void main(String[] args) throws Exception {
		// db data
		// plain text = 4387751111111111
		String encryptedData = "MIIB2AYJKoZIhvcNAQcDoIIByTCCAcUCAQAxggGBMIIBfQIBADBlMFcxEzARBgoJkiaJk/IsZAEZFgNvcmcxGTAXBgoJkiaJk/IsZAEZFglkcmVzc2Jhcm4xJTAjBgNVBAMTHERyZXNzYmFybiBFbnRlcnByaXNlIFJvb3QgQ0ECCj9TNzoAAAAAA0IwDQYJKoZIhvcNAQEBBQAEggEAH08pHaMVbsHq3oqERufthfIFaFKxQxD7tkweIBpri+cNmokJb0AEaM3uIL/cOYalMmRDtIwVVfLhsWBrRxWd9j9aCrU9lQGQWKDYVEbTd+iOrBXyWAelpG92SYr2lgnCA8/hhOQAnPD3UBjraQESJF6w7lKFjnKCBAvvoJj2unXwBXc+82lq6/GIBnlFoenqP8iMAmyNqEA+fb4IIY9j7QaQQhwyRJQNP+Iz+eQZyIWnhHf6psXYVx59kePiN4qAwy00hNgu9RNGzuLaRxr0EtP+odl5FoD9rWIP+a+vqWuujg95VjwwhmWOIU1jT7gKfCrUIbILMzhT0xDmoderxjA7BgkqhkiG9w0BBwEwDAYIKoZIhvcNAwQFAIAgmhbR1iZIuauilwWJ49cQ+R7D77zQDb0gFLVn+uCsE6E=";
//		String encryptedData = "MIIB1AYJKoZIhvcNAQcDoIIBxTCCAcECAQAxggGBMIIBfQIBADBlMFcxEzARBgoJkiaJk/IsZAEZFgNvcmcxGTAXBgoJkiaJk/IsZAEZFglkcmVzc2Jhcm4xJTAjBgNVBAMTHERyZXNzYmFybiBFbnRlcnByaXNlIFJvb3QgQ0ECCj9TNzoAAAAAA0IwDQYJKoZIhvcNAQEBBQAEggEALZ158oHbdj6QNDw9L68ZyVz9hsxTNRhVGrI74tBjBirmUtx+opcqPZ453o6uSbOxjWCYiBII7jVKFEGOZtSzSgiS34eyucwstZ+6hVtF0qoejwIV1e7FDeXalKoU7Osha4Z8k3NCo2i0jadgzIYp/28x1IOb+BKJcY886b99cbUSxLNwSL+2Tw5fc1qyUpCTYyE301F3BfFGrFIjngVEHTP4fs1o8pHcLN29h4Ke0kz1bZbZUKZP1jINvCO4SPd0qIwFk+uneG+v8vTiUMxTszgU2O1gxFPdoigTY2H+l3nx6eP+fYszvwh/RCwYMgAHoXT56Au3zuz5rz1gbuU0hDA3BgkqhkiG9w0BBwEwDAYIKoZIhvcNAwQFAIAcmmmW6fGzKV0+m13t8NZGupLwTakYazh0CmbrIQ==";
//		String encryptedData = "MIIB1AYJKoZIhvcNAQcDoIIBxTCCAcECAQAxggGBMIIBfQIBADBlMFcxEzARBgoJkiaJk/IsZAEZFgNvcmcxGTAXBgoJkiaJk/IsZAEZFglkcmVzc2Jhcm4xJTAjBgNVBAMTHERyZXNzYmFybiBFbnRlcnByaXNlIFJvb3QgQ0ECCj9TNzoAAAAAA0IwDQYJKoZIhvcNAQEBBQAEggEAHTAcjAOLqh+ElBQaFylMUlnYFrtk6KVWhMUmJHvp/tbU9p4VO6WQZpDqg5sNdll41ywmXT9ZVu0wQCiPjy+R0Dd7iJCkAREnYDrbX5Cu1ADrah97/2ftBJlipetuBa0aXZ9CTPYJcJO87V/K0jJh1lRJNHeemLnGZh5UxgjXajAuxduGBlngP/4S7nUbpA5WzxP6obQx+CKZD5iioxd8DRfZaNgIQGy/CUISOkSzB4tkcjOn61g6M4TOpBKIbTYDTWYvcgX5JW7dlarE0hES1FZfDewPkRMZREZLXY8GAxiyEv3Z6u3/gX41G2GQ8LSBauMVKi1wbmZh7iEGzIB9WDA3BgkqhkiG9w0BBwEwDAYIKoZIhvcNAwQFAIAcntaf9HPFcGPr0a90giG/B5D4aIORMp+Rjh8HPw==";
//		String encryptedData = "MIIB2AYJKoZIhvcNAQcDoIIByTCCAcUCAQAxggGBMIIBfQIBADBlMFcxEzARBgoJkiaJk/IsZAEZFgNvcmcxGTAXBgoJkiaJk/IsZAEZFglkcmVzc2Jhcm4xJTAjBgNVBAMTHERyZXNzYmFybiBFbnRlcnByaXNlIFJvb3QgQ0ECCj9TNzoAAAAAA0IwDQYJKoZIhvcNAQEBBQAEggEALN7k1j2W1TvQJkubAw770+bcdGlFLBIdpXUCxtV8lZYsOgFPeP/LOSAVqWEphLCygyuqtmnkD155n1DUWU+I7PqeEjTq+tYUN+korEFwyDfpFQ3FSAgHI0q0LeIuhLqeL0BA9uuUCAUe3lHTkwD1E56gTjLkJ/PuyExtBlQKrBqftJ8UgwZiNNbgLjyL+Hy3RrBBVXmPswqcC+iwPeoWJCIS6VCZE+GeFvKp7hLs1/7gPB3EIpLy/c/vPJj6rbCxjRNohUF50TMahkz8uqEq+Q3LVh/pP94qzxNPOaZSqxz/BgrwhvQuJ9jpWCA47yHXjNV3FJqKRg0wIC04LeYCejA7BgkqhkiG9w0BBwEwDAYIKoZIhvcNAwQFAIAgj12EiVeLdc7+78/pl1q7VHU8TGCUwuy4NoR1xzUMYXs=";

// mau data
//		String encryptedData = "MIIB2AYJKoZIhvcNAQcDoIIByTCCAcUCAQAxggGBMIIBfQIBADBlMFcxEzARBgoJkiaJk/IsZAEZFgNvcmcxGTAXBgoJkiaJk/IsZAEZFglkcmVzc2Jhcm4xJTAjBgNVBAMTHERyZXNzYmFybiBFbnRlcnByaXNlIFJvb3QgQ0ECCj9TNzoAAAAAA0IwDQYJKoZIhvcNAQEBBQAEggEAVhzECL/xpafIRlpivDWZ+My+wJQWwUVlElLh1FkHj/U2ltS9RF3y4FEwjYvGjnKEMhSphjDKRKGDtW05QgVPSD4MrktL5hvu3ZWFsgNZTiDToGKLP4becyxDlM0hMAT0ihFsBPCVbqYYTTvTJk6pA23kTotkwgmEcRY8aQLzC1wflebwKOPwAbtVCO3R+D7+eFK0r4PqasnFC4WKE51XoWjYMlkbMCrBO8KAvFMtkU3gm3e3pGHUVokUvFU/TQlznjMNR3BcHMLr7F69wtcbO+/GDTbAbW5m5k63wgvH5g26/EtwO9lPEJRZPSGx46rDpEHMP9Btyc1A+HcGS6QppjA7BgkqhkiG9w0BBwEwDAYIKoZIhvcNAwQFAIAgjMgNlZy7IbKBljyM33+sDEw0coEtc+Lw4XvrgvaBS4k=";
//		String encryptedData = "MIIB1gYJKoZIhvcNAQcDoIIBxzCCAcMCAQAxggF/MIIBewIBADBjMFUxEzARBgoJkiaJk/IsZAEZFgNjb20xGDAWBgoJkiaJk/IsZAEZFghNYXVyaWNlczEkMCIGA1UEAxMbTWF1cmljZXMgRW50ZXJwcmlzZSBSb290IENBAgoa58qkAAAAAABWMA0GCSqGSIb3DQEBAQUABIIBADS2ONGwjXFSZyAJwjIMACtJ5KFtNG27lD7RjQdmTA4rx3sYuJF1mD3uAo50fWISKz0nnPrU0iAXt7yw6knTSvMpFIM4oKysQsBdtNAU7nFCgSROuvzpO9gApQNw6Rhpv9Pk+GI6vstyoeRqxzP3rbyRzL8dEetKomk8xzh22/nfQLe3tGeqJvRxfrDHZrQ2kYhOWi96G3GFBAYY27SBx/HrcajX4gUcGopa0Us/nFc/o/KqydmRGeudtfCQvHZzG2hD96ZvcyIb1AwC4ZUZ0ftMiQv2R3Gm7TeZnhXl2qcvYLYkzMyH32X6euwPqiVa8w1S8wRqXgcI7Pb3ZkjvKZEwOwYJKoZIhvcNAQcBMAwGCCqGSIb3DQMEBQCAIH+p+DCOpuUYf7ELibFYpRhF0GPLFQXmJ9R3tbBPPoAN";
//		String encryptedData = "MIIB1gYJKoZIhvcNAQcDoIIBxzCCAcMCAQAxggF/MIIBewIBADBjMFUxEzARBgoJkiaJk/IsZAEZFgNjb20xGDAWBgoJkiaJk/IsZAEZFghNYXVyaWNlczEkMCIGA1UEAxMbTWF1cmljZXMgRW50ZXJwcmlzZSBSb290IENBAgoa58qkAAAAAABWMA0GCSqGSIb3DQEBAQUABIIBAA7HHO36mARAVXDD2ui4byLl5Y0jwNVv7SQq+KsafEXnlVz1gvUKW2r3G24hkMms3bt1wRmztBtzSuWRU4xp+1D4gv0haCQDiGYZ6uHpDDGW+fWvMfZY4o6O499NIkR9iYwSUbMeMAgv8YMZ6Oyy2x6485tU7p6FtvSwKYsOy59QkEuAwM4r9Y+VV8V69wWoEbLUV+/I7fdYo59HjMCH2g3GmQj0gTRdgyqqn2Yepn6yJ6Tb4qf7AbntDx6QfFYby5L1suyGOWoHR8UU8SH3Vtrb4MKd63SmLHPhW2f7W8LvtojXbRlc7OnQ6EpGV3EkMeuGhKbnWPhvZYn5eQlXwi0wOwYJKoZIhvcNAQcBMAwGCCqGSIb3DQMEBQCAIPzVZCTUugXcML76dK0U0ddoGzJ6tBq39PluTJV3A5H+";
//		String encryptedData = "MIIB2AYJKoZIhvcNAQcDoIIByTCCAcUCAQAxggGBMIIBfQIBADBlMFcxEzARBgoJkiaJk/IsZAEZFgNvcmcxGTAXBgoJkiaJk/IsZAEZFglkcmVzc2Jhcm4xJTAjBgNVBAMTHERyZXNzYmFybiBFbnRlcnByaXNlIFJvb3QgQ0ECCj9TNzoAAAAAA0IwDQYJKoZIhvcNAQEBBQAEggEAVhzECL/xpafIRlpivDWZ+My+wJQWwUVlElLh1FkHj/U2ltS9RF3y4FEwjYvGjnKEMhSphjDKRKGDtW05QgVPSD4MrktL5hvu3ZWFsgNZTiDToGKLP4becyxDlM0hMAT0ihFsBPCVbqYYTTvTJk6pA23kTotkwgmEcRY8aQLzC1wflebwKOPwAbtVCO3R+D7+eFK0r4PqasnFC4WKE51XoWjYMlkbMCrBO8KAvFMtkU3gm3e3pGHUVokUvFU/TQlznjMNR3BcHMLr7F69wtcbO+/GDTbAbW5m5k63wgvH5g26/EtwO9lPEJRZPSGx46rDpEHMP9Btyc1A+HcGS6QppjA7BgkqhkiG9w0BBwEwDAYIKoZIhvcNAwQFAIAgjMgNlZy7IbKBljyM33+sDEw0coEtc+Lw4XvrgvaBS4k=";
//		String encryptedData = "MIIB1gYJKoZIhvcNAQcDoIIBxzCCAcMCAQAxggF/MIIBewIBADBjMFUxEzARBgoJkiaJk/IsZAEZFgNjb20xGDAWBgoJkiaJk/IsZAEZFghNYXVyaWNlczEkMCIGA1UEAxMbTWF1cmljZXMgRW50ZXJwcmlzZSBSb290IENBAgoa58qkAAAAAABWMA0GCSqGSIb3DQEBAQUABIIBANKUjuVQORc9NsdmGFHP9zGV61DqupWl+m0xEnYJ8uoWDC6XiRV7My5zlmj4vqBQ9x+yIDm6+n9zWHkF5IlXRcM/y1FhUxwuEp1JeEuhbddbqbniSHVzOb+8eoUhvgK/ze2AGwqGbkxrHYl60wzXj0SHhA62cA/d8v7Qq22dsZoWp8rAYUyrpdNw2Dv69weTo0pnEa486J2+KhbV57/Ft0vwJLCLlKAUXs9LxzZGfifcFNo52LPj5oVQJPvRn20s7pGsQfG/JonotYW1AzCliQwdLFncF8/6sQV81O9WXHdFD7t9fbW+qbB1LqBCUw2Yu2mSPsyiEFjEQTRw5L8mAVMwOwYJKoZIhvcNAQcBMAwGCCqGSIb3DQMEBQCAIF7j1cGkGTHydOf88EOHnSPAhm+iA1Llgh6Zyza4Kwck";

		// This is a Sun internal class, it's best not to depend on it
		File f = new File("sample.txt");
		char[] cbuf = new char[(int) f.length()];
		FileReader fr = new FileReader(f);
		fr.read(cbuf);
		fr.close();
		BASE64Decoder decoder = new BASE64Decoder();
		encryptedData = new String(cbuf);
		ByteArrayInputStream bis = new ByteArrayInputStream(decoder.decodeBuffer(encryptedData));
		try {
			Encoding enc = Encoding.read(bis);
			System.out.println(enc);
			System.exit(0);
			String path = enc.findPrimitivePath("[0] 32 bytes");
			PrimitiveEncoding pkcs7CipherText = (PrimitiveEncoding) enc.select(path);
			KeyStore ks = KeyStore.getInstance("PKCS12");

			char[] password = "salero".toCharArray();
			ks.load(new FileInputStream("augkey_privkey.pfx"), password);

//			char[] password = "newpos".toCharArray();
//			ks.load(new FileInputStream("mau_newkey2.pfx"), password);

			Key privateKey = null;
			Key publicKey = null;
			Enumeration<String> aliases = ks.aliases();
			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();
				privateKey = ks.getKey(alias, password);
				publicKey = ks.getCertificate(alias).getPublicKey();
			}
//			System.out.println(privateKey);
//			System.out.println();
//			System.out.println(publicKey);
//			System.out.println();
			
			// test encryption with public key
			Cipher encrypt = Cipher.getInstance("RSA");
			encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] ciphertext = encrypt.doFinal("4387751111111111".getBytes());
			//System.out.println("Cipher text");
			//dump(ciphertext);
			//System.out.println();

			Cipher decrypt = Cipher.getInstance("RSA");
			decrypt.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] input = (byte[]) pkcs7CipherText.getContent();
			dump(input);
			System.exit(0);
			//input = ciphertext;
			//System.out.println("PKCS#7 cipher text");
			//dump(input);
			byte[] output = decrypt.doFinal(input);
			System.out.println("Decrypted size: " + output.length);
			System.out.println("Plain text:");
			dump(output);
//			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//			KeyPair pair = kpg.generateKeyPair();
//			RSAPublicKey pub = (RSAPublicKey) pair.getPublic();
//			RSAPrivateKey priv = (RSAPrivateKey) pair.getPrivate();
//			System.out.println(priv.getModulus());
//			System.out.println(priv.getPrivateExponent());
//			Encoding key = getEncapsulated(priv.getEncoded());
//			System.out.println(key);
//			System.out.println(getPrivateKey(key));
		} finally {
			bis.close();
		}
	}

	private static void dump(byte[] output) {
		int block = 32;
		for (int i = 0; i < output.length; i+=block) {
			int n = i + block;
			if (n > output.length) {
				n = output.length;
			}
			for (int j = i; j < n; j++) {
				System.out.printf("%02X ", output[j]);
			}
			for (int j = i; j < n; j++) {
				char c = (char) output[j];
				if (c < ' ' || c > '~') {
					c = '.';
				}
				System.out.printf("%c", c);
			}
			System.out.println();
		}
	}

	private static Encoding getPublicKey(Encoding key) throws IOException {
		Encoding sel = key.select("1");
		PrimitiveEncoding p = (PrimitiveEncoding) sel;
		byte[] data = (byte[]) p.getContent();
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		// Apparently RSA public keys include a leading 0
		bis.read();
		return Encoding.read(bis);
	}

	private static Encoding getPrivateKey(Encoding key) throws IOException {
		Encoding sel = key.select("2");
		PrimitiveEncoding p = (PrimitiveEncoding) sel;
		byte[] data = (byte[]) p.getContent();
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		// Apparently RSA private keys don't include a leading 0
		System.out.println("Leading byte: " + data[0]);
		return Encoding.read(bis);
	}

	private static Encoding getEncapsulated(byte[] data) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		return Encoding.read(bis);
	}
}
