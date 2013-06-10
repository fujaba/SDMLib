package de.uniks.jism.gui.table.controls;

import java.text.ParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public class SpinnerEditControl extends EditControl<Spinner> {
	private EditField owner;

	public SpinnerEditControl(EditField owner){
		this.fieldTyp = EditFields.INTEGER;
		this.owner = owner;
	}
	@Override
	public void setValue(Object value, boolean selectAll) {
		if (value instanceof Integer || value instanceof String) {
			control.setSelection(Integer.valueOf("" + value));
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
	public void keyReleased(KeyEvent e) {
		if (e.keyCode == SWT.ESC) {
			e.doit = false;
			if(cellOwner != null){
				cellOwner.cancelEditor();
			}
		}
	}

	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		String value = control.getText();
		if(convert){
			return owner.convertFromString(value);
		}
		return value;
	}

	@Override
	public void createControl(EditField owner, Composite parent) {
		this.cellOwner = owner.getCellEditorElement();
		control = new Spinner(parent, SWT.BORDER);
		control.setMaximum(Integer.MAX_VALUE);
		control.setMinimum(Integer.MIN_VALUE);

		if(cellOwner!=null){
			control.addSelectionListener(cellOwner);
		};
	}
}
