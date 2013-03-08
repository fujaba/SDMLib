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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.sdmlib.serialization.interfaces.IdMapCounter;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.TypList;
import org.sdmlib.serialization.json.UpdateListener;

/**
 * The Class IdMap.
 */
public class IdMap extends AbstractIdMap{
	/** The Constant ID. */
	public static final String ID="id";

	/** The Constant REMOVE. */
	public static final String REMOVE= "rem";
	
	/** The Constant UPDATE. */
	public static final String UPDATE = "upd";
	
	/** The Constant NEW. */
	public static final String NEW = "new";
	
	/** The Constant PRIO. */
	public static final String PRIO = "prio";
	
	/** The keys. */
	protected Map<Object, String> keys;
	
	/** The values. */
	protected Map<String, Object> values;
	
	/** The counter. */
	private IdMapCounter counter;
	
	/** The update listener. */
	protected UpdateListener updateListener;
	
	/** The parent. */
	protected IdMap parent;

	protected ArrayList<TypList> typList;
	
	/**
	 * Instantiates a new id map.
	 */
	public IdMap() {
		super();
		this.keys = new HashMap<Object, String>();
		this.values = new HashMap<String, Object>();
		this.addCreator(new TextItems());
	}

	/**
	 * Instantiates a new id map.
	 *
	 * @param parent the parent
	 */
	public IdMap(IdMap parent) {
		this.parent = parent;
	}

	/**
	 * Sets the counter.
	 *
	 * @param counter the new counter
	 */
	public void setCounter(IdMapCounter counter) {
		this.counter = counter;
	}

	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public IdMapCounter getCounter() {
		if (this.counter == null) {
			this.counter = new SimpleIdCounter();
		}
		return this.counter;
	}

	/**
	 * Sets the session id.
	 *
	 * @param value the new session id
	 */
	public void setSessionId(String value) {
		getCounter().setPrefixId(value);
	}
	
	/**
	 * Get the current Session Id
	 * @return sessionid as String
	 */
	public String getSessionId()
	{
		return getCounter().getPrefixId();
	}
	
	/**
	 * Get the current Splitter Character
	 * @return Splitter as Character
	 */
	public String getPrefixSession()
	{
		IdMapCounter counter = getCounter();
		if(counter==null || !counter.isId()){
			return null;
		}
		return counter.getPrefixId()+counter.getSplitter();
	}
	
	
	/**
	 * Sets the splitter Character.
	 *
	 * @param Character the new splitter-Character for the session id
	 */
	public void setSplitterId(char splitter) {
		getCounter().setSplitter(splitter);
	}

	// Key Value paar
	/**
	 * Gets the Id. Do not generate a Id
	 *
	 * @param obj the obj
	 * @return the key
	 */
	public String getKey(Object obj) {
		if (this.parent != null) {
			return this.parent.getKey(obj);
		}
		return this.keys.get(obj);
	}

	/**
	 * Gets the object.
	 *
	 * @param key the key
	 * @return the object
	 */
	public Object getObject(String key) {
		if (this.parent != null) {
			return this.parent.getObject(key);
		}
		return this.values.get(key);
	}

	/**
	 * Gets or Create the id. 
	 *
	 * @param obj the obj
	 * @return the id
	 */
	public String getId(Object obj) {
		if (!getCounter().isId()) {
			return "";
		}
		if (this.parent != null) {
			return this.parent.getId(obj);
		}
		String key = this.keys.get(obj);
		if (key == null) {
			key = getCounter().getId(obj);
			put(key, obj);
		}
		return key;
	}

	/**
	 * Put.
	 *
	 * @param jsonId the json id
	 * @param object the object
	 * @return 
	 */
	public Object put(String jsonId, Object object) {
		if (this.parent != null) {
			this.parent.put(jsonId, object);
		} else {
			this.values.put(jsonId, object);
			this.keys.put(object, jsonId);
			addListener(object);
			addTypList(object);
		}
		return object;
	}
	
	private void addTypList(Object object){
		if(this.typList!=null){
			for(TypList list : this.typList){
				list.addObject(object);
			}
		}
	}
	
	public void addToTypList(TypList typList){
		if(typList==null){
			this.typList = new ArrayList<TypList>();
		}
		this.typList.add(typList);
	}
	
	/**
	 * @param check for add Listener to object 
	 * @return success of adding
	 */
	public boolean addListener(Object object){
		if (object instanceof SendableEntity) {
			return ((SendableEntity) object).addPropertyChangeListener(
					IdMap.UPDATE, getUpdateListener());
		}
		return false;
	}
	
	public UpdateListener getUpdateListener(){
		if (this.updateListener == null) {
			this.updateListener = new UpdateListener(this);
		}
		return this.updateListener;
	}

	/**
	 * Removes the Entity from List or Destroy them
	 * @param oldValue
	 * @param destroy
	 * @param destroyAll
	 * @return boolean if success
	 */
	public boolean removeObj(Object oldValue, boolean destroy) {
		if (this.parent != null) {
			return this.parent.removeObj(oldValue, destroy);
		}
		String key = getKey(oldValue);
		if(destroy){
			SendableEntityCreator creator=getCreatorClass(oldValue);
			if(creator!=null){
				String[] props = creator.getProperties();
				for(String prop : props){
					Object reference = creator.getValue(oldValue, prop);
					if(reference instanceof Collection<?>){
						Collection<?> continee=(Collection<?>) reference;
						Iterator<?> i = continee.iterator();
						while(i.hasNext()){
							creator.setValue(oldValue, prop, i.next(), IdMap.REMOVE);
						}
					}else{
						creator.setValue(oldValue, prop, reference, IdMap.REMOVE);
					}
				}
			}
		}
		if (key != null) {
			this.keys.remove(oldValue);
			this.values.remove(key);
			if(this.typList!=null){
				for(TypList list : this.typList){
					list.removeObject(oldValue);
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		if (this.parent != null) {
			return this.parent.size();
		}
		return this.keys.size();
	}

	/**
	 * Gets the creator classes.
	 *
	 * @param className the class name
	 * @return the creator classes
	 */
	public SendableEntityCreator getCreatorClasses(String className) {
		if (this.parent != null) {
			return this.parent.getCreatorClasses(className);
		}
		return super.getCreatorClasses(className);
	}


	/**
	 * Clone object.
	 *
	 * @param reference the reference
	 * @param filter the filter
	 * @return the object
	 */
	public Object cloneObject(Object reference, CloneFilter filter) {
		SendableEntityCreator creatorClass = getCreatorClass(reference);
		Object newObject = null;
		if (creatorClass != null) {
			newObject = creatorClass.getSendableInstance(false);
			String[] properties = creatorClass.getProperties();
			filter.addObject(reference, newObject);

			for (String property : properties) {
				Object value = creatorClass.getValue(reference, property);
				if(filter.getTyp()==CloneFilter.SIMPLE){
					creatorClass.setValue(newObject, property, value, IdMap.NEW);
				} else if (value instanceof Collection<?>) {
					if(filter.getTyp()==CloneFilter.FULL){
						Collection<?> list = (Collection<?>) value;
						for (Object item : list) {
							if(filter.hasObject(item)){
								creatorClass.setValue(newObject, property,
										filter.getObject(item), IdMap.NEW);
							} else {
								SendableEntityCreator childCreatorClass = getCreatorClass(item);
								if (childCreatorClass != null) {
									if(!filter.isConvertable(this, item, property, value, true)){
										creatorClass.setValue(newObject, property, item, IdMap.NEW);
									}else{
										int oldDeep=filter.setDeep(filter.getDeep()-1);
										cloneObject(item, filter);
										filter.setDeep(oldDeep);
									}
								}else{
									creatorClass.setValue(newObject, property,
											item, IdMap.NEW);
								}
							}
						}
					} else {
						creatorClass.setValue(newObject, property, value, IdMap.NEW);
					}
				} else {
					if(filter.hasObject(value)){
						creatorClass.setValue(newObject, property,
								filter.getObject(value), IdMap.NEW);
					} else {
						SendableEntityCreator childCreatorClass = getCreatorClass(value);
						if (childCreatorClass != null) {
							if(!filter.isConvertable(this, value, property, value, false)){
								creatorClass.setValue(newObject, property, value, IdMap.NEW);
							}else{
								int oldDeep=filter.setDeep(filter.getDeep()-1);
								cloneObject(value, filter);
								filter.setDeep(oldDeep);
							}
						}else{
							creatorClass.setValue(newObject, property,
									value, IdMap.NEW);
						}
					}
				}
			}
		}
		return newObject;
	}


	@Override
	public void addCreator(String className, SendableEntityCreator creator) {
		if (this.parent != null) {
			this.parent.addCreator(className, creator);
			return;
		}
		super.addCreator(className, creator);
	}
	

	/**
	 * Adds the creator.
	 *
	 * @param createrClass the creater class
	 * @return true, if successful
	 */
	public boolean addCreator(SendableEntityCreator createrClass) {
		if (this.parent != null) {
			return this.parent.addCreator(createrClass);
		} 
		return super.addCreator(createrClass);
	}
	
	/**
	 * Start carbage collection.
	 *
	 * @param root the root
	 */
	public void startCarbageCollection(Object root){
		if (this.updateListener == null) {
			this.updateListener = new UpdateListener(this);
		}
		this.updateListener.startGarbageColection(root);
	}
	
	/**
	 * Garbage collection.
	 *
	 * @param root the root
	 */
	public void garbageCollection(Object root){
		if (this.updateListener == null) {
			this.updateListener = new UpdateListener(this);
		}
		this.updateListener.garbageCollection(root);
	}
	
	public void garbageCollection(Set<String> classCounts) {
		// Must be override
	}
	public Object startUpdateModell(String clazz){
		SendableEntityCreator creator=getCreatorClasses(clazz);
		if(creator!=null){
			Object result=creator.getSendableInstance(false);
			String id = getId(result);
			put(id, result);
			return result;
		}
		return null;
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
			if(obj instanceof Comparable<?>){
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
		}
		return result;
	}
	
	public boolean isEmpty() {
		if (this.parent != null) {
			return this.parent.isEmpty();
		}
		return this.values.size()<1;
	}
	
	public boolean containsKey(Object key) {
		if (this.parent != null) {
			return this.parent.containsKey(key);
		}
		return this.keys.containsKey(key);
	}
	
	public boolean containsValue(Object value) {
		if (this.parent != null) {
			return this.parent.containsValue(value);
		}
		return this.values.containsKey(value);
	}
	
	public Object get(Object key) {
		return getKey(key);
	}
	
	public Object remove(Object oldValue) {
		if(removeObj(oldValue, false)){
			return oldValue;
		}
		return null;
	}
	
	public void putAll(Map<? extends String, ? extends Object> map) {
		if (this.parent != null) {
			this.parent.putAll(map);
			return;
		}
		this.clear();
		
		for (Iterator<?> i = map.entrySet().iterator(); i.hasNext();) {
			java.util.Map.Entry<?,?> mapEntity = (Entry<?, ?>) i.next();
			put(""+mapEntity.getKey(), mapEntity.getValue());
		}
	}
	
	public void clear() {
		if (this.parent != null) {
			this.parent.clear();
			return;
		}		
		this.values.clear();
		this.keys.clear();
	}
	
	public Set<String> keySet() {
		if (this.parent != null) {
			return this.parent.keySet();
		}	
		return values.keySet();
	}
	
	public Collection<Object> values() {
		if (this.parent != null) {
			return this.parent.values();
		}	
		return values.values();
	}
	
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		if (this.parent != null) {
			return this.parent.entrySet();
		}
		return values.entrySet();
	}
}
