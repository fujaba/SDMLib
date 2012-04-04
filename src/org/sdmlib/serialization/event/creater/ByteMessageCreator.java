package org.sdmlib.serialization.event.creater;

import org.sdmlib.serialization.event.ByteMessage;
import org.sdmlib.serialization.interfaces.ByteEntityCreator;

public class ByteMessageCreator implements ByteEntityCreator {
	private final String[] properties = new String[] { ByteMessage.PROPERTY_VALUE };

	public String[] getProperties() {
		return properties;
	}

	public Object getSendableInstance(boolean reference) {
		return new ByteMessage();
	}

	public byte getEventTyp() {
		return 0x01;
	}

	public Object getValue(Object entity, String attribute) {
		return ((ByteMessage) entity).get(attribute);
	}

	public boolean setValue(Object entity, String attribute, Object value) {
		return ((ByteMessage) entity).set(attribute, value);
	}
}
