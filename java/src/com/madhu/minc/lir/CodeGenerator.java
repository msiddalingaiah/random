
/*
 * $Id: CodeGenerator.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc.lir;

import com.madhu.minc.hir.BinaryQuad;
import com.madhu.minc.hir.ConditionalBranchQuad;
import com.madhu.minc.hir.ExpReturnQuad;
import com.madhu.minc.hir.PhiAssignQuad;
import com.madhu.minc.hir.UnaryQuad;
import com.madhu.minc.hir.UnconditionalBranchQuad;
import com.madhu.minc.hir.VarAssignQuad;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public abstract class CodeGenerator {

	public CodeGenerator() {
	}

	public abstract void generate();

	/**
	 * @param quad
	 */
	public abstract void generateCode(ExpReturnQuad quad);

	/**
	 * @param quad
	 */
	public abstract void generateCode(BinaryQuad quad);

	/**
	 * @param quad
	 */
	public abstract void generateCode(ConditionalBranchQuad quad);

	/**
	 * @param quad
	 */
	public abstract void generateCode(PhiAssignQuad quad);

	/**
	 * @param quad
	 */
	public abstract void generateCode(UnaryQuad quad);

	/**
	 * @param quad
	 */
	public abstract void generateCode(UnconditionalBranchQuad quad);

	/**
	 * @param quad
	 */
	public abstract void generateCode(VarAssignQuad quad);

	/**
	 * 
	 */
	public abstract void begin();

	/**
	 * 
	 */
	public abstract void end();
}
