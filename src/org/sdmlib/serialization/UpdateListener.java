package org.sdmlib.serialization;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;

public class UpdateListener implements PropertyChangeListener{
	private JsonIdMap map;
	private boolean suspendPeerNotifications;

	public UpdateListener(IdMap<?> map) {
		this.map=(JsonIdMap) map;
	}
	
	public void suspendNotification(){
		this.suspendPeerNotifications=true;
	}
	public void resetNotification(){
		this.suspendPeerNotifications=false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// put changes into msg and send to receiver
		Object source = evt.getSource();
				

		JsonObject jsonObject = (JsonObject) new JsonObject()
		.put(JsonIdMap.JSON_ID, map.getId(source));

		String propertyName = evt.getPropertyName();
		Object oldValue = evt.getOldValue();
		Object newValue = evt.getNewValue();
		
		SendableEntityCreator oldCreatorClass = null;
		if(oldValue!=null){
			oldCreatorClass=map.getCreatorClass(oldValue);
		}
		JsonObject childProp=new JsonObject();
		if(oldCreatorClass!=null)
		{
			String oldId = map.getId(oldValue);
			// map.remove(oldValue);
			// oldValue.removePropertyChangeListener(this);
			childProp.put(propertyName, oldId);
			jsonObject.put(IdMap.REMOVE, childProp);
		}
		
		SendableEntityCreator newCreatorClass = null;
		if(newValue!=null){
			newCreatorClass=map.getCreatorClass(newValue);
		}

		if(newCreatorClass!= null)
		{
			String key = map.getKey(newValue);
			if(key!=null){
				childProp.put(propertyName, key);
				jsonObject.put(IdMap.UPDATE, childProp);
			}else{
				childProp.put(propertyName, map.toJsonObject(newValue));
				jsonObject.put(IdMap.UPDATE, childProp);
			}
		}
		else if (newValue != null)
		{
			// plain attribute
			SendableEntityCreator createrClass = map.getCreatorClass(evt.getSource());
			boolean done = false;

			for (String attrName : createrClass.getProperties())
			{
				if (attrName.equals(propertyName))
				{
					childProp.put(propertyName, newValue);
					jsonObject.put(IdMap.UPDATE, childProp);
					done = true;
					break;
				}
			}

			if (!done)
			{
				// this property is not part of the replicated model, do not replicate
				return;
			}
		}		

		if (!suspendPeerNotifications)
		{
			map.sendUpdateMsg(jsonObject);
		}
	}		
}
