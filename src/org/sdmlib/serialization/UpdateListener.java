package org.sdmlib.serialization;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;

public class UpdateListener implements PropertyChangeListener{
	private JsonIdMap map;
	private ArrayList<String> suspendIdList;

	public UpdateListener(IdMap map) {
		if(map instanceof JsonIdMap){
			this.map=(JsonIdMap) map;
		}
	}
	
	public void suspendNotification(){
		this.suspendIdList=new ArrayList<String>();
	}
	public void resetNotification(){
		map.toJsonArrayByIds(suspendIdList);
		this.suspendIdList=null;
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
				if(suspendIdList!=null){
					suspendIdList.add(map.getId(newValue));
				}
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

		if(suspendIdList==null){
			map.sendUpdateMsg(jsonObject);
		}
	}		
}
