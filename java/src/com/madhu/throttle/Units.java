
package com.madhu.throttle;

public class Units {
	public static final double MICRO = 1.0e-6;
	public static final double MILLI = 1.0e-3;
	public static final double CENTI = 1.0e-2;
	public static final double KILO = 1.0e3;
	public static final double MEGA = 1.0e6;
	
	public static final double METERS = 1.0;
	public static final double GRAMS = 1.0e-3;
	public static final double KILOGRAMS = KILO * GRAMS;
	public static final double SECONDS = 1.0;
	public static final double HERTZ = 1.0;
	public static final double RADIANS = 1.0;

	public static final double INCHES = 1/(2.54 * CENTI * METERS);
	public static final double POUNDS = 1/(2.20462262 * KILOGRAMS);
	public static final double DEGREES = 2 * Math.PI * RADIANS/360.0;
}
