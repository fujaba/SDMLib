package org.sdmlib.serialization;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public abstract class EntityList extends BaseEntity{
	public EntityList(){
		
	}
	/**
	 * Construct a JSONArray from a Collection.
	 * 
	 * @param collection
	 *            A Collection.
	 */
	public EntityList(Collection<?> collection) {
		initWithMap(collection);
	}
	public EntityList initWithMap(Collection<?> collection){
		if (collection != null) {
			getElements();
			Iterator<?> iter = collection.iterator();
			while (iter.hasNext()) {
				put(EntityUtil.wrap(iter.next(), this));
			}
		}
		return this;
	}
	
	/**
	 * The arrayList where the JSONArray's properties are kept.
	 */
	private ArrayList<Object> myArrayList;
	public List<Object> getElements(){
		if(myArrayList==null){
			myArrayList=new ArrayList<Object>();
		}
		return myArrayList;
	}
	/**
	 * Get the object value associated with an index.
	 * 
	 * @param index
	 *            The index must be between 0 and length() - 1.
	 * @return An object value.
	 * @throws RuntimeException
	 *             If there is no value for the index.
	 */
	public Object get(int index) throws RuntimeException {
		Object object = getElements().get(index);
		if (object == null) {
			throw new RuntimeException("JSONArray[" + index + "] not found.");
		}
		return object;
	}
	/**
	 * Get the boolean value associated with an index. The string values "true"
	 * and "false" are converted to boolean.
	 * 
	 * @param index
	 *            The index must be between 0 and length() - 1.
	 * @return The truth.
	 * @throws RuntimeException
	 *             If there is no value for the index or if the value is not
	 *             convertible to boolean.
	 */
	public boolean getBoolean(int index) throws RuntimeException {
		Object object = get(index);
		if (object.equals(Boolean.FALSE)
				|| (object instanceof String && ((String) object)
						.equalsIgnoreCase("false"))) {
			return false;
		} else if (object.equals(Boolean.TRUE)
				|| (object instanceof String && ((String) object)
						.equalsIgnoreCase("true"))) {
			return true;
		}
		throw new RuntimeException("JSONArray[" + index + "] is not a boolean.");
	}

	/**
	 * Get the double value associated with an index.
	 * 
	 * @param index
	 *            The index must be between 0 and length() - 1.
	 * @return The value.
	 * @throws RuntimeException
	 *             If the key is not found or if the value cannot be converted
	 *             to a number.
	 */
	public double getDouble(int index) throws RuntimeException {
		Object object = get(index);
		try {
			return object instanceof Number ? ((Number) object).doubleValue()
					: Double.parseDouble((String) object);
		} catch (Exception e) {
			throw new RuntimeException("JSONArray[" + index
					+ "] is not a number.");
		}
	}

	/**
	 * Get the int value associated with an index.
	 * 
	 * @param index
	 *            The index must be between 0 and length() - 1.
	 * @return The value.
	 * @throws RuntimeException
	 *             If the key is not found or if the value is not a number.
	 */
	public int getInt(int index) throws RuntimeException {
		Object object = get(index);
		try {
			return object instanceof Number ? ((Number) object).intValue()
					: Integer.parseInt((String) object);
		} catch (Exception e) {
			throw new RuntimeException("JSONArray[" + index
					+ "] is not a number.");
		}
	}
	/**
	 * Get the long value associated with an index.
	 * 
	 * @param index
	 *            The index must be between 0 and length() - 1.
	 * @return The value.
	 * @throws RuntimeException
	 *             If the key is not found or if the value cannot be converted
	 *             to a number.
	 */
	public long getLong(int index) throws RuntimeException {
		Object object = get(index);
		try {
			return object instanceof Number ? ((Number) object).longValue()
					: Long.parseLong((String) object);
		} catch (Exception e) {
			throw new RuntimeException("JSONArray[" + index
					+ "] is not a number.");
		}
	}

	/**
	 * Get the string associated with an index.
	 * 
	 * @param index
	 *            The index must be between 0 and length() - 1.
	 * @return A string value.
	 * @throws RuntimeException
	 *             If there is no value for the index.
	 */
	public String getString(int index) throws RuntimeException {
		Object object = get(index);
		if (object == null) {
			return null;
		}
		return object.toString();
	}

	/**
	 * Make a string from the contents of this JSONArray. The
	 * <code>separator</code> string is inserted between each element. Warning:
	 * This method assumes that the data structure is acyclical.
	 * 
	 * @param separator
	 *            A string that will be inserted between the elements.
	 * @return a string.
	 * @throws RuntimeException
	 *             If the array contains an invalid number.
	 */
	public String join(String separator) throws RuntimeException {
		List<Object> elements = getElements();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < elements.size(); i += 1) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(EntityUtil.valueToString(elements.get(i), this));
		}
		return sb.toString();
	}

	/**
	 * Get the number of elements in the JSONArray, included nulls.
	 * 
	 * @return The length (or size).
	 */
	public int length() {
		return getElements().size();
	}

	/**
	 * Append a boolean value. This increases the array's length by one.
	 * 
	 * @param value
	 *            A boolean value.
	 * @return 
	 */
	public EntityList put(boolean value) {
		put(value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}
	/**
	 * Append an int value. This increases the array's length by one.
	 * 
	 * @param value
	 *            An int value.
	 * @return 
	 */
	public EntityList put(int value) {
		put(new Integer(value));
		return this;
	}

	/**
	 * Append an long value. This increases the array's length by one.
	 * 
	 * @param value
	 *            A long value.
	 * @return 
	 * @return this.
	 */
	public EntityList put(long value) {
		put(new Long(value));
		return this;
	}

	/**
	 * Append an object value. This increases the array's length by one.
	 * 
	 * @param value
	 *            An object value. The value should be a Boolean, Double,
	 *            Integer, JSONArray, JSONObject, Long, or String, or the
	 *            JSONObject.NULL object.
	 * @return 
	 */
	public EntityList put(Object value) {
		getElements().add(value);
		return this;
	}

	/**
	 * Put or replace a boolean value in the JSONArray. If the index is greater
	 * than the length of the JSONArray, then null elements will be added as
	 * necessary to pad it out.
	 * 
	 * @param index
	 *            The subscript.
	 * @param value
	 *            A boolean value.
	 * @return 
	 * @return this.
	 * @throws RuntimeException
	 *             If the index is negative.
	 */
	public EntityList put(int index, boolean value) throws RuntimeException {
		put(index, value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}
	/**
	 * Put or replace a double value. If the index is greater than the length of
	 * the JSONArray, then null elements will be added as necessary to pad it
	 * out.
	 * 
	 * @param index
	 *            The subscript.
	 * @param value
	 *            A double value.
	 * @return 
	 * @throws RuntimeException
	 *             If the index is negative or if the value is not finite.
	 */
	public EntityList put(int index, double value) throws RuntimeException {
		put(index, new Double(value));
		return this;
	}

	/**
	 * Put or replace an int value. If the index is greater than the length of
	 * the JSONArray, then null elements will be added as necessary to pad it
	 * out.
	 * 
	 * @param index
	 *            The subscript.
	 * @param value
	 *            An int value.
	 * @return 
	 * @throws RuntimeException
	 *             If the index is negative.
	 */
	public EntityList put(int index, int value) throws RuntimeException {
		put(index, new Integer(value));
		return this;
	}

	/**
	 * Put or replace a long value. If the index is greater than the length of
	 * the JSONArray, then null elements will be added as necessary to pad it
	 * out.
	 * 
	 * @param index
	 *            The subscript.
	 * @param value
	 *            A long value.
	 * @return 
	 * @throws RuntimeException
	 *             If the index is negative.
	 */
	public EntityList put(int index, long value) throws RuntimeException {
		put(index, new Long(value));
		return this;
	}

	/**
	 * Put or replace an object value in the JSONArray. If the index is greater
	 * than the length of the JSONArray, then null elements will be added as
	 * necessary to pad it out.
	 * 
	 * @param index
	 *            The subscript.
	 * @param value
	 *            The value to put into the array. The value should be a
	 *            Boolean, Double, Integer, JSONArray, JSONObject, Long, or
	 *            String, or the JSONObject.NULL object.
	 * @return this.
	 * @throws RuntimeException
	 *             If the index is negative or if the the value is an invalid
	 *             number.
	 */
	public EntityList put(int index, Object value) throws RuntimeException {
		EntityUtil.testValidity(value);
		if (index < 0) {
			throw new RuntimeException("JSONArray[" + index + "] not found.");
		}
		if (index < length()) {
			getElements().set(index, value);
		} else {
			while (index != length()) {
				put(null);
			}
			put(value);
		}
		return this;
	}

	/**
	 * Append a double value. This increases the array's length by one.
	 * 
	 * @param value
	 *            A double value.
	 * @throws RuntimeException
	 *             if the value is not finite.
	 * @return this.
	 */
	public EntityList put(double value) throws RuntimeException {
		Double d = new Double(value);
		EntityUtil.testValidity(d);
		put(d);
		return this;
	}
	
	/**
	 * Remove an index and close the hole.
	 * 
	 * @param index
	 *            The index of the element to be removed.
	 * @return The value that was associated with the index, or null if there
	 *         was no value.
	 */
	public Object remove(int index) {
		Object o = get(index);
		getElements().remove(index);
		return o;
	}
	// public abstract Writer write(Writer writer);
	public abstract String toString(int indentFactor);
	public abstract String toString(int indentFactor, int intent);
	public abstract String toString();
}
