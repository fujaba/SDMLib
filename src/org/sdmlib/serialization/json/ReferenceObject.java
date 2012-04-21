package org.sdmlib.serialization.json;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ReferenceObject {
	private String jsonId;
	private SendableEntityCreator creator;
	private String property;
	private Object entity;
	private IdMap map;

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
}
