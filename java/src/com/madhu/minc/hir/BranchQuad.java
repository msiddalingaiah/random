
/*
 * Created on Nov 15, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

/**
 * @author Madhu Siddalingaiah
 *
 */
public abstract class BranchQuad extends Quad {
	private Block branchBlock;

	/**
	 * @param afterIfBlock
	 */
	public BranchQuad(Block branchBlock) {
		this.branchBlock = branchBlock;
	}

	/**
	 * @return
	 */
	public Block getBranchBlock() {
		return branchBlock;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Quad#getDef()
	 */
	public Variable getDef() {
		return null;
	}
}
