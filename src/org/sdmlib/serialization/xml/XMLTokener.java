package org.sdmlib.serialization.xml;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
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

public class XMLTokener extends Tokener {
	/**
	 * Get the next value. The value can be a Boolean, Double, Integer,
	 * JSONArray, JSONObject, Long, or String, or the JSONObject.NULL object.
	 * 
	 * @return An object.
	 */
	@Override
	public Object nextValue(BaseEntity creator) {
		char c = nextClean();

		switch (c) {
		case '"':
		case '\'':
			next();
			return nextString(c, false);
		case '<':
			back();
			BaseEntity element = creator.getNewObject();
			if (element instanceof Entity) {
				parseToEntity((Entity) element);
			}
			return element;
		default:
			break;
		}
		back();
		if(getCurrentChar()=='"'){
			next();
			next();
			return "";
		}
		return super.nextValue(creator);
	}

	@Override
	public void parseToEntity(BaseEntity entity) throws TextParsingException{
		char c=getCurrentChar();

		if (c!= '<') {
			c = nextClean();
		}
		if (c != '<') {
			throw new TextParsingException("A XML text must begin with '<'",
					this);
		}
		if (!(entity instanceof XMLEntity)) {
			throw new TextParsingException("Parse only XMLEntity", this);
		}
		XMLEntity xmlEntity = (XMLEntity) entity;
		StringBuilder sb = new StringBuilder();
		c = nextClean();
		while (c >= ' ' && getStopChars().indexOf(c) < 0) {
			sb.append(c);
			c = next();
		}
		xmlEntity.setTag(sb.toString());
		XMLEntity child;
		while (true) {
			c = nextStartClean();
			if (c == 0) {
				break;
			} else if (c == '>') {
				c = nextClean();
				if(c==0){
					return;
				}
				if (c != '<') {
					xmlEntity.setValue(nextString('<', false));
					back();
					continue;
				}
			}

			if (c == '<') {
				if (charAt(position()+1) == '/') {
					stepPos(">", false, false);
					break;
				} else {
					if (getCurrentChar() == '<') {
						child = (XMLEntity) xmlEntity.getNewObject();
						parseToEntity((BaseEntity) child);
						xmlEntity.addChild(child);
					} else {
						xmlEntity.setValue(nextString('<', false));
						back();
					}
				}
			} else if (c == '/') {
				next();
				break;
			} else {
				back();
				String key = nextValue(xmlEntity).toString();
				if ( key.length()>0 ) {
					xmlEntity.put(key, nextValue(xmlEntity));
				}
			}
		}
	}

	@Override
	public XMLTokener withText(String value) {
		super.withText(value);
		return this;
	}
	
	@Override
	public void parseToEntity(BaseEntityList entityList) {
		// Do Nothing
	}
}
