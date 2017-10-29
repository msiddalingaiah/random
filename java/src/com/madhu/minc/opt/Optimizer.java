
/*
 * $Id: Optimizer.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc.opt;

import com.madhu.minc.hir.HighLevelIR;
import com.madhu.minc.lir.LowLevelIR;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public abstract class Optimizer {

	/**
	 * @param highLevelIR
	 */
	public abstract LowLevelIR optimize(HighLevelIR highLevelIR);
}
