package de.uniks.jism.gui.table.controls;

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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import de.uniks.jism.gui.table.celledit.CellEditorElement;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public abstract class EditControl<T extends Control> implements CellEditorElement {
	protected T control;
	protected EditFields fieldTyp;
	protected CellEditorElement cellOwner;

	public T getControl() {
		if (control != null && !control.isDisposed()) {
			return control;
		}
		return null;
	}
	
	public void setVisible(boolean value){
		 T control=getControl();
		 if(control!=null){
			 control.setVisible(value);
		 }
	}
	
	public boolean isVisible(){
		 Control control=getControl();
		 if(control!=null){
			 return control.getVisible();
		 }
		return false;
	}
	
	public boolean setFocus(){
		 Control control=getControl();
		 if(control!=null){
			 return control.setFocus();
		 }
		 return false;
	}
	public boolean isActive(){
		if (control != null && !control.isDisposed()) {
			return true;
		}
		return false;
	}
	
	public void dispose(){
		if(isActive()){
			control.dispose();
		}
		control=null;
	}
	
	public EditFields getFieldTyp(){
		return fieldTyp;
	}
	
	public void registerListener(CellEditorElement owner) {
		if(owner!=null){
    	   control.addFocusListener(owner);
    	   control.addKeyListener(owner);
    	   control.addTraverseListener(owner);
       }		
	}
	
	public void setFormatFromParent(Composite parent) {
		control.setFont(parent.getFont());
		control.setBackground(parent.getBackground());		
	}
	
	@Override
	public void cancelEditor() {
		if(cellOwner != null){
			cellOwner.cancelEditor();
		}
	}

	@Override
	public void applyEditorValue() {
		if(cellOwner != null){
			cellOwner.applyEditorValue();
		}
	}

	
	@Override
	public void lostFocus() {
		if(cellOwner != null){
			cellOwner.lostFocus();
		}
	}
	
	public void addChoiceList(Object value){
		
	}
	public abstract void createControl(EditField owner, Composite parent);
	public abstract void setValue(Object value, boolean selectAll);
	public abstract boolean isCorrect(Object value, EditField field) throws ParseException;
	
	
	@Override
	public void focusLost(FocusEvent e) {
	}
	public void focusGained(FocusEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTraversed(TraverseEvent e) {
		if(e.keyCode==SWT.TAB){
			cellOwner.keyTraversed(e);
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}
}
