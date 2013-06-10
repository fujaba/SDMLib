package de.uniks.jism.gui.table.celledit;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
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

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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

import java.text.ParseException;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import de.uniks.jism.gui.table.Column;
import de.uniks.jism.gui.table.TableColumnView;
import de.uniks.jism.gui.table.TableComponent;

public class JISMCellEditor extends CellEditor implements CellEditorElement {
	protected EditField editField;
	protected TableComponent tableComponent;
	private Object element;
	private TableColumnView view;
	
	public JISMCellEditor(Composite parent, EditField field, TableComponent tableComponent) {
		super(parent);
		this.editField = field;
		this.tableComponent = tableComponent;
		
		editField.setParent(this, parent);
	}
	
	public JISMCellEditor withElement(Object element, TableColumnView view){
		this.element = element;
		this.view = view;
		return this;
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		if(editField!=null){
			try {
				return editField.getEditorValue(true);
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
			editField.setValue(value, true);
		}
	}
	
	@Override
	public Control getControl() {
		if(editField!=null){
			return editField.getControl();
		}
		return null;
	}
	
	public Column getColumn(){
		if(editField!=null){
			return editField.getColumn();
		}
		return null;
	}
	
	public Object getElement(){
		return element;
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

	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		if(editField!=null){
			return editField.getEditorValue(convert);
		}
		return null;
	}

	@Override
	public void focusLost(FocusEvent e) {
		deactivate();
		focusLost();
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyTraversed(TraverseEvent e) {
		if(e.keyCode==SWT.TAB){
			tableComponent.updatePosition();
			TableColumnView columnNext = tableComponent.getNextColumn(view);
			int column = tableComponent.getColumn(columnNext);
			if(columnNext != null){
				columnNext.getTableViewer().editElement(element, column);
			}
		}else if(e.keyCode==SWT.ESC){
			fireCancelEditor();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.keyCode==SWT.CR){
			fireApplyEditorValue();
			deactivate();
			return;
		}
		keyReleaseOccured(e);
	}
	

	/**
    * Handles a default selection event from the text control by applying the editor
    * value and deactivating this cell editor.
    * 
    * @param event the selection event
    * 
    * @since 3.0
    */
   public void handleDefaultSelection(SelectionEvent event) {
       // same with enter-key handling code in keyReleaseOccured(e);
       fireApplyEditorValue();
       deactivate();
   }
	@Override
	protected Control createControl(Composite parent) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#isCorrect(java.lang.Object)
	 */
	@Override
	protected boolean isCorrect(Object value) {
		try {
			return editField.isCorrect(value);
		}catch(Exception e) {
			setErrorMessage(e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	@Override
	public void cancelEditor() {
		fireCancelEditor();
	}

	@Override
	public void lostFocus() {
		focusLost();
	}

	@Override
	public void applyEditorValue() {
		fireApplyEditorValue();
	}

}
