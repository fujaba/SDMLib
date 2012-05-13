package org.sdmlib.serialization;

// import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Entity extends BaseEntity{
	/**
	 * The map where the JsonObject's properties are kept.
	 */
	private Map<String, Object> map;
	protected Map<String, Object> getMap(){
		if(map==null){
			map=new LinkedHashMap<String, Object>();
		}
		return map;
	}

	/**
	 * Construct an empty JsonObject.
	 */
	public Entity() {
	}

		/**
	 * Construct a JsonObject from a Map.
	 *
	 * @param map A map object that can be used to initialize the contents of
	 *  the JsonObject.
	 */
	public Entity(Map<String, Object> map) {
		initWithMap(map);
	}
	public Entity initWithMap(Map<String, Object> map){
		getMap();
		if (map != null) {
			Iterator<Entry<String, Object>> i = map.entrySet().iterator();
			while (i.hasNext()) {
				Entry<String, Object> e = i.next();
				Object value = e.getValue();
				if (value != null) {
					this.put(e.getKey(), EntityUtil.wrap(value, this));
				}
			}
		}
		return this;
	}
	


	/**
	 * Accumulate values under a key. It is similar to the put method except
	 * that if there is already an object stored under the key then a
	 * JsonArray is stored under the key to hold all of the accumulated values.
	 * If there is already a JsonArray, then the new value is appended to it.
	 * In contrast, the put method replaces the previous value.
	 *
	 * If only one value is accumulated that is not a JsonArray, then the
	 * result will be the same as using put. But if multiple values are
	 * accumulated, then the result will be like append.
	 * @param key   A key string.
	 * @param value An object to be accumulated under the key.
	 * @return this.
	 * @throws JSONException If the value is an invalid number
	 *  or if the key is null.
	 */
	public Entity accumulate(String key, Object value){
		EntityUtil.testValidity(value);
		Object object = this.get(key);
		if (object == null) {
			this.put(key, value instanceof EntityList
					? getNewArray().put(value)
							: value);
		} else if (object instanceof EntityList) {
			((EntityList)object).put(value);
		} else {
			this.put(key, getNewArray().put(object).put(value));
		}
		return this;
	}


	/**
	 * Append values to the array under a key. If the key does not exist in the
	 * JsonObject, then the key is put in the JsonObject with its value being a
	 * JsonArray containing the value parameter. If the key was already
	 * associated with a JsonArray, then the value parameter is appended to it.
	 * @param key   A key string.
	 * @param value An object to be accumulated under the key.
	 * @return this.
	 * @throws RuntimeException If the key is null or if the current value
	 *  associated with the key is not a JsonArray.
	 */
	public Entity append(String key, Object value) {
		EntityUtil.testValidity(value);
		Object object = this.get(key);
		if (object == null) {
			this.put(key, getNewArray().put(value));
		} else if (object instanceof EntityList) {
			this.put(key, ((EntityList)object).put(value));
		} else {
			throw new RuntimeException("JsonObject[" + key +
			                                       "] is not a JsonArray.");
		}
		return this;
	}


	/**
	 * Produce a string from a double. The string "null" will be returned if
	 * the number is not finite.
	 * @param  d A double.
	 * @return A String.
	 */
	public static String doubleToString(double d) {
		if (Double.isInfinite(d) || Double.isNaN(d)) {
			return "null";
		}

		// Shave off trailing zeros and decimal point, if possible.

		String string = Double.toString(d);
		if (string.indexOf('.') > 0 && string.indexOf('e') < 0 &&
				string.indexOf('E') < 0) {
			while (string.endsWith("0")) {
				string = string.substring(0, string.length() - 1);
			}
			if (string.endsWith(".")) {
				string = string.substring(0, string.length() - 1);
			}
		}
		return string;
	}


	/**
	 * Get the value object associated with a key.
	 *
	 * @param key   A key string.
	 * @return      The object associated with the key.
	 * @throws      RuntimeException if the key is not found.
	 */
	public Object get(String key) {
		if (key == null) {
			throw new RuntimeException("Null key.");
		}
		return getMap().get(key);
	}


	/**
	 * Get the boolean value associated with a key.
	 *
	 * @param key   A key string.
	 * @return      The truth.
	 * @throws      RuntimeException
	 *  if the value is not a Boolean or the String "true" or "false".
	 */
	public boolean getBoolean(String key)  {
		Object object = this.get(key);
		if (object == null || object.equals(Boolean.FALSE) ||
				(object instanceof String &&
						((String)object).equalsIgnoreCase("false"))) {
			return false;
		} else if (object.equals(Boolean.TRUE) ||
				(object instanceof String &&
						((String)object).equalsIgnoreCase("true"))) {
			return true;
		}
		throw new RuntimeException("JsonObject[" + EntityUtil.quote(key) +
				"] is not a Boolean.");
	}


	/**
	 * Get the double value associated with a key.
	 * @param key   A key string.
	 * @return      The numeric value.
	 * @throws RuntimeException if the key is not found or
	 *  if the value is not a Number object and cannot be converted to a number.
	 */
	public double getDouble(String key) {
		Object object = this.get(key);
		try {
			return object instanceof Number
					? ((Number)object).doubleValue()
							: Double.parseDouble((String)object);
		} catch (Exception e) {
			throw new RuntimeException("JsonObject[" + EntityUtil.quote(key) +
			                                       "] is not a number.");
		}
	}


	/**
	 * Get the int value associated with a key.
	 *
	 * @param key   A key string.
	 * @return      The integer value.
	 * @throws   RuntimeException if the key is not found or if the value cannot
	 *  be converted to an integer.
	 */
	public int getInt(String key)  {
		Object object = this.get(key);
		try {
			return object instanceof Number
					? ((Number)object).intValue()
							: Integer.parseInt((String)object);
		} catch (Exception e) {
			throw new RuntimeException("JsonObject[" + EntityUtil.quote(key) +
			                                       "] is not an int.");
		}
	}

	/**
	 * Get the long value associated with a key.
	 *
	 * @param key   A key string.
	 * @return      The long value.
	 * @throws   RuntimeException if the key is not found or if the value cannot
	 *  be converted to a long.
	 */
	public long getLong(String key)  {
		Object object = this.get(key);
		try {
			return object instanceof Number
					? ((Number)object).longValue()
							: Long.parseLong((String)object);
		} catch (Exception e) {
			throw new RuntimeException("JsonObject[" + EntityUtil.quote(key) +
			                                       "] is not a long.");
		}
	}


	/**
	 * Get an array of field names from a JsonObject.
	 *
	 * @return An array of field names, or null if there are no names.
	 */
	public static String[] getNames(Entity jo) {
		int length = jo.length();
		if (length == 0) {
			return null;
		}
		Iterator<String> keys = jo.keys();
		String[] names = new String[length];
		int i = 0;
		while (keys.hasNext()) {
			names[i] = (String)keys.next();
			i += 1;
		}
		return names;
	}

	/**
	 * Get the string associated with a key.
	 *
	 * @param key   A key string.
	 * @return      A string which is the value.
	 * @throws   RuntimeException if there is no string value for the key.
	 */
	public String getString(String key) {
		Object object = this.get(key);
		if (object instanceof String) {
			return (String)object;
		}
		throw new RuntimeException("JsonObject[" + EntityUtil.quote(key) +
				"] not a string.");
	}

	/**
	 * Determine if the JsonObject contains a specific key.
	 * @param key   A key string.
	 * @return      true if the key exists in the JsonObject.
	 */
	public boolean has(String key) {
		return getMap().containsKey(key);
	}


	/**
	 * Increment a property of a JsonObject. If there is no such property,
	 * create one with a value of 1. If there is such a property, and if
	 * it is an Integer, Long, Double, or Float, then add one to it.
	 * @param key  A key string.
	 * @return this.
	 * @throws RuntimeException If there is already a property with this name
	 * that is not an Integer, Long, Double, or Float.
	 */
	public Entity increment(String key) {
		Object value = this.get(key);
		if (value == null) {
			this.put(key, 1);
		} else if (value instanceof Integer) {
			this.put(key, ((Integer)value).intValue() + 1);
		} else if (value instanceof Long) {
			this.put(key, ((Long)value).longValue() + 1);
		} else if (value instanceof Double) {
			this.put(key, ((Double)value).doubleValue() + 1);
		} else if (value instanceof Float) {
			this.put(key, ((Float)value).floatValue() + 1);
		} else {
			throw new RuntimeException("Unable to increment [" + EntityUtil.quote(key) + "].");
		}
		return this;
	}

	/**
	 * Get an enumeration of the keys of the JsonObject.
	 *
	 * @return An iterator of the keys.
	 */
	public Iterator<String> keys() {
		return getMap().keySet().iterator();
	}

	/**
	 * Get the number of keys stored in the JsonObject.
	 *
	 * @return The number of keys in the JsonObject.
	 */
	public int length() {
		if(map==null){
			return 0;
		}
		return this.map.size();
	}

	/**
	 * Produce a JsonArray containing the names of the elements of this
	 * JsonObject.
	 * @return A JsonArray containing the key strings, or null if the JsonObject
	 * is empty.
	 */
	public EntityList names() {
		EntityList ja = getNewArray();
		Iterator<String> keys = this.keys();
		while (keys.hasNext()) {
			ja.put(keys.next());
		}
		return ja.length() == 0 ? null : ja;
	}

	

	/**
	 * Put a key/boolean pair in the JsonObject.
	 *
	 * @param key   A key string.
	 * @param value A boolean which is the value.
	 * @return this.
	 */
	public Entity put(String key, boolean value) {
		this.put(key, value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}


	/**
	 * Put a key/value pair in the JsonObject, where the value will be a
	 * JsonArray which is produced from a Collection.
	 * @param key   A key string.
	 * @param value A Collection value.
	 * @return      this.
	 */
	public Entity put(String key, Collection<?> value) {
		this.put(key, getNewArray().initWithMap(value));
		return this;
	}


	/**
	 * Put a key/double pair in the JsonObject.
	 *
	 * @param key   A key string.
	 * @param value A double which is the value.
	 * @return this.
	 */
	public Entity put(String key, double value) {
		this.put(key, new Double(value));
		return this;
	}


	/**
	 * Put a key/int pair in the JsonObject.
	 *
	 * @param key   A key string.
	 * @param value An int which is the value.
	 * @return this.
	 */
	public Entity put(String key, int value)  {
		this.put(key, new Integer(value));
		return this;
	}


	/**
	 * Put a key/long pair in the JsonObject.
	 *
	 * @param key   A key string.
	 * @param value A long which is the value.
	 * @return this.
	 */
	public Entity put(String key, long value)  {
		this.put(key, new Long(value));
		return this;
	}


	/**
	 * Put a key/value pair in the JsonObject, where the value will be a
	 * JsonObject which is produced from a Map.
	 * @param key   A key string.
	 * @param value A Map value.
	 * @return      this.
	 * @throws JSONException
	 */
	public Entity put(String key, Map<String, Object> value)  {
		this.put(key, getNewObject().initWithMap(value));
		return this;
	}


	/**
	 * Put a key/value pair in the JsonObject. If the value is null,
	 * then the key will be removed from the JsonObject if it is present.
	 * @param key   A key string.
	 * @param value An object which is the value. It should be of one of these
	 *  types: Boolean, Double, Integer, JsonArray, JsonObject, Long, String,
	 *  or the JsonObject.NULL object.
	 * @return this.
	 * @throws RuntimeException If the value is non-finite number
	 *  or if the key is null.
	 */
	public Entity put(String key, Object value) {
		if (key == null) {
			throw new RuntimeException("Null key.");
		}
		if (value != null) {
			EntityUtil.testValidity(value);
			getMap().put(key, value);
		} else {
			this.remove(key);
		}
		return this;
	}

	/**
	 * Remove a name and its value, if present.
	 * @param key The name to be removed.
	 * @return The value that was associated with the name,
	 * or null if there was no value.
	 */
	public Object remove(String key) {
		return getMap().remove(key);
	}

	/**
	 * Make a JSON text of this JsonObject. For compactness, no whitespace
	 * is added. If this would not result in a syntactically correct JSON text,
	 * then null will be returned instead.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 *
	 * @return a printable, displayable, portable, transmittable
	 *  representation of the object, beginning
	 *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
	 *  with <code>}</code>&nbsp;<small>(right brace)</small>.
	 */
	public abstract String toString();
	/**
	 * Make a prettyprinted JSON text of this JsonObject.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 * @param indentFactor The number of spaces to add to each level of
	 *  indentation.
	 * @return a printable, displayable, portable, transmittable
	 *  representation of the object, beginning
	 *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
	 *  with <code>}</code>&nbsp;<small>(right brace)</small>.
	 */
	public abstract String toString(int indentFactor);
	public abstract String toString(int indentFactor, int intent);
	// public abstract Writer write(Writer writer);
}