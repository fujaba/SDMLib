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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.event.StyleFormat;

/**
 * The Class XMLEntity.
 */
public class XMLEntity extends Entity{
	/** The children. */
	protected ArrayList<XMLEntity> children;
	
	/** StyleFormat */
	private StyleFormat style;
	
	/** The tag. */
	protected String tag;
	
	/** The value. */
	protected String value;
	
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
	public XMLEntity(String value){
		this(new XMLTokener(value));
	}
	
    /**
     * Construct a XMLEntity from a Tokener.
     * @param value A Tokener object containing the source string.
     *  or a duplicated key.
     */
    public XMLEntity(Tokener tokener) {
        this();
        tokener.parseToEntity(this);
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
		if(this.children==null){
			this.children=new  ArrayList<XMLEntity>();
		}
		return this.children;
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
		return this.tag;
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
		return this.value;
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
		StringBuilder sb=new StringBuilder();
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
		boolean hasChild=(this.children!=null&&this.children.size()>0);
		boolean hasStyle=this.style!=null;
		
		
		if(this.value==null&&!hasChild && !hasStyle){
			sb.append("/>");
		}else{
			sb.append(">");
			// parse Children
			if(hasStyle){
				sb.append(style.getStartTag());
			}
			if(hasChild){
				for(XMLEntity child : this.children){
					sb.append(child.toString(indentFactor));
				}
			}else if(this.value!=null){
				sb.append(this.value);
			}
			if(hasStyle){
				sb.append(style.getEndTag());
			}

			sb.append("</"+getTag()+">");
		}
		return sb.toString();
	}

	public StyleFormat getStyle() {
		return style;
	}

	public void setStyle(StyleFormat style) {
		this.style = style;
	}
}
