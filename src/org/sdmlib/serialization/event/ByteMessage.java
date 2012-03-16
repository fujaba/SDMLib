package org.sdmlib.serialization.event;

import org.sdmlib.serialization.interfaces.PeerMessage;

public class ByteMessage implements PeerMessage {
	public static final String PROPERTY_VALUE = "value";
	private byte[] value = new byte[] {};

	public ByteMessage() {
	}

	public ByteMessage(byte[] message) {
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
			return value;
		}
		return null;
	}

	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_VALUE)) {
			setValue((byte[]) value);
			return true;
		}
		return false;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}
}
