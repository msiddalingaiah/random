
package com.madhu.picolr;

import java.util.ArrayList;

/*
 * Created on Apr 22, 2008 at 6:35:24 PM
 */
public class Tree<E,T extends Tree> {
	private E value;
	private ArrayList<T> children;
	
	public Tree(E value) {
		this.value = value;
		children = new ArrayList<T>();
	}

	public Tree() {
		this(null);
	}

	public T get(int ... indices) {
		T t = (T) this;
		for (int i = 0; i < indices.length; i++) {
			t = (T) t.children.get(indices[i]);
		}
		return t;
	}
	
	public void add(T t) {
		children.add(t);
	}
	
	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}
	
	public int size() {
		return children.size();
	}
	
	public boolean isLeaf() {
		return size() == 0;
	}
	
	public void clear() {
		children.clear();
	}

	public boolean equals(Object o) {
		if (o instanceof Tree) {
			Tree t = (Tree) o;
			return value.equals(t.getValue()) && children.equals(t.getChildren());
		}
		return false;
	}

	private ArrayList<T> getChildren() {
		return children;
	}

	public String toString() {
//		return value.toString();
		return toStringTree();
	}

	public String toStringTree() {
		if (isLeaf()) {
			return value.toString();
		}
		StringBuffer sb = new StringBuffer("(");
		sb.append(value.toString());
		int n = size();
		for (int i = 0; i < n; i += 1) {
			sb.append(' ');
			sb.append(get(i).toString());
		}
		sb.append(")");
		return sb.toString();
	}
}
