
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

public class OPCodes {
	public static byte sizes[] = {
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 2, 3, // 19
		3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1,
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3,
		3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 0, 0, 1, 1, 1, 1, 1, 1, 3, 3, // 179
		3, 3, 3, 3, 3, 5, 0, 3, 2, 3, 1, 1, 3, 3, 1, 1, 0, 4, 3, 3, // 199
		5, 5
	};
	
	public final static int _nop0 = 0;
	public final static int _aconst_null = 1;
	public final static int _iconst_m1 = 2;
	public final static int _iconst_0 = 3;
	public final static int _iconst_1 = 4;
	public final static int _iconst_2 = 5;
	public final static int _iconst_3 = 6;
	public final static int _iconst_4 = 7;
	public final static int _iconst_5 = 8;
	public final static int _lconst_0 = 9;
	public final static int _lconst_1 = 10;
	public final static int _fconst_0 = 11;
	public final static int _fconst_1 = 12;
	public final static int _fconst_2 = 13;
	public final static int _dconst_0 = 14;
	public final static int _dconst_1 = 15;
	public final static int _bipush = 16;
	public final static int _sipush = 17;
	public final static int _ldc = 18;
	public final static int _ldc_w = 19;
	public final static int _ldc2_w = 20;
	public final static int _iload = 21;
	public final static int _lload = 22;
	public final static int _fload = 23;
	public final static int _dload = 24;
	public final static int _aload = 25;
	public final static int _iload_0 = 26;
	public final static int _iload_1 = 27;
	public final static int _iload_2 = 28;
	public final static int _iload_3 = 29;
	public final static int _lload_0 = 30;
	public final static int _lload_1 = 31;
	public final static int _lload_2 = 32;
	public final static int _lload_3 = 33;
	public final static int _fload_0 = 34;
	public final static int _fload_1 = 35;
	public final static int _fload_2 = 36;
	public final static int _fload_3 = 37;
	public final static int _dload_0 = 38;
	public final static int _dload_1 = 39;
	public final static int _dload_2 = 40;
	public final static int _dload_3 = 41;
	public final static int _aload_0 = 42;
	public final static int _aload_1 = 43;
	public final static int _aload_2 = 44;
	public final static int _aload_3 = 45;
	public final static int _iaload = 46;
	public final static int _laload = 47;
	public final static int _faload = 48;
	public final static int _daload = 49;
	public final static int _aaload = 50;
	public final static int _baload = 51;
	public final static int _caload = 52;
	public final static int _saload = 53;
	public final static int _istore = 54;
	public final static int _lstore = 55;
	public final static int _fstore = 56;
	public final static int _dstore = 57;
	public final static int _astore = 58;
	public final static int _istore_0 = 59;
	public final static int _istore_1 = 60;
	public final static int _istore_2 = 61;
	public final static int _istore_3 = 62;
	public final static int _lstore_0 = 63;
	public final static int _lstore_1 = 64;
	public final static int _lstore_2 = 65;
	public final static int _lstore_3 = 66;
	public final static int _fstore_0 = 67;
	public final static int _fstore_1 = 68;
	public final static int _fstore_2 = 69;
	public final static int _fstore_3 = 70;
	public final static int _dstore_0 = 71;
	public final static int _dstore_1 = 72;
	public final static int _dstore_2 = 73;
	public final static int _dstore_3 = 74;
	public final static int _astore_0 = 75;
	public final static int _astore_1 = 76;
	public final static int _astore_2 = 77;
	public final static int _astore_3 = 78;
	public final static int _iastore = 79;
	public final static int _lastore = 80;
	public final static int _fastore = 81;
	public final static int _dastore = 82;
	public final static int _aastore = 83;
	public final static int _bastore = 84;
	public final static int _castore = 85;
	public final static int _sastore = 86;
	public final static int _pop = 87;
	public final static int _pop2 = 88;
	public final static int _dup = 89;
	public final static int _dup_x1 = 90;
	public final static int _dup_x2 = 91;
	public final static int _dup2 = 92;
	public final static int _dup2_x1 = 93;
	public final static int _dup2_x2 = 94;
	public final static int _swap = 95;
	public final static int _iadd = 96;
	public final static int _ladd = 97;
	public final static int _fadd = 98;
	public final static int _dadd = 99;
	public final static int _isub = 100;
	public final static int _lsub = 101;
	public final static int _fsub = 102;
	public final static int _dsub = 103;
	public final static int _imul = 104;
	public final static int _lmul = 105;
	public final static int _fmul = 106;
	public final static int _dmul = 107;
	public final static int _idiv = 108;
	public final static int _ldiv = 109;
	public final static int _fdiv = 110;
	public final static int _ddiv = 111;
	public final static int _irem = 112;
	public final static int _lrem = 113;
	public final static int _frem = 114;
	public final static int _drem = 115;
	public final static int _ineg = 116;
	public final static int _lneg = 117;
	public final static int _fneg = 118;
	public final static int _dneg = 119;
	public final static int _ishl = 120;
	public final static int _lshl = 121;
	public final static int _ishr = 122;
	public final static int _lshr = 123;
	public final static int _iushr = 124;
	public final static int _lushr = 125;
	public final static int _iand = 126;
	public final static int _land = 127;
	public final static int _ior = 128;
	public final static int _lor = 129;
	public final static int _ixor = 130;
	public final static int _lxor = 131;
	public final static int _iinc = 132;
	public final static int _i2l = 133;
	public final static int _i2f = 134;
	public final static int _i2d = 135;
	public final static int _l2i = 136;
	public final static int _l2f = 137;
	public final static int _l2d = 138;
	public final static int _f2i = 139;
	public final static int _f2l = 140;
	public final static int _f2d = 141;
	public final static int _d2i = 142;
	public final static int _d2l = 143;
	public final static int _d2f = 144;
	public final static int _int2byte = 145;
	public final static int _int2char = 146;
	public final static int _int2short = 147;
	public final static int _lcmp = 148;
	public final static int _fcmpl = 149;
	public final static int _fcmpg = 150;
	public final static int _dcmpl = 151;
	public final static int _dcmpg = 152;
	public final static int _ifeq = 153;
	public final static int _ifne = 154;
	public final static int _iflt = 155;
	public final static int _ifge = 156;
	public final static int _ifgt = 157;
	public final static int _ifle = 158;
	public final static int _if_icmpeq = 159;
	public final static int _if_icmpne = 160;
	public final static int _if_icmplt = 161;
	public final static int _if_icmpge = 162;
	public final static int _if_icmpgt = 163;
	public final static int _if_icmple = 164;
	public final static int _if_acmpeq = 165;
	public final static int _if_acmpne = 166;
	public final static int _goto = 167;
	public final static int _jsr = 168;
	public final static int _ret = 169;
	public final static int _tableswitch = 170;
	public final static int _lookupswitch = 171;
	public final static int _ireturn = 172;
	public final static int _lreturn = 173;
	public final static int _freturn = 174;
	public final static int _dreturn = 175;
	public final static int _areturn = 176;
	public final static int _return = 177;
	public final static int _getstatic = 178;
	public final static int _putstatic = 179;
	public final static int _getfield = 180;
	public final static int _putfield = 181;
	public final static int _invokevirtual = 182;
	public final static int _invokenonvirtual = 183;
	public final static int _invokestatic = 184;
	public final static int _invokeinterface = 185;
	public final static int _xxxunusedxxx = 186;
	public final static int _new = 187;
	public final static int _newarray = 188;
	public final static int _anewarray = 189;
	public final static int _arraylength = 190;
	public final static int _athrow = 191;
	public final static int _checkcast = 192;
	public final static int _instanceof = 193;
	public final static int _monitorenter = 194;
	public final static int _monitorexit = 195;
	public final static int _wide = 196;
	public final static int _multianewarray = 197;
	public final static int _ifnull = 198;
	public final static int _ifnonnull = 199;
	public final static int _goto_w = 200;
	public final static int _jsr_w = 201;
}
