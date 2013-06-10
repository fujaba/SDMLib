package de.uniks.jism.gui.table.controls;

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
