
package com.madhu.minc.sgc;

import java.util.HashMap;

import com.madhu.minc.sgc.Register;
import com.madhu.minc.sgc.SGCTree;
import com.madhu.minc.sgc.Variable;

public class RegisterAllocator {
	private Register[] registers;
	private HashMap<String,Variable> symbols;
	private int tempCount;

	public RegisterAllocator(HashMap<String,Variable> symbols) {
		this.symbols = symbols;
		registers = new Register[16];
		int n = registers.length;
		for (int i = 0; i < n; i += 1) {
			registers[i] = new Register(i + 1);
		}
	}

	public Register allocate(SGCTree p) {
		Variable var = symbols.get(p.getText());
		if (var == null) {
			var = symbols.get(p.getText());
			var = new Variable(String.format("_t_%d", tempCount++),
					SyntaxParser.INT);
			var.setStartAddress(p.getAddress());
			//symbols.put(var.getName(), var);
		}
		freeUnusedRegs(p.getAddress());
		Register reg = var.getRegister();
		if (reg == null) {
			reg = doAllocate();
			reg.allocate(var);
		}
		return reg;
	}

	private void freeUnusedRegs(int number) {
		for (Register r : registers) {
			if (!r.isAvailable() && !r.getVariable().isTemporary() &&
					number > r.getVariable().getEndAddress()) {
				r.release();
			}
		}
	}

	private Register doAllocate() {
		for (Register r : registers) {
			if (r.isAvailable()) {
				return r;
			}
		}
		// TODO spill
		return null;
	}
}
