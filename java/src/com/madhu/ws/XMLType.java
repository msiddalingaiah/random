
package com.madhu.ws;

public class XMLType {
	private String name;
	private String type;

	public XMLType(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
	public String toString() {
		return type;
	}
	
	public boolean equals(Object other) {
		return type.equals(other);
	}
	
	public int hashCode() {
		return type.hashCode();
	}
}
