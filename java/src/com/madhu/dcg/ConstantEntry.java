
/*
 * Copyright 1996-1998 Madhu Siddalingaiah
 * All rights reserved.
 * mailto:madhu@madhu.com http://www.madhu.com
 *
 * This software is the confidential and proprietary information
 * of Madhu Siddalingaiah ("Confidential Information").
 * Unauthorized duplication, modification, or distribution of this
 * software, its algorithms, documentation, or related intellectual
 * property without expressed written consent by Madhu Siddalingaiah
 * is strictly prohibited.
 */
package com.madhu.dcg;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ConstantEntry {
	public static final int CP_Utf8 = 1;
	public static final int CP_Integer = 3;
	public static final int CP_Float = 4;
	public static final int CP_Long = 5;
	public static final int CP_Double = 6;
	public static final int CP_Class = 7;
	public static final int CP_String = 8;
	public static final int CP_Fieldref = 9;
	public static final int CP_Methodref = 10;
	public static final int CP_InterfaceMethodref = 11;
	public static final int CP_NameAndType = 12;

	/**
	 * Used to flag if this constant has been defined in already
	 * in the generated code.
	 * It's a little bogus, but less bogus than the alternatives
	 */
	private boolean defined;

	public static ConstantEntry get(ByteArray byteArray) {
		int sval1, sval2;
		int tag = byteArray.readByte();
		ConstantEntry result;

		switch (tag) {
			case CP_Utf8:	/* Utf8 */
				int len = byteArray.readShort();
				StringBuffer sb = new StringBuffer(len);
				for (int j=0; j<len; j+=1) {
					int x = byteArray.readByte();
					int y, z;
					char c;
					if ((x & 0x80) > 0) {
						if ((x & 0xe0) == 0xc0) {
							y = byteArray.readByte();
							c = (char) (((x&0x1f)<<6) | (y&0x3f));
						} else {
							y = byteArray.readByte();
							z = byteArray.readByte();
							c = (char) (((x&0x0f)<<12) |
								((y&0x3f)<<6) | (z&0x3f));
						}
					} else {
						c = (char) x;
					}
					sb.append(c);
				}
				result = new Utf8Entry(sb.toString());
				break;

			case CP_Integer:
				result = new IntegerEntry(byteArray.readInt());
				break;

			case CP_Float:
				result = new FloatEntry(byteArray.readInt());
				break;

			case CP_Long:
				result = new LongEntry(byteArray.readInt(),
					byteArray.readInt());
				break;

			case CP_Double:
				result = new DoubleEntry(byteArray.readInt(),
					byteArray.readInt());
				break;

			case CP_Class:
				result = new ClassEntry(byteArray.readShort());
				break;

			case CP_String:
				result = new StringEntry(byteArray.readShort());
				break;

			case CP_Fieldref:
				sval1 = byteArray.readShort();
				sval2 = byteArray.readShort();
				result = new FieldRefEntry(sval1, sval2);
				break;

			case CP_Methodref:
				sval1 = byteArray.readShort();
				sval2 = byteArray.readShort();
				result = new MethodRefEntry(sval1, sval2);
				break;

			case CP_InterfaceMethodref:
				sval1 = byteArray.readShort();
				sval2 = byteArray.readShort();
				result = new InterfaceMethodRefEntry(sval1, sval2);
				break;

			case CP_NameAndType:
				sval1 = byteArray.readShort();
				sval2 = byteArray.readShort();
				result = new NameAndTypeEntry(sval1, sval2);
				break;

			default:
				throw new ClassFormatError("ConstantEntry.get: unknown constant type");
		}
		return result;
	}

	public void resolve(ConstantPool constants) {
	}

	public void setDefined(boolean value) {
		defined = value;
	}

	public boolean isDefined() {
		return defined;
	}

	public abstract void write(DataOutputStream dsOut) throws IOException;

	public String toString() {
		return "Unknown constant type";
	}
}
