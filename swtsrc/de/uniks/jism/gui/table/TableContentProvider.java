package de.uniks.jism.gui.table;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.uniks.jism.gui.TableList;

public class TableContentProvider implements IStructuredContentProvider{

	private TableList list;

	public TableContentProvider(TableList list) {
		this.list=list;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return list.toArray(new Object[list.size()]);
	}
}
