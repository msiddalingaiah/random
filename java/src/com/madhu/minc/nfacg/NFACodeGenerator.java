
/*
 * Created on Apr 23, 2005 12:33:39 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.nfacg;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import com.madhu.minc.hir.BinaryQuad;
import com.madhu.minc.hir.ConditionalBranchQuad;
import com.madhu.minc.hir.ExpReturnQuad;
import com.madhu.minc.hir.PhiAssignQuad;
import com.madhu.minc.hir.UnaryQuad;
import com.madhu.minc.hir.UnconditionalBranchQuad;
import com.madhu.minc.hir.VarAssignQuad;
import com.madhu.minc.lir.CodeGenerator;
import com.madhu.minc.lir.Instruction;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class NFACodeGenerator extends CodeGenerator {
	private int index;
	private List instructions;
	private NFAState start;
	private DFAState currentState;
	private DFAState nextState;
	private PrintWriter out;

	public NFACodeGenerator(List insts, OutputStream os) {
		start = new NFAState("__start__");
		currentState = new DFAState();
		nextState = new DFAState();
		this.instructions = insts;
		index = 0;
		out = new PrintWriter(new OutputStreamWriter(os));
	}

	public void addRule(NFAState rule) {
		start.addEpsilonEdge(rule);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#generate()
	 */
	public void generate() {
		String name = doGenerate();
		while (name != null) {
			out.println("// accepting " + name);
			name = doGenerate();
		}
	}

	private String doGenerate() {
		reset();
		Instruction inst = getNext();
		if (inst == null) {
			backup();
			return null;
		}
		while (currentState.advance(nextState, inst)) {
			swap();
			nextState.clear();
			inst = getNext();
		}
		backup();
		if (currentState.isAccept()) {
			return currentState.getName();
		}
		throw new IllegalArgumentException("no match on " + inst);
	}

	/**
	 * @return
	 */
	private Instruction getNext() {
		if (index >= instructions.size()) {
			return null;
		}
		return (Instruction) instructions.get(index++);
	}

	/**
	 * 
	 */
	private void backup() {
		index -= 1;
	}

	private void reset() {
		currentState.clear();
		nextState.clear();
		currentState.add(start);
		start.eClosure(currentState);
	}

	private void swap() {
		DFAState temp = currentState;
		currentState = nextState;
		nextState = temp;
	}

	public void generateCode(ExpReturnQuad quad) { }
	public void generateCode(BinaryQuad quad) { }
	public void generateCode(ConditionalBranchQuad quad) { }
	public void generateCode(PhiAssignQuad quad) { }
	public void generateCode(UnaryQuad quad) { }
	public void generateCode(UnconditionalBranchQuad quad) { }
	public void generateCode(VarAssignQuad quad) { }
	public void begin() { }
	public void end() { }
}
