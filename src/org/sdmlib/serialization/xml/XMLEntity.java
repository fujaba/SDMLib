package org.sdmlib.serialization.xml;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;

public class XMLEntity extends Entity{
	private ArrayList<XMLEntity> children;
	private String tag;
	private String value;
	public XMLEntity(){
		
	}
	
	public XMLEntity(String tag){
		this.setTag(tag);
	}
	
	@Override
	public EntityList getNewArray() {
		return null;
	}

	@Override
	public Entity getNewObject() {
		return new XMLEntity();
	}

	public ArrayList<XMLEntity> getChildren() {
		if(children==null){
			children=new  ArrayList<XMLEntity>();
		}
		return children;
	}

	public void addChild(XMLEntity child) {
		getChildren().add(child);
	}
	public XMLEntity getChild(String tag){
		for(XMLEntity entity : getChildren()){
			if(tag.equals(entity.getTag())){
				return entity;
			}
		}
		return null;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public Writer write(Writer writer) {
		return null;
	}
	
	
	@Override
	public String toString() {
		return toString(0);
	}

	@Override
	public String toString(int indentFactor) {
		return toString(indentFactor, 0, false);
	}
	public String toString(int indentFactor, boolean header) {
		return toString(indentFactor, 0, header);
	}
	@Override
	public String toString(int indentFactor, int intent) {
		return toString(indentFactor, intent, false);
	}

	public String toString(int indentFactor, int intent, boolean header) {
		StringBuffer sb=new StringBuffer();
		if(header){
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		}
		if(indentFactor>0){
			sb.append("\n");
		}
		for (int i = 0; i < indentFactor; i += 1) {
            sb.append(' ');
        }
		sb.append("<"+this.getTag());
		Map<String, Object> attributes = getMap();
		
		for(Iterator<Entry<String, Object>> i = attributes.entrySet().iterator();i.hasNext();){
			Entry<String, Object> attribute = i.next();
			sb.append(" "+attribute.getKey()+"=\""+attribute.getValue()+"\"");
		}
		boolean hasChild=(children!=null&&children.size()>0);
		if(value==null&&!hasChild){
			sb.append("/>");
		}else{
			sb.append(">");
			// parse Children
			if(children!=null){
				for(XMLEntity child : children){
					sb.append(child.toString(indentFactor));
				}
			}else if(value!=null){
				sb.append(value);
			}
			sb.append("</"+getTag()+">");
		}
		return sb.toString();
	}
}
