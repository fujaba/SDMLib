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
public abstract class JsonIdMap extends IdMap{
	
	/** The Constant CLASS. */
	public static final String CLASS = "class";
	
	/** The Constant JSON_ID. */
	public static final String JSON_ID = "id";
	
	/** The Constant JSON_PROPS. */
	public static final String JSON_PROPS = "prop";
	
	/** The Constant REF_SUFFIX. */
	public static final String REF_SUFFIX = "_ref";
	
	/** The Constant MAINITEM. */
	public static final String MAINITEM = "main";
	
	/** The updatelistener. */
	private MapUpdateListener updatelistener;

	/**
	 * Instantiates a new json id map.
	 */
	public JsonIdMap() {
		super();
		this.addCreator(new DateCreator());
	}

	public JsonObject toJsonObject(Object object) {
		return toJsonObject(object,  new JsonFilter());
	}

	public JsonObject toJsonObject(Object entity, JsonFilter filter) {
		String id="";
		String className = entity.getClass().getName();

		SendableEntityCreator prototyp = getCreatorClasses(className);
		if(prototyp==null){
			return null;
		}
		
		if(!(prototyp instanceof NoIndexCreator)){
			id=getId(entity);	
		}
		JsonObject jsonProp = new JsonObject();

		String[] properties = prototyp.getProperties();
		filter.addObject(id);
		Object referenceObject = prototyp.getSendableInstance(true);
		if (properties != null) {
			for (String property : properties) {
				if(jsonProp.has(property)){
					throw new RuntimeException("Property duplicate:"+property+"("+className+")");
				}
				Object value = prototyp.getValue(entity, property);
				if (value != null) {
					boolean encoding=isSimpleCheck();
					if(!encoding){
						Object refValue = prototyp.getValue(referenceObject,
								property);
						encoding=!value.equals(refValue);
					}
					if(encoding){
						boolean aggregation = filter.isConvertable(this, entity, property, value);
						if (value instanceof Collection<?>) {
							JsonArray subValues = new JsonArray();
							for (Object containee : ((Collection<?>) value)) {
								if(containee!=null){
									subValues.put(parseObject(containee, aggregation, filter, null));
								}
							}
							jsonProp.put(property, subValues);
						} else {
							jsonProp.put(property, parseObject(value, aggregation, filter, null));
						}
					}
				}
			}
		}
		JsonObject jsonObject = new JsonObject();
		if(prototyp instanceof NoIndexCreator){
			Iterator<String> keys = jsonProp.keys();
			while(keys.hasNext()){
				String key=keys.next();
				jsonObject.put(key, jsonProp.get(key));
			}
			jsonObject.put(CLASS, className);
		}else{
			if (isId) {
				jsonObject.put(JSON_ID, id);
			}
			jsonObject.put(CLASS, className);
	
			if (jsonProp.length() > 0) {
				jsonObject.put(JSON_PROPS, jsonProp);
			}
		}
		return jsonObject;
	}
	
	public Object readJson(JsonArray jsonArray) {
		Object result = null;
		int len = jsonArray.length() - 1;
		// Add all Objects
		LinkedHashSet<ReferenceObject> refs=new LinkedHashSet<ReferenceObject>();
		for (int i = 0; i <= len; i++) {
			JsonObject kidObject = jsonArray.getJSONObject(i);
			Object tmp = readJson(kidObject, refs);
			if(kidObject.has(MAINITEM)){
				result = tmp;
			}else if(i == 0) {
				result = tmp;
			}
		}
		for(ReferenceObject ref : refs){
			ref.execute();
		}
		return result;
	}

	public Object readJson(JsonObject jsonObject) {
		LinkedHashSet<ReferenceObject> refs=new LinkedHashSet<ReferenceObject>();
		Object mainItem=readJson(jsonObject, refs);
		for(ReferenceObject ref : refs){
			ref.execute();
		}
		return mainItem;
	}
	public Object readJson(Object target, JsonObject jsonObject){
		LinkedHashSet<ReferenceObject> refs=new LinkedHashSet<ReferenceObject>();
		Object mainItem=readJson(target, jsonObject, refs);
		for(ReferenceObject ref : refs){
			ref.execute();
		}
		return mainItem;
	}
	private Object readJson(JsonObject jsonObject, LinkedHashSet<ReferenceObject> refs) {
		Object result = null;
		Object className = jsonObject.get(CLASS);
	
		SendableEntityCreator typeInfo = getCreatorClasses((String) className);

		if (typeInfo != null) {
			if (isId) {
				String jsonId = (String) jsonObject.get(JSON_ID);
				if (jsonId == null) {
					return null;
				}
				result = getObject(jsonId);
			}
			if (result == null) {
				result = typeInfo.getSendableInstance(false);
			}
			readJson(result, jsonObject, refs);
		}
		return result;
	}

	protected Object readJson(Object target, JsonObject jsonObject, LinkedHashSet<ReferenceObject> refs) {
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

	protected void parseValue(Object target, String property, Object value,
			SendableEntityCreator creator, LinkedHashSet<ReferenceObject> refs) {
		if (value != null) {
			if (value instanceof JsonArray) {
				JsonArray jsonArray = (JsonArray) value;
				for (int i = 0; i < jsonArray.length(); i++) {
					Object kid = jsonArray.get(i);
					if (kid instanceof JsonObject) {
						// got a new kid, create it
						JsonObject child=(JsonObject) kid;
						String className = (String) child.get(CLASS);
						String jsonId = (String) child.get(JSON_ID);
						if (className == null&&jsonId!=null) {
							// It is a Ref
							refs.add(new ReferenceObject(jsonId, creator, property, this, target));
						}else{
							creator.setValue(target, property, readJson((JsonObject) kid));
						}
					}else{
						creator.setValue(target, property, kid);
					}
				}
			} else {
				if (value instanceof JsonObject) {
//					// got a new kid, create it
					JsonObject child=(JsonObject) value;
					String className = (String) child.get(CLASS);
					String jsonId = (String) child.get(JSON_ID);
					if (className == null&&jsonId!=null) {
						// It is a Ref
						refs.add(new ReferenceObject(jsonId, creator, property, this, target));
					}else{
						creator.setValue(target, property, readJson((JsonObject) value));
					}
				}else{
					creator.setValue(target, property, value);
				}
			}
		}
	}

	public JsonArray toJsonArray(Object object) {
		return toJsonArray(object, null);
	}

	public JsonArray toJsonArray(Object object, JsonFilter filter) {
		JsonArray jsonArray = new JsonArray();
		if (filter == null) {
			filter = new JsonFilter();
		}
		toJsonArray(object, jsonArray, filter);
		return jsonArray;
	}

	public JsonArray toJsonSortedArray(Object object, String property) {
		JsonSortedArray jsonArray = new JsonSortedArray();
		jsonArray.setSortProp(property);
		toJsonArray(object, jsonArray, new JsonFilter());
		return jsonArray;
	}
	
	
	public JsonArray toJsonArray(Object entity, JsonArray jsonArray, 
			JsonFilter filter) {
		String className = entity.getClass().getName();
		String id=getId(entity);

		JsonObject jsonObject = new JsonObject();
		if (isId) {
			jsonObject.put(JSON_ID, id);
		}
		jsonObject.put(CLASS, className);
		jsonArray.put(jsonObject);

		SendableEntityCreator prototyp = getCreatorClasses(className);
		if(prototyp==null){
			throw new RuntimeException("No Creator exist for "+className);
		}
		String[] properties = prototyp.getProperties();
		filter.addObject(id);

		if (properties != null) {
			JsonObject jsonProps = new JsonObject();
			for (String property : properties) {
				Object value = prototyp.getValue(entity, property);
				if (value != null) {
					boolean aggregation = filter.isConvertable(this, entity, property, value);
					if (value instanceof Collection) {
						Collection<?> list = ((Collection<?>) value);
						if (list.size() > 0) {
							JsonArray refArray = new JsonArray();
							for (Object containee : list) {
								if (containee != null) {
									refArray.put(parseObject(containee, aggregation, filter, jsonArray));
								}
							}
							jsonProps.put(property, refArray);
						}
					} else {
						jsonProps.put(property, parseObject(value, aggregation, filter, jsonArray));
					}
				}
			}
			if (jsonProps.length() > 0) {
				jsonObject.put(JSON_PROPS, jsonProps);
			}
		}
		return jsonArray;
	}

	private Object parseObject(Object entity, boolean aggregation, JsonFilter filter, JsonArray jsonArray){
		SendableEntityCreator valueCreater = getCreatorClass(entity);
		if (valueCreater != null) {
			if (aggregation) {
				String subId = this.getKey(entity);
				if(valueCreater instanceof NoIndexCreator||!filter.existsObject(subId)){
					int oldValue = filter.setDeep(IdMapFilter.DEEPER);
					if(jsonArray==null){
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
		return entity;
	}

	public void setUpdateMsgListener(MapUpdateListener listener){
		this.updatelistener=listener;
	}

	public boolean sendUpdateMsg(JsonObject jsonObject) {
		if(this.updatelistener!=null){
			return this.updatelistener.sendUpdateMsg(jsonObject);	
		}
		return true;
	}
	
	public JsonObject toJsonObjectById(String id){
		return toJsonObject(super.getObject(id), new JsonFilter(0));
	}

	public void toJsonArrayByIds(ArrayList<String> suspendIdList) {
		JsonArray children=new JsonArray();
		for(String childId : suspendIdList){
			children.put(toJsonObjectById(childId));
		}
		JsonObject sendObj=new JsonObject();
		sendObj.put(IdMap.UPDATE, children);
		sendUpdateMsg(sendObj);
	}
	public JsonArray toJsonArray(List<Object> items){
		JsonArray jsonArray=new JsonArray();
		for(Object item : items){
			jsonArray.put(toJsonObject(item));
		}
		return jsonArray;
	}
	public boolean executeUpdateMsg(JsonObject element){
		if (this.updateListener == null) {
			this.updateListener = new UpdateListener(this);
		}
		return this.updateListener.execute(element);
	}

	public void garbageCollection(LinkedHashSet<String> classCounts) {
		Set<String> allIds = this.values.keySet();
		for(String id : allIds){
			if(!classCounts.contains(id)){
				remove(getObject(id));
			}
		}
	}
}
