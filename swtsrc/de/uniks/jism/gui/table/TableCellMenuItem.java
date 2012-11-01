package de.uniks.jism.gui.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableColumn;

public class TableCellMenuItem implements Listener{
	private TableColumnView column;
	private MenuItem menuItem;
	private TableComponent owner;
	public TableCellMenuItem(Menu parent, TableColumnView column, TableComponent owner){
		this.column = column;
		this.owner=owner;
		menuItem=new MenuItem(parent, SWT.CHECK);
		
		Column columnConfig = column.getColumn();
		menuItem.setText(columnConfig.getLabel());
		menuItem.setSelection(columnConfig.isVisible());
		menuItem.addListener(SWT.Selection, this);
		
	}
	@Override
	public void handleEvent(Event event) {
		Column columnConfig = column.getColumn();
		TableColumn tableColumn = column.getTableViewerColumn().getColumn();
		if (menuItem.getSelection()) {
			tableColumn.setWidth(columnConfig.getWidth());
			tableColumn.setResizable(columnConfig.isResizable());
			columnConfig.setVisible(true);
		} else {
			columnConfig.setWidth(tableColumn.getWidth());

			tableColumn.setWidth(0);
			tableColumn.setResizable(false);
			columnConfig.setVisible(false);
		}
		owner.onResizeColumn(tableColumn);
	}
}
