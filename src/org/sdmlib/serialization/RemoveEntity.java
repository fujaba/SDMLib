package org.sdmlib.serialization;

public class RemoveEntity {
	private IdMap<?> map;

	public RemoveEntity(IdMap<?> map, Object entity){
		this.map=map;
	}
	public boolean exeucte(Object entity){
		return map.remove(entity);
	}
}
