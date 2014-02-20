package org.sdmlib.serialization.json.creator;

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
import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonObject;

public class JsonObjectCreator implements SendableEntityCreator, NoIndexCreator {
	private final static String VALUE="VALUE";
	private final String[] properties = new String[] { VALUE };

	@Override
	public String[] getProperties() {
		return this.properties;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new JsonObject();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		if(VALUE.equalsIgnoreCase(attribute)){
			return entity.toString();
		}
		return ((JsonObject)entity).getValue(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String typ) {
		((JsonObject) entity).withValue((String) value);
		return true;
	}
}
