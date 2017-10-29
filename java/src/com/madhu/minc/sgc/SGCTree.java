
package com.madhu.minc.sgc;

import java.util.HashMap;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

/*
 * Created on Apr 24, 2008 at 4:43:27 PM
 */
public class SGCTree extends CommonTree {
	private static int count;
	private static SGCTree previous;
	private static SGCTree first;
	private int address;
	private SGCTree next;
	
	public SGCTree() {
	}

	public SGCTree(CommonTree ct) {
		super(ct);
	}

	public SGCTree(Token t) {
		super(t);
	}
	
	public String toString() {
		if (isNil()) {
			return "nil";
		} else {
			return getToken().getText();
		}
	}

	public SGCTree getSGCChild(int i) {
		return (SGCTree) getChild(i);
	}

	// JBurg methods
	public int getOperator() {
		return getType();
	}

	public SGCTree leftChild() {
		return (SGCTree) getChild(0);
	}

	public SGCTree rightChild() {
		return (SGCTree) getChild(1);
	}

	public void processBlock(HashMap<String, Variable> symbols) {
		int n = getChildCount();
		for (int i = 0; i < n; i += 1) {
			getSGCChild(i).doStatement(symbols);
		}
	}

	private void doStatement(HashMap<String, Variable> symbols) {
		switch (getType()) {
		case 0: break;
		case SyntaxParser.DECLARATION: doDeclaration(symbols); break;
		case SyntaxParser.ASSIGN: doAssignment(symbols); break;
		case SyntaxParser.WHILE: doWhile(symbols); break;
		case SyntaxParser.IF: doIf(symbols); break;
		default:
			System.out.printf("%d: unexpected node: %s\n",
					getToken().getLine(), getText());
			break;
		}
	}

	private void doIf(HashMap<String, Variable> symbols) {
		getSGCChild(0).doExpression(symbols);
		getSGCChild(2).processBlock(symbols);
		if (this.getChildCount() >= 4) {
			getSGCChild(3).processBlock(symbols);
		}
	}

	private void doWhile(HashMap<String, Variable> symbols) {
		getSGCChild(0).doExpression(symbols);
		getSGCChild(1).processBlock(symbols);
	}

	// TODO really should do SSA here...
	private void doAssignment(HashMap<String, Variable> symbols) {
		String name = getChild(0).getText();
		Variable var = symbols.get(name);
		if (var == null) {
			throw new IllegalArgumentException(
					String.format("%d: undefined variable: %s",
							getToken().getLine(), name));
		}
		if (var.getStartAddress() < 0) {
			var.setStartAddress(address);
		}
		doExpression(symbols);
	}

	private void doExpression(HashMap<String, Variable> symbols) {
		int n = getChildCount();
		for (int i = 0; i < n; i += 1) {
			SGCTree child = getSGCChild(i);
			child.doExpression(symbols);
		}
		if (getType() == SyntaxParser.IDENT) {
			String name = getText();
			Variable var = symbols.get(name);
			if (var == null) {
				throw new IllegalArgumentException(
						String.format("%d: undefined variable: %s",
								getToken().getLine(), name));
			}
			if (var.getEndAddress() < address) {
				var.setEndAddress(address);
			}
		}
	}

	private void doDeclaration(HashMap<String, Variable> symbols) {
		String name = getChild(1).getText();
		if (symbols.get(name) != null) {
			throw new IllegalArgumentException(
				String.format("%d: double definition of %s",
						getToken().getLine(), name));
		}
		Variable var = new Variable(name, getChild(0).getType());
		symbols.put(name, var);
	}

	public void linearize() {
		int n = getChildCount();
		for (int i = 0; i < n; i += 1) {
			getSGCChild(i).linearize();
		}
		address = count++;
		if (first == null) {
			first = this;
		} else {
			previous.next = this;
		}
		previous = this;
	}

	public SGCTree getNext() {
		return next;
	}

	public static SGCTree getFirst() {
		return first;
	}
	
	public int getAddress() {
		return address;
	}
}
