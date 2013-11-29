package org.sdmlib.serialization.gui.table;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public interface TableCellValue {
	public Column getColumn();
	public SendableEntityCreator getCreator();
	public Object getItem();
	public Object getSimpleValue();
}
