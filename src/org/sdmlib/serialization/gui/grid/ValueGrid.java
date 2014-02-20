package org.sdmlib.serialization.gui.grid;

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
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.sdmlib.serialization.interfaces.GUIPosition;

public class ValueGrid {
	private int maxRows=0;
	private int maxColumns=0;
	private LinkedHashMap<GridStyle, Object> children=new LinkedHashMap<GridStyle, Object>();
	private LinkedHashMap<PropertyChangeListener, Object> container=new LinkedHashMap<PropertyChangeListener, Object>();
	private GridStyle selectedCell;
	private GridGUITable guiElement;


	public ValueGrid withGridTable(GridGUITable value){
		this.guiElement = value;
		return this;
	}
	
	public GridStyle add(Object node, int col, int row, String width, String height) {
		GridStyle cell = add(node, col, row);
		cell.withHeight(height).withWidth(width);
		return cell;
	}
	
	public GridStyle add(Object node, int col, int row) {
		boolean refresh=false;

		if(col>=maxColumns){
			maxColumns = col;
			refresh=true;
		}
		if(row>=maxRows){
			maxRows = row;
			refresh=true;
		}

		GridStyle guiCell = null;
		if(guiElement != null){
			guiCell=guiElement.getNewStyle();
			PropertyChangeListener child;
			if(node instanceof PropertyChangeListener){
				child = (PropertyChangeListener) node;
			}else{
				child = guiElement.getNewCell(node);
				container.put(child, node);
			}
			guiCell.addPropertyChangeListener(child);
			guiCell.withGrid(this);
			guiCell.withColumn(col);
			guiCell.withRow(row);
			
			children.put(guiCell, node);
			if(guiElement!=null){
				guiElement.add(child);
			}
		}
		

		if(refresh){
			refreshLines();
		}
		return guiCell;
	}
	
	public GridStyle getGridStyle(Object node){
		if(node instanceof GridStyle){
			return (GridStyle) node;
		}
		Object result=container.get(node);
		if(result==null){
			result = node;
		}
		for(Iterator<Entry<GridStyle, Object>> iterator = children.entrySet().iterator();iterator.hasNext();){
			Entry<GridStyle, Object> item = iterator.next();
			if(item.getValue()==result){
				return item.getKey();
			}
		}
		return null;
	}
	
	public Object getCell(Object node){
		if(node instanceof GridStyle){
			return children.get(node);
		}
		for(Iterator<Entry<PropertyChangeListener, Object>> iterator = container.entrySet().iterator();iterator.hasNext();){
			Entry<PropertyChangeListener, Object> item = iterator.next();
			if(item.getValue()==node){
				return item.getKey();
			}
		}
		return node;
	}
	
	public void setSpanRow(Object node, int row){
		GridStyle cell = getGridStyle(node);

		if(cell!=null){
			cell.withRowSpan(row);
			refreshLines();
		}
	}
	
	public void setSpanColumn(Object node, int column){
		GridStyle cell = getGridStyle(node);

		if(cell!=null){
			cell.withColumnSpan(column);
			refreshLines();
		}
	}
	
	public void refreshLines(){
		 for (GridStyle n: children.keySet()) {
			 int rowEnd = n.getRowEnd();
			 int colEnd = n.getColumnEnd();

			 n.setBorder(GUIPosition.NORTH, "1", "black");
			 n.setBorder(GUIPosition.WEST, "1", "black");
			 if(rowEnd>=maxRows){
				 n.setBorder(GUIPosition.SOUTH, "1", "black");
			 }else{
				 n.setBorder(GUIPosition.SOUTH, null, null);
			 }
			 if(colEnd>=maxColumns){
				 n.setBorder(GUIPosition.EAST, "1", "black");
			 }else{
				 n.setBorder(GUIPosition.EAST, null, null);

			 }
		}
	}

	public boolean selectCell(GridStyle cell) {
		if(selectedCell==cell){
			return true;
		}
		if(selectedCell!=null){
			selectedCell.deselect();
			selectedCell=null;
		}
		if(cell!=null){
			cell.select();
			this.selectedCell=cell;
			return true;
		}
		return false;
	}

	public int getCountColumns() {
		return maxColumns;
	}
	public int getCountRows() {
		return maxRows;
	}
	
	public void insertRow(int offset){
		GridStyle[] items = children.keySet().toArray(new GridStyle[children.size()]);
		for(GridStyle cell : items){
			if(cell.getRow()>=offset){
				cell.withRow(cell.getRow()+1);
				if(guiElement!=null){
					guiElement.move(cell);
				}
			}
		}
		maxRows++;
	}
}
