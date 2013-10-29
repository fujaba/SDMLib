package org.sdmlib.serialization.event.creator;

/*
 NetworkParser
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
