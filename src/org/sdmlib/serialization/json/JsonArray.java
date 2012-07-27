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

import java.util.Collection;
import java.util.Iterator;

import org.sdmlib.serialization.BaseEntity;
import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.Tokener;

/**
 * A JSONArray is an ordered sequence of values. Its external text form is a
 * string wrapped in square brackets with commas separating the values. The
 * internal form is an object having <code>get</code> and <code>opt</code>
 * methods for accessing the values by index, and <code>put</code> methods for
 * adding or replacing values. The values can be any of these types:
 * <code>Boolean</code>, <code>JSONArray</code>, <code>JSONObject</code>,
 * <code>Number</code>, <code>String</code>, or the
 * <code>JSONObject.NULL object</code>.
 * <p>
 * The constructor can convert a JSON text into a Java object. The
 * <code>toString</code> method converts to JSON text.
 * <p>
 * A <code>get</code> method returns a value if one can be found, and throws an
 * exception if one cannot be found. An <code>opt</code> method returns a
 * default value instead of throwing an exception, and so is useful for
 * obtaining optional values.
 * <p>
 * The generic <code>get()</code> and <code>opt()</code> methods return an
 * object which you can cast or query for type. There are also typed
 * <code>get</code> and <code>opt</code> methods that do type checking and type
 * coercion for you.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to
 * JSON syntax rules. The constructors are more forgiving in the texts they will
 * accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just
 * before the closing bracket.</li>
 * <li>The <code>null</code> value will be inserted when there is <code>,</code>
 * &nbsp;<small>(comma)</small> elision.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single
 * quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a quote
 * or single quote, and if they do not contain leading or trailing spaces, and
 * if they do not contain any of these characters:
 * <code>{ } [ ] / \ : , = ; #</code> and if they do not look like numbers and
 * if they are not the reserved words <code>true</code>, <code>false</code>, or
 * <code>null</code>.</li>
 * <li>Values can be separated by <code>;</code> <small>(semicolon)</small> as
 * well as by <code>,</code> <small>(comma)</small>.</li>
 * <li>Numbers may have the <code>0x-</code> <small>(hex)</small> prefix.</li>
 * </ul>
 * 
 * @author JSON.org
 * @version 2010-12-28
 */

public class JsonArray extends EntityList{
	/**
	 * Construct an empty JSONArray.
	 */
	public JsonArray() {
	}

	/**
	 * Construct a JSONArray from a JSONTokener.
	 * 
	 * @param x
	 *            A JSONTokener
	 * @throws RuntimeException
	 *             If there is a syntax error.
	 */
	public JsonArray(Tokener x) throws RuntimeException {
		this();
		x.parseToEntity(this);
	}
	    


	/**
	 * Construct a JSONArray from a source JSON text.
	 * 
	 * @param source
	 *            A string that begins with <code>[</code>&nbsp;<small>(left
	 *            bracket)</small> and ends with <code>]</code>
	 *            &nbsp;<small>(right bracket)</small>.
	 * @throws RuntimeException
	 *             If there is a syntax error.
	 */
	public JsonArray(String source) throws RuntimeException {
		this(new JsonTokener(source));
	}

	/**
	 * Construct a JSONArray from a Collection.
	 * 
	 * @param collection
	 *            A Collection.
	 */
	public JsonArray(Collection<?> collection) {
		if (collection != null) {
			getElements();
			Iterator<?> iter = collection.iterator();
			while (iter.hasNext()) {
				put(EntityUtil.wrap(iter.next(), this));
			}
		}
	}
	
	/**
	 * Construct a JSONArray from a BaseEntityArray.
	 * 
	 * @param Array of Elements.
	 */
	public JsonArray(BaseEntity... values) {
		for(int i=0;i<values.length;i++){
			put(EntityUtil.wrap(values[i], this));
		}
	}

	/**
	 * Get the JSONArray associated with an index.
	 * 
	 * @param index
	 *            The index must be between 0 and length() - 1.
	 * @return A JSONArray value.
	 * @throws RuntimeException
	 *             If there is no value for the index. or if the value is not a
	 *             JSONArray
	 */
	public JsonArray getJSONArray(int index) throws RuntimeException {
		Object object = get(index);
		if (object instanceof JsonArray) {
			return (JsonArray) object;
		}
		throw new RuntimeException("JSONArray[" + index
				+ "] is not a JSONArray.");
	}

	/**
	 * Get the JSONObject associated with an index.
	 * 
	 * @param index
	 *            subscript
	 * @return A JSONObject value.
	 * @throws RuntimeException
	 *             If there is no value for the index or if the value is not a
	 *             JSONObject
	 */
	public JsonObject getJSONObject(int index) throws RuntimeException {
		Object object = get(index);
		if (object instanceof JsonObject) {
			return (JsonObject) object;
		}
		throw new RuntimeException("JSONArray[" + index
				+ "] is not a JSONObject.");
	}




	/**
	 * Put a value in the JSONArray, where the value will be a JSONArray which
	 * is produced from a Collection.
	 * 
	 * @param value
	 *            A Collection value.
	 * @return this.
	 */
	public JsonArray put(Collection<?> value) {
		super.put(new JsonArray(value));
		return this;
	}

	/**
	 * Put a value in the JSONArray, where the value will be a JSONArray which
	 * is produced from a Collection.
	 * 
	 * @param index
	 *            The subscript.
	 * @param value
	 *            A Collection value.
	 * @return this.
	 * @throws RuntimeException
	 *             If the index is negative or if the value is not finite.
	 */
	public JsonArray put(int index, Collection<?> value)
			throws RuntimeException {
		super.put(index, new JsonArray(value));
		return this;
	}



	/**
	 * Produce a JSONObject by combining a JSONArray of names with the values of
	 * this JSONArray.
	 * 
	 * @param names
	 *            A JSONArray containing a list of key strings. These will be
	 *            paired with the values.
	 * @return A JSONObject, or null if there are no names or if this JSONArray
	 *         has no values.
	 * @throws RuntimeException
	 *             If any of the names are null.
	 */
	public JsonObject toJSONObject(JsonArray names) throws RuntimeException {
		if (names == null || names.size() == 0 || size() == 0) {
			return null;
		}
		JsonObject jo = new JsonObject();
		for (int i = 0; i < names.size(); i += 1) {
			jo.put(names.getString(i), this.get(i));
		}
		return jo;
	}

	/**
	 * Make a JSON text of this JSONArray. For compactness, no unnecessary
	 * whitespace is added. If it is not possible to produce a syntactically
	 * correct JSON text then null will be returned instead. This could occur if
	 * the array contains an invalid number.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 * 
	 * @return a printable, displayable, transmittable representation of the
	 *         array.
	 */
	public String toString() {
		try {
			return '[' + join(",") + ']';
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Make a prettyprinted JSON text of this JSONArray. Warning: This method
	 * assumes that the data structure is acyclical.
	 *
	 * @param indentFactor The number of spaces to add to each level of indentation.
	 * @return a printable, displayable, transmittable representation of the
	 * object, beginning with <code>[</code>&nbsp;<small>(left
	 * bracket)</small> and ending with <code>]</code>
	 * &nbsp;<small>(right bracket)</small>.
	 * @throws RuntimeException the runtime exception
	 */
	@Override
	public String toString(int indentFactor) throws RuntimeException {
		return toString(indentFactor, 0);
	}
	
	/** 
	 * Make a prettyprinted JSON text of this JSONArray.
	 */
	public String toString(int indentFactor, int indent) {
		Iterator<Object> iterator = getElements().iterator();
		if(!iterator.hasNext()){
			return "[]";
		}

		StringBuilder sb = new StringBuilder();
        for(int i = 0; i < indentFactor; i++) {
        	sb.append(' ');
        }
        String step=sb.toString();
        String prefix="";
        int newindent = indent + indentFactor;
		if(newindent>0){
        	sb = new StringBuilder();
            for(int i = 0; i < indent; i+=indentFactor) {
            	sb.append(step);
            }
            prefix=CRLF+sb.toString();
		}
		//First Element
		
		sb = new StringBuilder("["+prefix+step);
		Object element = iterator.next();
		sb.append(EntityUtil.valueToString(element, indentFactor, newindent, false, this));
		
		while(iterator.hasNext()){
			element = iterator.next();
			sb.append(","+prefix+step);
			sb.append(EntityUtil.valueToString(element, indentFactor, newindent, false, this));
		}
		sb.append(prefix+']');
		return sb.toString();
	}

	/**
	 *  Get a new Instance of a JsonObject
	 */
	@Override
	public Entity getNewObject() {
		return new JsonObject();
	}

    public boolean setAllValue(String value){
    	clear();
    	JsonTokener tokener = new JsonTokener(value);
    	tokener.parseToEntity(this);
    	return true;
    }
	/**
	 * Get a new Instance of a JsonArray
	 */
	@Override
	public EntityList getNewArray() {
		return new JsonArray();
	}
}