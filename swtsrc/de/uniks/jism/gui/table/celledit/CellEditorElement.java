package de.uniks.jism.gui.table.celledit;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;

public interface CellEditorElement {
	public void defaultSelection(SelectionEvent event);
	public void keyRelease(KeyEvent event);
	public void onFocusLost();
	public Object getEditorValue();
}
