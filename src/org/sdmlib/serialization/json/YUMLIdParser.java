package org.sdmlib.serialization.json;
/*
Copyright (c) 2012 Stefan Lindel

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

/**
 * The Class YUMLIdParser.
 */
public class YUMLIdParser extends IdMap{
	
	/** The Constant URL. */
	public static final String URL="http://yuml.me/diagram/class/";
	
	/** The Constant for CLASS Diagramms. */
	public static final int CLASS = 1;
	
	/** The Constant for OBJECT Diagramms. */
	public static final int OBJECT = 2;
	
	/** The link property. */
	private HashMap<String, String> linkProperty = new HashMap<String, String>();
	
	/** The link cardinality. */
	private HashMap<String, String> linkCardinality = new HashMap<String, String>();
	
	/** The value yuml. */
	private HashMap<String, String> valueYUML = new HashMap<String, String>();
	
	/** The show line. */
	private boolean showLine;
	
	/** The type. */
	private int type;
	
	/** The show cardinality. */
	private boolean showCardinality;

	/**
	 * Instantiates a new yUML id parser.
	 */
	public YUMLIdParser() {
		super();
	}
	
	/**
	 * Instantiates a new yUML id parser.
	 *
	 * @param parent the parent
	 */
	public YUMLIdParser(IdMap parent) {
		super(parent);
	}

	/**
	 * Parses the object.
	 *
	 * @param object the object
	 * @return the string
	 */
	public String parseObject(Object object) {
		return parse(object, OBJECT);
	}

	/**
	 * Parses the class.
	 *
	 * @param object the object
	 * @param showCardinality the show cardinality
	 * @return the string
	 */
	public String parseClass(Object object, boolean showCardinality) {
		this.showCardinality=showCardinality;
		return parse(object, CLASS);
	}

	/**
	 * Parses the.
	 *
	 * @param object the object
	 * @param typ the typ
	 * @return the string
	 */
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

	/**
	 * Gets the yUML string.
	 *
	 * @param id the id
	 * @return the yUML string
	 */
	private String getYUMLString(String id) {
		String removeString = valueYUML.remove(id);
		if (removeString != null) {
			return removeString;
		}
		if (type == OBJECT) {
			if(isShowLine()){
				String text=id + " : " + getClassName(id);
				return "[" + text+"\\n"+new String(new char[text.length()]).replace("\0", "&oline;") + "]";
			}else{
				return "[" + id + " : " + getClassName(id) + "]";
			}
		} else {
			return "[" + id + "]";
		}

	}

	/**
	 * Parses the.
	 *
	 * @param object the object
	 * @return the string
	 */
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

	/**
	 * Gets the cardinality.
	 *
	 * @param cardinaltity the cardinaltity
	 * @return the cardinality
	 */
	private String getCardinality(String cardinaltity) {
		if (type == OBJECT) {
			return "";
		} else {
			return cardinaltity;
		}
	}

	/**
	 * Gets the class name.
	 *
	 * @param object the object
	 * @return the class name
	 */
	public String getClassName(Object object) {
		if (object instanceof String) {
			object = getObject((String) object);
		}
		String className = object.getClass().getName();
		return className.substring(className.lastIndexOf('.') + 1);
	}
	
	/**
	 * Checks if is show line.
	 *
	 * @return true, if is show line
	 */
	public boolean isShowLine() {
		return showLine;
	}
	
	/**
	 * Sets the show line.
	 *
	 * @param showLine the new show line
	 */
	public void setShowLine(boolean showLine) {
		this.showLine = showLine;
	}
}