package org.sdmlib.serialization.gui.table;

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

public enum FieldTyp {
	ASSOC(1), DATE(2), CHECKBOX(3), COMBOBOX(4), DOUBLE(5), INTEGER(6), TEXT(7), PASSWORD(8), VALUEFROMDROPDOWNLIST(9);

	private int value;
	
	FieldTyp(int value) {
		this.setValue(value);
    }
	  
	  public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
