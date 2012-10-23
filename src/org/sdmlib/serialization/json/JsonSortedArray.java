package org.sdmlib.serialization.json;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.sdmlib.serialization.EntityList;

/**
 * The Class JsonSortedArray.
 */
public class JsonSortedArray extends JsonArray{
	/** The my sort array list. */
	private SortedMap<Object, JsonObject> mySortArrayList;
	private ArrayList<JsonObject> unsorted;
			
	/** The sort prop. */
	private String sortProp=JsonIdMap.ID;
	
	/** The prop sort. */
	private boolean propSort;
		
	/**
	 * Instantiates a new json sorted array.
	 */
	public JsonSortedArray(){
		this.mySortArrayList=new TreeMap<Object, JsonObject>();
	}
	
	/**
	 * Instantiates a new json sorted array.
	 *
	 * @param property the property
	 */
	public JsonSortedArray(String property){
		this.mySortArrayList=new TreeMap<Object, JsonObject>();
		this.setSortProp(property);
	}
	
	/**
	 * Instantiates a new json sorted array.
	 *
	 * @param comparator the comparator
	 */
	public JsonSortedArray(Comparator<Object> comparator){
		this.mySortArrayList=new TreeMap<Object, JsonObject>(comparator);
	}
	
	/**
	 * Instantiates a new json sorted array.
	 *
	 * @param comparator the comparator
	 * @param property the property
	 */
	public JsonSortedArray(Comparator<Object> comparator, String property){
		this.mySortArrayList=new TreeMap<Object, JsonObject>(comparator);
		this.setSortProp(property);
	}

	/**
	 * Sets the sort prop.
	 *
	 * @param property the new sort prop
	 */
	public void setSortProp(String property) {
		if(property.startsWith(JsonIdMap.JSON_PROPS+".")){
			this.sortProp=property.substring(JsonIdMap.JSON_PROPS.length()+1);
			this.propSort=true;
		}else{
			this.sortProp=property;
			this.propSort=false;
		}
	}
	
	/**
	 * Save a JsonObject to the Array
	 */
	@Override
	public EntityList put(Object value) {
		super.put(value);
		addToTree((JsonObject) value);
		return this;
	}
	private void addToTree(JsonObject value){
		if(this.propSort){
			JsonObject props = (JsonObject) value.get(JsonIdMap.JSON_PROPS);
			if(props!=null){
				this.mySortArrayList.put(props.get(this.sortProp), value);
			}else{
				if(unsorted==null){
					unsorted=new ArrayList<JsonObject>();
				}
				unsorted.add(value);
			}
		}else{
			this.mySortArrayList.put(value.get(this.sortProp), value);
		}
	}

	public void finishUnsorted(){
		if(unsorted==null){
			return;
		}
		for(Iterator<JsonObject> i = unsorted.iterator();i.hasNext();){
			JsonObject json=i.next();
			JsonObject props = (JsonObject) json.get(JsonIdMap.JSON_PROPS);
			if(props!=null){
				this.mySortArrayList.put(props.get(this.sortProp), json);
				i.remove();
			}
		}
	}
	
	/**
	 * Get alle Elements of the Array
	 */
	@Override
	public List<Object> getElements() {
		return new ArrayList<Object>(this.mySortArrayList.values());
	}
}
