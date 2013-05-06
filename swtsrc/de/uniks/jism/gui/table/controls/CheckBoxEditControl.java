package de.uniks.jism.gui.table.controls;


import java.text.ParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public class CheckBoxEditControl extends EditControl<Button>{
public CheckBoxEditControl(){
		this.fieldTyp = EditFields.CHECKBOX;
	}
	
	@Override
	public void setValue(Object value, boolean selectAll) {
		control.setText(""+value);
	}

	@Override
	public boolean isCorrect(Object value, EditField field)
			throws ParseException {
		return true;
	}


	@Override
	public void createControl(EditField owner, Composite parent) {
		control = new Button(parent, SWT.CHECK);		
	}


	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object getValue(EditField owner, boolean convert)
			throws ParseException {
		return control.getSelection();
	}
}
