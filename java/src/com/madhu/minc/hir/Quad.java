
/*
 * Created on Nov 11, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

import java.util.HashMap;

import com.madhu.minc.lir.CodeGenerator;

/**
 * @author Madhu Siddalingaiah
 *
 */
public abstract class Quad {
	private boolean deadCode;
	private Block block;
	private int address;

	public Quad() {
		deadCode = false;
	}

	public abstract Operand[] getRefs();
	public abstract Variable getDef();

	public abstract void simplifyOperands();

	/**
	 * @param variableMap
	 */
	public abstract void mapVariables(HashMap variableMap);
	public abstract void generateCode(CodeGenerator cg);

	/**
	 * @param block
	 */
	public void setBlock(Block block) {
		this.block = block;
	}

	/**
	 * @return
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * @return
	 */
	public boolean isDeadCode() {
		return deadCode;
	}

	/**
	 * @param b
	 */
	public void setDeadCode(boolean b) {
		deadCode = b;
	}

	/**
	 * @return
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * @param i
	 */
	public void setAddress(int addr) {
		address = addr;
	}
}
