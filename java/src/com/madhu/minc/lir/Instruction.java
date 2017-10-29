
/*
 * Created on Apr 23, 2005 12:07:25 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.lir;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public abstract class Instruction {
	public static int REGISTER = 1;
	public static int STACK = 2;
	public static int IMMEDIATE = 3;

	public static int RRR_MODE = (REGISTER << 16) | (REGISTER << 8) | (REGISTER << 0);
	public static int RRS_MODE = (REGISTER << 16) | (REGISTER << 8) | (STACK << 0);
	public static int RSR_MODE = (REGISTER << 16) | (STACK << 8) | (REGISTER << 0);
	public static int RSS_MODE = (REGISTER << 16) | (STACK << 8) | (STACK << 0);
	public static int RRI_MODE = (REGISTER << 16) | (REGISTER << 8) | (IMMEDIATE << 0);
	public static int RIR_MODE = (REGISTER << 16) | (IMMEDIATE << 8) | (REGISTER << 0);

	public static int SRR_MODE = (STACK << 16) | (REGISTER << 8) | (REGISTER << 0);
	public static int SRS_MODE = (STACK << 16) | (REGISTER << 8) | (STACK << 0);
	public static int SSR_MODE = (STACK << 16) | (STACK << 8) | (REGISTER << 0);
	public static int SSS_MODE = (STACK << 16) | (STACK << 8) | (STACK << 0);
	public static int SRI_MODE = (STACK << 16) | (REGISTER << 8) | (IMMEDIATE << 0);
	public static int SIR_MODE = (STACK << 16) | (IMMEDIATE << 8) | (REGISTER << 0);

	public static int RR_MODE = (REGISTER << 16) | (REGISTER << 8);
	public static int RS_MODE = (REGISTER << 16) | (STACK << 8);
	public static int RI_MODE = (REGISTER << 16) | (IMMEDIATE << 8);
	public static int SR_MODE = (STACK << 16) | (REGISTER << 8);
	public static int SS_MODE = (STACK << 16) | (STACK << 8);
	public static int SI_MODE = (STACK << 16) | (IMMEDIATE << 8);

	public static int R_MODE = (REGISTER << 16);
	public static int S_MODE = (STACK << 16);
	public static int I_MODE = (IMMEDIATE << 16);

	private int opCode;
	private int mode;
	protected int[] operands;
	
	public Instruction() {
		operands = new int[3];
	}

	/**
	 * @return
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * @return
	 */
	public int getOpCode() {
		return opCode;
	}

	/**
	 * @param i
	 */
	public void setMode(int i) {
		mode = i;
	}

	/**
	 * @param i
	 */
	public void setOpCode(int i) {
		opCode = i;
	}
	
	public int[] getOperands() {
		return operands;
	}
}
