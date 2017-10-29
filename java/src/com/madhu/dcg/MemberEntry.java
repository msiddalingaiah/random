
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

public abstract class MemberEntry extends ConstantEntry {
	private int classIndex;
	private int nameAndTypeIndex;
	private String className;
	private String memberName;
	private String descriptor;

	public MemberEntry(int aClassIndex, int aNameAndTypeIndex) {
		classIndex = aClassIndex;
		nameAndTypeIndex = aNameAndTypeIndex;
		className = Integer.toString(aClassIndex);
		memberName = Integer.toString(aNameAndTypeIndex);
		descriptor = Integer.toString(aNameAndTypeIndex);
	}

	public void resolve(ConstantPool constants) {
		if (!(constants.getEntry(classIndex) instanceof ClassEntry)) throw
			new ClassFormatError (getType() + "Entry does not point to a ClassEntry!");
		if (!(constants.getEntry(nameAndTypeIndex) instanceof NameAndTypeEntry)) throw
			new ClassFormatError (getType() + "Entry does not point to a NameAndTypeEntry!");

		Utf8Entry utf8;
		ClassEntry ce = (ClassEntry) constants.getEntry(classIndex);
		utf8 = (Utf8Entry) constants.getEntry(ce.getValue());
		className = utf8.getValue();
		NameAndTypeEntry nat = (NameAndTypeEntry) constants.getEntry(nameAndTypeIndex);
		utf8 = (Utf8Entry) constants.getEntry(nat.getNameIndex());
		memberName = utf8.getValue();
		utf8 = (Utf8Entry) constants.getEntry(nat.getDescriptorIndex());
		descriptor = utf8.getValue();
	}

	public String getClassName() {
		return className;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public abstract String getType();

	public String toString() {
		return getType() + ": " + getClassName() + "." +
			getMemberName() + " " + getDescriptor();
	}

	/**
	 * @return
	 */
	public int getClassIndex() {
		return classIndex;
	}

	/**
	 * @return
	 */
	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}
}
