
package com.madhu.minc.s4;

/**
 * Created on Jul 1, 2007 at 10:00 AM
 * 
 * A symbol represents either a terminal or non-terminal.
 * Terminals must start with an uppercase letter, non-terminals not.
 */
public class Symbol {
	private String name;
	private Object value;

	public Symbol(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Symbol(String name) {
		this(name, null);
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Symbol)) {
			return false;
		}
		Symbol so = (Symbol) o;
		return name.equals(so.getName());
	}
	
	public int hashCode() {
		return name.hashCode();
	}

	public boolean isTerminal() {
		return Character.isUpperCase(name.charAt(0));
	}

	public String getName() {
		return name;
	}
	
	public Object getValue() {
		return value;
	}

	public String toString() {
		if (value == null) {
			return name;
		} else {
			return name + " " + value;
		}
	}
}
