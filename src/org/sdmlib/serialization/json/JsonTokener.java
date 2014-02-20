package org.sdmlib.serialization.json;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or - as soon they
 will be approved by the European Commission - subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.TextParsingException;
import org.sdmlib.serialization.Tokener;
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
