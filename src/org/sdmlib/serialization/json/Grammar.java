package org.sdmlib.serialization.json;

import java.util.Iterator;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class Grammar {
	/**
	 * @param jsonObject
	 * @return the props of theJsonObject 
	 */
	public JsonObject getJsonObjectProperties(JsonObject jsonObject) {
		if(jsonObject.has(JsonIdMap.JSON_PROPS)){
			return jsonObject.getJsonObject(JsonIdMap.JSON_PROPS);
		}
		return null;
	}

	/**
	 * @param jsonObject
	 * @return the Creator for this JsonObject
	 */
	public SendableEntityCreator getJsonObjectCreator(JsonObject jsonObject, IdMap map) {
		Object className = jsonObject.get(JsonIdMap.CLASS);
		return map.getCreatorClasses((String) className);
	}

	/**
	 * @param jsonObject
	 * @return the Creator for this JsonObject
	 */
	public SendableEntityCreator getObjectCreator(Object modelItem, IdMap map) {
		return map.getCreatorClass(modelItem);
	}

	public JsonObject getJsonObject(IdMap map,
			SendableEntityCreator prototyp, String className, String id,
			JsonObject jsonProp, JsonFilter filter) {
		JsonObject json=new JsonObject();
		if (prototyp instanceof NoIndexCreator) {
			Iterator<String> keys = jsonProp.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				json.put(key, jsonProp.get(key));
			}
			json.put(JsonIdMap.CLASS, className);
			return json;
		}
		if (map.getCounter().isId()&&filter.isId()) {
			json.put(IdMap.ID, id);
		}
		json.put(JsonIdMap.CLASS, className);

		if (jsonProp.size() > 0) {
			json.put(JsonIdMap.JSON_PROPS, jsonProp);
		}
		return json;
	}
}
