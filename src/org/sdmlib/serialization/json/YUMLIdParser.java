package org.sdmlib.serialization.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;


public class YUMLIdParser extends IdMap<SendableEntityCreator>{
	public static final String URL="http://yuml.me/diagram/class/";
	public static final int CLASS = 1;
	public static final int OBJECT = 2;
	private HashMap<String, String> linkProperty = new HashMap<String, String>();
	private HashMap<String, String> linkCardinality = new HashMap<String, String>();
	private HashMap<String, String> valueYUML = new HashMap<String, String>();
	private int type;
	private boolean showCardinality;
	

	public YUMLIdParser() {
		super();
	}
	public YUMLIdParser(IdMap<SendableEntityCreator> parent) {
		super(parent);
	}

	public String parseObject(Object object) {
		return parse(object, OBJECT);
	}

	public String parseClass(Object object, boolean showCardinality) {
		this.showCardinality=showCardinality;
		return parse(object, CLASS);
	}

	public String parse(Object object, int typ) {
		String result = "";
		boolean isFirst = true;
		this.type = typ;
		String id = parse(object);
		// Links auflösen
		ArrayList<String> keys = new ArrayList<String>();
		keys.addAll(linkProperty.keySet());
		if (keys.size() > 0) {
			while (keys.size() > 0) {
				String key = keys.remove(0);
				String[] itemsId = key.split("-");
				
				String first = getYUMLString(itemsId[0]);
				String second = getYUMLString(itemsId[1]);
				if (!isFirst) {
					result += ",";
				}

				if(type==OBJECT){
					result += first+"-" + second;
				}else{
					String firstCardNo=linkCardinality.get(key);
					String secondCardNo=linkCardinality.get(itemsId[1] + "-" + itemsId[0]);
					result += first;
					if(showCardinality){
						String firstCardName = linkProperty.get(key);
						String secondCardName = linkProperty.get(itemsId[1] + "-" + itemsId[0]);
						result += firstCardName+ ": "+firstCardNo+"-";
						if(secondCardName!=null){
							result += secondCardName+ ": "+secondCardNo;
						}
					}else{
						result += firstCardNo+"-";
						if(secondCardNo!=null){
							result += secondCardNo;
						}
					}
					result += second;
				}
				keys.remove(itemsId[1] + "-" + itemsId[0]);
				isFirst = false;
			}
		} else {
			result = getYUMLString(id);
		}
		return result;
	}

	private String getYUMLString(String id) {
		String removeString = valueYUML.remove(id);
		if (removeString != null) {
			return removeString;
		}
		if (type == OBJECT) {
			return "[" + id + " : " + getClassName(id) + "]";
		} else {
			return "[" + id + "]";
		}

	}

	private String parse(Object object) {
		String className="";
		String id="";
		String mainKey="";
		SendableEntityCreator prototyp=null;
		String result = "[";
		
		if(object!=null){
			mainKey = getId(object);
			prototyp = getCreatorClass(object);
			className = object.getClass().getName();
			className = className.substring(className.lastIndexOf('.') + 1);
			if (type == OBJECT) {
//				id=getId(object);
				id=mainKey;
				result += id+ " : " + className;
			} else {
				result += className;
				id = className;
			}
		}
		
		if (prototyp != null && !valueYUML.containsKey(id)) {
			valueYUML.put(id, null);
			if (prototyp.getProperties().length > 0) {
				result += "|";
				boolean first = true;
				for (String property : prototyp.getProperties()) {
					Object value = prototyp.getValue(object, property);
					if (value != null) {
						if (value instanceof Collection<?>) {
							for (Object containee : ((Collection<?>) value)) {
								String subId = parse(containee);
								String key = id + "-" + subId;
								linkProperty.put(key, property);
								linkCardinality
										.put(key, getCardinality("0..n"));
							}
						} else {
							SendableEntityCreator valueCreater = getCreatorClass(value);
							if (valueCreater != null) {
								String subId = parse(value);
								String key = id + "-" + subId;
								linkProperty.put(key, property);
								linkCardinality
										.put(key, getCardinality("0..1"));
							} else {
								if (!first) {
									result += ";";
								}
								// plain attr just add text
								if(type==OBJECT){
								result += property + "=" + value;
								}else{
									result += property + ":" + value.getClass().getName();
								}
								first = false;
							}
						}
					}
				}
			}
			result += "]";
			put(id, object);
			valueYUML.put(id, result);
//		} else if (type == CLASS) {
//			String className = object.getClass().getName();
//			className = className.substring(className.lastIndexOf('.') + 1);
//			id = className;
		}
		return id;
	}

	private String getCardinality(String cardinaltity) {
		if (type == OBJECT) {
			return "";
		} else {
			return cardinaltity;
		}
	}

	public String getClassName(Object object) {
		if (object instanceof String) {
			object = getObject((String) object);
		}
		String className = object.getClass().getName();
		return className.substring(className.lastIndexOf('.') + 1);
	}
}