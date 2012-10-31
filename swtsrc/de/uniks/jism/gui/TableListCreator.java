package de.uniks.jism.gui;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class TableListCreator implements SendableEntityCreator{
	public static final String[] properties=new String[]{TableList.PROPERTY_ITEMS};
	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new TableList();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return ((TableList)entity).get(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		return ((TableList)entity).set(attribute, value);
	}
}
