
package com.madhu.picolr.burg;

/*
 * Created on Apr 22, 2008 at 6:35:24 PM
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

	public boolean isTerminal() {
		return Character.isUpperCase(name.charAt(0));
	}
	
	public boolean isNonTerminal() {
		return !isTerminal();
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public String toString() {
		if (value == null) {
			return name;
		} else {
			return String.format("%s: %s", name, value);
		}
	}
	
	public boolean equals(Object o) {
		if (o instanceof Symbol && name != null) {
			Symbol s = (Symbol) o;
			return name.equals(s.getName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
