
package com.madhu.minc.sgc;

import java.io.PrintWriter;

public class ASMWriter {
	private static int START = 0;
	private static int DATA_SECTION = 1;
	private static int TEXT_SECTION = 2;

	private int section;
	private PrintWriter out;

	public ASMWriter(PrintWriter out) {
		this.out = out;
		section = START;
	}

	public void printInstruction(String opcode, Object dst, Object operand1,
			Object operand2) {
		setSection(TEXT_SECTION);

		out.print("\t");
		out.print(opcode);
		out.print("\t");
		out.print(dst.toString());

		if (operand1 != null) {
			out.print(", " + operand1.toString());
		}

		if (operand2 != null) {
			out.print(", " + operand2.toString());
		}

		out.println();
		out.flush();
	}

	public void enterProcedure(String procedureName) {
		setSection(TEXT_SECTION);
		out.println(procedureName + ":");
		out.flush();
	}

	public void exitProcedure() {
		out.println("\tjr\t$ra");
		out.flush();
	}

	public void printAddressInstruction(String opcode, Object reg, Object addr, int dest) {
		setSection(TEXT_SECTION);

		out.print("\t" + opcode + "\t");
		if (dest == 0) {
			out.print("(" + reg.toString() + "), ");
		} else {
			out.print(reg.toString() + ", ");
		}
		if (dest == 1) {
			out.println("(" + addr.toString() + ")");
		} else {
			out.println(addr.toString());
		}
		out.flush();
	}

	public void printDecl(Object type, Object sym) {
		setSection(DATA_SECTION);

		out.println("\t.comm\t" + sym.toString() + ",4");
		out.flush();
	}

	void setSection(int aSection) {
		if (section != aSection) {
			if (DATA_SECTION == aSection) {
				out.println("\t.data");
			} else if (TEXT_SECTION == aSection) {
				out.println("\t.text");
			} else {
				throw new IllegalStateException("Unknown section "
						+ Integer.toString(aSection));
			}
			section = aSection;
		}
		out.flush();
	}

	public void printLabel(String label) {
		out.printf("%s:%n", label);
	}
}
