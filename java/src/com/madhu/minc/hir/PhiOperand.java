
/*
 * Created on Nov 17, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.hir;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class PhiOperand extends Operand {
	private ArrayList refs;

	/**
	 * 
	 */
	public PhiOperand() {
		refs = new ArrayList();
	}
	
	/**
	 * @param operand
	 */
	public PhiOperand(Variable var) {
		this();
		refs.add(var);
	}

	public void addRef(Variable var) {
		refs.add(var);
	}

	/**
	 * @return
	 */
	public List getRefs() {
		return refs;
	}

	public String toString() {
		int n = refs.size();
		StringBuffer sb = new StringBuffer(6*n + 6);
		sb.append("phi(");
		for (int i=0; i<n-1; i+=1) {
			sb.append(refs.get(i));
			sb.append(',');
		}
		if (n > 0) {
			sb.append(refs.get(n-1));
		}
		sb.append(')');
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.hir.Operand#simplify()
	 */
	public Operand simplify() {
		int n = refs.size();
		for (int i = 0; i < n; i++) {
			Operand op = (Operand) refs.get(i);
			op = op.simplify();
			if (op instanceof Variable) {
				refs.set(i, op);
			}
		}
		return this;
	}
}
