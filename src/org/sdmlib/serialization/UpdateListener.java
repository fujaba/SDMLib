package org.sdmlib.serialization;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;

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
		String propertyName = evt.getPropertyName();
		SendableEntityCreator creatorClass =map.getCreatorClass(source);
		
		boolean done=false;
		for (String attrName : creatorClass.getProperties())
		{
			if (attrName.equals(propertyName))
			{
				done = true;
				break;
			}
		}
		if(!done){
			// this property is not part of the replicated model, do not replicate
			return;
		}

		JsonObject jsonObject = new JsonObject(JsonIdMap.JSON_ID, map.getId(source));
		
		Object oldValue = evt.getOldValue();
		Object newValue = evt.getNewValue();
		
		if(oldValue!=null){
			creatorClass=map.getCreatorClass(oldValue);
			
			JsonObject child=new JsonObject();
			if(creatorClass!=null)
			{
				
				String oldId = map.getId(oldValue);
				if(oldId!=null){
					child.put(propertyName, new JsonObject(JsonIdMap.JSON_ID, oldId));
				}
			}else{
				child.put(propertyName, oldValue);
			}
			jsonObject.put(IdMap.REMOVE, child);
		}

		if(newValue!=null){
			creatorClass=map.getCreatorClass(newValue);

			JsonObject child=new JsonObject();
			if(creatorClass!= null)
			{
				String key = map.getKey(newValue);
				if(key!=null){
					child.put(propertyName, new JsonObject(JsonIdMap.JSON_ID, key));
				}else{
					child.put(propertyName, map.toJsonObject(newValue));
					if(suspendIdList!=null){
						suspendIdList.add(map.getId(newValue));
					}
				}
			}else{
				// plain attribute
				child.put(propertyName, newValue);
			}
			jsonObject.put(IdMap.UPDATE, child);
		}
		if(map.getPrio()!=null){
			jsonObject.put(IdMap.PRIO, map.getPrio());
		}

		if(suspendIdList==null){
			map.sendUpdateMsg(jsonObject);
		}
	}
	public boolean execute(JsonObject updateMessage){
		if(updateMessage.has(JsonIdMap.JSON_PROPS)){
			// its a new Object
			map.readJson(updateMessage);
			return true;
		}
		
		String id=updateMessage.getString(JsonIdMap.JSON_ID);
		JsonObject remove=(JsonObject) updateMessage.get(JsonIdMap.REMOVE);
		JsonObject update=(JsonObject) updateMessage.get(JsonIdMap.UPDATE);
		Object prio=updateMessage.get(JsonIdMap.PRIO);
		Object masterObj = map.getObject(id);
		if(masterObj!=null){
			SendableEntityCreator creator=map.getCreatorClass(masterObj);
			if(remove==null){
				// create Message
				Object refObject = creator.getSendableInstance(true);
				Iterator<String> keys=update.keys();
				while(keys.hasNext()){
					String key = keys.next();
					Object value=creator.getValue(masterObj, key);
					if(value==null){
						if(creator.getValue(refObject, key)==null){
							// Old Value is Standard
							return setValue(creator, masterObj, key, update.get(key));
						}else{
							//ERROR
							if(checkPrio(prio)){
								return setValue(creator, masterObj, key, update.get(key));
							}
						}
					}else if(creator.getValue(masterObj, key).equals(creator.getValue(refObject, key))){
						// Old Value is standard
						return setValue(creator, masterObj, key, update.get(key));
					}else{
						// ERROR
						if(checkPrio(prio)){
							return setValue(creator, masterObj, key, update.get(key));
						}
					}
				}
				return true;
			}else if(update==null){
				// delete Message
				Object refObject = creator.getSendableInstance(true);
				Iterator<String> keys=remove.keys();
				while(keys.hasNext()){
					String key = keys.next();
					Object value=creator.getValue(masterObj, key);
					if(checkValue(value, key, remove)){
						setValue(creator, masterObj, key, creator.getValue(refObject, key));
					}else if(checkPrio(prio)){
						setValue(creator, masterObj, key, creator.getValue(refObject, key));
					}
				}
				return true;
			}else{
				// update Message
				Iterator<String> keys=update.keys();
				while(keys.hasNext()){
					String key = keys.next();
					// CHECK WITH REMOVE key
					Object value=creator.getValue(masterObj, key);

					if(checkValue(value, key, remove)){
						setValue(creator, masterObj, key, update.get(key));
					}else if(checkPrio(prio)){
						setValue(creator, masterObj, key, update.get(key));
					}
				}
				return true;
				
			}
		}
		return false;
	}
	
	private boolean checkValue(Object value, String key, JsonObject jsonObj){
		if(value!=null){
			Object oldValue=jsonObj.get(key);
			if(oldValue instanceof JsonObject){
				// GLAUB ICH MAL
				String oldId=(String) ((JsonObject)oldValue).get(JsonIdMap.JSON_ID);
				return oldId.equals(map.getId(value));
			}else if(oldValue.equals(value)){
				return true;
			}
		}
		return false;
	}

	private boolean checkPrio(Object prio){
		Object myPrio=map.getPrio();
		if(prio!=null&&myPrio!=null){
			if(prio instanceof Integer&&myPrio instanceof Integer){
				Integer ref=(Integer) myPrio;
				return ref.compareTo((Integer) prio)>0;
			}else if(prio instanceof String&&myPrio instanceof String){
				String ref=(String) myPrio;
				return ref.compareTo((String) prio)>0;
			}
		}
		return false;
	}

	private boolean setValue(SendableEntityCreator creator, Object element, String key, Object newValue){
		if(newValue instanceof JsonObject){
			creator.setValue(element, key, map.readJson((JsonObject) newValue));
		}else{
			creator.setValue(element, key, newValue);
		}
		return true;
	}
}
