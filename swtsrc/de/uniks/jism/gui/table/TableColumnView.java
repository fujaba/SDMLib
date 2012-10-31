package de.uniks.jism.gui.table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.sdmlib.serialization.interfaces.PeerMessage;

public class TableColumnView {
	protected TableViewer tableViewer;
	private TableViewerColumn tableViewerColumn;
	private Column column;
	private TableLabelProvider tableLabelProvider;
	private TableViewer viewer;
	private TableComponent owner;

	public TableColumnView(TableComponent tableComponent,TableViewer tableViewer, Column column) {
		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		this.viewer=tableViewer;
		this.owner=tableComponent;

		TableColumn tblclmnColumn = tableViewerColumn.getColumn();
		tblclmnColumn.setMoveable(true);
		new ColumnViewerSorter(tableViewer, tableViewerColumn, column.getAttrName());
		tblclmnColumn.setWidth(column.getWidth());
		tblclmnColumn.setText(column.getLabel());
		tblclmnColumn.setResizable(column.isResizable());
		

		// RegExpression
		if(column.getEditColumn()!=null){
			tableViewerColumn.setEditingSupport(new EditListener(owner, tableViewer, column.getEditColumn(), column.getRegEx()));
		}else if(column.getEditingSupport()!=null){
			tableViewerColumn.setEditingSupport(column.getEditingSupport());
		}

		if(column.getTextalignment()!=-1){
			tblclmnColumn.setAlignment(column.getTextalignment());
		}
		this.column=column;
	}

	public Column getColumn() {
		return column;
	}

	public TableViewerColumn getTableViewerColumn() {
		return tableViewerColumn;
	}

	public void addLabelProvider(){
		if(column.getCellValue()!=null){
			this.tableLabelProvider=new TableLabelProvider(column);
			tableViewerColumn.setLabelProvider(tableLabelProvider);
		}
	}
	public TableLabelProvider getTableProvider(){
		return tableLabelProvider;
	}
	
	public TableViewer getViewer() {
		return viewer;
	}

	public void setViewer(TableViewer viewer) {
		this.viewer = viewer;
	}

	class EditListener extends EditingSupport{
		private String editColumn=null;
		private String regEx;
		private TableComponent owner;

		public EditListener(TableComponent owner, TableViewer tableViewer, String editColumn, String regEx){
			super(tableViewer);
			this.editColumn=editColumn;
			this.regEx=regEx;
			this.owner=owner;
		}

		protected boolean canEdit(Object arg0) {
			return editColumn!=null;
		}

		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(((TableViewer)getViewer()).getTable());
		}
		protected Object getValue(Object element) 
		{
			Object value=((PeerMessage)element).get(editColumn);
			if(Column.DATE.equalsIgnoreCase(regEx)){
				return getDateFormat((Long) value);
			}
			return value;
		}
		private String getDateFormat(long value){
			if(value==0){
				return "";
			}
			DateFormat formatter = new SimpleDateFormat("dd.MM.yy - HH:mm:ss");
			return formatter.format(new Date(value));
		}
		protected void setValue(Object element, Object value) 
		{
			PeerMessage msg=(PeerMessage) element;
			this.owner.setElementValue(msg, editColumn, msg.get(editColumn), value);
		}
	}
}
