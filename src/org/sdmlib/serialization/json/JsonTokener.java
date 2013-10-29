package org.sdmlib.serialization.json;

/*
 NetworkParser
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
import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.exceptions.TextParsingException;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;
import org.sdmlib.serialization.interfaces.TextEntity;
import org.sdmlib.serialization.xml.XMLEntity;

public class JsonTokener extends Tokener {
	public final static String STOPCHARS = ",:]}/\\\"[{;=# ";

	@Override
	public Object nextValue(BaseEntity creator) {
		char c = nextStartClean();

		switch (c) {
		case '"':
		case '\'':
			next();
			return nextString(c, false);
		case '{':
			BaseEntity element = creator.getNewObject();
			if (element instanceof Entity) {
				this.parseToEntity((Entity) element);
			}
			return element;
		case '[':
			BaseEntityList elementList = creator.getNewArray();
			this.parseToEntity(elementList);
			return elementList;
		default:
			break;
		}
//		back();
		return super.nextValue(creator);
	}

	@Override
	protected String getStopChars() {
		return STOPCHARS;
	}

	public Entity parseEntity(JsonObject parent, Entity newValue) {
		if (newValue instanceof XMLEntity) {
			XMLEntity xmlEntity = (XMLEntity) newValue;
			String[] names = Entity.getNames(xmlEntity);
			parent.put(JsonIdMap.CLASS, xmlEntity.getTag());
			JsonObject props = new JsonObject();
			if (xmlEntity.getValue() != null
					&& xmlEntity.getValue().length() > 0) {
				parent.put(JsonIdMap.VALUE, xmlEntity.getValue());
			}
			for (String prop : names) {
				Object propValue = xmlEntity.get(prop);
				parseEntityProp(props, propValue, prop);
			}
			for (XMLEntity children : xmlEntity.getChildren()) {
				parseEntityProp(props, children, children.getTag());
			}
			parent.put(JsonIdMap.JSON_PROPS, props);
		}
		return parent;
	}

	public void parseEntityProp(JsonObject props, Object propValue, String prop) {
		if (propValue != null) {
			if (propValue instanceof XMLEntity) {
				if (props.has(prop)) {
					Object child = props.get(prop);
					JsonArray propList = null;
					if (child instanceof JsonObject) {
						propList = new JsonArray();
						propList.put(child);
					} else if (child instanceof JsonArray) {
						propList = (JsonArray) child;
					}
					if (propList != null) {
						propList.put(parseEntity(new JsonObject(),
								(XMLEntity) propValue));
						props.put(prop, propList);
					}
				} else {
					props.put(
							prop,
							parseEntity(new JsonObject(), (XMLEntity) propValue));
				}
			} else {
				props.put(prop, propValue);
			}
		}
	}

	/**
	 * Cross compiling
	 * 
	 * @param parent
	 * @param newValue
	 * @return
	 */
	public Entity parseToEntity(JsonObject parent, Entity newValue) {
		if (newValue instanceof XMLEntity) {
			XMLEntity xmlEntity = (XMLEntity) newValue;
			String[] names = Entity.getNames(xmlEntity);
			parent.put(JsonIdMap.CLASS, xmlEntity.getTag());
			JsonObject props = new JsonObject();
			if (xmlEntity.getValue() != null
					&& xmlEntity.getValue().length() > 0) {
				parent.put(JsonIdMap.VALUE, xmlEntity.getValue());
			}
			for (String prop : names) {
				Object propValue = xmlEntity.get(prop);
				parseEntityProp(props, propValue, prop);
			}
			for (XMLEntity children : xmlEntity.getChildren()) {
				parseEntityProp(props, children, children.getTag());
			}
			parent.put(JsonIdMap.JSON_PROPS, props);
		}
		return parent;
	}

	@Override
	public void parseToEntity(BaseEntity entity) throws TextParsingException{
		if (!(entity instanceof TextEntity)) {
			return;
		}
		char c;
		String key;
		TextEntity item=(TextEntity)entity;
		if (nextStartClean() != '{') {
			throw new TextParsingException(
					"A JsonObject text must begin with '{'", this);
		}
		next();
		for (;;) {
			c = nextStartClean();
			switch (c) {
			case 0:
				throw new TextParsingException(
						"A JsonObject text must end with '}'", this);
			case '}':
				next();
				return;
			case ',':
				next();
				key = nextValue(item).toString();
				break;
			default:
				key = nextValue(item).toString();
			}
			c = nextStartClean();
			if (c == '=') {
				if (charAt(position()+1) == '>') {
					next();
				}
			} else if (c != ':') {
				throw new TextParsingException("Expected a ':' after a key ["
						+ getNextString(30) + "]", this);
			}
			next();
			item.put(key, nextValue(entity));
		}
	}

	@Override
	public void parseToEntity(BaseEntityList entityList) throws TextParsingException{
		char c=nextStartClean();
		if (c != '[') {
			throw new TextParsingException(
					"A JSONArray text must start with '['", this);
		}
		if ((nextClean()) != ']') {
			for (;;) {
				c=getCurrentChar();
				if (c == ',') {
					entityList.put(null);
				} else {
					entityList.put(nextValue(entityList));
				}
				c = nextStartClean();
				switch (c) {
				case ';':
				case ',':
					if (nextClean() == ']') {
						return;
					}
					break;
				case ']':
					next();
					return;
				default:
					throw new TextParsingException("Expected a ',' or ']' not '"+getCurrentChar()+"'",
							this);
				}
			}
		}
		next();
	}
}
