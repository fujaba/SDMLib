package org.sdmlib.serialization.event.creator;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonArray;

public class JsonArrayCreator extends EntityFactory{
	private final String[] properties= new String[]{"VALUE"};
	@Override
	public String[] getProperties() {
		return this.properties;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new JsonArray();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return entity.toString();
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value, String typ) {
		return ((JsonArray)entity).setAllValue((String) value);
	}

}