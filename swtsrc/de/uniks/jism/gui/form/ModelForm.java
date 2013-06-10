package de.uniks.jism.gui.form;

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

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.sdmlib.serialization.DefaultTextItems;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.TextItems;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import de.uniks.jism.gui.table.Column;

public class ModelForm extends Composite{
	private IdMap map;
	private TextItems textClazz= null;
	private LinkedHashSet<PropertyComposite> properties=new LinkedHashSet<PropertyComposite>();
	private Composite actionComposite;
	private Button saveBtn;
	private Button reloadBtn;
	private Object item;
	private PropertyComposite currentFocus;

	public ModelForm(Composite parent, int style) {
		super(parent, style);
		
		setLayout(new RowLayout(SWT.VERTICAL));
	}
	
	public void setPreSize(){
		int max=0;
		
		for(Iterator<PropertyComposite> iterator = properties.iterator();iterator.hasNext();){
			int temp = iterator.next().getLabelLength();
			if(temp>max){
				max = temp; 
			}
		}
		for(Iterator<PropertyComposite> iterator = properties.iterator();iterator.hasNext();){
			iterator.next().setLabelLength(max);
		}
	}
	
	public void init(IdMap map, Object item){
		this.map = map;
		this.item = item;
	}
	
	public void finishDataBinding(){
		for(Iterator<PropertyComposite> iterator = properties.iterator();iterator.hasNext();){
			iterator.next().setDataBinding(map, item);
		}	
	}
	
	public void dispose(){
		for(Iterator<PropertyComposite> iterator = properties.iterator();iterator.hasNext();){
			iterator.next().dispose();
		}	
	}
	
	public void init(IdMap map, Object item, boolean addCommandBtn){
		this.map = map;
		this.item = item;
		textClazz = (TextItems) map.getCreatorClasses(TextItems.class.getName());
		
		SendableEntityCreator creator = map.getCreatorClass(item);
		if(creator != null){
			int max=0;
			for(String property : creator.getProperties()){
				PropertyComposite propertyComposite = new PropertyComposite(this, SWT.NONE);
				Column column = new Column();
				if(this.textClazz!=null){
					column.withLabel(this.textClazz.getText(property, item, this));
					propertyComposite.setLabelOrientation(LabelPosition.WEST);
				}
				column.withAttrName(property);
				
				propertyComposite.setDataBinding(map, item, column);
				int temp = propertyComposite.getLabelLength();
				if(temp>max){
					max = temp; 
				}
				properties.add(propertyComposite);
			}
			for(Iterator<PropertyComposite> iterator = properties.iterator();iterator.hasNext();){
				iterator.next().setLabelLength(max);
			}
			
			if(addCommandBtn){
				this.actionComposite = new Composite(this, SWT.NONE);
				this.actionComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
				
				this.saveBtn = new Button(actionComposite, SWT.NONE);
				this.saveBtn.setText(getText(DefaultTextItems.SAVE));
				this.saveBtn.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						save();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
					}
				});
				this.reloadBtn = new Button(actionComposite, SWT.NONE);
				this.reloadBtn.setText(getText(DefaultTextItems.RELOAD));
				this.reloadBtn.addSelectionListener(new SelectionListener() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						reload();
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
					}
				});
				
			}
		}
	}
	
	private String getText(String label){
		if(this.textClazz!=null){
			return this.textClazz.getText(label, item, this);
		}
		return label;
	}
	
	public void save(){
		for(Iterator<PropertyComposite> iterator = properties.iterator();iterator.hasNext();){
			iterator.next().save();
		}
	}
	public void reload(){
		for(Iterator<PropertyComposite> iterator = properties.iterator();iterator.hasNext();){
			iterator.next().reload();
		}
	}

	public IdMap getMap() {
		return map;
	}
	
	public Object getItem() {
		return item;
	}

	public TextItems getTextClazz() {
		return textClazz;
	}
	
	public void addProperty(PropertyComposite propertyComposite){
		this.properties.add(propertyComposite);
	}
	
	public PropertyComposite getProperty(String property){
		for(Iterator<PropertyComposite> iterator = properties.iterator();iterator.hasNext();){
			PropertyComposite item = iterator.next();
			if(item.getProperty()!=null && item.getProperty().equals(property)){
				return item;
			}
		}
		return null;
	}

	public boolean focusnext() {
		if(currentFocus!=null){
			Iterator<PropertyComposite> iterator = properties.iterator();
			while(iterator.hasNext()){
				if(iterator.next()==currentFocus){
					break;
				}
			}
			if(iterator.hasNext()){
				return iterator.next().setFocus();
			}
			currentFocus = null;
		}
		return false;
	}

	public void onFocus(PropertyComposite propertyComposite) {
		this.currentFocus=propertyComposite;
	}
	
	public void onFocusLost(PropertyComposite propertyComposite) {
		if(this.currentFocus==propertyComposite){
			this.currentFocus=null;
		}
	}
	public void onKeyReleased(KeyEvent event){
		if(event.keyCode == SWT.CR && event.stateMask == 0){
			// ENTER
			focusnext();
		}else if(event.keyCode == SWT.ESC && event.stateMask == 0){
			// EXIT
		}
	}
	public void onKeyTraversed(KeyEvent event){
		if(event.keyCode == SWT.TAB && event.stateMask == 0){
			// TAB
			event.doit=false;
			System.out.println(event.time);
			focusnext();
		}
		
	}
}
