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
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class Between implements Condition, SendableEntityCreator {
	public static final String FROM="from";
	public static final String TO="to";

	private double fromValue;
	private double toValue;

	public Between withRange(double from, double to){
		this.fromValue = from;
		this.toValue = to;
		return this;
	}
	
	public Between withFrom(double from){
		this.fromValue = from;
		return this;
	}
	
	public Between withTo(double to){
		this.toValue = to;
		return this;
	}

	public double getFrom() {
		return fromValue;
	}
	
	public double getTo() {
		return toValue;
	}

	@Override
	public boolean matches(ValuesSimple values) {
		if(values.value instanceof Number){
			return (((Double)values.value)>=fromValue && ((Double)values.value)<=toValue);
		}
		return false;
	}

	@Override
	public String[] getProperties() {
		return new String[]{FROM, TO};
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new Between();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		if(FROM.equalsIgnoreCase(attribute)){
			return ((Between)entity).getFrom();
		}
		if(TO.equalsIgnoreCase(attribute)){
			return ((Between)entity).getTo();
		}
		return null;
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		if(FROM.equalsIgnoreCase(attribute)){
			((Between)entity).withFrom((Double) value);
			return true;
		}
		if(TO.equalsIgnoreCase(attribute)){
			((Between)entity).withTo((Double) value);
			return true;
		}
		return false;
	}
}
