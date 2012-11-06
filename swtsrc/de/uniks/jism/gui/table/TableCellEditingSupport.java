package de.uniks.jism.gui.table;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.sdmlib.serialization.interfaces.PeerMessage;

public class TableCellEditingSupport extends EditingSupport {
	
	private TableComponent owner;
	private Column column;

	public TableCellEditingSupport(ColumnViewer viewer) {
		super(viewer);
	}

	public TableCellEditingSupport(TableComponent owner,
			TableViewer tableViewer, Column column) {
		super(tableViewer);
		this.column=column;
		this.owner = owner;
	}

	protected boolean canEdit(Object arg0) {
		return column.getEditColumn() != null;
	}

	protected CellEditor getCellEditor(Object element) {
		if(column.getAttrName()!=null){
			Object value = ((PeerMessage)element).get(column.getAttrName());
			if(value instanceof String){
				try{
					Integer.valueOf((String)value);
					return new NumberCellEditor(((TableViewer) getViewer()).getTable(), "###", NumberCellEditor.FORMAT_INTEGER);
				}catch(Exception e){
					
				}
			}else if(value instanceof Integer){
				return new NumberCellEditor(((TableViewer) getViewer()).getTable(), "###", NumberCellEditor.FORMAT_INTEGER);
			}
		}
		return new TextCellEditor(((TableViewer) getViewer()).getTable());
	}

	protected Object getValue(Object element) {
		Object value = ((PeerMessage) element).get(column.getEditColumn());
		if (Column.DATE.equalsIgnoreCase(column.getRegEx())) {
			return getDateFormat((Long) value);
		}
		if(value==null){
			return "";
		}
		return value;
	}

	private String getDateFormat(long value) {
		if (value == 0) {
			return "";
		}
		DateFormat formatter = new SimpleDateFormat("dd.MM.yy - HH:mm:ss");
		return formatter.format(new Date(value));
	}

	protected void setValue(Object element, Object value) {
		PeerMessage msg = (PeerMessage) element;
		Object oldValue = msg.get(column.getEditColumn());
		msg.set(column.getEditColumn(), value);
		this.owner.propertyChange(new PropertyChangeEvent(msg, column.getEditColumn(), oldValue, value));
	}
}
