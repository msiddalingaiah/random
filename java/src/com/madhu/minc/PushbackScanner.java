
/*
 * $Id: PushbackScanner.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc;

import java.io.IOException;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class PushbackScanner extends Scanner {
	private Scanner scanner;
	private int available;
	private int inputIndex;
	private Token[] tokenStack;

	public PushbackScanner(Scanner next) {
		scanner = next;
		available = 0;
		inputIndex = 0;
		tokenStack = new Token[8];
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.Scanner#nextToken()
	 */
	public Token nextToken() throws IOException {
		Token c;
		if (available > 0) {
			c = tokenStack[inputIndex++];
			inputIndex %= tokenStack.length;
			available -= 1;
		} else {
			c = scanner.nextToken();
			tokenStack[inputIndex++] = c;
			inputIndex %= tokenStack.length;
		}
		return c;
	}

	public void pushBack() {
		inputIndex -= 1;
		if (inputIndex < 0) {
			inputIndex = tokenStack.length - 1;
		}
		available += 1;
	}
}
