package org.sdmlib.serialization.xml;
/*
Copyright (c) 2012 Stefan Lindel

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.Tokener;

/**
 * The Class XMLEntity.
 */
public class XMLEntity extends Entity{
	
	/** The children. */
	private ArrayList<XMLEntity> children;
	
	/** The tag. */
	private String tag;
	
	/** The value. */
	private String value;
	
	/**
	 * Instantiates a new xML entity.
	 */
	public XMLEntity(){
		
	}
	
	/**
	 * Instantiates a new xML entity.
	 *
	 * @param tag the tag
	 */
	public XMLEntity(String tag){
		this.setTag(tag);
	}
	
    /**
     * Construct a XMLEntity from a Tokener.
     * @param x A Tokener object containing the source string.
     *  or a duplicated key.
     */
    public XMLEntity(Tokener x) {
        this();
        setTokener(x);
    }
    public void setTokener(Tokener x){
        char c;
        
        x.setCreator(this);

        if (x.nextClean() != '<') {
            throw x.syntaxError("A JsonObject text must begin with '<'");
        }
        StringBuffer sb = new StringBuffer();
        c = x.nextClean();
        while (c >= ' ' && ",:]>/\\\"<;=# ".indexOf(c) < 0) {
            sb.append(c);
            c = x.next();
        }
        x.back();
        setTag(sb.toString());
        XMLEntity child;
        for (;;) {
        	String key=null;
            c = x.nextClean();
            switch (c) {
            case 0:
                throw x.syntaxError("A JsonObject text must end with '>'");
            case '>':
            	if(x.getCurrentChar()>' '||x.nextClean()>' '){
            		if(x.getCurrentChar()=='<'){
                		child = (XMLEntity) getNewObject();
                		child.setTokener(x);
                		this.addChild(child);
            		}else{
            			String value = x.nextString('<');
            			this.setValue(value);
            			x.back();
            		}
            	}
        		break;
            case '<':
            	if(x.next()=='/'){
            		x.stepPos('>');
            		x.next();
            		return;
            	}else{
            		x.back();
            		x.back();
            		child = (XMLEntity) getNewObject();
            		child.setTokener(x);
            		this.addChild(child);
            		break;
            	}
            case '/':
            	x.next();
            	return;
            default:
                x.back();
                key = x.nextValue().toString();
            }
            if(key!=null){
// The key is followed by ':'. We will also tolerate '=' or '=>'.
	            c = x.nextClean();
	            if (c == '=') {
	                if (x.next() != '>') {
	                    x.back();
	                }
	            }
	            this.put(key, x.nextValue());
            }
        }
    }

	/* (non-Javadoc)
	 * @see de.uni.kassel.peermessage.BaseEntity#getNewArray()
	 */
	@Override
	public EntityList getNewArray() {
		return null;
	}

	/* (non-Javadoc)
	 * @see de.uni.kassel.peermessage.BaseEntity#getNewObject()
	 */
	@Override
	public Entity getNewObject() {
		return new XMLEntity();
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public ArrayList<XMLEntity> getChildren() {
		if(children==null){
			children=new  ArrayList<XMLEntity>();
		}
		return children;
	}

	/**
	 * Adds the child.
	 *
	 * @param child the child
	 */
	public void addChild(XMLEntity child) {
		getChildren().add(child);
	}
	
	/**
	 * Gets the child.
	 *
	 * @param tag the tag
	 * @return the child
	 */
	public XMLEntity getChild(String tag){
		for(XMLEntity entity : getChildren()){
			if(tag.equals(entity.getTag())){
				return entity;
			}
		}
		return null;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag the new tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see de.uni.kassel.peermessage.Entity#toString()
	 */
	@Override
	public String toString() {
		return toString(0);
	}

	/* (non-Javadoc)
	 * @see de.uni.kassel.peermessage.Entity#toString(int)
	 */
	@Override
	public String toString(int indentFactor) {
		return toString(indentFactor, 0, false);
	}
	
	/**
	 * To string.
	 *
	 * @param indentFactor the indent factor
	 * @param header the header
	 * @return the string
	 */
	public String toString(int indentFactor, boolean header) {
		return toString(indentFactor, 0, header);
	}
	
	/* (non-Javadoc)
	 * @see de.uni.kassel.peermessage.Entity#toString(int, int)
	 */
	@Override
	public String toString(int indentFactor, int intent) {
		return toString(indentFactor, intent, false);
	}

	/**
	 * To string.
	 *
	 * @param indentFactor the indent factor
	 * @param intent the intent
	 * @param header the header
	 * @return the string
	 */
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
			sb.append(" "+attribute.getKey()+"="+EntityUtil.quote((String)attribute.getValue()));
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
