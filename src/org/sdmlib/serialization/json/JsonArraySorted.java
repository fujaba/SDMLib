package org.sdmlib.serialization.json;

import java.util.Comparator;
import java.util.LinkedList;

import org.sdmlib.serialization.sort.EntityComparator;
import org.sdmlib.serialization.sort.SortingDirection;


public class JsonArraySorted extends JsonArray {
	protected Comparator<? super Object> cpr;
	
	/**
	 * Instantiates a new json sorted array.
	 */
	public JsonArraySorted(){
		this.cpr = new EntityComparator(EntityComparator.HASHCODE, SortingDirection.ASC);
	}
	
	/**
	 * Instantiates a new json sorted array.
	 * 
	 * @param property
	 *            the property
	 */
	public JsonArraySorted(String property) {
		this.cpr = new EntityComparator(property, SortingDirection.ASC);
	}
	
	/**
	 * Instantiates a new json sorted array.
	 * 
	 * @param comparator
	 *            the comparator
	 */
	public JsonArraySorted(Comparator<Object> comparator) {
		this.cpr = comparator;
	}

	
	
	
	@Override
	protected void initMap() {
		values = new LinkedList<Object>();
	}
	@Override
	public void add(int index, Object element) {
		// TODO Auto-generated method stub
		super.add(index, element);
	}
	@Override
	public boolean add(Object newValue) {
		for (int i = 0; i < values.size(); i++) {
			int result = cpr.compare(values.get(i), newValue);
			if (result >= 0) {
				values.add(i, newValue);
				return true;
			}
		}
		return values.add(newValue);
	}
}
