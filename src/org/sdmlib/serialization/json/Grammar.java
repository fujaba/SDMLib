package org.sdmlib.serialization.json;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
import java.util.Iterator;
import java.util.Map.Entry;

import org.sdmlib.serialization.Filter;
import org.sdmlib.serialization.IdMapEncoder;
import org.sdmlib.serialization.event.MapEntry;
import org.sdmlib.serialization.interfaces.IdMapCounter;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreatorNoIndex;

public class Grammar {
	/**
	 * @param jsonObject
	 * @return the props of theJsonObject
	 */
	public JsonObject getReadProperties(JsonObject jsonObject, IdMapEncoder map, Filter filter, boolean isId) {
		if(isId){
			if (jsonObject.has(JsonIdMap.JSON_PROPS)) {
				return jsonObject.getJsonObject(JsonIdMap.JSON_PROPS);
			}
		}else{
			JsonObject props=new JsonObject();
			for(Iterator<MapEntry<String>> i=jsonObject.iterator();i.hasNext();){
				Entry<String, Object> item = i.next();
				if(!JsonIdMap.CLASS.equalsIgnoreCase(item.getKey())){
					props.put(item.getKey(), item.getValue());
				}
			}
			return props;
		}
		return null;
	}

	/**
	 * @param jsonObject
	 * @return the Creator for this JsonObject
	 */
	public SendableEntityCreator getReadCreator(JsonObject jsonObject,
			IdMapEncoder map) {
		Object className = jsonObject.get(JsonIdMap.CLASS);
		return map.getCreatorClassName((String) className, true);
	}

	/**
	 * @param jsonObject
	 * @return the Creator for this JsonObject
	 */
	public SendableEntityCreator getWriteCreator(Object modelItem,
			String className, IdMapEncoder map) {
		return map.getCreatorClassName(className, true);
	}

	public JsonObject getWriteObject(IdMapEncoder map, SendableEntityCreator prototyp,
			String className, String id, JsonObject jsonProp, Filter filter) {
		JsonObject json = new JsonObject();
		if (prototyp instanceof SendableEntityCreatorNoIndex) {
			Iterator<String> keys = jsonProp.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				json.put(key, jsonProp.get(key));
			}
			json.put(JsonIdMap.CLASS, className);
			return json;
		}
		if (filter.isId(map, jsonProp, className)) {
			json.put(IdMapEncoder.ID, id);
		}
		json.put(JsonIdMap.CLASS, className);

		if (jsonProp.size() > 0) {
			json.put(JsonIdMap.JSON_PROPS, jsonProp);
		}
		return json;
	}
	
	public boolean hasReadValue(JsonObject json, String property){
		return json.has(property);
	}
	public String getReadValue(JsonObject json, String property){
		return json.getString(property);
	}

	public String getWriteId(Object obj, IdMapCounter counter) {
		return null;
	}
}
