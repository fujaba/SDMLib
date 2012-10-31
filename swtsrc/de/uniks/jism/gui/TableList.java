package de.uniks.jism.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.PeerMessage;
import org.sdmlib.utils.PropertyChangeClient;

public class TableList implements PeerMessage, PropertyChangeClient {
	public static final String PROPERTY_ITEMS = "items";

	private LinkedHashSet<PeerMessage> items;

	public TableList(){
		
	}
	public TableList(Collection<? extends PeerMessage> items){
		addAll(items);
	}
	public TableList(Iterator<? extends PeerMessage> items){
		addAll(items);
	}
	
	public Object get(String attrName) {
		int pos = attrName.indexOf('.');
		String attribute = attrName;

		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		}
		if (PROPERTY_ITEMS.equalsIgnoreCase(attribute)) {
			return getItems();
		}
		return null;
	}

	public boolean set(String attrName, Object value) {
		if (PROPERTY_ITEMS.equalsIgnoreCase(attrName)) {
			if (value instanceof PeerMessage) {
				PeerMessage entry = (PeerMessage) value;
				addToItems(entry);
				return true;
			}
		}else if ((PROPERTY_ITEMS+IdMap.REMOVE).equalsIgnoreCase(attrName)) {
			if (value instanceof PeerMessage) {
				PeerMessage entry = (PeerMessage) value;
				removeFromItems(entry);
				return true;
			}
		}
		return false;
	}

	public LinkedHashSet<PeerMessage> getItems() {
		if (items == null) {
			this.items = new LinkedHashSet<PeerMessage>();
		}
		return items;
	}

	public void setItems(LinkedHashSet<PeerMessage> items) {
		this.items = items;
	}

	public boolean addToItems(PeerMessage value){
		if (this.items == null) 
		{
			this.items = new LinkedHashSet<PeerMessage>();
		}
		
		if (!this.items.contains(value)) 
		{
			this.items.add(value);
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, null, value);
			return true;
		}
		return false;
	}
	
	public boolean removeFromItems(PeerMessage value) {
		if (this.items == null) {
			return true;
		}

		if (this.items.contains(value)) {
			this.items.remove(value);
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, value,null);
			return true;
		}
		return false;
	}
	
	public boolean addAll(Iterator<? extends PeerMessage> list){
		while(list.hasNext()){
			PeerMessage item = list.next();
			if(item!=null){
				if(!addToItems(item)){
					return false;
				}	
			}
		}
		return true;
	}
	public boolean addAll(Collection<? extends PeerMessage> list){
		for(PeerMessage item : list){
			if(!addToItems(item)){
				return false;
			}
		}
		return true;
	}

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	// ==========================================================================

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(String name,
			PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(name, listener);		
	}

	public void addPropertyChangeListener(String name,
			PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(name, listener);
	
	}
}
