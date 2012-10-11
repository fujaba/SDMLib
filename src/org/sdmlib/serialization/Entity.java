package org.sdmlib.serialization;
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.serialization.interfaces.BaseEntity;

public abstract class Entity implements BaseEntity{
	/**
	 * The map where the Entity's properties are kept.
	 */
	private Map<String, Object> map;
	protected Map<String, Object> getMap(){
		if(this.map==null){
			this.map=new LinkedHashMap<String, Object>();
		}
		return this.map;
	}

	/**
	 * Construct an empty Entity.
	 */
	public Entity() {
	}

	/**
	 * Construct a Entity from a Map.
	 *
	 * @param map A map object that can be used to initialize the contents of
	 *  the Entity.
	 */
	public Entity(Map<String, Object> map) {
		initWithMap(map);
	}
	public Entity initWithMap(Map<String, Object> map){
		getMap();
		if (map != null) {
			Iterator<Entry<String, Object>> i = map.entrySet().iterator();
			while (i.hasNext()) {
				Entry<String, Object> e = i.next();
				Object value = e.getValue();
				if (value != null) {
					this.put(EntityUtil.wrap(e.getKey(), this).toString(), EntityUtil.wrap(value, this));
				}
			}
		}
		return this;
	}
	


	/**
	 * Accumulate values under a key. It is similar to the put method except
	 * that if there is already an object stored under the key then a
	 * EntityList is stored under the key to hold all of the accumulated values.
	 * If there is already a EntityList, then the new value is appended to it.
	 * In contrast, the put method replaces the previous value.
	 *
	 * If only one value is accumulated that is not a EntityList, then the
	 * result will be the same as using put. But if multiple values are
	 * accumulated, then the result will be like append.
	 * @param key   A key string.
	 * @param value An object to be accumulated under the key.
	 * @return this.
	 */
	public Entity accumulate(String key, Object value){
		EntityUtil.testValidity(value);
		Object object = this.get(key);
		if (object == null) {
			this.put(key, value instanceof EntityList
					? getNewArray().put(value)
							: value);
		} else if (object instanceof EntityList) {
			((EntityList)object).put(value);
		} else {
			this.put(key, getNewArray().put(object).put(value));
		}
		return this;
	}


	/**
	 * Append values to the array under a key. If the key does not exist in the
	 * Entity, then the key is put in the Entity with its value being a
	 * EntityList containing the value parameter. If the key was already
	 * associated with a EntityList, then the value parameter is appended to it.
	 * @param key   A key string.
	 * @param value An object to be accumulated under the key.
	 * @return this.
	 * @throws RuntimeException If the key is null or if the current value
	 *  associated with the key is not a EntityList.
	 */
	public Entity append(String key, Object value) {
		EntityUtil.testValidity(value);
		Object object = this.get(key);
		if (object == null) {
			this.put(key, getNewArray().put(value));
		} else if (object instanceof EntityList) {
			this.put(key, ((EntityList)object).put(value));
		} else {
			throw new RuntimeException("Entity["+key+"] is not a EntityList.");
		}
		return this;
	}


	/**
	 * Produce a string from a double. The string "null" will be returned if
	 * the number is not finite.
	 * @param  d A double.
	 * @return A String.
	 */
	public static String doubleToString(double d) {
		if (Double.isInfinite(d) || Double.isNaN(d)) {
			return "null";
		}

		// Shave off trailing zeros and decimal point, if possible.

		String string = Double.toString(d);
		if (string.indexOf('.') > 0 && string.indexOf('e') < 0 &&
				string.indexOf('E') < 0) {
			while (string.endsWith("0")) {
				string = string.substring(0, string.length() - 1);
			}
			if (string.endsWith(".")) {
				string = string.substring(0, string.length() - 1);
			}
		}
		return string;
	}


	/**
	 * Get the value object associated with a key.
	 *
	 * @param key   A key string.
	 * @return      The object associated with the key.
	 * @throws      RuntimeException if the key is not found.
	 */
	public Object get(String key) {
		if (key == null) {
			throw new RuntimeException("Null key.");
		}
		return getMap().get(key);
	}


	/**
	 * Get the boolean value associated with a key.
	 *
	 * @param key   A key string.
	 * @return      The truth.
	 * @throws      RuntimeException
	 *  if the value is not a Boolean or the String "true" or "false".
	 */
	public boolean getBoolean(String key)  {
		Object object = this.get(key);
		if (object == null || object.equals(Boolean.FALSE) ||
				(object instanceof String &&
						((String)object).equalsIgnoreCase("false"))) {
			return false;
		} else if (object.equals(Boolean.TRUE) ||
				(object instanceof String &&
						((String)object).equalsIgnoreCase("true"))) {
			return true;
		}
		throw new RuntimeException("Entity["+EntityUtil.quote(key)+"] is not a Boolean.");
	}


	/**
	 * Get the double value associated with a key.
	 * @param key   A key string.
	 * @return      The numeric value.
	 * @throws RuntimeException if the key is not found or
	 *  if the value is not a Number object and cannot be converted to a number.
	 */
	public double getDouble(String key) {
		Object object = this.get(key);
		try {
			return object instanceof Number
					? ((Number)object).doubleValue()
							: Double.parseDouble((String)object);
		} catch (Exception e) {
			throw new RuntimeException("Entity["+EntityUtil.quote(key)+"] is not a number.");
		}
	}


	/**
	 * Get the int value associated with a key.
	 *
	 * @param key   A key string.
	 * @return      The integer value.
	 * @throws   RuntimeException if the key is not found or if the value cannot
	 *  be converted to an integer.
	 */
	public int getInt(String key)  {
		Object object = this.get(key);
		try {
			return object instanceof Number
					? ((Number)object).intValue()
							: Integer.parseInt((String)object);
		} catch (Exception e) {
			throw new RuntimeException("Entity["+EntityUtil.quote(key)+"] is not an int.");
		}
	}

	/**
	 * Get the long value associated with a key.
	 *
	 * @param key   A key string.
	 * @return      The long value.
	 * @throws   RuntimeException if the key is not found or if the value cannot
	 *  be converted to a long.
	 */
	public long getLong(String key)  {
		Object object = this.get(key);
		try {
			return object instanceof Number
					? ((Number)object).longValue()
							: Long.parseLong((String)object);
		} catch (Exception e) {
			throw new RuntimeException("Entity["+EntityUtil.quote(key)+"] is not a long.");
		}
	}


	/**
	 * Get an array of field names from a Entity.
	 *
	 * @return An array of field names, or null if there are no names.
	 */
	public static String[] getNames(Entity jo) {
		int length = jo.size();
		if (length == 0) {
			return null;
		}
		Iterator<String> keys = jo.keys();
		String[] names = new String[length];
		int i = 0;
		while (keys.hasNext()) {
			names[i] = keys.next();
			i += 1;
		}
		return names;
	}

	/**
	 * Get the string associated with a key.
	 *
	 * @param key   A key string.
	 * @return      A string which is the value.
	 * @throws   RuntimeException if there is no string value for the key.
	 */
	public String getString(String key) {
		Object object = this.get(key);
		if (object instanceof String) {
			return (String)object;
		}else if (object instanceof Entity) {
			return object.toString();
		}
		throw new RuntimeException("Entity["+EntityUtil.quote(key)+"] not a string.");
	}
	/**
	 * Get the string associated with a key.
	 *
	 * @param key   		A key string.
	 * @param defaultValue  A defaultValue string.
	 * @return      		A string which is the value or defaultValue
	 */
	public String getString(String key, String defaultValue) {
		Object object = this.get(key);
		if (object instanceof String) {
			return (String)object;
		}else if (object instanceof Entity) {
			return object.toString();
		}
		return defaultValue;
	}

	/**
	 * Determine if the Entity contains a specific key.
	 * @param key   A key string.
	 * @return      true if the key exists in the Entity.
	 */
	public boolean has(String key) {
		return getMap().containsKey(key);
	}


	/**
	 * Increment a property of a Entity. If there is no such property,
	 * create one with a value of 1. If there is such a property, and if
	 * it is an Integer, Long, Double, or Float, then add one to it.
	 * @param key  A key string.
	 * @return this.
	 * @throws RuntimeException If there is already a property with this name
	 * that is not an Integer, Long, Double, or Float.
	 */
	public Entity increment(String key) {
		Object value = this.get(key);
		if (value == null) {
			this.put(key, 1);
		} else if (value instanceof Integer) {
			this.put(key, ((Integer)value).intValue() + 1);
		} else if (value instanceof Long) {
			this.put(key, ((Long)value).longValue() + 1);
		} else if (value instanceof Double) {
			this.put(key, ((Double)value).doubleValue() + 1);
		} else if (value instanceof Float) {
			this.put(key, ((Float)value).floatValue() + 1);
		} else {
			throw new RuntimeException("Unable to increment [" + EntityUtil.quote(key) + "].");
		}
		return this;
	}

	/**
	 * Get an enumeration of the keys of the Entity.
	 *
	 * @return An iterator of the keys.
	 */
	public Iterator<String> keys() {
		return getMap().keySet().iterator();
	}

	/**
	 * Get the number of keys stored in the Entity.
	 *
	 * @return The number of keys in the Entity.
	 */
	public int size() {
		if(this.map==null){
			return 0;
		}
		return this.map.size();
	}

	/**
	 * Produce a EntityList containing the names of the elements of this
	 * Entity.
	 * @return A EntityList containing the key strings, or null if the Entity
	 * is empty.
	 */
	public EntityList names() {
		EntityList ja = getNewArray();
		Iterator<String> keys = this.keys();
		while (keys.hasNext()) {
			ja.put(keys.next());
		}
		return ja.size() == 0 ? null : ja;
	}

	

	/**
	 * Put a key/boolean pair in the Entity.
	 *
	 * @param key   A key string.
	 * @param value A boolean which is the value.
	 * @return this.
	 */
	public Entity put(String key, boolean value) {
		this.put(key, value ? Boolean.TRUE : Boolean.FALSE);
		return this;
	}


	/**
	 * Put a key/value pair in the Entity, where the value will be a
	 * EntityList which is produced from a Collection.
	 * @param key   A key string.
	 * @param value A Collection value.
	 * @return      this.
	 */
	public Entity put(String key, Collection<?> value) {
		put(key, (Object)getNewArray().initWithMap(value));
		return this;
	}


	/**
	 * Put a key/double pair in the Entity.
	 *
	 * @param key   A key string.
	 * @param value A double which is the value.
	 * @return this.
	 */
	public Entity put(String key, double value) {
		this.put(key, new Double(value));
		return this;
	}


	/**
	 * Put a key/int pair in the Entity.
	 *
	 * @param key   A key string.
	 * @param value An int which is the value.
	 * @return this.
	 */
	public Entity put(String key, int value)  {
		this.put(key, new Integer(value));
		return this;
	}


	/**
	 * Put a key/long pair in the Entity.
	 *
	 * @param key   A key string.
	 * @param value A long which is the value.
	 * @return this.
	 */
	public Entity put(String key, long value)  {
		this.put(key, new Long(value));
		return this;
	}


	/**
	 * Put a key/value pair in the Entity, where the value will be a
	 * Entity which is produced from a Map.
	 * @param key   A key string.
	 * @param value A Map value.
	 * @return      this.
	 */
	public Entity put(String key, Map<String, Object> value)  {
		this.put(key, getNewObject().initWithMap(value));
		return this;
	}


	/**
	 * Put a key/value pair in the Entity. If the value is null,
	 * then the key will be removed from the Entity if it is present.
	 * @param key   A key string.
	 * @param value An object which is the value. It should be of one of these
	 *  types: Boolean, Double, Integer, EntityList, Entity, Long or String object.
	 * @return this.
	 */
	public Entity put(String key, Object value) {
		if (key == null) {
			throw new RuntimeException("Null key.");
		}
		if (value != null) {
			EntityUtil.testValidity(value);
			getMap().put(key, value);
		} else {
			this.remove(key);
		}
		return this;
	}

	/**
	 * Remove a name and its value, if present.
	 * @param key The name to be removed.
	 * @return The value that was associated with the name,
	 * or null if there was no value.
	 */
	public Object remove(String key) {
		return getMap().remove(key);
	}

	/**
	 * Make a Text of this Entity. For compactness, no whitespace
	 * is added. If this would not result in a syntactically correct Text,
	 * then null will be returned instead.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 *
	 * @return a printable, displayable, portable, transmittable
	 *  representation of the object, beginning
	 *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
	 *  with <code>}</code>&nbsp;<small>(right brace)</small>.
	 */
	@Override
	public abstract String toString();
	/**
	 * Make a prettyprinted Text of this Entity.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 * @param indentFactor The number of spaces to add to each level of
	 *  indentation.
	 * @return a printable, displayable, portable, transmittable
	 *  representation of the object, beginning
	 *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
	 *  with <code>}</code>&nbsp;<small>(right brace)</small>.
	 */
	public abstract String toString(int indentFactor);
	public abstract String toString(int indentFactor, int intent);

	public Object getValue(String key){
		int len=0;
		int end=0;
		int id=0;
    	for(;len<key.length();len++){
    		char temp=key.charAt(len);
    		if(temp=='['){
    			for(end=len+1;end<key.length();end++){
    				temp=key.charAt(end);
    				if(key.charAt(end)==']'){
    					end++;
    					break;
    				}else if(temp>47&&temp<58&&id>=0){
   						id=id*10+temp-48;
    				}else if(temp=='L'){
    					id=-2;
    				}
    			}
    			if(end==key.length()){
    				end=0;
    			}
    			break;
    		}else if(temp=='.'){
    			end=len;
    			id=-1;
    			break;
    		}
    	}
    	if(end==0&&len==key.length()){
    		id=-1;
    	}
    	
    	Object child = get(key.substring(0, len));
    	if(child!=null){
	    	if (end==0) {
	    		if(id>=0||id==-2){
	    			if(child instanceof EntityList){
	    				EntityList list=(EntityList) child;
	    				if(id==-2){
	    					id=list.size()-1;
	    				}
	    				if(list.size()>=id){
	    					return list.get(id);
	    				}
	    			}
	    		}else{
	    			return child;
	    		}
			}else{
				if(id>=0||id==-2){
					if(child instanceof EntityList){
				    	if(end==len+2){
				    		// Get List
				    		EntityList result = getNewArray();
				    		EntityList items=(EntityList) child;
				    		for(int z=0;z<items.size();z++){
				    			result.add(((Entity)items.get(z)).getValue(key.substring(end+1)));
				    		}
				    		return result;
				    	}

						EntityList list=(EntityList) child;
	    				if(id==-2){
	    					id=list.size()-1;
	    				}
	    				if(list.size()>=id){
	    					return ((Entity)list.get(id)).getValue(key.substring(end+1));
	    				}
					}
				}else{
					return ((Entity)child).getValue(key.substring(end+1));
				}
			}
    	}
    	return null;
	}
	
	public void setValue(String key, Object value){
		int len=0;
		int end=0;
		int id=0;
    	for(;len<key.length();len++){
    		char temp=key.charAt(len);
    		if(temp=='['){
    			for(end=len+1;end<key.length();end++){
    				temp=key.charAt(end);
    				if(key.charAt(end)==']'){
    					end++;
    					break;
    				}else if(temp>47&&temp<58&&id>=0){
   						id=id*10+temp-48;
    				}else if(temp=='L'){
    					id=-2;
    				}
    			}
    			if(end==key.length()){
    				end=0;
    			}
    			break;
    		}else if(temp=='.'){
    			end=len;
    			id=-1;
    			break;
    		}
    	}
    	if(end==0&&len==key.length()){
    		id=-1;
    	}

    	Object child = get(key.substring(0, len));
    	if(child!=null){
	    	if (end==0) {
	    		if(id>=0||id==-2){
	    			if(child instanceof EntityList){
	    				EntityList list=(EntityList) child;
	    				if(id==-2){
	    					id=list.size()-1;
	    				}
	    				if(list.size()>=id){
	    					if(value==null){
	    						list.remove(id);
	    					}else{
	    						list.set(id, value);
	    					}
	    				}
	    			}
	    		}else{
	    			if(value==null){
	    				remove(key.substring(0, len));
	    			}else{
	    				put(key.substring(0, len), value);
	    			}
	    		}
			}else{
				if(id>=0||id==-2){
					if(child instanceof EntityList){
						EntityList list=(EntityList) child;
	    				if(id==-2){
	    					id=list.size()-1;
	    				}
	    				if(list.size()>=id){
	    					((Entity)list.get(id)).setValue(key.substring(end+1), value);
	    				}
					}
				}else{
					((Entity)child).setValue(key.substring(end+1), value);
				}
			}
    	}
	}
}
