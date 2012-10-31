package de.uniks.jism.gui.table;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableColumn;

public class MenueItem implements Listener{
	private MenuItem itemName;
	private TableColumnView column;
	public MenueItem(MenuItem itemName, TableColumnView column){
		this.itemName = itemName;
		this.column = column;
		
	}
	@Override
	public void handleEvent(Event arg0) {
		Column columnConfig = column.getColumn();
		TableColumn tableColumn = column.getTableViewerColumn().getColumn();
		if (itemName.getSelection()) {
			tableColumn.setWidth(columnConfig.getWidth());
			tableColumn.setResizable(columnConfig.isResizable());
			columnConfig.setVisible(true);
		} else {
			columnConfig.setWidth(tableColumn.getWidth());

			tableColumn.setWidth(0);
			tableColumn.setResizable(false);
			columnConfig.setVisible(false);
		}		
	}

}
