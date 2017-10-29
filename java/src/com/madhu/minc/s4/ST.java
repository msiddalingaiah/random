
package com.madhu.minc.s4;

/*
 * Created on Jul 1, 2007 at 10:00 AM
 */
public class ST extends Expression {
	private MatchList matchList;

	public ST(Symbol symbol) {
		super(symbol);
		matchList = new MatchList();
	}

	public ST(String name) {
		this(new Symbol(name));
	}

	public ST(String name, ST st1) {
		this(name);
		add(st1);
	}

	public ST(String name, ST st1, ST st2) {
		this(name);
		add(st1);
		add(st2);
	}

	public ST getOperandST(int index) {
		return (ST) getOperand(index);
	}

	public Match getMatch(Symbol lhs) {
		return matchList.getMatch(lhs);
	}
	
	public boolean derives(Symbol lhs) {
		return matchList.derives(lhs);
	}

	@Override
	protected boolean nodeEquals(Expression other) {
		return super.nodeEquals(other) || derives(other.getSymbol());
	}

	// TODO cost computation is not quite right...
	public int computeCost(Expression rhs, int cost) {
		Symbol symbol = rhs.getSymbol();
		if (!symbol.isTerminal()) {
			int c = getMatch(symbol).getCost();
			if (c == Integer.MAX_VALUE) {
				return c;
			}
			cost += c;
		} else if (!getSymbol().equals(symbol)) {
			return Integer.MAX_VALUE;
		}
		int n = getSize();
		if (n != rhs.getSize()) {
			return Integer.MAX_VALUE;
		}
		for (int i = 0; i < n; i += 1) {
			int c = getOperandST(i).computeCost(rhs.getOperand(i), cost);
			cost += c;
		}
		return cost;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(128);
		String tabs = "";
		doToString(tabs, sb);
		return sb.toString();
	}

	private void doToString(String tabs, StringBuffer sb) {
		sb.append(tabs);
		sb.append(getSymbol());
		sb.append('\n');
		sb.append(matchList);
		tabs += '\t';
		int n = getSize();
		for (int i = 0; i < n; i += 1) {
			sb.append('\n');
			getOperandST(i).doToString(tabs, sb);
		}
		tabs = tabs.substring(1);
	}
}
