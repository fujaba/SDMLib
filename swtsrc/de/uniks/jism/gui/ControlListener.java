package de.uniks.jism.gui;

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
