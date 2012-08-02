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

import java.util.HashSet;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.IdMapFilter;

/**
// * The Class JsonFilter.
 * This Class is for filter the elements of a modell. The Standard is filtering the deep of
 * serialization  
 */
public class JsonFilter extends IdMapFilter{
	/** The Constant REFERENCE. */
	public static final String REF= "_ref";

	
	/** The exclusive properties. */
	private String[] exclusiveProperties;
	
	/** The objects. */
	private HashSet<String> objects = new HashSet<String>();
	
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
		return this.exclusiveProperties;
	}

	/**
	 * Exists object.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean existsObject(String id) {
		boolean result = this.objects.contains(id);
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
	@Override
	public boolean isConvertable(IdMap map, Object entity, String property, Object value, boolean isMany) {
		if (!super.isConvertable(map, entity, property, value, isMany)){
			return false;
		}
		if(property.endsWith(REF)){
			return false;
		}
		if (getExcusiveProperties() != null) {
			for (String prop : getExcusiveProperties()) {
				if (property.equalsIgnoreCase(prop)) {
					return false;
				}
			}
		}
		if(!isManySerialization()&&isMany){
			return false;
		}
		return !property.endsWith(JsonIdMap.REF_SUFFIX);
	}
	
	/**
	 * Gets the objects.
	 *
	 * @return the objects
	 */
	public String[] getObjects(){
		return this.objects.toArray(new String[this.objects.size()]);
	}
}
