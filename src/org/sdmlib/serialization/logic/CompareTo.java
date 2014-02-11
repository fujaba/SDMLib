package org.sdmlib.serialization.logic;

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
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class CompareTo implements Condition, SendableEntityCreator {
	public static final String VALUE="value";
	public static final String COMPARE="compare";
	public static final int GREATER=1;
	public static final int LOWER=-1;
	private Comparable<Object> value;
	private int compare;
	
	public Comparable<?> getValue() {
		return value;
	}
	
	public CompareTo withValue(Comparable<Object> value) {
		this.value = value;
		return this;
	}
	
	public int getCompare() {
		return compare;
	}
	
	public CompareTo withCompare(int compare) {
		this.compare = compare;
		return this;
	}

	@Override
	public boolean matches(ValuesSimple value) {
		Object entityValue = value.value;
		if(entityValue!=null){
			if(entityValue instanceof Comparable<?>){
				Comparable<?> comparatorValue = (Comparable<?>) entityValue;
				if(compare<0){
					return this.value.compareTo(comparatorValue)<=compare;
				}else{
					return this.value.compareTo(comparatorValue)>=compare;
				}
			}
		}
		return false;
	}

	@Override
	public String[] getProperties() {
		return new String[]{COMPARE, VALUE};
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new CompareTo();
	}
	
	@Override
	public Object getValue(Object entity, String attribute) {
		if(COMPARE.equalsIgnoreCase(attribute)){
			return ((CompareTo)entity).getCompare();
		}
		if(VALUE.equalsIgnoreCase(attribute)){
			return ((CompareTo)entity).getValue();
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		if(COMPARE.equalsIgnoreCase(attribute)){
			((CompareTo)entity).withCompare(Integer.parseInt(""+value));
			return true;
		}
		if(VALUE.equalsIgnoreCase(attribute)){
			if(value instanceof Comparable<?>){
				((CompareTo)entity).withValue((Comparable<Object>) value);
			}
			return true;
		}		
		
		return false;
	}
}
