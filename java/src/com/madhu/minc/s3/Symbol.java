
/*
 * Madhu Siddalingaiah
 * http://www.madhu.com
 * Created Jun 23, 2006
 */

package com.madhu.minc.s3;

public abstract class Symbol {
	private String name;

	public Symbol(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isTerminal() {
		return this instanceof Terminal;
	}

	public boolean equals(Object other) {
		if (other instanceof Symbol) {
			Symbol s = (Symbol) other;
			return name.equals(s.getName()) && (isTerminal() == s.isTerminal());
		}
		return false;
	}
	
	public int hashCode() {
		return name.hashCode();
	}

	public String toString() {
		return name;
	}
}
