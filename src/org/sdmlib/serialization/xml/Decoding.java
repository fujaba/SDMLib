package org.sdmlib.serialization.xml;

import java.util.ArrayList;
import java.util.HashSet;

import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLEntityCreator;

public class Decoding {
	public static final char ENDTAG='/';
	public static final char ITEMEND='>';
	public static final char ITEMSTART='<';
	public static final char SPACE=' ';
	private String buffer;
//	private int len;
	private int pos;
	private ArrayList<ReferenceObject> stack = new ArrayList<ReferenceObject>();
	private HashSet<String> stopwords=new HashSet<String>();
	private XMLIdMap parent;

	public Decoding(XMLIdMap parent){
		this.parent=parent;
		stopwords.add("?xml");
		stopwords.add("!--");
		stopwords.add("!DOCTYPE");
	}

	public Object decode(String value) {
		Object result = null;
		this.buffer = value;
		this.pos = 0;
		this.stack.clear();
		while (pos < buffer.length()) {
			result = findTag("");
			if (result != null && !(result instanceof String)) {
				break;
			}
		}
		return result;
	}

	public boolean stepPos(char... character) {
		boolean exit = false;
		while (pos < buffer.length() && !exit) {
			for (char zeichen : character) {
				if (buffer.charAt(pos) == zeichen) {
					exit = true;
					break;
				}
			}
			if (!exit) {
				pos++;
			}
		}
		return exit;
	}
	public boolean stepEmptyPos(String newPrefix, Object entity, String tag) {
		boolean exit = false;
		boolean empty=true;
		
		if(!newPrefix.equals("&")){
			return stepPos(ITEMSTART);
		}
		if (buffer.charAt(pos) != ITEMSTART) {
			pos++;
		}
		int start=pos;
		while (pos < buffer.length() && !exit) {
			if(buffer.charAt(pos)!=9&&buffer.charAt(pos)!=10&&buffer.charAt(pos)!=12&&buffer.charAt(pos)!=32&&buffer.charAt(pos)!=ITEMSTART){
				empty=false;
			}
			if (buffer.charAt(pos) == ITEMSTART) {
				if(empty||buffer.charAt(pos+1)==ENDTAG){
					exit = true;
					break;
				}
			}
			if (!exit) {
				pos++;
			}
		}
		if(!empty&&exit){
			String value=buffer.substring(start, pos);
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
	
	public boolean stepPosButNot(char not, char... character) {
		boolean exit = false;
		while (pos < buffer.length() && !exit) {
			for (char zeichen : character) {
				if (buffer.charAt(pos) == zeichen&& buffer.charAt(pos-1)!=not) {
					exit = true;
					break;
				}
			}
			if (!exit) {
				pos++;
			}
		}
		return exit;
	}
	
	public boolean stepPos(String searchString) {
		boolean exit = false;
		int strLen=searchString.length();
		int z=0;
		while (pos < buffer.length() && !exit) {
			if (buffer.charAt(pos) == searchString.charAt(z)) {
					z++;
					if(z>=strLen){
						exit = true;
						break;
					}
			}else{
				z=0;
			}
			if (!exit) {
				pos++;
			}
		}
		return exit;
	}

	private Object findTag(String prefix) {
		if (stepPos(ITEMSTART)) {
			int start = ++pos;

			if (stepPos(SPACE, ITEMEND, ENDTAG)) {
				String tag = getEntity(start);
				return findTag(prefix, tag);
			}
		}
		return null;
	}
	private Object findTag(String prefix, String tag){
		if (tag.length() > 0) {
			XMLEntityCreator entityCreater = parent.getCreatorDecodeClass(tag);
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
					while (pos < buffer.length() && buffer.charAt(pos) != ITEMEND) {
						if (buffer.charAt(pos) == ENDTAG) {
							break;
						}
						int start = ++pos;
						if (buffer.charAt(pos) != ENDTAG) {
							if (stepPos('=')) {
								String key = buffer.substring(start, pos);
								pos += 2;
								start = pos;
								if (stepPosButNot('\\', '"')) {
									String value = buffer.substring(start, pos++);
									entityCreater.setValue(entity, prefix + key, value);
								}
							}
						}
					}
					
					if(buffer.charAt(pos)!=ENDTAG){
						//Children
						parseChildren(newPrefix, entity, tag);
					}else{
						pos++;
					}
					return entity;
				}
				if(buffer.charAt(pos)==ENDTAG){
					pos++;
				}else{
					int start = ++pos;
					stepPosButNot('\\', ITEMSTART);
					String value= buffer.substring(start, pos);
					entityCreater.setValue(entity, prefix, value);
					stepPos(ITEMSTART);
					stepPos(ITEMEND);
				}
				return null;
			}
			return entity;
		}
		return null;
	}
	
	private void parseChildren(String newPrefix, Object entity, String tag){
		while (pos < buffer.length()) {
//FIXME			if (stepPos(ITEMSTART)) {
			if (stepEmptyPos(newPrefix, entity, tag)) {
				int start = ++pos;

				if (stepPos(SPACE, ITEMEND, ENDTAG)) {
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
					if(pos>=buffer.length()){
						if(entity!=null&&stack.size()>0){
							stack.remove(stack.size() - 1);
						}
					}else if(buffer.charAt(pos)==ENDTAG){
						stepPos(ITEMEND);
						break;
					}
					pos++;
				}
			}
		}
	}
	
	public String getNextTag(){
		String tag="";
		int savePos=pos;
		stepPos(ITEMSTART);
		int start=++pos;
		stepPos(SPACE, ITEMEND);
		if(start<buffer.length()){
			tag=buffer.substring(start, pos);
		}
		pos=savePos;
		return tag;
	}

	private String getEntity(int start) {
		String tag = buffer.substring(start, pos);
		for(String stopword : stopwords){
			if(tag.startsWith(stopword)){
				return "";
			}
		}
		return tag;
	}
	public void addStopWords(String... stopwords){
		for(String stopword : stopwords){
			this.stopwords.add(stopword);
		}
	}
}
