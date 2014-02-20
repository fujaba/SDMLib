package org.sdmlib.serialization.gui.table;

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

public interface CellEditorElement {
	public CellEditorElement withColumn(Column column);
	public void cancel();
	public boolean setFocus(boolean value);
	public boolean onActive(boolean value);
	public boolean nextFocus();
	public void apply();
	public Object getValue(boolean convert);
	public CellEditorElement withValue(Object value);
	public FieldTyp getControllForTyp(Object value);
	public void dispose();
}
