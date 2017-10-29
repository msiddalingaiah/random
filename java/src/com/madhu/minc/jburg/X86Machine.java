
package com.madhu.minc.jburg;

import com.madhu.minc.jburg.State;
import com.madhu.minc.jburg.STree;
import com.madhu.minc.jburg.PatternMatcher;

public class X86Machine extends PatternMatcher {
	private static final int burm_stmt_NT = 1;
	private static final int burm_reg_NT = 2;
	private static final int burm_con_NT = 3;
	private static final int burm_acon_NT = 4;
	private static final int burm_base_NT = 5;
	private static final int burm_index_NT = 6;
	private static final int burm_con1_NT = 7;
	private static final int burm_con2_NT = 8;
	private static final int burm_con3_NT = 9;
	private static final int burm_addr_NT = 10;
	private static final int burm_mem_NT = 11;
	private static final int burm_rc_NT = 12;
	private static final int burm_mr_NT = 13;
	private static final int burm_mrc0_NT = 14;
	private static final int burm_mrc1_NT = 15;
	private static final int burm_mrc3_NT = 16;
	private static final int burm_con5_NT = 17;
	private static final int burm_memf_NT = 18;
	private static final int burm_flt_NT = 19;
	private static final int burm_addrj_NT = 20;
	private static final int burm_cmpf_NT = 21;

	private static short burm_nts_0[] = { 0 };
	private static short burm_nts_1[] = { burm_reg_NT, 0 };
	private static short burm_nts_2[] = { burm_con_NT, 0 };
	private static short burm_nts_3[] = { burm_reg_NT, burm_acon_NT, 0 };
	private static short burm_nts_4[] = { burm_reg_NT, burm_con1_NT, 0 };
	private static short burm_nts_5[] = { burm_reg_NT, burm_con2_NT, 0 };
	private static short burm_nts_6[] = { burm_reg_NT, burm_con3_NT, 0 };
	private static short burm_nts_7[] = { burm_base_NT, 0 };
	private static short burm_nts_8[] = { burm_index_NT, burm_base_NT, 0 };
	private static short burm_nts_9[] = { burm_index_NT, 0 };
	private static short burm_nts_10[] = { burm_addr_NT, 0 };
	private static short burm_nts_11[] = { burm_mem_NT, 0 };
	private static short burm_nts_12[] = { burm_rc_NT, 0 };
	private static short burm_nts_13[] = { burm_mrc0_NT, 0 };
	private static short burm_nts_14[] = { burm_reg_NT, burm_mrc1_NT, 0 };
	private static short burm_nts_15[] = { burm_addr_NT, burm_mem_NT, burm_con1_NT, 0 };
	private static short burm_nts_16[] = { burm_addr_NT, burm_mem_NT, burm_rc_NT, 0 };
	private static short burm_nts_17[] = { burm_addr_NT, burm_mem_NT, 0 };
	private static short burm_nts_18[] = { burm_reg_NT, burm_con5_NT, 0 };
	private static short burm_nts_19[] = { burm_addr_NT, burm_mem_NT, burm_con5_NT, 0 };
	private static short burm_nts_20[] = { burm_reg_NT, burm_reg_NT, 0 };
	private static short burm_nts_21[] = { burm_reg_NT, burm_mrc3_NT, 0 };
	private static short burm_nts_22[] = { burm_con_NT, burm_mr_NT, 0 };
	private static short burm_nts_23[] = { burm_reg_NT, burm_mr_NT, 0 };
	private static short burm_nts_24[] = { burm_addr_NT, burm_rc_NT, 0 };
	private static short burm_nts_25[] = { burm_mrc3_NT, 0 };
	private static short burm_nts_26[] = { burm_memf_NT, 0 };
	private static short burm_nts_27[] = { burm_addr_NT, burm_reg_NT, 0 };
	private static short burm_nts_28[] = { burm_reg_NT, burm_flt_NT, 0 };
	private static short burm_nts_29[] = { burm_addrj_NT, 0 };
	private static short burm_nts_30[] = { burm_mem_NT, burm_rc_NT, 0 };
	private static short burm_nts_31[] = { burm_cmpf_NT, burm_reg_NT, 0 };

	private short burm_nts[][] = {
		null,	/* 0 */
		burm_nts_0,	/* 1 */
		burm_nts_0,	/* 2 */
		burm_nts_0,	/* 3 */
		burm_nts_0,	/* 4 */
		burm_nts_0,	/* 5 */
		burm_nts_0,	/* 6 */
		burm_nts_0,	/* 7 */
		burm_nts_0,	/* 8 */
		burm_nts_0,	/* 9 */
		burm_nts_0,	/* 10 */
		burm_nts_0,	/* 11 */
		burm_nts_0,	/* 12 */
		burm_nts_1,	/* 13 */
		burm_nts_1,	/* 14 */
		burm_nts_1,	/* 15 */
		burm_nts_1,	/* 16 */
		burm_nts_1,	/* 17 */
		burm_nts_1,	/* 18 */
		burm_nts_1,	/* 19 */
		burm_nts_1,	/* 20 */
		burm_nts_1,	/* 21 */
		burm_nts_1,	/* 22 */
		burm_nts_1,	/* 23 */
		burm_nts_1,	/* 24 */
		burm_nts_0,	/* 25 */
		burm_nts_0,	/* 26 */
		burm_nts_0,	/* 27 */
		burm_nts_0,	/* 28 */
		burm_nts_0,	/* 29 */
		burm_nts_0,	/* 30 */
		burm_nts_0,	/* 31 */
		burm_nts_0,	/* 32 */
		burm_nts_0,	/* 33 */
		burm_nts_0,	/* 34 */
		burm_nts_1,	/* 35 */
		burm_nts_0,	/* 36 */
		burm_nts_2,	/* 37 */
		burm_nts_0,	/* 38 */
		burm_nts_1,	/* 39 */
		burm_nts_3,	/* 40 */
		burm_nts_3,	/* 41 */
		burm_nts_3,	/* 42 */
		burm_nts_0,	/* 43 */
		burm_nts_0,	/* 44 */
		burm_nts_1,	/* 45 */
		burm_nts_4,	/* 46 */
		burm_nts_5,	/* 47 */
		burm_nts_6,	/* 48 */
		burm_nts_0,	/* 49 */
		burm_nts_0,	/* 50 */
		burm_nts_0,	/* 51 */
		burm_nts_0,	/* 52 */
		burm_nts_0,	/* 53 */
		burm_nts_0,	/* 54 */
		burm_nts_4,	/* 55 */
		burm_nts_5,	/* 56 */
		burm_nts_6,	/* 57 */
		burm_nts_7,	/* 58 */
		burm_nts_8,	/* 59 */
		burm_nts_8,	/* 60 */
		burm_nts_8,	/* 61 */
		burm_nts_9,	/* 62 */
		burm_nts_10,	/* 63 */
		burm_nts_10,	/* 64 */
		burm_nts_10,	/* 65 */
		burm_nts_10,	/* 66 */
		burm_nts_10,	/* 67 */
		burm_nts_10,	/* 68 */
		burm_nts_10,	/* 69 */
		burm_nts_1,	/* 70 */
		burm_nts_2,	/* 71 */
		burm_nts_1,	/* 72 */
		burm_nts_11,	/* 73 */
		burm_nts_11,	/* 74 */
		burm_nts_12,	/* 75 */
		burm_nts_11,	/* 76 */
		burm_nts_12,	/* 77 */
		burm_nts_11,	/* 78 */
		burm_nts_12,	/* 79 */
		burm_nts_10,	/* 80 */
		burm_nts_13,	/* 81 */
		burm_nts_1,	/* 82 */
		burm_nts_1,	/* 83 */
		burm_nts_1,	/* 84 */
		burm_nts_1,	/* 85 */
		burm_nts_1,	/* 86 */
		burm_nts_1,	/* 87 */
		burm_nts_1,	/* 88 */
		burm_nts_14,	/* 89 */
		burm_nts_14,	/* 90 */
		burm_nts_14,	/* 91 */
		burm_nts_14,	/* 92 */
		burm_nts_14,	/* 93 */
		burm_nts_14,	/* 94 */
		burm_nts_14,	/* 95 */
		burm_nts_14,	/* 96 */
		burm_nts_14,	/* 97 */
		burm_nts_14,	/* 98 */
		burm_nts_14,	/* 99 */
		burm_nts_14,	/* 100 */
		burm_nts_15,	/* 101 */
		burm_nts_15,	/* 102 */
		burm_nts_15,	/* 103 */
		burm_nts_15,	/* 104 */
		burm_nts_15,	/* 105 */
		burm_nts_15,	/* 106 */
		burm_nts_16,	/* 107 */
		burm_nts_16,	/* 108 */
		burm_nts_16,	/* 109 */
		burm_nts_16,	/* 110 */
		burm_nts_16,	/* 111 */
		burm_nts_16,	/* 112 */
		burm_nts_16,	/* 113 */
		burm_nts_16,	/* 114 */
		burm_nts_16,	/* 115 */
		burm_nts_16,	/* 116 */
		burm_nts_1,	/* 117 */
		burm_nts_1,	/* 118 */
		burm_nts_1,	/* 119 */
		burm_nts_17,	/* 120 */
		burm_nts_17,	/* 121 */
		burm_nts_17,	/* 122 */
		burm_nts_18,	/* 123 */
		burm_nts_18,	/* 124 */
		burm_nts_18,	/* 125 */
		burm_nts_18,	/* 126 */
		burm_nts_19,	/* 127 */
		burm_nts_19,	/* 128 */
		burm_nts_19,	/* 129 */
		burm_nts_19,	/* 130 */
		burm_nts_0,	/* 131 */
		burm_nts_20,	/* 132 */
		burm_nts_20,	/* 133 */
		burm_nts_20,	/* 134 */
		burm_nts_20,	/* 135 */
		burm_nts_21,	/* 136 */
		burm_nts_22,	/* 137 */
		burm_nts_23,	/* 138 */
		burm_nts_20,	/* 139 */
		burm_nts_20,	/* 140 */
		burm_nts_20,	/* 141 */
		burm_nts_20,	/* 142 */
		burm_nts_1,	/* 143 */
		burm_nts_1,	/* 144 */
		burm_nts_10,	/* 145 */
		burm_nts_10,	/* 146 */
		burm_nts_10,	/* 147 */
		burm_nts_10,	/* 148 */
		burm_nts_1,	/* 149 */
		burm_nts_1,	/* 150 */
		burm_nts_1,	/* 151 */
		burm_nts_1,	/* 152 */
		burm_nts_1,	/* 153 */
		burm_nts_1,	/* 154 */
		burm_nts_1,	/* 155 */
		burm_nts_1,	/* 156 */
		burm_nts_24,	/* 157 */
		burm_nts_24,	/* 158 */
		burm_nts_24,	/* 159 */
		burm_nts_24,	/* 160 */
		burm_nts_24,	/* 161 */
		burm_nts_24,	/* 162 */
		burm_nts_24,	/* 163 */
		burm_nts_25,	/* 164 */
		burm_nts_25,	/* 165 */
		burm_nts_25,	/* 166 */
		burm_nts_20,	/* 167 */
		burm_nts_1,	/* 168 */
		burm_nts_10,	/* 169 */
		burm_nts_10,	/* 170 */
		burm_nts_10,	/* 171 */
		burm_nts_26,	/* 172 */
		burm_nts_27,	/* 173 */
		burm_nts_27,	/* 174 */
		burm_nts_27,	/* 175 */
		burm_nts_1,	/* 176 */
		burm_nts_1,	/* 177 */
		burm_nts_1,	/* 178 */
		burm_nts_1,	/* 179 */
		burm_nts_26,	/* 180 */
		burm_nts_1,	/* 181 */
		burm_nts_28,	/* 182 */
		burm_nts_28,	/* 183 */
		burm_nts_28,	/* 184 */
		burm_nts_28,	/* 185 */
		burm_nts_28,	/* 186 */
		burm_nts_28,	/* 187 */
		burm_nts_28,	/* 188 */
		burm_nts_28,	/* 189 */
		burm_nts_1,	/* 190 */
		burm_nts_1,	/* 191 */
		burm_nts_1,	/* 192 */
		burm_nts_10,	/* 193 */
		burm_nts_1,	/* 194 */
		burm_nts_1,	/* 195 */
		burm_nts_0,	/* 196 */
		burm_nts_1,	/* 197 */
		burm_nts_11,	/* 198 */
		burm_nts_29,	/* 199 */
		burm_nts_0,	/* 200 */
		burm_nts_30,	/* 201 */
		burm_nts_30,	/* 202 */
		burm_nts_30,	/* 203 */
		burm_nts_30,	/* 204 */
		burm_nts_30,	/* 205 */
		burm_nts_30,	/* 206 */
		burm_nts_30,	/* 207 */
		burm_nts_30,	/* 208 */
		burm_nts_30,	/* 209 */
		burm_nts_30,	/* 210 */
		burm_nts_14,	/* 211 */
		burm_nts_14,	/* 212 */
		burm_nts_14,	/* 213 */
		burm_nts_14,	/* 214 */
		burm_nts_14,	/* 215 */
		burm_nts_14,	/* 216 */
		burm_nts_14,	/* 217 */
		burm_nts_14,	/* 218 */
		burm_nts_14,	/* 219 */
		burm_nts_14,	/* 220 */
		burm_nts_14,	/* 221 */
		burm_nts_14,	/* 222 */
		burm_nts_26,	/* 223 */
		burm_nts_1,	/* 224 */
		burm_nts_31,	/* 225 */
		burm_nts_31,	/* 226 */
		burm_nts_31,	/* 227 */
		burm_nts_31,	/* 228 */
		burm_nts_31,	/* 229 */
		burm_nts_31,	/* 230 */
		burm_nts_31,	/* 231 */
		burm_nts_31,	/* 232 */
		burm_nts_31,	/* 233 */
		burm_nts_31,	/* 234 */
		burm_nts_31,	/* 235 */
		burm_nts_31,	/* 236 */
		burm_nts_29,	/* 237 */
		burm_nts_29,	/* 238 */
		burm_nts_29,	/* 239 */
		burm_nts_29,	/* 240 */
		burm_nts_29,	/* 241 */
		burm_nts_29,	/* 242 */
		burm_nts_29,	/* 243 */
		burm_nts_29,	/* 244 */
		burm_nts_1,	/* 245 */
		burm_nts_1,	/* 246 */
		burm_nts_1,	/* 247 */
		burm_nts_1,	/* 248 */
		burm_nts_1,	/* 249 */
	};

	public short[] getNonTerminals(int eruleno) {
		return burm_nts[eruleno];
	}

	private static final int CNSTF4 = 4113;
	private static final int CNSTF8 = 8209;
	private static final int CNSTF16 = 16401;
	private static final int CNSTI1 = 1045;
	private static final int CNSTI2 = 2069;
	private static final int CNSTI4 = 4117;
	private static final int CNSTI8 = 8213;
	private static final int CNSTP4 = 4119;
	private static final int CNSTP8 = 8215;
	private static final int CNSTU1 = 1046;
	private static final int CNSTU2 = 2070;
	private static final int CNSTU4 = 4118;
	private static final int CNSTU8 = 8214;
	private static final int ARGB = 41;
	private static final int ARGF4 = 4129;
	private static final int ARGF8 = 8225;
	private static final int ARGF16 = 16417;
	private static final int ARGI4 = 4133;
	private static final int ARGI8 = 8229;
	private static final int ARGP4 = 4135;
	private static final int ARGP8 = 8231;
	private static final int ARGU4 = 4134;
	private static final int ARGU8 = 8230;
	private static final int ASGNB = 57;
	private static final int ASGNF4 = 4145;
	private static final int ASGNF8 = 8241;
	private static final int ASGNF16 = 16433;
	private static final int ASGNI1 = 1077;
	private static final int ASGNI2 = 2101;
	private static final int ASGNI4 = 4149;
	private static final int ASGNI8 = 8245;
	private static final int ASGNP4 = 4151;
	private static final int ASGNP8 = 8247;
	private static final int ASGNU1 = 1078;
	private static final int ASGNU2 = 2102;
	private static final int ASGNU4 = 4150;
	private static final int ASGNU8 = 8246;
	private static final int INDIRB = 73;
	private static final int INDIRF4 = 4161;
	private static final int INDIRF8 = 8257;
	private static final int INDIRF16 = 16449;
	private static final int INDIRI1 = 1093;
	private static final int INDIRI2 = 2117;
	private static final int INDIRI4 = 4165;
	private static final int INDIRI8 = 8261;
	private static final int INDIRP4 = 4167;
	private static final int INDIRP8 = 8263;
	private static final int INDIRU1 = 1094;
	private static final int INDIRU2 = 2118;
	private static final int INDIRU4 = 4166;
	private static final int INDIRU8 = 8262;
	private static final int CVFF4 = 4209;
	private static final int CVFF8 = 8305;
	private static final int CVFF16 = 16497;
	private static final int CVFI4 = 4213;
	private static final int CVFI8 = 8309;
	private static final int CVIF4 = 4225;
	private static final int CVIF8 = 8321;
	private static final int CVIF16 = 16513;
	private static final int CVII1 = 1157;
	private static final int CVII2 = 2181;
	private static final int CVII4 = 4229;
	private static final int CVII8 = 8325;
	private static final int CVIU1 = 1158;
	private static final int CVIU2 = 2182;
	private static final int CVIU4 = 4230;
	private static final int CVIU8 = 8326;
	private static final int CVPP4 = 4247;
	private static final int CVPP8 = 8343;
	private static final int CVPP16 = 16535;
	private static final int CVPU4 = 4246;
	private static final int CVPU8 = 8342;
	private static final int CVUI1 = 1205;
	private static final int CVUI2 = 2229;
	private static final int CVUI4 = 4277;
	private static final int CVUI8 = 8373;
	private static final int CVUP4 = 4279;
	private static final int CVUP8 = 8375;
	private static final int CVUP16 = 16567;
	private static final int CVUU1 = 1206;
	private static final int CVUU2 = 2230;
	private static final int CVUU4 = 4278;
	private static final int CVUU8 = 8374;
	private static final int NEGF4 = 4289;
	private static final int NEGF8 = 8385;
	private static final int NEGF16 = 16577;
	private static final int NEGI4 = 4293;
	private static final int NEGI8 = 8389;
	private static final int CALLB = 217;
	private static final int CALLF4 = 4305;
	private static final int CALLF8 = 8401;
	private static final int CALLF16 = 16593;
	private static final int CALLI4 = 4309;
	private static final int CALLI8 = 8405;
	private static final int CALLP4 = 4311;
	private static final int CALLP8 = 8407;
	private static final int CALLU4 = 4310;
	private static final int CALLU8 = 8406;
	private static final int CALLV = 216;
	private static final int RETF4 = 4337;
	private static final int RETF8 = 8433;
	private static final int RETF16 = 16625;
	private static final int RETI4 = 4341;
	private static final int RETI8 = 8437;
	private static final int RETP4 = 4343;
	private static final int RETP8 = 8439;
	private static final int RETU4 = 4342;
	private static final int RETU8 = 8438;
	private static final int RETV = 248;
	private static final int ADDRGP4 = 4359;
	private static final int ADDRGP8 = 8455;
	private static final int ADDRFP4 = 4375;
	private static final int ADDRFP8 = 8471;
	private static final int ADDRLP4 = 4391;
	private static final int ADDRLP8 = 8487;
	private static final int ADDF4 = 4401;
	private static final int ADDF8 = 8497;
	private static final int ADDF16 = 16689;
	private static final int ADDI4 = 4405;
	private static final int ADDI8 = 8501;
	private static final int ADDP4 = 4407;
	private static final int ADDP8 = 8503;
	private static final int ADDU4 = 4406;
	private static final int ADDU8 = 8502;
	private static final int SUBF4 = 4417;
	private static final int SUBF8 = 8513;
	private static final int SUBF16 = 16705;
	private static final int SUBI4 = 4421;
	private static final int SUBI8 = 8517;
	private static final int SUBP4 = 4423;
	private static final int SUBP8 = 8519;
	private static final int SUBU4 = 4422;
	private static final int SUBU8 = 8518;
	private static final int LSHI4 = 4437;
	private static final int LSHI8 = 8533;
	private static final int LSHU4 = 4438;
	private static final int LSHU8 = 8534;
	private static final int MODI4 = 4453;
	private static final int MODI8 = 8549;
	private static final int MODU4 = 4454;
	private static final int MODU8 = 8550;
	private static final int RSHI4 = 4469;
	private static final int RSHI8 = 8565;
	private static final int RSHU4 = 4470;
	private static final int RSHU8 = 8566;
	private static final int BANDI4 = 4485;
	private static final int BANDI8 = 8581;
	private static final int BANDU4 = 4486;
	private static final int BANDU8 = 8582;
	private static final int BCOMI4 = 4501;
	private static final int BCOMI8 = 8597;
	private static final int BCOMU4 = 4502;
	private static final int BCOMU8 = 8598;
	private static final int BORI4 = 4517;
	private static final int BORI8 = 8613;
	private static final int BORU4 = 4518;
	private static final int BORU8 = 8614;
	private static final int BXORI4 = 4533;
	private static final int BXORI8 = 8629;
	private static final int BXORU4 = 4534;
	private static final int BXORU8 = 8630;
	private static final int DIVF4 = 4545;
	private static final int DIVF8 = 8641;
	private static final int DIVF16 = 16833;
	private static final int DIVI4 = 4549;
	private static final int DIVI8 = 8645;
	private static final int DIVU4 = 4550;
	private static final int DIVU8 = 8646;
	private static final int MULF4 = 4561;
	private static final int MULF8 = 8657;
	private static final int MULF16 = 16849;
	private static final int MULI4 = 4565;
	private static final int MULI8 = 8661;
	private static final int MULU4 = 4566;
	private static final int MULU8 = 8662;
	private static final int EQF4 = 4577;
	private static final int EQF8 = 8673;
	private static final int EQF16 = 16865;
	private static final int EQI4 = 4581;
	private static final int EQI8 = 8677;
	private static final int EQU4 = 4582;
	private static final int EQU8 = 8678;
	private static final int GEF4 = 4593;
	private static final int GEF8 = 8689;
	private static final int GEI4 = 4597;
	private static final int GEI8 = 8693;
	private static final int GEI16 = 16885;
	private static final int GEU4 = 4598;
	private static final int GEU8 = 8694;
	private static final int GTF4 = 4609;
	private static final int GTF8 = 8705;
	private static final int GTF16 = 16897;
	private static final int GTI4 = 4613;
	private static final int GTI8 = 8709;
	private static final int GTU4 = 4614;
	private static final int GTU8 = 8710;
	private static final int LEF4 = 4625;
	private static final int LEF8 = 8721;
	private static final int LEF16 = 16913;
	private static final int LEI4 = 4629;
	private static final int LEI8 = 8725;
	private static final int LEU4 = 4630;
	private static final int LEU8 = 8726;
	private static final int LTF4 = 4641;
	private static final int LTF8 = 8737;
	private static final int LTF16 = 16929;
	private static final int LTI4 = 4645;
	private static final int LTI8 = 8741;
	private static final int LTU4 = 4646;
	private static final int LTU8 = 8742;
	private static final int NEF4 = 4657;
	private static final int NEF8 = 8753;
	private static final int NEF16 = 16945;
	private static final int NEI4 = 4661;
	private static final int NEI8 = 8757;
	private static final int NEU4 = 4662;
	private static final int NEU8 = 8758;
	private static final int JUMPV = 584;
	private static final int LABELV = 600;
	private static final int LOADB = 233;
	private static final int LOADF4 = 4321;
	private static final int LOADF8 = 8417;
	private static final int LOADF16 = 16609;
	private static final int LOADI1 = 1253;
	private static final int LOADI2 = 2277;
	private static final int LOADI4 = 4325;
	private static final int LOADI8 = 8421;
	private static final int LOADP4 = 4327;
	private static final int LOADP8 = 8423;
	private static final int LOADU1 = 1254;
	private static final int LOADU2 = 2278;
	private static final int LOADU4 = 4326;
	private static final int LOADU8 = 8422;
	private static final int VREGP = 711;

	public int getArity(int op) {
		switch(op) {
			case CNSTF4: return 0;
			case CNSTF8: return 0;
			case CNSTF16: return 0;
			case CNSTI1: return 0;
			case CNSTI2: return 0;
			case CNSTI4: return 0;
			case CNSTI8: return 0;
			case CNSTP4: return 0;
			case CNSTP8: return 0;
			case CNSTU1: return 0;
			case CNSTU2: return 0;
			case CNSTU4: return 0;
			case CNSTU8: return 0;
			case ARGB: return 1;
			case ARGF4: return 1;
			case ARGF8: return 1;
			case ARGF16: return 0;
			case ARGI4: return 1;
			case ARGI8: return 0;
			case ARGP4: return 1;
			case ARGP8: return 0;
			case ARGU4: return 1;
			case ARGU8: return 0;
			case ASGNB: return 2;
			case ASGNF4: return 2;
			case ASGNF8: return 2;
			case ASGNF16: return 0;
			case ASGNI1: return 2;
			case ASGNI2: return 2;
			case ASGNI4: return 2;
			case ASGNI8: return 2;
			case ASGNP4: return 2;
			case ASGNP8: return 2;
			case ASGNU1: return 2;
			case ASGNU2: return 2;
			case ASGNU4: return 2;
			case ASGNU8: return 2;
			case INDIRB: return 1;
			case INDIRF4: return 1;
			case INDIRF8: return 1;
			case INDIRF16: return 0;
			case INDIRI1: return 1;
			case INDIRI2: return 1;
			case INDIRI4: return 1;
			case INDIRI8: return 1;
			case INDIRP4: return 1;
			case INDIRP8: return 1;
			case INDIRU1: return 1;
			case INDIRU2: return 1;
			case INDIRU4: return 1;
			case INDIRU8: return 1;
			case CVFF4: return 1;
			case CVFF8: return 1;
			case CVFF16: return 0;
			case CVFI4: return 1;
			case CVFI8: return 0;
			case CVIF4: return 1;
			case CVIF8: return 1;
			case CVIF16: return 0;
			case CVII1: return 1;
			case CVII2: return 1;
			case CVII4: return 1;
			case CVII8: return 0;
			case CVIU1: return 0;
			case CVIU2: return 0;
			case CVIU4: return 1;
			case CVIU8: return 0;
			case CVPP4: return 0;
			case CVPP8: return 0;
			case CVPP16: return 0;
			case CVPU4: return 1;
			case CVPU8: return 0;
			case CVUI1: return 0;
			case CVUI2: return 0;
			case CVUI4: return 1;
			case CVUI8: return 0;
			case CVUP4: return 1;
			case CVUP8: return 0;
			case CVUP16: return 0;
			case CVUU1: return 1;
			case CVUU2: return 1;
			case CVUU4: return 1;
			case CVUU8: return 0;
			case NEGF4: return 1;
			case NEGF8: return 1;
			case NEGF16: return 0;
			case NEGI4: return 1;
			case NEGI8: return 0;
			case CALLB: return 0;
			case CALLF4: return 1;
			case CALLF8: return 1;
			case CALLF16: return 0;
			case CALLI4: return 1;
			case CALLI8: return 0;
			case CALLP4: return 1;
			case CALLP8: return 0;
			case CALLU4: return 1;
			case CALLU8: return 0;
			case CALLV: return 1;
			case RETF4: return 1;
			case RETF8: return 1;
			case RETF16: return 0;
			case RETI4: return 1;
			case RETI8: return 0;
			case RETP4: return 1;
			case RETP8: return 0;
			case RETU4: return 1;
			case RETU8: return 0;
			case RETV: return 0;
			case ADDRGP4: return 0;
			case ADDRGP8: return 0;
			case ADDRFP4: return 0;
			case ADDRFP8: return 0;
			case ADDRLP4: return 0;
			case ADDRLP8: return 0;
			case ADDF4: return 2;
			case ADDF8: return 2;
			case ADDF16: return 0;
			case ADDI4: return 2;
			case ADDI8: return 0;
			case ADDP4: return 2;
			case ADDP8: return 0;
			case ADDU4: return 2;
			case ADDU8: return 0;
			case SUBF4: return 2;
			case SUBF8: return 2;
			case SUBF16: return 0;
			case SUBI4: return 2;
			case SUBI8: return 0;
			case SUBP4: return 2;
			case SUBP8: return 0;
			case SUBU4: return 2;
			case SUBU8: return 0;
			case LSHI4: return 2;
			case LSHI8: return 0;
			case LSHU4: return 2;
			case LSHU8: return 0;
			case MODI4: return 2;
			case MODI8: return 0;
			case MODU4: return 2;
			case MODU8: return 0;
			case RSHI4: return 2;
			case RSHI8: return 0;
			case RSHU4: return 2;
			case RSHU8: return 0;
			case BANDI4: return 2;
			case BANDI8: return 0;
			case BANDU4: return 2;
			case BANDU8: return 0;
			case BCOMI4: return 1;
			case BCOMI8: return 0;
			case BCOMU4: return 1;
			case BCOMU8: return 0;
			case BORI4: return 2;
			case BORI8: return 0;
			case BORU4: return 2;
			case BORU8: return 0;
			case BXORI4: return 2;
			case BXORI8: return 0;
			case BXORU4: return 2;
			case BXORU8: return 0;
			case DIVF4: return 2;
			case DIVF8: return 2;
			case DIVF16: return 0;
			case DIVI4: return 2;
			case DIVI8: return 0;
			case DIVU4: return 2;
			case DIVU8: return 0;
			case MULF4: return 2;
			case MULF8: return 2;
			case MULF16: return 0;
			case MULI4: return 2;
			case MULI8: return 0;
			case MULU4: return 2;
			case MULU8: return 0;
			case EQF4: return 2;
			case EQF8: return 2;
			case EQF16: return 0;
			case EQI4: return 2;
			case EQI8: return 0;
			case EQU4: return 2;
			case EQU8: return 0;
			case GEF4: return 2;
			case GEF8: return 2;
			case GEI4: return 2;
			case GEI8: return 0;
			case GEI16: return 0;
			case GEU4: return 2;
			case GEU8: return 0;
			case GTF4: return 2;
			case GTF8: return 2;
			case GTF16: return 0;
			case GTI4: return 2;
			case GTI8: return 0;
			case GTU4: return 2;
			case GTU8: return 0;
			case LEF4: return 2;
			case LEF8: return 2;
			case LEF16: return 0;
			case LEI4: return 2;
			case LEI8: return 0;
			case LEU4: return 2;
			case LEU8: return 0;
			case LTF4: return 2;
			case LTF8: return 2;
			case LTF16: return 0;
			case LTI4: return 2;
			case LTI8: return 0;
			case LTU4: return 2;
			case LTU8: return 0;
			case NEF4: return 2;
			case NEF8: return 2;
			case NEF16: return 0;
			case NEI4: return 2;
			case NEI8: return 0;
			case NEU4: return 2;
			case NEU8: return 0;
			case JUMPV: return 1;
			case LABELV: return 0;
			case LOADB: return 0;
			case LOADF4: return 0;
			case LOADF8: return 0;
			case LOADF16: return 0;
			case LOADI1: return 1;
			case LOADI2: return 1;
			case LOADI4: return 1;
			case LOADI8: return 0;
			case LOADP4: return 1;
			case LOADP8: return 0;
			case LOADU1: return 1;
			case LOADU2: return 1;
			case LOADU4: return 1;
			case LOADU8: return 0;
			case VREGP: return 0;
		}
		return 0;
	}

	private static String[] burm_string = {
	/* 0 */	null,
	/* 1 */	"reg: INDIRI1(VREGP)",
	/* 2 */	"reg: INDIRU1(VREGP)",
	/* 3 */	"reg: INDIRI2(VREGP)",
	/* 4 */	"reg: INDIRU2(VREGP)",
	/* 5 */	"reg: INDIRF4(VREGP)",
	/* 6 */	"reg: INDIRI4(VREGP)",
	/* 7 */	"reg: INDIRP4(VREGP)",
	/* 8 */	"reg: INDIRU4(VREGP)",
	/* 9 */	"reg: INDIRF8(VREGP)",
	/* 10 */	"reg: INDIRI8(VREGP)",
	/* 11 */	"reg: INDIRP8(VREGP)",
	/* 12 */	"reg: INDIRU8(VREGP)",
	/* 13 */	"stmt: ASGNI1(VREGP,reg)",
	/* 14 */	"stmt: ASGNU1(VREGP,reg)",
	/* 15 */	"stmt: ASGNI2(VREGP,reg)",
	/* 16 */	"stmt: ASGNU2(VREGP,reg)",
	/* 17 */	"stmt: ASGNF4(VREGP,reg)",
	/* 18 */	"stmt: ASGNI4(VREGP,reg)",
	/* 19 */	"stmt: ASGNP4(VREGP,reg)",
	/* 20 */	"stmt: ASGNU4(VREGP,reg)",
	/* 21 */	"stmt: ASGNF8(VREGP,reg)",
	/* 22 */	"stmt: ASGNI8(VREGP,reg)",
	/* 23 */	"stmt: ASGNP8(VREGP,reg)",
	/* 24 */	"stmt: ASGNU8(VREGP,reg)",
	/* 25 */	"con: CNSTI1",
	/* 26 */	"con: CNSTU1",
	/* 27 */	"con: CNSTI2",
	/* 28 */	"con: CNSTU2",
	/* 29 */	"con: CNSTI4",
	/* 30 */	"con: CNSTU4",
	/* 31 */	"con: CNSTP4",
	/* 32 */	"con: CNSTI8",
	/* 33 */	"con: CNSTU8",
	/* 34 */	"con: CNSTP8",
	/* 35 */	"stmt: reg",
	/* 36 */	"acon: ADDRGP4",
	/* 37 */	"acon: con",
	/* 38 */	"base: ADDRGP4",
	/* 39 */	"base: reg",
	/* 40 */	"base: ADDI4(reg,acon)",
	/* 41 */	"base: ADDP4(reg,acon)",
	/* 42 */	"base: ADDU4(reg,acon)",
	/* 43 */	"base: ADDRFP4",
	/* 44 */	"base: ADDRLP4",
	/* 45 */	"index: reg",
	/* 46 */	"index: LSHI4(reg,con1)",
	/* 47 */	"index: LSHI4(reg,con2)",
	/* 48 */	"index: LSHI4(reg,con3)",
	/* 49 */	"con1: CNSTI4",
	/* 50 */	"con1: CNSTU4",
	/* 51 */	"con2: CNSTI4",
	/* 52 */	"con2: CNSTU4",
	/* 53 */	"con3: CNSTI4",
	/* 54 */	"con3: CNSTU4",
	/* 55 */	"index: LSHU4(reg,con1)",
	/* 56 */	"index: LSHU4(reg,con2)",
	/* 57 */	"index: LSHU4(reg,con3)",
	/* 58 */	"addr: base",
	/* 59 */	"addr: ADDI4(index,base)",
	/* 60 */	"addr: ADDP4(index,base)",
	/* 61 */	"addr: ADDU4(index,base)",
	/* 62 */	"addr: index",
	/* 63 */	"mem: INDIRI1(addr)",
	/* 64 */	"mem: INDIRI2(addr)",
	/* 65 */	"mem: INDIRI4(addr)",
	/* 66 */	"mem: INDIRU1(addr)",
	/* 67 */	"mem: INDIRU2(addr)",
	/* 68 */	"mem: INDIRU4(addr)",
	/* 69 */	"mem: INDIRP4(addr)",
	/* 70 */	"rc: reg",
	/* 71 */	"rc: con",
	/* 72 */	"mr: reg",
	/* 73 */	"mr: mem",
	/* 74 */	"mrc0: mem",
	/* 75 */	"mrc0: rc",
	/* 76 */	"mrc1: mem",
	/* 77 */	"mrc1: rc",
	/* 78 */	"mrc3: mem",
	/* 79 */	"mrc3: rc",
	/* 80 */	"reg: addr",
	/* 81 */	"reg: mrc0",
	/* 82 */	"reg: LOADI1(reg)",
	/* 83 */	"reg: LOADI2(reg)",
	/* 84 */	"reg: LOADI4(reg)",
	/* 85 */	"reg: LOADU1(reg)",
	/* 86 */	"reg: LOADU2(reg)",
	/* 87 */	"reg: LOADU4(reg)",
	/* 88 */	"reg: LOADP4(reg)",
	/* 89 */	"reg: ADDI4(reg,mrc1)",
	/* 90 */	"reg: ADDP4(reg,mrc1)",
	/* 91 */	"reg: ADDU4(reg,mrc1)",
	/* 92 */	"reg: SUBI4(reg,mrc1)",
	/* 93 */	"reg: SUBP4(reg,mrc1)",
	/* 94 */	"reg: SUBU4(reg,mrc1)",
	/* 95 */	"reg: BANDI4(reg,mrc1)",
	/* 96 */	"reg: BORI4(reg,mrc1)",
	/* 97 */	"reg: BXORI4(reg,mrc1)",
	/* 98 */	"reg: BANDU4(reg,mrc1)",
	/* 99 */	"reg: BORU4(reg,mrc1)",
	/* 100 */	"reg: BXORU4(reg,mrc1)",
	/* 101 */	"stmt: ASGNI4(addr,ADDI4(mem,con1))",
	/* 102 */	"stmt: ASGNI4(addr,ADDU4(mem,con1))",
	/* 103 */	"stmt: ASGNP4(addr,ADDP4(mem,con1))",
	/* 104 */	"stmt: ASGNI4(addr,SUBI4(mem,con1))",
	/* 105 */	"stmt: ASGNI4(addr,SUBU4(mem,con1))",
	/* 106 */	"stmt: ASGNP4(addr,SUBP4(mem,con1))",
	/* 107 */	"stmt: ASGNI4(addr,ADDI4(mem,rc))",
	/* 108 */	"stmt: ASGNI4(addr,SUBI4(mem,rc))",
	/* 109 */	"stmt: ASGNU4(addr,ADDU4(mem,rc))",
	/* 110 */	"stmt: ASGNU4(addr,SUBU4(mem,rc))",
	/* 111 */	"stmt: ASGNI4(addr,BANDI4(mem,rc))",
	/* 112 */	"stmt: ASGNI4(addr,BORI4(mem,rc))",
	/* 113 */	"stmt: ASGNI4(addr,BXORI4(mem,rc))",
	/* 114 */	"stmt: ASGNU4(addr,BANDU4(mem,rc))",
	/* 115 */	"stmt: ASGNU4(addr,BORU4(mem,rc))",
	/* 116 */	"stmt: ASGNU4(addr,BXORU4(mem,rc))",
	/* 117 */	"reg: BCOMI4(reg)",
	/* 118 */	"reg: BCOMU4(reg)",
	/* 119 */	"reg: NEGI4(reg)",
	/* 120 */	"stmt: ASGNI4(addr,BCOMI4(mem))",
	/* 121 */	"stmt: ASGNU4(addr,BCOMU4(mem))",
	/* 122 */	"stmt: ASGNI4(addr,NEGI4(mem))",
	/* 123 */	"reg: LSHI4(reg,con5)",
	/* 124 */	"reg: LSHU4(reg,con5)",
	/* 125 */	"reg: RSHI4(reg,con5)",
	/* 126 */	"reg: RSHU4(reg,con5)",
	/* 127 */	"stmt: ASGNI4(addr,LSHI4(mem,con5))",
	/* 128 */	"stmt: ASGNI4(addr,LSHU4(mem,con5))",
	/* 129 */	"stmt: ASGNI4(addr,RSHI4(mem,con5))",
	/* 130 */	"stmt: ASGNI4(addr,RSHU4(mem,con5))",
	/* 131 */	"con5: CNSTI4",
	/* 132 */	"reg: LSHI4(reg,reg)",
	/* 133 */	"reg: LSHU4(reg,reg)",
	/* 134 */	"reg: RSHI4(reg,reg)",
	/* 135 */	"reg: RSHU4(reg,reg)",
	/* 136 */	"reg: MULI4(reg,mrc3)",
	/* 137 */	"reg: MULI4(con,mr)",
	/* 138 */	"reg: MULU4(reg,mr)",
	/* 139 */	"reg: DIVU4(reg,reg)",
	/* 140 */	"reg: MODU4(reg,reg)",
	/* 141 */	"reg: DIVI4(reg,reg)",
	/* 142 */	"reg: MODI4(reg,reg)",
	/* 143 */	"reg: CVPU4(reg)",
	/* 144 */	"reg: CVUP4(reg)",
	/* 145 */	"reg: CVII4(INDIRI1(addr))",
	/* 146 */	"reg: CVII4(INDIRI2(addr))",
	/* 147 */	"reg: CVUU4(INDIRU1(addr))",
	/* 148 */	"reg: CVUU4(INDIRU2(addr))",
	/* 149 */	"reg: CVII4(reg)",
	/* 150 */	"reg: CVIU4(reg)",
	/* 151 */	"reg: CVUI4(reg)",
	/* 152 */	"reg: CVUU4(reg)",
	/* 153 */	"reg: CVII1(reg)",
	/* 154 */	"reg: CVII2(reg)",
	/* 155 */	"reg: CVUU1(reg)",
	/* 156 */	"reg: CVUU2(reg)",
	/* 157 */	"stmt: ASGNI1(addr,rc)",
	/* 158 */	"stmt: ASGNI2(addr,rc)",
	/* 159 */	"stmt: ASGNI4(addr,rc)",
	/* 160 */	"stmt: ASGNU1(addr,rc)",
	/* 161 */	"stmt: ASGNU2(addr,rc)",
	/* 162 */	"stmt: ASGNU4(addr,rc)",
	/* 163 */	"stmt: ASGNP4(addr,rc)",
	/* 164 */	"stmt: ARGI4(mrc3)",
	/* 165 */	"stmt: ARGU4(mrc3)",
	/* 166 */	"stmt: ARGP4(mrc3)",
	/* 167 */	"stmt: ASGNB(reg,INDIRB(reg))",
	/* 168 */	"stmt: ARGB(INDIRB(reg))",
	/* 169 */	"memf: INDIRF8(addr)",
	/* 170 */	"memf: INDIRF4(addr)",
	/* 171 */	"memf: CVFF8(INDIRF4(addr))",
	/* 172 */	"reg: memf",
	/* 173 */	"stmt: ASGNF8(addr,reg)",
	/* 174 */	"stmt: ASGNF4(addr,reg)",
	/* 175 */	"stmt: ASGNF4(addr,CVFF4(reg))",
	/* 176 */	"stmt: ARGF8(reg)",
	/* 177 */	"stmt: ARGF4(reg)",
	/* 178 */	"reg: NEGF8(reg)",
	/* 179 */	"reg: NEGF4(reg)",
	/* 180 */	"flt: memf",
	/* 181 */	"flt: reg",
	/* 182 */	"reg: ADDF8(reg,flt)",
	/* 183 */	"reg: ADDF4(reg,flt)",
	/* 184 */	"reg: DIVF8(reg,flt)",
	/* 185 */	"reg: DIVF4(reg,flt)",
	/* 186 */	"reg: MULF8(reg,flt)",
	/* 187 */	"reg: MULF4(reg,flt)",
	/* 188 */	"reg: SUBF8(reg,flt)",
	/* 189 */	"reg: SUBF4(reg,flt)",
	/* 190 */	"reg: CVFF8(reg)",
	/* 191 */	"reg: CVFF4(reg)",
	/* 192 */	"reg: CVFI4(reg)",
	/* 193 */	"reg: CVIF8(INDIRI4(addr))",
	/* 194 */	"reg: CVIF4(reg)",
	/* 195 */	"reg: CVIF8(reg)",
	/* 196 */	"addrj: ADDRGP4",
	/* 197 */	"addrj: reg",
	/* 198 */	"addrj: mem",
	/* 199 */	"stmt: JUMPV(addrj)",
	/* 200 */	"stmt: LABELV",
	/* 201 */	"stmt: EQI4(mem,rc)",
	/* 202 */	"stmt: GEI4(mem,rc)",
	/* 203 */	"stmt: GTI4(mem,rc)",
	/* 204 */	"stmt: LEI4(mem,rc)",
	/* 205 */	"stmt: LTI4(mem,rc)",
	/* 206 */	"stmt: NEI4(mem,rc)",
	/* 207 */	"stmt: GEU4(mem,rc)",
	/* 208 */	"stmt: GTU4(mem,rc)",
	/* 209 */	"stmt: LEU4(mem,rc)",
	/* 210 */	"stmt: LTU4(mem,rc)",
	/* 211 */	"stmt: EQI4(reg,mrc1)",
	/* 212 */	"stmt: GEI4(reg,mrc1)",
	/* 213 */	"stmt: GTI4(reg,mrc1)",
	/* 214 */	"stmt: LEI4(reg,mrc1)",
	/* 215 */	"stmt: LTI4(reg,mrc1)",
	/* 216 */	"stmt: NEI4(reg,mrc1)",
	/* 217 */	"stmt: EQU4(reg,mrc1)",
	/* 218 */	"stmt: GEU4(reg,mrc1)",
	/* 219 */	"stmt: GTU4(reg,mrc1)",
	/* 220 */	"stmt: LEU4(reg,mrc1)",
	/* 221 */	"stmt: LTU4(reg,mrc1)",
	/* 222 */	"stmt: NEU4(reg,mrc1)",
	/* 223 */	"cmpf: memf",
	/* 224 */	"cmpf: reg",
	/* 225 */	"stmt: EQF8(cmpf,reg)",
	/* 226 */	"stmt: GEF8(cmpf,reg)",
	/* 227 */	"stmt: GTF8(cmpf,reg)",
	/* 228 */	"stmt: LEF8(cmpf,reg)",
	/* 229 */	"stmt: LTF8(cmpf,reg)",
	/* 230 */	"stmt: NEF8(cmpf,reg)",
	/* 231 */	"stmt: EQF4(cmpf,reg)",
	/* 232 */	"stmt: GEF4(cmpf,reg)",
	/* 233 */	"stmt: GTF4(cmpf,reg)",
	/* 234 */	"stmt: LEF4(cmpf,reg)",
	/* 235 */	"stmt: LTF4(cmpf,reg)",
	/* 236 */	"stmt: NEF4(cmpf,reg)",
	/* 237 */	"reg: CALLI4(addrj)",
	/* 238 */	"reg: CALLU4(addrj)",
	/* 239 */	"reg: CALLP4(addrj)",
	/* 240 */	"stmt: CALLV(addrj)",
	/* 241 */	"reg: CALLF4(addrj)",
	/* 242 */	"reg: CALLF8(addrj)",
	/* 243 */	"stmt: CALLF4(addrj)",
	/* 244 */	"stmt: CALLF8(addrj)",
	/* 245 */	"stmt: RETI4(reg)",
	/* 246 */	"stmt: RETU4(reg)",
	/* 247 */	"stmt: RETP4(reg)",
	/* 248 */	"stmt: RETF4(reg)",
	/* 249 */	"stmt: RETF8(reg)",
	};

	public String getRuleString(int eruleno) {
		return burm_string[eruleno];
	}

	private static short burm_decode_stmt[] = {
		0,
		13,
		14,
		15,
		16,
		17,
		18,
		19,
		20,
		21,
		22,
		23,
		24,
		35,
		101,
		102,
		103,
		104,
		105,
		106,
		107,
		108,
		109,
		110,
		111,
		112,
		113,
		114,
		115,
		116,
		120,
		121,
		122,
		127,
		128,
		129,
		130,
		157,
		158,
		159,
		160,
		161,
		162,
		163,
		164,
		165,
		166,
		167,
		168,
		173,
		174,
		175,
		176,
		177,
		199,
		200,
		201,
		202,
		203,
		204,
		205,
		206,
		207,
		208,
		209,
		210,
		211,
		212,
		213,
		214,
		215,
		216,
		217,
		218,
		219,
		220,
		221,
		222,
		225,
		226,
		227,
		228,
		229,
		230,
		231,
		232,
		233,
		234,
		235,
		236,
		240,
		243,
		244,
		245,
		246,
		247,
		248,
		249,
	};

	private static short burm_decode_reg[] = {
		0,
		1,
		2,
		3,
		4,
		5,
		6,
		7,
		8,
		9,
		10,
		11,
		12,
		80,
		81,
		82,
		83,
		84,
		85,
		86,
		87,
		88,
		89,
		90,
		91,
		92,
		93,
		94,
		95,
		96,
		97,
		98,
		99,
		100,
		117,
		118,
		119,
		123,
		124,
		125,
		126,
		132,
		133,
		134,
		135,
		136,
		137,
		138,
		139,
		140,
		141,
		142,
		143,
		144,
		145,
		146,
		147,
		148,
		149,
		150,
		151,
		152,
		153,
		154,
		155,
		156,
		172,
		178,
		179,
		182,
		183,
		184,
		185,
		186,
		187,
		188,
		189,
		190,
		191,
		192,
		193,
		194,
		195,
		237,
		238,
		239,
		241,
		242,
	};

	private static short burm_decode_con[] = {
		0,
		25,
		26,
		27,
		28,
		29,
		30,
		31,
		32,
		33,
		34,
	};

	private static short burm_decode_acon[] = {
		0,
		36,
		37,
	};

	private static short burm_decode_base[] = {
		0,
		38,
		39,
		40,
		41,
		42,
		43,
		44,
	};

	private static short burm_decode_index[] = {
		0,
		45,
		46,
		47,
		48,
		55,
		56,
		57,
	};

	private static short burm_decode_con1[] = {
		0,
		49,
		50,
	};

	private static short burm_decode_con2[] = {
		0,
		51,
		52,
	};

	private static short burm_decode_con3[] = {
		0,
		53,
		54,
	};

	private static short burm_decode_addr[] = {
		0,
		58,
		59,
		60,
		61,
		62,
	};

	private static short burm_decode_mem[] = {
		0,
		63,
		64,
		65,
		66,
		67,
		68,
		69,
	};

	private static short burm_decode_rc[] = {
		0,
		70,
		71,
	};

	private static short burm_decode_mr[] = {
		0,
		72,
		73,
	};

	private static short burm_decode_mrc0[] = {
		0,
		74,
		75,
	};

	private static short burm_decode_mrc1[] = {
		0,
		76,
		77,
	};

	private static short burm_decode_mrc3[] = {
		0,
		78,
		79,
	};

	private static short burm_decode_con5[] = {
		0,
		131,
	};

	private static short burm_decode_memf[] = {
		0,
		169,
		170,
		171,
	};

	private static short burm_decode_flt[] = {
		0,
		180,
		181,
	};

	private static short burm_decode_addrj[] = {
		0,
		196,
		197,
		198,
	};

	private static short burm_decode_cmpf[] = {
		0,
		223,
		224,
	};

	public int getRule(State state, int goalnt) {
		if (state == null) {
			return 0;
		}
		switch (goalnt) {
			case burm_stmt_NT: return burm_decode_stmt[state.getRule(goalnt)];
			case burm_reg_NT: return burm_decode_reg[state.getRule(goalnt)];
			case burm_con_NT: return burm_decode_con[state.getRule(goalnt)];
			case burm_acon_NT: return burm_decode_acon[state.getRule(goalnt)];
			case burm_base_NT: return burm_decode_base[state.getRule(goalnt)];
			case burm_index_NT: return burm_decode_index[state.getRule(goalnt)];
			case burm_con1_NT: return burm_decode_con1[state.getRule(goalnt)];
			case burm_con2_NT: return burm_decode_con2[state.getRule(goalnt)];
			case burm_con3_NT: return burm_decode_con3[state.getRule(goalnt)];
			case burm_addr_NT: return burm_decode_addr[state.getRule(goalnt)];
			case burm_mem_NT: return burm_decode_mem[state.getRule(goalnt)];
			case burm_rc_NT: return burm_decode_rc[state.getRule(goalnt)];
			case burm_mr_NT: return burm_decode_mr[state.getRule(goalnt)];
			case burm_mrc0_NT: return burm_decode_mrc0[state.getRule(goalnt)];
			case burm_mrc1_NT: return burm_decode_mrc1[state.getRule(goalnt)];
			case burm_mrc3_NT: return burm_decode_mrc3[state.getRule(goalnt)];
			case burm_con5_NT: return burm_decode_con5[state.getRule(goalnt)];
			case burm_memf_NT: return burm_decode_memf[state.getRule(goalnt)];
			case burm_flt_NT: return burm_decode_flt[state.getRule(goalnt)];
			case burm_addrj_NT: return burm_decode_addrj[state.getRule(goalnt)];
			case burm_cmpf_NT: return burm_decode_cmpf[state.getRule(goalnt)];
		}
		throw new AssertionError("Bad goal nonterminal in getRule(): " + goalnt);
	}

	private void doClosure_reg(State p, int c) {
		if (c + 0 < p.getCost(burm_cmpf_NT)) {
			p.setCostRule(burm_cmpf_NT, c + 0, 2);
		}
		if (c + 2 < p.getCost(burm_addrj_NT)) {
			p.setCostRule(burm_addrj_NT, c + 2, 2);
		}
		if (c + 0 < p.getCost(burm_flt_NT)) {
			p.setCostRule(burm_flt_NT, c + 0, 2);
		}
		if (c + 0 < p.getCost(burm_mr_NT)) {
			p.setCostRule(burm_mr_NT, c + 0, 1);
		}
		if (c + 0 < p.getCost(burm_rc_NT)) {
			p.setCostRule(burm_rc_NT, c + 0, 1);
			doClosure_rc(p, c + 0);
		}
		if (c + 0 < p.getCost(burm_index_NT)) {
			p.setCostRule(burm_index_NT, c + 0, 1);
			doClosure_index(p, c + 0);
		}
		if (c + 0 < p.getCost(burm_base_NT)) {
			p.setCostRule(burm_base_NT, c + 0, 2);
			doClosure_base(p, c + 0);
		}
		if (c + 0 < p.getCost(burm_stmt_NT)) {
			p.setCostRule(burm_stmt_NT, c + 0, 13);
		}
	}

	private void doClosure_con(State p, int c) {
		if (c + 0 < p.getCost(burm_rc_NT)) {
			p.setCostRule(burm_rc_NT, c + 0, 2);
			doClosure_rc(p, c + 0);
		}
		if (c + 0 < p.getCost(burm_acon_NT)) {
			p.setCostRule(burm_acon_NT, c + 0, 2);
		}
	}

	private void doClosure_base(State p, int c) {
		if (c + 0 < p.getCost(burm_addr_NT)) {
			p.setCostRule(burm_addr_NT, c + 0, 1);
			doClosure_addr(p, c + 0);
		}
	}

	private void doClosure_index(State p, int c) {
		if (c + 0 < p.getCost(burm_addr_NT)) {
			p.setCostRule(burm_addr_NT, c + 0, 5);
			doClosure_addr(p, c + 0);
		}
	}

	private void doClosure_addr(State p, int c) {
		if (c + 1 < p.getCost(burm_reg_NT)) {
			p.setCostRule(burm_reg_NT, c + 1, 13);
			doClosure_reg(p, c + 1);
		}
	}

	private void doClosure_mem(State p, int c) {
		if (c + 2 < p.getCost(burm_addrj_NT)) {
			p.setCostRule(burm_addrj_NT, c + 2, 3);
		}
		if (c + 3 < p.getCost(burm_mrc3_NT)) {
			p.setCostRule(burm_mrc3_NT, c + 3, 1);
		}
		if (c + 1 < p.getCost(burm_mrc1_NT)) {
			p.setCostRule(burm_mrc1_NT, c + 1, 1);
		}
		if (c + 0 < p.getCost(burm_mrc0_NT)) {
			p.setCostRule(burm_mrc0_NT, c + 0, 1);
			doClosure_mrc0(p, c + 0);
		}
		if (c + 0 < p.getCost(burm_mr_NT)) {
			p.setCostRule(burm_mr_NT, c + 0, 2);
		}
	}

	private void doClosure_rc(State p, int c) {
		if (c + 0 < p.getCost(burm_mrc3_NT)) {
			p.setCostRule(burm_mrc3_NT, c + 0, 2);
		}
		if (c + 0 < p.getCost(burm_mrc1_NT)) {
			p.setCostRule(burm_mrc1_NT, c + 0, 2);
		}
		if (c + 0 < p.getCost(burm_mrc0_NT)) {
			p.setCostRule(burm_mrc0_NT, c + 0, 2);
			doClosure_mrc0(p, c + 0);
		}
	}

	private void doClosure_mrc0(State p, int c) {
		if (c + 1 < p.getCost(burm_reg_NT)) {
			p.setCostRule(burm_reg_NT, c + 1, 14);
			doClosure_reg(p, c + 1);
		}
	}

	private void doClosure_memf(State p, int c) {
		if (c + 0 < p.getCost(burm_cmpf_NT)) {
			p.setCostRule(burm_cmpf_NT, c + 0, 1);
		}
		if (c + 0 < p.getCost(burm_flt_NT)) {
			p.setCostRule(burm_flt_NT, c + 0, 1);
		}
		if (c + 3 < p.getCost(burm_reg_NT)) {
			p.setCostRule(burm_reg_NT, c + 3, 66);
			doClosure_reg(p, c + 3);
		}
	}

	public State getNextState(int op, State left, State right) {
		int c;
		State p = null;
		State l = left;
		State r = right;

		p = new State(op, left, right);
		p.setRule(burm_stmt_NT, 0);

		switch (op) {
		case 4113: /* CNSTF4 */
		break;

		case 8209: /* CNSTF8 */
		break;

		case 16401: /* CNSTF16 */
		break;

		case 1045: /* CNSTI1 */
		{	/* con: CNSTI1 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 1);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 2069: /* CNSTI2 */
		{	/* con: CNSTI2 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 3);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 4117: /* CNSTI4 */
		{	/* con5: CNSTI4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con5_NT)) {
				p.setCostRule(burm_con5_NT, c + 0, 1);
			}
		}
		{	/* con3: CNSTI4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con3_NT)) {
				p.setCostRule(burm_con3_NT, c + 0, 1);
			}
		}
		{	/* con2: CNSTI4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con2_NT)) {
				p.setCostRule(burm_con2_NT, c + 0, 1);
			}
		}
		{	/* con1: CNSTI4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con1_NT)) {
				p.setCostRule(burm_con1_NT, c + 0, 1);
			}
		}
		{	/* con: CNSTI4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 5);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 8213: /* CNSTI8 */
		{	/* con: CNSTI8 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 8);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 4119: /* CNSTP4 */
		{	/* con: CNSTP4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 7);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 8215: /* CNSTP8 */
		{	/* con: CNSTP8 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 10);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 1046: /* CNSTU1 */
		{	/* con: CNSTU1 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 2);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 2070: /* CNSTU2 */
		{	/* con: CNSTU2 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 4);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 4118: /* CNSTU4 */
		{	/* con3: CNSTU4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con3_NT)) {
				p.setCostRule(burm_con3_NT, c + 0, 2);
			}
		}
		{	/* con2: CNSTU4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con2_NT)) {
				p.setCostRule(burm_con2_NT, c + 0, 2);
			}
		}
		{	/* con1: CNSTU4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con1_NT)) {
				p.setCostRule(burm_con1_NT, c + 0, 2);
			}
		}
		{	/* con: CNSTU4 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 6);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 8214: /* CNSTU8 */
		{	/* con: CNSTU8 */
			c = 0;
			if (c + 0 < p.getCost(burm_con_NT)) {
				p.setCostRule(burm_con_NT, c + 0, 9);
				doClosure_con(p, c + 0);
			}
		}
		break;

		case 41: /* ARGB */
		if (	/* stmt: ARGB(INDIRB(reg)) */
			l.getOperator() == 73 /* INDIRB */
		) {
			c = l.getLeft().getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 48);
			}
		}
		break;

		case 4129: /* ARGF4 */
		{	/* stmt: ARGF4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 53);
			}
		}
		break;

		case 8225: /* ARGF8 */
		{	/* stmt: ARGF8(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 52);
			}
		}
		break;

		case 16417: /* ARGF16 */
		break;

		case 4133: /* ARGI4 */
		{	/* stmt: ARGI4(mrc3) */
			c = l.getCost(burm_mrc3_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 44);
			}
		}
		break;

		case 8229: /* ARGI8 */
		break;

		case 4135: /* ARGP4 */
		{	/* stmt: ARGP4(mrc3) */
			c = l.getCost(burm_mrc3_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 46);
			}
		}
		break;

		case 8231: /* ARGP8 */
		break;

		case 4134: /* ARGU4 */
		{	/* stmt: ARGU4(mrc3) */
			c = l.getCost(burm_mrc3_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 45);
			}
		}
		break;

		case 8230: /* ARGU8 */
		break;

		case 57: /* ASGNB */
		if (	/* stmt: ASGNB(reg,INDIRB(reg)) */
			r.getOperator() == 73 /* INDIRB */
		) {
			c = l.getCost(burm_reg_NT) + r.getLeft().getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 47);
			}
		}
		break;

		case 4145: /* ASGNF4 */
		if (	/* stmt: ASGNF4(addr,CVFF4(reg)) */
			r.getOperator() == 4209 /* CVFF4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_reg_NT) + 7;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 51);
			}
		}
		{	/* stmt: ASGNF4(addr,reg) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_reg_NT) + 7;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 50);
			}
		}
		if (	/* stmt: ASGNF4(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 5);
			}
		}
		break;

		case 8241: /* ASGNF8 */
		{	/* stmt: ASGNF8(addr,reg) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_reg_NT) + 7;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 49);
			}
		}
		if (	/* stmt: ASGNF8(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 9);
			}
		}
		break;

		case 16433: /* ASGNF16 */
		break;

		case 1077: /* ASGNI1 */
		{	/* stmt: ASGNI1(addr,rc) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_rc_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 37);
			}
		}
		if (	/* stmt: ASGNI1(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 1);
			}
		}
		break;

		case 2101: /* ASGNI2 */
		{	/* stmt: ASGNI2(addr,rc) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_rc_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 38);
			}
		}
		if (	/* stmt: ASGNI2(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 3);
			}
		}
		break;

		case 4149: /* ASGNI4 */
		{	/* stmt: ASGNI4(addr,rc) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_rc_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 39);
			}
		}
		if (	/* stmt: ASGNI4(addr,RSHU4(mem,con5)) */
			r.getOperator() == 4470 /* RSHU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con5_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 36);
			}
		}
		if (	/* stmt: ASGNI4(addr,RSHI4(mem,con5)) */
			r.getOperator() == 4469 /* RSHI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con5_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 35);
			}
		}
		if (	/* stmt: ASGNI4(addr,LSHU4(mem,con5)) */
			r.getOperator() == 4438 /* LSHU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con5_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 34);
			}
		}
		if (	/* stmt: ASGNI4(addr,LSHI4(mem,con5)) */
			r.getOperator() == 4437 /* LSHI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con5_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 33);
			}
		}
		if (	/* stmt: ASGNI4(addr,NEGI4(mem)) */
			r.getOperator() == 4293 /* NEGI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 32);
			}
		}
		if (	/* stmt: ASGNI4(addr,BCOMI4(mem)) */
			r.getOperator() == 4501 /* BCOMI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 30);
			}
		}
		if (	/* stmt: ASGNI4(addr,BXORI4(mem,rc)) */
			r.getOperator() == 4533 /* BXORI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 26);
			}
		}
		if (	/* stmt: ASGNI4(addr,BORI4(mem,rc)) */
			r.getOperator() == 4517 /* BORI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 25);
			}
		}
		if (	/* stmt: ASGNI4(addr,BANDI4(mem,rc)) */
			r.getOperator() == 4485 /* BANDI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 24);
			}
		}
		if (	/* stmt: ASGNI4(addr,SUBI4(mem,rc)) */
			r.getOperator() == 4421 /* SUBI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 21);
			}
		}
		if (	/* stmt: ASGNI4(addr,ADDI4(mem,rc)) */
			r.getOperator() == 4405 /* ADDI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 20);
			}
		}
		if (	/* stmt: ASGNI4(addr,SUBU4(mem,con1)) */
			r.getOperator() == 4422 /* SUBU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con1_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 18);
			}
		}
		if (	/* stmt: ASGNI4(addr,SUBI4(mem,con1)) */
			r.getOperator() == 4421 /* SUBI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con1_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 17);
			}
		}
		if (	/* stmt: ASGNI4(addr,ADDU4(mem,con1)) */
			r.getOperator() == 4406 /* ADDU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con1_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 15);
			}
		}
		if (	/* stmt: ASGNI4(addr,ADDI4(mem,con1)) */
			r.getOperator() == 4405 /* ADDI4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con1_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 14);
			}
		}
		if (	/* stmt: ASGNI4(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 6);
			}
		}
		break;

		case 8245: /* ASGNI8 */
		if (	/* stmt: ASGNI8(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 10);
			}
		}
		break;

		case 4151: /* ASGNP4 */
		{	/* stmt: ASGNP4(addr,rc) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_rc_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 43);
			}
		}
		if (	/* stmt: ASGNP4(addr,SUBP4(mem,con1)) */
			r.getOperator() == 4423 /* SUBP4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con1_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 19);
			}
		}
		if (	/* stmt: ASGNP4(addr,ADDP4(mem,con1)) */
			r.getOperator() == 4407 /* ADDP4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_con1_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 16);
			}
		}
		if (	/* stmt: ASGNP4(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 7);
			}
		}
		break;

		case 8247: /* ASGNP8 */
		if (	/* stmt: ASGNP8(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 11);
			}
		}
		break;

		case 1078: /* ASGNU1 */
		{	/* stmt: ASGNU1(addr,rc) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_rc_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 40);
			}
		}
		if (	/* stmt: ASGNU1(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 2);
			}
		}
		break;

		case 2102: /* ASGNU2 */
		{	/* stmt: ASGNU2(addr,rc) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_rc_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 41);
			}
		}
		if (	/* stmt: ASGNU2(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 4);
			}
		}
		break;

		case 4150: /* ASGNU4 */
		{	/* stmt: ASGNU4(addr,rc) */
			c = l.getCost(burm_addr_NT) + r.getCost(burm_rc_NT) + 1;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 42);
			}
		}
		if (	/* stmt: ASGNU4(addr,BCOMU4(mem)) */
			r.getOperator() == 4502 /* BCOMU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 31);
			}
		}
		if (	/* stmt: ASGNU4(addr,BXORU4(mem,rc)) */
			r.getOperator() == 4534 /* BXORU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 29);
			}
		}
		if (	/* stmt: ASGNU4(addr,BORU4(mem,rc)) */
			r.getOperator() == 4518 /* BORU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 28);
			}
		}
		if (	/* stmt: ASGNU4(addr,BANDU4(mem,rc)) */
			r.getOperator() == 4486 /* BANDU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 27);
			}
		}
		if (	/* stmt: ASGNU4(addr,SUBU4(mem,rc)) */
			r.getOperator() == 4422 /* SUBU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 23);
			}
		}
		if (	/* stmt: ASGNU4(addr,ADDU4(mem,rc)) */
			r.getOperator() == 4406 /* ADDU4 */
		) {
			c = l.getCost(burm_addr_NT) + r.getLeft().getCost(burm_mem_NT) + r.getRight().getCost(burm_rc_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 22);
			}
		}
		if (	/* stmt: ASGNU4(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 8);
			}
		}
		break;

		case 8246: /* ASGNU8 */
		if (	/* stmt: ASGNU8(VREGP,reg) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 12);
			}
		}
		break;

		case 73: /* INDIRB */
		break;

		case 4161: /* INDIRF4 */
		{	/* memf: INDIRF4(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_memf_NT)) {
				p.setCostRule(burm_memf_NT, c + 0, 2);
				doClosure_memf(p, c + 0);
			}
		}
		if (	/* reg: INDIRF4(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 5);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8257: /* INDIRF8 */
		{	/* memf: INDIRF8(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_memf_NT)) {
				p.setCostRule(burm_memf_NT, c + 0, 1);
				doClosure_memf(p, c + 0);
			}
		}
		if (	/* reg: INDIRF8(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 9);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 16449: /* INDIRF16 */
		break;

		case 1093: /* INDIRI1 */
		{	/* mem: INDIRI1(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_mem_NT)) {
				p.setCostRule(burm_mem_NT, c + 0, 1);
				doClosure_mem(p, c + 0);
			}
		}
		if (	/* reg: INDIRI1(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 1);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 2117: /* INDIRI2 */
		{	/* mem: INDIRI2(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_mem_NT)) {
				p.setCostRule(burm_mem_NT, c + 0, 2);
				doClosure_mem(p, c + 0);
			}
		}
		if (	/* reg: INDIRI2(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 3);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 4165: /* INDIRI4 */
		{	/* mem: INDIRI4(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_mem_NT)) {
				p.setCostRule(burm_mem_NT, c + 0, 3);
				doClosure_mem(p, c + 0);
			}
		}
		if (	/* reg: INDIRI4(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 6);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8261: /* INDIRI8 */
		if (	/* reg: INDIRI8(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 10);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 4167: /* INDIRP4 */
		{	/* mem: INDIRP4(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_mem_NT)) {
				p.setCostRule(burm_mem_NT, c + 0, 7);
				doClosure_mem(p, c + 0);
			}
		}
		if (	/* reg: INDIRP4(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 7);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8263: /* INDIRP8 */
		if (	/* reg: INDIRP8(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 11);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 1094: /* INDIRU1 */
		{	/* mem: INDIRU1(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_mem_NT)) {
				p.setCostRule(burm_mem_NT, c + 0, 4);
				doClosure_mem(p, c + 0);
			}
		}
		if (	/* reg: INDIRU1(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 2);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 2118: /* INDIRU2 */
		{	/* mem: INDIRU2(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_mem_NT)) {
				p.setCostRule(burm_mem_NT, c + 0, 5);
				doClosure_mem(p, c + 0);
			}
		}
		if (	/* reg: INDIRU2(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 4);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 4166: /* INDIRU4 */
		{	/* mem: INDIRU4(addr) */
			c = l.getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_mem_NT)) {
				p.setCostRule(burm_mem_NT, c + 0, 6);
				doClosure_mem(p, c + 0);
			}
		}
		if (	/* reg: INDIRU4(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 8);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8262: /* INDIRU8 */
		if (	/* reg: INDIRU8(VREGP) */
			l.getOperator() == 711 /* VREGP */
		) {
			c = 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 12);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 4209: /* CVFF4 */
		{	/* reg: CVFF4(reg) */
			c = l.getCost(burm_reg_NT) + 12;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 78);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8305: /* CVFF8 */
		{	/* reg: CVFF8(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 77);
				doClosure_reg(p, c + 0);
			}
		}
		if (	/* memf: CVFF8(INDIRF4(addr)) */
			l.getOperator() == 4161 /* INDIRF4 */
		) {
			c = l.getLeft().getCost(burm_addr_NT) + 0;
			if (c + 0 < p.getCost(burm_memf_NT)) {
				p.setCostRule(burm_memf_NT, c + 0, 3);
				doClosure_memf(p, c + 0);
			}
		}
		break;

		case 16497: /* CVFF16 */
		break;

		case 4213: /* CVFI4 */
		{	/* reg: CVFI4(reg) */
			c = l.getCost(burm_reg_NT) + 31;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 79);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8309: /* CVFI8 */
		break;

		case 4225: /* CVIF4 */
		{	/* reg: CVIF4(reg) */
			c = l.getCost(burm_reg_NT) + 12;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 81);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8321: /* CVIF8 */
		{	/* reg: CVIF8(reg) */
			c = l.getCost(burm_reg_NT) + 12;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 82);
				doClosure_reg(p, c + 0);
			}
		}
		if (	/* reg: CVIF8(INDIRI4(addr)) */
			l.getOperator() == 4165 /* INDIRI4 */
		) {
			c = l.getLeft().getCost(burm_addr_NT) + 10;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 80);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 16513: /* CVIF16 */
		break;

		case 1157: /* CVII1 */
		{	/* reg: CVII1(reg) */
			c = l.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 62);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 2181: /* CVII2 */
		{	/* reg: CVII2(reg) */
			c = l.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 63);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 4229: /* CVII4 */
		{	/* reg: CVII4(reg) */
			c = l.getCost(burm_reg_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 58);
				doClosure_reg(p, c + 0);
			}
		}
		if (	/* reg: CVII4(INDIRI2(addr)) */
			l.getOperator() == 2117 /* INDIRI2 */
		) {
			c = l.getLeft().getCost(burm_addr_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 55);
				doClosure_reg(p, c + 0);
			}
		}
		if (	/* reg: CVII4(INDIRI1(addr)) */
			l.getOperator() == 1093 /* INDIRI1 */
		) {
			c = l.getLeft().getCost(burm_addr_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 54);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8325: /* CVII8 */
		break;

		case 1158: /* CVIU1 */
		break;

		case 2182: /* CVIU2 */
		break;

		case 4230: /* CVIU4 */
		{	/* reg: CVIU4(reg) */
			c = l.getCost(burm_reg_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 59);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8326: /* CVIU8 */
		break;

		case 4247: /* CVPP4 */
		break;

		case 8343: /* CVPP8 */
		break;

		case 16535: /* CVPP16 */
		break;

		case 4246: /* CVPU4 */
		{	/* reg: CVPU4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 52);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8342: /* CVPU8 */
		break;

		case 1205: /* CVUI1 */
		break;

		case 2229: /* CVUI2 */
		break;

		case 4277: /* CVUI4 */
		{	/* reg: CVUI4(reg) */
			c = l.getCost(burm_reg_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 60);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8373: /* CVUI8 */
		break;

		case 4279: /* CVUP4 */
		{	/* reg: CVUP4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 53);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8375: /* CVUP8 */
		break;

		case 16567: /* CVUP16 */
		break;

		case 1206: /* CVUU1 */
		{	/* reg: CVUU1(reg) */
			c = l.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 64);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 2230: /* CVUU2 */
		{	/* reg: CVUU2(reg) */
			c = l.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 65);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 4278: /* CVUU4 */
		{	/* reg: CVUU4(reg) */
			c = l.getCost(burm_reg_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 61);
				doClosure_reg(p, c + 0);
			}
		}
		if (	/* reg: CVUU4(INDIRU2(addr)) */
			l.getOperator() == 2118 /* INDIRU2 */
		) {
			c = l.getLeft().getCost(burm_addr_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 57);
				doClosure_reg(p, c + 0);
			}
		}
		if (	/* reg: CVUU4(INDIRU1(addr)) */
			l.getOperator() == 1094 /* INDIRU1 */
		) {
			c = l.getLeft().getCost(burm_addr_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 56);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8374: /* CVUU8 */
		break;

		case 4289: /* NEGF4 */
		{	/* reg: NEGF4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 68);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8385: /* NEGF8 */
		{	/* reg: NEGF8(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 67);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 16577: /* NEGF16 */
		break;

		case 4293: /* NEGI4 */
		{	/* reg: NEGI4(reg) */
			c = l.getCost(burm_reg_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 36);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8389: /* NEGI8 */
		break;

		case 217: /* CALLB */
		break;

		case 4305: /* CALLF4 */
		{	/* stmt: CALLF4(addrj) */
			c = l.getCost(burm_addrj_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 91);
			}
		}
		{	/* reg: CALLF4(addrj) */
			c = l.getCost(burm_addrj_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 86);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8401: /* CALLF8 */
		{	/* stmt: CALLF8(addrj) */
			c = l.getCost(burm_addrj_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 92);
			}
		}
		{	/* reg: CALLF8(addrj) */
			c = l.getCost(burm_addrj_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 87);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 16593: /* CALLF16 */
		break;

		case 4309: /* CALLI4 */
		{	/* reg: CALLI4(addrj) */
			c = l.getCost(burm_addrj_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 83);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8405: /* CALLI8 */
		break;

		case 4311: /* CALLP4 */
		{	/* reg: CALLP4(addrj) */
			c = l.getCost(burm_addrj_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 85);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8407: /* CALLP8 */
		break;

		case 4310: /* CALLU4 */
		{	/* reg: CALLU4(addrj) */
			c = l.getCost(burm_addrj_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 84);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8406: /* CALLU8 */
		break;

		case 216: /* CALLV */
		{	/* stmt: CALLV(addrj) */
			c = l.getCost(burm_addrj_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 90);
			}
		}
		break;

		case 4337: /* RETF4 */
		{	/* stmt: RETF4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 96);
			}
		}
		break;

		case 8433: /* RETF8 */
		{	/* stmt: RETF8(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 97);
			}
		}
		break;

		case 16625: /* RETF16 */
		break;

		case 4341: /* RETI4 */
		{	/* stmt: RETI4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 93);
			}
		}
		break;

		case 8437: /* RETI8 */
		break;

		case 4343: /* RETP4 */
		{	/* stmt: RETP4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 95);
			}
		}
		break;

		case 8439: /* RETP8 */
		break;

		case 4342: /* RETU4 */
		{	/* stmt: RETU4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 94);
			}
		}
		break;

		case 8438: /* RETU8 */
		break;

		case 248: /* RETV */
		break;

		case 4359: /* ADDRGP4 */
		{	/* addrj: ADDRGP4 */
			c = 0;
			if (c + 0 < p.getCost(burm_addrj_NT)) {
				p.setCostRule(burm_addrj_NT, c + 0, 1);
			}
		}
		{	/* base: ADDRGP4 */
			c = 0;
			if (c + 0 < p.getCost(burm_base_NT)) {
				p.setCostRule(burm_base_NT, c + 0, 1);
				doClosure_base(p, c + 0);
			}
		}
		{	/* acon: ADDRGP4 */
			c = 0;
			if (c + 0 < p.getCost(burm_acon_NT)) {
				p.setCostRule(burm_acon_NT, c + 0, 1);
			}
		}
		break;

		case 8455: /* ADDRGP8 */
		break;

		case 4375: /* ADDRFP4 */
		{	/* base: ADDRFP4 */
			c = 0;
			if (c + 0 < p.getCost(burm_base_NT)) {
				p.setCostRule(burm_base_NT, c + 0, 6);
				doClosure_base(p, c + 0);
			}
		}
		break;

		case 8471: /* ADDRFP8 */
		break;

		case 4391: /* ADDRLP4 */
		{	/* base: ADDRLP4 */
			c = 0;
			if (c + 0 < p.getCost(burm_base_NT)) {
				p.setCostRule(burm_base_NT, c + 0, 7);
				doClosure_base(p, c + 0);
			}
		}
		break;

		case 8487: /* ADDRLP8 */
		break;

		case 4401: /* ADDF4 */
		{	/* reg: ADDF4(reg,flt) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_flt_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 70);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8497: /* ADDF8 */
		{	/* reg: ADDF8(reg,flt) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_flt_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 69);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 16689: /* ADDF16 */
		break;

		case 4405: /* ADDI4 */
		{	/* reg: ADDI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 22);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* addr: ADDI4(index,base) */
			c = l.getCost(burm_index_NT) + r.getCost(burm_base_NT) + 0;
			if (c + 0 < p.getCost(burm_addr_NT)) {
				p.setCostRule(burm_addr_NT, c + 0, 2);
				doClosure_addr(p, c + 0);
			}
		}
		{	/* base: ADDI4(reg,acon) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_acon_NT) + 0;
			if (c + 0 < p.getCost(burm_base_NT)) {
				p.setCostRule(burm_base_NT, c + 0, 3);
				doClosure_base(p, c + 0);
			}
		}
		break;

		case 8501: /* ADDI8 */
		break;

		case 4407: /* ADDP4 */
		{	/* reg: ADDP4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 23);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* addr: ADDP4(index,base) */
			c = l.getCost(burm_index_NT) + r.getCost(burm_base_NT) + 0;
			if (c + 0 < p.getCost(burm_addr_NT)) {
				p.setCostRule(burm_addr_NT, c + 0, 3);
				doClosure_addr(p, c + 0);
			}
		}
		{	/* base: ADDP4(reg,acon) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_acon_NT) + 0;
			if (c + 0 < p.getCost(burm_base_NT)) {
				p.setCostRule(burm_base_NT, c + 0, 4);
				doClosure_base(p, c + 0);
			}
		}
		break;

		case 8503: /* ADDP8 */
		break;

		case 4406: /* ADDU4 */
		{	/* reg: ADDU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 24);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* addr: ADDU4(index,base) */
			c = l.getCost(burm_index_NT) + r.getCost(burm_base_NT) + 0;
			if (c + 0 < p.getCost(burm_addr_NT)) {
				p.setCostRule(burm_addr_NT, c + 0, 4);
				doClosure_addr(p, c + 0);
			}
		}
		{	/* base: ADDU4(reg,acon) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_acon_NT) + 0;
			if (c + 0 < p.getCost(burm_base_NT)) {
				p.setCostRule(burm_base_NT, c + 0, 5);
				doClosure_base(p, c + 0);
			}
		}
		break;

		case 8502: /* ADDU8 */
		break;

		case 4417: /* SUBF4 */
		{	/* reg: SUBF4(reg,flt) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_flt_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 76);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8513: /* SUBF8 */
		{	/* reg: SUBF8(reg,flt) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_flt_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 75);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 16705: /* SUBF16 */
		break;

		case 4421: /* SUBI4 */
		{	/* reg: SUBI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 25);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8517: /* SUBI8 */
		break;

		case 4423: /* SUBP4 */
		{	/* reg: SUBP4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 26);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8519: /* SUBP8 */
		break;

		case 4422: /* SUBU4 */
		{	/* reg: SUBU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 27);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8518: /* SUBU8 */
		break;

		case 4437: /* LSHI4 */
		{	/* reg: LSHI4(reg,reg) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_reg_NT) + 3;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 41);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* reg: LSHI4(reg,con5) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con5_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 37);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* index: LSHI4(reg,con3) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con3_NT) + 0;
			if (c + 0 < p.getCost(burm_index_NT)) {
				p.setCostRule(burm_index_NT, c + 0, 4);
				doClosure_index(p, c + 0);
			}
		}
		{	/* index: LSHI4(reg,con2) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con2_NT) + 0;
			if (c + 0 < p.getCost(burm_index_NT)) {
				p.setCostRule(burm_index_NT, c + 0, 3);
				doClosure_index(p, c + 0);
			}
		}
		{	/* index: LSHI4(reg,con1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con1_NT) + 0;
			if (c + 0 < p.getCost(burm_index_NT)) {
				p.setCostRule(burm_index_NT, c + 0, 2);
				doClosure_index(p, c + 0);
			}
		}
		break;

		case 8533: /* LSHI8 */
		break;

		case 4438: /* LSHU4 */
		{	/* reg: LSHU4(reg,reg) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_reg_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 42);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* reg: LSHU4(reg,con5) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con5_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 38);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* index: LSHU4(reg,con3) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con3_NT) + 0;
			if (c + 0 < p.getCost(burm_index_NT)) {
				p.setCostRule(burm_index_NT, c + 0, 7);
				doClosure_index(p, c + 0);
			}
		}
		{	/* index: LSHU4(reg,con2) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con2_NT) + 0;
			if (c + 0 < p.getCost(burm_index_NT)) {
				p.setCostRule(burm_index_NT, c + 0, 6);
				doClosure_index(p, c + 0);
			}
		}
		{	/* index: LSHU4(reg,con1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con1_NT) + 0;
			if (c + 0 < p.getCost(burm_index_NT)) {
				p.setCostRule(burm_index_NT, c + 0, 5);
				doClosure_index(p, c + 0);
			}
		}
		break;

		case 8534: /* LSHU8 */
		break;

		case 4453: /* MODI4 */
		{	/* reg: MODI4(reg,reg) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 51);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8549: /* MODI8 */
		break;

		case 4454: /* MODU4 */
		{	/* reg: MODU4(reg,reg) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 49);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8550: /* MODU8 */
		break;

		case 4469: /* RSHI4 */
		{	/* reg: RSHI4(reg,reg) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_reg_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 43);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* reg: RSHI4(reg,con5) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con5_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 39);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8565: /* RSHI8 */
		break;

		case 4470: /* RSHU4 */
		{	/* reg: RSHU4(reg,reg) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_reg_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 44);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* reg: RSHU4(reg,con5) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_con5_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 40);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8566: /* RSHU8 */
		break;

		case 4485: /* BANDI4 */
		{	/* reg: BANDI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 28);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8581: /* BANDI8 */
		break;

		case 4486: /* BANDU4 */
		{	/* reg: BANDU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 31);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8582: /* BANDU8 */
		break;

		case 4501: /* BCOMI4 */
		{	/* reg: BCOMI4(reg) */
			c = l.getCost(burm_reg_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 34);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8597: /* BCOMI8 */
		break;

		case 4502: /* BCOMU4 */
		{	/* reg: BCOMU4(reg) */
			c = l.getCost(burm_reg_NT) + 2;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 35);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8598: /* BCOMU8 */
		break;

		case 4517: /* BORI4 */
		{	/* reg: BORI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 29);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8613: /* BORI8 */
		break;

		case 4518: /* BORU4 */
		{	/* reg: BORU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 32);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8614: /* BORU8 */
		break;

		case 4533: /* BXORI4 */
		{	/* reg: BXORI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 30);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8629: /* BXORI8 */
		break;

		case 4534: /* BXORU4 */
		{	/* reg: BXORU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 33);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8630: /* BXORU8 */
		break;

		case 4545: /* DIVF4 */
		{	/* reg: DIVF4(reg,flt) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_flt_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 72);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8641: /* DIVF8 */
		{	/* reg: DIVF8(reg,flt) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_flt_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 71);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 16833: /* DIVF16 */
		break;

		case 4549: /* DIVI4 */
		{	/* reg: DIVI4(reg,reg) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 50);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8645: /* DIVI8 */
		break;

		case 4550: /* DIVU4 */
		{	/* reg: DIVU4(reg,reg) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 48);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8646: /* DIVU8 */
		break;

		case 4561: /* MULF4 */
		{	/* reg: MULF4(reg,flt) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_flt_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 74);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8657: /* MULF8 */
		{	/* reg: MULF8(reg,flt) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_flt_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 73);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 16849: /* MULF16 */
		break;

		case 4565: /* MULI4 */
		{	/* reg: MULI4(con,mr) */
			c = l.getCost(burm_con_NT) + r.getCost(burm_mr_NT) + 13;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 46);
				doClosure_reg(p, c + 0);
			}
		}
		{	/* reg: MULI4(reg,mrc3) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc3_NT) + 14;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 45);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8661: /* MULI8 */
		break;

		case 4566: /* MULU4 */
		{	/* reg: MULU4(reg,mr) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mr_NT) + 13;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 47);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8662: /* MULU8 */
		break;

		case 4577: /* EQF4 */
		{	/* stmt: EQF4(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 84);
			}
		}
		break;

		case 8673: /* EQF8 */
		{	/* stmt: EQF8(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 78);
			}
		}
		break;

		case 16865: /* EQF16 */
		break;

		case 4581: /* EQI4 */
		{	/* stmt: EQI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 66);
			}
		}
		{	/* stmt: EQI4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 56);
			}
		}
		break;

		case 8677: /* EQI8 */
		break;

		case 4582: /* EQU4 */
		{	/* stmt: EQU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 72);
			}
		}
		break;

		case 8678: /* EQU8 */
		break;

		case 4593: /* GEF4 */
		{	/* stmt: GEF4(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 85);
			}
		}
		break;

		case 8689: /* GEF8 */
		{	/* stmt: GEF8(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 79);
			}
		}
		break;

		case 4597: /* GEI4 */
		{	/* stmt: GEI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 67);
			}
		}
		{	/* stmt: GEI4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 57);
			}
		}
		break;

		case 8693: /* GEI8 */
		break;

		case 16885: /* GEI16 */
		break;

		case 4598: /* GEU4 */
		{	/* stmt: GEU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 73);
			}
		}
		{	/* stmt: GEU4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 62);
			}
		}
		break;

		case 8694: /* GEU8 */
		break;

		case 4609: /* GTF4 */
		{	/* stmt: GTF4(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 86);
			}
		}
		break;

		case 8705: /* GTF8 */
		{	/* stmt: GTF8(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 80);
			}
		}
		break;

		case 16897: /* GTF16 */
		break;

		case 4613: /* GTI4 */
		{	/* stmt: GTI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 68);
			}
		}
		{	/* stmt: GTI4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 58);
			}
		}
		break;

		case 8709: /* GTI8 */
		break;

		case 4614: /* GTU4 */
		{	/* stmt: GTU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 74);
			}
		}
		{	/* stmt: GTU4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 63);
			}
		}
		break;

		case 8710: /* GTU8 */
		break;

		case 4625: /* LEF4 */
		{	/* stmt: LEF4(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 87);
			}
		}
		break;

		case 8721: /* LEF8 */
		{	/* stmt: LEF8(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 81);
			}
		}
		break;

		case 16913: /* LEF16 */
		break;

		case 4629: /* LEI4 */
		{	/* stmt: LEI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 69);
			}
		}
		{	/* stmt: LEI4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 59);
			}
		}
		break;

		case 8725: /* LEI8 */
		break;

		case 4630: /* LEU4 */
		{	/* stmt: LEU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 75);
			}
		}
		{	/* stmt: LEU4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 64);
			}
		}
		break;

		case 8726: /* LEU8 */
		break;

		case 4641: /* LTF4 */
		{	/* stmt: LTF4(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 88);
			}
		}
		break;

		case 8737: /* LTF8 */
		{	/* stmt: LTF8(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 82);
			}
		}
		break;

		case 16929: /* LTF16 */
		break;

		case 4645: /* LTI4 */
		{	/* stmt: LTI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 70);
			}
		}
		{	/* stmt: LTI4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 60);
			}
		}
		break;

		case 8741: /* LTI8 */
		break;

		case 4646: /* LTU4 */
		{	/* stmt: LTU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 76);
			}
		}
		{	/* stmt: LTU4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 65);
			}
		}
		break;

		case 8742: /* LTU8 */
		break;

		case 4657: /* NEF4 */
		{	/* stmt: NEF4(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 89);
			}
		}
		break;

		case 8753: /* NEF8 */
		{	/* stmt: NEF8(cmpf,reg) */
			c = l.getCost(burm_cmpf_NT) + r.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 83);
			}
		}
		break;

		case 16945: /* NEF16 */
		break;

		case 4661: /* NEI4 */
		{	/* stmt: NEI4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 71);
			}
		}
		{	/* stmt: NEI4(mem,rc) */
			c = l.getCost(burm_mem_NT) + r.getCost(burm_rc_NT) + 5;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 61);
			}
		}
		break;

		case 8757: /* NEI8 */
		break;

		case 4662: /* NEU4 */
		{	/* stmt: NEU4(reg,mrc1) */
			c = l.getCost(burm_reg_NT) + r.getCost(burm_mrc1_NT) + 4;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 77);
			}
		}
		break;

		case 8758: /* NEU8 */
		break;

		case 584: /* JUMPV */
		{	/* stmt: JUMPV(addrj) */
			c = l.getCost(burm_addrj_NT) + 3;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 54);
			}
		}
		break;

		case 600: /* LABELV */
		{	/* stmt: LABELV */
			c = 0;
			if (c + 0 < p.getCost(burm_stmt_NT)) {
				p.setCostRule(burm_stmt_NT, c + 0, 55);
			}
		}
		break;

		case 233: /* LOADB */
		break;

		case 4321: /* LOADF4 */
		break;

		case 8417: /* LOADF8 */
		break;

		case 16609: /* LOADF16 */
		break;

		case 1253: /* LOADI1 */
		{	/* reg: LOADI1(reg) */
			c = l.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 15);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 2277: /* LOADI2 */
		{	/* reg: LOADI2(reg) */
			c = l.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 16);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 4325: /* LOADI4 */
		{	/* reg: LOADI4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 17);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8421: /* LOADI8 */
		break;

		case 4327: /* LOADP4 */
		{	/* reg: LOADP4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 21);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8423: /* LOADP8 */
		break;

		case 1254: /* LOADU1 */
		{	/* reg: LOADU1(reg) */
			c = l.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 18);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 2278: /* LOADU2 */
		{	/* reg: LOADU2(reg) */
			c = l.getCost(burm_reg_NT) + 1;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 19);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 4326: /* LOADU4 */
		{	/* reg: LOADU4(reg) */
			c = l.getCost(burm_reg_NT) + 0;
			if (c + 0 < p.getCost(burm_reg_NT)) {
				p.setCostRule(burm_reg_NT, c + 0, 20);
				doClosure_reg(p, c + 0);
			}
		}
		break;

		case 8422: /* LOADU8 */
		break;

		case 711: /* VREGP */
		break;

		default:
			throw new AssertionError();
		}
		return p;
	}

	public void burm_kids(STree p, int eruleno, STree kids[]) {
		switch (eruleno) {
		case 200: /* stmt: LABELV */
		case 196: /* addrj: ADDRGP4 */
		case 131: /* con5: CNSTI4 */
		case 54: /* con3: CNSTU4 */
		case 53: /* con3: CNSTI4 */
		case 52: /* con2: CNSTU4 */
		case 51: /* con2: CNSTI4 */
		case 50: /* con1: CNSTU4 */
		case 49: /* con1: CNSTI4 */
		case 44: /* base: ADDRLP4 */
		case 43: /* base: ADDRFP4 */
		case 38: /* base: ADDRGP4 */
		case 36: /* acon: ADDRGP4 */
		case 34: /* con: CNSTP8 */
		case 33: /* con: CNSTU8 */
		case 32: /* con: CNSTI8 */
		case 31: /* con: CNSTP4 */
		case 30: /* con: CNSTU4 */
		case 29: /* con: CNSTI4 */
		case 28: /* con: CNSTU2 */
		case 27: /* con: CNSTI2 */
		case 26: /* con: CNSTU1 */
		case 25: /* con: CNSTI1 */
		case 12: /* reg: INDIRU8(VREGP) */
		case 11: /* reg: INDIRP8(VREGP) */
		case 10: /* reg: INDIRI8(VREGP) */
		case 9: /* reg: INDIRF8(VREGP) */
		case 8: /* reg: INDIRU4(VREGP) */
		case 7: /* reg: INDIRP4(VREGP) */
		case 6: /* reg: INDIRI4(VREGP) */
		case 5: /* reg: INDIRF4(VREGP) */
		case 4: /* reg: INDIRU2(VREGP) */
		case 3: /* reg: INDIRI2(VREGP) */
		case 2: /* reg: INDIRU1(VREGP) */
		case 1: /* reg: INDIRI1(VREGP) */
			break;

		case 24: /* stmt: ASGNU8(VREGP,reg) */
		case 23: /* stmt: ASGNP8(VREGP,reg) */
		case 22: /* stmt: ASGNI8(VREGP,reg) */
		case 21: /* stmt: ASGNF8(VREGP,reg) */
		case 20: /* stmt: ASGNU4(VREGP,reg) */
		case 19: /* stmt: ASGNP4(VREGP,reg) */
		case 18: /* stmt: ASGNI4(VREGP,reg) */
		case 17: /* stmt: ASGNF4(VREGP,reg) */
		case 16: /* stmt: ASGNU2(VREGP,reg) */
		case 15: /* stmt: ASGNI2(VREGP,reg) */
		case 14: /* stmt: ASGNU1(VREGP,reg) */
		case 13: /* stmt: ASGNI1(VREGP,reg) */
			kids[0] = p.getRight();
			break;

		case 224: /* cmpf: reg */
		case 223: /* cmpf: memf */
		case 198: /* addrj: mem */
		case 197: /* addrj: reg */
		case 181: /* flt: reg */
		case 180: /* flt: memf */
		case 172: /* reg: memf */
		case 81: /* reg: mrc0 */
		case 80: /* reg: addr */
		case 79: /* mrc3: rc */
		case 78: /* mrc3: mem */
		case 77: /* mrc1: rc */
		case 76: /* mrc1: mem */
		case 75: /* mrc0: rc */
		case 74: /* mrc0: mem */
		case 73: /* mr: mem */
		case 72: /* mr: reg */
		case 71: /* rc: con */
		case 70: /* rc: reg */
		case 62: /* addr: index */
		case 58: /* addr: base */
		case 45: /* index: reg */
		case 39: /* base: reg */
		case 37: /* acon: con */
		case 35: /* stmt: reg */
			kids[0] = p;
			break;

		case 236: /* stmt: NEF4(cmpf,reg) */
		case 235: /* stmt: LTF4(cmpf,reg) */
		case 234: /* stmt: LEF4(cmpf,reg) */
		case 233: /* stmt: GTF4(cmpf,reg) */
		case 232: /* stmt: GEF4(cmpf,reg) */
		case 231: /* stmt: EQF4(cmpf,reg) */
		case 230: /* stmt: NEF8(cmpf,reg) */
		case 229: /* stmt: LTF8(cmpf,reg) */
		case 228: /* stmt: LEF8(cmpf,reg) */
		case 227: /* stmt: GTF8(cmpf,reg) */
		case 226: /* stmt: GEF8(cmpf,reg) */
		case 225: /* stmt: EQF8(cmpf,reg) */
		case 222: /* stmt: NEU4(reg,mrc1) */
		case 221: /* stmt: LTU4(reg,mrc1) */
		case 220: /* stmt: LEU4(reg,mrc1) */
		case 219: /* stmt: GTU4(reg,mrc1) */
		case 218: /* stmt: GEU4(reg,mrc1) */
		case 217: /* stmt: EQU4(reg,mrc1) */
		case 216: /* stmt: NEI4(reg,mrc1) */
		case 215: /* stmt: LTI4(reg,mrc1) */
		case 214: /* stmt: LEI4(reg,mrc1) */
		case 213: /* stmt: GTI4(reg,mrc1) */
		case 212: /* stmt: GEI4(reg,mrc1) */
		case 211: /* stmt: EQI4(reg,mrc1) */
		case 210: /* stmt: LTU4(mem,rc) */
		case 209: /* stmt: LEU4(mem,rc) */
		case 208: /* stmt: GTU4(mem,rc) */
		case 207: /* stmt: GEU4(mem,rc) */
		case 206: /* stmt: NEI4(mem,rc) */
		case 205: /* stmt: LTI4(mem,rc) */
		case 204: /* stmt: LEI4(mem,rc) */
		case 203: /* stmt: GTI4(mem,rc) */
		case 202: /* stmt: GEI4(mem,rc) */
		case 201: /* stmt: EQI4(mem,rc) */
		case 189: /* reg: SUBF4(reg,flt) */
		case 188: /* reg: SUBF8(reg,flt) */
		case 187: /* reg: MULF4(reg,flt) */
		case 186: /* reg: MULF8(reg,flt) */
		case 185: /* reg: DIVF4(reg,flt) */
		case 184: /* reg: DIVF8(reg,flt) */
		case 183: /* reg: ADDF4(reg,flt) */
		case 182: /* reg: ADDF8(reg,flt) */
		case 174: /* stmt: ASGNF4(addr,reg) */
		case 173: /* stmt: ASGNF8(addr,reg) */
		case 163: /* stmt: ASGNP4(addr,rc) */
		case 162: /* stmt: ASGNU4(addr,rc) */
		case 161: /* stmt: ASGNU2(addr,rc) */
		case 160: /* stmt: ASGNU1(addr,rc) */
		case 159: /* stmt: ASGNI4(addr,rc) */
		case 158: /* stmt: ASGNI2(addr,rc) */
		case 157: /* stmt: ASGNI1(addr,rc) */
		case 142: /* reg: MODI4(reg,reg) */
		case 141: /* reg: DIVI4(reg,reg) */
		case 140: /* reg: MODU4(reg,reg) */
		case 139: /* reg: DIVU4(reg,reg) */
		case 138: /* reg: MULU4(reg,mr) */
		case 137: /* reg: MULI4(con,mr) */
		case 136: /* reg: MULI4(reg,mrc3) */
		case 135: /* reg: RSHU4(reg,reg) */
		case 134: /* reg: RSHI4(reg,reg) */
		case 133: /* reg: LSHU4(reg,reg) */
		case 132: /* reg: LSHI4(reg,reg) */
		case 126: /* reg: RSHU4(reg,con5) */
		case 125: /* reg: RSHI4(reg,con5) */
		case 124: /* reg: LSHU4(reg,con5) */
		case 123: /* reg: LSHI4(reg,con5) */
		case 100: /* reg: BXORU4(reg,mrc1) */
		case 99: /* reg: BORU4(reg,mrc1) */
		case 98: /* reg: BANDU4(reg,mrc1) */
		case 97: /* reg: BXORI4(reg,mrc1) */
		case 96: /* reg: BORI4(reg,mrc1) */
		case 95: /* reg: BANDI4(reg,mrc1) */
		case 94: /* reg: SUBU4(reg,mrc1) */
		case 93: /* reg: SUBP4(reg,mrc1) */
		case 92: /* reg: SUBI4(reg,mrc1) */
		case 91: /* reg: ADDU4(reg,mrc1) */
		case 90: /* reg: ADDP4(reg,mrc1) */
		case 89: /* reg: ADDI4(reg,mrc1) */
		case 61: /* addr: ADDU4(index,base) */
		case 60: /* addr: ADDP4(index,base) */
		case 59: /* addr: ADDI4(index,base) */
		case 57: /* index: LSHU4(reg,con3) */
		case 56: /* index: LSHU4(reg,con2) */
		case 55: /* index: LSHU4(reg,con1) */
		case 48: /* index: LSHI4(reg,con3) */
		case 47: /* index: LSHI4(reg,con2) */
		case 46: /* index: LSHI4(reg,con1) */
		case 42: /* base: ADDU4(reg,acon) */
		case 41: /* base: ADDP4(reg,acon) */
		case 40: /* base: ADDI4(reg,acon) */
			kids[0] = p.getLeft();
			kids[1] = p.getRight();
			break;

		case 249: /* stmt: RETF8(reg) */
		case 248: /* stmt: RETF4(reg) */
		case 247: /* stmt: RETP4(reg) */
		case 246: /* stmt: RETU4(reg) */
		case 245: /* stmt: RETI4(reg) */
		case 244: /* stmt: CALLF8(addrj) */
		case 243: /* stmt: CALLF4(addrj) */
		case 242: /* reg: CALLF8(addrj) */
		case 241: /* reg: CALLF4(addrj) */
		case 240: /* stmt: CALLV(addrj) */
		case 239: /* reg: CALLP4(addrj) */
		case 238: /* reg: CALLU4(addrj) */
		case 237: /* reg: CALLI4(addrj) */
		case 199: /* stmt: JUMPV(addrj) */
		case 195: /* reg: CVIF8(reg) */
		case 194: /* reg: CVIF4(reg) */
		case 192: /* reg: CVFI4(reg) */
		case 191: /* reg: CVFF4(reg) */
		case 190: /* reg: CVFF8(reg) */
		case 179: /* reg: NEGF4(reg) */
		case 178: /* reg: NEGF8(reg) */
		case 177: /* stmt: ARGF4(reg) */
		case 176: /* stmt: ARGF8(reg) */
		case 170: /* memf: INDIRF4(addr) */
		case 169: /* memf: INDIRF8(addr) */
		case 166: /* stmt: ARGP4(mrc3) */
		case 165: /* stmt: ARGU4(mrc3) */
		case 164: /* stmt: ARGI4(mrc3) */
		case 156: /* reg: CVUU2(reg) */
		case 155: /* reg: CVUU1(reg) */
		case 154: /* reg: CVII2(reg) */
		case 153: /* reg: CVII1(reg) */
		case 152: /* reg: CVUU4(reg) */
		case 151: /* reg: CVUI4(reg) */
		case 150: /* reg: CVIU4(reg) */
		case 149: /* reg: CVII4(reg) */
		case 144: /* reg: CVUP4(reg) */
		case 143: /* reg: CVPU4(reg) */
		case 119: /* reg: NEGI4(reg) */
		case 118: /* reg: BCOMU4(reg) */
		case 117: /* reg: BCOMI4(reg) */
		case 88: /* reg: LOADP4(reg) */
		case 87: /* reg: LOADU4(reg) */
		case 86: /* reg: LOADU2(reg) */
		case 85: /* reg: LOADU1(reg) */
		case 84: /* reg: LOADI4(reg) */
		case 83: /* reg: LOADI2(reg) */
		case 82: /* reg: LOADI1(reg) */
		case 69: /* mem: INDIRP4(addr) */
		case 68: /* mem: INDIRU4(addr) */
		case 67: /* mem: INDIRU2(addr) */
		case 66: /* mem: INDIRU1(addr) */
		case 65: /* mem: INDIRI4(addr) */
		case 64: /* mem: INDIRI2(addr) */
		case 63: /* mem: INDIRI1(addr) */
			kids[0] = p.getLeft();
			break;

		case 130: /* stmt: ASGNI4(addr,RSHU4(mem,con5)) */
		case 129: /* stmt: ASGNI4(addr,RSHI4(mem,con5)) */
		case 128: /* stmt: ASGNI4(addr,LSHU4(mem,con5)) */
		case 127: /* stmt: ASGNI4(addr,LSHI4(mem,con5)) */
		case 116: /* stmt: ASGNU4(addr,BXORU4(mem,rc)) */
		case 115: /* stmt: ASGNU4(addr,BORU4(mem,rc)) */
		case 114: /* stmt: ASGNU4(addr,BANDU4(mem,rc)) */
		case 113: /* stmt: ASGNI4(addr,BXORI4(mem,rc)) */
		case 112: /* stmt: ASGNI4(addr,BORI4(mem,rc)) */
		case 111: /* stmt: ASGNI4(addr,BANDI4(mem,rc)) */
		case 110: /* stmt: ASGNU4(addr,SUBU4(mem,rc)) */
		case 109: /* stmt: ASGNU4(addr,ADDU4(mem,rc)) */
		case 108: /* stmt: ASGNI4(addr,SUBI4(mem,rc)) */
		case 107: /* stmt: ASGNI4(addr,ADDI4(mem,rc)) */
		case 106: /* stmt: ASGNP4(addr,SUBP4(mem,con1)) */
		case 105: /* stmt: ASGNI4(addr,SUBU4(mem,con1)) */
		case 104: /* stmt: ASGNI4(addr,SUBI4(mem,con1)) */
		case 103: /* stmt: ASGNP4(addr,ADDP4(mem,con1)) */
		case 102: /* stmt: ASGNI4(addr,ADDU4(mem,con1)) */
		case 101: /* stmt: ASGNI4(addr,ADDI4(mem,con1)) */
			kids[0] = p.getLeft();
			kids[1] = p.getRight().getLeft();
			kids[2] = p.getRight().getRight();
			break;

		case 175: /* stmt: ASGNF4(addr,CVFF4(reg)) */
		case 167: /* stmt: ASGNB(reg,INDIRB(reg)) */
		case 122: /* stmt: ASGNI4(addr,NEGI4(mem)) */
		case 121: /* stmt: ASGNU4(addr,BCOMU4(mem)) */
		case 120: /* stmt: ASGNI4(addr,BCOMI4(mem)) */
			kids[0] = p.getLeft();
			kids[1] = p.getRight().getLeft();
			break;

		case 193: /* reg: CVIF8(INDIRI4(addr)) */
		case 171: /* memf: CVFF8(INDIRF4(addr)) */
		case 168: /* stmt: ARGB(INDIRB(reg)) */
		case 148: /* reg: CVUU4(INDIRU2(addr)) */
		case 147: /* reg: CVUU4(INDIRU1(addr)) */
		case 146: /* reg: CVII4(INDIRI2(addr)) */
		case 145: /* reg: CVII4(INDIRI1(addr)) */
			kids[0] = p.getLeft().getLeft();
			break;

		default:
			throw new AssertionError("Bad external rule number");
		}
	}
}
