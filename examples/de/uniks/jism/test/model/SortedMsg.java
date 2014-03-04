package de.uniks.jism.test.model;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.interfaces.SendableEntity;

public class SortedMsg implements SendableEntity {
	public static final String PROPERTY_ID="number";
	public static final String PROPERTY_CHILD="child";
	public static final String PROPERTY_MSG="msg";
	public static final String PROPERTY_PARENT="parent";
	private int number;
	private SortedMsg child;
	private SortedMsg parent;
	private String msg;
	private PropertyChangeSupport change=new PropertyChangeSupport(this);

	public int getNumber() {
		return number;
	}

	public void setNumber(int id) {
		int oldValue=this.number;
		this.number = id;
		if(id!=oldValue){
			change.firePropertyChange(SortedMsg.PROPERTY_ID, oldValue, id);
		}
	}
	public void updateNumber(int id) {
		this.number = id;
	}

	public SortedMsg getChild() {
		return child;
	}

	public void setChild(SortedMsg value) {
		
		if(value!=this.child){
			SortedMsg oldValue=this.child;
			this.child = value;
			change.firePropertyChange(SortedMsg.PROPERTY_CHILD, oldValue, value);
			if(value!=null){
				value.setParent(this);
			}
		}
	}

	public void setParent(SortedMsg value) {
		if(value!=this.parent){
			SortedMsg oldValue=this.parent;
			this.parent = value;
			change.firePropertyChange(SortedMsg.PROPERTY_PARENT, oldValue, value);
			if(value!=null){
				value.setChild(this);
			}
		}
	}

	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_ID)) {
			setNumber((Integer) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_CHILD)) {
			setChild((SortedMsg) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_PARENT)) {
			setParent((SortedMsg) value);
			return true;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_MSG)) {
			setMsg((String) value);
			return true;
		}
		return false;
	}

	public Object get(String attrName) {
		String attribute;
		int pos = attrName.indexOf(".");
		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		} else {
			attribute = attrName;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_ID)) {
			return getNumber();
		}
		if (attribute.equalsIgnoreCase(PROPERTY_CHILD)) {
			return getChild();
		}
		if (attribute.equalsIgnoreCase(PROPERTY_PARENT)) {
			return getParent();
		}
		if (attribute.equalsIgnoreCase(PROPERTY_MSG)) {
			return getMsg();
		}
		return null;
	}

	@Override
	public boolean addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		change.addPropertyChangeListener(propertyName, listener);
		return true;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public SortedMsg withMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public SortedMsg getParent() {
		return parent;
	}

	@Override
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		change.removePropertyChangeListener(listener);
		return true;
	}

	@Override
	public boolean addPropertyChangeListener(PropertyChangeListener listener) {
		 change.addPropertyChangeListener(listener);
		return true;
	}
}
