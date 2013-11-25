package org.sdmlib.serialization.yuml;

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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;

public class YUMLEntity implements BaseEntity {
	private String className;
	private String id;
	private boolean showLine;
	private boolean isVisible = true;
	private LinkedHashMap<String, String> objValues = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, String> clazzValues = new LinkedHashMap<String, String>();

	@Override
	public BaseEntityList getNewArray() {
		return new YUMLList();
	}

	@Override
	public BaseEntity getNewObject() {
		return new YUMLEntity();
	}

	@Override
	public String toString(int indentFactor) {
		return toString(indentFactor, 0);
	}

	@Override
	public String toString(int indentFactor, int intent) {
		if (id == null) {
			return toString(YUMLIdMap.CLASS, false);
		}
		return toString(YUMLIdMap.OBJECT, false);
	}

	public String toString(int typ, boolean shortString) {
		if (!isVisible) {
			return "";
		}
		if (typ == YUMLIdMap.OBJECT) {
			if (showLine) {
				String text = id + " : " + className;
				return "["
						+ text
						+ "\\n"
						+ new String(new char[text.length()]).replace("\0",
								"&oline;") + "]";
			}
			return "[" + id + " : " + className + parseValues(typ, shortString)
					+ "]";
		}
		return "[" + className + parseValues(typ, shortString) + "]";
	}

	public String parseValues(int typ, boolean shortString) {
		if (shortString) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, String>> i = null;
		String splitter = "";
		if (objValues.size() > 0) {
			if (typ == YUMLIdMap.OBJECT) {
				i = objValues.entrySet().iterator();
				splitter = "=";
			} else if (typ == YUMLIdMap.CLASS) {
				i = clazzValues.entrySet().iterator();
				splitter = ":";

			}
		}
		if (i != null) {
			sb.append("|");
			Entry<String, String> item = i.next();
			sb.append(item.getKey() + splitter + item.getValue());

			while (i.hasNext()) {
				item = i.next();
				sb.append(";");
				sb.append(item.getKey() + splitter + item.getValue());
			}
		}
		return sb.toString();
	}

	@Override
	public YUMLEntity withVisible(boolean value) {
		this.isVisible = value;
		return this;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

	// GETTER AND SETTER
	public String getClassName() {
		return className;
	}

	public YUMLEntity withClassName(String className) {
		this.className = className;
		return this;
	}

	public String getId() {
		return id;
	}

	public YUMLEntity withId(String id) {
		this.id = id;
		return this;
	}

	public boolean isShowLine() {
		return showLine;
	}

	public YUMLEntity withShowLine(boolean showLine) {
		this.showLine = showLine;
		return this;
	}

	public void addValue(String property, String clazz, String value) {
		this.objValues.put(property, value);
		this.clazzValues.put(property, clazz);
	}
}
