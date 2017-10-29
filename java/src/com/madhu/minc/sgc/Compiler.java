
package com.madhu.minc.sgc;

import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class Compiler {
	private ASMWriter emitter;
	private MIPSGenerator codeGen;
	private int labelCount;

	public static void main(String[] args) throws Exception {
		Compiler c = new Compiler();
		c.compile();
	}
	
	public void compile() throws Exception {
		FileInputStream in = new FileInputStream("sgc/in.sgc");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				System.out));

		ANTLRInputStream input = new ANTLRInputStream(in);
		SyntaxLexer lexer = new SyntaxLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SyntaxParser parser = new SyntaxParser(tokens);
		parser.setTreeAdaptor(new CommonTreeAdaptor() {
			@Override
			public Object create(Token payload) {
				return new SGCTree(payload);
			}
		});

		HashMap<String,Variable> symbols = new HashMap<String, Variable>();

		// Walk statements
		SGCTree root = (SGCTree) parser.program().getTree();
		
		printProg(root);

//		System.exit(0);

		root.linearize();
		root.processBlock(symbols);

//		SGCTree t = SGCTree.getFirst();
//		while (t != null) {
//			System.out.printf("%d: %s\n", t.getIndex(), t.toString());
//			t = t.getNext();
//		}

//		for (Variable var : symbols.values()) {
//			System.out.println(var);
//		}

		emitter = new ASMWriter(out);

		codeGen = new MIPSGenerator();
		codeGen.setRegisterallocator(new RegisterAllocator(symbols));
		codeGen.setAsmwriter(emitter);

		emitter.enterProcedure("main");

		genStatements(root);

		emitter.exitProcedure();

		out.close();
	}

	// TODO see C:\java\jburg-0.9.7\src\grammars\TL2.jbg for mode insight
	private void genStatements(SGCTree tree) throws Exception {
		for (int i = 0; i < tree.getChildCount(); i++) {
			SGCTree stmt = (SGCTree) tree.getChild(i);

			switch (stmt.getType()) {
			case 0: break;
			case SyntaxParser.DECLARATION:
				emitter.printDecl(stmt.getChild(0), stmt.getChild(1));
				break;
			case SyntaxParser.ASSIGN:
				codeGen.burm(stmt);
				break;
			case SyntaxParser.WHILE:
				break;
			case SyntaxParser.IF:
				String l1 = newLabel();
				stmt.getSGCChild(1).getToken().setText(l1);
				SGCTree ifStmt = new SGCTree(stmt.getToken());
				ifStmt.addChild(stmt.getChild(0));
				ifStmt.addChild(stmt.getChild(1));
				codeGen.burm(ifStmt);
				genStatements(stmt.getSGCChild(2));
				if (stmt.getChildCount() >= 4) {
					String l2 = newLabel();
					emitter.printInstruction("jump", l2, null, null);
					emitter.printLabel(l1);
					genStatements(stmt.getSGCChild(3));
					emitter.printLabel(l2);
				} else {
					emitter.printLabel(l1);
				}
				break;
			default:
				System.out.printf("%d: unexpected node: %s\n",
						stmt.getToken().getLine(), stmt.getText());
				break;
			}
		}
	}

	private String newLabel() {
		labelCount += 1;
		return String.format("L%d", labelCount);
	}

	private void printProg(SGCTree root) {
		int n = root.getChildCount();
		for (int i = 0; i < n; i += 1) {
			System.out.println(root.getChild(i).toStringTree());
		}
	}
}
