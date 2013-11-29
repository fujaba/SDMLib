package org.sdmlib.serialization.gui.grid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.sdmlib.serialization.gui.GUILine;
import org.sdmlib.serialization.gui.Style;
import org.sdmlib.serialization.interfaces.GUIPosition;
import org.sdmlib.serialization.interfaces.SendableEntity;

public class GridStyle extends Style implements SendableEntity{
	public static final String PROPERTY_ROW = "gridpane-row";
	public static final String PROPERTY_COLUMN = "gridpane-column";
	public static final String PROPERTY_ROWSPAN = "gridpane-row-span";
	public static final String PROPERTY_COLUMNSPAN = "gridpane-column-span";
	public static final String PROPERTY_HEIGHTEXPRESSION="height_expression";
	public static final String PROPERTY_WIDTHEXPRESSION="width_expression";
	public static final String PROPERTY_BORDER="border";
	public static final String COUNT="count";
	public static final String POSITION="position";
	public static final String MAXIMIZE="maximize";
	private String heightExpression;
	private String widthExpression;
	private int rowSpan=1;
	private int columnSpan=1;
	private int column;
	private int row;
	private ValueGrid grid;
	private LinkedHashMap<String, ArrayList<PropertyChangeListener>> listeners=new LinkedHashMap<String, ArrayList<PropertyChangeListener>>();
	private String selectedBackground=null;

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	public int getRowEnd(){
		if(heightExpression!=null){
			return 0;
		}

		return getRow()+getRowSpan()-1;
	}
	public int getColumnEnd(){
		if(widthExpression!=null){
			return 0;
		}
		return getColumn()+getColumnSpan()-1;
	}
	
	public GridStyle withGrid(ValueGrid grid){
		this.grid=grid;
		return this;
	}
	
	public int getColumnSpan() {
		if(widthExpression!=null){
			return getColumnEnd()-getColumn()+1;
		}
		return columnSpan;
	}

	public int getRowSpan() {
		if(heightExpression!=null){
			return getRowEnd()-getRow()+1;
		}
		return rowSpan;
	}
	
	public GridStyle withHeight(String value) {
		String oldValue = heightExpression;
		this.heightExpression = value;
		propertyChange(PROPERTY_HEIGHTEXPRESSION, oldValue, value);
		return this;
	}

	public GridStyle withWidth(String value) {
		String oldValue = widthExpression;
		this.widthExpression = value;
		propertyChange(PROPERTY_WIDTHEXPRESSION, oldValue, value);
		return this;
	}
	
	public void withRow(int value) {
		int oldValue = row;
		this.row = value;
		propertyChange(PROPERTY_ROW, oldValue, value);
	}
	
	public void withColumn(int value) {
		int oldValue = column;
		this.column = value;
		propertyChange(PROPERTY_COLUMN, oldValue, value);
	}
	
	public GridStyle withRowSpan(int value) {
		if(value<1) {
			return this;
		}
		int oldValue = rowSpan;
		this.rowSpan = value;
		propertyChange(PROPERTY_ROWSPAN, oldValue, value);
		return this;
	}
	
	public GridStyle withColumnSpan(int value) {
		if(value<1) {
			return this;
		}
		
		int oldValue = columnSpan;
		this.columnSpan = value;
		propertyChange(PROPERTY_COLUMNSPAN, oldValue, value);
		return this;
	}

	@Override
	public void propertyChange(String propertyName, Object oldValue, Object newValue) {
		super.propertyChange(propertyName, oldValue, newValue);
		
		if((oldValue==null && newValue!=null)||(oldValue!=null && !oldValue.equals(newValue))){
			PropertyChangeEvent change = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
			executeEvent(change, null);
			executeEvent(change, propertyName);
		}
	}
	
	private void executeEvent(PropertyChangeEvent change, String key){
		ArrayList<PropertyChangeListener> list = listeners.get(key);
		if(list != null){
			for(PropertyChangeListener listener : list){
				listener.propertyChange(change);
			}
		}
	}
	
	@Override
	public boolean addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		ArrayList<PropertyChangeListener> list = listeners.get(propertyName);
		if(list==null){
			list = new ArrayList<PropertyChangeListener>();
			listeners.put(propertyName, list);
		}
		return list.add(listener);
	}

	@Override
	public boolean addPropertyChangeListener(PropertyChangeListener listener) {
		ArrayList<PropertyChangeListener> list = listeners.get(null);
		if(list==null){
			list = new ArrayList<PropertyChangeListener>();
			listeners.put(null, list);
		}
		return list.add(listener);
	}

	@Override
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		boolean result=false;
		for(Iterator<Entry<String, ArrayList<PropertyChangeListener>>> iterator = listeners.entrySet().iterator();iterator.hasNext();){
			Entry<String, ArrayList<PropertyChangeListener>> item = iterator.next();
			for(Iterator<PropertyChangeListener> i = item.getValue().iterator();i.hasNext();){
				PropertyChangeListener propertyChangeListener = i.next();
				if(propertyChangeListener==listener){
					i.remove();
					result=true;
				}
			}
			if(item.getValue().size()<1){
				iterator.remove();
			}
		}
		return result;
	}
	
	public void setBorder(GUIPosition position, String width, String color){
		GUILine border = this.borders.get(position);
		if(width!=null){
			if(border==null){
				this.borders.put(position, new GUILine().withColor(color).withWidth(width));
				this.propertyChange(PROPERTY_BORDER, null, this.borders);
			}else{
				border.withColor(color);
				border.withWidth(width);
				this.propertyChange(PROPERTY_BORDER, null, this.borders);
			}
		}else if(border!=null){
			this.borders.remove(position);
			this.propertyChange(PROPERTY_BORDER, null, this.borders);
		}
	}
	
	public void select(){
		if(selectedBackground==null){
			selectedBackground = this.getBackground();
			withBackground("#d8f0f3");
		}
	}
	
	public void deselect(){
		if(selectedBackground != null ){
			withBackground(selectedBackground);
			selectedBackground = null;
		}
	}
	
	public void maximize(){
		propertyChange(MAXIMIZE, null, null);
	}
}
