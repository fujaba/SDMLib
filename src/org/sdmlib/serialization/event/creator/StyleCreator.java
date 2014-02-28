package org.sdmlib.serialization.event.creator;

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
import org.sdmlib.serialization.gui.Style;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class StyleCreator implements SendableEntityCreator{
	private static final String[] props=new String[]{Style.PROPERTY_BOLD, Style.PROPERTY_ITALIC, Style.PROPERTY_FONTFAMILY, 
													Style.PROPERTY_FONTSIZE, Style.PROPERTY_FORGROUND, Style.PROPERTY_BACKGROUND,
													Style.PROPERTY_UNDERLINE, Style.PROPERTY_ALIGNMENT, Style.PROPERTY_WIDTH, Style.PROPERTY_HEIGHT};

	@Override
	public String[] getProperties() {
		return props;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new Style();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return ((Style)entity).get(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		return ((Style)entity).set(attribute, value);
	}

}
