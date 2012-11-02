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
	private TableColumnView view;
	private Column column;

	public ColumnViewerSorter(TableColumnView tableView, Column column){
		super();
		this.column =column;
		this.view = tableView;
		TableViewerColumn tableViewerColumn = tableView.getTableViewerColumn();
		tableViewerColumn.getColumn().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refreshDirection();
			}
		});
	}

	public void refreshDirection(){
		TableViewerColumn tableViewerColumn = view.getTableViewerColumn();
		if (tableViewerColumn.getViewer().getComparator() != null) {
			if (tableViewerColumn.getViewer().getComparator() == ColumnViewerSorter.this) {
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
	public void setSorter(ColumnViewerSorter sorter, int direction) {
		TableViewerColumn tableViewerColumn = view.getTableViewerColumn();
		TableColumn column = tableViewerColumn.getColumn();
		if (direction == NONE) {
			column.getParent().setSortColumn(null);
			column.getParent().setSortDirection(SWT.NONE);
			tableViewerColumn.getViewer().setComparator(null);
		} else {
			column.getParent().setSortColumn(column);
			sorter.direction = direction;

			if (direction == ASC) {
				column.getParent().setSortDirection(SWT.DOWN);
			} else {
				column.getParent().setSortDirection(SWT.UP);
			}

			if (tableViewerColumn.getViewer().getComparator() == sorter) {
				tableViewerColumn.getViewer().refresh();
			} else {
				tableViewerColumn.getViewer().setComparator(sorter);
			}
		}
	}
	
	public int compare(Viewer viewer, Object e1, Object e2) {
		return direction * doCompare(viewer, e1, e2);
	}
	
	public void setInitSort(TableColumn column, int direction){
		TableViewerColumn tableViewerColumn = view.getTableViewerColumn();
		TableColumn newColumn = tableViewerColumn.getColumn();
		newColumn.getParent().setSortColumn(column);
		newColumn.getParent().setSortDirection(direction);
	}
	public void setDirection(int direction){
		TableViewerColumn tableViewerColumn = view.getTableViewerColumn();
		TableColumn newColumn = tableViewerColumn.getColumn();

		this.direction=direction;
		if (direction == ASC) {
			newColumn.getParent().setSortDirection(SWT.DOWN);
		} else {
			newColumn.getParent().setSortDirection(SWT.UP);
		}
	}
			
	protected int doCompare(Viewer viewer, Object e1, Object e2) {
		PeerMessage p1 = (PeerMessage) e1;
		PeerMessage p2 = (PeerMessage) e2;
		String attrName = column.getAttrName();
		if(p1.get(attrName) instanceof String){
			String valueA=(String) p1.get(attrName);
			String valueB=(String) p2.get(attrName);
			if(valueA!=null){
				return valueA.compareToIgnoreCase(valueB);
			}else{
				return -1;
			}
		}else if(p1.get(attrName) instanceof Integer){
			Integer valueA=(Integer) p1.get(attrName);
			Integer valueB=(Integer) p2.get(attrName);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}
		}else if(p1.get(attrName) instanceof Long){
			Long valueA=(Long) p1.get(attrName);
			Long valueB=(Long) p2.get(attrName);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}		}else if(p1.get(attrName) instanceof Boolean){
			Boolean valueA=(Boolean) p1.get(attrName);
			Boolean valueB=(Boolean) p2.get(attrName);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}
		}
		return 0;	
	}
}
