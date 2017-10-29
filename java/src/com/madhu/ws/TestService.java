
package com.madhu.ws;

public class TestService {
	@ParameterNames("a,b,d,e,f,g,h,i,j,l,m,n,o,p")
	public void doAllParams(
			boolean a, byte b, short d, int e, float f, double g, String h,
			boolean i[], byte[] j, short[] l, int[] m, float[] n, double[] o, String[] p) {
		return;
	}
	
	public boolean getBoolean() {
		return true;
	}
	
	public byte getByte() {
		return 0;
	}
	
	public short getShort() {
		return 0;
	}
	
	public int getInt() {
		return 0;
	}
	
	public float getFloat() {
		return 0;
	}
	
	public double getDouble() {
		return 0;
	}
	
	public String getString() {
		return "some string";
	}
	
	
	public boolean[] getBooleanA() {
		return new boolean[] { true, false };
	}
	
	public byte[] getByteA() {
		return new byte[] { 0, 1, 2 };
	}
	
	public short[] getShortA() {
		return new short[] { 0, 1, 2 };
	}
	
	public int[] getIntA() {
		return new int[] { 0, 1, 2 };
	}
	
	public float[] getFloatA() {
		return new float[] { 0, 1, 2 };
	}
	
	public double[] getDoubleA() {
		return new double[] { 0, 1, 2 };
	}
	
	public String[] getStringA() {
		return new String[] { "a", "b", "c" };
	}
	
	public String getNull() {
		return null;
	}
}
