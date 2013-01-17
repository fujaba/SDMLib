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

import java.text.ParseException;
import java.util.ArrayList;

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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class EditField {
	public static final int FORMAT_INTEGER=1;
	public static final int FORMAT_DOUBLE = 2;
	public static final int FORMAT_COMBOX=3;

	protected Spinner spinner;
	private int format;
	private CellEditorElement owner;
	private Composite parent;
	private String numberFormat;
	private ArrayList<Object> list;
	private IdMap map;
	private Control control;
	private SendableEntityCreator creatorClass;
	
	public EditField(CellEditorElement owner, Composite parent){
		this.owner=owner;
		this.parent=parent;
		
	}
	public void dispose(){
		if (spinner != null && !spinner.isDisposed()) {
			spinner.dispose();
		}
		spinner = null;
		
		control=null;
	}
	public int getFormat() {
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
	
	
	public void doSetValue(Object value) {
		if (spinner != null && !spinner.isDisposed()) {
			if(value instanceof Integer || value instanceof String){
				spinner.setSelection(Integer.valueOf(""+value));
			}
		}
		if(control instanceof Text){
			Text text=(Text) control;
			if (text != null && !text.isDisposed()) {
				text.setText(""+value);
			}
		}

		if(control instanceof CCombo){
			CCombo combo=(CCombo) control;
			if(combo!=null&& !combo.isDisposed()) {
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
		}
	}
	
	public void createControl(int format, IdMap map, SendableEntityCreator creator) {
		this.map=map;
		this.creatorClass=creator;
		createControl(format);
	}
	
	public void createControl(String numberFormat, int format) {
		this.numberFormat=numberFormat;
		createControl(format); 
	}
	public void createControl(int format) {
		this.format=format;
		if(format==FORMAT_INTEGER){
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
			control.setFont(parent.getFont());
			control.setBackground(parent.getBackground());		
		}
	}
	public void defaultSelection(SelectionEvent event){
		owner.defaultSelection(event);
	}
	public void keyRelease(KeyEvent event){
		owner.keyRelease(event);
	}
	public void onFocusLost(){
		owner.onFocusLost();
	}
	public Object getText() {
		if(format==FORMAT_DOUBLE){
			return ((Text) control).getText().replaceAll(",",".");
		}
		if(format==FORMAT_INTEGER){
			return spinner.getText();
		}
		if(format==FORMAT_COMBOX){
			return ((CCombo)control).getText();
		}
		return "";
	}
	public Object convertFromString(String value) throws ParseException{
		if((value==null)||(value.trim().equals(""))) return null;
		if(format==FORMAT_INTEGER){
			return Integer.valueOf(value.toString());
		}else if(format==FORMAT_DOUBLE){
			return Double.valueOf(value.toString());
		}else if(format==FORMAT_COMBOX){
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
	}
	public Object getValue() {
		if(format==FORMAT_COMBOX){
			
			Object value=((CCombo)control).getText();
			if("".equals(value)){
				return null;
			}
			CCombo combo=(CCombo) control;
			if(combo.getSelectionIndex()<0){
				return null;
			}
			return list.get(combo.getSelectionIndex());
		}
		return null;
	}
}
