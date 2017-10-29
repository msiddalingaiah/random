
package com.madhu.minc.s4;

import java.util.ArrayList;

/*

-(*(b b) *(*(4 a) c))

reg: op(mrc, reg)
reg: op(reg, mrc)
op: + | - | *
mrc: MEM | reg | CONST

 */
/*
 * Created on Jul 1, 2007 at 10:00 AM
 */
public class Expression {
	private Symbol symbol;
	private ArrayList<Expression> operands;
	
	public Expression(Symbol symbol) {
		if (symbol == null) {
			throw new NullPointerException("operator cannot be null");
		}
		this.symbol = symbol;
		operands = new ArrayList<Expression>();
	}
	
	public Expression(String name) {
		this(new Symbol(name));
	}

	public void add(Expression e) {
		operands.add(e);
	}

	public int getSize() {
		return operands.size();
	}

	public boolean isLeaf() {
		return operands.size() == 0;
	}

	public Expression getOperand(int index) {
		return operands.get(index);
	}
	
	public Symbol getSymbol() {
		return symbol;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Expression)) {
			throw new IllegalArgumentException("expecting class Expression: " + o.getClass());
		}
		Expression other = (Expression) o;
		if (!nodeEquals(other)) {
			return false;
		}
		int n = operands.size();
		if (n != other.getSize()) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			if (!getOperand(i).equals(other.getOperand(i))) {
				return false;
			}
		}
		return true;
	}

	protected boolean nodeEquals(Expression other) {
		return symbol.equals(other.getSymbol());
	}
	
	public String toString() {
		if (isLeaf()) {
			return symbol.toString();
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(symbol.toString());
			sb.append('(');
			sb.append(getOperand(0));
			int n = getSize();
			for (int i = 1; i < n; i++) {
				sb.append(' ');
				sb.append(getOperand(i));
			}
			sb.append(')');
			return sb.toString();
		}
	}
}
