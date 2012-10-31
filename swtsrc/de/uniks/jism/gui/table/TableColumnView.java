package de.uniks.jism.gui.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

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
			tableViewerColumn.setEditingSupport(new TableCellEditingSupport(owner, tableViewer, column.getEditColumn(), column.getRegEx()));
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
}
