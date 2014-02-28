package org.sdmlib.serialization.logic;

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
import java.util.ArrayList;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class Or implements Condition, SendableEntityCreator {
	public static final String CHILD="childs";
	private ArrayList<Condition> list = new ArrayList<Condition>();

	public Or add(Condition... conditions) {
		for (Condition condition : conditions) {
			this.list.add(condition);
		}
		return this;
	}
	
	private ArrayList<Condition> getList() {
		return list;
	}

	@Override
	public boolean matches(ValuesSimple values) {
		boolean result = true;
		for (Condition condition : list) {
			if (!condition.matches( values )) {
				result = false;
			}
		}
		return result;
	}

	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(Condition condition : list){
			sb.append("["+condition.toString()+" ");
		}
		sb.trimToSize();
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String[] getProperties() {
		return new String[]{CHILD};
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new Or();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		if(CHILD.equalsIgnoreCase(attribute)){
			return ((Or)entity).getList();
		}
		return null;
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		if(CHILD.equalsIgnoreCase(attribute)){
			((Or)entity).add((Condition) value);
			return true;
		}
		return false;
	}

}
