package org.sdmlib.serialization;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.sdmlib.serialization.interfaces.BaseEntityList;
/**
 * The Class EntityList.
 */

public abstract class EntityList implements BaseEntityList, List<Object> {
	protected List<Object> values;
	private boolean visible = true;

	public EntityList() {
		initMap();
	}
	
	/**
	 * Construct a EntityList from a Collection.
	 * 
	 * @param collection
	 *            A Collection.
	 */
	public EntityList(Collection<?> collection) {
		initWithMap(collection);
		initMap();
	}

	public EntityList initWithMap(Collection<?> collection) {
		if (collection != null) {
			Iterator<?> iter = collection.iterator();
			while (iter.hasNext()) {
				put(EntityUtil.wrap(iter.next(), this));
			}
		}
		return this;
	}

	protected void initMap(){
		this.values=new ArrayList<Object>();
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
	@Override
	public Object get(int index) throws RuntimeException {
		Object object = values.get(index);
		if (object == null) {
			throw new RuntimeException("EntityList[" + index + "] not found.");
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
		throw new RuntimeException("EntityList[" + index
				+ "] is not a boolean.");
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
			throw new RuntimeException("EntityList[" + index
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
			throw new RuntimeException("EntityList[" + index
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
			throw new RuntimeException("EntityList[" + index
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
		return get(index).toString();
	}

	/**
	 * Make a string from the contents of this EntityList. The
	 * <code>separator</code> string is inserted between each element. Warning:
	 * This method assumes that the data structure is acyclical.
	 * 
	 * @param separator
	 *            A string that will be inserted between the elements.
	 * @return a string.
	 */
	public String join(String separator) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < values.size(); i += 1) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(EntityUtil.valueToString(values.get(i), false, this));
		}
		return sb.toString();
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
		put(Integer.valueOf(value));
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
		put(Long.valueOf(value));
		return this;
	}

	/**
	 * Append an object value. This increases the array's length by one.
	 * 
	 * @param value
	 *            An object value. The value should be a Boolean, Double,
	 *            Integer, EntityList, Entity, Long, or String object.
	 * @return
	 */
	public EntityList put(Object value) {
		add(value);
		return this;
	}

	/**
	 * Put or replace a boolean value in the EntityList. If the index is greater
	 * than the length of the EntityList, then null elements will be added as
	 * necessary to pad it out.
	 * 
	 * @param index
	 *            The subscript.
	 * @param value
	 *            A boolean value.
	 * @return
	 * @return this.
	 * @throws RuntimeExceptioni
	 *             If the index is negative.
	 */
	public EntityList put(int index, boolean value) throws RuntimeException {
		put(index, value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}

	/**
	 * Put or replace a double value. If the index is greater than the length of
	 * the EntityList, then null elements will be added as necessary to pad it
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
		put(index, Double.valueOf(value));
		return this;
	}

	/**
	 * Put or replace an int value. If the index is greater than the length of
	 * the EntityList, then null elements will be added as necessary to pad it
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
		put(index, Integer.valueOf(value));
		return this;
	}

	/**
	 * Put or replace a long value. If the index is greater than the length of
	 * the EntityList, then null elements will be added as necessary to pad it
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
		put(index, Long.valueOf(value));
		return this;
	}

	/**
	 * Put or replace an object value in the EntityList. If the index is greater
	 * than the length of the EntityList, then null elements will be added as
	 * necessary to pad it out.
	 * 
	 * @param index
	 *            The subscript.
	 * @param value
	 *            The value to put into the array. The value should be a
	 *            Boolean, Double, Integer, EntityList, Entity, Long, or String
	 *            object.
	 * @return this.
	 * @throws RuntimeException
	 *             If the index is negative or if the the value is an invalid
	 *             number.
	 */
	public EntityList put(int index, Object value) throws RuntimeException {
		EntityUtil.testValidity(value);
		if (index < 0) {
			throw new RuntimeException("EntityList[" + index + "] not found.");
		}
		if (index < size()) {
			values.set(index, value);
		} else {
			while (index != size()) {
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
	@Override
	public Object remove(int index) {
		Object o = get(index);
		values.remove(index);
		return o;
	}

	public abstract String toString(int indentFactor);

	public abstract String toString(int indentFactor, int intent);

	@Override
	public abstract String toString();

	/**
	 * Get the number of elements in the EntityList, included nulls.
	 * 
	 * @return The length (or size).
	 */
	@Override
	public int size() {
		return values.size();
	}

	/**
	 * If the JsonArray is Empty
	 * 
	 * @return boolean of size
	 */
	@Override
	public boolean isEmpty() {
		return values.size() < 1;
	}

	@Override
	public boolean contains(Object o) {
		return values.contains(o);
	}

	@Override
	public Iterator<Object> iterator() {
		return values.iterator();
	}

	@Override
	public Object[] toArray() {
		return values.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return values.toArray(a);
	}

	/* The Basic-Add Method
	 * @see de.uniks.networkparser.interfaces.BaseEntityList#add(java.lang.Object)
	 */
	@Override
	public boolean add(Object e) {
		return values.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return values.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return values.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Object> c) {
		return values.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Object> c) {
		return values.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return values.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return values.retainAll(c);
	}

	@Override
	public void clear() {
		values.clear();
	}

	@Override
	public Object set(int index, Object element) {
		return values.set(index, element);
	}

	@Override
	public void add(int index, Object element) {
		values.add(index, element);
	}

	@Override
	public int indexOf(Object o) {
		return values.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return values.lastIndexOf(o);
	}

	@Override
	public ListIterator<Object> listIterator() {
		return values.listIterator();
	}

	@Override
	public ListIterator<Object> listIterator(int index) {
		return values.listIterator(index);
	}

	@Override
	public List<Object> subList(int fromIndex, int toIndex) {
		return values.subList(fromIndex, toIndex);
	}

	@Override
	public EntityList withVisible(boolean value) {
		this.visible = value;
		return this;
	}
	
	public abstract EntityList withValue(String value);

	public boolean isVisible() {
		return visible;
	}
}
