package org.sdmlib.serialization.yuml;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
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

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.IdMapFilter;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
/**
 * The Class YUMLIdParser.
 */

public class YUMLIdParser extends IdMap {
	/** The Constant URL. */
	public static final String URL = "http://yuml.me/diagram/class/";

	/** The Constant for CLASS Diagramms. */
	public static final int CLASS = 1;

	/** The Constant for OBJECT Diagramms. */
	public static final int OBJECT = 2;

	/**
	 * Instantiates a new yUML id parser.
	 */
	public YUMLIdParser() {
		super();
	}

	/**
	 * Instantiates a new yUML id parser.
	 *
	 * @param parent
	 *			the parent
	 */
	public YUMLIdParser(IdMap parent) {
		super(parent);
	}

	/**
	 * Parses the object.
	 *
	 * @param object
	 *			the object
	 * @return the string
	 */
	public String parseObject(Object object) {
		return parse(object, OBJECT, new YUmlIdMapFilter(true));
	}

	/**
	 * Parses the object.
	 *
	 * @param object the object
	 * @param filter  Filter for Serialisation
	 * @return the string
	 */
	public String parseObject(Object object, YUmlIdMapFilter filter) {
		return parse(object, OBJECT, filter);
	}

	/**
	 * Parses the class.
	 *
	 * @param object
	 *			the object
	 * @param showCardinality
	 *			the show cardinality
	 * @return the string
	 */
	public String parseClass(Object object, boolean showCardinality ) {
		return parse(object, CLASS, new YUmlIdMapFilter(showCardinality));
	}

	/**
	 * Parses the class.
	 *
	 * @param object           the object to Serialisation
	 * @param filter           Filter for Serialisation
	 * @return the string
	 */
	public String parseClass(Object object, YUmlIdMapFilter filter) {
		return parse(object, CLASS, filter);
	}

	/**
	 * Parses the.
	 *
	 * @param object  the object to Serialisation
	 * @param typ     Is it a OBJECT OR A CLASS diagram
	 * @param filter  Filter for Serialisation
	 * @param showCardinality  the show cardinality
	 * @return the Object as String
	 */
	protected String parse(Object object, int typ, YUmlIdMapFilter filter) {
		String id = parse(object, filter, typ);
		// Links aufloesen
		Set<String> keySet = filter.getLinkPropertys();
		if (keySet.size() > 0) {
			Iterator<String> i = keySet.iterator();

			String key = i.next();
			String result = getUMLText(key, typ, filter);

			while (i.hasNext()) {
				result += "," + getUMLText(i.next(), typ, filter);
			}
			return result;
		}
		return getYUMLString(id, typ, filter);
	}

	/**
	 * @param key of the Object
	 * @param typ Is it a OBJECT OR A CLASS diagram
	 * @param showCardinality  the show cardinality
	 * @return Object as String
	 */
	private String getUMLText(String key, int typ, YUmlIdMapFilter filter) {
		String[] itemsId = key.split("-");

		String first = getYUMLString(itemsId[0], typ, filter);
		String second = getYUMLString(itemsId[1], typ, filter);
		String result;
		if (typ == OBJECT) {
			result = first + "-" + second;
		} else {
			String firstCardNo = filter.getLinkCardinality(key);
			String secondCardNo = filter.getLinkCardinality(itemsId[1] + "-" + itemsId[0]);
			result = first;
			if ( filter.isShowCardinality() ) {
				String firstCardName = filter.getLinkProperty(key);
				String secondCardName = filter.getLinkProperty(itemsId[1] + "-" + itemsId[0]);
				result += firstCardName + ": " + firstCardNo + "-";
				if (secondCardName != null) {
					result += secondCardName + ": " + secondCardNo;
				}
			} else {
				result += firstCardNo + "-";
				if (secondCardNo != null) {
					result += secondCardNo;
				}
			}
			result += second;
		}
		return result;
	}

	/**
	 * Gets the yUML string.
	 *
	 * @param id
	 *			the id of object
	 * @param typ
	 *			Is it a OBJECT OR A CLASS diagram
	 * @return the yUML string
	 */
	private String getYUMLString(String id, int typ, YUmlIdMapFilter filter) {
		String removeString = filter.removeValueYUML(id);
		if (removeString != null) {
			return removeString;
		}
		if (typ == OBJECT) {
			if (filter.isShowLine()) {
				String text = id + " : " + getClassName(id);
				return "["
						+ text
						+ "\\n"
						+ new String(new char[text.length()]).replace("\0",
								"&oline;") + "]";
			}
			return "[" + id + " : " + getClassName(id) + "]";
		}
		return "[" + id + "]";
	}

	/**
	 * Parses the instance Object.
	 *
	 * @param object
	 *			the object
	 * @param typ
	 *			Is it a OBJECT OR A CLASS diagram
	 * @param filter
	 *			Filter for converting
	 * @return the string
	 */
	private String parse(Object object, YUmlIdMapFilter filter, int typ) {
		String className = "";
		String id = "";
		String mainKey = "";
		SendableEntityCreator prototyp = null;
		String result = "[";

		if (object != null) {
			mainKey = getId(object);
			prototyp = getCreatorClass(object);
			className = object.getClass().getName();
			className = className.substring(className.lastIndexOf('.') + 1);
			if (typ == OBJECT) {
				id = mainKey;
				result += id + " : " + className;
			} else {
				result += className;
				id = className;
			}
		}

		if (prototyp != null && !filter.containsKeyValueYUML(id)) {
			filter.addValueYUML(id,  null);
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
							if (!filter.isRegard(this, object, property,
									containee, true)) {
								continue;
							}
							if (!filter.isConvertable(this, object, property,
									containee, true)) {
								continue;
							}

							String subId = parse(containee, filter, typ);
							String key = id + "-" + subId;
							filter.addLinkProperty(key, property);
							filter.addLinkCardinality(key, getCardinality("0..n", typ));
						}
					} else {
						if (!filter.isRegard(this, object, property, value,
								false)) {
							continue;
						}
						if (!filter.isConvertable(this, object, property,
								value, false)) {
							continue;
						}
						SendableEntityCreator valueCreater = getCreatorClass(value);
						if (valueCreater != null) {
							String subId = parse(value, filter, typ);
							String key = id + "-" + subId;
							filter.addLinkProperty(key, property);
							filter.addLinkCardinality(key, getCardinality("0..n", typ));
						} else {
							if (!first) {
								result += ";";
							}
							// plain attr just add text
							if (typ == OBJECT) {
								result += property + "=" + value;
							} else {
								result += property + ":"
										+ value.getClass().getName();
							}
							first = false;
						}
					}
					filter.setDeep(oldValue);
				}
			}
			result += "]";
			put(id, object);
			filter.addValueYUML(id, result);
		}
		return id;
	}

	/**
	 * Gets the cardinality.
	 *
	 * @param cardinaltity
	 *			the cardinaltity
	 * @param typ
	 *			Is it a OBJECT OR A CLASS diagram
	 * @return the cardinality
	 */
	private String getCardinality(String cardinaltity, int typ) {
		if (typ == OBJECT) {
			return "";
		}
		return cardinaltity;
	}

	/**
	 * Gets the class name.
	 *
	 * @param object
	 *			the object
	 * @return the class name
	 */
	public String getClassName(Object object) {
		if (object instanceof String) {
			object = getObject((String) object);
		}
		String className = object.getClass().getName();
		return className.substring(className.lastIndexOf('.') + 1);
	}

}
