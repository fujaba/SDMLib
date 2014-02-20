package org.sdmlib.serialization.gui.table.creator;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or higher as soon they
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
import org.sdmlib.serialization.gui.table.Column;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ColumnCreator implements SendableEntityCreator{
	private static final String[] properties=new String[]{Column.PROPERTY_ATTRNAME,
		Column.PROPERTY_NUMBERFORMAT,
		Column.PROPERTY_EDITCOLUMN,
		Column.PROPERTY_LABEL,
		Column.PROPERTY_DEFAULTTEXT,
		Column.PROPERTY_RESIZE,
		Column.PROPERTY_VISIBLE,
		Column.PROPERTY_MOVABLE,
		Column.PROPERTY_ALTTEXT,
		Column.PROPERTY_BROWSERID,
		Column.PROPERTY_FIELDTYP,
		Column.PROPERTY_STYLE,
		Column.PROPERTY_ACTIVESTYLE};

	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new Column();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return ((Column)entity).get(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		return ((Column)entity).set(attribute, value);
	}

}
