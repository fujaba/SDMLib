package de.uniks.jism.gui.table.controls;

import java.text.ParseException;
import java.util.Date;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.sdmlib.serialization.date.DateTimeEntity;
import org.sdmlib.serialization.date.DateTimeFields;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public class DateTimeEditControl extends EditControl<org.eclipse.swt.widgets.DateTime>{
	public DateTimeEditControl(){
		this.fieldTyp = EditFields.DATE;
	}
	@Override
	public void setValue(Object value, boolean selectAll) {
		DateTimeEntity date= new DateTimeEntity((Date) value);
		control.setDay(date.getDate());
		control.setMonth(date.getMonth() - 1);
		control.setYear(date.getYear());
	}

	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		DateTimeEntity myDate=new DateTimeEntity();
		System.out.println(myDate.get(DateTimeFields.SECOND_OF_YEAR));
		myDate.setYear(control.getYear());
		myDate.setMonth(control.getMonth());
		myDate.setDate(control.getDay());
		myDate.setHours(control.getHours());
		myDate.setMinutes(control.getMinutes());
		myDate.setSeconds(control.getSeconds());
		return myDate;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.keyCode==SWT.CR){
			if(cellOwner != null){
				cellOwner.lostFocus();
			}
		}
	}


	@Override
	public void createControl(EditField owner, Composite parent) {
		control=new org.eclipse.swt.widgets.DateTime(parent, SWT.BORDER);
	}

	@Override
	public boolean isCorrect(Object value, EditField field)
			throws ParseException {
		return true;
	}
}
