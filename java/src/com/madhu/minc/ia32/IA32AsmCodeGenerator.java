
/*
 * Created on Nov 10, 2004
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.ia32;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.madhu.minc.Token;
import com.madhu.minc.hir.BinaryQuad;
import com.madhu.minc.hir.ConditionalBranchQuad;
import com.madhu.minc.hir.Constant;
import com.madhu.minc.hir.ExpReturnQuad;
import com.madhu.minc.hir.MethodArgument;
import com.madhu.minc.hir.Operand;
import com.madhu.minc.hir.PhiAssignQuad;
import com.madhu.minc.hir.Quad;
import com.madhu.minc.hir.UnaryQuad;
import com.madhu.minc.hir.UnconditionalBranchQuad;
import com.madhu.minc.hir.VarAssignQuad;
import com.madhu.minc.hir.Variable;
import com.madhu.minc.lir.CodeGenerator;
import com.madhu.minc.lir.LinearScanAllocator;
import com.madhu.minc.lir.Location;
import com.madhu.minc.lir.LowLevelIR;
import com.madhu.minc.lir.Register;
import com.madhu.minc.lir.StackLocation;

/**
 * @author Madhu Siddalingaiah
 *
 */
public class IA32AsmCodeGenerator extends CodeGenerator {
	private LowLevelIR lowLevelIR;
	private PrintWriter out;
	private String instructionOperand;

	public IA32AsmCodeGenerator(LowLevelIR lir, OutputStream out) {
		this.lowLevelIR = lir;
		this.out = new PrintWriter(out);
	}

	public void generate() {
		begin();
		List list = lowLevelIR.getRTL();
		int n = list.size();
		for (int i=0; i<n; i+=1) {
			Quad q = (Quad) list.get(i);
			q.generateCode(this);
		}
		end();
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#begin()
	 */
	public void begin() {
		String[] regNames = {
			"%eax", "%ebx", "%ecx", "%edx", "%esi", "%edi"
		};
		int nRegs = regNames.length;
		Register[] regs = new Register[nRegs];
		for (int i=0; i<nRegs; i+=1) {
			regs[i] = new Register(regNames[nRegs-i-1]);
		}
		LinearScanAllocator ra = new LinearScanAllocator(regs);
		ra.allocate(lowLevelIR.getVariableList());
		ArrayList spilled = ra.getSpilledVariableList();

		ArrayList variableList = lowLevelIR.getVariableList();
		System.out.println("\nLive ranges:\n");
		int n = variableList.size();
		for (int i = 0; i < n; i++) {
			Variable v = (Variable) variableList.get(i);
			System.out.println(v + ": " + v.getStartAddress() +
				"-" + v.getEndAddress() + " " + v.getLocation());
		}

		out.println();
		out.println("\t.text");
		out.println("\t.align 2");
		out.println(".globl _func");
		out.println("\t.def	_func;	.scl	2;	.type	32;	.endef");
		out.println("_func:");
		out.println("\tpushl	%ebp");
		out.println("\tmovl	%esp, %ebp");
		int nSpills = spilled.size();
		if (nSpills > 0) {
			out.println("\tsubl	$" + 4*nSpills + ", %esp");
		}
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#generateCode(com.madhu.minc.hir.ExpReturnQuad)
	 */
	public void generateCode(ExpReturnQuad quad) {
		Operand[] operands = quad.getRefs();
		String op = getCPUOperand(operands[0]);
		if (!op.equals("%eax")) {
			out.println("\tmovl	$" + op + ", %eax");
		}
		out.println("\tpopl	%ebp");
		out.println("\tret");
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#generateCode(com.madhu.minc.hir.PhiAssignQuad)
	 */
	public void generateCode(PhiAssignQuad quad) {
		throw new AssertionError("generateCode not implemented for " + quad);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#generateCode(com.madhu.minc.hir.BinaryQuad)
	 */
	public void generateCode(BinaryQuad quad) {
		String op1 = getCPUOperand(quad.getLeftOperand());
		String op2 = getCPUOperand(quad.getRightOperand());
		String lhs = getCPUOperand(quad.getLHS());
		// TODO handle operand classes
		String inst;
		switch (quad.getOperator()) {
			case Token.PLUS: inst = "addl"; break;
			case Token.MINUS: inst = "subl"; break;
			case Token.XOR: inst = "xorl"; break;
			case Token.AND:
			case Token.AND_AND: inst = "andl"; break;
			case Token.OR:
			case Token.OR_OR: inst = "orl"; break;

			default:
			throw new AssertionError("generateCode not implemented for " + quad);
		}
		emitBinaryInstruction(lhs, op1, inst, op2);
	}

	/**
	 * @param variable
	 * @param operand
	 * @param operand2
	 */
	private void emitBinaryInstruction(String lhs, String op1, String inst,
		String op2) {
		emitMove(lhs, op1);
		out.print("\t");
		out.print(inst);
		out.print("\t");
		out.print(op2);
		out.print(", ");
		out.println(lhs);
	}

	/**
	 * @param lhs
	 * @param op1
	 */
	private void emitMove(String lhs, String op) {
		out.print("\tmovl\t");
		out.print(op);
		out.print(", ");
		out.println(lhs);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#generateCode(com.madhu.minc.hir.UnaryQuad)
	 */
	public void generateCode(UnaryQuad quad) {
		throw new AssertionError("generateCode not implemented for " + quad);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#generateCode(com.madhu.minc.hir.UnconditionalBranchQuad)
	 */
	public void generateCode(UnconditionalBranchQuad quad) {
		throw new AssertionError("generateCode not implemented for " + quad);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#generateCode(com.madhu.minc.hir.ConditionalBranchQuad)
	 */
	public void generateCode(ConditionalBranchQuad quad) {
		throw new AssertionError("generateCode not implemented for " + quad);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#generateCode(com.madhu.minc.hir.VarAssignQuad)
	 */
	public void generateCode(VarAssignQuad quad) {
		out.print("\tmovl\t");
		out.print(getCPUOperand(quad.getRHS()).toString());
		out.print(", ");
		out.println(getCPUOperand(quad.getLHS()).toString());
		throw new AssertionError("generateCode not implemented for " + quad);
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.lir.CodeGenerator#end()
	 */
	public void end() {
		out.flush();
	}

	/**
	 * @param operand
	 */
	private String getCPUOperand(Operand operand) {
		if (operand instanceof Constant) {
			return "$" + ((Constant) operand).getValue();
		}
		if (operand instanceof Variable) {
			return getCPUOperand((Variable) operand);
		}
		throw new AssertionError("Unsupported operand class");
	}

	/**
	 * @param variable
	 */
	private String getCPUOperand(Variable variable) {
		if (variable instanceof MethodArgument) {
			int offset = ((StackLocation) variable.getLocation()).getOffset();
			return Integer.toString(4*offset + 8) + "(%ebp)";
		}
		Location loc = variable.getLocation();
		if (loc instanceof Register) {
			return ((Register) loc).getName();
		}
		if (loc instanceof StackLocation) {
			int offset = ((StackLocation) variable.getLocation()).getOffset();
			return Integer.toString(4*offset) + "(%ebp)";
		}
		throw new AssertionError("Unsupported operand class");
	}
}
