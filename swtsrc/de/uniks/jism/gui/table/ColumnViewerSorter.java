package de.uniks.jism.gui.table;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.sdmlib.serialization.interfaces.PeerMessage;

public class ColumnViewerSorter extends ViewerComparator {
	public static final int ASC = 1;
	public static final int NONE = 0;
	public static final int DESC = -1;
	private int direction = 0;
	private TableViewerColumn column;
	private ColumnViewer viewer;
	private String columnName;

	public ColumnViewerSorter(TableViewer viewer, TableViewerColumn column, String columnName){
		super();
		this.columnName=columnName;
		this.column = column;
		this.viewer = viewer;
		this.column.getColumn().addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (ColumnViewerSorter.this.viewer.getComparator() != null) {
					if (ColumnViewerSorter.this.viewer.getComparator() == ColumnViewerSorter.this) {
						int tdirection = ColumnViewerSorter.this.direction;
						if (tdirection == ASC) {
							setSorter(ColumnViewerSorter.this, DESC);
						} else if (tdirection == DESC) {
							setSorter(ColumnViewerSorter.this, NONE);
						}
					} else {
						setSorter(ColumnViewerSorter.this, ASC);
					}
				} else {
					setSorter(ColumnViewerSorter.this, ASC);
				}
			}
		});
	}

	public void setSorter(ColumnViewerSorter sorter, int direction) {
		if (direction == NONE) {
			column.getColumn().getParent().setSortColumn(null);
			column.getColumn().getParent().setSortDirection(SWT.NONE);
			viewer.setComparator(null);
		} else {
			column.getColumn().getParent().setSortColumn(column.getColumn());
			sorter.direction = direction;

			if (direction == ASC) {
				column.getColumn().getParent().setSortDirection(SWT.DOWN);
			} else {
				column.getColumn().getParent().setSortDirection(SWT.UP);
			}

			if (viewer.getComparator() == sorter) {
				viewer.refresh();
			} else {
				viewer.setComparator(sorter);
			}
		}
	}
	public int compare(Viewer viewer, Object e1, Object e2) {
		return direction * doCompare(viewer, e1, e2);
	}
	public void setInitSort(TableColumn column, int direction){
		this.column.getColumn().getParent().setSortColumn(column);
		this.column.getColumn().getParent().setSortDirection(direction);
	}
	public void setDirection(int direction){
		this.direction=direction;
		if (direction == ASC) {
			column.getColumn().getParent().setSortDirection(SWT.DOWN);
		} else {
			column.getColumn().getParent().setSortDirection(SWT.UP);
		}
	}
			
	protected int doCompare(Viewer viewer, Object e1, Object e2) {
		PeerMessage p1 = (PeerMessage) e1;
		PeerMessage p2 = (PeerMessage) e2;
		if(p1.get(columnName) instanceof String){
			String valueA=(String) p1.get(columnName);
			String valueB=(String) p2.get(columnName);
			if(valueA!=null){
				return valueA.compareToIgnoreCase(valueB);
			}else{
				return -1;
			}
		}else if(p1.get(columnName) instanceof Integer){
			Integer valueA=(Integer) p1.get(columnName);
			Integer valueB=(Integer) p2.get(columnName);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}
		}else if(p1.get(columnName) instanceof Long){
			Long valueA=(Long) p1.get(columnName);
			Long valueB=(Long) p2.get(columnName);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}		}else if(p1.get(columnName) instanceof Boolean){
			Boolean valueA=(Boolean) p1.get(columnName);
			Boolean valueB=(Boolean) p2.get(columnName);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}
		}
		return 0;	
	}
}
