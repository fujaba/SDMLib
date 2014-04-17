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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.sdmlib.serialization.interfaces.BaseEntityList;
import org.sdmlib.serialization.sort.EntityComparator;
import org.sdmlib.serialization.sort.SortingDirection;
/**
 * The Class EntityList.
 */

public abstract class EntityList<V> implements BaseEntityList, List<V> {
	protected List<V> values=getNewList();
	private boolean visible = true;
	private boolean allowDuplicate = true;
	protected Comparator<V> cpr;

	public List<V> getNewList(){
		return new ArrayList<V>();
	}
	
	public Comparator<V> comparator() {
		if(this.cpr==null){
			withComparator(new EntityComparator<V>().withColumn(EntityComparator.LIST).withDirection(SortingDirection.ASC));
		}
		return cpr;
	}
	
	public boolean isComparator(){
		return (this.cpr!=null);
	}
	
	public EntityList<V> withComparator(Comparator<V> comparator){
		this.cpr = comparator;
		return this;
	}
	public EntityList<V> withComparator(String column){
		this.cpr = new EntityComparator<V>().withColumn(column).withDirection(SortingDirection.ASC);
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean add(Object newValue) {
		if(cpr!=null){
			for (int i = 0; i < values.size(); i++) {
				int result = compare(values.get(i), (V)newValue);
				if (result >= 0) {
					values.add(i, (V) newValue);
					return true;
				}
			}
		}
		return values.add((V) newValue);
	}
	
	public int compare(V o1, V o2){
		return comparator().compare(o1, o2);
	}
	
	@Override
	public EntityList<V> with(Object newvalue){
		add(newvalue);
		return this;
	}

	@Override
	public abstract EntityList<V> getNewArray();
	
	public EntityList<V> subSet(V fromElement, V toElement) {
		EntityList<V> newList = getNewArray();
		
		// PRE WHILE
		Iterator<V> iterator = iterator();
		while(iterator.hasNext()){
			V item = iterator.next();
			if(compare(item, fromElement)>=0){
				newList.add(item);
				break;
			}
		}
		
		// MUST COPY
		while(iterator.hasNext()){
			V item = iterator.next();
			if(compare(item, toElement)>=0){
				break;
			}
			newList.add(item);
		}
		return newList;
	}
	@Override
	public List<V> subList(int fromIndex, int toIndex) {
		return values.subList(fromIndex, toIndex);
	}

    /**
     * Returns a view of the portion of this map whose keys are less than (or
     * equal to, if {@code inclusive} is true) {@code toKey}.  The returned
     * map is backed by this map, so changes in the returned map are reflected
     * in this map, and vice-versa.  The returned map supports all optional
     * map operations that this map supports.
     *
     * <p>The returned map will throw an {@code IllegalArgumentException}
     * on an attempt to insert a key outside its range.
     *
     * @param toKey high endpoint of the keys in the returned map
     * @param inclusive {@code true} if the high endpoint
     *        is to be included in the returned view
	*/
	public EntityList<V> headSet(V toElement, boolean inclusive) {
		Iterator<V> iterator = iterator();
		EntityList<V> newList = getNewArray();

		// MUST COPY
		while(iterator.hasNext()){
			V item = iterator.next();
			int compare = compare(item, toElement);
			if(compare==0){
				if(inclusive){
					newList.add(item);
				}
				break;
			}else if(compare>0){
				newList.add(item);
				break;
			}
		}
		return newList;
	}

	/**
     * Returns a view of the portion of this map whose keys are greater than (or
     * equal to, if {@code inclusive} is true) {@code fromKey}.
     *
     * @param fromKey low endpoint of the keys in the returned map
     * @param inclusive {@code true} if the low endpoint
     *        is to be included in the returned view
     * @return a view of the portion of this map whose keys are greater than
     *         (or equal to, if {@code inclusive} is true) {@code fromKey}
     *         
     */
	public EntityList<V> tailSet(V fromElement, boolean inclusive) {
		Iterator<V> iterator = iterator();
		EntityList<V> newList = getNewArray();

		// PRE WHILE
		while(iterator.hasNext()){
			V item = iterator.next();
			int compare = compare(item, fromElement);
			if(compare==0){
				if(inclusive){
					newList.add(item);
				}
				break;
			}else if(compare>0){
				newList.add(item);
				break;
			}
		}
	
		// MUST COPY
		while(iterator.hasNext()){
			Object item = iterator.next();
			newList.add(item);
		}
		return newList;
	}

	/**
	 * @return the First Element of the List
	 */
	public Object first() {
		return this.get(0);
	}

	/**
	 * @return the Last Element of the List
	 */
	public Object last() {
		return this.get(this.size()-1);
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
	public V get(int index) throws RuntimeException {
		V object = values.get(index);
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
	public EntityList<V> put(int index, V value) throws RuntimeException {
		EntityUtil.testValidity(value);
		if (index < 0) {
			throw new RuntimeException("EntityList[" + index + "] not found.");
		}
		if (index < size()) {
			values.set(index, value);
		} else {
			while (index != size()) {
				add(null);
			}
			add(value);
		}
		return this;
	}
	
	@Override
	public EntityList<V> withVisible(boolean value) {
		this.visible = value;
		return this;
	}
	@Override
	public boolean isVisible() {
		return visible;
	}

	public boolean isAllowDuplicate() {
		return allowDuplicate;
	}

	public EntityList<V> withAllowDuplicate(boolean allowDuplicate) {
		this.allowDuplicate = allowDuplicate;
		return this;
	}
	
//	public abstract EntityList withValue(String value);

	@Override
	public EntityList<V> withValues(Collection<?> collection) {
		if (collection != null) {
			Iterator<?> iter = collection.iterator();
			while (iter.hasNext()) {
				add(EntityUtil.wrap(iter.next(), this));
			}
		}
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
	public V remove(int index) {
		V o = get(index);
		values.remove(index);
		return o;
	}
	
	public EntityList<V> withList(List<V> reference){
		this.values = reference;
		return this;
	}

	public EntityList<V> withReference(EntityList<V> reference){
		this.cpr = reference.comparator();
		this.values = reference.getNewList();
		this.allowDuplicate = reference.isAllowDuplicate();
		return this;
	}

	
	@Override
	public abstract String toString(int indentFactor);

	@Override
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
	public Iterator<V> iterator() {
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


	@Override
	public boolean remove(Object o) {
		return values.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return values.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends V> c) {
		return values.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends V> c) {
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
	public V set(int index, V element) {
		return values.set(index, element);
	}

	@Override
	public void add(int index, V element) {
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
	public ListIterator<V> listIterator() {
		return values.listIterator();
	}

	@Override
	public ListIterator<V> listIterator(int index) {
		return values.listIterator(index);
	}
}
