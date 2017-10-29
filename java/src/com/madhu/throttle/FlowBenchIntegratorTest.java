
package com.madhu.throttle;

import static org.junit.Assert.*;
import static com.madhu.throttle.FlowBenchIntegrator.*;

import org.junit.Test;

public class FlowBenchIntegratorTest {
	private static final double[][] LOW_RPM_DATA = {
		{ 00.0, 0.00985, 55133.6998, 128.5 },
		{ 05.0, 0.01076, 59635.6733, 125.5 },
		{ 10.0, 0.01293, 70569.6996, 117.9 },
		{ 15.0, 0.01407, 76759.6049, 113.4 },
		{ 20.0, 0.01487, 81730.8055, 109.6 },
		{ 25.0, 0.01543, 86102.9700, 106.0 },
		{ 30.0, 0.01572, 89685.7037, 103.0 },
		{ 35.0, 0.01583, 92475.2829, 100.3 },
		{ 40.0, 0.01584, 94612.1529,  98.1 },
		{ 45.0, 0.01584, 96212.8387,  96.5 },
		{ 50.0, 0.01584, 97396.9155,  95.0 },
		{ 55.0, 0.01584, 98242.6312,  94.0 },
		{ 60.0, 0.01584, 98874.6984,  93.2 },
		{ 65.0, 0.01584, 99330.8410,  92.7 },
		{ 70.0, 0.01584, 99678.6673,  92.2 },
		{ 75.0, 0.01585, 99953.0005,  91.8 },
	};
	private static final double[][] LOW_RPM_PRESSURE = {
		{ 0.000000, 101325.0000 },
		{ 20.000000, 95136.5889 },
		{ 40.000000, 83076.4485 },
		{ 60.000000, 72395.9135 },
		{ 80.000000, 64904.4526 },
		{ 100.000000, 60403.4930 },
		{ 120.000000, 58471.2159 },
		{ 140.000000, 58879.8020 },
		{ 160.000000, 61666.9550 },
		{ 180.000000, 67160.6581 },		
	};

	@Test
	public void test1500RPMThrottle() {
		double rpm = 1500;
		double po = MSL_PRESSURE_PA;
		FlowBenchIntegrator fb = new FlowBenchIntegrator(1.575, 0.19, 8.43, 9.5, 0.079, 0.0712, po, 300);
		fb.setRPM(rpm);
		int i = 0;
		for (double angle = 0; angle <= 75; angle += 5) {
			fb.setThrottle(angle);
			fb.computeIntakeStroke(po);
			double[] values = LOW_RPM_DATA[i++];
			assertTrue(isCloseTo(angle, values[0]));
			assertTrue(isCloseTo(fb.getAirAmount(), values[1]));
			assertTrue(isCloseTo(fb.getMinPressure(), values[2]));
			assertTrue(isCloseTo(fb.getMinPressureAngle(), values[3]));
		}
	}

	@Test
	public void test1500RPMPressure() {
		double rpm = 1500;
		double po = MSL_PRESSURE_PA;
		FlowBenchIntegrator fb = new FlowBenchIntegrator(1.575, 0.19, 8.43, 9.5, 0.079, 0.0712, po, 300);
		fb.setRPM(rpm);
		int ti = 0;
		fb.setThrottle(4);
		fb.computeIntakeStroke(po);
		double[] map = fb.getManifoldPressure();
		for (double angle = 0; angle <= 180; angle += 20) {
			int i = (int) ((map.length - 1) * angle / 180);
			double[] values = LOW_RPM_PRESSURE[ti++];
			assertTrue(isCloseTo(angle, values[0]));
			assertTrue(isCloseTo(map[i], values[1]));
		}
	}

	private boolean isCloseTo(double d1, double d2) {
		boolean close;
		double diff;
		if (d1 == 0.0 || d2 == 0.0) {
			diff = Math.abs(d1 - d2);
			close = diff < 0.001;
		} else {
			diff = Math.abs(d1/d2 - 1.0);
			close = diff < 0.001;
		}
		if (!close) {
			System.out.printf("%f, %f, %f", d1, d2, diff);
		}
		return close;
	}
}
