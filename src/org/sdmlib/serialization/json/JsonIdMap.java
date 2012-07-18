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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.IdMapFilter;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.UpdateListener;
import org.sdmlib.serialization.event.creater.DateCreator;
import org.sdmlib.serialization.interfaces.MapUpdateListener;
import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

/**
 * The Class JsonIdMap.
 */
public class JsonIdMap extends IdMap{
	/** The Constant CLASS. */
	public static final String CLASS = "class";

	/** The Constant VALUE. */
	public static final String VALUE = "value";

	/** The Constant JSON_ID. */
	public static final String JSON_ID = "id";

	/** The Constant JSON_PROPS. */
	public static final String JSON_PROPS = "prop";

	/** The Constant REF_SUFFIX. */
	public static final String REF_SUFFIX = "_ref";

	/** The Constant REF_SUFFIX. */
	public static final String NOT_SUFFIX = "_not";

	/** The Constant MAINITEM. */
	public static final String MAINITEM = "main";

	/** The updatelistener. */
	private MapUpdateListener updatelistener;

	
	/** If this is true the IdMap save the Typ of primary datatypes. */
	private boolean typSave;
	/**
	 * Instantiates a new json id map.
	 */
	public JsonIdMap() {
		super();
		this.addCreator(new DateCreator());
	}

	/**
	 * To json object.
	 *
	 * @param object the object
	 * @return the json object
	 */
	public JsonObject toJsonObject(Object object) {
		return toJsonObject(object, new JsonFilter());
	}

	/**
	 * To json object.
	 *
	 * @param entity the entity
	 * @param filter the filter
	 * @return the json object
	 */
	public JsonObject toJsonObject(Object entity, JsonFilter filter) {
		String id = "";
		String className = entity.getClass().getName();

		SendableEntityCreator prototyp = getCreatorClasses(className);
		if (prototyp == null) {
			return null;
		}

		if (!(prototyp instanceof NoIndexCreator)) {
			id = getId(entity);
		}
		JsonObject jsonProp = new JsonObject();

		String[] properties = prototyp.getProperties();
		filter.addObject(id);
		Object referenceObject = prototyp.getSendableInstance(true);
		
		if (properties != null) {
			boolean typSave= !(prototyp instanceof NoIndexCreator)&&isTypSave();
			for (String property : properties) {
				if (jsonProp.has(property)) {
					throw new RuntimeException("Property duplicate:" + property
							+ "(" + className + ")");
				}
				Object value = prototyp.getValue(entity, property);
				if (value != null) {
					boolean encoding = isSimpleCheck();
					if (!encoding) {
						Object refValue = prototyp.getValue(referenceObject,
								property);
						encoding = !value.equals(refValue);
					}
					if (encoding) {
						if(property.endsWith(NOT_SUFFIX)){
							continue;
						}
						if(!filter.isRegard(this, entity, property, value)){
							continue;
						}
						boolean aggregation = filter.isConvertable(this,
								entity, property, value);
						
						if (value instanceof Collection<?>) {
							JsonArray subValues = new JsonArray();
							for (Object containee : ((Collection<?>) value)) {
								if (containee != null) {
									subValues.put(parseObject(containee,
											aggregation, filter, null, typSave));
								}
							}
							jsonProp.put(property, subValues);
						} else {
							jsonProp.put(
									property,
									parseObject(value, aggregation, filter,
											null,typSave));
						}
					}
				}
			}
		}
		JsonObject jsonObject = new JsonObject();
		if (prototyp instanceof NoIndexCreator) {
			Iterator<String> keys = jsonProp.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				jsonObject.put(key, jsonProp.get(key));
			}
			jsonObject.put(CLASS, className);
		} else {
			if (isId&&filter.isId()) {
				jsonObject.put(JSON_ID, id);
			}
			jsonObject.put(CLASS, className);

			if (jsonProp.size() > 0) {
				jsonObject.put(JSON_PROPS, jsonProp);
			}
		}
		return jsonObject;
	}

	/**
	 * Read json.
	 *
	 * @param jsonArray the json array
	 * @return the object
	 */
	public Object readJson(JsonArray jsonArray) {
		Object result = null;
		int len = jsonArray.size() - 1;
		// Add all Objects
		LinkedHashSet<ReferenceObject> refs = new LinkedHashSet<ReferenceObject>();
		for (int i = 0; i <= len; i++) {
			JsonObject kidObject = jsonArray.getJSONObject(i);
			Object tmp = readJson(kidObject, refs);
			if (kidObject.has(MAINITEM)) {
				result = tmp;
			} else if (i == 0) {
				result = tmp;
			}
		}
		for (ReferenceObject ref : refs) {
			ref.execute();
		}
		return result;
	}

	/**
	 * Read json.
	 *
	 * @param jsonObject the json object
	 * @return the object
	 */
	public Object readJson(JsonObject jsonObject) {
		LinkedHashSet<ReferenceObject> refs = new LinkedHashSet<ReferenceObject>();
		Object mainItem = readJson(jsonObject, refs);
		for (ReferenceObject ref : refs) {
			ref.execute();
		}
		return mainItem;
	}

	/**
	 * Read json.
	 *
	 * @param target the target
	 * @param jsonObject the json object
	 * @return the object
	 */
	public Object readJson(Object target, JsonObject jsonObject) {
		LinkedHashSet<ReferenceObject> refs = new LinkedHashSet<ReferenceObject>();
		Object mainItem = readJson(target, jsonObject, refs);
		for (ReferenceObject ref : refs) {
			ref.execute();
		}
		return mainItem;
	}

	/**
	 * Read json.
	 *
	 * @param jsonObject the json object
	 * @param refs the refs
	 * @return the object
	 */
	private Object readJson(JsonObject jsonObject,
			LinkedHashSet<ReferenceObject> refs) {
		Object result = null;
		Object className = jsonObject.get(CLASS);

		SendableEntityCreator typeInfo = getCreatorClasses((String) className);

		if (typeInfo != null) {
			if (isId) {
				String jsonId = (String) jsonObject.get(JSON_ID);
				if (jsonId != null) {
					result = getObject(jsonId);
				}
			}
			if (result == null) {
				result = typeInfo.getSendableInstance(false);
				sendReceiveMsg(MapUpdateListener.TYP_NEW, result, jsonObject);
			}else{
				sendReceiveMsg(MapUpdateListener.TYP_UPDATE, result, jsonObject);
			}
			if(typeInfo instanceof NoIndexCreator){
				String[] properties = typeInfo.getProperties();
				if (properties != null) {
					for (String property : properties) {
						Object obj = jsonObject.get(property);
						parseValue(result, property, obj, typeInfo, refs);
					}
				}
			}else{
				readJson(result, jsonObject, refs);
			}
		}else if(jsonObject.get(VALUE)!=null){
			return jsonObject.get(VALUE);
		}
		return result;
	}

	/**
	 * Read json.
	 *
	 * @param target the target
	 * @param jsonObject the json object
	 * @param refs the refs
	 * @return the object
	 */
	protected Object readJson(Object target, JsonObject jsonObject,
			LinkedHashSet<ReferenceObject> refs) {
		// JSONArray jsonArray;
		if (isId) {
			String jsonId = (String) jsonObject.get(JSON_ID);
			if (jsonId == null) {
				return target;
			}
			put(jsonId, target);

			getCounter().readId(jsonId);
		}
		if (jsonObject.has(JSON_PROPS)) {
			JsonObject jsonProp = (JsonObject) jsonObject.get(JSON_PROPS);
			SendableEntityCreator prototyp = getCreatorClass(target);
			String[] properties = prototyp.getProperties();
			if (properties != null) {
				for (String property : properties) {
					Object obj = jsonProp.get(property);
					parseValue(target, property, obj, prototyp, refs);
				}
			}
		}
		return target;
	}

	/**
	 * Parses the value.
	 *
	 * @param target the target
	 * @param property the property
	 * @param value the value
	 * @param creator the creator
	 * @param refs the refs
	 */
	protected void parseValue(Object target, String property, Object value,
			SendableEntityCreator creator, LinkedHashSet<ReferenceObject> refs) {
		if (value != null) {
			if (value instanceof JsonArray) {
				JsonArray jsonArray = (JsonArray) value;
				for (int i = 0; i < jsonArray.size(); i++) {
					Object kid = jsonArray.get(i);
					if (kid instanceof JsonObject) {
						// got a new kid, create it
						JsonObject child = (JsonObject) kid;
						String className = (String) child.get(CLASS);
						String jsonId = (String) child.get(JSON_ID);
						if (className == null && jsonId != null) {
							// It is a Ref
							refs.add(new ReferenceObject(jsonId, creator,
									property, this, target));
						} else {
							creator.setValue(target, property,
									readJson((JsonObject) kid));
						}
					} else {
						creator.setValue(target, property, kid);
					}
				}
			} else {
				if (value instanceof JsonObject) {
					// // got a new kid, create it
					JsonObject child = (JsonObject) value;
					String className = (String) child.get(CLASS);
					String jsonId = (String) child.get(JSON_ID);
					if (className == null && jsonId != null) {
						// It is a Ref
						refs.add(new ReferenceObject(jsonId, creator, property,
								this, target));
					}else{
						creator.setValue(target, property,
								readJson(child));
					}
				} else {
					creator.setValue(target, property, value);
				}
			}
		}
	}

	/**
	 * To json array.
	 *
	 * @param object the object
	 * @return the json array
	 */
	public JsonArray toJsonArray(Object object) {
		return toJsonArray(object, null);
	}

	/**
	 * To json array.
	 *
	 * @param object the object
	 * @param filter the filter
	 * @return the json array
	 */
	public JsonArray toJsonArray(Object object, JsonFilter filter) {
		JsonArray jsonArray = new JsonArray();
		if (filter == null) {
			filter = new JsonFilter();
		}
		toJsonArray(object, jsonArray, filter);
		return jsonArray;
	}

	/**
	 * To json sorted array.
	 *
	 * @param object the object
	 * @param property the property
	 * @return the json array
	 */
	public JsonArray toJsonSortedArray(Object object, String property) {
		JsonSortedArray jsonArray = new JsonSortedArray();
		jsonArray.setSortProp(property);
		toJsonArray(object, jsonArray, new JsonFilter());
		return jsonArray;
	}

	/**
	 * To json array.
	 *
	 * @param entity the entity
	 * @param jsonArray the json array
	 * @param filter the filter
	 * @return the json array
	 */
	public JsonArray toJsonArray(Object entity, JsonArray jsonArray,
			JsonFilter filter) {
		String className = entity.getClass().getName();
		String id = getId(entity);

		JsonObject jsonObject = new JsonObject();
		if (isId&&filter.isId()) {
			jsonObject.put(JSON_ID, id);
		}
		jsonObject.put(CLASS, className);
		jsonArray.put(jsonObject);

		SendableEntityCreator prototyp = getCreatorClasses(className);
		if (prototyp == null) {
			throw new RuntimeException("No Creator exist for " + className);
		}
		String[] properties = prototyp.getProperties();
		filter.addObject(id);

		if (properties != null) {
			JsonObject jsonProps = new JsonObject();
			for (String property : properties) {
				Object value = prototyp.getValue(entity, property);
				if (value != null) {
					if (value instanceof Collection) {
						Collection<?> list = ((Collection<?>) value);
						if (list.size() > 0) {
							JsonArray refArray = new JsonArray();
							for (Object containee : list) {

								if (containee != null && filter.isRegard(this, entity, property, containee)) {
									boolean aggregation = filter.isConvertable(this, entity,
											property, containee);
									refArray.put(parseObject(containee,
											aggregation, filter, jsonArray, isTypSave()));
								}
							}
							if(refArray.size()>0){
								jsonProps.put(property, refArray);
							}
						}
					} else if (filter.isRegard(this, entity, property, value)){
						boolean aggregation = filter.isConvertable(this, entity,
								property, value);
						jsonProps.put(
								property,
								parseObject(value, aggregation, filter,
										jsonArray, isTypSave()));
					}
				}
			}
			if (jsonProps.size() > 0) {
				jsonObject.put(JSON_PROPS, jsonProps);
			}
		}
		return jsonArray;
	}

	/**
	 * Parses the object.
	 *
	 * @param entity the entity
	 * @param aggregation the aggregation
	 * @param filter the filter
	 * @param jsonArray the json array
	 * @return the object
	 */
	private Object parseObject(Object entity, boolean aggregation,
			JsonFilter filter, JsonArray jsonArray, boolean typSave) {
		SendableEntityCreator valueCreater = getCreatorClass(entity);
		if (valueCreater != null) {
			if (aggregation) {
				String subId = this.getKey(entity);
				if (valueCreater instanceof NoIndexCreator
						|| !filter.existsObject(subId)) {
					int oldValue = filter.setDeep(IdMapFilter.DEEPER);
					if (jsonArray == null) {
						JsonObject result = toJsonObject(entity, filter);
						filter.setDeep(oldValue);
						return result;
					}
					this.toJsonArray(entity, jsonArray, filter);
					filter.setDeep(oldValue);
				}
			}
			return new JsonObject(JSON_ID, getId(entity));
		}
		if(typSave){
			JsonObject returnValue=new JsonObject(CLASS, entity.getClass().getName());
			returnValue.put(VALUE, entity);
			return returnValue;
		}
		return entity;
	}

	/**
	 * Sets the update msg listener.
	 *
	 * @param listener the new update msg listener
	 */
	public void setUpdateMsgListener(MapUpdateListener listener) {
		this.updatelistener = listener;
	}

	/**
	 * Send update msg.
	 *
	 * @param jsonObject the json object
	 * @return true, if successful
	 */
	public boolean sendUpdateMsg(Object oldObj, Object newObject, JsonObject jsonObject) {
		if (this.updatelistener != null) {
			return this.updatelistener.sendUpdateMsg(oldObj, newObject, jsonObject);
		}
		return true;
	}
	
	public boolean sendReceiveMsg(String type, Object value, JsonObject props){
		if (this.updatelistener != null) {
			return this.updatelistener.readMessages(type, value, props);
		}
		return true;
	}

	/**
	 * To json object by id.
	 *
	 * @param id the id
	 * @return the json object
	 */
	public JsonObject toJsonObjectById(String id) {
		return toJsonObject(super.getObject(id), new JsonFilter(0));
	}

	/**
	 * To json array by ids.
	 *
	 * @param suspendIdList the suspend id list
	 */
	public void toJsonArrayByIds(ArrayList<String> suspendIdList) {
		JsonArray children = new JsonArray();
		for (String childId : suspendIdList) {
			children.put(toJsonObjectById(childId));
		}
		JsonObject sendObj = new JsonObject();
		sendObj.put(IdMap.UPDATE, children);
		sendUpdateMsg(null, null, sendObj);
	}

	/**
	 * To json array.
	 *
	 * @param items the items
	 * @return the json array
	 */
	public JsonArray toJsonArray(List<? extends Object> items) {
		JsonArray jsonArray = new JsonArray();
		for (Object item : items) {
			jsonArray.put(toJsonObject(item));
		}
		return jsonArray;
	}

	/**
	 * Execute update msg.
	 *
	 * @param element the element
	 * @return true, if successful
	 */
	public boolean executeUpdateMsg(JsonObject element) {
		if (this.updateListener == null) {
			this.updateListener = new UpdateListener(this);
		}
		return this.updateListener.execute(element);
	}

	/* (non-Javadoc)
	 * @see de.uni.kassel.peermessage.IdMap#garbageCollection(java.util.Set)
	 */
	public void garbageCollection(Set<String> classCounts) {
		Set<String> allIds = this.values.keySet();
		for (String id : allIds) {
			if (!classCounts.contains(id)) {
				remove(getObject(id));
			}
		}
	}
	
	/**
	 * Gets the keys.
	 *
	 * @return the keys
	 */
	public Set<String> getKeys() {
		return values.keySet();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.getClass().getName()+" ("+this.size()+")";
	}

	/**
	 * Checks if is typ save.
	 *
	 * @return true, if is typ save
	 */
	public boolean isTypSave() {
		return typSave;
	}

	/**
	 * Sets the typ save.
	 *
	 * @param typSave the new typ save
	 */
	public void setTypSave(boolean typSave) {
		this.typSave = typSave;
	}
	
	public ArrayList<Object> getTypList(SendableEntityCreator creator){
		if(creator==null){
			return null;
		}
		ArrayList<Object> result=new ArrayList<Object>();
		String clazzName = creator.getSendableInstance(true).getClass().getName();
		for(Object obj : this.values.values()){
			if(obj!=null){
				if(obj.getClass().getName().equals(clazzName)){
					result.add(obj);
				}
			}
		}
		return result;
	}
	public boolean replaceObject(Object newObject){
		String key = getKey(newObject);
		if(key!=null){
			return false;
		}
		if(!(newObject instanceof Comparable<?>)){
			return false;
		}
		SendableEntityCreator creator=getCreatorClass(newObject);
		if(creator==null){
			return false;
		}
		boolean result=false;
		ArrayList<Object> oldValues = getTypList(creator);
		for(Object obj : oldValues){
			@SuppressWarnings("unchecked")
			Comparable<Object> oldValue=(Comparable<Object>) obj;
			if(oldValue.compareTo(newObject)==0){
				String oldKey=getKey(oldValue);
				if(oldKey!=null){
					remove(oldValue);
					put(oldKey, newObject);
				}
			}
		}
		return result;
	}
}