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
import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.exceptions.TextParsingException;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;
import org.sdmlib.serialization.interfaces.JSIMEntity;

public class XMLTokener extends Tokener{

	public XMLTokener(String s) {
		super(s);
	}

	/**
	 * Get the next value. The value can be a Boolean, Double, Integer,
	 * JSONArray, JSONObject, Long, or String, or the JSONObject.NULL object.
	 * 
	 * @return An object.
	 */
	@Override
	public Object nextValue(JSIMEntity creator) {
		char c = nextClean();

		switch (c) {
		case '"':
		case '\'':
			return nextString(c, false, false);
		case '<':
			back();
			JSIMEntity element = creator.getNewObject();
			if (element instanceof Entity) {
				parseToEntity((Entity) element);
			}
			return element;
		default:
			break;
		}
		back();
		return super.nextValue(creator);
	}

	@Override
	public void parseToEntity(BaseEntity entity) {
		char c;

		if (nextClean() != '<') {
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
		back();
		xmlEntity.setTag(sb.toString());
		XMLEntity child;
		while (true) {
			c = nextClean();
			if (c == 0) {
				break;
			} else if (c == '>') {
				c = nextClean();
				if (c != '<') {
					back();
					xmlEntity.setValue(nextString('<', false, false));
					back();
					continue;
				}
			}

			if (c == '<') {
				if (next() == '/') {
					stepPos(">", false, false);
					next();
					break;
				} else {
					back();
					back();
					if (getCurrentChar() == '<') {
						child = (XMLEntity) xmlEntity.getNewObject();
						parseToEntity((BaseEntity) child);
						xmlEntity.addChild(child);
					} else {
						xmlEntity.setValue(nextString('<', false, false));
						back();
					}
				}
			} else if (c == '/') {
				next();
				break;
			} else {
				back();
				String key = nextValue(xmlEntity).toString();
				if (key != null) {
					// The key is followed by ':'. We will also tolerate '=' or
					// '=>'.
					c = nextClean();
					if (c == '=') {
						if (next() != '>') {
							back();
						}
					}
					xmlEntity.put(key, nextValue(xmlEntity));
				}
			}
		}
	}

	@Override
	public void parseToEntity(BaseEntityList entityList) {
		// Do Nothing
	}
}
