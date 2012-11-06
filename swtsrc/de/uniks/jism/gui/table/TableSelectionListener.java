package de.uniks.jism.gui.table;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

public class TableSelectionListener implements Listener{
	//FIXME
	private HashSet<Integer> selection=new HashSet<Integer>();
	private TableViewer tableviewer;
	
	public TableSelectionListener(TableViewer table){
		this.tableviewer=table;
	}
	@Override
	public void handleEvent(Event event) {
		if(event.type==SWT.Selection){
//			selection.add((TableItem) event.item);
			System.out.println(event.keyCode);
			selection.add(tableviewer.getTable().getSelectionIndex());
			int[] array=new int[selection.size()];
			int pos=0;
			for(int no : selection){
				array[pos++]=no;
			}
			System.out.println(array);
			Table table=tableviewer.getTable();
			ArrayList<Object> item=new ArrayList<Object>();
//			item.add(table.getItem(3).getData());
//			item.add(table.getItem(4).getData());
//			ISelection selection = new StructuredSelection(item);
			
//			tableviewer.setSelection(selection);
//			tableviewer.getTable().setSelection(array);
//			tableviewer.getTable().redraw();
//			table.getTable().getSelectionIndex();
//			table.setSelection(new ISelection() {
//				
//				@Override
//				public boolean isEmpty() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//			})setSelection(selection.toArray(new TableItem[selection.size()]));
		}
	}

}
