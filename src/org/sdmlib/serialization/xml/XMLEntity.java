package org.sdmlib.serialization.xml;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
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

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;
/**
 * The Class XMLEntity.
 */

public class XMLEntity extends Entity implements BaseEntityList {
	/** The children. */
	protected ArrayList<XMLEntity> children;

	/** The tag. */
	protected String tag;

	/** The value. */
	protected String value;

	/**
	 * Instantiates a new xML entity.
	 */
	public XMLEntity() {

	}

	/**
	 * Instantiates a new xML entity.
	 * 
	 * @param tag
	 *            the tag
	 */
	public XMLEntity(String value) {
		this(new XMLTokener(value));
	}

	/**
	 * Construct a XMLEntity from a Tokener.
	 * 
	 * @param value
	 *            A Tokener object containing the source string. or a duplicated
	 *            key.
	 */
	public XMLEntity(Tokener tokener) {
		this();
		tokener.parseToEntity((BaseEntity) this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni.kassel.peermessage.BaseEntity#getNewArray()
	 */
	public EntityList getNewArray() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni.kassel.peermessage.BaseEntity#getNewObject()
	 */
	public Entity getNewObject() {
		return new XMLEntity();
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public ArrayList<XMLEntity> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<XMLEntity>();
		}
		return this.children;
	}

	/**
	 * Adds the child.
	 * 
	 * @param child
	 *            the child
	 */
	public void addChild(XMLEntity child) {
		getChildren().add(child);
	}

	/**
	 * Gets the child.
	 * 
	 * @param tag
	 *            the tag
	 * @return the child
	 */
	public XMLEntity getChild(String tag) {
		for (XMLEntity entity : getChildren()) {
			if (tag.equals(entity.getTag())) {
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
	 * @param tag
	 *            the new tag
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
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni.kassel.peermessage.Entity#toString()
	 */
	@Override
	public String toString() {
		return toString(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni.kassel.peermessage.Entity#toString(int)
	 */
	@Override
	public String toString(int indentFactor) {
		return toString(indentFactor, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.uni.kassel.peermessage.Entity#toString(int, int)
	 */
	@Override
	public String toString(int indentFactor, int intent) {
		StringBuilder sb = new StringBuilder();
		if (indentFactor > 0) {
			sb.append("\n");
		}
		for (int i = 0; i < indentFactor; i += 1) {
			sb.append(' ');
		}
		sb.append("<" + this.getTag());
		Map<String, Object> attributes = getMap();

		for (Iterator<Entry<String, Object>> i = attributes.entrySet()
				.iterator(); i.hasNext();) {
			Entry<String, Object> attribute = i.next();
			sb.append(" " + attribute.getKey() + "="
					+ EntityUtil.quote((String) attribute.getValue()));
		}

		boolean hasChild = (this.children != null && this.children.size() > 0);
		if (this.value == null && !hasChild) {
			sb.append("/>");
		} else {
			sb.append(">");
			sb.append(toStringValue(indentFactor));
			sb.append("</" + getTag() + ">");
		}
		return sb.toString();
	}

	protected String toStringValue(int indentFactor) {
		StringBuilder sb = new StringBuilder();

		// parse Children
		if (this.children != null && this.children.size() > 0) {
			for (XMLEntity child : this.children) {
				sb.append(child.toString(indentFactor));
			}
		} else if (this.value != null) {
			sb.append(this.value);
		}
		return sb.toString();
	}

	public BaseEntityList initWithMap(Collection<?> value) {
		for (Iterator<?> i = value.iterator(); i.hasNext();) {
			children.add((XMLEntity) i.next());
		}
		return this;
	}

	public BaseEntityList put(Object value) {
		children.add((XMLEntity) value);
		return this;
	}

	@Override
	public boolean add(Object value) {
		return children.add((XMLEntity) value);
	}

	@Override
	public Object get(int index) {
		return children.get(index);
	}
}
