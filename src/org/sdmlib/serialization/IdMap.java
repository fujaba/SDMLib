package org.sdmlib.serialization;

import java.util.LinkedHashMap;

import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;


public class IdMap<T extends SendableEntityCreator> {
	protected String sessionId="J1";
	protected long number = 1;
	private LinkedHashMap<Object, String> keys = new LinkedHashMap<Object, String>();
	private LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
	private LinkedHashMap<String, T> creators = new LinkedHashMap<String, T>();
	protected IdMap<T> parent;
	protected boolean isId = true;

	public IdMap(){
		keys = new LinkedHashMap<Object, String>();
		values = new LinkedHashMap<String, Object>();
		creators = new LinkedHashMap<String, T>();
	}
	public IdMap(IdMap<T> parent){
		this.parent=parent;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	// Key Value paar
	public String getKey(Object obj) {
		if(parent!=null){
			return parent.getKey(obj);
		}
		return keys.get(obj);
	}

	public Object getObject(String key) {
		if(parent!=null){
			return parent.getObject(key);
		}
		return values.get(key);
	}

	public String getId(Object obj) {
		if(!isId){
			return "";
		}
		if(parent!=null){
			return parent.getId(obj);
		}
		String key = keys.get(obj);
		if (key == null) {
			// new object generate key and add to tables
			// <session id>.<first char><running number>
			if (obj == null) {
				try {
					throw new Exception("NullPointer: " + obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String className = obj.getClass().getName();
			char firstChar = className.charAt(className.lastIndexOf('.') + 1);
			if (sessionId != null) {
				key = sessionId + "." + firstChar + number;
			} else {
				key = "" + firstChar + number;
			}
			number++;

			put(key, obj);
		}
		return key;
	}

	public void put(String jsonId, Object object) {
		if(parent!=null){
			parent.put(jsonId, object);
		}else{
			values.put(jsonId, object);
			keys.put(object, jsonId);
			if(object instanceof SendableEntity){
				((SendableEntity)object).setRemoveListener(new RemoveEntity(this, object));
			}
		}
	}

	public boolean remove(Object oldValue) {
		if(parent!=null){
			return parent.remove(oldValue);
		}
		String key = getKey(oldValue);
		if (key != null) {
			keys.remove(key);
			values.remove(oldValue);
			return true;
		}
		return false;
	} 
	public boolean isId(){
		return isId;
	}


	public int size() {
		if(parent!=null){
			return parent.size();
		}
		return keys.size();
	}

	public T getCreatorClasses(String className) {
		if(parent!=null){
			return (T)parent.getCreatorClasses(className);
		}
		return creators.get(className);
	}

	public T getCreatorClass(Object reference) {
		if(parent!=null){
			return (T)parent.getCreatorClass(reference);
		}
		return creators.get(reference.getClass().getName());
	}
	
	public boolean addCreater(T createrClass) {
		if(parent!=null){
			return parent.addCreater(createrClass);
		}else{
			Object reference = createrClass.getSendableInstance(true);
			creators.put(reference.getClass().getName(), createrClass);
			return true;
		}
	}
}
