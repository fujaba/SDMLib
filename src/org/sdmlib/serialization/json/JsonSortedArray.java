package org.sdmlib.serialization.json;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class JsonSortedArray extends JsonArray{
	private SortedMap<Object, JsonObject> mySortArrayList=new TreeMap<Object, JsonObject>();
			
	private String sortProp;
	private boolean propSort;

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
		JsonArray result = super.put(value);
		JsonObject json=(JsonObject) value;
		if(propSort){
			JsonObject props = (JsonObject) json.get(JsonIdMap.JSON_PROPS);
			if(props!=null){
				this.mySortArrayList.put(props.get(sortProp), json);
			}
		}else{
			this.mySortArrayList.put(json.get(sortProp), json);
		}
		return result;
	}
	@Override
	public List<Object> getElements() {
		return new ArrayList<Object>(mySortArrayList.values());
	}
}
