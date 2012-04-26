package org.sdmlib.serialization.xml;

import java.util.ArrayList;
import java.util.HashSet;

import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLEntityCreator;

public class Decoding {
	public static final char PREFIX = ':';
	private String buffer;
	private int len;
	private int pos;
	private ArrayList<ReferenceObject> stack = new ArrayList<ReferenceObject>();
	private HashSet<String> stopwords=new HashSet<String>();
	private XMLIdMap parent;

	public Decoding(XMLIdMap parent){
		this.parent=parent;
		stopwords.add("?xml");
		stopwords.add("!--");
		stopwords.add("!DOCTYPE");
		stopwords.add("/");
	}

	public Object decode(String value) {
		Object result = null;
		this.buffer = value;
		this.len = value.length();
		this.pos = 0;
		this.stack.clear();
		while (pos < len) {
			result = findTag("");
			if (result != null && !(result instanceof String)) {
				break;
			}
		}
		return result;
	}

	private boolean stepPos(char... character) {
		boolean exit = false;
		while (pos < len && !exit) {
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

	private Object findTag(String prefix) {
		if (stepPos('<')) {
			int start = ++pos;

			if (stepPos(' ', '>')) {
				String tag = getEntity(start);
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
							}
						}

						if (entity != null) {
							if (!plainvalue) {
								newPrefix = prefix + XMLIdMap.ENTITYSPLITTER;
								prefix += XMLIdMap.ATTRIBUTEVALUE;
							}
							stack.add(new ReferenceObject(entityCreater, tag, this.parent, entity));
						}
					} else {
						entity = entityCreater.getSendableInstance(false);
						stack.add(new ReferenceObject(entityCreater, tag, this.parent, entity));
						newPrefix = XMLIdMap.ENTITYSPLITTER;
					}
					if (entity != null) {
						if (!plainvalue) {
							convertParams(entityCreater, entity, prefix);
						}
						if (plainvalue) {
							start = ++pos;
							String value;
							stepPos('<');
							value = buffer.substring(start, pos);
							stepPos('>');
							pos++;
							entityCreater.setValue(entity, prefix, value);
							return null;
						} else {
							//Children
							while (pos < len) {
								String currentTag=getNextTag();
								Object result = findTag(newPrefix);
								String startTag=getNextTag();
								if(stack.size()>0&&result!=null){
									ReferenceObject refObject = stack.get(stack.size() - 1);
									SendableEntityCreator parentCreator=refObject.getCreater();
									parentCreator.setValue(refObject.getEntity(), currentTag, result);
								}
								System.out.println(startTag);
								if(startTag.startsWith("/"+tag)){
									if(stack.size()>0){
										stack.remove(stack.size() - 1);
									}
									stepPos('<');
									pos++;
									break;
								}
								start = ++pos;
							}
						}
					}
					return entity;
				}
			}
		}
		return null;
	}
	public String getNextTag(){
		String tag="";
		int savePos=pos;
		stepPos('<');
		int start=++pos;
		stepPos(' ', '>');
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

	private void convertParams(XMLEntityCreator entityCreater, Object entity,
			String prefix) {
		while (pos < len && buffer.charAt(pos) != '>') {
			if (buffer.charAt(pos) == '/') {
				break;
			}
			int start = ++pos;
			if (buffer.charAt(pos) != '/') {
				if (stepPos('=')) {
					String key = buffer.substring(start, pos);
					pos += 2;
					start = pos;
					if (stepPos('"')) {
						String value = buffer.substring(start, pos++);
						entityCreater.setValue(entity, prefix + key, value);
					}
				}
			}
		}
	}
}
