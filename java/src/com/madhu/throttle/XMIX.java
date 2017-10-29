
package com.madhu.throttle;

public class XMIX {
	private double WMOL, CPMIX, CVMIX, GKMIX;
	private double CPH2O, CVH2O, WH2O, WAIR, WFUEL, CPAIR, CVAIR;

	public XMIX(double cph2o, double cvh2o, double wh2o, double wair,
			double wfuel, double cpair, double cvair) {
		super();
		CPH2O = cph2o;
		CVH2O = cvh2o;
		WH2O = wh2o;
		WAIR = wair;
		WFUEL = wfuel;
		CPAIR = cpair;
		CVAIR = cvair;
	}

	public void compute(double TOF, double POHG, double HUMID, double FTYPE,
			double TFUEL, double FA) {
		//
		// THIS SUBROUTINE CALCULATES THE THERMODYNAMIC PROPERTIES OF AN
		// IDEAL GAS MIXTURE CONSISTING OF AIR,WATER VAPOR, AND FUEL VAPOR
		// THIS IS DONE FOR ANY SPECIFIED FUEL,FUFL-AIR RATIO,PRESSURE,
		// TEMPERATUPRE, AND HUMIDITY
		//
		//
		double PO = POHG * 0.4912;
		double TOR = TOF + 459.6;
		double TF = TOF / 100.0;
		double PMAXW = 1.302 * TF * TF * TF - 0.765 * TF * TF + 0.413 * TF;
		double OMEGA = 0.622 * HUMID * PMAXW / PO;
		//
		// GET AIR PROPERTIES
		//
		FPROP fp = new FPROP(8.0, TOF, TOF);
		//
		// GET WATER VAPOR PROPERTIES;
		fp = new FPROP(2.0, TOF, TOF);
		//
		// GET FUEL PROPERTIES;
		//
		fp = new FPROP(FTYPE, TFUEL, TFUEL);
		//
		double C4 = 1.0 + FA + OMEGA;
		double C5 = FA / WFUEL + 1.0 / WAIR + OMEGA / WH2O;
		WMOL = C4 / C5;
		CPMIX = (CPAIR + OMEGA * CPH2O + FA * fp.getCPFUEL()) / C4;
		CVMIX = (CVAIR + OMEGA * CVH2O + FA * fp.getCVFUEL()) / C4;
		GKMIX = CPMIX / CVMIX;
	}

	public double getWMOL() {
		return WMOL;
	}

	public double getCPMIX() {
		return CPMIX;
	}

	public double getCVMIX() {
		return CVMIX;
	}

	public double getGKMIX() {
		return GKMIX;
	}
}
