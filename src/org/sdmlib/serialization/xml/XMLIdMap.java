package org.sdmlib.serialization.xml;
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
import java.util.HashSet;

import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLEntityCreator;

/**
 * The Class XMLIdMap.
 */
public class XMLIdMap extends IdMap {
	
	/** The Constant ENTITYSPLITTER. */
	public static final String ENTITYSPLITTER = "&";
	
	/** The Constant ATTRIBUTEVALUE. */
	public static final String ATTRIBUTEVALUE = "?";
	
	/** The Constant ENDTAG. */
	public static final char ENDTAG='/';
	
	/** The Constant ITEMEND. */
	public static final char ITEMEND='>';
	
	/** The Constant ITEMSTART. */
	public static final char ITEMSTART='<';
	
	/** The Constant SPACE. */
	public static final char SPACE=' ';
	
	/** The decoder map. */
	private HashMap<String, XMLEntityCreator> decoderMap;
	
	/** The stack. */
	private ArrayList<ReferenceObject> stack = new ArrayList<ReferenceObject>();
	
	/** The stopwords. */
	private HashSet<String> stopwords=new HashSet<String>();
	
	/** The value. */
	private Tokener value;
	
	/** The Constant ID. */
	public static final String ID="id";

	/**
	 * Instantiates a new xML id map.
	 */
	public XMLIdMap() {
		super();
		init();
	}
	
	/**
	 * Instantiates a new xML id map.
	 *
	 * @param parent the parent
	 */
	public XMLIdMap(IdMap parent){
		super(parent);
		init();
	}
	
	/**
	 * Inits the.
	 */
	private void init(){
		stopwords.add("?xml");
		stopwords.add("!--");
		stopwords.add("!DOCTYPE");
		isId=false;
	}
	
	/* (non-Javadoc)
	 * @see org.sdmlib.serialization.IdMap#addCreator(org.sdmlib.serialization.interfaces.SendableEntityCreator)
	 */
	public boolean addCreator(SendableEntityCreator createrClass) {
		boolean result=super.addCreator(createrClass);
		if (decoderMap == null) {
			decoderMap = new HashMap<String, XMLEntityCreator>();
		}
		if(createrClass instanceof XMLEntityCreator){
			XMLEntityCreator xmlCreator=(XMLEntityCreator) createrClass;
			if (decoderMap.containsKey(xmlCreator.getTag())) {
				return false;
			}
			decoderMap.put(xmlCreator.getTag(), xmlCreator);
		}else{
			return false;
		}
		return result;
	}

	/**
	 * Gets the creator decode class.
	 *
	 * @param tag the tag
	 * @return the creator decode class
	 */
	public XMLEntityCreator getCreatorDecodeClass(String tag) {
		if(decoderMap==null){
			return null;
		}
		return decoderMap.get(tag);
	}

	/**
	 * Sets the id.
	 *
	 * @param value the new id
	 */
	public void setId(boolean value) {
		this.isId=value;
	}
	
	/**
	 * Encode.
	 *
	 * @param entity the entity
	 * @return the xML entity
	 */
	public XMLEntity encode(Object entity) {
		SendableEntityCreator createrProtoTyp = getCreatorClass(entity);
		if (createrProtoTyp == null) {
			return null;
		} 
		XMLEntity xmlEntity=new XMLEntity();
		
		if(createrProtoTyp instanceof XMLEntityCreator){
			XMLEntityCreator xmlCreater=(XMLEntityCreator) createrProtoTyp;
			if(xmlCreater.getTag()!=null){
				xmlEntity.setTag(xmlCreater.getTag());
			}else{
				xmlEntity.setTag(entity.getClass().getName());
			}
		}else{
			xmlEntity.setTag(entity.getClass().getName());
		}
		String[] properties = createrProtoTyp.getProperties();
		Object referenceObject = createrProtoTyp.getSendableInstance(true);
		
		if(isId()){
			xmlEntity.put(ID, getId(entity));
		}
		
		if (properties != null) {
			for (String property : properties) {
				Object value = createrProtoTyp.getValue(entity, property);
				if (value != null) {
					boolean encoding=isSimpleCheck();
					if(!encoding){
						Object refValue = createrProtoTyp.getValue(referenceObject,
								property);
						encoding=!value.equals(refValue);
					}
					if(encoding){
						if (property.startsWith(XMLIdMap.ENTITYSPLITTER)) {
							parserChild(xmlEntity, property, value);
						}else{
							if (value instanceof Collection<?>) {
								for(Object item : (Collection<?>)value){
									xmlEntity.addChild(encode(item)); 
								}
								
							}else{
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
	 * @param parent the parent
	 * @param property the property
	 * @param value the value
	 * @return the xML entity
	 */
	private XMLEntity parserChild(XMLEntity parent,
			String property, Object value) {
		
		if(property.startsWith(XMLIdMap.ENTITYSPLITTER)){
			int pos = property.indexOf(XMLIdMap.ENTITYSPLITTER, 1);
			if(pos<0){
				pos = property.indexOf(XMLIdMap.ATTRIBUTEVALUE, 1);
			}
			String label;
			String newProp="";
			if(pos>0){
				label=property.substring(1,pos);
				newProp=property.substring(pos);
			}else{
				label=property.substring(1);
			}
			if(label.length()>0){
				XMLEntity child = parent.getChild(label);
				if(child==null){
					child=new XMLEntity(label);
					parserChild(child, newProp, value);
					parent.addChild(child);
				}else{
					parserChild(child, newProp, value);
				}
				return child;
			}
		}else if(property.startsWith(XMLIdMap.ATTRIBUTEVALUE)){
			parent.put(property.substring(1), EntityUtil.valueToString(value, true, parent));
		}else if("".equals(property)){
			parent.setValue(EntityUtil.valueToString(value, true, parent));
		}
		return null;
	}

	/**
	 * Decode.
	 *
	 * @param value the value
	 * @return the object
	 */
	public Object decode(String value) {
		Object result = null;
		this.value=new Tokener(value);
		this.stack.clear();
		while (!this.value.end()) {
			result = findTag("");
			if (result != null && !(result instanceof String)) {
				break;
			}
		}
		return result;
	}
	
	/**
	 * Step empty pos.
	 *
	 * @param newPrefix the new prefix
	 * @param entity the entity
	 * @param tag the tag
	 * @return true, if successful
	 */
	public boolean stepEmptyPos(String newPrefix, Object entity, String tag) {
		boolean exit = false;
		boolean empty=true;
		
		if(!newPrefix.equals("&")){
			return value.stepPos(ITEMSTART);
		}
		if (value.getCurrentChar() != ITEMSTART) {
			value.next();
		}
		int start=value.getIndex();
		while (!value.end() && !exit) {
			if(value.checkValues('\t', '\r', '\n', ' ', ITEMSTART)){
				empty=false;
			}
			if (value.getCurrentChar() == ITEMSTART) {
				if(empty||value.charAt(value.getIndex()+1)==ENDTAG){
					exit = true;
					break;
				}
			}
			if (!exit) {
				value.next();
			}
		}
		if(!empty&&exit){
			String value=this.value.previous(start);
			ReferenceObject refObject=null;
			if("&".equals(newPrefix)){
				refObject = stack.get(stack.size() - 1);
			}
			if(refObject!=null){
				SendableEntityCreator parentCreator=refObject.getCreater();
				parentCreator.setValue(refObject.getEntity(), newPrefix, value);
			}
		}
		return exit;
	}
	
	/**
	 * Find tag.
	 *
	 * @param prefix the prefix
	 * @return the object
	 */
	private Object findTag(String prefix) {
		if (value.stepPos(ITEMSTART)) {
			int start = value.nextPos();

			if (value.stepPos(SPACE, ITEMEND, ENDTAG)) {
				String tag = getEntity(start);
				return findTag(prefix, tag);
			}
		}
		return null;
	}
	
	/**
	 * Find tag.
	 *
	 * @param prefix the prefix
	 * @param tag the tag
	 * @return the object
	 */
	private Object findTag(String prefix, String tag){
		if (tag.length() > 0) {
			XMLEntityCreator entityCreater = getCreatorDecodeClass(tag);
			Object entity = null;
			boolean plainvalue = false;
			String newPrefix = "";
			if (entityCreater == null) {
				if(stack.size()==0){
					return null;
				}
				// Not found child creater
				ReferenceObject referenceObject = stack.get(stack.size() - 1);
				entityCreater = (XMLEntityCreator) referenceObject.getCreater();
				String[] properties = entityCreater.getProperties();
				prefix += tag;

				for (String prop : properties) {
					if (prop.equalsIgnoreCase(prefix)) {
						entity = referenceObject.getEntity();
						plainvalue = true;
						break;
					} else if (prop.startsWith(prefix)) {
						entity = referenceObject.getEntity();
						break;
					}
				}

				if (entity != null) {
					if (!plainvalue) {
						newPrefix = prefix + XMLIdMap.ENTITYSPLITTER;
						prefix += XMLIdMap.ATTRIBUTEVALUE;
					}
				}
			} else {
				entity = entityCreater.getSendableInstance(false);
				stack.add(new ReferenceObject(entityCreater, tag, this.parent, entity));
				newPrefix = XMLIdMap.ENTITYSPLITTER;
				prefix="";
			}
			if(entity==null){
				//Children
				parseChildren(prefix + XMLIdMap.ENTITYSPLITTER, entity, tag);
			}else{
				if (!plainvalue) {
					// Parse Attributes
					while (!value.end() && value.getCurrentChar() != ITEMEND) {
						if (value.getCurrentChar() == ENDTAG) {
							break;
						}
						int start = value.nextPos();
						if (value.getCurrentChar() != ENDTAG) {
							if (value.stepPos('=')) {
								String key = value.previous(start);
								value.skip(2);
								start = value.getIndex();
								if (value.stepPosButNot('\\', '"')) {
									String value = this.value.previous(start);
									this.value.next();
									entityCreater.setValue(entity, prefix + key, value);
								}
							}
						}
					}
					
					if(value.getCurrentChar()!=ENDTAG){
						//Children
						parseChildren(newPrefix, entity, tag);
					}else{
						value.next();
					}
					return entity;
				}
				if(value.getCurrentChar()==ENDTAG){
					value.next();
				}else{
					int start = this.value.nextPos();
					this.value.stepPosButNot('\\', ITEMSTART);
					String value= this.value.previous(start);
					entityCreater.setValue(entity, prefix, value);
					this.value.stepPos(ITEMSTART);
					this.value.stepPos(ITEMEND);
				}
				return null;
			}
			return entity;
		}
		return null;
	}
	
	/**
	 * Parses the children.
	 *
	 * @param newPrefix the new prefix
	 * @param entity the entity
	 * @param tag the tag
	 */
	private void parseChildren(String newPrefix, Object entity, String tag){
		while (!value.end()) {
			if (stepEmptyPos(newPrefix, entity, tag)) {
				int start = value.nextPos();

				if (value.stepPos(SPACE, ITEMEND, ENDTAG)) {
					String nextTag = getEntity(start);
			
					if(nextTag.length()>0){
						Object result = findTag(newPrefix, nextTag);
			
						if(result!=null){
							ReferenceObject refObject=null;
							if(result!=entity){
								if("&".equals(newPrefix)){
									refObject = stack.get(stack.size() - 2);
								}else{
									refObject = stack.get(stack.size() - 1);
								}
								if(refObject!=null){
									SendableEntityCreator parentCreator=refObject.getCreater();
									parentCreator.setValue(refObject.getEntity(), nextTag, result);
									if(entity!=null&&stack.size()>0){
										stack.remove(stack.size() - 1);
									}
								}
							}
						}
					}
					if(value.end()){
						if(entity!=null&&stack.size()>0){
							stack.remove(stack.size() - 1);
						}
					}else if(value.getCurrentChar()==ENDTAG){
						value.stepPos(ITEMEND);
						break;
					}
					value.next();
				}
			}
		}
	}
	
	/**
	 * Gets the entity.
	 *
	 * @param start the start
	 * @return the entity
	 */
	private String getEntity(int start) {
		String tag = value.substring(start, value.getIndex());
		for(String stopword : stopwords){
			if(tag.startsWith(stopword)){
				return "";
			}
		}
		return tag;
	}
	
	/**
	 * Adds the stop words.
	 *
	 * @param stopwords the stopwords
	 * @return true, if successful
	 */
	public boolean addStopWords(String... stopwords){
		for(String stopword : stopwords){
			this.stopwords.add(stopword);
		}
		return true;
	}
}
