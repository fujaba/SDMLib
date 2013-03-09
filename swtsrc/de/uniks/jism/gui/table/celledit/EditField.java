package de.uniks.jism.gui.table.celledit;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.event.creator.DateCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

import de.uniks.jism.gui.table.Column;

public class EditField {
	private EditFields format=EditFields.TEXT;
	private CellEditorElement owner;
	private Composite parent;
	private String numberFormat;
	private ArrayList<Object> list;
	private IdMap map;
	protected Spinner spinner;
	private Control control;
	private boolean manuellControlField=false;
	private SendableEntityCreator creatorClass;
	private boolean layoutFromParent=true;

	public EditField(){
		
	}
	
	public EditField init(Column column) {
		EditField editField = new EditField();
		editField.setFormat(EditFields.TEXT);
		return this;
	}
	
	public EditField init(Object element, IdMap map, Column column) {
		if(column.getAttrName()!=null){
			SendableEntityCreator creatorClass = map.getCreatorClass(element);
			Object value=null;
			if(creatorClass!=null){
				value = creatorClass.getValue(element, column.getAttrName());
			}
			if(value==null && column.isGetDropDownListFromMap()){
				// Check column
				Class<?> classDef=element.getClass();
		         
		         try {
					Method method = classDef.getDeclaredMethod("get"+getFirstUpperCase(column.getAttrName()));
					SendableEntityCreator creator = map.getCreatorClasses(method.getReturnType().getName());
					if(creator!=null){
						setFormat(EditFields.COMBOBOX, map, creator);
						return this;
					}
				} catch (SecurityException e) {
				} catch (NoSuchMethodException e) {
				}
			}
			if(value!=null){
				SendableEntityCreator creator = map.getCreatorClass(value);
				if(creator!=null){
					if(creator instanceof DateCreator){
						setFormat(EditFields.DATE);
						return this;
					}
					setFormat(EditFields.COMBOBOX, map, creator);
					return this;
				}
				else if(value instanceof Integer){
					setFormat(EditFields.INTEGER, "###");
				}else if(value instanceof Double){
					setFormat(EditFields.DOUBLE, "###.##");
				}else if(value instanceof String){
					setFormat(EditFields.TEXT);
				}
			}
		}
		return this;
	}

	private String getFirstUpperCase(String text){

		StringBuilder result = new StringBuilder(text.length());
		String[] words = text.split("\\s");
		for(int i=0,l=words.length;i<l;++i) {
		  if(i>0) result.append(" ");      
		  result.append(Character.toUpperCase(words[i].charAt(0)))
		        .append(words[i].substring(1));
		}
		return result.toString();
	}
	
	public void dispose(){
		if (spinner != null && !spinner.isDisposed()) {
			spinner.dispose();
		}
		spinner = null;
		
		control=null;
	}
	public EditFields getFormat() {
		return format;
	}
	
	public Control getControl() {
		if (spinner != null && !spinner.isDisposed()) {
			return spinner;
		}
		return control;
	}
	
	
	public void setVisible(boolean value){
		 Control control=getControl();
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
	
	public void setFocus(){
		 Control control=getControl();
		 if(control!=null){
			 control.setFocus();
		 }
	}
	
	
	@SuppressWarnings("deprecation")
	public void setValue(Object value) {
		if (spinner != null && !spinner.isDisposed()) {
			if(value instanceof Integer || value instanceof String){
				spinner.setSelection(Integer.valueOf(""+value));
			}
		}
		if (control != null && !control.isDisposed()){
			if(control instanceof Text){
				((Text) control).setText(""+value);
			}
			if(control instanceof CCombo){
				CCombo combo=(CCombo) control;
				if(creatorClass!=null){
					this.list = this.map.getTypList(creatorClass);
					for(int i=0;i<list.size();i++){
						
					}
					String[] items=new String[list.size()];
					int count=0;
					for(Object item : list){
						items[count++]=item.toString();
					}
					combo.setItems(items);
					combo.setText(""+value);
				}
			}
			if(control instanceof DateTime){
				DateTime dateTime=(DateTime) control;
				Date date=(Date) value;
				dateTime.setDay(date.getDate());
				dateTime.setMonth(date.getMonth());
				dateTime.setYear(date.getYear());
			}
		}
	}
	
	
	
	public void setFormat(EditFields format, IdMap map, SendableEntityCreator creator) {
		this.map=map;
		this.creatorClass=creator;
		this.format=format;
	}
	
	public void setFormat(EditFields format) {
		this.format=format;
	}
	public void setFormat(EditFields format, String numberFormat) {
		this.numberFormat=numberFormat;
		this.format=format;
	}
	public boolean setParent(CellEditorElement owner, Composite parent){
		this.owner=owner;
		this.parent=parent;
		return createControl();
	}
	public boolean setParent(Composite parent){
		if(parent instanceof CellEditorElement ){
			this.owner=(CellEditorElement) parent;
		}
		this.parent=parent;
		return createControl();
	}
	
	private boolean createControl(){
		if(parent==null){
			return false;
		}
		if(!manuellControlField&&control!=null){
			control.dispose();
			control=null;
		}
		if(control==null){
			if(format==EditFields.TEXT||format==EditFields.DOUBLE){
				control = new Text(parent, SWT.BORDER | SWT.FILL); 
			}else if(format==EditFields.INTEGER){
				spinner = new Spinner(parent, SWT.BORDER);
				spinner.setMaximum(Integer.MAX_VALUE);
				spinner.setMinimum(Integer.MIN_VALUE);
				control=spinner;
				
				spinner.addSelectionListener(new SelectionAdapter() {
		            public void widgetDefaultSelected(SelectionEvent e) {
		            	defaultSelection(e);
		            }
		        });
				spinner.addKeyListener(new KeyAdapter() {
		            // hook key pressed - see PR 14201  
		            public void keyPressed(KeyEvent e) {
		            	if(e.keyCode==SWT.ESC){
		            		onFocusLost();
		            		e.doit = false;
		            	}
		            }
		        });
			}else if(format==EditFields.COMBOBOX){
				CCombo combo=new CCombo(parent, SWT.BORDER);
				control = combo;
			}else if(format==EditFields.DATE){
				DateTime dateTime=new DateTime(parent, SWT.BORDER);
				control = dateTime;
			}
			
		}
		if(control!=null){
	        // We really want a selection listener but it is not supported so we
	        // use a key listener and a mouse listener to know when selection changes
	        // may have occurred
	       
			control.addFocusListener(new FocusAdapter() {
	            public void focusLost(FocusEvent e) {
	            	onFocusLost();
	            }
	        });
			if(layoutFromParent){
				control.setFont(parent.getFont());
				control.setBackground(parent.getBackground());
			}
			return true;
		}
		return false;
	}
	public void defaultSelection(SelectionEvent event){
		if(owner!=null){
			owner.defaultSelection(event);
		}
	}
	public void keyRelease(KeyEvent event){
		if(owner!=null){
			owner.keyRelease(event);
		}
	}
	public void onFocusLost(){
		if(owner!=null){
			owner.onFocusLost();
		}
	}

	public Object convertFromString(String value) throws ParseException{
		if((value==null)||(value.trim().equals(""))) return null;
		if(format==EditFields.INTEGER){
			return Integer.valueOf(value.toString());
		}else if(format==EditFields.DOUBLE){
			return Double.valueOf(value.toString());
		}else if(format==EditFields.COMBOBOX){
			return value.toString();
		}
		return value;
	}
	
	public String getNumberFormat() {
		return numberFormat;
	}
	
	public ArrayList<Object> getList() {
		return list;
	}
	
	public void setEditField(Control control) {
		this.control=control;
		manuellControlField = true;
	}
	
	public Object getValue(boolean convert) throws ParseException {
		if(format==EditFields.COMBOBOX){
//			return ((CCombo)control).getText();
			Object value=((CCombo)control).getText();
			if("".equals(value)){
				return null;
			}
			CCombo combo=(CCombo) control;
			if(combo.getSelectionIndex()<0){
				return null;
			}
			return list.get(combo.getSelectionIndex());
		}else if(format==EditFields.DOUBLE){
			String value = ((Text) control).getText().replaceAll(",",".");
			if(convert){
				return convertFromString(value);
			}
			return value;
		}else if(format==EditFields.INTEGER){
			String value = spinner.getText();
			if(convert){
				return convertFromString(value);
			}
			return value;
		}
		return null;
	}

	public void setLayoutFromParent(boolean layoutFromParent) {
		this.layoutFromParent = layoutFromParent;
	}
}
