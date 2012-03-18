package org.sdmlib.serialization.json;

import java.util.Collection;
import java.util.Iterator;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.event.creater.DateCreator;
import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.UpdateListener;

public class JsonIdMap extends IdMap<SendableEntityCreator>{
	public static final String REMOVE_SUFFIX = ".old";
	public static final String CLASS = "class";
	public static final String JSON_ID = "id";
	public static final String JSON_PROPS = "prop";
	public static final String REF_SUFFIX = "_ref";
	public static final String JSONVALUE = "value";
	private UpdateListener updatelistener;

	public JsonIdMap() {
		super();
		this.addCreater(new DateCreator());
	}

	public JsonObject toJsonObject(Object object) {
		return toJsonObject(object, null);
	}

	private boolean isConvertable(JsonFilter filter, String property) {
		if (filter.getDeep() == 0)
			return false;
		if (filter.getExcusiveProperties() != null) {
			for (String prop : filter.getExcusiveProperties()) {
				if (property.equalsIgnoreCase(prop)) {
					return false;
				}
			}
		}
		return !property.endsWith(REF_SUFFIX);
	}

	private void writeJsonObject(JsonArray parent, Object value,
			boolean aggregation) {
		SendableEntityCreator valueCreater = getCreatorClass(value);
		if (valueCreater != null) {
			if (aggregation) {
				parent.put(toJsonObject(value, null));
			} else {
				parent.put(this.getId(value));
			}
		} else {
			parent.put(value);
		}
	}

	private JsonObject toJsonObject(Object object, JsonFilter filter) {
		String id="";
		String className = object.getClass().getName();

		if (filter == null) {
			filter = new JsonFilter();
		}

		SendableEntityCreator prototyp = getCreatorClass(object);
		
		if(!(prototyp instanceof NoIndexCreator)){
			id=getId(object);	
		}
		JsonObject jsonProp = new JsonObject();

		String[] properties = prototyp.getProperties();
		Object referenceObject = prototyp.getSendableInstance(true);
		if (properties != null) {
			for (String property : properties) {
				Object value = prototyp.getValue(object, property);
				if (value != null) {
					Object refValue = prototyp.getValue(referenceObject,
							property);
					if (!value.equals(refValue)) {
						boolean aggregation = isConvertable(filter, property);
						if (value instanceof Collection<?>) {
							JsonArray subValues = new JsonArray();
							for (Object containee : ((Collection<?>) value)) {
								writeJsonObject(subValues, containee,
										aggregation);
							}
							jsonProp.put(property, subValues);
						} else {
							SendableEntityCreator valueCreater = getCreatorClass(value);
							if (valueCreater != null) {
								if (aggregation) {
									if(valueCreater instanceof NoIndexCreator){
									jsonProp.put(property,
											toJsonObject(value, filter));
									}else{
										String subId = this.getId(value);
										if (!filter.existsObject(subId)) {
											int oldValue = filter.setDeeper();
											jsonProp.put(property,
													toJsonObject(value, filter));
											filter.setDeep(oldValue);
										}
									}
								} else {
									jsonProp.put(property, getId(value));
								}
							} else {
								jsonProp.put(property, value);
							}
						}
					}
				}
			}
		}
		JsonObject jsonObject = new JsonObject();
		if(prototyp instanceof NoIndexCreator){
			Iterator<String> keys = jsonProp.keys();
			while(keys.hasNext()){
				String key=keys.next();
				jsonObject.put(key, jsonProp.get(key));
			}
			jsonObject.put(CLASS, className);
		}else{
			if (isId) {
				jsonObject.put(JSON_ID, id);
			}
			jsonObject.put(CLASS, className);
	
			if (jsonProp.length() > 0) {
				jsonObject.put(JSON_PROPS, jsonProp);
			}
		}
		return jsonObject;
	}

	public Object readJson(JsonArray jsonArray) {
		Object result = null;
		int len = jsonArray.length() - 1;
		// Add all Objects
		for (int i = 0; i <= len; i++) {
			JsonObject kidObject = jsonArray.getJSONObject(i);
			readJson(kidObject, false);
		}
		for (int i = 0; i <= len; i++) {
			JsonObject kidObject = jsonArray.getJSONObject(i);
			Object tmp = readJson(kidObject);
			if (i == 0) {
				result = tmp;
			}
		}
		return result;
	}

	public Object readJson(JsonObject jsonObject) {
		return readJson(jsonObject, true);
	}

	private Object readJson(JsonObject jsonObject, boolean properties) {
		Object result = null;
		Object className = jsonObject.get(CLASS);

		if (className != null) {
			SendableEntityCreator typeInfo = getCreatorClasses((String) className);

			if (typeInfo != null) {
				if (isId) {
					String jsonId = (String) jsonObject.get(JSON_ID);
					if (jsonId == null) {
						return null;
					}
					result = getObject(jsonId);
				}
				if (result == null) {
					result = typeInfo.getSendableInstance(false);
				}
				readJson(result, jsonObject, properties);
			}
		}

		return result;
	}

	public void readJson(Object target, JsonObject jsonObject) {
		readJson(target, jsonObject, true);
	}

	private void readJson(Object target, JsonObject jsonObject,
			boolean isProperties) {
		// JSONArray jsonArray;
		if (isId) {
			String jsonId = (String) jsonObject.get(JSON_ID);
			if (jsonId == null) {
				return;
			}
			put(jsonId, target);

			getCounter().readId(jsonId);
		}
		if (isProperties && jsonObject.has(JSON_PROPS)) {
			JsonObject jsonProp = (JsonObject) jsonObject.get(JSON_PROPS);
			SendableEntityCreator prototyp = getCreatorClass(target);
			String[] properties = prototyp.getProperties();
			if (properties != null) {
				for (String property : properties) {
					Object obj = jsonProp.get(property);
					Object oldObj = null;

					if (obj == null) {
						oldObj = jsonProp.get(property + REMOVE_SUFFIX);
						parseValue(target, property + REMOVE_SUFFIX, oldObj,
								prototyp);
					} else {
						parseValue(target, property, obj, prototyp);
					}
				}
			}
		}
	}

	private Object parseValue(Object target, Object kid) {
		if (kid instanceof JsonObject) {
			// got a new kid, create it
			return readJson((JsonObject) kid);
		} else {
			// got a reference
			// got the id, ask the map
			if (kid instanceof String) {
				Object kidObj = this.getObject((String) kid);
				if (kidObj != null) {
					return kidObj;
				} else {
					// MayBe a Attribute
					return kid;
				}
			} else {
				return kid;
			}
		}
	}

	private void parseValue(Object target, String property, Object value,
			SendableEntityCreator creater) {
		if (value != null) {
			if (value instanceof JsonArray) {
				JsonArray jsonArray = (JsonArray) value;
				for (int i = 0; i < jsonArray.length(); i++) {
					Object kid = jsonArray.get(i);
					creater.setValue(target, property, parseValue(target, kid));
				}
			} else {
					creater.setValue(target, property,
							parseValue(target, value));
			}
		}
	}

	public JsonArray toJsonArray(Object object) {
		return toJsonArray(object, null);
	}

	public JsonArray toJsonArray(Object object, JsonFilter filter) {
		JsonArray jsonArray = new JsonArray();
		if (filter == null) {
			filter = new JsonFilter();
		}
		toJsonArray(jsonArray, object, filter);
		return jsonArray;
	}

	public JsonArray toJsonSortedArray(Object object, String property) {
		JsonSortedArray jsonArray = new JsonSortedArray();
		jsonArray.setSortProp(property);
		JsonFilter filter = new JsonFilter();
		toJsonArray(jsonArray, object, filter);
		return jsonArray;
	}
	
	private void toJsonArray(JsonArray jsonArray, Object object,
			JsonFilter filter) {
		
		String id = getId(object);
		String className = object.getClass().getName();

		SendableEntityCreator prototyp = getCreatorClasses(className);
		String[] properties = prototyp.getProperties();
		JsonObject jsonProps = new JsonObject();
		if (properties != null) {
			for (String property : properties) {
				Object value = prototyp.getValue(object, property);
				if (value != null) {
					boolean aggregation = isConvertable(filter, property);
					if (value instanceof Collection) {
						Collection<?> list = ((Collection<?>) value);
						if (list.size() > 0) {
							JsonArray refArray = new JsonArray();
							for (Object containee : list) {
								if (containee != null) {
									SendableEntityCreator containeeCreater = getCreatorClass(containee);
									if (containeeCreater != null) {
										String subId = this.getId(containee);
										refArray.put(subId);
										if (aggregation) {
											if (!filter.existsObject(subId)) {
												int oldValue = filter
														.setDeeper();
												this.toJsonArray(jsonArray,
														containee, filter);
												filter.setDeep(oldValue);
											}
										}
									} else {
										jsonArray.put(containee);
									}
								}
							}
							jsonProps.put(property, refArray);
						}
					} else {
						SendableEntityCreator valueCreater = getCreatorClass(value);
						if (valueCreater != null) {
							String subId = this.getId(value);
							jsonProps.put(property, subId);
							if (aggregation) {
								if (!filter.existsObject(subId)) {
									int oldValue = filter.setDeeper();
									this.toJsonArray(jsonArray, value, filter);
									filter.setDeep(oldValue);
								}
							}
						} else {
							jsonProps.put(property, value);
						}
					}
				}
			}
		}
		JsonObject jsonObject = new JsonObject();
		if (isId) {
			jsonObject.put(JSON_ID, id);
		}
		jsonObject.put(CLASS, className);
		if (jsonProps.length() > 0) {
			jsonObject.put(JSON_PROPS, jsonProps);
		}
		jsonArray.put(jsonObject);
	}

	public String toToYUmlObject(Object object) {
		YUMLIdParser parser=new YUMLIdParser(this);
		return parser.parseObject(object);
	}
	public String toToYUmlClass(Object object) {
		YUMLIdParser parser=new YUMLIdParser(this);
		return parser.parseClass(object, false);
	}
	
	public void setUpdateMsgListener(UpdateListener listener){
		this.updatelistener=listener;
	}

	public void sendUpdateMsg(JsonObject jsonObject) {
		this.updatelistener.sendUpdateMsg(jsonObject);
	}
   public JsonIdMap withSessionId(String sessionID)
   {
      setSessionId(sessionID);
      return this;
   }
}
