
/*
 * $Id: LLParser.java,v 1.1 2006/03/04 22:23:25 madhu Exp $
 */
package com.madhu.minc;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.madhu.minc.hir.BinaryQuad;
import com.madhu.minc.hir.Block;
import com.madhu.minc.hir.ConditionalBranchQuad;
import com.madhu.minc.hir.ControlFlowGraph;
import com.madhu.minc.hir.ExpReturnQuad;
import com.madhu.minc.hir.HighLevelIR;
import com.madhu.minc.hir.Constant;
import com.madhu.minc.hir.MethodArgument;
import com.madhu.minc.hir.Operand;
import com.madhu.minc.hir.UnaryQuad;
import com.madhu.minc.hir.UnconditionalBranchQuad;
import com.madhu.minc.hir.VarAssignQuad;
import com.madhu.minc.hir.Variable;

/**
 * @author Madhu Siddalingaiah
 * 
 */
public class LLParser extends Parser {
	private PushbackScanner scanner;
	private HighLevelIR hir;
	private String methodName;
	private HashMap methodArgsMap;
	private ArrayList methodArgs;

	public LLParser() {
	}

	/* (non-Javadoc)
	 * @see com.madhu.minc.Parser#parse(java.io.Reader)
	 */
	public HighLevelIR parse(Reader in) throws IOException {
		scanner = new PushbackScanner(new SimpleScanner(in));
		hir = new HighLevelIR();
		methodArgsMap = new HashMap();
		methodArgs = new ArrayList();
		ControlFlowGraph cfg = parseFile();
		hir.setControlFlowGraph(cfg);
		hir.setMethodArgs(methodArgs);
		return hir;
	}

	/**
	 * @return
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * 
	 */
	private ControlFlowGraph parseFile() throws IOException {
		ControlFlowGraph cfg = new ControlFlowGraph();
		parseMethod(cfg);
		Token t = scanner.nextToken();
		if (t.getType() != Token.EOF) {
			throw new SyntaxError("Missing EOF", t);
		}
		return cfg;
	}

	/**
	 * @param cfg
	 */
	private void parseMethod(ControlFlowGraph cfg) throws IOException {
		Token t = scanner.nextToken();
		if (t.getType() != Token.ID) {
			throw new SyntaxError("Missing method name", t);
		}
		methodName = t.getText();
		t = scanner.nextToken();
		if (t.getType() != Token.OPEN_PAREN) {
			throw new SyntaxError("Missing (", t);
		}
		t = scanner.nextToken();
		scanner.pushBack();
		int argNo = 0;
		while (t.getType() == Token.ID) {
			scanner.nextToken();
			String name = t.getText();
			MethodArgument arg = new MethodArgument(name, argNo++);
			methodArgsMap.put(name, arg);
			methodArgs.add(arg);
			t = scanner.nextToken();
			scanner.pushBack();
			if (t.getType() == Token.COMMA) {
				scanner.nextToken();
				t = scanner.nextToken();
				scanner.pushBack();
			}
		}
		t = scanner.nextToken();
		if (t.getType() != Token.CLOSE_PAREN) {
			throw new SyntaxError("Missing )", t);
		}
		parseBlock(cfg);
	}

	/**
	 * @return
	 */
	private void parseBlock(ControlFlowGraph cfg) throws IOException {
		Token t = scanner.nextToken();
		int type = t.getType();
		if (type != Token.OPEN_BRACE) {
			throw new SyntaxError("Expected {", t);
		}
		t = scanner.nextToken();
		type = t.getType();
		while (type != Token.EOF && type != Token.CLOSE_BRACE) {
			scanner.pushBack();
			parseStatement(cfg);
			t = scanner.nextToken();
			type = t.getType();
		}
		if (type != Token.CLOSE_BRACE) {
			throw new SyntaxError("Missing }", t);
		}
	}

	private void parseStatement(ControlFlowGraph cfg) throws IOException {
		int type = scanner.nextToken().getType();
		scanner.pushBack();
		if (type == Token.EOF || type == Token.CLOSE_BRACE || type == Token.ELSE) {
			return;
		}
		if (type == Token.SEMI_COLON) {
			scanner.nextToken();
			return;
		}
		if (type == Token.OPEN_BRACE) {
			parseBlock(cfg);
			return;
		}
		if (type == Token.IF) {
			parseIfStatement(cfg);
			return;
		}
		if (type == Token.WHILE) {
			parseWhileStatement(cfg);
			return;
		}
		if (type == Token.RETURN) {
			parseReturnStatement(cfg);
			return;
		}
		parseAssignment(cfg);
	}

	/**
	 * @return
	 */
	private void parseAssignment(ControlFlowGraph cfg) throws IOException {
		Token t = scanner.nextToken();
		if (t.getType() != Token.ID) {
			throw new SyntaxError("Expected variable", t);
		}
		Variable lhs = new Variable(t.getText());
		t = scanner.nextToken();
		if (t.getType() == Token.SEMI_COLON) {
			return;
		}
		if (t.getType() != Token.EQUAL) {
			throw new SyntaxError("Expected =", t);
		}
		Operand n = parseExpression(cfg);
		t = scanner.nextToken();
		if (t.getType() != Token.SEMI_COLON) {
			throw new SyntaxError("Missing ;", t);
		}
		cfg.getEndBlock().add(new VarAssignQuad(lhs, n));
	}

	/**
	 * 
	 */
	private Operand parseExpression(ControlFlowGraph cfg) throws IOException {
		Operand op1 = parseEx1(cfg);
		int op = scanner.nextToken().getType();
		while (op == Token.AND_AND || op == Token.OR_OR ||
			op == Token.GREATER_THAN || op == Token.GREATER_THAN_EQUAL ||
			op == Token.LESS_THAN || op == Token.LESS_THAN_EQUAL ||
			op == Token.EQUAL_EQUAL || op == Token.NOT_EQUAL) {

			Operand op2 = parseEx1(cfg);
			BinaryQuad quad = new BinaryQuad(op1, op, op2);
			cfg.getEndBlock().add(quad);
			op1 = quad.getLHS();
			op = scanner.nextToken().getType();
		}
		scanner.pushBack();
		return op1;
	}

	/**
	 * @return
	 */
	private Operand parseEx1(ControlFlowGraph cfg) throws IOException {
		Operand op1 = parseEx2(cfg);
		int op = scanner.nextToken().getType();
		while (op == Token.AND || op == Token.OR || op == Token.XOR) {
			Operand op2 = parseEx2(cfg);
			BinaryQuad quad = new BinaryQuad(op1, op, op2);
			cfg.getEndBlock().add(quad);
			op1 = quad.getLHS();
			op = scanner.nextToken().getType();
		}
		scanner.pushBack();
		return op1;
	}

	/**
	 * @return
	 */
	private Operand parseEx2(ControlFlowGraph cfg) throws IOException {
		Operand op1 = parseEx3(cfg);
		int op = scanner.nextToken().getType();
		while (op == Token.PLUS || op == Token.MINUS) {
			Operand op2 = parseEx3(cfg);
			BinaryQuad quad = new BinaryQuad(op1, op, op2);
			cfg.getEndBlock().add(quad);
			op1 = quad.getLHS();
			op = scanner.nextToken().getType();
		}
		scanner.pushBack();
		return op1;
	}

	/**
	 * @return
	 */
	private Operand parseEx3(ControlFlowGraph cfg) throws IOException {
		Operand op1 = parsePrim(cfg);
		int op = scanner.nextToken().getType();
		while (op == Token.STAR || op == Token.SLASH || op == Token.MOD) {
			Operand op2 = parsePrim(cfg);
			BinaryQuad quad = new BinaryQuad(op1, op, op2);
			cfg.getEndBlock().add(quad);
			op1 = quad.getLHS();
			op = scanner.nextToken().getType();
		}
		scanner.pushBack();
		return op1;
	}

	/**
	 * @return
	 */
	private Operand parsePrim(ControlFlowGraph cfg) throws IOException {
		Token t = scanner.nextToken();
		int type = t.getType();
		if (type == Token.INT_LITERAL) {
			return new Constant(Integer.parseInt(t.getText()));
		}
		if (type == Token.ID) {
			String name = t.getText();
			MethodArgument arg = (MethodArgument) methodArgsMap.get(name);
			if (arg != null) {
				return arg;
			}
			return new Variable(name);
		}
		if (type == Token.BANG || type == Token.MINUS ||
			type == Token.TILDE) {

			Operand op = parseExpression(cfg);
			UnaryQuad quad = new UnaryQuad(type, op);
			cfg.getEndBlock().add(quad);
			return quad.getLHS();
		}
		if (type == Token.OPEN_PAREN) {
			Operand op = parseExpression(cfg);
			t = scanner.nextToken();
			if (t.getType() != Token.CLOSE_PAREN) {
				throw new SyntaxError("Missing )", t);
			}
			return op;
		}
		throw new SyntaxError("Expected -, !, ~, or expression", t);
	}

	/**
	 * @return
	 */
	private void parseIfStatement(ControlFlowGraph cfg) throws IOException {
		Token t = scanner.nextToken();
		if (t.getType() != Token.IF) {
			throw new AssertionError("Expected if: " + t);
		}
		t = scanner.nextToken();
		if (t.getType() != Token.OPEN_PAREN) {
			throw new SyntaxError("Expected (", t);
		}
		Operand op = parseExpression(cfg);
		t = scanner.nextToken();
		if (t.getType() != Token.CLOSE_PAREN) {
			throw new SyntaxError("Expected )", t);
		}

		ControlFlowGraph ifCFG = new ControlFlowGraph();
		parseStatement(ifCFG);
		Block afterIfBlock = null;

		t = scanner.nextToken();
		if (t.getType() != Token.ELSE) {
			scanner.pushBack();
			afterIfBlock = new Block();
			cfg.add(new ConditionalBranchQuad(op, false, afterIfBlock));
			cfg.add(ifCFG, true);
			ifCFG.add(afterIfBlock, true);
		} else {
			ControlFlowGraph elseCFG = new ControlFlowGraph();
			parseStatement(elseCFG);
			cfg.add(new ConditionalBranchQuad(op, false, elseCFG.getStartBlock()));
			afterIfBlock = new Block();
			ifCFG.add(new UnconditionalBranchQuad(afterIfBlock));
			cfg.add(ifCFG, true);
			ifCFG.add(elseCFG, false);
			elseCFG.add(afterIfBlock, true);
		}
		cfg.setEndBlock(afterIfBlock);
	}

	/**
	 * @return
	 */
	private void parseWhileStatement(ControlFlowGraph preLoopCFG) throws IOException {
		Token t = scanner.nextToken();
		if (t.getType() != Token.WHILE) {
			throw new AssertionError("Expected while: " + t);
		}
		t = scanner.nextToken();
		if (t.getType() != Token.OPEN_PAREN) {
			throw new SyntaxError("Expected (", t);
		}
		ControlFlowGraph bodyCFG = new ControlFlowGraph();
		ControlFlowGraph expCFG = new ControlFlowGraph();
		Operand op = parseExpression(expCFG);
		t = scanner.nextToken();
		if (t.getType() != Token.CLOSE_PAREN) {
			throw new SyntaxError("Expected )", t);
		}

		parseStatement(bodyCFG);
		Block postLoopBlock = new Block();
		
		preLoopCFG.add(new UnconditionalBranchQuad(expCFG.getStartBlock()));
		preLoopCFG.add(bodyCFG, false);
		bodyCFG.add(expCFG, true);
		expCFG.add(new ConditionalBranchQuad(op, true, bodyCFG.getStartBlock()));
		expCFG.add(postLoopBlock, true);
		preLoopCFG.setEndBlock(postLoopBlock);
	}

	/**
	 * @param cfg
	 */
	private void parseReturnStatement(ControlFlowGraph cfg) throws IOException {
		Token t = scanner.nextToken();
		if (t.getType() != Token.RETURN) {
			throw new AssertionError("Expected return: " + t);
		}
		Operand op = parseExpression(cfg);
		t = scanner.nextToken();
		if (t.getType() != Token.SEMI_COLON) {
			throw new SyntaxError("Missing ;", t);
		}
		cfg.getEndBlock().add(new ExpReturnQuad(op));
		Block postRetBlock = new Block();
		cfg.add(postRetBlock, false);
		cfg.setEndBlock(postRetBlock);
	}

	/**
	 * @return
	 */
	public List getMethodArgs() {
		return methodArgs;
	}
}
