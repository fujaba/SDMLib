package de.uniks.jism.gui.table.controls;


import java.text.ParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public class NumberEditControl extends EditControl<Text>{
private EditField owner;

	public NumberEditControl(EditField owner){
		this.fieldTyp = EditFields.DOUBLE;
		this.owner=owner;
	}
	
	@Override
	public void setValue(Object value, boolean selectAll) {
		if(value instanceof Integer || value instanceof String){
			control.setText(""+value);
		}
	}


	@Override
	public boolean isCorrect(Object value, EditField editField)
			throws ParseException {
		if (value instanceof Number)
			return true;
		editField.convertFromString((String) value);
		return true;
	}

	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		String value = control.getText().replaceAll(",",".");
		if(convert){
			return owner.convertFromString(value);
		}
		return value;
	}

	@Override
	public void createControl(EditField owner, Composite parent) {
		control = new Text(parent, SWT.BORDER | SWT.FILL);
	}
}
