package org.sdmlib.serialization;

import java.util.HashMap;


public class CloneFilter extends IdMapFilter{
	public static final int OBJECT=0;
	public static final int SIMPLE=1;
	public static final int FULL=2;
	private int typ;
	private HashMap<Object, Object> assocs=new HashMap<Object, Object>();
	
	public CloneFilter(){
		
	}
	public CloneFilter(int typ){
		setTyp(typ);
	}
	
	public boolean isConvertable(IdMap map, Object entity, String property,
			Object value) {
		if (!super.isConvertable(map, entity, property, value)){
			return false;
		}
		return true;
	}

	public void addObject(Object reference, Object newObject){
		this.assocs.put(reference, newObject);
	}
	
	public boolean hasObject(Object objects){
		return assocs.containsKey(objects);
	}
	
	public Object getObject(Object objects){
		return assocs.get(objects);
	}
	
	public int getTyp() {
		return typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
		if(typ==OBJECT){
			this.deep=LASTDEEP;
		}
	}

}
