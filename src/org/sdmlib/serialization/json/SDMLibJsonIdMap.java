package org.sdmlib.serialization.json;

import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class SDMLibJsonIdMap extends JsonIdMap{
	
	@Override
	public boolean addListener(Object object) {
		if(super.addListener(object)){
			return true;
		}
		if (object instanceof PropertyChangeSupport) {
			((PropertyChangeSupport) object).addPropertyChangeListener(
					IdMap.UPDATE, getUpdateListener());
			return true;
		} else if (object instanceof PropertyChangeInterface)
		{
		   ((PropertyChangeInterface) object).getPropertyChangeSupport().addPropertyChangeListener(getUpdateListener());
		   return true;
		}
		return false;
		
	}
	
	public JsonIdMap withSessionId(String sessionId){
		setSessionId(sessionId);
		return this;
	}
}
