package org.sdmlib.serialization;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashMap;

import org.sdmlib.serialization.interfaces.IdMapCounter;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class IdMap {
	public static final String REMOVE= "rem";
	public static final String UPDATE = "upd";
	public static final String PRIO = "prio";
	private HashMap<Object, String> keys;
	private HashMap<String, Object> values;
	private HashMap<String, SendableEntityCreator> creators;
	protected IdMap parent;
	protected boolean isId = true;
	private IdMapCounter counter;
	protected UpdateListener updateListener;
	protected RemoveListener removeListener;
	private boolean simpleCheck;
	private Object prio;

	public IdMap() {
		keys = new HashMap<Object, String>();
		values = new HashMap<String, Object>();
		creators = new HashMap<String, SendableEntityCreator>();
	}

	public IdMap(IdMap parent) {
		this.parent = parent;
	}

	public void setCounter(IdMapCounter counter) {
		this.counter = counter;
	}

	public IdMapCounter getCounter() {
		if (counter == null) {
			counter = new SimpleIdCounter();
		}
		return counter;
	}

	public void setSessionId(String sessionId) {
		getCounter().setPrefixId(sessionId);
	}

	// Key Value paar
	public String getKey(Object obj) {
		if (parent != null) {
			return parent.getKey(obj);
		}
		return keys.get(obj);
	}

	public Object getObject(String key) {
		if (parent != null) {
			return parent.getObject(key);
		}
		return values.get(key);
	}

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

	public void put(String jsonId, Object object) {
		if (parent != null) {
			parent.put(jsonId, object);
		} else {
			values.put(jsonId, object);
			keys.put(object, jsonId);
			if (object instanceof SendableEntity) {
				((SendableEntity) object).addPropertyChangeListener(
						IdMap.REMOVE, getListener(IdMap.REMOVE));
				((SendableEntity) object).addPropertyChangeListener(
						IdMap.UPDATE, getListener(IdMap.UPDATE));
			} else if (object instanceof PropertyChangeSupport) {
				((PropertyChangeSupport) object).addPropertyChangeListener(
						IdMap.REMOVE, getListener(IdMap.REMOVE));
				((PropertyChangeSupport) object).addPropertyChangeListener(
						IdMap.UPDATE, getListener(IdMap.UPDATE));
			}
		}
	}

	public PropertyChangeListener getListener(String id) {
		if (id == IdMap.UPDATE) {
			if (this.updateListener == null) {
				this.updateListener = new UpdateListener(this);
			}
			return updateListener;
		} else if (id == IdMap.REMOVE) {
			if (this.removeListener == null) {
				this.removeListener = new RemoveListener(this);
			}
			return removeListener;
		}
		return null;
	}

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

	public boolean isId() {
		return isId;
	}

	public int size() {
		if (parent != null) {
			return parent.size();
		}
		return keys.size();
	}

	public SendableEntityCreator getCreatorClasses(String className) {
		if (parent != null) {
			return parent.getCreatorClasses(className);
		}
		return creators.get(className);
	}

	public SendableEntityCreator getCreatorClass(Object reference) {
		return getCreatorClasses(reference.getClass().getName());
	}

	public Object cloneObject(Object reference) {
		return cloneObject(reference, new CloneFilter(CloneFilter.SIMPLE));
	}
	
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

	public boolean addCreator(SendableEntityCreator createrClass) {
		if (parent != null) {
			return parent.addCreator(createrClass);
		} else {
			Object reference = createrClass.getSendableInstance(true);
			if(reference == null){
				return false;
			}
			creators.put(reference.getClass().getName(), createrClass);
			return true;
		}
	}
	public boolean isSimpleCheck() {
		return simpleCheck;
	}

	public boolean setSimpleCheck(boolean simpleCheck) {
		this.simpleCheck = simpleCheck;
		return simpleCheck;
	}

	public Object getPrio() {
		return prio;
	}

	public void setPrio(Object prio) {
		this.prio = prio;
	}
}
