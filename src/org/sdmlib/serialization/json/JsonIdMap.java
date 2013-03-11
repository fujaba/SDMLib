/*
   Copyright (c) 2013 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.serialization.json;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.IdMapFilter;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.event.MapEntry;
import org.sdmlib.serialization.event.creator.DateCreator;
import org.sdmlib.serialization.event.creator.MapEntryCreator;
import org.sdmlib.serialization.interfaces.MapUpdateListener;
import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.creator.JsonArrayCreator;
import org.sdmlib.serialization.json.creator.JsonObjectCreator;
import org.sdmlib.utils.PropertyChangeInterface;

/**
 * The Class JsonIdMap.
 */
public class JsonIdMap extends IdMap implements PropertyChangeInterface{
	/** The Constant CLASS. */
	public static final String CLASS = "class";

	/** The Constant VALUE. */
	public static final String VALUE = "value";

	/** The Constant JSON_PROPS. */
	public static final String JSON_PROPS = "prop";

	/** The Constant MAINITEM. */
	public static final String MAINITEM = "main";
	
	protected Grammar grammar=new Grammar();

	/** The updatelistener. */
	private MapUpdateListener updatelistener;
	/** The updatelistener. */
	private PropertyChangeListener updatePropertylistener;

	
	/** If this is true the IdMap save the Typ of primary datatypes. */
	private boolean typSave;
	/**
	 * Instantiates a new json id map.
	 */
	public JsonIdMap() {
		super();
		this.addCreator(new DateCreator());
		this.addCreator(new JsonObjectCreator());
		this.addCreator(new JsonArrayCreator());
		this.addCreator(new MapEntryCreator());
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
	 * To Jsonobject.
	 *
	 * @param entity the entity
	 * @param filter the filter
	 * @return the Jsonobject
	 */
	public JsonObject toJsonObject(Object entity, JsonFilter filter) {
		String className = entity.getClass().getName();
		return toJsonObject(entity, filter, className, new LinkedHashSet<String>());
	}
	
	/**
	 * To Jsonobject.
	 *
	 * @param entity the entity to convert
	 * @param filter the filter
	 * @param className the className of the entity
	 * @return the Jsonobject
	 */
	public JsonObject toJsonObject(Object entity, JsonFilter filter, String className) {
		return toJsonObject(entity, filter, className, new LinkedHashSet<String>());
	}
	
	/**
	 * To Jsonobject.
	 *
	 * @param entity the entity to convert
	 * @param filter the filter
	 * @param className the className of the entity
	 * @return the Jsonobject
	 */
	protected JsonObject toJsonObject(Object entity, JsonFilter filter, String className, LinkedHashSet<String> visitedObjects) {
		String id = "";
		SendableEntityCreator prototyp = grammar.getObjectCreator(entity, className, this);
		if (prototyp == null) {
			return null;
		}
		if (!(prototyp instanceof NoIndexCreator||!filter.isId())) {
			id = getId(entity);
		}
		JsonObject jsonProp = new JsonObject();

		String[] properties = prototyp.getProperties();
		visitedObjects.add(id);
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
					boolean encoding = filter.isFullSerialization();
					if (!encoding) {
						Object refValue = prototyp.getValue(referenceObject,
								property);
						encoding = !value.equals(refValue);
					}
					if (encoding) {
						SendableEntityCreator referenceCreator=getCreatorClass(value);
						
						if (value instanceof Collection<?>&&referenceCreator==null) {
							// Simple List or Assocs
							JsonArray subValues = new JsonArray();
							for (Object containee : ((Collection<?>) value)) {
								Object item=getItem(containee, filter, entity, property, visitedObjects);
								if(item!=null){
									subValues.put(item);
								}
							}
							if(subValues.size()>0){
								jsonProp.put(property, subValues);
							}
						} else if (value instanceof Map<?,?>&&referenceCreator==null) {
							// Maps
							JsonArray subValues = new JsonArray();
							Map<?, ?> map=(Map<?, ?>) value;
							String packageName = MapEntry.class.getName();
							for (Iterator<?> i = map.entrySet().iterator(); i.hasNext();) {
								Entry<?,?> mapEntry = (Entry<?, ?>) i.next();
								JsonObject item=toJsonObject(mapEntry, filter, packageName, visitedObjects);
								if(item!=null){
									subValues.add(item);
								}
							}
							if(subValues.size()>0){
								jsonProp.put(property, subValues);
							}
						} else {
							if(!filter.isRegard(this, entity, property, value, true)){
								continue;
							}
							boolean aggregation = filter.isConvertable(this,
									entity, property, value, false);
							jsonProp.put(
									property,
									parseObject(value, aggregation, filter,
											null, referenceCreator, typSave, visitedObjects));
						}
					}
				}
			}
		}
		return grammar.getJsonObject(this, prototyp, className, id, jsonProp, filter);
	}
	public Object getItem(Object item, JsonFilter filter, Object entity, String property, LinkedHashSet<String> visitedObjects){
		if (item != null&&filter.isRegard(this, entity, property, item, true)){
			boolean aggregation = filter.isConvertable(this,
					entity, property, item, true);
	
			SendableEntityCreator referenceCreator=getCreatorClass(item);
			return parseObject(item, aggregation, filter, null, referenceCreator, typSave, visitedObjects);
		}
		return null;
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
		return readJson(jsonObject, true);
	}
	
	/**
	 * Read json.
	 *
	 * @param jsonObject the json object
	 * @return the object
	 */
	public Object readJson(JsonObject jsonObject, boolean readId) {
		LinkedHashSet<ReferenceObject> refs = new LinkedHashSet<ReferenceObject>();
		if(jsonObject.has(UPDATE) || jsonObject.has(REMOVE)){
			// Must be an update
			if(executeUpdateMsg(jsonObject)){
				String id = jsonObject.getString(JsonIdMap.ID);
				return getObject(id);
			}
			return null;
		}
		Object mainItem = readJson(jsonObject, refs, readId);
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
		return readJson(target, jsonObject, true);
	}
	
	public Object readJson(Object target, JsonObject jsonObject, boolean readId) {
		LinkedHashSet<ReferenceObject> refs = new LinkedHashSet<ReferenceObject>();
		Object mainItem = readJson(target, jsonObject, refs, readId);
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
		return readJson(jsonObject, refs, true);
	}

	/**
	 * Read json.
	 *
	 * @param jsonObject the json object
	 * @param refs the refs
	 * @param readId for read the id from JsonObject
	 * @return the object
	 */
	private Object readJson(JsonObject jsonObject,
			LinkedHashSet<ReferenceObject> refs, boolean readId) {
		Object result = null;
		SendableEntityCreator typeInfo = grammar.getJsonObjectCreator(jsonObject, this);

		if (typeInfo != null) {
			if (getCounter().isId()) {
				String jsonId = (String) jsonObject.get(ID);
				if (jsonId != null) {
					result = getObject(jsonId);
				}
			}
			if (result == null) {
				result = typeInfo.getSendableInstance(false);
				sendReceiveMsg(NEW, result, jsonObject);
			}else{
				sendReceiveMsg(UPDATE, result, jsonObject);
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
				readJson(result, jsonObject, refs, readId);
			}
		}else if(jsonObject.get(VALUE)!=null){
			return jsonObject.get(VALUE);
		}else if(jsonObject.get(ID)!=null){
			result = getObject((String) jsonObject.get(ID));
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
			LinkedHashSet<ReferenceObject> refs, boolean readId) {
		// JSONArray jsonArray;
		if (getCounter().isId()&&readId) {
			String jsonId = (String) jsonObject.get(ID);
			if (jsonId == null) {
				return target;
			}
			put(jsonId, target);
			getCounter().readId(jsonId);
		}
		JsonObject jsonProp=grammar.getJsonObjectProperties(jsonObject);
		if (jsonProp!=null) {
			SendableEntityCreator prototyp = grammar.getObjectCreator(target, target.getClass().getName(), this);
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
						String jsonId = (String) child.get(ID);
						if (className == null && jsonId != null) {
							// It is a Ref
							refs.add(new ReferenceObject(jsonId, creator,
									property, this, target));
						} else {
							creator.setValue(target, property,
									readJson((JsonObject) kid), NEW);
						}
					} else {
						creator.setValue(target, property, kid, NEW);
					}
				}
			} else {
				if (value instanceof JsonObject) {
					// // got a new kid, create it
					JsonObject child = (JsonObject) value;
					String className = (String) child.get(CLASS);
					String jsonId = (String) child.get(ID);
					// CHECK LIST AND MAPS
					Object ref_Obj = creator.getSendableInstance(true);
					Object refValue = creator.getValue(ref_Obj, property);
					if(refValue instanceof Map<?,?>){
						JsonObject json=(JsonObject) value;
						Iterator<String> i=json.keys();
						while(i.hasNext()){
							String key = i.next();
							Object entryValue= json.get(key);
							if(entryValue instanceof JsonObject){
								creator.setValue(target, property, new MapEntry(key, readJson((JsonObject) entryValue)), NEW);
							}else if(entryValue instanceof JsonArray){
								creator.setValue(target, property, new MapEntry(key, readJson((JsonArray) entryValue)), NEW);
							}else{
								creator.setValue(target, property, new MapEntry(key, entryValue), NEW);
							}
						}
					}else if (className == null && jsonId != null) {
						// It is a Ref
						refs.add(new ReferenceObject(jsonId, creator, property,
								this, target));
					}else{
						creator.setValue(target, property,
								readJson(child), NEW);
					}
				} else {
					creator.setValue(target, property, value, NEW);
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
		toJsonArray(object, jsonArray, filter, new LinkedHashSet<String>());
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
		toJsonArray(object, jsonArray, new JsonFilter(), new LinkedHashSet<String>());
		jsonArray.finishUnsorted();
		return jsonArray;
	}

	public JsonArray toJsonArray(Object object, JsonArray jsonArray,
			JsonFilter filter) {

		return toJsonArray(object, jsonArray, filter,
				new LinkedHashSet<String>());
	}

	/**
	 * To json array.
	 *
	 * @param entity the entity
	 * @param jsonArray the json array
	 * @param filter the filter
	 * @return the json array
	 */
	protected JsonArray toJsonArray(Object entity, JsonArray jsonArray,
			JsonFilter filter, LinkedHashSet<String> visitedObjects) {
		String className = entity.getClass().getName();
		String id = getId(entity);

		JsonObject jsonObject = new JsonObject();
		if ( ! visitedObjects.contains(id) && 
			      filter.checkProperty(this, JsonFilter.CUTREFERENCE, id, entity)){
			if (getCounter().isId()&&filter.isId()) {
				jsonObject.put(ID, id);
			}
			jsonObject.put(CLASS, className);
			jsonArray.put(jsonObject);
		}

		SendableEntityCreator prototyp = getCreatorClasses(className);
		if (prototyp == null) {
			throw new RuntimeException("No Creator exist for " + className);
		}
		String[] properties = prototyp.getProperties();
		visitedObjects.add(id);

		if (properties != null) {
			JsonObject jsonProps = new JsonObject();
			for (String property : properties) {
				Object value = prototyp.getValue(entity, property);
				if(value!=null){
					SendableEntityCreator referenceCreator=getCreatorClass(value);
					if (value instanceof Collection&&referenceCreator==null) {
						Collection<?> list = ((Collection<?>) value);
						if (list.size() > 0) {
							JsonArray refArray = new JsonArray();
							for (Object containee : list) {
								if (containee != null && filter.isRegard(this, entity, property, containee, true)) {
									boolean aggregation = filter.isConvertable(this, entity,
											property, containee, true);
									referenceCreator=getCreatorClass(containee);
									refArray.put(parseObject(containee,
											aggregation, filter, jsonArray, referenceCreator, isTypSave(), visitedObjects));
								}
							}
							if(refArray.size()>0){
								jsonProps.put(property, refArray);
							}
						}
					} else if (filter.isRegard(this, entity, property, value, false)){
						boolean aggregation = filter.isConvertable(this, entity,
								property, value, false);
						jsonProps.put(
								property,
								parseObject(value, aggregation, filter,
										jsonArray, referenceCreator, isTypSave(), visitedObjects));
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
	protected Object parseObject(Object entity, boolean aggregation,
			JsonFilter filter, JsonArray jsonArray, SendableEntityCreator valueCreater, boolean typSave, LinkedHashSet<String> visitedObjects) {
		if (valueCreater != null) {
			if (aggregation) {
				String subId = this.getKey(entity);
				if (valueCreater instanceof NoIndexCreator
						|| subId==null || ! visitedObjects.contains(subId)) {
					int oldValue = filter.setDeep(IdMapFilter.DEEPER);
					if (jsonArray == null) {
						JsonObject result = toJsonObject(entity, filter);
						filter.setDeep(oldValue);
						return result;
					}
					this.toJsonArray(entity, jsonArray, filter, visitedObjects);
					filter.setDeep(oldValue);
				}
			}
			return new JsonObject(ID, getId(entity));
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
		if(listener instanceof PropertyChangeListener){
			this.updatePropertylistener=(PropertyChangeListener) listener;
		}
	}

	public void setUpdateMsgListener(PropertyChangeListener listener) {
		this.updatePropertylistener = listener;
		if(listener instanceof MapUpdateListener){
			this.updatelistener=(MapUpdateListener) listener;
		}
	}

	/**
	 * Send update msg from PropertyChange MapUpdater 
	 *
	 * @param jsonObject the json object
	 * @return true, if successful
	 */
	public boolean sendUpdateMsg(PropertyChangeEvent evt, JsonObject jsonObject) {
		if(updatePropertylistener != null && evt!=null){
			updatePropertylistener.propertyChange(evt);
		}

		if (this.updatelistener != null && evt!=null) {
			return this.updatelistener.sendUpdateMsg(evt.getOldValue(), evt.getNewValue(), jsonObject);
		}
		return true;
	}
	
	public boolean sendReceiveMsg(String type, Object value, JsonObject props){
		if (this.updatelistener != null) {
			return this.updatelistener.readMessages(type, value, props);
		}
		return true;
	}
	public boolean sendReceiveObj(Object element, String key, Object value,
			String typ) {
		if (this.updatelistener != null) {
			return this.updatelistener.readMessageObj(element, key, value, typ);
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
		sendUpdateMsg(null, sendObj);
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
	@Override
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
		return this.values.keySet();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.getClass().getName()+" ("+this.size()+")";
	}

	/**
	 * Checks if is typ save.
	 *
	 * @return true, if is typ save
	 */
	public boolean isTypSave() {
		return this.typSave;
	}

	/**
	 * Sets the typ save.
	 *
	 * @param typSave the new typ save
	 */
	public void setTypSave(boolean typSave) {
		this.typSave = typSave;
	}
	
	public boolean skipCollision(Object masterObj, String key, Object value, JsonObject removeJson, JsonObject updateJson){
		if(this.updatelistener!=null){
			return this.updatelistener.skipCollision(masterObj, key, value, removeJson, updateJson);
		}
		return true;
	}
	public void setGrammar(Grammar value){
		this.grammar=value;
	}

	  //==========================================================================
	   
	   public Object get(String attrName)
	   {
	      return null;
	   }

	   
	   //==========================================================================
	   
	   public boolean set(String attrName, Object value)
	   {
	      return false;
	   }

	   
	   //==========================================================================
	   
	   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	   
	   public PropertyChangeSupport getPropertyChangeSupport()
	   {
	      return listeners;
	   }

	   
	   //==========================================================================
	   
	   public void removeYou()
	   {
	      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	   }

}
