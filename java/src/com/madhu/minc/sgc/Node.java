
/*
 * Created on Nov 30, 2004 6:32:52 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.minc.sgc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Node {
	private static int postOrderCounter = 1;
	
	private String name;

	/**
	 * This nodes immediate dominator (up the dom tree)
	 */
	private Node idominator;
	
	/**
	 * The nodes immediately dominated (down the dom tree)
	 */
	private List<Node> dominatedBlocks;
	
	/**
	 * The nodes in the dominance frontier of this node
	 */
	private List<Node> dominanceFrontier;
	
	/**
	 * The immediate predecessors of this node
	 */
	private List<Node> predecessors;
	
	/**
	 * The immediate successors of this node
	 */
	private List<Node> successors;
	
	/**
	 * Depth from the root (root = 0, next lower = 1 etc., unknown = -1)
	 */
	private int postOrderNumber;
	
	public Node(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name can't be null!");
		}
		this.name = name;
		predecessors = new ArrayList<Node>();
		successors = new ArrayList<Node>();
		dominatedBlocks = new ArrayList<Node>();
		postOrderNumber = -1;
		dominanceFrontier = new ArrayList<Node>();
	}

	private void addPredecessor(Node p) {
		if (!predecessors.contains(p)) {
			predecessors.add(p);
		}
	}

	public void addDominatedBlock(Node db) {
		if (!dominatedBlocks.contains(db)) {
			dominatedBlocks.add(db);
		}
	}

	/**
	 * Compares the names, assumes node names are unique
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		Node other = (Node) obj;
		return name.equals(other.getName());
	}

	/**
	 * Returns hashcode based on name, assumes names are unique
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return name.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}

	/**
	 * @return
	 */
	public Node getIDominator() {
		return idominator;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public List<Node> getPredecessors() {
		return predecessors;
	}

	/**
	 * @return
	 */
	public List<Node> getSuccessors() {
		return successors;
	}

	/**
	 * @param node
	 */
	public void addSuccessor(Node node) {
		if (!successors.contains(node)) {
			successors.add(node);
			node.addPredecessor(this);
		}
	}

	/**
	 * @param node
	 */
	public void setIDominator(Node node) {
		idominator = node;
		if (node != null) {
			node.addDominatedBlock(this);
		}
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @return
	 */
	public int getPostOrderNumber() {
		return postOrderNumber;
	}
	
	/**
	 * @param i
	 */
	public void setPostOrderNumber(int i) {
		postOrderNumber = i;
	}

	public void computePostOrder(List<Node> list) {
		setPostOrderNumber(0);
		int n = successors.size();
		for (int i = 0; i < n; i++) {
			Node b = successors.get(i);
			if (b != null && b.getPostOrderNumber() < 0) {
				b.computePostOrder(list);
			}
		}
		setPostOrderNumber(postOrderCounter++);
		list.add(this);
	}

	public void printDomTree() {
		System.out.print(getName() + " doms:");
		Node d = getIDominator();
		while (d != null) {
			System.out.print(" " + d.getName());
			d = d.getIDominator();
		}
		System.out.println();
	}
	
	public void printPredecessors() {
		System.out.print(getName() + " preds:");
		Iterator<Node> it = predecessors.iterator();
		while (it.hasNext()) {
			Node b = it.next();
			System.out.print(" " + b.getName());
		}
		System.out.println();
	}

	/**
	 * @param b
	 */
	public void addDominanceFrontier(Node b) {
		if (!dominanceFrontier.contains(b)) {
			dominanceFrontier.add(b);
		}
	}

	/**
	 * @return
	 */
	public List<Node> getDominanceFrontier() {
		return dominanceFrontier;
	}

	/**
	 * @return
	 */
	public List<Node> getDominatedBlocks() {
		return dominatedBlocks;
	}
}
