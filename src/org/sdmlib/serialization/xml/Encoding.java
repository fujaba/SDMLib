package org.sdmlib.serialization.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLEntityCreator;


public class Encoding {
	public static final String ID="id";
	private StringBuffer buffer;
	private ArrayList<String> tags;
	private boolean showEbene;
	private XMLIdMap parent;
	private HashMap<String, ArrayList<String>> children=new HashMap<String, ArrayList<String>>();
	private boolean fullEncoding;

	public Encoding(XMLIdMap parent){
		this.parent=parent;
	}

	public String encode(Object object, boolean simple, boolean fullEncoding) {
		this.setShowEbene(showEbene);
		buffer = new StringBuffer();
		if (!simple) {
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		}
		tags = new ArrayList<String>();
		this.fullEncoding=fullEncoding;
		parser(object);
		return buffer.toString();
	}

	private void parser(Object entity) {
		XMLEntityCreator createrProtoTyp = parent.getCreatorClass(entity);
		if (createrProtoTyp == null) {
			return;
		}
		
		startElement(createrProtoTyp.getTag());
		String[] properties = createrProtoTyp.getProperties();
		Object referenceObject = createrProtoTyp.getSendableInstance(true);
		
		if(parent.isId()){
			buffer.append(ID+"="+parent.getId(entity));
		}
		
		if (properties != null) {
			for (String property : properties) {
				Object value = createrProtoTyp.getValue(entity, property);
				if (value != null) {
					boolean encoding=fullEncoding;
					if(!encoding){
						Object referenceTyp = createrProtoTyp.getValue(referenceObject,
								property);
						encoding=!value.equals(referenceTyp);
					}
					if (encoding) {
						boolean add=false;
						if (property.startsWith(XMLIdMap.ENTITYSPLITTER)) {
							int pos = property.indexOf(XMLIdMap.ENTITYSPLITTER, 1);
							if(pos<0){
								pos = property.indexOf(XMLIdMap.ATTRIBUTEVALUE, 1);
							}
							String label;
							if(pos>0){
								label=property.substring(1,pos);
							}else{
								label=property.substring(1);;
							}
							ArrayList<String> list = children.get(label);
							if(list==null){
								list=new ArrayList();
							}
							list.add(property);
							children.put(label, list);							
						}else{
							if (value instanceof Collection<?>) {
								add=true;
							}else{
								SendableEntityCreator valueCreater = parent.getCreatorClass(value);
								if (valueCreater != null) {
									add=true;
								} else {
									addAttribute(property, value);
								}
							}
						}
						if(add){
							property+=XMLIdMap.ENTITYSPLITTER;
							ArrayList<String> list = children.get(property);
							if(list==null){
								list=new ArrayList();
								children.put(property, list);
							}else{
								list.add(property);
							}
						}
					}
				}
			}
			boolean isChild = children.size() > 0;
			endStartElement(isChild);
				//parsing Children
				while (children.size()>0) {
					Entry<String, ArrayList<String>> i = children.entrySet().iterator().next();
					String key = i.getKey();
					parseRefs(entity, createrProtoTyp, key);
				}
			endElement(isChild);
		}
	}
	
	private void parseRefs(Object entity,XMLEntityCreator createrProtoTyp, String currentTag ){
		//Children
		parseRefs(entity, createrProtoTyp, currentTag, children.remove(currentTag));
	}
	private void parseRefs(Object entity,XMLEntityCreator createrProtoTyp, String currentTag, ArrayList<String> props){
		
		boolean openTag=false;
		String tagvalue="";
		int len=currentTag.length()+1;
		for(int z=0;z<props.size();){
			String prop = props.get(z);
			Object value = createrProtoTyp.getValue(entity, prop);
			if (value instanceof Collection<?>) {
				z++;
			}else{
				SendableEntityCreator valueCreater = parent.getCreatorClass(value);
				if (valueCreater != null) {
					z++;
				} else {
					if(!openTag){
						String tag=currentTag;
						int pos=currentTag.indexOf(XMLIdMap.ENTITYSPLITTER, 1);
						if(pos>0){
							tag=currentTag.substring(pos+1);
						}
						startElement(tag);
						openTag=true;
					}
					if(prop.length()<=len){
						tagvalue=(String) value;
						props.remove(z);	
					}else if(prop.charAt(len)==XMLIdMap.ATTRIBUTEVALUE.charAt(0)){
						String label=prop.substring(prop.indexOf(XMLIdMap.ATTRIBUTEVALUE)+1);
						addAttribute(label, value);
						props.remove(z);
					}else if(prop.charAt(len)==XMLIdMap.ENTITYSPLITTER.charAt(0)){
						z++;
					}else{
						tagvalue=(String) value;
						props.remove(z);	
					}
				}					
			}
		}
		boolean isChildren=props.size()>0;
		if(openTag){
			if(tagvalue.length()>0){
				endStartElement(true);
				buffer.append(tagvalue);
				isChildren=true;
			}else{
				endStartElement(isChildren);
			}
		}

		// new Childs
		
		for(int z=0;z<props.size();){
			String prop = props.get(z);
			Object value = createrProtoTyp.getValue(entity, prop);
			if (value instanceof Collection<?>) {
				for(Object child : (Collection<?>)value){
					parser(child);
				}
				props.remove(z);
			}else{
				SendableEntityCreator valueCreater = parent.getCreatorClass(value);
				if (valueCreater != null) {
					parser(value);
					props.remove(z);
				}else{
					parseRefs(entity, createrProtoTyp, prop, props);
				}
			}
		}
		endElement(isChildren);
	}
	
	private void startElement(String tag) {
		tags.add(tag);
		if (isShowEbene()) {
			buffer.append("\n");
			for (int z = 1; z < tags.size(); z++) {
				buffer.append("\t");
			}
		}
		buffer.append("<" + tag);
	}

	private void endStartElement(boolean child) {
		if (!child) {
			buffer.append("/>");
		} else {
			buffer.append(">");
		}
	}

	private void endElement(boolean child) {
		if (child) {
			if (this.isShowEbene() && showEbene) {
				buffer.append("\n");
				for (int z = 1; z < tags.size(); z++) {
					buffer.append("\t");
				}
			}
			buffer.append("</" + tags.remove(tags.size() - 1) + ">");
		}
	}

	private void addAttribute(String tag, Object value) {
		if (value != null) {
			String temp = String.valueOf(value);
			if (temp != null && temp != "") {
				buffer.append(" " + tag + "=\"" + value + "\"");
			}
		}
	}

	public boolean isShowEbene() {
		return showEbene;
	}

	public void setShowEbene(boolean showEbene) {
		this.showEbene = showEbene;
	}
}
