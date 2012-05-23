package org.sdmlib.serialization.json;

import java.util.LinkedHashSet;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class SDMLibJsonIdMap extends JsonIdMap{
	public static final String JSON_NEW_NEIGHBORS = "newNeighbors";
	
	@Override
	protected Object readJson(Object target, JsonObject jsonObject, LinkedHashSet<ReferenceObject> refs) {
		// JSONArray jsonArray;
		if (isId) {
			String jsonId = (String) jsonObject.get(JSON_ID);
			if (jsonId == null) {
				return target;
			}
			put(jsonId, target);

			getCounter().readId(jsonId);
		}
		if (jsonObject.has(JSON_PROPS)) {
			JsonObject jsonProp = (JsonObject) jsonObject.get(JSON_PROPS);
			SendableEntityCreator prototyp = getCreatorClass(target);
			String[] properties = prototyp.getProperties();
			if (properties != null) {
				//FIXME new version for ConfNet
				for (String property : properties) {
               Object obj = jsonProp.get(property);
               if (obj != null)
               {
                  parseValue(target, property, obj, prototyp, refs);
               }
               else
               {
                  obj = jsonProp.get(property + IdMap.REMOVE);
                  if (obj != null)
                  {
                     parseValue(target, property + IdMap.REMOVE, obj, prototyp, refs);
                  }
               }
            }
			}
		}
		return target;
	}
	
	@Override
	protected void parseValue(Object target, String property, Object value,
			SendableEntityCreator creator, LinkedHashSet<ReferenceObject> refs) {
		if (value != null)
		{
			if (value instanceof JsonArray)
			{
				JsonArray jsonArray = (JsonArray) value;
				for (int i = 0; i < jsonArray.length(); i++)
				{
					Object kid = jsonArray.get(i);
					if (kid instanceof JsonObject)
					{
						// got a new kid, create it
						JsonObject child=(JsonObject) kid;
						String className = (String) child.get(CLASS);
						String jsonId = (String) child.get(JSON_ID);
						//FIXME if (className == null&&jsonId!=null)
						if (className == null&&jsonId!=null&& child.length() == 1)
						{
							// It is a Ref
							refs.add(new ReferenceObject(jsonId, creator, property, this, target));
						}
						else
						{
							creator.setValue(target, property, readJson((JsonObject) kid));
						}
					}
					else
					{
						creator.setValue(target, property, kid);
					}
				}
			}
			else
			{
				if (value instanceof JsonObject) {
//					// got a new kid, create it
					JsonObject child=(JsonObject) value;
					String className = (String) child.get(CLASS);
					String jsonId = (String) child.get(JSON_ID);
					if (className == null && jsonId != null && child.length()==1) 
					{
						// It is a Ref
						refs.add(new ReferenceObject(jsonId, creator, property, this, target));
					}
					else if (className != null && jsonId != null )
					{
						creator.setValue(target, property, readJson((JsonObject) value));
					}
					else 
					{
					   creator.setValue(target, property, value);
					}
//FIXME					if (className == null&&jsonId!=null) {
//						// It is a Ref
//						refs.add(new ReferenceObject(jsonId, creator, property, this, target));
//					}else{
//						creator.setValue(target, property, readJson((JsonObject) value));
//					}
				}else{
					creator.setValue(target, property, value);
				}
			}
		}
	}
	public JsonIdMap withSessionId(String sessionId){
		setSessionId(sessionId);
		return this;
	}
}
