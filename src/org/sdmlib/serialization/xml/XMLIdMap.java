package org.sdmlib.serialization.xml;

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

public class XMLIdMap extends IdMap {
	public static final String ENTITYSPLITTER = "&";
	public static final String ATTRIBUTEVALUE = "?";
	public static final char ENDTAG='/';
	public static final char ITEMEND='>';
	public static final char ITEMSTART='<';
	public static final char SPACE=' ';
	private HashMap<String, XMLEntityCreator> decoderMap;
	private ArrayList<ReferenceObject> stack = new ArrayList<ReferenceObject>();
	private HashSet<String> stopwords=new HashSet<String>();
	private Tokener value;
	public static final String ID="id";

	public XMLIdMap() {
		super();
		init();
	}
	
	public XMLIdMap(IdMap parent){
		super(parent);
		init();
	}
	private void init(){
		stopwords.add("?xml");
		stopwords.add("!--");
		stopwords.add("!DOCTYPE");
		isId=false;
	}
	
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

	public XMLEntityCreator getCreatorDecodeClass(String tag) {
		if(decoderMap==null){
			return null;
		}
		return decoderMap.get(tag);
	}

	public void setId(boolean value) {
		this.isId=value;
	}
	
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
	
	private String getEntity(int start) {
		String tag = value.substring(start, value.getIndex());
		for(String stopword : stopwords){
			if(tag.startsWith(stopword)){
				return "";
			}
		}
		return tag;
	}
	public boolean addStopWords(String... stopwords){
		for(String stopword : stopwords){
			this.stopwords.add(stopword);
		}
		return true;
	}
}
