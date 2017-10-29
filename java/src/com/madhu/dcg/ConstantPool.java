
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

import java.util.ArrayList;

public class ConstantPool {
	private ArrayList entries;

	public ConstantPool() {
		entries = new ArrayList();
		entries.add(new NullEntry());
	}

	public void add(ConstantEntry entry) {
		entries.add(entry);
	}

	public int size() {
		return entries.size();
	}

	public int addUtf8Entry(String string) {
		entries.add(new Utf8Entry(string));
		return entries.size() - 1;
	}

	public int addClassEntry(String name) {
		int index = entries.size();
		entries.add(new Utf8Entry(name));
		entries.add(new ClassEntry(index));
		return entries.size() - 1;
	}

	public int addNameAndTypeEntry(String name, String type) {
		int nameIndex = entries.size();
		entries.add(new Utf8Entry(name));
		int typeIndex = entries.size();
		entries.add(new Utf8Entry(type));
		entries.add(new NameAndTypeEntry(nameIndex, typeIndex));
		return entries.size() - 1;
	}

	public int addFieldRefEntry(String className, String fieldName,
		String type) {
		int classIndex = addClassEntry(className);
		int nameIndex = addNameAndTypeEntry(fieldName, type);
		entries.add(new FieldRefEntry(classIndex, nameIndex));
		return entries.size() - 1;
	}

	public int addMethodRefEntry(String className, String methodName,
		String type) {
		int classIndex = addClassEntry(className);
		int nameIndex = addNameAndTypeEntry(methodName, type);
		entries.add(new MethodRefEntry(classIndex, nameIndex));
		return entries.size() - 1;
	}

	public int addInterfaceMethodRefEntry(String className, String methodName,
		String type) {
		int classIndex = addClassEntry(className);
		int nameIndex = addNameAndTypeEntry(methodName, type);
		entries.add(new InterfaceMethodRefEntry(classIndex, nameIndex));
		return entries.size() - 1;
	}

	public ConstantEntry getEntry(int i) {
		return (ConstantEntry) entries.get(i);
	}
}
