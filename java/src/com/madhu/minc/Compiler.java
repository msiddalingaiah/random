
/*
 * $Id: Compiler.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

import com.madhu.minc.hir.HighLevelIR;
import com.madhu.minc.ia32.IA32AsmCodeGenerator;
import com.madhu.minc.lir.CodeGenerator;
import com.madhu.minc.lir.LowLevelIR;
import com.madhu.minc.opt.Optimizer;
import com.madhu.minc.opt.SimpleOptimizer;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class Compiler {
	private Parser parser;
	private Optimizer optimizer;
	private CodeGenerator codeGenerator;
    
	public Compiler() {
		parser = new LLParser();
		optimizer = new SimpleOptimizer();
	}

	public void compile(Reader in, OutputStream out) throws IOException {
		HighLevelIR hir = parser.parse(in);

		long start = System.currentTimeMillis();
		hir.constructSSA();
		long time = System.currentTimeMillis() - start;
		// System.out.println("Construct SSA: " + time + "ms");

		hir.optimizeSSA();
		//hir.getControlFlowGraph().printBlocks();
		hir.printHIR();

		LowLevelIR lir = optimizer.optimize(hir);

		lir.printRTL();

//		CFGFrame cfgFrame = new CFGFrame("Control Flow Graph",
//			hir.getControlFlowGraph());
//		cfgFrame.setVisible(true);

		codeGenerator = new IA32AsmCodeGenerator(lir, out);
		codeGenerator.generate();
		System.out.println("Done");
	}
	
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println(
				"usage: java com.madhu.minc.Compiler <src-file>");
			System.exit(1);
		}
		FileReader in = new FileReader(args[0]);
		String outFile = args[0].substring(0, args[0].lastIndexOf('.'));
		FileOutputStream out = new FileOutputStream(outFile + ".s");
		Compiler c = new Compiler();
		try {
			c.compile(in, out);
		} catch (SyntaxError e) {
			System.out.println(e.getMessage());
		} finally {
			in.close();
			out.close();
		}
	}
}
