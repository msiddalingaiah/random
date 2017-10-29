
/*
 * Created on Apr 23, 2005 12:53:31 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.nfacg;

import com.madhu.minc.lir.Instruction;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class ExpressionEdge extends Edge {
	private int index;
	private int opCode;
	private int mode;
	private String expression;

	public ExpressionEdge(int opCode, int mode, String expression) {
		this.opCode = opCode;
		this.mode = mode;
		this.expression = expression;
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.nfacg.Edge#accepts(com.madhu.minc.lir.Instruction)
	 */
	public boolean accepts(Instruction inst) {
		if (opCode != inst.getOpCode() || mode != inst.getMode()) {
			return false;
		}
		index = 0;
		return level0(inst) != 0;
	}

	/**
	 * @param inst
	 * @return
	 */
	private int level0(Instruction inst) {
		int v1 = level1(inst);
		while (true) {
			int c = read();
			switch (c) {
				case '<': v1 = v1 < level1(inst) ? 1 : 0; break;
				case '>': v1 = v1 > level1(inst) ? 1 : 0; break;
				case '=': v1 = v1 == level1(inst) ? 1 : 0; break;
				case -1: return v1;
				default: throw new IllegalArgumentException(
					"expected >, <, =, found " + (char) c);
			}
		}
	}
	
	/**
	 * @param inst
	 * @return
	 */
	private int level1(Instruction inst) {
		int v1 = level2(inst);
		while (true) {
			int c = read();
			switch (c) {
				case '&': v1 &= level2(inst); break;
				case '|': v1 |= level2(inst); break;
				default: unread(); return v1;
			}
		}
	}

	/**
	 * @param inst
	 * @return
	 */
	private int level2(Instruction inst) {
		int v1 = level3(inst);
		while (true) {
			int c = read();
			switch (c) {
				case '+': v1 += level3(inst); break;
				case '-': v1 -= level3(inst); break;
				default: unread(); return v1;
			}
		}
	}

	/**
	 * @param inst
	 * @return
	 */
	private int level3(Instruction inst) {
		int v1 = level4(inst);
		while (true) {
			int c = read();
			switch (c) {
				case '*': v1 *= level4(inst); break;
				case '/': v1 /= level4(inst); break;
				default: unread(); return v1;
			}
		}
	}

	/**
	 * @param inst
	 * @return
	 */
	private int level4(Instruction inst) {
		int c = read();
		switch (c) {
			case 'a': return inst.getOperands()[0];
			case 'b': return inst.getOperands()[1];
			case 'c': return inst.getOperands()[2];
			case '-': return -level4(inst);
			case '+': return +level4(inst);
			case '(':
				int v1 = level4(inst);
				c = read();
				if (c == ')') {
					return v1;
				}
				throw new IllegalArgumentException("expected ), found: " + (char) c);
			case -1:
				throw new IllegalArgumentException("unexpected end");
		}
		if (c >= '0' && c <= '9') {
			int v1 = 0;
			while (c >= '0' && c <= '9') {
				v1 *= 10;
				v1 += c - '0';
				c = read();
			}
			unread();
			return v1;
		}
		throw new IllegalArgumentException("unexpected char: " + (char) c);
	}

	private int read() {
		if (index >= expression.length()) {
			return -1;
		}
		return expression.charAt(index++);
	}
	
	private void unread() {
		if (index < expression.length()) {
			index -= 1;
		}
	}
	
	public static void main(String[] args) {
		ExpressionEdge e = new ExpressionEdge(0, 0, "10+1<11");
		System.out.println(e.level0(null));
	}
}
