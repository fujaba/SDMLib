package org.sdmlib.replication.util;

import java.beans.PropertyChangeEvent;

import de.uniks.networkparser.interfaces.BaseItem;
import de.uniks.networkparser.interfaces.SendableEntityCreatorWrapper;

public class PropertyChangeEventWrapper extends SendableEntityCreatorWrapper {
	public static final String PROPERTY_SOURCE="source";
	public static final String PROPERTY_PROPERTY="property";
	public static final String PROPERTY_OLDVALUE="oldValue";
	public static final String PROPERTY_NEWVALUE="newValue";
	public static final String SENDABLECLASSSTRING="java.beans.PropertyChangeEvent";
	
	@Override
	public String[] getProperties() {
		return new String[]{PROPERTY_SOURCE, PROPERTY_PROPERTY, PROPERTY_OLDVALUE, PROPERTY_NEWVALUE};
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new PropertyChangeEvent(this,null,null,null);
	}
	
	@Override
	public Object getValue(Object entity, String attribute) {
		if(entity instanceof PropertyChangeEvent) {
			if(PROPERTY_SOURCE.equalsIgnoreCase(attribute)) {
				return ((PropertyChangeEvent)entity).getSource();
			}
			if(PROPERTY_PROPERTY.equalsIgnoreCase(attribute)) {
				return ((PropertyChangeEvent)entity).getPropertyName();
			}
			if(PROPERTY_OLDVALUE.equalsIgnoreCase(attribute)) {
				return ((PropertyChangeEvent)entity).getOldValue();
			}
			if(PROPERTY_NEWVALUE.equalsIgnoreCase(attribute)) {
				return ((PropertyChangeEvent)entity).getNewValue();
			}
		}
		return null;
	}

	@Override
	public Object newInstance(BaseItem item) {
		Object source = item.getValueItem(PROPERTY_SOURCE);
		String property = ""+item.getValueItem(PROPERTY_PROPERTY);
		Object oldValue = item.getValueItem(PROPERTY_OLDVALUE);
		Object newValue = item.getValueItem(PROPERTY_NEWVALUE);
		return new PropertyChangeEvent(source, property, oldValue, newValue);
	}
}
