package org.sdmlib.serialization.json;
/*
Copyright (c) 2012, Stefan Lindel
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. All advertising materials mentioning features or use of this software
   must display the following acknowledgement:
   This product includes software developed by Stefan Lindel.
4. Neither the name of contributors may be used to endorse or promote products
   derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.IdMapFilter;
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
		return parse(object, OBJECT, new IdMapFilter());
	}
	
	/**
	 * Parses the object.
	 *
	 * @param object the object
	 * @return the string
	 */
	public String parseObject(Object object, IdMapFilter filter) {
		return parse(object, OBJECT, filter);
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
		return parse(object, CLASS, new IdMapFilter());
	}
	/**
	 * Parses the class.
	 *
	 * @param object the object
	 * @param showCardinality the show cardinality
	 * @param IdMapFilter Filter function
	 * @return the string
	 */
	public String parseClass(Object object, boolean showCardinality, IdMapFilter filter) {
		this.showCardinality=showCardinality;
		return parse(object, CLASS, filter);
	}

	/**
	 * Parses the.
	 *
	 * @param object the object
	 * @param typ the typ
	 * @param IdMapFilter Filter for serialisation
	 * @return the string
	 */
	public String parse(Object object, int typ, IdMapFilter filter) {
		String result = "";
		boolean isFirst = true;
		this.type = typ;
		linkProperty.clear();
		linkCardinality.clear();
		valueYUML.clear();
		String id = parse(object, filter);
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
	private String parse(Object object, IdMapFilter filter) {
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
					if (value == null) {
						continue;
					}
					int oldValue = filter.setDeep(IdMapFilter.DEEPER);
					if (value instanceof Collection<?>) {
						for (Object containee : ((Collection<?>) value)) {
							if(!filter.isRegard(this, object, property, value)){
								continue;
							}
							if(!filter.isConvertable(this, object, property, value)){
								continue;
							}

							String subId = parse(containee, filter);
							String key = id + "-" + subId;
							linkProperty.put(key, property);
							linkCardinality
									.put(key, getCardinality("0..n"));
						}
					} else {
						if(!filter.isRegard(this, object, property, value)){
							continue;
						}
						if(!filter.isConvertable(this, object, property, value)){
							continue;
						}
						SendableEntityCreator valueCreater = getCreatorClass(value);
						if (valueCreater != null) {
							String subId = parse(value, filter);
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
					filter.setDeep(oldValue);
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