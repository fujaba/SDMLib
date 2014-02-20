package org.sdmlib.serialization.xml;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or - as soon they
 will be approved by the European Commission - subsequent
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import org.sdmlib.serialization.AbstractMap;
import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.Filter;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLCreator;
import org.sdmlib.serialization.interfaces.XMLGrammar;
import org.sdmlib.serialization.logic.BooleanCondition;
/**
 * The Class XMLIdMap.
 */

public class XMLIdMap extends XMLSimpleIdMap {
	/** The Constant ENTITYSPLITTER. */
	public static final String ENTITYSPLITTER = "&";

	/** The Constant ATTRIBUTEVALUE. */
	public static final String ATTRIBUTEVALUE = "?";

	/** The decoder map. */
	private HashMap<String, XMLCreator> decoderMap;

	/** The stack. */
	private ArrayList<ReferenceObject> stack = new ArrayList<ReferenceObject>();

	/** The stopwords. */
	private HashSet<String> stopwords = new HashSet<String>();

	/**
	 * Inits the.
	 */
	@Override
	protected void init() {
		super.init();
		getCounter();
		this.filter.withIdFilter(BooleanCondition.value(false));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sdmlib.serialization.IdMap#addCreator(org.sdmlib.serialization.interfaces
	 * .SendableEntityCreator)
	 */
	public boolean addCreator(SendableEntityCreator createrClass) {
		if (createrClass instanceof XMLCreator) {
			if(this.decoderMap != null){
				if (this.decoderMap.containsKey(((XMLCreator)createrClass).getTag())) {
					return false;
				}
			}
		}else{
			return false;
		}
		super.withCreator(createrClass);
		return true;
	}
	
	@Override
	public AbstractMap withCreator(String className,
			SendableEntityCreator createrClass) {
		super.withCreator(className, createrClass);

		if (createrClass instanceof XMLCreator) {
			XMLCreator xmlCreator = (XMLCreator) createrClass;
			if (this.decoderMap == null) {
				this.decoderMap = new HashMap<String, XMLCreator>();
			}
			this.decoderMap.put(xmlCreator.getTag(), xmlCreator);
		}
		return this;
	}

	/**
	 * Gets the creator decode class.
	 * 
	 * @param tag
	 *            the tag
	 * @return the creator decode class
	 */
	public XMLCreator getCreatorDecodeClass(String tag) {
		if (this.decoderMap == null) {
			return null;
		}
		return this.decoderMap.get(tag);
	}

	/**
	 * Encode.
	 * 
	 * @param entity
	 *            the entity
	 * @return the xML entity
	 */
	@Override
	public XMLEntity encode(Object entity) {
		return encode(entity, filter.cloneObj());
	}

	public XMLEntity encode(Object entity, Filter filter) {
		SendableEntityCreator createrProtoTyp = getCreatorClass(entity);
		if (createrProtoTyp == null) {
			return null;
		}
		XMLEntity xmlEntity = new XMLEntity();

		if (createrProtoTyp instanceof XMLCreator) {
			XMLCreator xmlCreater = (XMLCreator) createrProtoTyp;
			if (xmlCreater.getTag() != null) {
				xmlEntity.setTag(xmlCreater.getTag());
			} else {
				xmlEntity.setTag(entity.getClass().getName());
			}
		} else {
			xmlEntity.setTag(entity.getClass().getName());
		}
		String[] properties = createrProtoTyp.getProperties();
		Object referenceObject = createrProtoTyp.getSendableInstance(true);

		if(filter.isId(this, entity, entity.getClass().getName())){
			xmlEntity.put(ID, getId(entity));
		}

		if (properties != null) {
			for (String property : properties) {
				Object value = createrProtoTyp.getValue(entity, property);
				if (value != null) {
						Object refValue = createrProtoTyp.getValue(
								referenceObject, property);
					boolean encoding = !value.equals(refValue);
					if (encoding) {
						if (property.startsWith(XMLIdMap.ENTITYSPLITTER)) {
							parserChild(xmlEntity, property, value);
						} else {
							if (value instanceof Collection<?>) {
								for (Object item : (Collection<?>) value) {
									xmlEntity.addChild(encode(item));
								}

							} else {
								SendableEntityCreator valueCreater = getCreatorClass(value);
								if (valueCreater != null) {
									xmlEntity.addChild(encode(value));
								} else {
									xmlEntity.put(property, value);
								}
							}
						}
					}
				}
			}
		}
		return xmlEntity;
	}

	/**
	 * Parser child.
	 * 
	 * @param parent
	 *            the parent
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the xML entity
	 */
	private XMLEntity parserChild(XMLEntity parent, String property,
			Object value) {

		if (property.startsWith(XMLIdMap.ENTITYSPLITTER)) {
			int pos = property.indexOf(XMLIdMap.ENTITYSPLITTER, 1);
			if (pos < 0) {
				pos = property.indexOf(XMLIdMap.ATTRIBUTEVALUE, 1);
			}
			String label;
			String newProp = "";
			if (pos > 0) {
				label = property.substring(1, pos);
				newProp = property.substring(pos);
			} else {
				label = property.substring(1);
			}
			if (label.length() > 0) {
				XMLEntity child = parent.getChild(label);
				if (child == null) {
					child = new XMLEntity();
					child.setTag(label);
					parserChild(child, newProp, value);
					parent.addChild(child);
				} else {
					parserChild(child, newProp, value);
				}
				return child;
			}
		} else if (property.startsWith(XMLIdMap.ATTRIBUTEVALUE)) {
			parent.put(property.substring(1),
					EntityUtil.valueToString(value, true, parent));
		} else if ("".equals(property)) {
			parent.setValue(EntityUtil.valueToString(value, true, parent));
		}
		return null;
	}

//FIXME	/**
//	 * Decode.
//	 * 
//	 * @param value
//	 *            the value
//	 * @return the object
//	 */
//	@Override
//	public Object decode(XMLTokener entity, XMLGrammar factory) {
//		Object result = null;
//		this.value = new XMLTokener().withText(value);
//		this.stack.clear();
//		while (!this.value.isEnd()) {
//			if (this.value.stepPos("" + ITEMSTART, false, false)) {
//				XMLEntity item = getEntity(null);
//				result = parse(item, factory);
//			}
//		}
//		return result;
//	}
	public Object decode(String value) {
		return decode((XMLTokener) new XMLTokener().withText(value), null);
	}

	/**
	 * Step empty pos.
	 * 
	 * @param newPrefix
	 *            the new prefix
	 * @param entity
	 *            the entity
	 * @param tag
	 *            the tag
	 * @return true, if successful
	 */
	public boolean stepEmptyPos(String newPrefix, Object entity, String tag) {
		boolean exit = false;
		boolean empty = true;

		if (!newPrefix.equals("&")) {
			return this.value.stepPos("" + ITEMSTART, false, false);
		}
		if (this.value.getCurrentChar() != ITEMSTART) {
			this.value.next();
		}
		int start = this.value.position();
		ArrayList<String> stack = new ArrayList<String>();
		while (!this.value.isEnd() && !exit) {
			if (!this.value.checkValues('\t', '\r', '\n', ' ', ITEMSTART)) {
				empty = false;
			}
			if (this.value.getCurrentChar() == ITEMSTART) {
				if (empty) {
					exit = true;
					break;
				}
				String nextTag = this.value.getNextTag();
				if (nextTag.length() > 0) {
					stack.add(nextTag);
					continue;
				}
				if (this.value.getCurrentChar() == ENDTAG) {
					if (stack.size() > 0) {
						int temp = this.value.position();
						String endTag = this.value.getNextTag();
						if (stack.get(stack.size() - 1).equals(endTag)) {
							stack.remove(stack.size() - 1);
						} else {
							stack.remove(stack.size() - 1);
							this.value.setIndex(temp - 1);
							continue;
						}

					} else {
						this.value.back();
						exit = true;
						break;
					}
				}
			}
			if (!exit) {
				this.value.next();
			}
		}
		if (!empty && exit) {
			String value = this.value.substring(start, -1);
			ReferenceObject refObject = null;
			if ("&".equals(newPrefix)) {
				refObject = this.stack.get(this.stack.size() - 1);
			}
			if (refObject != null) {
				SendableEntityCreator parentCreator = refObject.getCreater();
				parentCreator.setValue(refObject.getEntity(), newPrefix, value,
						IdMap.NEW);
			}
		}
		return exit;
	}

	/**
	 * Find tag.
	 * 
	 * @param prefix
	 *            the prefix
	 * @param tag
	 *            the tag
	 * @return the object
	 */
//	@Override
	protected Object parse(XMLEntity entity, XMLGrammar formatCreator, String prefix) {
//	private Object parseXMLEntity(String prefix, XMLEntity item) {
		String tag = entity.getTag();
		Object item = null;

		if (tag.length() > 0) {
			XMLCreator entityCreater = getCreatorDecodeClass(tag);
			boolean plainvalue = false;
			String newPrefix = "";
			if (entityCreater == null) {
				if (this.stack.size() == 0) {
					return null;
				}
				// Not found child creater
				ReferenceObject referenceObject = this.stack.get(this.stack
						.size() - 1);
				entityCreater = (XMLCreator) referenceObject.getCreater();
				String[] properties = entityCreater.getProperties();
				prefix += tag;

				for (String prop : properties) {
					if (prop.equalsIgnoreCase(prefix)) {
						// It is a Attribute
						item = referenceObject.getEntity();
						plainvalue = true;
						break;
					} else if (prop.startsWith(prefix)) {
						// it is a Child
						item = referenceObject.getEntity();
						break;
					}
				}

				if (item != null) {
					if (!plainvalue) {
						newPrefix = prefix + XMLIdMap.ENTITYSPLITTER;
						prefix += XMLIdMap.ATTRIBUTEVALUE;
					}
				}
			} else {
				item = entityCreater.getSendableInstance(false);
				this.stack.add(new ReferenceObject()
							.withCreator(entityCreater)
							.withProperty(tag)
							.withEntity(item));
				newPrefix = XMLIdMap.ENTITYSPLITTER;
				prefix = "";
			}
			if (item == null) {
				// First Skip not valid entry
				ArrayList<String> myStack = new ArrayList<String>();
				myStack.add(tag);

				while (!this.value.isEnd() && myStack.size() > 0) {
					if (this.value.getCurrentChar() == ENDTAG) {
						String nextTag = this.value.getNextTag();
						if (nextTag.length() < 1
								|| myStack.get(myStack.size() - 1)
										.equalsIgnoreCase(nextTag)) {
							myStack.remove(myStack.size() - 1);
							continue;
						}
					}
					if (this.value.getCurrentChar() == ITEMSTART) {
						XMLEntity nextTag = getEntity(null);
						if (nextTag != null && nextTag.getTag().length() > 0) {
							myStack.add(nextTag.getTag());
						}
						continue;
					}
					this.value.next();
				}
			} else {
				if (!plainvalue) {
					// Parse Attributes
					while (!this.value.isEnd()
							&& this.value.getCurrentChar() != ITEMEND) {
						if (this.value.getCurrentChar() == ENDTAG) {
							break;
						}
						this.value.next();
						int start = this.value.position();
						if (this.value.getCurrentChar() != ENDTAG) {
							if (this.value.stepPos("=", false, false)) {
								String key = this.value.substring(start, -1);
								this.value.skip(2);
								start = this.value.position();
								if (this.value.stepPos("\"", false, true)) {
									String value = this.value.substring(start,
											-1);
									this.value.next();
									entityCreater.setValue(item,
											prefix + key, value, IdMap.NEW);
								}
							}
						}
					}

					if (this.value.getCurrentChar() != ENDTAG) {
						// Children
						parseChildren(newPrefix, item, tag);
					} else {
						this.value.next();
					}
					return item;
				}
				if (this.value.getCurrentChar() == ENDTAG) {
					this.value.next();
				} else {
					this.value.next();
					int start = this.value.position();
					this.value.stepPos("" + ITEMSTART, false, true);
					String value = this.value.substring(start, -1);
					entityCreater.setValue(item, prefix, value, IdMap.NEW);
					this.value.stepPos("" + ITEMSTART, false, false);
					this.value.stepPos("" + ITEMEND, false, false);
				}
				return null;
			}
			return item;
		}
		return null;
	}

	/**
	 * Parses the children.
	 * 
	 * @param newPrefix
	 *            the new prefix
	 * @param entity
	 *            the entity
	 * @param tag
	 *            the tag
	 */
	private void parseChildren(String newPrefix, Object entity, String tag) {
		while (!this.value.isEnd()) {
			if (stepEmptyPos(newPrefix, entity, tag)) {
				XMLEntity nextTag = getEntity(null);

				if (nextTag != null) {
					Object result = parse(nextTag, null, newPrefix);

					if (result != null) {
						ReferenceObject refObject = null;
						if (result != entity) {
							if ("&".equals(newPrefix)) {
								refObject = this.stack
										.get(this.stack.size() - 2);
							} else {
								refObject = this.stack
										.get(this.stack.size() - 1);
							}
							if (refObject != null) {
								SendableEntityCreator parentCreator = refObject
										.getCreater();
								parentCreator.setValue(refObject.getEntity(),
										nextTag.getTag(), result, IdMap.NEW);
								if (entity != null && this.stack.size() > 0) {
									this.stack.remove(this.stack.size() - 1);
								}
							}
						}
					}
				}
				if (this.value.isEnd()) {
					if (entity != null && this.stack.size() > 0) {
						this.stack.remove(this.stack.size() - 1);
					}
				} else if (this.value.getCurrentChar() == ENDTAG) {
					this.value.stepPos("" + ITEMEND, false, false);
					break;
				}
				this.value.next();
			}
		}
	}

	/**
	 * Adds the stop words.
	 * 
	 * @param stopwords
	 *            the stopwords
	 * @return true, if successful
	 */
	public boolean addStopWords(String... stopwords) {
		for (String stopword : stopwords) {
			this.stopwords.add(stopword);
		}
		return true;
	}
}
