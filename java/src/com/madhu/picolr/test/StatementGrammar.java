
package com.madhu.picolr.test;

import java.io.IOException;
import java.io.StringReader;

import com.madhu.picolr.AST;
import com.madhu.picolr.Grammar;
import com.madhu.picolr.Parser;
import com.madhu.picolr.Terminal;
import com.madhu.picolr.TreeFactory;
import com.madhu.picolr.gui.TreeFrame;

/*
 * Created on Dec 17, 2008 at 10:56:32 PM
 */
public class StatementGrammar {
	public static void main(String[] args) throws IOException {
		Grammar<AST> g = new Grammar<AST>();
		g.addTerminal(Grammar.WHITESPACE, "( |\t|\r|\n|\f)+");
		g.addTerminal("(", "\\(");
		g.addTerminal(")", "\\)");
		g.addTerminal("number", "(0-9)+");
		g.addTerminal("id", "(a-z|A-Z|_)(a-z|A-Z|0-9|_)*");
		g.addTerminal("string", "\"( -!|#-~|\\\\\")*\"");
		g.addTerminal("*", "\\*");
		g.addTerminal("+", "\\+");
		g.addTerminal("-", "\\-");
		g.addTerminal("{", "{");
		g.addTerminal("}", "}");
		g.addTerminal(";", ";");
		g.addTerminal("=", "=");
		g.addTerminal(".", ".");
		g.addTerminal("if", "id", "if");
		g.addTerminal("else", "id", "else");
		g.addTerminal("while", "id", "while");

		g.addProduction("program",
			"block EOF", "block"
		);
		g.addProduction("block",
			"{ statlist }", "statlist"
		);
		g.addProduction("statlist",
			"statlist stat", "statlist stat",
			"", "null"
		);
		g.addProduction("stat",
			"block", "block",
			"id = casted_E ;", "istore id casted_E",
			"id ( E ) ;", "selfcall id E",
			"P . m=id ( arglist ) ;", "methodcall P m arglist",
			"if ( E ) stat elseclause", "if E stat elseclause",
			"while ( E ) stat", "while E stat"
		);
		g.addProduction("elseclause",
			"else stat", "stat",
			"", "null"
		);
		g.addProduction("casted_E",
			"( id ) E", "cast id E",
			"E", "E"
		);
		g.addProduction("arglist",
			"E , arglist", ", E arglist",
			"E", "E",
			"", "null"
		);
		g.addProduction("E",
			"E + T", "+ E T",
			"E - T", "- E T",
			"T", "T"
		);
		g.addProduction("T",
			"T * P", "* T P",
			"P", "P"
		);
		g.addProduction("P",
			"number", "number",
			"string", "string",
			"id", "id",
			"( E )", "E",
			"- P", "- P",
			"P . m=id ( arglist )", "methodcall P m arglist"
		);

		//g.setStartSymbol("program");

		Parser<AST> parser = g.getParser();
		parser.setTreeFactory(new TreeFactory<AST>() {
			@Override
			public AST create(Terminal t) {
				return new AST(t);
			}
		});
		String input = "{ if (a) { abc=1+2*3; b=2; } else { c=\"cdef\".length(); } }";
		long t0, t1;
		t0 = System.currentTimeMillis();
		AST tree = parser.parse(new StringReader(input));
		t1 = System.currentTimeMillis();
		System.out.println("Time: " + (t1-t0) + "ms");
		TreeFrame tf = new TreeFrame("Parse Tree", tree);
		tf.setSize(800, 600);
		tf.setVisible(true);
	}
}
