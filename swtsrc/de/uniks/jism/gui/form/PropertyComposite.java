package de.uniks.jism.gui.form;
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

import de.uniks.jism.gui.GUIPosition;
import de.uniks.jism.gui.layout.BorderLayout;
import de.uniks.jism.gui.table.Column;
import de.uniks.jism.gui.table.celledit.CellEditorElement;
import de.uniks.jism.gui.table.celledit.EditField;
import de.uniks.jism.gui.table.celledit.EditFields;

public class PropertyComposite extends Composite implements PropertyChangeListener, CellEditorElement{
	private CLabel westLabel;
	private Composite centerComposite;
	private CLabel eastLabel;
	private LabelPosition labelOrientation=LabelPosition.WEST;
	private String labelPostText=": ";
	private EditField field=new EditField();
	private SendableEntityCreator creator;
	private Object item;
	private Column column=new Column();
	private BorderLayout layout;

	public PropertyComposite(Composite parent, int style) {
		super(parent, style);
		layout = new BorderLayout(0,0);
		setLayout(layout);

		westLabel = new CLabel(this, SWT.NONE);
		westLabel.setLayoutData(GUIPosition.WEST);
		
		centerComposite = new Composite(this, SWT.NONE);
		centerComposite.setLayoutData(GUIPosition.CENTER);
		centerComposite.setLayout(new FillLayout());
		
		field.setLayoutFromParent(false);
		
		eastLabel = new CLabel(this, SWT.NONE);
		eastLabel.setLayoutData(GUIPosition.EAST);
		if(this.getParent() instanceof ModelForm){
			ModelForm modelForm=(ModelForm) this.getParent();
			modelForm.addProperty(this);
		}
	}
	
	public String getLabelText() {
		return column.getLabel();
	}

	public void setLabelText(String value) {
		this.column.setLabel(value);
		initLabel();
		initControl();
	}
	
	public void setFieldType(EditFields type){
		this.field.setFormat(type);
	}


	public LabelPosition getLabelOrientation() {
		return labelOrientation;
	}
	
	public void initLabel() {
		if(LabelPosition.WEST.equals(labelOrientation)){
			westLabel.setVisible(true);
			
			eastLabel.setVisible(false);
			if(column.getLabel()!=null){
				westLabel.setText(column.getLabel()+labelPostText);
			}
		}else if(LabelPosition.EAST.equals(labelOrientation)){
			westLabel.setVisible(false);
			eastLabel.setVisible(true);
			if(column.getLabel()!=null){
				eastLabel.setText(column.getLabel());
			}
		}else{
			westLabel.setVisible(false);
			eastLabel.setVisible(false);
		}
	}


	public void setLabelOrientation(LabelPosition labelOrientation) {
		this.labelOrientation = labelOrientation;
		initLabel();
	}
	
	private CLabel getLabelControl(){
		if(labelOrientation==null){
		}else if(labelOrientation.equals(LabelPosition.WEST)){
			return westLabel;
		}else if(labelOrientation.equals(LabelPosition.EAST)){
			return eastLabel;
		}
		return null;
	}
	
	public int getLabelLength(){
		CLabel control = getLabelControl();
		if(control!=null){
			Point size = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			return size.x;
		}
		return 0;
	}
	
	public void setLabelLength(int width){
		CLabel control = getLabelControl();
		if(control!=null){
			layout.setFixWestSize(width);
			control.layout();
		}
	}

	public String getLabelPostText() {
		return labelPostText;
	}


	public void setLabelPostText(String value) {
		this.labelPostText = value;
	}
		
	public void setProperty(String value){
		this.column.setAttrName(value);
		field.init(column);
		initControl();
	}
	public String getProperty(){
		return column.getAttrName();
	}
	
	public void setDataBinding(IdMap map, Object item) {
		setDataBinding(map, item, column);
	}
	
	public void setDataBinding(IdMap map, Object item, Column column) {
		this.creator = map.getCreatorClass(item);;
		this.item = item;
		if(column!=null){
			this.column = column;
		}
		field.init(item, map, this.column);
		initControl();
		field.setValue(creator.getValue(item, this.column.getAttrName()));
//		if(column.getWidth()>0){
//			Control control = field.getControl();
//		}
		initLabel();
		if(item instanceof SendableEntity) {
			((SendableEntity) item).addPropertyChangeListener(this.column.getAttrName(), this);
		}
	}
	
	private void initControl(){
		field.setParent(this, centerComposite);
		field.getControl().addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				focusnext();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		field.getControl().addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				onFocusLost();
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				onFocus();
			};
		});
	}

	public void focusnext(){
		if(this.getParent() instanceof ModelForm){
			ModelForm parent=(ModelForm) this.getParent();
			parent.focusnext();
		}
	}

	public void addFieldListener(FocusListener listener) {
		field.getControl().addFocusListener(listener);
	}
	
	public void addFieldListener(KeyListener listener) {
		field.getControl().addKeyListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		field.setValue(evt.getNewValue());
	}

	public void reload() {
		Object value = creator.getValue(item, column.getAttrName());
		field.setValue(value);
		
	}

	public void save() {
		try {
			creator.setValue(item, column.getAttrName(), field.getValue(true), IdMap.UPDATE);
		} catch (ParseException e) {
		}
	}

	public void defaultSelection(SelectionEvent event) {
//		field.dispose();
	}

	public void keyRelease(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void onFocusLost() {
		if(this.getParent() instanceof ModelForm){
			ModelForm parent=(ModelForm) this.getParent();
			parent.onFocusLost(this);
		}
	}
	
	public void onFocus() {
		if(this.getParent() instanceof ModelForm){
			ModelForm parent=(ModelForm) this.getParent();
			parent.onFocus(this);
		}
	}

	@Override
	public Object getEditorValue(boolean convert) throws ParseException {
		return field.getValue(convert); 
	}
}
