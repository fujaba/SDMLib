package org.sdmlib.serialization.xml;
/*
Copyright (c) 2013, Stefan Lindel
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

THIS SOFTWARE 'Json Id Serialisierung Map' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
import java.util.HashSet;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.interfaces.XMLGrammar;

public class XMLSimpleIdMap extends IdMap{
	/** The Constant ENDTAG. */
	public static final char ENDTAG='/';
	
	/** The Constant ITEMEND. */
	public static final char ITEMEND='>';
	
	/** The Constant ITEMSTART. */
	public static final char ITEMSTART='<';
	
	/** The Constant SPACE. */
	public static final char SPACE=' ';
	
	/** The stack. */
	protected ArrayList<ReferenceObject> stack = new ArrayList<ReferenceObject>();
	
	/** The stopwords. */
	protected HashSet<String> stopwords=new HashSet<String>();
	
	/** The value. */
	protected Tokener value;
	
	/**
	 * Instantiates a new XML id map.
	 */
	public XMLSimpleIdMap(){
		super();
		init();
	}
	
	/**
	 * Instantiates a new XML id map.
	 *
	 * @param parent the parent
	 */
	public XMLSimpleIdMap(IdMap parent){
		super(parent);
		init();
	}
	
	protected void init(){
		this.stopwords.add("?xml");
		this.stopwords.add("!--");
		this.stopwords.add("!DOCTYPE");
	}

	public Object decode(String value, XMLGrammar factory) {
		Object result = null;
		Object temp = null;
		this.value=new XMLTokener(value);
		
		this.stack.clear();
		while (!this.value.isEnd()) {
			if (this.value.stepPos(""+ITEMSTART, false, false)) {
				XMLEntity entity=getEntity(factory);
				if(entity!=null){
					temp = findTag(entity, factory);
				}
			}
			if (temp != null && !(temp instanceof String)) {
				result = temp;
			}
		}
		return result;
	}
	
	
	/**
	 * Find tag.
	 *
	 * @param prefix the prefix
	 * @param tag the tag
	 * @param styleFormat 
	 * @param styleFormatCreator 
	 * @return the object
	 */
	private XMLEntity findTag(XMLEntity entity, XMLGrammar styleFormatCreator){
		if (entity != null) {
			// Parsing attributes
			char myChar=this.value.getCurrentChar();
			while(myChar!=ITEMEND){
				if(myChar==SPACE){
					value.next();
				}
				int start=this.value.getIndex();
				if (this.value.stepPos("=>", false, false)) {
					myChar=this.value.getCurrentChar();
					if(myChar=='='){
						String key = this.value.substring(start, -1);
						this.value.skip(2);
						start = this.value.getIndex();
						if (this.value.stepPos("\"", false, true)) {
							String value = this.value.substring(start, -1);
							this.value.next();
							styleFormatCreator.setValue(entity, key, value, IdMap.NEW);
						}						
					}
				}else{
					break;
				}
			}

			// Add to StackTrace
			this.stack.add(new ReferenceObject(entity.getTag(), entity));

			// Parsing next Element
			if(value.stepPos("/>", false, false)){
				if(value.getCurrentChar()=='/'){ 
					stack.remove(stack.size()-1);
					value.next();
					String tag = value.getNextTag();
					styleFormatCreator.endChild(tag);
//					skipEntity();
					return entity;
				}

				char quote = (char)ITEMSTART;
				// Skip >
				value.next();
				String strvalue = value.nextString(quote, true, false);
				
				// BACK TO <
				value.back();
				strvalue = strvalue.trim();
				XMLEntity newTag;
				if (this.value.getCurrentChar()==ITEMSTART) {
					// show next Tag
					XMLEntity child = null;
					do{
						boolean saveValue=true;
						do{
							newTag=getEntity(styleFormatCreator);
							if(newTag==null ){
								entity.setValue(strvalue);
								stack.remove(stack.size()-1);
								skipEntity();
								return entity;
							}
							if(newTag.getTag().isEmpty()){
								if(saveValue){
									entity.setValue(newTag.getValue());
								}
								skipEntity();
								newTag=getEntity(styleFormatCreator);
								if(newTag==null){
									stack.remove(stack.size()-1);
									skipEntity();
								}
								return entity;
							}
							if(styleFormatCreator.parseChild(entity, newTag, value)){
								// Skip >
								saveValue=false;
								value.next();
							}else{
								break;
							}
						}while(newTag != null);
						child = findTag(newTag, styleFormatCreator);
						if(child!=null){
							styleFormatCreator.addChildren(entity, child);
						}
					}while(child!=null);
				}
			}
		}
		return entity;
	}
	
	protected void skipEntity(){
		value.stepPos(">", false, false);
		// Skip >
		value.next();
	}
	/**
	 * Gets the entity.
	 *
	 * @param start the start
	 * @return the entity
	 */
	protected XMLEntity getEntity(XMLGrammar factory) {
		XMLEntity entity;
		if(factory!=null){
			Object newObj = factory.getSendableInstance(false);
			if(newObj instanceof XMLEntity){
				entity=(XMLEntity) newObj;
			}else{
				entity=new XMLEntity();	
			}
		}else{
			entity=new XMLEntity();
		}
		String tag=null;
		boolean isEmpty=true;
		do{
			if(value.getCurrentChar()!=ITEMSTART){
				String strValue = value.nextString(ITEMSTART, true, false);
				if(strValue!=null){
					value.back();
					strValue = strValue.trim();
					isEmpty = strValue.isEmpty();
				}
				entity.setValue(strValue);
			}
			tag = this.value.getNextTag();
			if(tag != null){
				for(String stopword : this.stopwords){
					if(tag.startsWith(stopword)){
						this.value.stepPos(">", false, false);
						this.value.stepPos("<", false, false);
						tag =null;
						break;
					}
				}
			}
		}while(tag==null);
		if(tag.isEmpty() && isEmpty){
			return null;
		}
		entity.setTag(tag);
		return entity;
	}
	
}
