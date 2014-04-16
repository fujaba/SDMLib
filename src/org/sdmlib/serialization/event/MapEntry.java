package org.sdmlib.serialization.event;

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
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.serialization.ArrayEntryList;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreatorNoIndex;

public class MapEntry<K> implements Entry<K, Object>, SendableEntityCreator, SendableEntityCreatorNoIndex, BaseEntity{
	public static final String PROPERTY_KEY = "key";
	public static final String PROPERTY_VALUE = "value";
	private final String[] properties = new String[] { PROPERTY_KEY, PROPERTY_VALUE };
	private K key;
	private boolean visible=true;
	private Object value;

	public MapEntry<K> with(K key, Object value) {
		this.key = key;
		this.value = value;
		return this;
	}

	public Object setKey(K key) {
		this.key = key;
		return key;
	}

	@Override
	public K getKey() {
		return key;
	}
	
	public String getKeyString() {
		if(key instanceof String){
			return ""+key;
		}
		throw new RuntimeException("Key is not a String <"+key+">");
	}
	
	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object setValue(Object value) {
		this.value = value;
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Map.Entry))
			return false;
		Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
		return eq(key, e.getKey()) && eq(value, e.getValue());
	}

	@Override
	public int hashCode() {
		return ((key == null) ? 0 : key.hashCode())
				^ ((value == null) ? 0 : value.hashCode());
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}
	
	public MapEntry<K> withKey(K key) {
		this.key = key;
		return this;
	}

	public MapEntry<K> withValue(Object value) {
		this.value = value;
		return this;
	}

	private static boolean eq(Object o1, Object o2) {
		return (o1 == null ? o2 == null : o1.equals(o2));
	}
	
	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new MapEntry<K>();
	}
	
	@Override
	public Object getValue(Object entity, String attribute) {
		Entry<?, ?> obj = ((Entry<?, ?>) entity);
		if (PROPERTY_KEY.equalsIgnoreCase(attribute)) {
			return obj.getKey();
		} else if (PROPERTY_VALUE.equalsIgnoreCase(attribute)) {
			return obj.getValue();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		MapEntry<K> entry = (MapEntry<K>) entity;
		if (PROPERTY_KEY.equalsIgnoreCase(attribute)) {
			entry.setKey((K)value);
			return true;
		} else if (PROPERTY_VALUE.equalsIgnoreCase(attribute)) {
			if (value instanceof Entry<?, ?>) {
				Object map = entry.getValue();
				if (map == null) {
					map = new MapSet();
				}
				if (map instanceof MapSet) {
					((MapSet) map).add(value);
				}
				entry.setValue(map);
			} else {
				entry.setValue(value);
			}

			return true;
		}
		return false;
	}

	@Override
	public BaseEntityList getNewArray() {
		return new ArrayEntryList<MapEntry<K>>();
	}

	@Override
	public BaseEntity getNewObject() {
		return new MapEntry<K>();
	}

	@Override
	public String toString(int indentFactor) {
		return toString();
	}

	@Override
	public String toString(int indentFactor, int intent) {
		return toString();
	}

	@Override
	public BaseEntity withVisible(boolean value) {
		this.visible = value;
		return this;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
}
