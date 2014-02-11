package org.sdmlib.serialization;

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
import java.util.TreeMap;
import org.sdmlib.serialization.interfaces.LocalisationInterface;
import org.sdmlib.serialization.interfaces.PeerMessage;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class TextItems implements SendableEntityCreator, PeerMessage, LocalisationInterface {
	public static final String PROPERTY_VALUE = "value";
	private TreeMap<String, String> values = new TreeMap<String, String>();
	private LocalisationInterface customLanguage = null;
	
	@Override
	public Object getValue(Object entity, String attribute) {
		return ((TextItems) entity).get(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		return ((TextItems) entity).set(attribute, value);
	}

	@Override
	public Object get(String attribute) {
		if (values.containsKey(attribute)) {
			return values.get(attribute);
		}
		return attribute;
	}
	
	public String getText(String label, Object model, Object gui) {
		String text = null;
		if (customLanguage != null) {
			text = customLanguage.getText(label, model, gui);
			if (text != null) {
				return text;
			}
		}
		text = getLabelValue(label + "."
				+ System.getProperty("java.class.version", ""));
		if (text != null) {
			return text;

		}
		text = getLabelValue(label);
		if (text != null) {
			return text;
		}
		return label;
	}

	private String getLabelValue(String label) {
		if (values.containsKey(label)) {
			return values.get(label);
		}
		return null;
	}

	public void addTextLabel(String key, String value) {
		values.put(key, value);
	}

	@Override
	public boolean set(String attribute, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getProperties() {
		return new String[] { PROPERTY_VALUE };
	}

	public Object getSendableInstance(boolean prototyp) {
		return new TextItems();
	}
	public void setCustomLanguage(LocalisationInterface value){
		this.customLanguage=value;
	}
}
