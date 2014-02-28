package org.sdmlib.serialization.graph;


public class Attribute {
	private String key;
	private String clazz;
	private String value;
	
	public String getKey() {
		return key;
	}
	public Attribute withKey(String key) {
		this.key = key;
		return this;
	}
	public String getClazz() {
		return clazz;
	}
	public Attribute withClazz(String clazz) {
		this.clazz = clazz;
		return this;
	}
	public String getValue() {
		return value;
	}
	public Attribute withValue(String value) {
		this.value = value;
		return this;
	}
	public String getValue(String typ) {
		if(typ.equals(GraphIdMap.CLASS)){
			return clazz;
		}
		return value;
	}
}
