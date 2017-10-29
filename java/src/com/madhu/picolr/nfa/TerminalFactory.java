
package com.madhu.picolr.nfa;

public abstract class TerminalFactory<E> {
	public abstract E create(String name, String text, int line, int column);
}
