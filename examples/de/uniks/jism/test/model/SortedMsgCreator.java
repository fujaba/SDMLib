package de.uniks.jism.test.model;

import org.sdmlib.serialization.interfaces.SendableEntityCreatorByte;

public class SortedMsgCreator implements SendableEntityCreatorByte{

	@Override
	public String[] getProperties() {
		return new String[]{SortedMsg.PROPERTY_ID, SortedMsg.PROPERTY_CHILD, SortedMsg.PROPERTY_PARENT, SortedMsg.PROPERTY_MSG};
	}

	@Override
	public Object getSendableInstance(boolean reference) {
		return new SortedMsg();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return ((SortedMsg)entity).get(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value, String typ) {
		return ((SortedMsg)entity).set(attribute, value);
	}

	@Override
	public byte getEventTyp() {
		return 0x42;
	}

}
