package org.sdmlib.serialization;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import org.sdmlib.serialization.interfaces.IdCounter;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class IdMap<T extends SendableEntityCreator> {
	public static final String REMOVE="rem";
	public static final String UPDATE="upd";
	private HashMap<Object, String> keys = new HashMap<Object, String>();
	private HashMap<String, Object> values = new HashMap<String, Object>();
	private HashMap<String, T> creators = new HashMap<String, T>();
	protected IdMap<T> parent;
	protected boolean isId = true;
	private IdCounter counter;
	private UpdateListener updateListener;
	private RemoveListener removeListener;

	public IdMap(){
		keys = new HashMap<Object, String>();
		values = new HashMap<String, Object>();
		creators = new HashMap<String, T>();
	}
	public IdMap(IdMap<T> parent){
		this.parent=parent;
	}

	public void setCounter(IdCounter counter){
		this.counter=counter;
	}
	public IdCounter getCounter(){
		if(counter==null){
			counter=new SimpleIdCounter();
		}
		return counter;
	}
	
	public void setSessionId(String sessionId) {
		getCounter().setPrefixId(sessionId);
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
		if(key==null){
			key=getCounter().getId(obj);
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
				((SendableEntity)object).addPropertyChangeListener(IdMap.REMOVE, getListener(IdMap.REMOVE));
				((SendableEntity)object).addPropertyChangeListener(IdMap.UPDATE, getListener(IdMap.UPDATE));
			}else if(object instanceof PropertyChangeSupport){
				((PropertyChangeSupport)object).addPropertyChangeListener(IdMap.REMOVE, getListener(IdMap.REMOVE));
				((PropertyChangeSupport)object).addPropertyChangeListener(IdMap.UPDATE, getListener(IdMap.UPDATE));
			}
		}
	}
	
	public PropertyChangeListener getListener(String id){
		if(id==IdMap.UPDATE){
			if(this.updateListener==null){
				this.updateListener=new UpdateListener(this);
			}
			return updateListener;
		}else if(id==IdMap.REMOVE){
			if(this.removeListener==null){
				this.removeListener=new RemoveListener(this);
			}
			return removeListener;
		}
		return null;
	}

	public boolean remove(Object oldValue) {
		if(parent!=null){
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
	
	public boolean addCreator(T createrClass) {
		if(parent!=null){
			return parent.addCreator(createrClass);
		}else{
			Object reference = createrClass.getSendableInstance(true);
			creators.put(reference.getClass().getName(), createrClass);
			return true;
		}
	}
}
