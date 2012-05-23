package org.sdmlib.serialization.json;
/*
Copyright (c) 2012 Stefan Lindel

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The Class JsonSortedArray.
 */
public class JsonSortedArray extends JsonArray{
	
	/** The my sort array list. */
	private SortedMap<Object, JsonObject> mySortArrayList;
			
	/** The sort prop. */
	private String sortProp=JsonIdMap.JSON_ID;
	
	/** The prop sort. */
	private boolean propSort;
		
	/**
	 * Instantiates a new json sorted array.
	 */
	public JsonSortedArray(){
		mySortArrayList=new TreeMap<Object, JsonObject>();
	}
	
	/**
	 * Instantiates a new json sorted array.
	 *
	 * @param property the property
	 */
	public JsonSortedArray(String property){
		mySortArrayList=new TreeMap<Object, JsonObject>();
		this.setSortProp(property);
	}
	
	/**
	 * Instantiates a new json sorted array.
	 *
	 * @param comparator the comparator
	 */
	public JsonSortedArray(Comparator<Object> comparator){
		mySortArrayList=new TreeMap<Object, JsonObject>(comparator);
	}
	
	/**
	 * Instantiates a new json sorted array.
	 *
	 * @param comparator the comparator
	 * @param property the property
	 */
	public JsonSortedArray(Comparator<Object> comparator, String property){
		mySortArrayList=new TreeMap<Object, JsonObject>(comparator);
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
			propSort=true;
		}else{
			this.sortProp=property;
			propSort=false;
		}
	}
	
	/**
	 * Save a JsonObject to the Array
	 */
	@Override
	public JsonArray put(Object value) {
		super.put(value);
		JsonObject json=(JsonObject) value;
		if(propSort){
			JsonObject props = (JsonObject) json.get(JsonIdMap.JSON_PROPS);
			if(props!=null){
				this.mySortArrayList.put(props.get(sortProp), json);
			}
		}else{
			this.mySortArrayList.put(json.get(sortProp), json);
		}
		return this;
	}
	
	/**
	 * Get alle Elements of the Array
	 */
	@Override
	public List<Object> getElements() {
		return new ArrayList<Object>(mySortArrayList.values());
	}
}
