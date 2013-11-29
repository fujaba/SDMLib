package org.sdmlib.serialization.gui.grid;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
