package org.sdmlib.serialization.json;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or higher as soon they
 will be approved by the European Commission - subsequent
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
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;
import org.sdmlib.serialization.sort.EntityComparator;
import org.sdmlib.serialization.sort.SortingDirection;

public class JsonArraySorted extends JsonArray implements SortedSet<Object>{
	protected Comparator<Object> cpr;
	
	@Override
	protected void initMap() {
		values = new LinkedList<Object>();
	}

	@Override
	public boolean add(Object newValue) {
		for (int i = 0; i < values.size(); i++) {
			int result = comparator().compare(values.get(i), newValue);
			if (result >= 0) {
				values.add(i, newValue);
				return true;
			}
		}
		return values.add(newValue);
	}
	
	public JsonArraySorted withComparator(Comparator<Object> comparator){
		this.cpr = comparator;
		return this;
	}
	
	@Override
	public Comparator<? super Object> comparator() {
		if(this.cpr==null){
			this.cpr = new EntityComparator().withColumn(EntityComparator.HASHCODE).withDirection(SortingDirection.ASC);
		}
		return cpr;
	}

	@Override
	public SortedSet<Object> subSet(Object fromElement, Object toElement) {
		JsonArraySorted newList = new JsonArraySorted().withComparator(cpr);
		
		// PRE WHILE
		Iterator<Object> iterator = iterator();
		while(iterator.hasNext()){
			Object item = iterator.next();
			if(cpr.compare(item, fromElement)>=0){
				newList.add(item);
				break;
			}
		}
		
		// MUST COPY
		while(iterator.hasNext()){
			Object item = iterator.next();
			if(cpr.compare(item, toElement)>=0){
				break;
			}
			newList.add(item);
		}
		return newList;
	}

	@Override
	public SortedSet<Object> headSet(Object toElement) {
		Iterator<Object> iterator = iterator();
		JsonArraySorted newList = new JsonArraySorted().withComparator(cpr);

		// MUST COPY
		while(iterator.hasNext()){
			Object item = iterator.next();
			if(cpr.compare(item, toElement)>=0){
				break;
			}
			newList.add(item);
		}
		return newList;
	}

	@Override
	public SortedSet<Object> tailSet(Object fromElement) {
		Iterator<Object> iterator = iterator();
		JsonArraySorted newList = new JsonArraySorted().withComparator(cpr);

		// PRE WHILE
		while(iterator.hasNext()){
			Object item = iterator.next();
			if(cpr.compare(item, fromElement)>=0){
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

	@Override
	public Object first() {
		return this.get(0);
	}

	@Override
	public Object last() {
		return this.get(this.size()-1);
	}
}
