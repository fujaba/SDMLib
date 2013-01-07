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
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

import de.uniks.jism.gui.GUIPosition;
import de.uniks.jism.gui.SortingDirection;
import de.uniks.jism.gui.TableList;
import de.uniks.jism.gui.TableListCreator;
import de.uniks.jism.gui.layout.BorderLayout;

public class TableComponent extends Composite implements Listener,
		PropertyChangeListener {
	private ArrayList<TableColumnView> columns = new ArrayList<TableColumnView>();
	private Cursor defaultCursor = new Cursor(Display.getDefault(),
			SWT.CURSOR_ARROW);
	private Cursor handCursor = new Cursor(Display.getDefault(),
			SWT.CURSOR_HAND);
	private TableItem activeItem;

	private TableViewer tableViewer;
	private TableViewer fixedTableViewerLeft;

	private boolean isToolTip;
	private Composite tableComposite;
	private TableSyncronizer tableSyncronizer;
	private String property;
	private Menu mnuColumns;

	protected TableList list;
	protected Object source;
	protected SendableEntityCreator sourceCreator;
	protected int additionKey;
	protected IdMap map;
	protected TableFilterView tableFilterView;
	private Menu headerMenu;

	public IdMap getMap()
   {
      return map;
   }
	
	public TableComponent(Composite parent, int style, IdMap map) {
		super(parent, style);

		if (map != null) {
			this.map = map;
			this.map.addCreator(new TableListCreator());
		}

		createContent();
	}

	public TableComponent(Composite parent, int style) {
		super(parent, style);
	}

	public void createContent() {
		tableComposite = new Composite(this, SWT.NONE | SWT.FILL);
		tableComposite.setLayoutData(GUIPosition.CENTER);
		tableComposite.setLayout(new BorderLayout(0, 0));

		this.list = new TableList();
		this.list.addPropertyChangeListener(this);
		this.list.setIdMap(map);

		tableFilterView = new TableFilterView(this, map);

		headerMenu = new Menu(getShell(), SWT.POP_UP);
		MenuItem columnsMenue = new MenuItem(headerMenu, SWT.CASCADE);
		columnsMenue.setText("Columns");
		mnuColumns = new Menu(getShell(), SWT.DROP_DOWN);
		columnsMenue.setMenu(mnuColumns);

		tableViewer = createBrowser(GUIPosition.CENTER);

		setLayout(new BorderLayout(0, 0));
	}

	protected TableViewer createBrowser(GUIPosition browserId) {
		int flags = SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION;
		if (!browserId.equals(GUIPosition.CENTER)) {
			flags = flags | SWT.NO_SCROLL | SWT.FILL;
		}
		TableViewer tableViewer = new TableViewer(tableComposite, flags);
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setFilters(new ViewerFilter[] { tableFilterView });

		Table table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setMenu(headerMenu);

		table.addListener(SWT.KeyDown, this);
		table.addListener(SWT.MouseMove, this);
		table.addListener(SWT.MouseUp, this);
		table.addListener(SWT.MouseExit, this);
		table.addListener(SWT.SELECTED, this);

		table.setLayoutData(browserId);

		return tableViewer;
	}

	public void addColumn(Column column) {
		if (column.getBrowserId().equals(GUIPosition.WEST)) {
			setVisibleFixedColumns(true);
		}
		this.columns.add(new TableColumnView(this, column, mnuColumns, map));
		if (column.getAltAttribute() != null) {
			if (!isToolTip) {
				isToolTip = true;
				if (fixedTableViewerLeft != null) {
					ColumnViewerToolTipSupport.enableFor(fixedTableViewerLeft,
							ToolTip.NO_RECREATE);
				}
			}
		}
	}

	public void removeColumn(Column column) {
		TableColumnView[] array = this.columns
				.toArray(new TableColumnView[this.columns.size()]);
		for (TableColumnView item : array) {
			if (item.getColumn().equals(column)) {
				removeColumn(item);
			}
		}
	}

	public boolean setCounterField(Column column) {
		return tableFilterView.setCounterField(column);
	}

	public void clearColumns(GUIPosition browser) {
		TableColumnView[] array = this.columns
				.toArray(new TableColumnView[this.columns.size()]);

		for (TableColumnView item : array) {
			if (browser.equals(GUIPosition.ALL)
					|| browser.equals(item.getColumn().getBrowserId())) {
				removeColumn(item);
			}
		}
		refresh();
	}

	public TableColumnView getColumn(Column column) {
		if (column != null) {
			for (Iterator<TableColumnView> i = this.columns.iterator(); i
					.hasNext();) {
				TableColumnView item = i.next();
				if (item.getColumn().equals(column)) {
					return item;
				}
			}
		}
		return null;
	}

	public void removeColumn(TableColumnView column) {
		if (this.columns.remove(column)) {
			column.disposeColumn();
			onResizeColumn(column);
		}
	}

	public TableViewer getBrowserView(GUIPosition browserId) {
		if (browserId.equals(GUIPosition.WEST)) {
			return fixedTableViewerLeft;
		} else {
			return tableViewer;
		}
	}

	public void setVisibleFixedColumns(boolean visible) {
		if (fixedTableViewerLeft != null && !visible) {
			for (TableColumnView item : columns) {
				if (item.getColumn().getBrowserId().equals(GUIPosition.WEST)) {
					item.setVisible(false);
				}
			}

			fixedTableViewerLeft.getTable().dispose();
			fixedTableViewerLeft = null;
		} else if (fixedTableViewerLeft == null && visible) {
			fixedTableViewerLeft = createBrowser(GUIPosition.WEST);

			tableSyncronizer = new TableSyncronizer(this,
					fixedTableViewerLeft.getTable(), tableViewer.getTable());
			tableViewer.getTable().addMouseWheelListener(tableSyncronizer);
			tableViewer.getTable().addListener(SWT.Selection, tableSyncronizer);
			if (tableViewer.getTable().getVerticalBar() != null) {
				tableViewer.getTable().getVerticalBar()
						.addListener(SWT.Selection, tableSyncronizer);
			}

			for (TableColumnView item : columns) {
				if (item.getColumn().getBrowserId().equals(GUIPosition.WEST)) {
					item.setVisible(true);
				}
			}
		}
	}

	public void createFromCreator(SendableEntityCreator creator) {
		createFromCreator(creator, false);

	}

	public void createFromCreator(SendableEntityCreator creator, boolean edit) {
		String[] properties = creator.getProperties();
		Object prototyp = creator.getSendableInstance(true);
		for (String property : properties) {
			Object value = creator.getValue(prototyp, property);
			if (!(value instanceof Collection<?>)) {
				addColumn(new Column(property, edit, true));
			}
		}
	}

	public void addControl(Control control) {
		control.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				3, 1));
	}

	public void refresh(Object object) {
		ArrayList<String> refreshColumns = new ArrayList<String>();
		for (TableColumnView tableColumnView : columns) {
			refreshColumns.add(tableColumnView.getColumn().getAttrName());
		}
		refresh(object,
				refreshColumns.toArray(new String[refreshColumns.size()]));
	}

	public void refresh(Object object, String[] columns) {
		if (fixedTableViewerLeft != null) {
			fixedTableViewerLeft.update(object, columns);
		}
		if (tableViewer != null) {
			tableViewer.update(object, columns);
		}

	}

	public void addItem(Object item) {
		if (!list.contains(item)) {
			if (tableFilterView.matchesSearchCriteria(item)) {
				list.add(item);
				tableFilterView.refreshCounter();
			}
		}
	}

	public void removeItem(Object item) {
		if (list.contains(item)) {
			list.set(property + IdMap.REMOVE, item);
			sourceCreator.setValue(source, property, item, IdMap.REMOVE);
			tableFilterView.refreshCounter();
		}
	}

	public void refresh() {
		if (fixedTableViewerLeft != null) {
			fixedTableViewerLeft.refresh();
		}
		tableViewer.refresh();
		this.tableFilterView.refreshCounter();
	}

	public void refreshViewer() {
		if (fixedTableViewerLeft != null) {
			fixedTableViewerLeft.refresh();
		}
		tableViewer.refresh();

	}

	public boolean finishDataBinding(TableList item) {
		return finishDataBinding(item, TableList.PROPERTY_ITEMS);
	}

	public boolean finishDataBinding(Object item, String property) {

		this.source = item;
		this.sourceCreator = map.getCreatorClass(source);
		this.property = property;

		// Copy all Elements to TableList
		Collection<?> collection = (Collection<?>) sourceCreator.getValue(item,
				property);
		if(collection!=null){
			for (Iterator<?> i = collection.iterator(); i.hasNext();) {
				addItem(i.next());
			}
		}
		addUpdateListener(source);
		return true;
	}

	public void setSearchProperties(String... searchProperties) {
		tableFilterView.setSearchProperties(searchProperties);
		tableFilterView.refresh();
	}

	public void addUpdateListener(Object list) {
		new UpdateSearchList(this, list);
	}

	public String getProperty() {
		return property;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt != null) {
			if (list.equals(evt.getSource())) {
				if (evt.getPropertyName().equals(TableList.PROPERTY_ITEMS)) {
					if (evt.getOldValue() == null && evt.getNewValue() != null) {
						// ADD a new Item
						if (fixedTableViewerLeft != null) {
							fixedTableViewerLeft.add(evt.getNewValue());
						}
						if (tableViewer != null) {
							tableViewer.add(evt.getNewValue());
						}
					} else if (evt.getOldValue() != null
							&& evt.getNewValue() == null) {
						if (fixedTableViewerLeft != null && !fixedTableViewerLeft.getTable().isDisposed()) {
							fixedTableViewerLeft.remove(evt.getOldValue());
						}
						if (tableViewer != null && !tableViewer.getTable().isDisposed()) {
							tableViewer.remove(evt.getOldValue());
						}
					}
				}
			} else if (source.equals(evt.getSource())) {
				if (evt.getPropertyName().equals(property)) {
					if (evt.getOldValue() != null && evt.getNewValue() == null) {
						list.set(property + IdMap.REMOVE, evt.getOldValue());

					}
				}
			} else {
				// Must be an update
				refresh(evt.getSource(), new String[] { evt.getPropertyName() });
			}
		}
	}

	protected void onSelection(Table table, TableItem[] tableItems) {
		
	}
	

	@Override
	public void handleEvent(Event event) {
		Point pt = new Point(event.x, event.y);

		TableItem currentItem = tableViewer.getTable().getItem(pt);
		if (SWT.MouseMove == event.type | SWT.MouseUp == event.type
				| SWT.MouseExit == event.type) {
			if (SWT.MouseUp == event.type) {
				ViewerCell cell = getTableViewer().getCell(pt);
				if (cell != null) {
					int columnIndex = cell.getColumnIndex();
					int offset = 0;
					if (fixedTableViewerLeft != null) {
						offset = fixedTableViewerLeft.getTable()
								.getColumnCount();
					}
					if (columnIndex + offset >= columns.size()) {
						return;
					}
					TableColumnView tableColumnView = columns.get(columnIndex
							+ offset);
					if (tableColumnView != null) {
						Composite parent = this;
						int x = event.x;
						int y = event.y;
						while (parent != null) {
							x += parent.getBounds().x;
							y += parent.getBounds().y;
							parent = parent.getParent();
						}
						Column column = tableColumnView.getColumn();
						if (column instanceof ColumnNotification) {
							((ColumnNotification) column).setSelection(this,
									currentItem, x, y);
						} else {
							column.setSelection(x, y);
						}

					}
					// setSelection(currentItem, cell, columnIndex);
				}else{
					// Deselect All
					selectNone();
					onSelection(getTable(), getTable().getSelection());
				}
			}
			if (currentItem == null || currentItem.isDisposed()) {
				tableViewer.getTable().setCursor(defaultCursor);
			} else {
				boolean activ = false;
				for (int i = 0; i < columns.size(); i++) {
					TableColumnView tableViewerColumn = columns.get(i);
					Rectangle positem = currentItem.getBounds(i);
					if (positem != null) {
						if (event.x > positem.x
								&& event.x < (positem.x + positem.width)) {
							if (tableViewerColumn.getColumn()
									.isEditingSupport()) {
								this.activeItem = currentItem;
								activ = true;
								TableColumnLabelProvider activeCell = tableViewerColumn
										.getTableProvider();
								if (activeCell != null) {
									Color color = activeCell
											.getForgroundColorActiv();
									if (color != null) {
										this.activeItem.setForeground(i, color);
									}
									color = activeCell
											.getBackgroundColorActiv();
									if (color != null) {
										this.activeItem.setBackground(i, color);
									}
								}
								tableViewer.getTable().setCursor(handCursor);
							}
						}
					}
				}
				if (!activ) {
					tableViewer.getTable().setCursor(defaultCursor);
				}
			}
		} else if (SWT.KeyDown == event.type) {
			if (event.keyCode == SWT.CTRL
					|| (event.stateMask & SWT.CONTROL) != 0) {
				this.additionKey = SWT.CTRL;
				if (event.keyCode == 'a') {
					// Select all
					TableComponent.this.selectAll();
				}
			} else if (event.keyCode == SWT.SHIFT
					|| (event.stateMask & SWT.SHIFT) != 0) {
				this.additionKey = SWT.ALT;
			} else {
				this.additionKey = 0;
			}
		} else if(SWT.SELECTED == event.type){
			// Notifiy Selection
			Table table = (Table) event.widget;
			onSelection(table, table.getSelection());
		}
	}

	public void selectNone(){
		tableViewer.getTable().setSelection(new int[0]);
	}
	
	public void selectAll() {
		int count = tableViewer.getTable().getItemCount();
		int[] array = new int[count];
		for (int i = 0; i < count; i++) {
			array[i] = i;
		}
		tableViewer.getTable().setSelection(array);
	}

	public int getTableItemCount() {
		return tableViewer.getTable().getItemCount();
	}

	public Table getTable() {
		return tableViewer.getTable();
	}
	
	public ArrayList<Object> getSelectionItems() {
		ArrayList<Object> selecteditems=new ArrayList<Object>();
		for(TableItem item : getTable().getSelection()){
			if(item.getData() != null){
				selecteditems.add(item.getData());
			}
		}
		return selecteditems;
	}


	public void onResizeColumn(TableColumn column) {
		for (TableColumnView item : columns) {
			if (item.getTableColumn() == column) {
				onResizeColumn(item);
			}
		}
	}

	public void onResizeColumn(TableColumnView item) {
		if (item.getColumn().getBrowserId().equals(GUIPosition.WEST)) {
			int size = 0;
			for (TableColumnView view : columns) {
				if (view.getColumn().getBrowserId().equals(GUIPosition.WEST)) {
					if (view.getColumn().isVisible()) {
						size += view.getColumn().getWidth();
					}
				}
			}
			if (size == 0) {
				setVisibleFixedColumns(false);
			} else {
				setVisibleFixedColumns(true);
			}
			tableComposite.layout();
		}
	}

	public void onVisibleColumn(Column column, boolean value) {
		if (column.getBrowserId().equals(GUIPosition.WEST)) {
			if (value) {
				setVisibleFixedColumns(true);
			}
		}
	}

	public void removeSelectionItems() {
		TableItem[] selectionItems = getTable().getSelection();

		if (selectionItems.length > 0) {
			for (TableItem tableItem : selectionItems) {
				removeItem((Object) tableItem.getData());
			}
		}
	}

	public TableList getList() {
		return list;
	}

	public IdMap getIdMap() {
		return map;
	}

	public void setSorting(String column, SortingDirection direction) {
		for (TableColumnView columnView : columns) {
			String value = columnView.getColumn().getAttrName();
			if (value == null) {
				value = columnView.getColumn().getCellValue();
			}
			if (value == null) {
				value = columnView.getColumn().getLabel();
			}
			if (value != null) {
				if (value.equalsIgnoreCase(column)) {
					list.setSort(value, direction);
					setSorting(columnView.getTableColumn(), direction,
							columnView.getColumnSorter());
					break;
				}
			}
		}
	}

	public void setSorting(TableColumn column, SortingDirection direction,
			ColumnViewerSorter sorter) {
		
		if (fixedTableViewerLeft != null) {
			setSorting(column, direction, fixedTableViewerLeft, sorter);
		}
		setSorting(column, direction, tableViewer, sorter);
	}

	private void setSorting(TableColumn column, SortingDirection direction,
			TableViewer viewer, ColumnViewerSorter sorter) {
		Table table = viewer.getTable();
		if (column.getParent() == table) {
			table.setSortColumn(column);
			if (direction == SortingDirection.ASC) {
				table.setSortDirection(SWT.UP);
			} else {
				table.setSortDirection(SWT.DOWN);
			}
			
		} else {
			table.setSortColumn(null);
			table.setSortDirection(SWT.NONE);
		}
		if (viewer.getComparator() != sorter) {
			viewer.setComparator(sorter);
		}
		refresh();
	}
	public void clear() {
		this.list.clear();
	}
}
