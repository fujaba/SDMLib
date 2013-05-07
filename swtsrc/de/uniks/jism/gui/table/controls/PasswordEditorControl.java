package de.uniks.jism.gui.table.controls;

import java.text.ParseException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public class PasswordEditorControl extends EditControl<Text>{
	public PasswordEditorControl(){
		this.fieldTyp = EditFields.PASSWORD;
	}
	@Override
	public void setValue(Object value, boolean selectAll) {
		control.setText(""+value);
		if(selectAll){
			control.selectAll();
		}
	}

	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		return control.getText();
	}

	@Override
	public void createControl(EditField owner, Composite parent) {
		control = new Text(parent, SWT.BORDER | SWT.FILL | SWT.PASSWORD); 
	}

	@Override
	public boolean isCorrect(Object value, EditField field)
			throws ParseException {
		return true;
	}

	@Override
	public Object getValue(EditField owner, boolean convert)
			throws ParseException {
		return control.getText();
	}
}
