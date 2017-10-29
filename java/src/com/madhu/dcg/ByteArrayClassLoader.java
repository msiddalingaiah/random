
/*
 * Copyright 1996-1998 Madhu Siddalingaiah
 * All rights reserved.
 * mailto:madhu@madhu.com http://www.madhu.com
 *
 * This software is the confidential and proprietary information
 * of Madhu Siddalingaiah ("Confidential Information").
 * Unauthorized duplication, modification, or distribution of this
 * software, its algorithms, documentation, or related intellectual
 * property without expressed written consent by Madhu Siddalingaiah
 * is strictly prohibited.
 */
package com.madhu.dcg;

import java.util.HashMap;

public class ByteArrayClassLoader extends ClassLoader {
	private HashMap classData;

	public ByteArrayClassLoader() {
		classData = new HashMap();
	}

	public void addClassData(String name, byte[] data) {
		classData.put(name, data);
	}

	public Class findClass(String name) {
		// byte[] b = loadClassData(name);
		byte[] b = (byte[]) classData.get(name);
		return defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) {
		return (byte[]) classData.get(name);
	}
}
