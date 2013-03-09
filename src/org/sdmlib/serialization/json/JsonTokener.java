package org.sdmlib.serialization.json;

import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.exceptions.TextParsingException;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;
import org.sdmlib.serialization.interfaces.JSIMEntity;
import org.sdmlib.serialization.xml.XMLEntity;

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
public class JsonTokener extends Tokener{
	public final static String STOPCHARS=",:]}/\\\"[{;=# ";
	public JsonTokener() {
		super();
	}

	public JsonTokener(String s) {
		super(s);
	}

	@Override
	public Object nextValue(JSIMEntity creator) {
		char c = nextClean();

		switch (c) {
		case '"':
		case '\'':
			return nextString(c, false, false);
		case '{':
			back();
			JSIMEntity element = creator.getNewObject();
			if(element instanceof Entity){
				this.parseToEntity((Entity)element);
			}
			return element;
		case '[':
			back();
			BaseEntityList elementList = creator.getNewArray();
			this.parseToEntity(elementList);
			return elementList;
		default:
			break;
		}
		back();
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
	public void parseToEntity(BaseEntity entity) {
		char c;
		String key;

		if (nextClean() != '{') {
			throw new TextParsingException("A JsonObject text must begin with '{'", this);
		}
		for (;;) {
			c = nextClean();
			switch (c) {
			case 0:
				throw new TextParsingException("A JsonObject text must end with '}'", this);
			case '}':
				return;
			default:
				back();
				key = nextValue(entity).toString();
			}
			// The key is followed by ':'. We will also tolerate '=' or '=>'.
			c = nextClean();
			if (c == '=') {
				if (next() != '>') {
					back();
				}
			} else if (c != ':') {
				throw new TextParsingException("Expected a ':' after a key ["+getNextString(30)+"]", this);
			}
			entity.put(key, nextValue(entity));

			// Pairs are separated by ','. We will also tolerate ';'.
			char nextClean = nextClean();
			switch (nextClean) {
			case ';':
			case ',':
				if (nextClean() == '}') {
					return;
				}
				back();
				break;
			case '}':
				return;
			default:
				
				throw new TextParsingException("Expected a ',' or '}' got a " + nextClean, this);
			}
		}
	}

	@Override
	public void parseToEntity(BaseEntityList entityList) {
		if (nextClean() != '[') {
			throw new TextParsingException("A JSONArray text must start with '['", this);
		}
		if (nextClean() != ']') {
			back();
			for (;;) {
				if (nextClean() == ',') {
					back();
					entityList.put(null);
				} else {
					back();
					entityList.put(nextValue(entityList));
				}
				switch (nextClean()) {
				case ';':
				case ',':
					if (nextClean() == ']') {
						return;
					}
					back();
					break;
				case ']':
					return;
				default:
					throw new TextParsingException("Expected a ',' or ']'", this);
				}
			}
		}
	}
}
