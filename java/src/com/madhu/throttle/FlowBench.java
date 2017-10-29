
package com.madhu.throttle;

import static java.lang.Math.*;

public class FlowBench {
	/**
	 * Throttle bore diameter in inches
	 */
	private double throttleBore;
	/**
	 * Throttle shaft diameter in inches
	 */
	private double throttleShaft;
	/**
	 * Close throttle angle in degrees
	 */
	private double closedAngle;
	private double wmolet;
	/**
	 * Throttle flow area
	 */
	private double aflow;
	/**
	 * Ambient pressure in PSIA
	 */
	private double PO;
	/**
	 * Total temperature of the mixture at the throttle plate (OR) Rankine
	 */
	private double TOTR;
	private double cdt;
	/**
	 * Displacement in cubic inches
	 */
	private double displacement;
	/**
	 * Ambient pressure in inches of mercury
	 */
	private double pohg;
	private double c8, c9;
	/**
	 * Molecular weight of mixture in engine cylinder
	 */
	private double wmolem;
	/**
	 * Ratio of specific heats
	 */
	private double GK;
	private double vacman;
	private double[][] CDTFig3 = {
		{ 0, 0.6 },
		{ 2.47, 0.61 },
		{ 4.95, 0.64 },
		{ 7.42, 0.72 },
		{ 9.89, 0.79 },
		{ 12.36, 0.82 },
		{ 14.84, 0.84 },
		{ 17.31, 0.85 },
		{ 19.78, 0.86 },
		{ 22.25, 0.87 },
		{ 29.92, 0.89 },
		{ 39.81, 0.91 },
		{ 49.7, 0.93 },
		{ 59.84, 0.94 },
		{ 69.73, 0.94 },
		{ 79.74, 0.95 },
		{ 89.88, 0.95 },
	};
	// Values from page 292, not exactly the same as fig. 3 on page 24
	// Maybe this accounts for EQN. 2.6?
	private double[][] CDT66Fairlane = {
		{ 0, 0.683 },
		{ 2, 0.721 },
		{ 4, 0.757 },
		{ 6, 0.790 },
		{ 8, 0.817 },
		{ 10, 0.838 },
		{ 12, 0.854 },
		{ 14, 0.859 },
		{ 16, 0.865 },
		{ 18, 0.871 },
		{ 20, 0.876 },
		{ 25, 0.890 },
		{ 30, 0.900 },
		{ 35, 0.911 },
		{ 40, 0.920 },
		{ 45, 0.927 },
		{ 50, 0.932 },
		{ 60, 0.940 },
		{ 70, 0.943 }
	};

	/**
	 * 
	 * @param displacement - engine displacement in cubic inches
	 * @param compressionRatio - engine compression ratio (dimensionless)
	 * @param throttleBore - throttle bore diameter in inches
	 * @param throttleShaft - throttle shaft diameter in inches
	 * @param closedAngle - closed throttle angle in degrees
	 * @param tman - Absolute temperature of mixture leaving the intake manifold in Rankine
	 */
	public FlowBench(double displacement, double compressionRatio, double throttleBore, double throttleShaft, double closedAngle, double tman) {
		this.displacement = displacement;
		this.throttleBore = throttleBore;
		this.throttleShaft = throttleShaft;
		// EQN. 2.6 page 23
		this.closedAngle = closedAngle - 3.8 - 0.1*closedAngle;
		// FIXME I think this the dry air value, need to include humidity
		wmolet = 28.950;
		// FIXME I think this the dry air value, need to include humidity
		wmolem = 28.950;
		// FIXME Make these configurable
		PO = 14.7;
		pohg = 29.92;
		TOTR = 75 + 459.6;
		double cpair = 0.2410;
		double cvair = 0.1710;
		GK = cpair / cvair;
		// Assuming 0 F/A ratio
		double tclr = 1350;
		double tcylr = tman+tclr/9.00;
        double c5 = tcylr/ tman;
        double c6 = tclr/tman;
        double c7 = c5/c6;
        c8 = 0.400*c5*1545.4*tman*(compressionRatio-1.0)/(wmolem*compressionRatio*0.4912);
        c9 = (compressionRatio-c7)/compressionRatio;
        reset();
	}

	public void reset() {
        // guess a value
        vacman = 12.0;
	}

	/**
	 * 
	 * @param angle - throttle angle in degrees
	 */
	private void setThrottle(double angle) {
		if (angle < closedAngle || angle > 90) {
			throw new IllegalArgumentException("Throttle angle is out of range: " + angle);
		}
		double da = angle - closedAngle;
		double[][] cdtTable = CDTFig3;
		if (da > cdtTable[cdtTable.length-1][0]) {
			cdt = cdtTable[cdtTable.length-1][1];
		} else {
			double[] cdPair0 = null;
			double[] cdPair1 = null;
			for (int i=0; i<cdtTable.length-1; i+=1) {
				cdPair0 = cdtTable[i+0];
				cdPair1 = cdtTable[i+1];
				if (da >= cdPair0[0] && da <= cdPair1[0]) {
					break;
				}
			}
			// linear interpolation
			double f = (da - cdPair0[0])/(cdPair1[0] - cdPair0[0]);
			cdt = cdPair0[1] + f * (cdPair1[1] - cdPair0[1]);
		}
		// now compute aflow
		double th = angle * PI / 180.0;
		double th0 = closedAngle * PI / 180.0;
		double D = throttleBore;
		double d = throttleShaft;
		double costh = cos(th);
		double costh0 = cos(th0);
		aflow = PI*D*D*(1 - (costh/costh0))/4.0 + d*sqrt(D*D*costh*costh - d*d*costh0*costh0)/(2*costh) +
			D*D*costh*asin(d*costh0/(D*costh))/(2*costh0) - d*sqrt(D*D - d*d)/2.0 + D*D*asin(d/D)/2.0;
	}

	private double getExhausterFlowRate(double rpm) {
		return displacement*rpm*(pohg*c9-vacman)/c8;
	}

	/**
	 * 
	 * @param gasm2 - exhauster flow rate
	 * @return
	 */
	private double getNozzleFlowRate(double gasm2) {
		double d1 = gasm2/1000.0;
		double PT=PO - 1.200*0.4912*d1*d1;
		double c1 = 2.0*3600.0*Math.sqrt(GK*wmolet*32.174/1545.4)*aflow*PT*Math.sqrt(2.0/(GK-1.0))/Math.sqrt(TOTR);
		double c2 = (GK-1.0)/GK;
		double c3 = (GK+1.0)/(2.0*GK);
		double pr=PT/(PO-vacman*0.4912);
		return c1*Math.sqrt(Math.pow(pr, c2) - 1.0)*cdt/Math.pow(pr, c3);
	}
	
	public void compute(double angle, double rpm) {
		reset();
		setThrottle(angle);
		double gasm2 = getExhausterFlowRate(rpm);
		double gasm1 = getNozzleFlowRate(gasm2);
		double err1 = Math.abs(gasm1 - gasm2);
		double dv = 0.1;
		for (int i=0; i<30; i+=1) {
			vacman += dv;
			gasm2 = getExhausterFlowRate(rpm);
			gasm1 = getNozzleFlowRate(gasm2);
			double err2 = Math.abs(gasm1 - gasm2);
			System.out.printf("%10.3f\t%10.3f\t%10.3f\t%10.3f\t%10.3f%n", vacman, gasm1, gasm2, err1, err2);
			if (err1 > err2) {
				dv /= -2;
			}
			err1 = err2;
		}
	}
	
	public static void main(String[] args) {
		// 1990 DR350S with Yamaha YZF-R6 throttle body
		FlowBench fb = new FlowBench(21.3, 9.5, 1.575, 0.19, 8.43, 600);
//		FlowBench fb = new FlowBench(289, 8.5, 1.575, 0.19, 8.43, 600);
		fb.compute(8.43+4, 1500);
//		fb.setThrottle(8.43+10);
//		System.out.printf("%4.2f\t%6.3f\t%6.3f\t%6.3f%n", 10.0, fb.cdt, fb.aflow, fb.cdt*fb.aflow);
		// 1966 Ford Fairlane
//		for (int i=0; i<20; i+=1) {
//			double a = 3*i + 8.43;
//			fb.setThrottle(a);
//			System.out.printf("%4.2f\t%6.3f\t%6.3f\t%6.3f%n", a, fb.cdt, fb.aflow, fb.cdt*fb.aflow);
//		}
	}
}
