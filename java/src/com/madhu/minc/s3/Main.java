
package com.madhu.minc.s3;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Terminal plus = new Terminal("PLUS");
		Terminal mem = new Terminal("MEM");
		Terminal con = new Terminal("CONST");
		StateTree plustree = new StateTree(plus, new StateTree(mem), new StateTree(con));
		StateTree t2 = new StateTree(plus, new StateTree(mem), plustree);
		StateTree subject = t2;

		NonTerminal reg = new NonTerminal("reg");
		Tree p1 = new Tree(plus, new Tree(mem), new Tree(con));
		Tree p2 = new Tree(plus, new Tree(mem), new Tree(reg));
		Tree p4 = new Tree(con);
		
		Matcher m = new Matcher();
		m.addRule(new Rule(reg, p1, 1));
		m.addRule(new Rule(reg, p2, 2));
		m.addRule(new Rule(reg, p4, 0));

		m.label(subject);
		if (subject.getState().getCost(reg) >= Match.NO_MATCH) {
			System.out.println("No cover!");
		}
		TreeFrame tf = new TreeFrame("Cover", subject);
		tf.setSize(400, 300);
		tf.setVisible(true);
	}
}
