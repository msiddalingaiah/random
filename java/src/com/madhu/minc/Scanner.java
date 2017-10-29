
/*
 * $Id: Scanner.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc;

import java.io.IOException;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public abstract class Scanner {
	public abstract Token nextToken() throws IOException;
}
