package org.sdmlib.serialization.json;

/*
 Json Id Serialisierung Map
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
