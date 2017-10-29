
/*
 * Created on Nov 11, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

import com.madhu.minc.lir.Location;
import com.madhu.minc.lir.StackLocation;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class Variable extends Operand implements Comparable {
	private AssignQuad assignQuad;
	private String name;
	private int ssaNumber;
	private int startAddress;
	private int endAddress;
	private Location location;
	
	/**
	 * @param name
	 * @param location
	 */
	public Variable(String name, StackLocation location) {
		this(name);
		this.location = location;
	}

	public Variable(String name) {
		this(name, -1);
	}
	
	/**
	 * @param string
	 * @param i
	 */
	public Variable(String name, int number) {
		this.name = name;
		ssaNumber = number;
		startAddress = Integer.MAX_VALUE;
		endAddress = Integer.MIN_VALUE;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	public boolean equals(Object other) {
		if (other instanceof Variable) {
			Variable v = (Variable) other;
			return ssaNumber == v.getSSANumber() &&
				name.equals(v.getName());
		}
		return false;
	}

	/**
	 * @return
	 */
	public int getSSANumber() {
		return ssaNumber;
	}

	/**
	 * @param i
	 */
	public void setSSANumber(int number) {
		ssaNumber = number;
	}

	public String toString() {
		if (ssaNumber < 0) {
			return name;
		} else {
			return name + '_' + ssaNumber;
		}
	}

	/**
	 * @param quad
	 */
	public void setAssignQuad(AssignQuad quad) {
		this.assignQuad = quad;
	}

	/**
	 * @return
	 */
	public AssignQuad getAssignQuad() {
		return assignQuad;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Operand#simplify()
	 */
	public Operand simplify() {
		Operand operand = assignQuad.propagateRHS(this);
		return operand;
	}

	/**
	 * @param address
	 */
	public void updateLiveRange(int address) {
		if (address < startAddress) {
			startAddress = address;
		}
		if (address > endAddress) {
			endAddress = address;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		Variable v = (Variable) o;
		return startAddress - v.getEndAddress();
	}

	/**
	 * @return
	 */
	public int getStartAddress() {
		return startAddress;
	}

	/**
	 * @return
	 */
	public int getEndAddress() {
		return endAddress;
	}

	/**
	 * @return
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
}
