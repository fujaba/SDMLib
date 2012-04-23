package org.sdmlib.serialization.json;

import java.util.Collection;
import java.util.HashSet;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.IdMapFilter;

public class JsonFilter implements IdMapFilter{
	private int deep = ALLDEEP;
	private String[] exclusiveProperties;
	private HashSet<String> objects = new HashSet<String>();
	private boolean treesync=false;

	public JsonFilter() {

	}

	public JsonFilter(int deep) {
		this.deep = deep;
	}

	public JsonFilter(String... filter) {
		this.exclusiveProperties = filter;
	}

	public JsonFilter(int deep, String... filter) {
		this.deep = deep;
		this.exclusiveProperties = filter;
	}

	public int getDeep() {
		return deep;
	}

	public String[] getExcusiveProperties() {
		return exclusiveProperties;
	}

	public int setDeep(int value) {
		int oldValue = deep;
		if(value==DEEPER){
			if (deep != ALLDEEP) {
				deep = deep - 1;
			}
		}else{
			deep=value;
		}
		return oldValue;
	}

	public boolean existsObject(String id) {
		boolean result = objects.contains(id);
		return result;
	}
	public boolean addObject(String id){
		if(id!=null&&id.length()>0){
			this.objects.add(id);
			return true;
		}
		return false;
	}
	public boolean isConvertable(IdMap map, Object entity, String property, Object value) {
		if (getDeep() == LASTDEEP)
			return false;
		if (getExcusiveProperties() != null) {
			for (String prop : getExcusiveProperties()) {
				if (property.equalsIgnoreCase(prop)) {
					return false;
				}
			}
		}
		if(isTreesync()){
			if(!(value instanceof Collection<?>)){
				if(map.getCreatorClass(value)!=null){
					return false;
				}
			}
		}
		return !property.endsWith(JsonIdMap.REF_SUFFIX);
	}
	public String[] getObjects(){
		return objects.toArray(new String[objects.size()]);
	}

	public boolean isTreesync() {
		return treesync;
	}

	public void setTreesync(boolean treesync) {
		this.treesync = treesync;
	}
}
