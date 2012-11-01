package de.uniks.jism.gui.table;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.TableColumn;

public class TableColumnView implements ControlListener {
	protected TableViewer tableViewer;
	private TableViewerColumn tableViewerColumn;
	private Column column;
	private TableLabelProvider tableLabelProvider;
	private TableViewer viewer;
	private TableComponent owner;
	private TableColumn tableColumn;

	public TableColumnView(TableComponent tableComponent,TableViewer tableViewer, Column column) {
		tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		this.viewer=tableViewer;
		this.owner=tableComponent;

		tableColumn = tableViewerColumn.getColumn();
		tableColumn.setMoveable(true);
		new ColumnViewerSorter(tableViewer, tableViewerColumn, column.getAttrName());
		tableColumn.setWidth(column.getWidth());
		tableColumn.setText(column.getLabel());
		tableColumn.setResizable(column.isResizable());
		tableColumn.addControlListener(this);

		// RegExpression
		if(column.getEditColumn()!=null){
			tableViewerColumn.setEditingSupport(new TableCellEditingSupport(owner, tableViewer, column.getEditColumn(), column.getRegEx()));
		}else if(column.getEditingSupport()!=null){
			tableViewerColumn.setEditingSupport(column.getEditingSupport());
		}

		if(column.getTextalignment()!=-1){
			tableColumn.setAlignment(column.getTextalignment());
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

	@Override
	public void controlMoved(ControlEvent event) {
		
	}

	@Override
	public void controlResized(ControlEvent event) {
		owner.onResizeColumn((TableColumn) event.getSource());
	}

	public TableColumn getTableColumn() {
		return tableColumn;
	}
}
