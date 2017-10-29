
/*
 * $Id: LowLevelIR.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc.lir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.madhu.minc.hir.Block;
import com.madhu.minc.hir.HighLevelIR;
import com.madhu.minc.hir.Operand;
import com.madhu.minc.hir.PhiAssignQuad;
import com.madhu.minc.hir.PhiMoveQuad;
import com.madhu.minc.hir.Quad;
import com.madhu.minc.hir.Variable;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class LowLevelIR {
	private List rtl;
	private HashMap variableMap;
	private ArrayList variableList;
	private HighLevelIR hir;

	public LowLevelIR(HighLevelIR hir) {
		this.hir = hir;
		rtl = new ArrayList();
		variableMap = new HashMap();
		variableList = new ArrayList();
		deconstrucSSA(hir);
		createRTL(hir);
	}

	/**
	 * @return
	 */
	public List getRTL() {
		return rtl;
	}

	private void deconstrucSSA(HighLevelIR hir) {
		Block b = hir.getControlFlowGraph().getStartBlock();
		while (b != null) {
			Iterator it = b.getQuads().iterator();
			while (it.hasNext()) {
				Quad q = (Quad) it.next();
				if (q instanceof PhiAssignQuad) {
					PhiAssignQuad paq = (PhiAssignQuad) q;
					Variable lhs = paq.getLHS();
					List phis = paq.getPhiOperand().getRefs();
					Iterator pit = phis.iterator();
					while (pit.hasNext()) {
						Variable rhs = (Variable) pit.next();
						Block ab = rhs.getAssignQuad().getBlock();
						PhiMoveQuad pmq = new PhiMoveQuad(lhs, rhs);
						Operand phiRHS = pmq.getRHS();
						// Coalesce phi moves
						if (phiRHS instanceof Variable) {
							Variable v = (Variable) variableMap.get(phiRHS);
							if (v == null) {
								variableMap.put(phiRHS, lhs);
								variableMap.put(lhs, lhs);
							} else {
								variableMap.put(lhs, v);
							}
						} else {
							ab.add(pmq);
						}
					}
				} else {
					break;
				}
			}
			b = b.getNextBlock();
		}
	}

	private void createRTL(HighLevelIR hir) {
		int addr = 0;
		Block b = hir.getControlFlowGraph().getStartBlock();
		while (b != null) {
			b.setAddress(addr);
			List quads = b.getQuads();
			int n = quads.size();
			for (int i=0; i<n; i+=1) {
				Quad q = (Quad) quads.get(i);
				if (!(q instanceof PhiAssignQuad)) {
					q.mapVariables(variableMap);
					if (!q.isDeadCode()) {
						q.setAddress(addr++);
						Variable v = q.getDef();
						if (v != null) {
							v.updateLiveRange(addr);
							if (!variableList.contains(v)) {
								variableList.add(v);
							}
						}
						Operand[] refs = q.getRefs();
						if (refs != null) {
							for (int j=0; j<refs.length; j+=1) {
								if (refs[j] instanceof Variable) {
									v = (Variable) refs[j];
									v.updateLiveRange(addr-1);
									if (!variableList.contains(v)) {
										variableList.add(v);
									}
								}
							}
						}
						rtl.add(q);
					}
				}
			}
			b = b.getNextBlock();
		}
		Collections.sort(variableList);
	}
	
	public void printRTL() {
		System.out.println("\nRTL\n");
		Iterator it = rtl.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	/**
	 * @return
	 */
	public ArrayList getVariableList() {
		return variableList;
	}

	/**
	 * @return
	 */
	public List getMethodArgs() {
		return hir.getMethodArgs();
	}
}
