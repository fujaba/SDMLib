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

import java.util.Collection;
import java.util.HashSet;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.IdMapFilter;

/**
// * The Class JsonFilter.
 * This Class is for filter the elements of a modell. The Standard is filtering the deep of
 * serialization  
 */
public class JsonFilter extends IdMapFilter{
	
	/** The exclusive properties. */
	private String[] exclusiveProperties;
	
	/** The objects. */
	private HashSet<String> objects = new HashSet<String>();
	
	/** The treesync. */
	private boolean treesync=false;

	/**
	 * Instantiates a new json filter.
	 */
	public JsonFilter() {

	}

	/**
	 * Instantiates a new json filter.
	 *
	 * @param deep the deep
	 */
	public JsonFilter(int deep) {
		this.deep = deep;
	}

	/**
	 * Instantiates a new json filter.
	 *
	 * @param filter the filter
	 */
	public JsonFilter(String... filter) {
		this.exclusiveProperties = filter;
	}

	/**
	 * Instantiates a new json filter.
	 *
	 * @param deep the deep
	 * @param filter the filter
	 */
	public JsonFilter(int deep, String... filter) {
		this.deep = deep;
		this.exclusiveProperties = filter;
	}

	/**
	 * Gets the excusive properties.
	 *
	 * @return the excusive properties
	 */
	public String[] getExcusiveProperties() {
		return exclusiveProperties;
	}

	/**
	 * Exists object.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean existsObject(String id) {
		boolean result = objects.contains(id);
		return result;
	}
	
	/**
	 * Adds the object.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean addObject(String id){
		if(id!=null&&id.length()>0){
			this.objects.add(id);
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see de.uni.kassel.peermessage.IdMapFilter#isConvertable(de.uni.kassel.peermessage.IdMap, java.lang.Object, java.lang.String, java.lang.Object)
	 */
	public boolean isConvertable(IdMap map, Object entity, String property, Object value) {
		if (!super.isConvertable(map, entity, property, value)){
			return false;
		}
		if (getExcusiveProperties() != null) {
			for (String prop : getExcusiveProperties()) {
				if (property.equalsIgnoreCase(prop)) {
					return false;
				}
			}
		}
		if(isTreesync()){
			if(!(value instanceof Collection<?>)){
				if(map.getCreatorClass(value)!=null){
					return false;
				}
			}
		}
		return !property.endsWith(JsonIdMap.REF_SUFFIX);
	}
	
	/**
	 * Gets the objects.
	 *
	 * @return the objects
	 */
	public String[] getObjects(){
		return objects.toArray(new String[objects.size()]);
	}

	/**
	 * Checks if is treesync.
	 *
	 * @return true, if is treesync
	 */
	public boolean isTreesync() {
		return treesync;
	}

	/**
	 * Sets the treesync.
	 *
	 * @param treesync the new treesync
	 */
	public void setTreesync(boolean treesync) {
		this.treesync = treesync;
	}
}
