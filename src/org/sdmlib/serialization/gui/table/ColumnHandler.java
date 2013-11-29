package org.sdmlib.serialization.gui.table;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ColumnHandler implements ColumnListener {
	public static final String AUTOEDIT="AUTOEDIT";
	protected Column column;

	public ColumnHandler withColumn(Column column){
		this.column = column;
		return this;
	}
	
	public Object getValue(Object entity, SendableEntityCreator creator){
		return creator.getValue(entity, column.getAttrName());
	}

	public boolean canEdit(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator){
		return column.isEditable();
	}
	
	public void onSelection(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator, int x, int y){
	}
	
	public Object onEdit(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator){
		return AUTOEDIT;
	}
	
	public Object getValue(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator) {
		if(creator==null){
			return null;
		}
		return getValue(entity, creator);
	}

	public boolean setValue(TableComponentInterface tableComponent, Object entity, SendableEntityCreator creator, Object value) {
		if(creator==null){
			return false;
		}
		return creator.setValue(entity, column.getAttrName(), value, IdMap.UPDATE);
	}
	public void dispose(){
		
	}
	public boolean updateWidth(int oldWidth, int newWidth){
		return true;
	}

	@Override
	public void update(Object cell) {
	}
}
