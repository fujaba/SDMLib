package org.sdmlib.utils;

public class PropertyChangeEvent {
	private final String propertyName;
	private final Object source;
	private final Object oldValue;
	private final Object newValue;
	
	
	public PropertyChangeEvent(Object source, String propertyName,
			Object oldValue, Object newValue) {
		super();
		this.source = source;
		this.propertyName = propertyName;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	public Object getSource() {
		return source;
	}
	public Object getOldValue() {
		return oldValue;
	}
	public Object getNewValue() {
		return newValue;
	}
	
	
}
