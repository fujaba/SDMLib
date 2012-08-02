package org.sdmlib.serialization.event.creater;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonObject;

public class JsonObjectCreator implements SendableEntityCreator{
	private final String[] properties= new String[]{"VALUE"};
	@Override
	public String[] getProperties() {
		return this.properties;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new JsonObject();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return entity.toString();
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value, String typ) {
		return ((JsonObject)entity).setAllValue((String) value);
	}

}
