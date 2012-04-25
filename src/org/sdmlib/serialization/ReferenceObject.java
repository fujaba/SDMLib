package org.sdmlib.serialization;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ReferenceObject {
	private String jsonId;
	private SendableEntityCreator creator;
	private String property;
	private Object entity;
	private IdMap map;
	public ReferenceObject(SendableEntityCreator creator, String property, IdMap map, Object entity){
		this.creator=creator;
		this.property=property;
		this.entity=entity;
		this.map=map;
	}	
	public ReferenceObject(String jsonId, SendableEntityCreator creator, String property, IdMap map, Object entity){
		this.jsonId=jsonId;
		this.creator=creator;
		this.property=property;
		this.entity=entity;
		this.map=map;
	}
	public boolean execute(){
		Object assoc = map.getObject(jsonId);
		if(assoc!=null){
			creator.setValue(entity, property, assoc);
			return true;
		}
		return false;
	}
	public SendableEntityCreator getCreater(){
		return creator;
	}
	public Object getEntity(){
		return entity;
	}
	public String getProperty() {
		return property;
	}
	@Override
	public String toString() {
		return property+":"+entity.getClass().getName();
	}
}
