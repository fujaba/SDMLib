package org.sdmlib.serialization.yuml;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or – as soon they
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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;

public class YUMLEntity implements BaseEntity {
	private String className;
	private String id;
	private boolean showLine;
	private boolean visible = true;
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
		if (!visible) {
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
		this.visible = value;
		return this;
	}

	@Override
	public boolean isVisible() {
		return visible;
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
	
	public String getTyp(int typ){
		if(typ==YUMLIdMap.OBJECT){
			return getId();
		}else if(typ==YUMLIdMap.CLASS){
			return getClassName();
		}
		return "";
	}
	
	public YUMLEntity withTyp(int typ, String value){
		if(typ==YUMLIdMap.OBJECT){
			withId(value);
		}else if(typ==YUMLIdMap.CLASS){
			withClassName(value);
		}
		return this;
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
