package org.sdmlib.serialization.xml.creator;

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
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.gui.Style;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLGrammar;
import org.sdmlib.serialization.xml.XMLEntity;
import org.sdmlib.serialization.xml.XMLStyledEntity;

public class XMLStyledEntityCreator implements SendableEntityCreator, XMLGrammar {
	/** The properties. */
	private final String[] properties = new String[] {
			Style.PROPERTY_FONTFAMILY,
			Style.PROPERTY_FONTSIZE, Style.PROPERTY_BOLD,
			Style.PROPERTY_ITALIC };

	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new XMLStyledEntity();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return ((XMLStyledEntity) entity).get(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		return ((XMLStyledEntity) entity).set(attribute, value);
	}

	public boolean parseChild(XMLEntity entity, XMLEntity child, Tokener value) {
		XMLStyledEntity source = (XMLStyledEntity) entity;
		XMLStyledEntity target = (XMLStyledEntity) child;

		for (String property : getProperties()) {
			if(source.get(property)!=null){
				target.set(property, source.get(property));
			}
		}

		if ("b".equalsIgnoreCase(child.getTag())) {
			if (!source.isBold()) {
				source.setBold(true);
				return true;
			}
		}
		return false;
	}

	@Override
	public void addChildren(XMLEntity parent, XMLEntity child) {
		parent.addChild(child);
	}

	@Override
	public void endChild(String tag) {
	}
}
