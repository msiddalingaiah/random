
/*
 * Created on May 18, 2005 5:31:45 PM
 *
 * Copyright (c) 2004 Madhu Siddalingaiah
 * All rights reserved
 * mailto:madhu@madhu.com
 */
package com.madhu.picolr;

import java.util.ArrayList;

import com.madhu.picolr.nfa.NFAState;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Terminal extends GrammarSymbol {
	private NFAState nfaState;
	private ArrayList<Terminal> list;
	private String text;
	private int lineNumber;
	private int columnNumber;

	public Terminal(int number) {
		super(number);
	}

	public Terminal(int number, String name) {
		this(number);
		setName(name);
	}

	/**
	 * @param terminal
	 */
	public Terminal(Terminal t) {
		super(t.hashCode());
		setName(t.getName());
	}

	public Terminal(Terminal t, String text) {
		this(t);
		setText(text);
	}

	/**
	 * @param t
	 */
	public void addChild(Terminal t) {
		if (list == null) {
			list = new ArrayList<Terminal>();
		}
		list.add(t);
	}

	/**
	 * Tries to find a more specific Terminal that mathes the input text.
	 * This handles the typical case where keywords are specific instances
	 * of an ID.
	 * 
	 * @param text the text of the input token
	 * @return a new Terminal that matches text or name (in that order)
	 */
	public Terminal resolve(String text) {
		if (list == null) {
			return new Terminal(this, text);
		}
		for (Terminal t : list) {
			if (t.getText().equals(text)) {
				return new Terminal(t);
			}
		}
		return new Terminal(this, text);
	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param string
	 */
	public void setText(String string) {
		text = string;
	}

	/**
	 * @param state
	 */
	public void setNFAState(NFAState state) {
		this.nfaState = state;
	}

	/**
	 * @return
	 */
	public NFAState getNFAState() {
		return nfaState;
	}

	public String toString() {
		if (getText() == null) {
			return getName();
		}
		return getText();
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}
}
