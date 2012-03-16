package org.sdmlib.serialization.event;

import org.sdmlib.serialization.interfaces.PeerMessage;

public class BasicMessage implements PeerMessage {
	public static final String PROPERTY_VALUE = "value";
	private String value;

	public BasicMessage() {

	}

	public BasicMessage(String message) {
		setValue(message);
	}

	public Object get(String attrName) {
		String attribute;
		int pos = attrName.indexOf(".");
		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		} else {
			attribute = attrName;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_VALUE)) {
			return getValue();
		}
		return null;
	}

	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_VALUE)) {
			setValue((String) value);
			return true;
		}
		return false;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
