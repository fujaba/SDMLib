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

import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Table;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import de.uniks.jism.gui.table.celledit.CellEditorElement;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.JISMCellEditor;

public class TableCellEditingSupport extends EditingSupport {
	protected TableComponent owner;
	protected Column column;
	protected TableColumnView tableColumn;

	public TableCellEditingSupport(TableComponent owner,
			TableViewer tableViewer, Column column, TableColumnView tableColumn) {
		super(tableViewer);
		this.column=column;
		this.owner = owner;
		this.tableColumn = tableColumn;
	}

	public TableCellEditingSupport(TableComponent owner, Column column) {
		super(owner.getBrowserView(column.getBrowserId()));
		this.column=column;
		this.owner = owner;
	}

	protected boolean canEdit(Object arg0) {
		return column.getEditColumn() != null;
	}
	
	@Override
	protected void saveCellEditorValue(CellEditor cellEditor, ViewerCell cell) {
		Object value = null;
		if(cellEditor instanceof CellEditorElement){
			try {
				value=((CellEditorElement)cellEditor).getEditorValue(true);
				setValue(cell.getElement(), value);
			} catch (ParseException e) {
			}
		}else{
			value = cellEditor.getValue();
		}
		if(value!=null){
			setValue(cell.getElement(), value);
		}
	}

	protected CellEditor getCellEditor(Object element) {
		IdMap map = owner.getIdMap();
		EditField editField=new EditField().init(element, map, column);
		Table table = ((TableViewer) getViewer()).getTable();
		JISMCellEditor editor = new JISMCellEditor(table, editField, owner).withElement(element, tableColumn);
		
		owner.notifiyCellEditor(editor);
		return editor;
	}
	
	protected Object getValue(Object element) {
		Object value=null;
		IdMap map = owner.getIdMap();
		SendableEntityCreator creatorClass = map.getCreatorClass(element);
		if(creatorClass!=null){
			value = creatorClass.getValue(element, column.getAttrName());
		}

		if (Column.DATE.equalsIgnoreCase(column.getNumberFormat())) {
			return getDateFormat((Long) value);
		}
		if(value==null){
			return "";
		}
		return value;
	}

	protected String getDateFormat(long value) {
		if (value == 0) {
			return "";
		}
		DateFormat formatter = new SimpleDateFormat("dd.MM.yy - HH:mm:ss");
		return formatter.format(new Date(value));
	}

	protected void setValue(Object element, Object value) {
		IdMap map = owner.getIdMap();
		SendableEntityCreator creatorClass = map.getCreatorClass(element);
		if(creatorClass!=null){
			Object oldValue = creatorClass.getValue(element, column.getEditColumn());
			creatorClass.setValue(element, column.getEditColumn(), value, IdMap.NEW);
			this.owner.propertyChange(new PropertyChangeEvent(element, column.getEditColumn(), oldValue, value));
		}
	}
}
