
/*
 * $Id: HighLevelIR.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc.hir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class HighLevelIR {
	private List methodArgs;
	private ControlFlowGraph cfg;
	private HashMap renumberMap;

	public HighLevelIR() {
	}

	/**
	 * @return
	 */
	public ControlFlowGraph getControlFlowGraph() {
		return cfg;
	}

	/**
	 * @param graph
	 */
	public void setControlFlowGraph(ControlFlowGraph graph) {
		cfg = graph;
	}

	/**
	 * 
	 */
	public void constructSSA() {
		cfg.computeDominance();
		placePhiFunctions();
		renumberMap = new HashMap();
		renameVariables(cfg.getStartBlock());
	}

	public void optimizeSSA() {
		Block b = cfg.getStartBlock();
		while (b != null) {
			b.optimizeSSA();
			b = b.getNextBlock();
		}
	}

	private void placePhiFunctions() {
		Block b = cfg.getStartBlock();
		while (b != null) {
			ArrayList defList = b.getDefList();
			for (int i=0; i<defList.size(); i+=1) {
				Variable def = (Variable) defList.get(i);
				List df = b.getDominanceFrontier();
				Iterator it = df.iterator();
				while (it.hasNext()) {
					Block dfb = (Block) it.next();
					dfb.add(new PhiAssignQuad(def));
				}
			}
			b = b.getNextBlock();
		}
	}
	
	/**
	 * @param block
	 */
	private void renameVariables(Block block) {
		doRenameVariables(block);
		List list = block.getSuccessors();
		int n = list.size();
		for (int i = 0; i < n; i++) {
			Block b = (Block) list.get(i);
			rewritePhiParams(b);
		}
		Iterator it = block.getDominatedBlocks().iterator();
		while (it.hasNext()) {
			Block b = (Block) it.next();
			if (b != block) {
				renameVariables(b);
			}
		}
		popVariables(block);
	}

	/**
	 * @param block
	 */
	private void doRenameVariables(Block block) {
		Iterator it = block.getQuads().iterator();
		while (it.hasNext()) {
			Quad q = (Quad) it.next();
			Operand[] refs = q.getRefs();
			if (refs != null) {
				int n = refs.length;
				for (int i=0; i<n; i+=1) {
					SSAStack st = getStack(refs[i]);
					if (st != null) {
						refs[i] = st.peek();
					}
				}
			}
			if (q instanceof AssignQuad) {
				AssignQuad aq = (AssignQuad) q;
				SSAStack st = getStack(aq.getLHS());
				aq.setLHS(st.getNewVariable());
			}
		}
	}

	/**
	 * @param block
	 */
	private void rewritePhiParams(Block block) {
		if (block == null) {
			return;
		}
		Iterator it = block.getQuads().iterator();
		while (it.hasNext()) {
			Quad q = (Quad) it.next();
			if (q instanceof PhiAssignQuad) {
				PhiAssignQuad aq = (PhiAssignQuad) q;
				SSAStack st = getStack(aq.getLHS());
				Variable var = st.peek();
				if (var != null) {
					PhiOperand phi = aq.getPhiOperand();
					phi.addRef(var);
				}
			}
		}
	}

	/**
	 * @param block
	 */
	private void popVariables(Block block) {
		Iterator it = block.getQuads().iterator();
		while (it.hasNext()) {
			Quad q = (Quad) it.next();
			if (q instanceof AssignQuad) {
				AssignQuad aq = (AssignQuad) q;
				SSAStack st = getStack(aq.getLHS());
				st.pop();
			}
		}
	}

	/**
	 * @param operand
	 * @return
	 */
	private SSAStack getStack(Operand operand) {
		if (operand instanceof Variable) {
			return getStack((Variable) operand);
		}
		return null;
	}

	private SSAStack getStack(Variable var) {
		String name = var.getName();
		SSAStack st = (SSAStack) renumberMap.get(name);
		if (st == null) {
			st = new SSAStack(name);
			renumberMap.put(name, st);
			if (var instanceof MethodArgument) {
				st.addMethodArgument((MethodArgument) var);
			}
		}
		return st;
	}

	public void printHIR() {
		System.out.println("Dominance:");
		cfg.printBlocks();
		System.out.println("\nQuads:");
		Block b = cfg.getStartBlock();
		while (b != null) {
			System.out.println("\n<<" + b.getName() + ">>");
			Iterator it = b.getQuads().iterator();
			while (it.hasNext()) {
				String text = it.next().toString();
				if (text != null) {
					System.out.println(text);
				}
			}
			b = b.getNextBlock();
		}
	}

	/**
	 * @param methodArgs
	 */
	public void setMethodArgs(List methodArgs) {
		this.methodArgs = methodArgs;
	}

	/**
	 * @return
	 */
	public List getMethodArgs() {
		return methodArgs;
	}
}
