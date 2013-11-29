package org.sdmlib.serialization.gui.table;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public interface ColumnListener {
	public boolean canEdit(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator);
	public void onSelection(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator, int x, int y);
	public Object onEdit(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator);
	public Object getValue(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator);
	public boolean setValue(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator, Object value);
	public void dispose();
	public boolean updateWidth(int oldWidth, int newWidth);
	public void update(Object cell);
	public ColumnListener withColumn(Column column);
}
