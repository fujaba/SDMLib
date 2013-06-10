package de.uniks.jism.gui;

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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ControlListener implements Listener, PropertyChangeListener {
	protected Widget widget;
	protected Object item;
	protected String property;
	protected SendableEntityCreator creator;

	public ControlListener(Widget widget, Object item, String property, SendableEntityCreator creator){
		this.widget = widget;
		this.item = item;
		this.property = property;
		this.creator = creator;

		setValue(creator.getValue(item, property));

		// Add Listener
		addListener(item);
		widget.addListener(SWT.CHANGED, this);
	}
	
	public void setControl(Text widget){
		this.widget = widget;
		
	}
	
	public void setValue(Object value){
		if(widget instanceof Text){
			((Text)widget).setText(""+value);
		}
	}
	
	public Object getValue(){
		if(widget instanceof Text){
			return ((Text)widget).getText();
		}
		return null;
	}

	/**
	 * @param check for add Listener to object 
	 * @return success of adding
	 */
	public boolean addListener(Object object){
		if (object instanceof SendableEntity) {
			return ((SendableEntity) object).addPropertyChangeListener(IdMap.UPDATE, this);
		}
		return false;
	}
	
	
	@Override
	public void handleEvent(Event event) {
		if(event.type==SWT.CHANGED){
			String typ=IdMap.UPDATE;
			if(creator.getValue(item, property)==null){
				typ=IdMap.NEW;
			}
			creator.setValue(item, property, getValue(), typ);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(property.equalsIgnoreCase(evt.getPropertyName())){
			setValue(evt.getNewValue());
		}
	}
}
