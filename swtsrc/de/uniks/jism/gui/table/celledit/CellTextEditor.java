package de.uniks.jism.gui.table.celledit;

import java.text.ParseException;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class CellTextEditor extends TextCellEditor implements CellEditorElement {
	protected EditField editField;
	
	public CellTextEditor(){
		super();
	}
	
	public CellTextEditor(Composite composite){
		super(composite);
	}
	
	public CellTextEditor(Composite composite, int style){
		super(composite, style);
	}
	
	public CellTextEditor(Composite parent, EditField field) {
		super(parent);
		this.editField = field;
		editField.setParent(this, parent);
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		if(editField!=null){
			try {
				return editField.getValue(true);
			} catch (ParseException e) {
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		if(editField!=null){
			editField.setValue(value);
		}
	}
	
	@Override
	public Control getControl() {
		if(editField!=null){
			return editField.getControl();
		}
		return null;
	}

	public boolean isActivated() {
		// Use the state of the visible style bit (getVisible()) rather than the
		// window's actual visibility (isVisible()) to get correct handling when
		// an ancestor control goes invisible, see bug 85331.
		if(editField!=null){
			return editField.isVisible();
		}
		return false;
	}

	/* (non-Javadoc)
     * Method declared on CellEditor.
     */
	@Override
    protected void doSetFocus() {
		if(editField!=null){
			editField.setFocus();
		}
    }

    /**
	 * Hides this cell editor's control. Does nothing if this cell editor is not
	 * visible.
	 */
	@Override
	public void deactivate() {
		if(editField!=null){
			editField.setVisible(false);
		}
	}

	/**
	 * Disposes of this cell editor and frees any associated SWT resources.
	 */
	@Override
	public void dispose() {
		if(editField!=null){
			editField.dispose();
		}
	}

	public void defaultSelection(SelectionEvent event){
		handleDefaultSelection(event);
	}
	public void keyRelease(KeyEvent event){
		keyReleaseOccured(event);
	}
	public void onFocusLost(){
		focusLost();
	}


	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		if(editField!=null){
			return editField.getValue(convert);
		}
		return null;
	}
}
