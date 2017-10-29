
/*
 * $Id: Block.java,v 1.1 2008/04/30 04:23:47 madhu Exp $
 */
package com.madhu.minc.sgc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Block extends Node {
	private static int blockIndex = 1;

	/**
	 * The next block in code generation order.
	 * This block might not be a logical successor.
	 */
	private Block nextBlock;

	/**
	 * The trees in this block
	 */
	private List<SGCTree> trees;
	private ArrayList defList;
	private int address;

	public Block() {
		this("B" + Integer.toString(blockIndex++));
	}

	public Block(String name) {
		super(name);
		trees = new ArrayList<SGCTree>();
		defList = new ArrayList();
	}

//	public void add(Quad q) {
//		if (q instanceof BranchQuad) {
//			quads.add(q);
//		} else {
//			int n = quads.size();
//			if (n > 0 && quads.get(n-1) instanceof BranchQuad) {
//				quads.add(n-1, q);
//			} else {
//				quads.add(q);
//			}
//		}
//		addDef(q);
//		q.setBlock(this);
//	}
//
//	public void add(PhiAssignQuad paq) {
//		if (!quads.contains(paq)) {
//			addDef(paq);
//			quads.add(0, paq);
//			paq.setBlock(this);
//		}
//	}
//
//	private void addDef(Quad q) {
//		Operand def = q.getDef();
//		if (def instanceof Variable &&
//			!(def instanceof Temporary) &&
//			!defList.contains(def)) {
//			
//			defList.add(def);
//		}
//	}

	public void optimizeSSA() {
//		for (int i = 0; i < quads.size(); i++) {
//			Quad q = (Quad) quads.get(i);
//			q.simplifyOperands();
//		}
	}

	/**
	 * @return
	 */
	public List<SGCTree> getTrees() {
		return trees;
	}

	/**
	 * @return
	 */
	public Block getNextBlock() {
		return nextBlock;
	}

	/**
	 * @param block
	 */
	public void setNextBlock(Block block) {
		nextBlock = block;
	}

	/**
	 * @return
	 */
	public ArrayList getDefList() {
		return defList;
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
