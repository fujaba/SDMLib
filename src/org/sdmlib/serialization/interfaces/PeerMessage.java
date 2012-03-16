package org.sdmlib.serialization.interfaces;

public interface PeerMessage {
	/**
	 * Gets the Datatype of a value.
	 * 
	 * @param typ
	 *            the typ
	 * @return the DataTyp
	 */
	public Object get(String attribute);

	public boolean set(String attribute, Object value);
}
