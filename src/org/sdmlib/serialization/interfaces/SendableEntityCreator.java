package org.sdmlib.serialization.interfaces;

public interface SendableEntityCreator {
	public String[] getProperties();

	public Object getSendableInstance(boolean prototyp);

	public Object getValue(Object entity, String attribute);

	public boolean setValue(Object entity, String attribute, Object value);
}
