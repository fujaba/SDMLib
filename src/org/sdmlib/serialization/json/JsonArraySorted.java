package org.sdmlib.serialization.json;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
