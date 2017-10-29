
package com.madhu.minc.sgc;

/*
 * Created on Apr 24, 2008 at 8:17:59 PM
 */
public class Variable {
	private String name;
	private int type;
	private int startAddress;
	private int endAddress;
	private Register register;

	public Variable(String name, int type) {
		this.name = name;
		this.type = type;
		startAddress = -1;
		endAddress = -1;
	}

	public int getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(int startAdress) {
		this.startAddress = startAdress;
	}

	public int getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(int endAddress) {
		this.endAddress = endAddress;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return String.format("%s: %d-%d", name, startAddress, endAddress);
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}
	
	public boolean isTemporary() {
		return endAddress < 0;
	}
}
