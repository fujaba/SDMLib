package org.sdmlib.serialization;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RemoveListener implements PropertyChangeListener{
	private IdMap map;

	public RemoveListener(IdMap map){
		this.map=map;
	}
	public boolean exeucte(Object entity){
		return map.remove(entity);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getNewValue()==null){
			String key = map.getKey(evt.getOldValue());
			if(key!=null){
				exeucte(evt.getOldValue());
			}
		}
	}
}
