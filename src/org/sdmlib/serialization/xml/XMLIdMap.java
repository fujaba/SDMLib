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
import org.sdmlib.serialization.IdMapFilter;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLEntityCreator;

/**
 * The Class XMLIdMap.
 */
public class XMLIdMap extends XMLSimpleIdMap {
	/** The Constant ENTITYSPLITTER. */
	public static final String ENTITYSPLITTER = "&";
	
	/** The Constant ATTRIBUTEVALUE. */
	public static final String ATTRIBUTEVALUE = "?";
	
	/** The decoder map. */
	private HashMap<String, XMLEntityCreator> decoderMap;
	
	/** The stack. */
	private ArrayList<ReferenceObject> stack = new ArrayList<ReferenceObject>();
	
	/** The stopwords. */
	private HashSet<String> stopwords=new HashSet<String>();
	
	/**
	 * Inits the.
	 */
	@Override
	protected void init(){
		super.init();
		getCounter().enableId(false);
	}
	
	/* (non-Javadoc)
	 * @see org.sdmlib.serialization.IdMap#addCreator(org.sdmlib.serialization.interfaces.SendableEntityCreator)
	 */
	@Override
	public boolean addCreator(SendableEntityCreator createrClass) {
		boolean result=super.addCreator(createrClass);
		if (this.decoderMap == null) {
			this.decoderMap = new HashMap<String, XMLEntityCreator>();
		}
		if(createrClass instanceof XMLEntityCreator){
			XMLEntityCreator xmlCreator=(XMLEntityCreator) createrClass;
			if (this.decoderMap.containsKey(xmlCreator.getTag())) {
				return false;
			}
			this.decoderMap.put(xmlCreator.getTag(), xmlCreator);
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
		if(this.decoderMap==null){
			return null;
		}
		return this.decoderMap.get(tag);
	}

	/**
	 * Encode.
	 *
	 * @param entity the entity
	 * @return the xML entity
	 */
	public XMLEntity encode(Object entity) {
		return encode(entity, new IdMapFilter());
	}
	/**
	 * Encode.
	 *
	 * @param entity the entity
	 * @return the xML entity
	 */
	public XMLEntity encode(Object entity, IdMapFilter filter) {
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
		
		if(getCounter().isId()){
			xmlEntity.put(ID, getId(entity));
		}
		
		if (properties != null) {
			for (String property : properties) {
				Object value = createrProtoTyp.getValue(entity, property);
				if (value != null) {
					boolean encoding=filter.isFullSerialization();
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
					child=new XMLEntity();
					child.setTag(label);
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
		this.value=new XMLTokener(value);
		this.stack.clear();
		while (!this.value.isEnd()) {
			if (this.value.stepPos(""+ITEMSTART, false, false)) {
				XMLEntity tag=getEntity();
				result = findTag("", tag);
			}
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
			return this.value.stepPos(""+ITEMSTART, false, false);
		}
		if (this.value.getCurrentChar() != ITEMSTART) {
			this.value.next();
		}
		int start=this.value.getIndex();
		ArrayList<String> stack=new ArrayList<String>();
		while (!this.value.isEnd() && !exit) {
			if(!this.value.checkValues('\t', '\r', '\n', ' ', ITEMSTART)){
				empty=false;
			}
			if (this.value.getCurrentChar() == ITEMSTART) {
				if(empty){
					exit = true;
					break;
				}
				String nextTag=this.value.getNextTag();
				if(nextTag.length()>0){
					stack.add(nextTag);
					continue;
				}
				if(this.value.getCurrentChar()==ENDTAG){
					if(stack.size()>0){
						int temp=this.value.getIndex();
						String endTag = this.value.getNextTag();
						if(stack.get(stack.size()-1).equals(endTag)){
							stack.remove(stack.size()-1);
						}else{
							stack.remove(stack.size()-1);
							this.value.setIndex(temp-1);
							continue;
						}
						
					}else{
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
		if(!empty&&exit){
			String value=this.value.getPreviousString(start);
			ReferenceObject refObject=null;
			if("&".equals(newPrefix)){
				refObject = this.stack.get(this.stack.size() - 1);
			}
			if(refObject!=null){
				SendableEntityCreator parentCreator=refObject.getCreater();
				parentCreator.setValue(refObject.getEntity(), newPrefix, value, IdMap.NEW);
			}
		}
		return exit;
	}
	
	/**
	 * Find tag.
	 *
	 * @param prefix the prefix
	 * @param tag the tag
	 * @return the object
	 */
	private Object findTag(String prefix, XMLEntity item){
		String tag=item.getTag();
		Object entity = null;

		if (tag.length() > 0) {
			XMLEntityCreator entityCreater = getCreatorDecodeClass(tag);
			boolean plainvalue = false;
			String newPrefix = "";
			if (entityCreater == null) {
				if(this.stack.size()==0){
					return null;
				}
				// Not found child creater
				ReferenceObject referenceObject = this.stack.get(this.stack.size() - 1);
				entityCreater = (XMLEntityCreator) referenceObject.getCreater();
				String[] properties = entityCreater.getProperties();
				prefix += tag;

				for (String prop : properties) {
					if (prop.equalsIgnoreCase(prefix)) {
						// It is a Attribute
						entity = referenceObject.getEntity();
						plainvalue = true;
						break;
					} else if (prop.startsWith(prefix)) {
						// it is a Child
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
				this.stack.add(new ReferenceObject(entityCreater, tag, this.parent, entity));
				newPrefix = XMLIdMap.ENTITYSPLITTER;
				prefix="";
			}
			if(entity==null){
				//First Skip not valid entry
				ArrayList<String> myStack=new ArrayList<String>();
				myStack.add(tag);
				
				while (!this.value.isEnd() && myStack.size()>0) {
					if(this.value.getCurrentChar()==ENDTAG){
						String nextTag=this.value.getNextTag();
						if(nextTag.length()<1||myStack.get(myStack.size() - 1).equalsIgnoreCase(nextTag)){
							myStack.remove(myStack.size() - 1);
							continue;
						}
					}
					if(this.value.getCurrentChar()==ITEMSTART){
						XMLEntity nextTag=getEntity();
						if(nextTag!=null && nextTag.getTag().length()>0){
							myStack.add(nextTag.getTag());
						}
						continue;
					}
					this.value.next();
				}
			}else{
				if (!plainvalue) {
					// Parse Attributes
					while (!this.value.isEnd() && this.value.getCurrentChar() != ITEMEND) {
						if (this.value.getCurrentChar() == ENDTAG) {
							break;
						}
						this.value.next();
						int start = this.value.getIndex();
						if (this.value.getCurrentChar() != ENDTAG) {
							if (this.value.stepPos("=", false, false)) {
								String key = this.value.getPreviousString(start);
								this.value.skip(2);
								start = this.value.getIndex();
								if (this.value.stepPos("\"", false, true)) {
									String value = this.value.getPreviousString(start);
									this.value.next();
									entityCreater.setValue(entity, prefix + key, value, IdMap.NEW);
								}
							}
						}
					}
					
					if(this.value.getCurrentChar()!=ENDTAG){
						//Children
						parseChildren(newPrefix, entity, tag);
					}else{
						this.value.next();
					}
					return entity;
				}
				if(this.value.getCurrentChar()==ENDTAG){
					this.value.next();
				}else{
					this.value.next();
					int start = this.value.getIndex();
					this.value.stepPos(""+ITEMSTART, false, true);
					String value= this.value.getPreviousString(start);
					entityCreater.setValue(entity, prefix, value, IdMap.NEW);
					this.value.stepPos(""+ITEMSTART, false, false);
					this.value.stepPos(""+ITEMEND, false, false);
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
		while (!this.value.isEnd()) {
			if (stepEmptyPos(newPrefix, entity, tag)) {
				XMLEntity nextTag=getEntity();

				if(nextTag != null ){
					Object result = findTag(newPrefix, nextTag);
		
					if(result!=null){
						ReferenceObject refObject=null;
						if(result!=entity){
							if("&".equals(newPrefix)){
								refObject = this.stack.get(this.stack.size() - 2);
							}else{
								refObject = this.stack.get(this.stack.size() - 1);
							}
							if(refObject!=null){
								SendableEntityCreator parentCreator=refObject.getCreater();
								parentCreator.setValue(refObject.getEntity(), nextTag.getTag(), result, IdMap.NEW);
								if(entity!=null&&this.stack.size()>0){
									this.stack.remove(this.stack.size() - 1);
								}
							}
						}
					}
				}
				if(this.value.isEnd()){
					if(entity!=null&&this.stack.size()>0){
						this.stack.remove(this.stack.size() - 1);
					}
				}else if(this.value.getCurrentChar()==ENDTAG){
					this.value.stepPos(""+ITEMEND, false, false);
					break;
				}
				this.value.next();
			}
		}
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
