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
import java.util.HashMap;
import java.util.Set;

import org.sdmlib.serialization.interfaces.IdMapCounter;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

/**
 * The Class IdMap.
 */
public class IdMap {
	/** The Constant REMOVE. */
	public static final String REMOVE= "rem";
	
	/** The Constant UPDATE. */
	public static final String UPDATE = "upd";
	
	/** The Constant PRIO. */
	public static final String PRIO = "prio";
	
	/** The keys. */
	protected HashMap<Object, String> keys;
	
	/** The values. */
	protected HashMap<String, Object> values;
	
	/** The creators. */
	protected HashMap<String, SendableEntityCreator> creators;
	
	/** The parent. */
	protected IdMap parent;
	
	/** The is id. */
	protected boolean isId = true;
	
	/** The counter. */
	private IdMapCounter counter;
	
	/** The update listener. */
	protected UpdateListener updateListener;
	
	/** The simple check. */
	private boolean simpleCheck;
	
	/** The prio. */
	private Object prio;

	/**
	 * Instantiates a new id map.
	 */
	public IdMap() {
		keys = new HashMap<Object, String>();
		values = new HashMap<String, Object>();
		creators = new HashMap<String, SendableEntityCreator>();
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
		if (counter == null) {
			counter = new SimpleIdCounter();
		}
		return counter;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionId the new session id
	 */
	public void setSessionId(String sessionId) {
		getCounter().setPrefixId(sessionId);
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
		if (parent != null) {
			return parent.getKey(obj);
		}
		return keys.get(obj);
	}

	/**
	 * Gets the object.
	 *
	 * @param key the key
	 * @return the object
	 */
	public Object getObject(String key) {
		if (parent != null) {
			return parent.getObject(key);
		}
		return values.get(key);
	}

	/**
	 * Gets or Create the id. 
	 *
	 * @param obj the obj
	 * @return the id
	 */
	public String getId(Object obj) {
		if (!isId) {
			return "";
		}
		if (parent != null) {
			return parent.getId(obj);
		}
		String key = keys.get(obj);
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
	 */
	public void put(String jsonId, Object object) {
		if (parent != null) {
			parent.put(jsonId, object);
		} else {
			values.put(jsonId, object);
			keys.put(object, jsonId);
			addListener(object);
		}
	}

	public UpdateListener getUpdateListener(){
		if (this.updateListener == null) {
			this.updateListener = new UpdateListener(this);
		}
		return updateListener;
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

	/**
	 * Removes the.
	 *
	 * @param oldValue the old value
	 * @return true, if successful
	 */
	public boolean remove(Object oldValue) {
		if (parent != null) {
			return parent.remove(oldValue);
		}
		String key = getKey(oldValue);
		if (key != null) {
			keys.remove(oldValue);
			values.remove(key);
			return true;
		}
		return false;
	}

	/**
	 * Checks if is id.
	 *
	 * @return true, if is id
	 */
	public boolean isId() {
		return isId;
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		if (parent != null) {
			return parent.size();
		}
		return keys.size();
	}

	/**
	 * Gets the creator classes.
	 *
	 * @param className the class name
	 * @return the creator classes
	 */
	public SendableEntityCreator getCreatorClasses(String className) {
		if (parent != null) {
			return parent.getCreatorClasses(className);
		}
		return creators.get(className);
	}

	/**
	 * Gets the creator class.
	 *
	 * @param reference the reference
	 * @return the creator class
	 */
	public SendableEntityCreator getCreatorClass(Object reference) {
		return getCreatorClasses(reference.getClass().getName());
	}

	/**
	 * Clone object.
	 *
	 * @param reference the reference
	 * @return the object
	 */
	public Object cloneObject(Object reference) {
		return cloneObject(reference, new CloneFilter(CloneFilter.SIMPLE));
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
					creatorClass.setValue(newObject, property, value);
				} else if (value instanceof Collection<?>) {
					if(filter.getTyp()==CloneFilter.FULL){
						Collection<?> list = (Collection<?>) value;
						for (Object item : list) {
							if(filter.hasObject(item)){
								creatorClass.setValue(newObject, property,
										filter.getObject(item));
							} else {
								SendableEntityCreator childCreatorClass = getCreatorClass(item);
								if (childCreatorClass != null) {
									if(!filter.isConvertable(this, item, property, value)){
										creatorClass.setValue(newObject, property, item);
									}else{
										int oldDeep=filter.setDeep(filter.getDeep()-1);
										cloneObject(item, filter);
										filter.setDeep(oldDeep);
									}
								}else{
									creatorClass.setValue(newObject, property,
											item);
								}
							}
						}
					} else {
						creatorClass.setValue(newObject, property, value);
					}
				} else {
					if(filter.hasObject(value)){
						creatorClass.setValue(newObject, property,
								filter.getObject(value));
					} else {
						SendableEntityCreator childCreatorClass = getCreatorClass(value);
						if (childCreatorClass != null) {
							if(!filter.isConvertable(this, value, property, value)){
								creatorClass.setValue(newObject, property, value);
							}else{
								int oldDeep=filter.setDeep(filter.getDeep()-1);
								cloneObject(value, filter);
								filter.setDeep(oldDeep);
							}
						}else{
							creatorClass.setValue(newObject, property,
									value);
						}
					}
				}
			}
		}
		return newObject;
	}

	/**
	 * Adds the creator.
	 *
	 * @param createrClass the creater class
	 * @return true, if successful
	 */
	public boolean addCreator(SendableEntityCreator createrClass) {
		if (parent != null) {
			return parent.addCreator(createrClass);
		} else {
			Object reference = createrClass.getSendableInstance(true);
			if(reference == null){
				return false;
			}
			addCreator(reference.getClass().getName(), createrClass);
			return true;
		}
	}
	
	/**
	 * add a Creator to list
	 *
	 * @param className the class name
	 * @param creator the creator
	 */
	public void addCreator(String className, SendableEntityCreator creator)
	{
	   creators.put(className, creator);
	}

	
	/**
	 * Checks if is simple check.
	 *
	 * @return true, if is simple check
	 */
	public boolean isSimpleCheck() {
		return simpleCheck;
	}

	/**
	 * Sets the simple check.
	 *
	 * @param simpleCheck the simple check
	 * @return true, if successful
	 */
	public boolean setSimpleCheck(boolean simpleCheck) {
		this.simpleCheck = simpleCheck;
		return simpleCheck;
	}

	/**
	 * Gets the prio.
	 *
	 * @return the prio
	 */
	public Object getPrio() {
		return prio;
	}

	/**
	 * Sets the prio.
	 *
	 * @param prio the new prio
	 */
	public void setPrio(Object prio) {
		this.prio = prio;
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
		updateListener.startGarbageColection(root);
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
		updateListener.garbageCollection(root);
	}
	
	public void garbageCollection(Set<String> classCounts) {
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
}
