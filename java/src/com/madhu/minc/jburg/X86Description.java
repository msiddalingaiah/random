
package com.madhu.minc.jburg;

import java.io.IOException;

/*
 * Created on Jul 20, 2007 at 1:23:13 PM
 */
public class X86Description {
	private PatternMatcher s4;

	static int trace;

	public static void main(String[] args) throws IOException {
//		generateMatcher();
		test();
	}

	public X86Description() {
		s4 = new X86Machine();
	}

	/* burm_trace - print trace message for matching p; decrement trace */
	void burm_trace(STree p, int eruleno, int cost, int bestcost) {
		if (trace < 0) {
			System.out.println("0x%p matched %s = %d with cost %d vs. %d");
			//fprintf(stderr, "0x%p matched %s = %d with cost %d vs. %d\n", p,
			//	burm_string[eruleno], eruleno, cost, bestcost);
		} else if (trace > 0 && cost < bestcost) {
			--trace;
			System.out.println("0x%p matched %s = %d with cost %d");
			//fprintf(stderr, "0x%p matched %s = %d with cost %d\n", p,
			//	burm_string[eruleno], eruleno, cost);
		}
	}

	/* dumpCover - print the matched cover for p */
	void dumpCover(STree p, int goalnt, int indent) {
		int eruleno = s4.getRule(p.getState(), goalnt);
		short[] nts = s4.getNonTerminals(eruleno);
		STree[] kids = new STree[10];
		int i;

		for (i = 0; i < indent; i++) {
			System.out.print(" ");
		}
		System.out.println(s4.getRuleString(eruleno));
		s4.burm_kids(p, eruleno, kids);
		for (i = 0; nts[i] != 0; i++) {
			dumpCover(kids[i], nts[i], indent + 1);
		}
	}

	void doLabel(STree p) {
		if (p == null) {
			throw new AssertionError("NULL tree in burm_label");
		}
		switch (s4.getArity(p.getOp())) {
		case 0:
			p.setState(s4.getNextState(p.getOp()));
			break;
		case 1:
			doLabel(p.getLeft());
			p.setState(s4.getNextState(p.getOp(),
				p.getLeft().getState()));
			break;
		case 2:
			doLabel(p.getLeft());
			doLabel(p.getRight());
			p.setState(s4.getNextState(p.getOp(),
				p.getLeft().getState(),
				p.getRight().getState()));
			break;
		}
	}

	State label(STree p) {
		doLabel(p);
		return p.getState().getRule(PatternMatcher.GOAL_NT) != 0 ? p.getState() : null;
	}

	void gen(STree p) {
		if (label(p) == null) {
			System.out.println("no cover");
		} else {
			dumpCover(p, PatternMatcher.GOAL_NT, 0);
		}
	}

	// TODO stopped here
	public static void test() {
		System.out.println("jburg: i = c + 4;");
		X86Description m = new X86Description();
		STree c4 = new STree(CNSTI4, 4);
		STree cv = new STree(CVII1,
			new STree(INDIRI4,
				new STree(ADDRLP4)));
		STree t = new STree(ASGNI4,
			new STree(ADDRLP4),
			new STree(ADDI4, cv, c4));
		m.gen(t);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void generateMatcher() throws IOException {
		JBurg p = new JBurg();
		p.addSymbol("CNSTF4", 4113);
		p.addSymbol("CNSTF8", 8209);
		p.addSymbol("CNSTF16", 16401);
		p.addSymbol("CNSTI1", 1045);
		p.addSymbol("CNSTI2", 2069);
		p.addSymbol("CNSTI4", 4117);
		p.addSymbol("CNSTI8", 8213);
		p.addSymbol("CNSTP4", 4119);
		p.addSymbol("CNSTP8", 8215);
		p.addSymbol("CNSTU1", 1046);
		p.addSymbol("CNSTU2", 2070);
		p.addSymbol("CNSTU4", 4118);
		p.addSymbol("CNSTU8", 8214);

		p.addSymbol("ARGB", 41);
		p.addSymbol("ARGF4", 4129);
		p.addSymbol("ARGF8", 8225);
		p.addSymbol("ARGF16", 16417);
		p.addSymbol("ARGI4", 4133);
		p.addSymbol("ARGI8", 8229);
		p.addSymbol("ARGP4", 4135);
		p.addSymbol("ARGP8", 8231);
		p.addSymbol("ARGU4", 4134);
		p.addSymbol("ARGU8", 8230);

		p.addSymbol("ASGNB", 57);
		p.addSymbol("ASGNF4", 4145);
		p.addSymbol("ASGNF8", 8241);
		p.addSymbol("ASGNF16", 16433);
		p.addSymbol("ASGNI1", 1077);
		p.addSymbol("ASGNI2", 2101);
		p.addSymbol("ASGNI4", 4149);
		p.addSymbol("ASGNI8", 8245);
		p.addSymbol("ASGNP4", 4151);
		p.addSymbol("ASGNP8", 8247);
		p.addSymbol("ASGNU1", 1078);
		p.addSymbol("ASGNU2", 2102);
		p.addSymbol("ASGNU4", 4150);
		p.addSymbol("ASGNU8", 8246);

		p.addSymbol("INDIRB", 73);
		p.addSymbol("INDIRF4", 4161);
		p.addSymbol("INDIRF8", 8257);
		p.addSymbol("INDIRF16", 16449);
		p.addSymbol("INDIRI1", 1093);
		p.addSymbol("INDIRI2", 2117);
		p.addSymbol("INDIRI4", 4165);
		p.addSymbol("INDIRI8", 8261);
		p.addSymbol("INDIRP4", 4167);
		p.addSymbol("INDIRP8", 8263);
		p.addSymbol("INDIRU1", 1094);
		p.addSymbol("INDIRU2", 2118);
		p.addSymbol("INDIRU4", 4166);
		p.addSymbol("INDIRU8", 8262);

		p.addSymbol("CVFF4", 4209);
		p.addSymbol("CVFF8", 8305);
		p.addSymbol("CVFF16", 16497);
		p.addSymbol("CVFI4", 4213);
		p.addSymbol("CVFI8", 8309);

		p.addSymbol("CVIF4", 4225);
		p.addSymbol("CVIF8", 8321);
		p.addSymbol("CVIF16", 16513);
		p.addSymbol("CVII1", 1157);
		p.addSymbol("CVII2", 2181);
		p.addSymbol("CVII4", 4229);
		p.addSymbol("CVII8", 8325);
		p.addSymbol("CVIU1", 1158);
		p.addSymbol("CVIU2", 2182);
		p.addSymbol("CVIU4", 4230);
		p.addSymbol("CVIU8", 8326);

		p.addSymbol("CVPP4", 4247);
		p.addSymbol("CVPP8", 8343);
		p.addSymbol("CVPP16", 16535);
		p.addSymbol("CVPU4", 4246);
		p.addSymbol("CVPU8", 8342);

		p.addSymbol("CVUI1", 1205);
		p.addSymbol("CVUI2", 2229);
		p.addSymbol("CVUI4", 4277);
		p.addSymbol("CVUI8", 8373);
		p.addSymbol("CVUP4", 4279);
		p.addSymbol("CVUP8", 8375);
		p.addSymbol("CVUP16", 16567);
		p.addSymbol("CVUU1", 1206);
		p.addSymbol("CVUU2", 2230);
		p.addSymbol("CVUU4", 4278);
		p.addSymbol("CVUU8", 8374);

		p.addSymbol("NEGF4", 4289);
		p.addSymbol("NEGF8", 8385);
		p.addSymbol("NEGF16", 16577);
		p.addSymbol("NEGI4", 4293);
		p.addSymbol("NEGI8", 8389);

		p.addSymbol("CALLB", 217);
		p.addSymbol("CALLF4", 4305);
		p.addSymbol("CALLF8", 8401);
		p.addSymbol("CALLF16", 16593);
		p.addSymbol("CALLI4", 4309);
		p.addSymbol("CALLI8", 8405);
		p.addSymbol("CALLP4", 4311);
		p.addSymbol("CALLP8", 8407);
		p.addSymbol("CALLU4", 4310);
		p.addSymbol("CALLU8", 8406);
		p.addSymbol("CALLV", 216);

		p.addSymbol("RETF4", 4337);
		p.addSymbol("RETF8", 8433);
		p.addSymbol("RETF16", 16625);
		p.addSymbol("RETI4", 4341);
		p.addSymbol("RETI8", 8437);
		p.addSymbol("RETP4", 4343);
		p.addSymbol("RETP8", 8439);
		p.addSymbol("RETU4", 4342);
		p.addSymbol("RETU8", 8438);
		p.addSymbol("RETV", 248);

		p.addSymbol("ADDRGP4", 4359);
		p.addSymbol("ADDRGP8", 8455);

		p.addSymbol("ADDRFP4", 4375);
		p.addSymbol("ADDRFP8", 8471);

		p.addSymbol("ADDRLP4", 4391);
		p.addSymbol("ADDRLP8", 8487);

		p.addSymbol("ADDF4", 4401);
		p.addSymbol("ADDF8", 8497);
		p.addSymbol("ADDF16", 16689);
		p.addSymbol("ADDI4", 4405);
		p.addSymbol("ADDI8", 8501);
		p.addSymbol("ADDP4", 4407);
		p.addSymbol("ADDP8", 8503);
		p.addSymbol("ADDU4", 4406);
		p.addSymbol("ADDU8", 8502);

		p.addSymbol("SUBF4", 4417);
		p.addSymbol("SUBF8", 8513);
		p.addSymbol("SUBF16", 16705);
		p.addSymbol("SUBI4", 4421);
		p.addSymbol("SUBI8", 8517);
		p.addSymbol("SUBP4", 4423);
		p.addSymbol("SUBP8", 8519);
		p.addSymbol("SUBU4", 4422);
		p.addSymbol("SUBU8", 8518);

		p.addSymbol("LSHI4", 4437);
		p.addSymbol("LSHI8", 8533);
		p.addSymbol("LSHU4", 4438);
		p.addSymbol("LSHU8", 8534);

		p.addSymbol("MODI4", 4453);
		p.addSymbol("MODI8", 8549);
		p.addSymbol("MODU4", 4454);
		p.addSymbol("MODU8", 8550);

		p.addSymbol("RSHI4", 4469);
		p.addSymbol("RSHI8", 8565);
		p.addSymbol("RSHU4", 4470);
		p.addSymbol("RSHU8", 8566);

		p.addSymbol("BANDI4", 4485);
		p.addSymbol("BANDI8", 8581);
		p.addSymbol("BANDU4", 4486);
		p.addSymbol("BANDU8", 8582);

		p.addSymbol("BCOMI4", 4501);
		p.addSymbol("BCOMI8", 8597);
		p.addSymbol("BCOMU4", 4502);
		p.addSymbol("BCOMU8", 8598);

		p.addSymbol("BORI4", 4517);
		p.addSymbol("BORI8", 8613);
		p.addSymbol("BORU4", 4518);
		p.addSymbol("BORU8", 8614);

		p.addSymbol("BXORI4", 4533);
		p.addSymbol("BXORI8", 8629);
		p.addSymbol("BXORU4", 4534);
		p.addSymbol("BXORU8", 8630);

		p.addSymbol("DIVF4", 4545);
		p.addSymbol("DIVF8", 8641);
		p.addSymbol("DIVF16", 16833);
		p.addSymbol("DIVI4", 4549);
		p.addSymbol("DIVI8", 8645);
		p.addSymbol("DIVU4", 4550);
		p.addSymbol("DIVU8", 8646);

		p.addSymbol("MULF4", 4561);
		p.addSymbol("MULF8", 8657);
		p.addSymbol("MULF16", 16849);
		p.addSymbol("MULI4", 4565);
		p.addSymbol("MULI8", 8661);
		p.addSymbol("MULU4", 4566);
		p.addSymbol("MULU8", 8662);

		p.addSymbol("EQF4", 4577);
		p.addSymbol("EQF8", 8673);
		p.addSymbol("EQF16", 16865);
		p.addSymbol("EQI4", 4581);
		p.addSymbol("EQI8", 8677);
		p.addSymbol("EQU4", 4582);
		p.addSymbol("EQU8", 8678);

		p.addSymbol("GEF4", 4593);
		p.addSymbol("GEF8", 8689);
		p.addSymbol("GEI4", 4597);
		p.addSymbol("GEI8", 8693);
		p.addSymbol("GEI16", 16885);
		p.addSymbol("GEU4", 4598);
		p.addSymbol("GEU8", 8694);

		p.addSymbol("GTF4", 4609);
		p.addSymbol("GTF8", 8705);
		p.addSymbol("GTF16", 16897);
		p.addSymbol("GTI4", 4613);
		p.addSymbol("GTI8", 8709);
		p.addSymbol("GTU4", 4614);
		p.addSymbol("GTU8", 8710);

		p.addSymbol("LEF4", 4625);
		p.addSymbol("LEF8", 8721);
		p.addSymbol("LEF16", 16913);
		p.addSymbol("LEI4", 4629);
		p.addSymbol("LEI8", 8725);
		p.addSymbol("LEU4", 4630);
		p.addSymbol("LEU8", 8726);

		p.addSymbol("LTF4", 4641);
		p.addSymbol("LTF8", 8737);
		p.addSymbol("LTF16", 16929);
		p.addSymbol("LTI4", 4645);
		p.addSymbol("LTI8", 8741);
		p.addSymbol("LTU4", 4646);
		p.addSymbol("LTU8", 8742);

		p.addSymbol("NEF4", 4657);
		p.addSymbol("NEF8", 8753);
		p.addSymbol("NEF16", 16945);
		p.addSymbol("NEI4", 4661);
		p.addSymbol("NEI8", 8757);
		p.addSymbol("NEU4", 4662);
		p.addSymbol("NEU8", 8758);

		p.addSymbol("JUMPV", 584);

		p.addSymbol("LABELV", 600);

		p.addSymbol("LOADB", 233);
		p.addSymbol("LOADF4", 4321);
		p.addSymbol("LOADF8", 8417);
		p.addSymbol("LOADF16", 16609);
		p.addSymbol("LOADI1", 1253);
		p.addSymbol("LOADI2", 2277);
		p.addSymbol("LOADI4", 4325);
		p.addSymbol("LOADI8", 8421);
		p.addSymbol("LOADP4", 4327);
		p.addSymbol("LOADP8", 8423);
		p.addSymbol("LOADU1", 1254);
		p.addSymbol("LOADU2", 2278);
		p.addSymbol("LOADU4", 4326);
		p.addSymbol("LOADU8", 8422);

		p.addSymbol("VREGP", 711);

		p.addSymbol("stmt");
		p.addSymbol("reg");
		p.addSymbol("con");
		p.addSymbol("acon");
		p.addSymbol("base");
		p.addSymbol("index");
		p.addSymbol("con1");
		p.addSymbol("con2");
		p.addSymbol("con3");
		p.addSymbol("addr");
		p.addSymbol("mem");
		p.addSymbol("rc");
		p.addSymbol("mr");
		p.addSymbol("mrc0");
		p.addSymbol("mrc1");
		p.addSymbol("mrc3");
		p.addSymbol("con5");
		p.addSymbol("memf");
		p.addSymbol("flt");
		p.addSymbol("addrj");
		p.addSymbol("cmpf");

		p.addRule("reg", p.tree("INDIRI1", p.tree("VREGP")), "# read register\n");
		p.addRule("reg", p.tree("INDIRU1", p.tree("VREGP")), "# read register\n");

		p.addRule("reg", p.tree("INDIRI2", p.tree("VREGP")), "# read register\n");
		p.addRule("reg", p.tree("INDIRU2", p.tree("VREGP")), "# read register\n");

		p.addRule("reg", p.tree("INDIRF4", p.tree("VREGP")), "# read register\n");
		p.addRule("reg", p.tree("INDIRI4", p.tree("VREGP")), "# read register\n");
		p.addRule("reg", p.tree("INDIRP4", p.tree("VREGP")), "# read register\n");
		p.addRule("reg", p.tree("INDIRU4", p.tree("VREGP")), "# read register\n");

		p.addRule("reg", p.tree("INDIRF8", p.tree("VREGP")), "# read register\n");
		p.addRule("reg", p.tree("INDIRI8", p.tree("VREGP")), "# read register\n");
		p.addRule("reg", p.tree("INDIRP8", p.tree("VREGP")), "# read register\n");
		p.addRule("reg", p.tree("INDIRU8", p.tree("VREGP")), "# read register\n");

		p.addRule("stmt", p.tree("ASGNI1", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("stmt", p.tree("ASGNU1", p.tree("VREGP"),p.tree("reg")), "# write register\n");

		p.addRule("stmt", p.tree("ASGNI2", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("stmt", p.tree("ASGNU2", p.tree("VREGP"),p.tree("reg")), "# write register\n");

		p.addRule("stmt", p.tree("ASGNF4", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("stmt", p.tree("ASGNP4", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("stmt", p.tree("ASGNU4", p.tree("VREGP"),p.tree("reg")), "# write register\n");

		p.addRule("stmt", p.tree("ASGNF8", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("stmt", p.tree("ASGNI8", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("stmt", p.tree("ASGNP8", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("stmt", p.tree("ASGNU8", p.tree("VREGP"),p.tree("reg")), "# write register\n");
		p.addRule("con", p.tree("CNSTI1"),  "%a");
		p.addRule("con", p.tree("CNSTU1"),  "%a");

		p.addRule("con", p.tree("CNSTI2"),  "%a");
		p.addRule("con", p.tree("CNSTU2"),  "%a");

		p.addRule("con", p.tree("CNSTI4"),  "%a");
		p.addRule("con", p.tree("CNSTU4"),  "%a");
		p.addRule("con", p.tree("CNSTP4"),  "%a");

		p.addRule("con", p.tree("CNSTI8"),  "%a");
		p.addRule("con", p.tree("CNSTU8"),  "%a");
		p.addRule("con", p.tree("CNSTP8"),  "%a");
		p.addRule("stmt", p.tree("reg"),  "");
		p.addRule("acon", p.tree("ADDRGP4"),  "(%a)");
		p.addRule("acon", p.tree("con"),     "(%0)");
		p.addRule("base", p.tree("ADDRGP4"),          "(%a)");
		p.addRule("base", p.tree("reg"),              "[%0]");
		p.addRule("base", p.tree("ADDI4", p.tree("reg"),p.tree("acon")), "%1[%0]");
		p.addRule("base", p.tree("ADDP4", p.tree("reg"),p.tree("acon")), "%1[%0]");
		p.addRule("base", p.tree("ADDU4", p.tree("reg"),p.tree("acon")), "%1[%0]");
		p.addRule("base", p.tree("ADDRFP4"),  "(%a)[ebp]");
		p.addRule("base", p.tree("ADDRLP4"),  "(%a)[ebp]");
		p.addRule("index", p.tree("reg"), "%0");
		p.addRule("index", p.tree("LSHI4", p.tree("reg"),p.tree("con1")), "%0*2");
		p.addRule("index", p.tree("LSHI4", p.tree("reg"),p.tree("con2")), "%0*4");
		p.addRule("index", p.tree("LSHI4", p.tree("reg"),p.tree("con3")), "%0*8");

		p.addRule("con1", p.tree("CNSTI4"),  "1");
		p.addRule("con1", p.tree("CNSTU4"),  "1");
		p.addRule("con2", p.tree("CNSTI4"),  "2");
		p.addRule("con2", p.tree("CNSTU4"),  "2");
		p.addRule("con3", p.tree("CNSTI4"),  "3");
		p.addRule("con3", p.tree("CNSTU4"),  "3");
		p.addRule("index", p.tree("LSHU4", p.tree("reg"),p.tree("con1")), "%0*2");
		p.addRule("index", p.tree("LSHU4", p.tree("reg"),p.tree("con2")), "%0*4");
		p.addRule("index", p.tree("LSHU4", p.tree("reg"),p.tree("con3")), "%0*8");
		p.addRule("addr", p.tree("base"),              "%0");
		p.addRule("addr", p.tree("ADDI4", p.tree("index"),p.tree("base")), "%1[%0]");
		p.addRule("addr", p.tree("ADDP4", p.tree("index"),p.tree("base")), "%1[%0]");
		p.addRule("addr", p.tree("ADDU4", p.tree("index"),p.tree("base")), "%1[%0]");
		p.addRule("addr", p.tree("index"),  "[%0]");
		p.addRule("mem", p.tree("INDIRI1", p.tree("addr")), "byte ptr %0");
		p.addRule("mem", p.tree("INDIRI2", p.tree("addr")), "word ptr %0");
		p.addRule("mem", p.tree("INDIRI4", p.tree("addr")), "dword ptr %0");
		p.addRule("mem", p.tree("INDIRU1", p.tree("addr")), "byte ptr %0");
		p.addRule("mem", p.tree("INDIRU2", p.tree("addr")), "word ptr %0");
		p.addRule("mem", p.tree("INDIRU4", p.tree("addr")), "dword ptr %0");
		p.addRule("mem", p.tree("INDIRP4", p.tree("addr")), "dword ptr %0");
		p.addRule("rc", p.tree("reg"), "%0");
		p.addRule("rc", p.tree("con"), "%0");

		p.addRule("mr", p.tree("reg"),  "%0");
		p.addRule("mr", p.tree("mem"),  "%0");

		p.addRule("mrc0", p.tree("mem"),  "%0");
		p.addRule("mrc0", p.tree("rc"),   "%0");
		p.addRule("mrc1", p.tree("mem"),  "%0", 1);
		p.addRule("mrc1", p.tree("rc"),   "%0");

		p.addRule("mrc3", p.tree("mem"),  "%0", 3);
		p.addRule("mrc3", p.tree("rc"),   "%0");
		p.addRule("reg", p.tree("addr"),         "lea %c,%0\n", 1);
		p.addRule("reg", p.tree("mrc0"),         "mov %c,%0\n", 1);
		p.addRule("reg", p.tree("LOADI1", p.tree("reg")), "# move\n", 1);
		p.addRule("reg", p.tree("LOADI2", p.tree("reg")), "# move\n", 1);
		p.addRule("reg", p.tree("LOADI4", p.tree("reg")), "# move\n");
		p.addRule("reg", p.tree("LOADU1", p.tree("reg")), "# move\n", 1);
		p.addRule("reg", p.tree("LOADU2", p.tree("reg")), "# move\n", 1);
		p.addRule("reg", p.tree("LOADU4", p.tree("reg")), "# move\n");
		p.addRule("reg", p.tree("LOADP4", p.tree("reg")), "# move\n");
		p.addRule("reg", p.tree("ADDI4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nadd %c,%1\n", 1);
		p.addRule("reg", p.tree("ADDP4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nadd %c,%1\n", 1);
		p.addRule("reg", p.tree("ADDU4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nadd %c,%1\n", 1);
		p.addRule("reg", p.tree("SUBI4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nsub %c,%1\n", 1);
		p.addRule("reg", p.tree("SUBP4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nsub %c,%1\n", 1);
		p.addRule("reg", p.tree("SUBU4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nsub %c,%1\n", 1);
		p.addRule("reg", p.tree("BANDI4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nand %c,%1\n", 1);
		p.addRule("reg", p.tree("BORI4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nor %c,%1\n", 1);
		p.addRule("reg", p.tree("BXORI4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nxor %c,%1\n", 1);
		p.addRule("reg", p.tree("BANDU4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nand %c,%1\n", 1);
		p.addRule("reg", p.tree("BORU4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nor %c,%1\n", 1);
		p.addRule("reg", p.tree("BXORU4", p.tree("reg"),p.tree("mrc1")), "?mov %c,%0\nxor %c,%1\n", 1);
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("ADDI4", p.tree("mem"),p.tree("con1"))), "inc %1\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("ADDU4", p.tree("mem"),p.tree("con1"))), "inc %1\n");
		p.addRule("stmt", p.tree("ASGNP4", p.tree("addr"),p.tree("ADDP4", p.tree("mem"),p.tree("con1"))), "inc %1\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("SUBI4", p.tree("mem"),p.tree("con1"))), "dec %1\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("SUBU4", p.tree("mem"),p.tree("con1"))), "dec %1\n");
		p.addRule("stmt", p.tree("ASGNP4", p.tree("addr"),p.tree("SUBP4", p.tree("mem"),p.tree("con1"))), "dec %1\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("ADDI4", p.tree("mem"),p.tree("rc"))), "add %1,%2\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("SUBI4", p.tree("mem"),p.tree("rc"))), "sub %1,%2\n");
		p.addRule("stmt", p.tree("ASGNU4", p.tree("addr"),p.tree("ADDU4", p.tree("mem"),p.tree("rc"))), "add %1,%2\n");
		p.addRule("stmt", p.tree("ASGNU4", p.tree("addr"),p.tree("SUBU4", p.tree("mem"),p.tree("rc"))), "sub %1,%2\n");

		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("BANDI4", p.tree("mem"),p.tree("rc"))), "and %1,%2\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("BORI4", p.tree("mem"),p.tree("rc"))), "or %1,%2\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("BXORI4", p.tree("mem"),p.tree("rc"))), "xor %1,%2\n");
		p.addRule("stmt", p.tree("ASGNU4", p.tree("addr"),p.tree("BANDU4", p.tree("mem"),p.tree("rc"))), "and %1,%2\n");
		p.addRule("stmt", p.tree("ASGNU4", p.tree("addr"),p.tree("BORU4", p.tree("mem"),p.tree("rc"))), "or %1,%2\n");
		p.addRule("stmt", p.tree("ASGNU4", p.tree("addr"),p.tree("BXORU4", p.tree("mem"),p.tree("rc"))), "xor %1,%2\n");
		p.addRule("reg", p.tree("BCOMI4", p.tree("reg")), "?mov %c,%0\nnot %c\n", 2);
		p.addRule("reg", p.tree("BCOMU4", p.tree("reg")), "?mov %c,%0\nnot %c\n", 2);
		p.addRule("reg", p.tree("NEGI4", p.tree("reg")), "?mov %c,%0\nneg %c\n", 2);

		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("BCOMI4", p.tree("mem"))), "not %1\n");
		p.addRule("stmt", p.tree("ASGNU4", p.tree("addr"),p.tree("BCOMU4", p.tree("mem"))), "not %1\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("NEGI4", p.tree("mem"))), "neg %1\n");
		p.addRule("reg", p.tree("LSHI4", p.tree("reg"),p.tree("con5")), "?mov %c,%0\nsal %c,%1\n", 2);
		p.addRule("reg", p.tree("LSHU4", p.tree("reg"),p.tree("con5")), "?mov %c,%0\nshl %c,%1\n", 2);
		p.addRule("reg", p.tree("RSHI4", p.tree("reg"),p.tree("con5")), "?mov %c,%0\nsar %c,%1\n", 2);
		p.addRule("reg", p.tree("RSHU4", p.tree("reg"),p.tree("con5")), "?mov %c,%0\nshr %c,%1\n", 2);

		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("LSHI4", p.tree("mem"),p.tree("con5"))), "sal %1,%2\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("LSHU4", p.tree("mem"),p.tree("con5"))), "shl %1,%2\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("RSHI4", p.tree("mem"),p.tree("con5"))), "sar %1,%2\n");
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("RSHU4", p.tree("mem"),p.tree("con5"))), "shr %1,%2\n");

		p.addRule("con5", p.tree("CNSTI4"),  "%a");

		p.addRule("reg", p.tree("LSHI4", p.tree("reg"),p.tree("reg")), "?mov %c,%0\nmov ecx,%1\nsal %c,cl\n", 3);
		p.addRule("reg", p.tree("LSHU4", p.tree("reg"),p.tree("reg")), "?mov %c,%0\nmov ecx,%1\nshl %c,cl\n", 2);
		p.addRule("reg", p.tree("RSHI4", p.tree("reg"),p.tree("reg")), "?mov %c,%0\nmov ecx,%1\nsar %c,cl\n", 2);
		p.addRule("reg", p.tree("RSHU4", p.tree("reg"),p.tree("reg")), "?mov %c,%0\nmov ecx,%1\nshr %c,cl\n", 2);
		p.addRule("reg", p.tree("MULI4", p.tree("reg"),p.tree("mrc3")), "?mov %c,%0\nimul %c,%1\n", 14);
		p.addRule("reg", p.tree("MULI4", p.tree("con"),p.tree("mr")), "imul %c,%1,%0\n", 13);
		p.addRule("reg", p.tree("MULU4", p.tree("reg"),p.tree("mr")), "mul %1\n", 13);
		p.addRule("reg", p.tree("DIVU4", p.tree("reg"),p.tree("reg")), "xor edx,edx\ndiv %1\n");
		p.addRule("reg", p.tree("MODU4", p.tree("reg"),p.tree("reg")), "xor edx,edx\ndiv %1\n");
		p.addRule("reg", p.tree("DIVI4", p.tree("reg"),p.tree("reg")), "cdq\nidiv %1\n");
		p.addRule("reg", p.tree("MODI4", p.tree("reg"),p.tree("reg")), "cdq\nidiv %1\n");
		p.addRule("reg", p.tree("CVPU4", p.tree("reg")), "mov %c,%0\n");
		p.addRule("reg", p.tree("CVUP4", p.tree("reg")), "mov %c,%0\n");
		p.addRule("reg", p.tree("CVII4", p.tree("INDIRI1", p.tree("addr"))), "movsx %c,byte ptr %0\n", 3);
		p.addRule("reg", p.tree("CVII4", p.tree("INDIRI2", p.tree("addr"))), "movsx %c,word ptr %0\n", 3);
		p.addRule("reg", p.tree("CVUU4", p.tree("INDIRU1", p.tree("addr"))), "movzx %c,byte ptr %0\n", 3);
		p.addRule("reg", p.tree("CVUU4", p.tree("INDIRU2", p.tree("addr"))), "movzx %c,word ptr %0\n", 3);
		p.addRule("reg", p.tree("CVII4", p.tree("reg")), "# extend\n", 3);
		p.addRule("reg", p.tree("CVIU4", p.tree("reg")), "# extend\n", 3);
		p.addRule("reg", p.tree("CVUI4", p.tree("reg")), "# extend\n", 3);
		p.addRule("reg", p.tree("CVUU4", p.tree("reg")), "# extend\n", 3);

		p.addRule("reg", p.tree("CVII1", p.tree("reg")), "# truncate\n", 1);
		p.addRule("reg", p.tree("CVII2", p.tree("reg")), "# truncate\n", 1);
		p.addRule("reg", p.tree("CVUU1", p.tree("reg")), "# truncate\n", 1);
		p.addRule("reg", p.tree("CVUU2", p.tree("reg")), "# truncate\n", 1);
		p.addRule("stmt", p.tree("ASGNI1", p.tree("addr"),p.tree("rc")), "mov byte ptr %0,%1\n", 1);
		p.addRule("stmt", p.tree("ASGNI2", p.tree("addr"),p.tree("rc")), "mov word ptr %0,%1\n", 1);
		p.addRule("stmt", p.tree("ASGNI4", p.tree("addr"),p.tree("rc")), "mov dword ptr %0,%1\n", 1);
		p.addRule("stmt", p.tree("ASGNU1", p.tree("addr"),p.tree("rc")), "mov byte ptr %0,%1\n", 1);
		p.addRule("stmt", p.tree("ASGNU2", p.tree("addr"),p.tree("rc")), "mov word ptr %0,%1\n", 1);
		p.addRule("stmt", p.tree("ASGNU4", p.tree("addr"),p.tree("rc")), "mov dword ptr %0,%1\n", 1);
		p.addRule("stmt", p.tree("ASGNP4", p.tree("addr"),p.tree("rc")), "mov dword ptr %0,%1\n", 1);
		p.addRule("stmt", p.tree("ARGI4", p.tree("mrc3")), "push %0\n", 1);
		p.addRule("stmt", p.tree("ARGU4", p.tree("mrc3")), "push %0\n", 1);
		p.addRule("stmt", p.tree("ARGP4", p.tree("mrc3")), "push %0\n", 1);
		p.addRule("stmt", p.tree("ASGNB", p.tree("reg"), p.tree("INDIRB", p.tree("reg"))), "mov ecx,%a\nrep movsb\n");
		p.addRule("stmt", p.tree("ARGB", p.tree("INDIRB", p.tree("reg"))), "# ARGB\n");
		p.addRule("memf", p.tree("INDIRF8", p.tree("addr")), "qword ptr %0");
		p.addRule("memf", p.tree("INDIRF4", p.tree("addr")), "dword ptr %0");
		p.addRule("memf", p.tree("CVFF8", p.tree("INDIRF4", p.tree("addr"))), "dword ptr %0");
		p.addRule("reg", p.tree("memf"),  "fld %0\n", 3);
		p.addRule("stmt", p.tree("ASGNF8", p.tree("addr"),p.tree("reg")), "fstp qword ptr %0\n", 7);
		p.addRule("stmt", p.tree("ASGNF4", p.tree("addr"),p.tree("reg")), "fstp dword ptr %0\n", 7);
		p.addRule("stmt", p.tree("ASGNF4", p.tree("addr"),p.tree("CVFF4", p.tree("reg"))), "fstp dword ptr %0\n", 7);
		p.addRule("stmt", p.tree("ARGF8", p.tree("reg")), "sub esp,8\nfstp qword ptr [esp]\n");
		p.addRule("stmt", p.tree("ARGF4", p.tree("reg")), "sub esp,4\nfstp dword ptr [esp]\n");
		p.addRule("reg", p.tree("NEGF8", p.tree("reg")), "fchs\n");
		p.addRule("reg", p.tree("NEGF4", p.tree("reg")), "fchs\n");
		p.addRule("flt", p.tree("memf"),  " %0");
		p.addRule("flt", p.tree("reg"),   "p st(1,st");
		p.addRule("reg", p.tree("ADDF8", p.tree("reg"),p.tree("flt")), "fadd%1\n");
		p.addRule("reg", p.tree("ADDF4", p.tree("reg"),p.tree("flt")), "fadd%1\n");
		p.addRule("reg", p.tree("DIVF8", p.tree("reg"),p.tree("flt")), "fdiv%1\n");
		p.addRule("reg", p.tree("DIVF4", p.tree("reg"),p.tree("flt")), "fdiv%1\n");
		p.addRule("reg", p.tree("MULF8", p.tree("reg"),p.tree("flt")), "fmul%1\n");
		p.addRule("reg", p.tree("MULF4", p.tree("reg"),p.tree("flt")), "fmul%1\n");
		p.addRule("reg", p.tree("SUBF8", p.tree("reg"),p.tree("flt")), "fsub%1\n");
		p.addRule("reg", p.tree("SUBF4", p.tree("reg"),p.tree("flt")), "fsub%1\n");
		p.addRule("reg", p.tree("CVFF8", p.tree("reg")), "# CVFF8\n");
		p.addRule("reg", p.tree("CVFF4", p.tree("reg")), "sub esp,4\nfstp dword ptr 0[esp]\nfld dword ptr 0[esp]\nadd esp,4\n", 12);

		p.addRule("reg", p.tree("CVFI4", p.tree("reg")), "call __ftol\n", 31);
		p.addRule("reg", p.tree("CVIF8", p.tree("INDIRI4", p.tree("addr"))), "fild dword ptr %0\n", 10);
		p.addRule("reg", p.tree("CVIF4", p.tree("reg")), "push %0\nfild dword ptr 0[esp]\nadd esp,4\n", 12);

		p.addRule("reg", p.tree("CVIF8", p.tree("reg")), "push %0\nfild dword ptr 0[esp]\nadd esp,4\n", 12);

		p.addRule("addrj", p.tree("ADDRGP4"),  "%a");
		p.addRule("addrj", p.tree("reg"),      "%0", 2);
		p.addRule("addrj", p.tree("mem"),      "%0", 2);

		p.addRule("stmt", p.tree("JUMPV", p.tree("addrj")), "jmp %0\n", 3);
		p.addRule("stmt", p.tree("LABELV"), "%a:\n");
		p.addRule("stmt", p.tree("EQI4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\nje %a\n", 5);
		p.addRule("stmt", p.tree("GEI4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\njge %a\n", 5);
		p.addRule("stmt", p.tree("GTI4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\njg %a\n", 5);
		p.addRule("stmt", p.tree("LEI4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\njle %a\n", 5);
		p.addRule("stmt", p.tree("LTI4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\njl %a\n", 5);
		p.addRule("stmt", p.tree("NEI4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\njne %a\n", 5);
		p.addRule("stmt", p.tree("GEU4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\njae %a\n", 5);
		p.addRule("stmt", p.tree("GTU4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\nja  %a\n", 5);
		p.addRule("stmt", p.tree("LEU4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\njbe %a\n", 5);
		p.addRule("stmt", p.tree("LTU4", p.tree("mem"),p.tree("rc")), "cmp %0,%1\njb  %a\n", 5);
		p.addRule("stmt", p.tree("EQI4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\nje %a\n", 4);
		p.addRule("stmt", p.tree("GEI4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njge %a\n", 4);
		p.addRule("stmt", p.tree("GTI4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njg %a\n", 4);
		p.addRule("stmt", p.tree("LEI4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njle %a\n", 4);
		p.addRule("stmt", p.tree("LTI4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njl %a\n", 4);
		p.addRule("stmt", p.tree("NEI4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njne %a\n", 4);

		p.addRule("stmt", p.tree("EQU4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\nje %a\n", 4);
		p.addRule("stmt", p.tree("GEU4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njae %a\n", 4);
		p.addRule("stmt", p.tree("GTU4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\nja %a\n", 4);
		p.addRule("stmt", p.tree("LEU4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njbe %a\n", 4);
		p.addRule("stmt", p.tree("LTU4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njb %a\n", 4);
		p.addRule("stmt", p.tree("NEU4", p.tree("reg"),p.tree("mrc1")), "cmp %0,%1\njne %a\n", 4);
		p.addRule("cmpf", p.tree("memf"), "%0");
		p.addRule("cmpf", p.tree("reg"),   "p");
		p.addRule("stmt", p.tree("EQF8", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %b\nje %a\n%b:\n");
		p.addRule("stmt", p.tree("GEF8", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\njbe %a\n");
		p.addRule("stmt", p.tree("GTF8", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\njb %a\n");
		p.addRule("stmt", p.tree("LEF8", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\njae %a\n");
		p.addRule("stmt", p.tree("LTF8", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\nja %a\n");
		p.addRule("stmt", p.tree("NEF8", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\njne %a\n");

		p.addRule("stmt", p.tree("EQF4", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %b\nje %a\n%b:\n");
		p.addRule("stmt", p.tree("GEF4", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\njbe %a\n\n");
		p.addRule("stmt", p.tree("GTF4", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\njb %a\n");
		p.addRule("stmt", p.tree("LEF4", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\njae %a\n\n");
		p.addRule("stmt", p.tree("LTF4", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\nja %a\n");
		p.addRule("stmt", p.tree("NEF4", p.tree("cmpf"),p.tree("reg")), "fcomp%0\nfstsw ax\nsahf\njp %a\njne %a\n");
		p.addRule("reg", p.tree("CALLI4", p.tree("addrj")), "call %0\nadd esp,%a\n");
		p.addRule("reg", p.tree("CALLU4", p.tree("addrj")), "call %0\nadd esp,%a\n");
		p.addRule("reg", p.tree("CALLP4", p.tree("addrj")), "call %0\nadd esp,%a\n");
		p.addRule("stmt", p.tree("CALLV", p.tree("addrj")), "call %0\nadd esp,%a\n");
		p.addRule("reg", p.tree("CALLF4", p.tree("addrj")), "call %0\nadd esp,%a\n");
		p.addRule("reg", p.tree("CALLF8", p.tree("addrj")), "call %0\nadd esp,%a\n");
		p.addRule("stmt", p.tree("CALLF4", p.tree("addrj")), "call %0\nadd esp,%a\nfstp\n");
		p.addRule("stmt", p.tree("CALLF8", p.tree("addrj")), "call %0\nadd esp,%a\nfstp\n");

		p.addRule("stmt", p.tree("RETI4", p.tree("reg")), "# ret\n");
		p.addRule("stmt", p.tree("RETU4", p.tree("reg")), "# ret\n");
		p.addRule("stmt", p.tree("RETP4", p.tree("reg")), "# ret\n");
		p.addRule("stmt", p.tree("RETF4", p.tree("reg")), "# ret\n");
		p.addRule("stmt", p.tree("RETF8", p.tree("reg")), "# ret\n");

		p.generateMatcher("src", "com.madhu.minc.jburg", "X86Machine");
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
}
