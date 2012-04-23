package org.sdmlib.serialization.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.event.creater.DateCreator;
import org.sdmlib.serialization.interfaces.IdMapFilter;
import org.sdmlib.serialization.interfaces.MapUpdateListener;
import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class JsonIdMap extends IdMap{
	public static final String CLASS = "class";
	public static final String JSON_ID = "id";
	public static final String JSON_PROPS = "prop";
	public static final String REF_SUFFIX = "_ref";
	public static final String MAINITEM = "main";
	private MapUpdateListener updatelistener;
	private boolean simpleCheck;

	public JsonIdMap() {
		super();
		this.addCreator(new DateCreator());
	}

	public JsonObject toJsonObject(Object object) {
		return toJsonObject(object,  new JsonFilter());
	}

	private JsonObject toJsonObject(Object entity, IdMapFilter filter) {
		String id="";
		String className = entity.getClass().getName();

		SendableEntityCreator prototyp = getCreatorClasses(className);
		if(prototyp==null){
			return null;
		}
		
		if(!(prototyp instanceof NoIndexCreator)){
			id=getId(entity);	
		}
		JsonObject jsonProp = new JsonObject();

		String[] properties = prototyp.getProperties();
		filter.addObject(id);
		Object referenceObject = prototyp.getSendableInstance(true);
		if (properties != null) {
			for (String property : properties) {
				if(jsonProp.has(property)){
					throw new RuntimeException("Property duplicate:"+property+"("+className+")");
				}
				Object value = prototyp.getValue(entity, property);
				if (value != null) {
					boolean encoding=simpleCheck;
					if(!simpleCheck){
						Object refValue = prototyp.getValue(referenceObject,
								property);
						encoding=!value.equals(refValue);
					}
					if(encoding){
						boolean aggregation = filter.isConvertable(this, entity, property, value);
						if (value instanceof Collection<?>) {
							JsonArray subValues = new JsonArray();
							int oldValue = filter.setDeep(IdMapFilter.DEEPER);
							for (Object containee : ((Collection<?>) value)) {
								boolean agg=aggregation;
								SendableEntityCreator valueCreater = getCreatorClass(containee);
								if (valueCreater != null) {
									if(agg){
										String subId = this.getKey(containee);
										agg=!filter.existsObject(subId);
									}
									if (agg) {
//System.out.println("REF1: "+id+":"+property+"-"+this.getId(containee));
										subValues.put(toJsonObject(containee, filter));
									} else {
										
										JsonObject child = new JsonObject();
										child.put(JSON_ID, this.getId(containee));
										subValues.put(child);
//System.out.println("LINK2: "+id+":"+property+"-"+this.getId(containee));
									}
								} else {
									subValues.put(value);
								}
							}
							filter.setDeep(oldValue);
							jsonProp.put(property, subValues);
						} else {
							SendableEntityCreator valueCreater = getCreatorClass(value);
							if (valueCreater != null) {
								if (aggregation) {
									if(valueCreater instanceof NoIndexCreator){
									jsonProp.put(property,
											toJsonObject(value, filter));
									}else{
										String subId = this.getKey(value);
										if(!filter.existsObject(subId)) {
											int oldValue = filter.setDeep(IdMapFilter.DEEPER);
//System.out.println("REF3: "+id+":"+property+"-"+this.getId(value));
											jsonProp.put(property,
													toJsonObject(value, filter));

											filter.setDeep(oldValue);
										}else{
											JsonObject child = new JsonObject();
											child.put(JSON_ID, subId);											
											jsonProp.put(property, child);
//System.out.println("LINK4: "+id+":"+property+"-"+subId);
										}
									}
								} else {
									JsonObject child = new JsonObject();
									child.put(JSON_ID, this.getId(value));
									jsonProp.put(property, child);
//System.out.println("LINK5: "+id+":"+property+"-"+this.getId(value));
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
		HashSet<ReferenceObject> refs=new HashSet<ReferenceObject>();
		for (int i = 0; i <= len; i++) {
			JsonObject kidObject = jsonArray.getJSONObject(i);
			Object tmp = readJson(kidObject, refs);
			if(kidObject.has(MAINITEM)){
				result = tmp;
			}else if(i == 0) {
				result = tmp;
			}
		}
		for(ReferenceObject ref : refs){
			ref.execute();
		}
		return result;
	}

	public Object readJson(JsonObject jsonObject) {
		HashSet<ReferenceObject> refs=new HashSet<ReferenceObject>();
		Object mainItem=readJson(jsonObject, refs);
		for(ReferenceObject ref : refs){
			ref.execute();
		}
		return mainItem;
	}
	public Object readJson(Object target, JsonObject jsonObject){
		HashSet<ReferenceObject> refs=new HashSet<ReferenceObject>();
		Object mainItem=readJson(target, jsonObject, refs);
		for(ReferenceObject ref : refs){
			ref.execute();
		}
		return mainItem;
	}
	private Object readJson(JsonObject jsonObject, HashSet<ReferenceObject> refs) {
		Object result = null;
		Object className = jsonObject.get(CLASS);
	
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
			readJson(result, jsonObject, refs);
		}
		return result;
	}

	private Object readJson(Object target, JsonObject jsonObject, HashSet<ReferenceObject> refs) {
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
				for (String property : properties) {
					Object obj = jsonProp.get(property);
					Object oldObj = null;

					if (obj == null) {
						oldObj = jsonProp.get(property + REMOVE);
						parseValue(target, property + REMOVE, oldObj,
								prototyp, refs);
					} else {
						parseValue(target, property, obj, prototyp, refs);
					}
				}
			}
		}
		return target;
	}

		private void parseValue(Object target, String property, Object value,
			SendableEntityCreator creator, HashSet<ReferenceObject> refs) {
		if (value != null) {
			if (value instanceof JsonArray) {
				JsonArray jsonArray = (JsonArray) value;
				for (int i = 0; i < jsonArray.length(); i++) {
					Object kid = jsonArray.get(i);
					if (kid instanceof JsonObject) {
//						// got a new kid, create it
						JsonObject child=(JsonObject) kid;
						String className = (String) child.get(CLASS);
						String jsonId = (String) child.get(JSON_ID);
						if (className == null&&jsonId!=null) {
							// It is a Ref
							refs.add(new ReferenceObject(jsonId, creator, property, this, target));
						}else{
							creator.setValue(target, property, readJson((JsonObject) kid));
						}
					}else{
						creator.setValue(target, property, kid);
					}
				}
			} else {
				if (value instanceof JsonObject) {
//					// got a new kid, create it
					JsonObject child=(JsonObject) value;
					String className = (String) child.get(CLASS);
					String jsonId = (String) child.get(JSON_ID);
					if (className == null&&jsonId!=null) {
						// It is a Ref
						refs.add(new ReferenceObject(jsonId, creator, property, this, target));
					}else{
						creator.setValue(target, property, readJson((JsonObject) value));
					}
				}else{
					creator.setValue(target, property, value);
				}
			}
		}
	}

	public JsonArray toJsonArray(Object object) {
		return toJsonArray(object, null);
	}

	public JsonArray toJsonArray(Object object, IdMapFilter filter) {
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
	
	private void toJsonArray(JsonArray jsonArray, Object entity,
			IdMapFilter filter) {
		toJsonArray(jsonArray, entity, filter, getId(entity));
	}
	private void toJsonArray(JsonArray jsonArray, Object entity,
				IdMapFilter filter, String id) {
		String className = entity.getClass().getName();

		JsonObject jsonObject = new JsonObject();
		if (isId) {
			jsonObject.put(JSON_ID, id);
		}
		jsonObject.put(CLASS, className);
		jsonArray.put(jsonObject);

		SendableEntityCreator prototyp = getCreatorClasses(className);
		String[] properties = prototyp.getProperties();
		filter.addObject(id);

		if (properties != null) {
			JsonObject jsonProps = new JsonObject();
			for (String property : properties) {
				Object value = prototyp.getValue(entity, property);
				if (value != null) {
					boolean aggregation = filter.isConvertable(this, entity, property, value);
					if (value instanceof Collection) {
						Collection<?> list = ((Collection<?>) value);
						if (list.size() > 0) {
							JsonArray refArray = new JsonArray();
							for (Object containee : list) {
								if (containee != null) {
									SendableEntityCreator containeeCreater = getCreatorClass(containee);
									if (containeeCreater != null) {
										String subId = this.getId(containee);
										JsonObject child = new JsonObject();
										child.put(JSON_ID, subId);
										refArray.put(child);
										if (aggregation) {
											if (!filter.existsObject(subId)) {
												int oldValue = filter.setDeep(IdMapFilter.DEEPER);
												this.toJsonArray(jsonArray,
														containee, filter, subId);
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
							JsonObject child = new JsonObject();
							child.put(JSON_ID, subId);
							jsonProps.put(property, child);
							if (aggregation) {
								int oldValue = filter.setDeep(IdMapFilter.DEEPER);
								this.toJsonArray(jsonArray, value, filter, subId);
								filter.setDeep(oldValue);
							}
						} else {
							jsonProps.put(property, value);
						}
					}
				}
			}
			if (jsonProps.length() > 0) {
				jsonObject.put(JSON_PROPS, jsonProps);
			}
		}
	}

	public String toToYUmlObject(Object object) {
		YUMLIdParser parser=new YUMLIdParser(this);
		return parser.parseObject(object);
	}
	public String toToYUmlClass(Object object) {
		YUMLIdParser parser=new YUMLIdParser(this);
		return parser.parseClass(object, false);
	}
	
	public void setUpdateMsgListener(MapUpdateListener listener){
		this.updatelistener=listener;
	}

	public boolean sendUpdateMsg(JsonObject jsonObject) {
		return this.updatelistener.sendUpdateMsg(jsonObject);
	}
	
	public JsonObject toJsonObjectById(String id){
		return toJsonObject(super.getObject(id), new JsonFilter(0));
	}

	public void toJsonArrayByIds(ArrayList<String> suspendIdList) {
		JsonArray children=new JsonArray();
		for(String childId : suspendIdList){
			children.put(toJsonObjectById(childId));
		}
		JsonObject sendObj=new JsonObject();
		sendObj.put(IdMap.UPDATE, children);
		sendUpdateMsg(sendObj);
	}

	public boolean isSimpleCheck() {
		return simpleCheck;
	}

	public void setSimpleCheck(boolean simpleCheck) {
		this.simpleCheck = simpleCheck;
	}
}
