
/*
 * $Id: ControlFlowGraph.java,v 1.2 2008/01/10 04:31:54 madhu Exp $
 */
package com.madhu.minc.hir;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Madhu Siddalingaiah
 *
 * A class containing a set of basic blocks comprising a flow graph.
 * This class implements a simple, fast dominance algorithm as
 * described by Keith D. Cooper, Timothy J. Harvey, and Ken Kennedy
 * Rice University Houston, Texas.
 * 
 * The paper can be found at this URL:
 * http://www.hipersoft.rice.edu/grads/publications/dom14.pdf
 */
public class ControlFlowGraph {
	private ArrayList postOrderList;

	private Block startBlock;
	private Block endBlock;

	public ControlFlowGraph() {
		this(new Block());
	}

	public ControlFlowGraph(Block startBlock) {
		this.startBlock = startBlock;
		this.endBlock = startBlock;
	}

	public void add(BranchQuad quad) {
		endBlock.add(quad);
		endBlock.addSuccessor(quad.getBranchBlock());
	}

	public void add(ControlFlowGraph cfg, boolean isSuccessor) {
		endBlock.setNextBlock(cfg.getStartBlock());
		if (isSuccessor) {
			endBlock.addSuccessor(cfg.getStartBlock());
			endBlock = cfg.getEndBlock();
		}
	}

	public void add(Block block, boolean isSuccessor) {
		endBlock.setNextBlock(block);
		if (isSuccessor) {
			endBlock.addSuccessor(block);
			endBlock = block;
		}
	}

	public void computeDominance() {
		postOrderList = new ArrayList();
		startBlock.computePostOrder(postOrderList);
		doComputeDominance();
		computeDominanceFrontier();
		computeDominatedBlocks();
	}

	/*
		for all nodes, b // initialize the dominators array
			doms[b] = Undefined
		doms[start_node] = start_node
		Changed = true
		while (Changed)
			Changed = false
			for all nodes, b, in reverse postorder (except start node)
				new_idom = first (processed) predecessor of b // (pick one)
				for all other predecessors, p, of b
					if doms[p] != Undefined // i.e., if doms[p] already calculated
						new_idom = intersect(p, new_idom)
			if doms[b] != new_idom
				doms[b] = new_idom
				Changed = true
	*/
	private void doComputeDominance() {
		// This is critical, must be done in reverse postorder
		startBlock.setIDominator(startBlock);
		boolean changed = true;
		while (changed) {
			changed = false;
			int i = postOrderList.size() - 1; // skip startBlock
			while (i >= 0) {
				Node b = (Node) postOrderList.get(i--);
				if (b == startBlock) {
					continue;
				}
				Iterator ip = b.getPredecessors().iterator();
				if (!ip.hasNext()) {
					throw new AssertionError(b + " has no predecessors!");
				}
				Node newIdom = (Node) ip.next();
				while (newIdom.getIDominator() == null && ip.hasNext()) {
					newIdom = (Node) ip.next();
				}
				if (newIdom.getIDominator() == null) {
					throw new AssertionError(newIdom + " has no dominator!");
				}
				while (ip.hasNext()) {
					Node p = (Node) ip.next();
					if (p.getIDominator() != null) {
						newIdom = intersect(p, newIdom);
					}
				}
				if (b.getIDominator() != newIdom) {
					b.setIDominator(newIdom);
					changed = true;
				}
			}
		}
		startBlock.setIDominator(null);
	}

	/**
	 *
	 * @param p
	 * @param newIdom
	 * @return
	 */
	/*
		function intersect(b1, b2) returns node
			finger1 = b1
			finger2 = b2
			while (finger1 != finger2)
				while (finger1 < finger2)
					finger1 = doms[finger1]
				while (finger2 < finger1)
					finger2 = doms[finger2]
			return finger1
	*/
	private Node intersect(Node b1, Node b2) {
		while (b1 != b2) {
			while (b1.getPostOrderNumber() < b2.getPostOrderNumber()) {
				b1 = b1.getIDominator();
			}
			while (b2.getPostOrderNumber() < b1.getPostOrderNumber()) {
				b2 = b2.getIDominator();
			}
		}
		return b1;
	}

	/*
		for all nodes, b
			if the number of predecessors of b >= 2
				for all predecessors, p, of b
					runner = p
					while runner != doms[b]
						add b to runner’s dominance frontier set
						runner = doms[runner]
	*/
	private void computeDominanceFrontier() {
		int n = postOrderList.size();
		for (int i=0; i<n; i+=1) {
			Node b = (Node) postOrderList.get(i);
			List predList = b.getPredecessors();
			if (predList.size() >= 2) {
				Iterator it = predList.iterator();
				while (it.hasNext()) {
					Node runner = (Node) it.next();
					while (runner != b.getIDominator()) {
						runner.addDominanceFrontier(b);
						runner = runner.getIDominator();
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	private void computeDominatedBlocks() {
		int n = postOrderList.size();
		for (int i=0; i<n; i+=1) {
			Node b = (Node) postOrderList.get(i);
			Node idom = b.getIDominator();
			if (idom != null) {
				idom.addDominatedBlock(b);
				idom = idom.getIDominator();
			}
		}
	}

	public void printBlocks() {
		int n = postOrderList.size();
		for (int i=n-1; i >= 0; i-=1) {
			Node b = (Node) postOrderList.get(i);
			System.out.print(b.getName() + ".idom: " + b.getIDominator());
			System.out.print("; DF:");
			Iterator it = b.getDominanceFrontier().iterator();
			while (it.hasNext()) {
				System.out.print(" " + it.next());
			}
			System.out.print("; Dom blocks:");
			it = b.getDominatedBlocks().iterator();
			while (it.hasNext()) {
				System.out.print(" " + it.next());
			}
			System.out.println();
		}
	}

	private static Block[] createLoopCFG() {
		Block[] b = new Block[3];
		for (int i=0; i<b.length; i+=1) {
			b[i] = new Block("B" + i);
		}
		b[0].addSuccessor(b[1]);
		b[1].addSuccessor(b[2]);
		b[2].addSuccessor(b[1]);
		return b;
	}

	private static Block[] createIfCFG() {
		Block[] b = new Block[4];
		for (int i=0; i<b.length; i+=1) {
			b[i] = new Block("B" + i);
		}
		b[0].addSuccessor(b[1]);
		b[0].addSuccessor(b[2]);
		b[1].addSuccessor(b[3]);
		b[2].addSuccessor(b[3]);
		return b;
	}

	private static Block[] createLoopWithIfCFG() {
		Block[] b = new Block[5];
		for (int i=0; i<b.length; i+=1) {
			b[i] = new Block("B" + (i+1));
		}

		b[0].addSuccessor(b[4]);
		b[4].addSuccessor(b[1]);

		b[1].addSuccessor(b[2]);
		b[1].addSuccessor(b[3]);

		b[2].addSuccessor(b[4]);
		b[3].addSuccessor(b[4]);
		return b;
	}

	private static Block[] createCFG1() {
		Block[] b = new Block[5];
		for (int i=0; i<b.length; i+=1) {
			b[i] = new Block("B" + (i+1));
		}
		b[0].addSuccessor(b[1]);
		b[1].addSuccessor(b[0]);
		b[2].addSuccessor(b[1]);
		b[3].addSuccessor(b[0]);
		b[4].addSuccessor(b[2]);
		b[4].addSuccessor(b[3]);
		return b;
	}

/*
     A
    / \
    B C
    | |
    D E
    \ /
     F
 */
	private static Block[] createToyCFG() {
		Block[] b = new Block[6];
		for (int i=0; i<b.length; i+=1) {
			b[i] = new Block(new String("" + (char) ('A' + i)));
		}
		b[0].addSuccessor(b[1]);
		b[0].addSuccessor(b[2]);
		b[1].addSuccessor(b[3]);
		b[2].addSuccessor(b[4]);
		b[3].addSuccessor(b[5]);
		b[4].addSuccessor(b[5]);
		return b;
	}

	public static void main(String[] args) {
		System.out.println("Loop CFG");
		Block[] b = createLoopCFG();
		ControlFlowGraph cfg = new ControlFlowGraph(b[0]);
		cfg.computeDominance();
		cfg.printBlocks();
		
		System.out.println("\nIf CFG");
		b = createIfCFG();
		cfg = new ControlFlowGraph(b[0]);
		cfg.computeDominance();
		cfg.printBlocks();

		System.out.println("\nLoop with if CFG");
		b = createLoopWithIfCFG();
		cfg = new ControlFlowGraph(b[0]);
		cfg.computeDominance();
		cfg.printBlocks();

		System.out.println("\nCFG1");
		b = createCFG1();
		cfg = new ControlFlowGraph(b[4]);
		cfg.computeDominance();
		cfg.printBlocks();

		System.out.println("\nToy CFG");
		b = createToyCFG();
		cfg = new ControlFlowGraph(b[0]);
		cfg.computeDominance();
		cfg.printBlocks();
	}

	/**
	 * @param b
	 */
	private static void printPredecessors(Node[] b) {
		for (int i=0; i < b.length; i+=1) {
			b[i].printPredecessors();
		}
	}

	/**
	 * @param b
	 */
	private static void printBlocks(Node[] b) {
		for (int i=0; i < b.length; i+=1) {
			System.out.print(b[i].getName() + ".idom: " + b[i].getIDominator());
			System.out.print("; DF:");
			Iterator it = b[i].getDominanceFrontier().iterator();
			while (it.hasNext()) {
				System.out.print(" " + it.next());
			}
			System.out.print("; Dom blocks:");
			it = b[i].getDominatedBlocks().iterator();
			while (it.hasNext()) {
				System.out.print(" " + it.next());
			}
			System.out.println();
		}
	}

	/**
	 * @return
	 */
	public Block getEndBlock() {
		return endBlock;
	}

	/**
	 * @return
	 */
	public Block getStartBlock() {
		return startBlock;
	}

	/**
	 * @param block
	 */
	public void setEndBlock(Block block) {
		endBlock = block;
	}

	/**
	 * @param block
	 */
	public void setStartBlock(Block block) {
		startBlock = block;
	}
}
