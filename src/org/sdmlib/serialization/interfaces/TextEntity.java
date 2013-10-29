package org.sdmlib.serialization.interfaces;


public interface TextEntity extends BaseEntity{
	/**
	 * @param key The Key for Item
	 * @param value The Vlaue of Item
	 */
	public void put(String key, Object value);
	
	/**
	 * Check if the Map has the key
	 * @param key for searching
	 * @return boolean if the Map has the key
	 */
	public boolean has(String key);
	
	public String getString(String key);
	
	public Object get(String key);
}
