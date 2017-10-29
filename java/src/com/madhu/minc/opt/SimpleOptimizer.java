
/*
 * Created on Nov 10, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.opt;

import com.madhu.minc.hir.HighLevelIR;
import com.madhu.minc.lir.LowLevelIR;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class SimpleOptimizer extends Optimizer {
	/* (non-Javadoc)
	 * @see com.madhu.minc.Optimizer#optimize(com.madhu.minc.HighLevelIR)
	 */
	public LowLevelIR optimize(HighLevelIR hir) {
		// TODO optimization
		LowLevelIR lir = new LowLevelIR(hir);
		return lir;
	}
}
