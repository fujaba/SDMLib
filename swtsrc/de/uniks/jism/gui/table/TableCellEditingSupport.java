package de.uniks.jism.gui.table;

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
	private String editColumn = null;
	private String regEx;
	private TableComponent owner;

	public TableCellEditingSupport(ColumnViewer viewer) {
		super(viewer);
	}

	public TableCellEditingSupport(TableComponent owner,
			TableViewer tableViewer, String editColumn, String regEx) {
		super(tableViewer);
		this.editColumn = editColumn;
		this.regEx = regEx;
		this.owner = owner;
	}

	protected boolean canEdit(Object arg0) {
		return editColumn != null;
	}

	protected CellEditor getCellEditor(Object element) {
		return new TextCellEditor(((TableViewer) getViewer()).getTable());
	}

	protected Object getValue(Object element) {
		Object value = ((PeerMessage) element).get(editColumn);
		if (Column.DATE.equalsIgnoreCase(regEx)) {
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
		this.owner.setElementValue(msg, editColumn, msg.get(editColumn), value);
	}
}
