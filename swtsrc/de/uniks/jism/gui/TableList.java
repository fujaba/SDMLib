package de.uniks.jism.gui;
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
