
/*
 * Created on May 18, 2005 9:14:01 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr;

import java.util.HashMap;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class StateList {
	private Terminal whitespace;
	private HashMap<Item,State> states;
	private int count;

	public StateList() {
		this(null);
	}
	
	/**
	 * @param whitespace
	 */
	public StateList(Terminal whitespace) {
		states = new HashMap<Item,State>();
		count = 0;
		this.whitespace = whitespace;
	}

	public void add(State state) {
		states.put(state.getFirstKernelItem(), state);
		count += 1;
	}
	
	/**
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return
	 */
	public Terminal getWhitespace() {
		return whitespace;
	}

	/**
	 * @param firstKi
	 * @return
	 */
	public State find(Item firstKi) {
		return states.get(firstKi);
	}
}
