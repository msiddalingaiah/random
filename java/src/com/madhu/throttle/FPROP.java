
package com.madhu.throttle;

/**
 * 
 *       SUBROUTINE FPROP (FLUID, TEMP,TMAN,DENSM,VISKM,CORR,CLIQ,
     1     CPFUEL,CVFUEL,WMOLEC,HVAPOR,SIGMA,HEATV )
 * @author madhu
 *
 */
public class FPROP {
	private double DENSM, VISKM, CORR, CLIQ, HVAPOR, SIGMA, HEATV;
	private double CPFUEL, CVFUEL;
	private double WMOLEC;

	public FPROP(double FLUID, double TEMP, double TMAN) {
		//
		// SIGMA IS THE SURFACE TENSION OF THE FLUID IN CONTACT;
		// WITH AIR IN DYNES/CM;
		//
		// BEST FIT CURVES FOR SPECifIC GRAVITY AND VISCOSITY;
		//
		// ISO-OCTANE...... FLUID=0.0;
		// STANDARD REGULAR...FLUID=1.0;
		// WATER ...... FLUID=2.0;
		// ETHYL ALCOHOL.....FLUID=3.0;
		// SHELL PREMIUM..... FLUID=4.0;
		// CLARK 100 ..... FLUID=5.0;
		// MARATHON REG ..... FLUID=6.0;
		// MINERAL SPIRITS....FLUID=7.0;
		// AIR..............FLUID=8.0;
		//
		if (FLUID == 0.0) {
			DENSM = 0.7219 - 0.00043826 * TEMP;
			VISKM = -0.4378 + 313.3462 / (TEMP + 200.0);
			CORR = Math.sqrt((0.7219 - 0.00043826 * TMAN) / DENSM);
			CLIQ = 0.4800;
			CPFUEL = 0.4000;
			CVFUEL = 0.3810;
			WMOLEC = 114.00;
			HVAPOR = -141.0;
			SIGMA = 22.2000 - 0.050 * TEMP;
			HEATV = 19080.0;
		} else if (FLUID == 1.0) {
			DENSM = 24720.0 / (TEMP + 6270.0) - 3.172;
			VISKM = 135.79 / (TEMP + 132.5) - 0.0830;
			CORR = Math.sqrt((24720.0 / (TMAN + 6270.0) - 3.17) / DENSM);
			CLIQ = 0.5810;
			CPFUEL = 0.4000;
			CVFUEL = 0.3810;
			WMOLEC = 126.00;
			HVAPOR = -142.0;
			SIGMA = 24.1000 - 0.048 * TEMP;
			HEATV = 19000.0;
		} else if (FLUID == 2.0) {
			DENSM = 1.000 - 0.000133 * TEMP;

			VISKM = -0.1570 + 102.0 / (TEMP + 20.0);
			CORR = Math.sqrt((1.00 - 0.000133 * TMAN) / DENSM);
			if (TEMP - 32.0 < 0) {
				VISKM = 100000.0;
			}
			CLIQ = 1.0000;
			CPFUEL = 0.4600;
			CVFUEL = 0.3600;
			WMOLEC = 18.016;
			HVAPOR = -1125.0 + 1.5625 * TEMP;
			SIGMA = 72.7;
			HEATV = 00000.0;
		} else if (FLUID == 3.0) {
			DENSM = 0.830 - 0.000340 * TEMP;
			VISKM = -0.0890 + 346.0 / (TEMP + 190.0);
			CORR = Math.sqrt((0.830 - 0.000340 * TMAN) / DENSM);
			CLIQ = 0.4800;
			CPFUEL = 0.4600;
			CVFUEL = 0.4070;
			WMOLEC = 46.000;
			HVAPOR = -361.0;
			SIGMA = 26.86 - 0.046 * TEMP;
			HEATV = 11550.0;
		} else if (FLUID == 4.0) {
			DENSM = 0.7893 - 0.000472 * TEMP;
			VISKM = 0.7710 - 0.002440 * TEMP;
			CORR = Math.sqrt((0.7893 - 0.000472 * TMAN) / DENSM);
			CLIQ = 0.4800;
			CPFUEL = 0.4000;
			CVFUEL = 0.3810;
			WMOLEC = 126.00;
			HVAPOR = -142.0;
			SIGMA = 27.20 - 0.052 - TEMP;
			HEATV = 19000.0;
		} else if (FLUID == 5.0) {
			DENSM = 0.7480 - (0.00040) * TEMP;
			VISKM = 0.6740 - (0.001933) * TEMP;
			CORR = Math.sqrt((0.7480 - (0.000400) * TMAN) / DENSM);
			CLIQ = 0.4800;
			CPFUEL = 0.4000;
			CVFUEL = 0.3810;
			WMOLEC = 126.00;
			HVAPOR = -142.0;
			SIGMA = 25.30 - 0.052 * TEMP;
			HEATV = 19000.0;
		} else if (FLUID == 6.0) {
			DENSM = 2328.56812 / (TEMP * 1.8 + 32. - 2074.76544) + 2.03898;
			VISKM = 118.12441 / (TEMP * 1.8 + 32. + 77.16928) - .07321;
			CORR = 1.0;
			CLIQ = 0.5150;
			CPFUEL = 0.4000;
			CVFUEL = 0.3810;
			WMOLEC = 126.00;
			HVAPOR = -142.0;
			SIGMA = 24.80 - 0.049 * TEMP;
			HEATV = 19000.0;
		} else if (FLUID == 7.0) {
			DENSM = 0.6409 + 41.88 / (TEMP + 215.0);
			VISKM = -0.1250 + 201.0 / (TEMP + 65.30);
			CORR = Math.sqrt((0.6409 + 41.88 / (TMAN + 215.0)) / DENSM);
			CLIQ = 1.0;
			CPFUEL = 0.5;
			CVFUEL = 0.4;
			WMOLEC = 100.0;
			HVAPOR = -100.0;
			SIGMA = 38.60 - 0.078 * TEMP;
			HEATV = 19000.0;
		} else if (FLUID == 8.0) {
			DENSM = 0.6360 / (TEMP + 459.6);
			VISKM = 12.0774 + 4.6452 * TEMP / 100.0;
			CORR = 1.0;
			CLIQ = 0.000;
			CPFUEL = 0.2410;
			CVFUEL = 0.1710;
			WMOLEC = 28.950;
			HVAPOR = -000.0;
			SIGMA = 0.0000;
			HEATV = 00000.0;
		} else {
			throw new IllegalArgumentException("Invalid valid for FLUID: "
					+ FLUID);
		}
	}

	public double getDENSM() {
		return DENSM;
	}

	public double getVISKM() {
		return VISKM;
	}

	public double getCORR() {
		return CORR;
	}

	public double getCLIQ() {
		return CLIQ;
	}

	public double getHVAPOR() {
		return HVAPOR;
	}

	public double getSIGMA() {
		return SIGMA;
	}

	public double getHEATV() {
		return HEATV;
	}

	public double getCPFUEL() {
		return CPFUEL;
	}

	public double getCVFUEL() {
		return CVFUEL;
	}

	public double getWMOLEC() {
		return WMOLEC;
	}
}
