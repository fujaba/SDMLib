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
