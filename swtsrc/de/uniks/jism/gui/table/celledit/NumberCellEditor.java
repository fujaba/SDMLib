package de.uniks.jism.gui.table.celledit;
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
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class NumberCellEditor extends TextCellEditor implements CellEditorElement {
	protected EditField editField;
	
	public NumberCellEditor(String numberFormat, int format) {
		super();
		init(numberFormat, format);
	}

	public NumberCellEditor(Composite parent, String numberFormat, int format) {
		super(parent);
		init(numberFormat, format);
	}

	public NumberCellEditor(Composite parent, int style, String numberFormat, int format) {
		super(parent, style);
		init(numberFormat, format);
	}
	
	public void init(String numberFormat, int format) {
		this.editField.createControl(numberFormat, format);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		return editField.getText();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		editField.doSetValue(value);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#isCorrect(java.lang.Object)
	 */
	@Override
	protected boolean isCorrect(Object value) {
		try{
			if(value instanceof Number) return super.isCorrect((Number)value);
			Object o = editField.convertFromString((String)value);
			return super.isCorrect(o);
		}catch(Exception e) {
			setErrorMessage(e.getLocalizedMessage());
			return false;
		}
	}
	
	protected Control createControl(Composite parent) {
		Text text=(Text) super.createControl(parent);
		editField=new EditField(this, parent);
		editField.setEditField(text);
        return text;
    }
	
	@Override
	public Control getControl() {
		return editField.getControl();
	}
	public boolean isActivated() {
		// Use the state of the visible style bit (getVisible()) rather than the
		// window's actual visibility (isVisible()) to get correct handling when
		// an ancestor control goes invisible, see bug 85331.
		return editField.isVisible();
	}
	  /* (non-Javadoc)
     * Method declared on CellEditor.
     */
	@Override
    protected void doSetFocus() {
		editField.setFocus();
    }
    /**
	 * Hides this cell editor's control. Does nothing if this cell editor is not
	 * visible.
	 */
	@Override
	public void deactivate() {
		editField.setVisible(false);
	}

	/**
	 * Disposes of this cell editor and frees any associated SWT resources.
	 */
	@Override
	public void dispose() {
		editField.dispose();
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
	public Object getEditorValue() {
		return getValue();
	}
}
