package org.sdmlib.serialization.event.creater;

import org.sdmlib.serialization.event.BasicMessage;
import org.sdmlib.serialization.interfaces.PrimaryEntityCreator;

public class BasicMessageCreator implements PrimaryEntityCreator {
	protected final String[] properties = new String[] { BasicMessage.PROPERTY_VALUE };

	public String[] getProperties() {
		return properties;
	}

	public Object getSendableInstance(boolean reference) {
		return new BasicMessage();
	}

	public byte getEventTyp() {
		return 0x42;
	}

	public Object getValue(Object entity, String attribute) {
		return ((BasicMessage) entity).get(attribute);
	}

	public boolean setValue(Object entity, String attribute, Object value) {
		return ((BasicMessage) entity).set(attribute, value);
	}
}
