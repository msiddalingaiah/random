
package com.madhu.dcg;

public class LabelRef {
	private int pc;
	private boolean isShort;
	private LabelRef next;

	public LabelRef(int inPC, boolean inIsShort) {
		pc = inPC;
		isShort = inIsShort;
	}

	public LabelRef getNext() {
		return next;
	}

	public void setNext(LabelRef inNext) {
		next = inNext;
	}

	public void resolve(int targetPC, byte[] code) {
		int offset = targetPC - pc;
		if (isShort) {
			code[pc+1] = (byte) (offset >> 8);
			code[pc+2] = (byte) (offset & 0xff);
		} else {
			code[pc+1] = (byte) ((offset >> 24) & 0xff);
			code[pc+2] = (byte) ((offset >> 16) & 0xff);
			code[pc+3] = (byte) ((offset >> 8) & 0xff);
			code[pc+4] = (byte) (offset & 0xff);
		}
	}
}
