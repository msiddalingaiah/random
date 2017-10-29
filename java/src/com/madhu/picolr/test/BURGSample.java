
package com.madhu.picolr.test;

import java.util.ArrayList;
import java.util.Collections;

import com.madhu.picolr.burg.AST;
import com.madhu.picolr.burg.BURGRule;
import com.madhu.picolr.burg.Match;
import com.madhu.picolr.burg.Rule;
import com.madhu.picolr.burg.RuleFactory;
import com.madhu.picolr.burg.Symbol;

/*
 * Created on May 2, 2008 at 12:58:22 PM
 */
public class BURGSample {
	/*
	stmt:	ASGNI(disp,reg) = 4 (1);
	stmt:	reg = 5;
	reg:	ADDI(reg,rc) = 6 (1);
	reg:	CVCI(INDIRC(disp)) = 7 (1);
	reg:	I0I = 8;
	reg:	disp = 9 (1);
	disp:	ADDI(reg,con) = 10;
	disp:	ADDRLP = 11;
	rc:	con = 12;
	rc:	reg = 13;
	con:	CNSTI = 14;
	con:	I0I = 15;
	
i = c + 4;
stmt: ASGNI(disp,reg)
 disp: ADDRLP
 reg: disp
  disp: ADDI(reg,con)
   reg: CVCI(INDIRC(disp))
    disp: ADDRLP
   con: CNSTI

con: CNSTI
disp: ADDRLP
reg: (CVCI (INDIRC disp))
disp: (ADDI reg con)
reg: disp
disp: ADDRLP
stmt: (ASGNI disp reg)
	 */
	@BURGRule(rule="stmt: ASGNI(disp,reg)", cost=1)
	public Object assignStmt(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="stmt: reg")
	public Object regStmt(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="reg: ADDI(reg,rc)", cost=1)
	public Object addiRC(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="reg: CVCI(INDIRC(disp))", cost=1)
	public Object cvci(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="reg: I0I")
	public Object ioi(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="reg: disp", cost=1)
	public Object disp(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="disp: ADDI(reg,con)")
	public Object addiCon(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="disp: ADDRLP")
	public Object addlrp(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="rc: con")
	public Object con(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="rc: reg")
	public Object regRC(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	@BURGRule(rule="con: CNSTI")
	public Object consti(AST ast, Rule rule) {
		System.out.printf("%s - %s%n", ast, rule);
		return null;
	}

	public static void main(String[] args) throws Exception {
		RuleFactory rf = new RuleFactory();
		BURGSample gen = new BURGSample();
		rf.create(gen);
//		rf.addRule("con: I0I");
//		rf.addRule("con: CNSTI");
//		rf.addRule("disp: ADDRLP");
//		rf.addRule("disp: ADDI(reg,con)");
//		rf.addRule("reg: I0I");
//		rf.addRule("reg: CVCI(INDIRC(disp))", 1);
//		rf.addRule("reg: ADDI(reg,rc)", 1);
//		rf.addRule("stmt: ASGNI(disp,reg)", 1);
//
//		rf.addRule("rc: reg");
//		rf.addRule("stmt: reg");
//		rf.addRule("rc: con");
//		rf.addRule("reg: disp", 1);

		System.out.println("i = c + 4;");
		AST c4 = new AST(new Symbol("CNSTI", "4"));
		AST cv = new AST(new Symbol("CVCI"), new AST(new Symbol("INDIRC"), new AST(new Symbol("ADDRLP"))));
		AST t = new AST(new Symbol("ASGNI"),
			new AST(new Symbol("ADDRLP")),
			new AST(new Symbol("ADDI"), cv, c4));

		System.out.println(t);

		t.label(rf);
		System.out.println(t.getCost(new Symbol("stmt")));
		t.dumpCover(new Symbol("stmt"), 0);
		System.out.println();

		System.out.println();
		ArrayList<Match> red = t.reduce("stmt");
		Collections.reverse(red);
		for (Match m : red) {
			System.out.println(m);
			AST ast = m.getAST();
			Rule rule = m.getRule();
			//Object result = rule.getAction().invoke(gen, ast, rule);
			//ast.setResult(result);
		}
	}
}
