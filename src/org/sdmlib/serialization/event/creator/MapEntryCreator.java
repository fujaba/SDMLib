package org.sdmlib.serialization.event.creator;

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
import java.util.Map.Entry;
import org.sdmlib.serialization.event.MapEntry;
import org.sdmlib.serialization.event.MapSet;
import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class MapEntryCreator implements SendableEntityCreator, NoIndexCreator {
	public static final String PROPERTY_KEY = "key";
	public static final String PROPERTY_VALUE = "value";
	private final String[] properties = new String[] { PROPERTY_KEY,
			PROPERTY_VALUE };

	public String[] getProperties() {
		return properties;
	}

	public Object getSendableInstance(boolean prototyp) {
		return new MapEntry();
	}

	public Object getValue(Object entity, String attribute) {
		Entry<?, ?> obj = ((Entry<?, ?>) entity);
		if (PROPERTY_KEY.equalsIgnoreCase(attribute)) {
			return obj.getKey();
		} else if (PROPERTY_VALUE.equalsIgnoreCase(attribute)) {
			return obj.getValue();
		}
		return null;
	}

	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		MapEntry entry = (MapEntry) entity;
		if (PROPERTY_KEY.equalsIgnoreCase(attribute)) {
			entry.setKey(value);
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

}
