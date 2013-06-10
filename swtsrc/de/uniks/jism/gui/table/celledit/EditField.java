package de.uniks.jism.gui.table.celledit;

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

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.event.creator.DateCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import de.uniks.jism.gui.table.Column;
import de.uniks.jism.gui.table.controls.CheckBoxEditControl;
import de.uniks.jism.gui.table.controls.ComboEditControl;
import de.uniks.jism.gui.table.controls.DateTimeEditControl;
import de.uniks.jism.gui.table.controls.EditControl;
import de.uniks.jism.gui.table.controls.NumberEditControl;
import de.uniks.jism.gui.table.controls.PasswordEditorControl;
import de.uniks.jism.gui.table.controls.SpinnerEditControl;
import de.uniks.jism.gui.table.controls.TextEditorControl;

public class EditField {
	private EditControl<?> editControl;
	protected IdMap map;
	protected EditFields format;
	protected String numberFormat;
	protected SendableEntityCreator creatorClass;
	protected CellEditorElement owner;
	protected Composite parent;
	protected HashMap<EditFields, EditControl<?>> fields=new HashMap<EditFields, EditControl<?>>();
	protected boolean layoutFromParent=true;
	protected Column column;

	public EditField(){
		addToEditControls( new CheckBoxEditControl() );
		addToEditControls( new ComboEditControl() );
		addToEditControls( new DateTimeEditControl() );
		addToEditControls( new NumberEditControl(this) );
		addToEditControls( new SpinnerEditControl(this) );
		addToEditControls( new TextEditorControl() );
		addToEditControls( new PasswordEditorControl());
	}
	public void addToEditControls(EditControl<?> field){
		fields.put(field.getFieldTyp(), field);
	}
	
	public void setLayoutFromParent(boolean visible){
		this.layoutFromParent = false;
	}
		
	public Column getColumn(){
		return column;
	}
	public EditField init(Column column, Object value) {
		if(map!=null){
			SendableEntityCreator creator = map.getCreatorClass(value);
			if(creator!=null){
				if(creator instanceof DateCreator){
					setFormat(EditFields.DATE);
					return this;
				}
				setFormat(EditFields.COMBOBOX, map, creator);
				return this;
			}
		}
		if(value instanceof Integer){
			setFormat(EditFields.INTEGER, "###");
		}else if(value instanceof Double){
			setFormat(EditFields.DOUBLE, "###.##");
		}else if(value instanceof String){
			if( format!=EditFields.COMBOBOX ){
				setFormat(EditFields.TEXT);
			}
		}else if(value instanceof Boolean){
			setFormat(EditFields.CHECKBOX);
		}
		return this;
	}
	public EditField init(Object element, IdMap map, Column column) {
		this.column = column;
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
				init(column, value);
			}
		}
		return this;
	}
	public void setFormat(EditFields format){
		this.format = format;
	}
	
	public void setFormat(EditFields format, String numberFormat){
		this.format = format;
		this.numberFormat = numberFormat;
	}
	public void setNumberFormat(String value){
		this.numberFormat = value;
	}
	
	public void setFormat(EditFields format, IdMap map,SendableEntityCreator creator ){
		this.format = format;
		this.map = map;
		this.creatorClass = creator;
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
	
	public void setValue(Object value, boolean selectAll) {
		if(value!=null){
			if(editControl.isActive()){  
				editControl.setValue(value, selectAll);
			}
		}
	}
	
	public boolean isCorrect(Object value) throws ParseException{
		return editControl.isCorrect(value, this);
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
	public boolean setParent(CellEditorElement owner, Composite parent){
		this.owner=owner;
		this.parent=parent;
		return createControl();
	}
	
	public String getNumberFormat() {
		return numberFormat;
	}
	
	public EditFields getEditFormat() {
		return format;
	}
	
	public SendableEntityCreator getSendableEntityCreator(){
		return creatorClass;
	}
	
	public CellEditorElement getCellEditorElement(){
		return owner;
	}
	
	public IdMap getMap() {
		return map;
	}
	
	public Control getControl() {
		if(editControl!=null){
			return editControl.getControl();
		}
		return null;
	}
	public boolean isVisible() {
		if(editControl!=null){
			return editControl.isVisible();
		}
		return false;
	}
	
	public boolean setFocus() {
		if(editControl!=null){
			return editControl.setFocus();
		}
		return false;
	}
	
	/**
	 * Hides this cell editor's control. Does nothing if this cell editor is not
	 * visible.
	 */
	public void setVisible(boolean visible) {
		if (editControl != null) {
			editControl.setVisible(visible);
		}
	}

	/**
	 * Disposes of this cell editor and frees any associated SWT resources.
	 */
	public void dispose() {
		if (editControl != null) {
			editControl.dispose();
		}
	}
	
	private boolean createControl(){
		if(parent==null){
			return false;
		}
		EditControl<?> newFieldControl;
		
		if(format==null){
			format=EditFields.TEXT;
		}
		
		newFieldControl = fields.get(format);
		
		if(editControl != null){
			if(editControl == newFieldControl){
				return false;
			}
			editControl.dispose();
		}
		

		if(newFieldControl==null){
			return false;
		}
		
		editControl = newFieldControl;
		editControl.createControl(this, parent);
        // We really want a selection listener but it is not supported so we
        // use a key listener and a mouse listener to know when selection changes
        // may have occurred
		editControl.registerListener(owner);
		if(layoutFromParent){
			editControl.setFormatFromParent(parent);
		}
		return true;
	}
	public Object getEditorValue(boolean convert) throws ParseException {
		return editControl.getEditorValue(convert);
	}
	public void addChoiceList(Object value) {
		editControl.addChoiceList(value);
	}
}
