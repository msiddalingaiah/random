
package com.madhu.ws;

public class ServiceDescriptor {
	private String nameSpace;
	private Class implementation;
	
	public ServiceDescriptor(String className) throws ClassNotFoundException {
		implementation = Class.forName(className);
		StringBuffer sb = new StringBuffer(128);
		sb.append("http://client");
		Package pack = implementation.getPackage();
		if (pack == null) { // default package
			nameSpace = "http://client.madhu.com";
			return;
		}
		String[] p = pack.getName().split("\\.");
		for (int i = p.length-1; i >= 0; i--) {
			sb.append('.');
			sb.append(p[i]);
		}
		nameSpace = sb.toString();
	}

	public Class getImplementation() {
		return implementation;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public String getSimpleName() {
		return implementation.getSimpleName();
	}
	
	public static void main(String[] args) throws Exception {
		ServiceDescriptor sd = new ServiceDescriptor("com.madhu.ws.test.ServiceDescriptor");
		System.out.println(sd.getNameSpace());
		System.out.println(sd.getSimpleName());
	}
}
