package de.uniks.jism.gui.table.controls;

import java.text.ParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public class TextEditorControl extends EditControl<Text>{
	public TextEditorControl(){
		this.fieldTyp = EditFields.TEXT;
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
		control = new Text(parent, SWT.BORDER | SWT.FILL); 
	}

	@Override
	public boolean isCorrect(Object value, EditField field)
			throws ParseException {
		return true;
	}

	@Override
	public void focusLost(FocusEvent e) {
//		super.focusLost(e);
//		System.out.println("LOST"+control.getText());
	}
}
