package de.uniks.jism.gui.table.controls;


import java.text.ParseException;
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.sdmlib.serialization.date.StringTokener;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public class ComboEditControl extends EditControl<CCombo>{
private ArrayList<Object> list;

	public ComboEditControl(){
		this.fieldTyp = EditFields.COMBOBOX;
	}

	@Override
	public void setValue(Object value, boolean selectAll) {
		control.setText(""+value);
	}

	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		Object value=control.getText();
		if("".equals(value)){
			return null;
		}
		if(control.getSelectionIndex()<0){
			return null;
		}
		return list.get(control.getSelectionIndex());
	}

	@Override
	public void createControl(EditField owner, Composite parent) {
		control=new CCombo(parent, SWT.BORDER);
		if(owner.getEditFormat()==EditFields.ASSOC){
			if(owner.getSendableEntityCreator()!=null){
				this.list = owner.getMap().getTypList(owner.getSendableEntityCreator());
			}
		}else if(owner.getNumberFormat()!=null && owner.getNumberFormat().startsWith("[")){
			StringTokener tokener=new StringTokener(owner.getNumberFormat());
			tokener.setIndex(1);
			tokener.setLength(tokener.getLength()-1);
			String sub;
			this.list=new ArrayList<Object>();
			do{
				sub = tokener.nextString(',', true, true);
				if(sub.length()>0){
					list.add(sub);
				}
			}while(sub.length()>0);
		}
		if(list!=null){
			String[] items=new String[list.size()];
			int count=0;
			for(Object item : list){
				items[count++]=item.toString();
			}
			control.setItems(items);
		}			
	}

	@Override
	public boolean isCorrect(Object value, EditField field)
			throws ParseException {
		return false;
	}
	
	@Override
	public void addChoiceList(Object value) {
		if(this.list == null){
			this.list = new ArrayList<Object>();
		}
		this.list.add(value);
		
		String[] items=new String[list.size()];
		int count=0;
		for(Object item : list){
			items[count++]=item.toString();
		}
		control.setItems(items);
	}
}
