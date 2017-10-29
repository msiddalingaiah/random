
package com.madhu.throttle;

public class Throttle {
	private double AFLOW;

	/**
	 * THIS SUBROUTINE CALCULATES THE FLOW AREA AT THE THROTTLE RESTRICTION FOR
	 * ANY VALUE OF THFTA,THETO,THROTTLE BORE DIAMETER AND THROTTLE SHAFT
	 * DIAMETER.
	 */
	public void compute(double THETA, double THETAO, double DT, double DS) {
		if (THETA < THETAO) {
			throw new IllegalArgumentException("THETA is less than THETAO");
		}
		if (90.0 - THETA < 0) {
			THETA = 180.0 - THETA;
		}
		double RTHETA = 3.1415926 * THETA / 180.0;
		double RTHETO = 3.1415926 * THETAO / 180.0;
		double DUM1 = Math.cos(RTHETA);
		double DUM2 = DUM1 * DUM1;
		double DUM3 = Math.cos(RTHETO);
		double DUM4 = DUM3 * DUM3;
		double DUM5 = DS * DUM3 / (DT * DUM1);
		double DUM6 = DUM1 / DUM3;
		double DUM7 = DS / DT;
		if (DT - DS < 0) {
			throw new IllegalArgumentException(
					"Shaft diameter is greater than throttle diameter");
		}
		double DUM8 = DUM7 * DUM3;
		double ANG1 = Math.asin(DUM7);
		double DUM9 = DT * DT - DS * DS;
		double ASHAFT = 0.5 * Math.sqrt(DUM9) * DS + 0.5 * DT * DT * ANG1;
		if (DUM1 - DUM8 < 0) {
			double ASTAR = 0.785398 * DT * DT * DUM6;
			AFLOW = 0.785398 * DT * DT - ASHAFT;
		} else {
			double ANG = Math.asin(DUM5);
			double ASTAR = DS * Math.sqrt(DT * DT * DUM2 - DS * DS * DUM4)
					* 0.50 / DUM1 + ANG * DT * DT * DUM1 * 0.50 / DUM3;
			AFLOW = 0.785398 * DT * DT * (1.0 - DUM6) + ASTAR - ASHAFT;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
