package org.sdmlib.serialization.json;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class JsonSortedArray extends JsonArray{
	private SortedMap<Object, JsonObject> mySortArrayList;
	
	private String sortProp=JsonIdMap.JSON_ID;
	private boolean propSort;
	
	
	public JsonSortedArray(){
		mySortArrayList=new TreeMap<Object, JsonObject>();
	}
	
	public JsonSortedArray(String property){
		mySortArrayList=new TreeMap<Object, JsonObject>();
		this.setSortProp(property);
	}
	
	public JsonSortedArray(Comparator<Object> comparator){
		mySortArrayList=new TreeMap<Object, JsonObject>(comparator);
	}
	
	public JsonSortedArray(Comparator<Object> comparator, String property){
		mySortArrayList=new TreeMap<Object, JsonObject>(comparator);
		this.setSortProp(property);
	}

	public void setSortProp(String property) {
		if(property.startsWith(JsonIdMap.JSON_PROPS+".")){
			this.sortProp=property.substring(JsonIdMap.JSON_PROPS.length()+1);
			propSort=true;
		}else{
			this.sortProp=property;
			propSort=false;
		}
	}
	
	@Override
	public JsonArray put(Object value) {
		super.put(value);
		JsonObject json=(JsonObject) value;
		if(propSort){
			JsonObject props = (JsonObject) json.get(JsonIdMap.JSON_PROPS);
			if(props!=null){
				this.mySortArrayList.put(props.get(sortProp), json);
			}
		}else{
			this.mySortArrayList.put(json.get(sortProp), json);
		}
		return this;
	}
	@Override
	public List<Object> getElements() {
		return new ArrayList<Object>(mySortArrayList.values());
	}
}
