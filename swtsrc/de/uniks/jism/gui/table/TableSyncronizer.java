package de.uniks.jism.gui.table;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

public class TableSyncronizer implements MouseWheelListener, Listener{
	private Table left;
	private Table right;

	public TableSyncronizer(TableComponent owner, Table left, Table right){
		this.left = left;
		this.right = right;
	}
	
	public void mouseScrolled(MouseEvent arg0) {
		left.setTopIndex(right.getTopIndex());
		right.setTopIndex(left.getTopIndex());
	}

	@Override
	public void handleEvent(Event event) {
		left.setSelection(right.getSelectionIndices());
		left.setTopIndex(right.getTopIndex());
		right.setTopIndex(left.getTopIndex());
	}
}
