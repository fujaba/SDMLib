package de.uniks.jism.gui.table;

/*
 Json Id Serialisierung Map
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

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;
import org.sdmlib.serialization.IdMap;

public class TableColumnView implements ControlListener {
	public static final int BorderCell=10;
	private TableViewerColumn tableViewerColumn;
	private Column column;
	private TableColumnLabelProvider tableLabelProvider;
	private TableComponent owner;
	private Menu mnuColumns;
	protected ColumnViewerSorter columnViewerSorter;
	private TableCellMenuItem tableCellMenuItem;
	private String oldTextWidth=null;
	private int width;
	private int x;

	public TableColumnView(TableComponent tableComponent, Column column, Menu mnuColumns) {
		this.owner=tableComponent;
		this.mnuColumns=mnuColumns;
		this.column=column;

		setVisible(true);
	}

	public TableViewer getTableViewer(){
		return owner.getBrowserView(column.getBrowserId());
	}
	public void setVisible(boolean visible){
		if(tableViewerColumn==null&&visible){
			TableViewer tv=getTableViewer();
			tableViewerColumn = new TableViewerColumn(tv, SWT.NONE);
			if(tableCellMenuItem==null){
				tableCellMenuItem = new TableCellMenuItem(this, mnuColumns);
			}
			columnViewerSorter = new ColumnViewerSorter(this, owner, column);
			// RegExpression
			if(column.getEditColumn()!=null){
				tableViewerColumn.setEditingSupport(new TableCellEditingSupport(owner, tv, column, this));
			}else if(column.isEditingSupport()){
				if(column instanceof ColumnNotification){
					tableViewerColumn.setEditingSupport(((ColumnNotification)column).getEditingSupport(owner, tv, column));
				}
			}
			this.tableLabelProvider=new TableColumnLabelProvider(column, this);
			tableViewerColumn.setLabelProvider(tableLabelProvider);

			TableColumn tableColumn = tableViewerColumn.getColumn();
			tableColumn.setMoveable(column.isMovable());
			tableColumn.setWidth(column.getWidth());
			tableColumn.setText(""+column.getLabel());
			onNewText(column.getLabel(), 12);
			tableColumn.setResizable(column.isResizable());
			tableColumn.addControlListener(this);

			if(column.getTextalignment()!=-1){
				tableColumn.setAlignment(column.getTextalignment());
			}
			
		}else if(tableViewerColumn!=null&&!visible){
			tableViewerColumn.setEditingSupport(null);
			TableColumn tableColumn = tableViewerColumn.getColumn();
			tableColumn.dispose();
			tableViewerColumn = null;
		}
	}
	
	public IdMap getMap(){
		return owner.getMap();
	}
	
	public void disposeColumn(){
		setVisible(false);
	}
	
	public Column getColumn() {
		return column;
	}

	public TableViewerColumn getTableViewerColumn() {
		return tableViewerColumn;
	}

	public TableColumnLabelProvider getTableProvider(){
		return tableLabelProvider;
	}
	
	@Override
	public void controlMoved(ControlEvent event) {
	}

	@Override
	public void controlResized(ControlEvent event) {
		owner.onResizeColumn((TableColumn) event.getSource());
	}

	public TableColumn getTableColumn() {
		if(tableViewerColumn!=null){
			return  tableViewerColumn.getColumn();
		}
		return null;
	}

	public void onResizeColumn(TableColumn tableColumn){
		this.owner.onResizeColumn(tableColumn);
	}

	public void onVisibleColumn(Column columnConfig, boolean value) {
		this.owner.onVisibleColumn(columnConfig, value);
	}
	public ColumnViewerSorter getColumnSorter(){
		return columnViewerSorter;
	}

	public void onNewText(String value, int borderCell) {
		if(column.getWidth()==Column.AUTOWIDTH){
			if(value != null){
				if(oldTextWidth==null || !value.equals(oldTextWidth)){
					oldTextWidth = value;
		        	GC gc = new GC(this.owner);
		        	Point size = gc.textExtent(value);
		        	int newWidth = size.x+borderCell;
		        	if(column instanceof ColumnNotification){
		        		if(((ColumnNotification)column).setNewWidth(column.getWidth(), newWidth)){
		        			column.withWidth(newWidth);
		        		}
		        	}else if(newWidth>column.getWidth()){
		        		column.withWidth(newWidth);
		        	}
		        	tableViewerColumn.getColumn().setWidth(column.getWidth());
		        }		
			}
		}
	}

	public void setPosition(int x, int width) {
		this.width = width;
		this.x = x;
	}	
	public int getX(){
		return x;
	}
	public int getWidth(){
		return width;
	}
}
