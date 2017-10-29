
/*
 * $Id: Parser.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc;

import java.io.IOException;
import java.io.Reader;

import com.madhu.minc.hir.*;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public abstract class Parser {
    public abstract HighLevelIR parse(Reader in) throws IOException;
}
