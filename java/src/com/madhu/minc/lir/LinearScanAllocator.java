
/*
 * Created on Dec 7, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.lir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import com.madhu.minc.hir.MethodArgument;
import com.madhu.minc.hir.Variable;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class LinearScanAllocator extends RegisterAllocator {
	private List liveRanges;
	private ArrayList active;
	private EndPointComparator endPointComparator;
	private ArrayList spilledVariableList;
	private Stack registerPool;
	private int spillOffset;

	/**
	 * 
	 */
	public LinearScanAllocator(Register[] regs) {
		this.active = new ArrayList();
		this.endPointComparator = new EndPointComparator();
		this.spilledVariableList = new ArrayList();
		registerPool = new Stack();
		int nRegs = regs.length;
		for (int i=0; i<nRegs; i+=1) {
			registerPool.push(regs[i]);
		}
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.RegisterAllocator#allocate(java.util.List)
	 */
	public void allocate(List variables) {
		liveRanges = variables;
		int n = liveRanges.size();
		for (int i=0; i<n; i+=1) {
			Variable lr = (Variable) liveRanges.get(i);
			if (!(lr instanceof MethodArgument)) {
				expireOldRange(lr);
				if (registerPool.size() <= 0) {
					spillRange(lr);
				} else {
					Register reg = (Register) registerPool.pop();
					lr.setLocation(reg);
					active.add(lr);
					Collections.sort(active, endPointComparator);
				}
			}
		}
	}

	/**
	 * @param lr
	 */
	private void expireOldRange(Variable lr) {
		for (int i=0; i<active.size(); i+=1) {
			Variable l = (Variable) active.get(i);
			if (l.getEndAddress() >= lr.getStartAddress()) {
				return;
			}
			active.remove(l);
			registerPool.push(l.getLocation());
		}
	}

	/**
	 * @param lr
	 */
	private void spillRange(Variable lr) {
		Variable spill = (Variable) active.get(active.size() - 1);
		if (spill.getEndAddress() > lr.getEndAddress()) {
			lr.setLocation(spill.getLocation());
			spill.setLocation(new StackLocation(--spillOffset));
			spilledVariableList.add(spill);
			active.remove(spill);
			active.add(lr);
			Collections.sort(active);
		} else {
			lr.setLocation(new StackLocation(--spillOffset));
			this.spilledVariableList.add(lr);
		}
	}
	/**
	 * @return
	 */
	public ArrayList getSpilledVariableList() {
		return spilledVariableList;
	}

}

class EndPointComparator implements Comparator {
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		Variable lr1 = (Variable) o1;
		Variable lr2 = (Variable) o2;
		return lr1.getEndAddress() - lr2.getEndAddress();
	}
}
