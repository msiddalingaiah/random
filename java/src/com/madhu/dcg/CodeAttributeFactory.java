
package com.madhu.dcg;

import java.util.HashMap;

public class CodeAttributeFactory {
	private byte code[];
	private int pc;
	private int maxStack;
	private int maxLocals;
	private ExceptionEntry table[];
	private AttributeInfo atts[];
	private HashMap labelRefs;
	private HashMap labelDefs;

	public CodeAttributeFactory(int maxSize, int inMaxStack,
		int inMaxLocals) {

		this(maxSize, inMaxStack, inMaxLocals,
			new ExceptionEntry[0], new AttributeInfo[0]);
	}

	public CodeAttributeFactory(int maxSize, int inMaxStack,
		int inMaxLocals, ExceptionEntry inTable[],
		AttributeInfo inAtts[]) {

		code = new byte[maxSize];
		maxStack = inMaxStack;
		maxLocals = inMaxLocals;
		table = inTable;
		atts = inAtts;
		labelRefs = new HashMap();
		labelDefs = new HashMap();
	}

	public void addByte(byte inByte) {
		code[pc++] = inByte;
	}

	public void addShort(short inShort) {
		code[pc++] = (byte) (inShort >> 8);
		code[pc++] = (byte) (inShort & 0xff);
	}

	public void addShortBranch(byte opCode, int branchLabel) {
		LabelRef lr = new LabelRef(pc, true);
		code[pc++] = opCode;
		code[pc++] = 0;
		code[pc++] = 0;
		Integer ib = new Integer(branchLabel);
		Object o = labelDefs.get(ib);
		if (o != null) {
			lr.resolve(((Integer) o).intValue(), code);
			return;
		}
		LabelRef next = (LabelRef) labelRefs.get(ib);
		lr.setNext(next);
		labelRefs.put(ib, lr);
	}

	public void addLabelDef(int branchLabel) {
		Integer ib = new Integer(branchLabel);
		labelDefs.put(ib, new Integer(pc));
		LabelRef lr = (LabelRef) labelRefs.get(ib);
		while (lr != null) {
			lr.resolve(pc, code);
			lr = lr.getNext();
		}
		labelRefs.put(ib, null);
	}

	public CodeAttribute createCodeAttribute(int nameIndex) {
		byte[] c = new byte[pc];
		System.arraycopy(code, 0, c, 0, pc);
		int length = 2 + 2 + 4 + pc + 2 + 2;
		return new CodeAttribute(length, nameIndex, maxStack,
			maxLocals, c, table, atts);
	}
}
