
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created May 26, 2006
 */

package com.madhu.minc.s2;

public class Variable {
	public static final int REGISTER = 1;
	public static final int STACK = 2;
	public static final int HEAP = 3;

	private int storageClass;
	private String name;
	private int address;
	private int assignAddress;
	private int lastUseAddres;

	public Variable(int storageClass, String name, int address) {
		this.storageClass = storageClass;
		this.name = name;
		this.address = address;
	}

	/**
	 * @return Returns the address.
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * @param address The address to set.
	 */
	public void setAddress(int address) {
		this.address = address;
	}

	/**
	 * @return Returns the assignAddress.
	 */
	public int getAssignAddress() {
		return assignAddress;
	}

	/**
	 * @param assignAddress The assignAddress to set.
	 */
	public void setAssignAddress(int assignAddress) {
		this.assignAddress = assignAddress;
	}

	/**
	 * @return Returns the lastUseAddres.
	 */
	public int getLastUseAddres() {
		return lastUseAddres;
	}

	/**
	 * @param lastUseAddres The lastUseAddres to set.
	 */
	public void setLastUseAddres(int lastUseAddres) {
		this.lastUseAddres = lastUseAddres;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the storageClass.
	 */
	public int getStorageClass() {
		return storageClass;
	}

	/**
	 * @param storageClass The storageClass to set.
	 */
	public void setStorageClass(int storageClass) {
		this.storageClass = storageClass;
	}

	public String toString() {
		return name;
	}
}
