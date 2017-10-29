
package com.madhu.picolr.test;

import java.io.IOException;
import java.io.StringReader;

import com.madhu.picolr.Grammar;
import com.madhu.picolr.Parser;
import com.madhu.picolr.AST;
import com.madhu.picolr.Terminal;
import com.madhu.picolr.TreeFactory;
import com.madhu.picolr.gui.TreeFrame;

public class ExpressionGrammar {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Grammar<AST> g = new Grammar<AST>();
		g.addTerminal(Grammar.WHITESPACE, "( |\t|\r|\n|\f)+");
		g.addTerminal("(", "\\(");
		g.addTerminal(")", "\\)");
		g.addTerminal("number", "(0-9)+");
		g.addTerminal("*", "\\*");
		g.addTerminal("+", "\\+");
		g.addTerminal("-", "\\-");

		g.addProduction("E", new String[] {
			"E + T", "+ E T",
			"E - T", "- E T",
			"T", "T"
		});
		g.addProduction("T", new String[] {
			"T * P", "* T P",
			"P", "P"
		});
		g.addProduction("P", new String[] {
			"number", "number",
			"( E )", "E",
			"- P", "negate P"
		});

		g.setStartSymbol("E");

		Parser<AST> parser = g.getParser();
		parser.setTreeFactory(new TreeFactory<AST>() {
			@Override
			public AST create(Terminal t) {
				return new AST(t);
			}
		});
		String input = "1+2*3*4";
		long t0, t1;
		t0 = System.currentTimeMillis();
		AST tree = parser.parse(new StringReader(input));
//		System.out.println(tree.toStringTree());
		t1 = System.currentTimeMillis();
		System.out.println("Time: " + (t1-t0) + "ms");
		TreeFrame tf = new TreeFrame("Parse Tree", tree);
		tf.setSize(800, 600);
		tf.setVisible(true);
	}
}
