package de.uniks.jism.gui.table;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class CellValueCreator {
	public Object getCellValue(Object value){
		return getCellValue(value, null, null);
	}
	public Object getCellValue(Object value, SendableEntityCreator creator, String property){
		if(creator!=null&&property!=null){
			return creator.getValue(value, property);
		}
		return null;
	}
}
