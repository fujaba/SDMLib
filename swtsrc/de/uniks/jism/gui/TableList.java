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
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.PeerMessage;
import org.sdmlib.utils.PropertyChangeClient;

import de.uniks.jism.gui.table.CellValueCreator;
import de.uniks.jism.gui.table.TableListComparator;

public class TableList extends TreeSet<Object> implements PeerMessage, PropertyChangeClient {
	private static final long serialVersionUID = 1L;
	public static final String PROPERTY_ITEMS = "items";
	private TableListComparator comparator;

	public TableList(){
		super(new TableListComparator());
		Comparator<? super Object> tc = this.comparator();
		if(tc instanceof TableListComparator){
			comparator=(TableListComparator) tc;
		}		
	}
	public TableList(Collection<? extends Object> items){
		super(new TableListComparator());
		Comparator<? super Object> tc = this.comparator();
		if(tc instanceof TableListComparator){
			comparator=(TableListComparator) tc;
		}		
		addAll(items);
	}
	public TableList(Iterator<? extends Object> items){
		super(new TableListComparator());
		Comparator<? super Object> tc = this.comparator();
		if(tc instanceof TableListComparator){
			comparator=(TableListComparator) tc;
		}		

		addAll(items);
	}
	public void setIdMap(IdMap map){
		if(this.comparator!=null){
			comparator.setIdMap(map);
		}
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
			add(value);
			return true;
		}else if ((PROPERTY_ITEMS+IdMap.REMOVE).equalsIgnoreCase(attrName)) {
			remove(value);
			return true;
		}
		return false;
	}

	public TreeSet<Object> getItems() {
		return this;
	}
	

	public void setItems(LinkedHashSet<Object> items) {
		addAll(items);
	}

	@Override
	public boolean add(Object value){
		if (!contains(value)) 
		{
			super.add(value);
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, null, value);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Object value) {
		if (contains(value)) {
			super.remove(value);
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, value,null);
			return true;
		}
		return false;
	}
	
	public boolean addAll(Iterator<? extends Object> list){
		while(list.hasNext()){
			Object item = list.next();
			if(item!=null){
				if(!add(item)){
					return false;
				}	
			}
		}
		return true;
	}
	public boolean addAll(Collection<? extends Object> list){
		for(Object item : list){
			if(!add(item)){
				return false;
			}
		}
		return true;
	}

	public boolean addAll(TableList list){
		for(Object item : list.getItems()){
			if(!add(item)){
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
	public boolean removeAllFromItems() {
		for(Iterator<Object> i=iterator();i.hasNext();){
			Object item = i.next();
			if(!remove(item)){
				return false;
			}
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, item, null);
		}
		return true;
	}
	
	public void setSort(String field, int direction, CellValueCreator cellValueCreator) {
		comparator.setColumn(field);
		comparator.setDirection(direction);
		comparator.setCellCreator(cellValueCreator);
	}
	
	public void setSort(String field) {
		comparator.setColumn(field);
	}
	
	public int compare(Object o1, Object o2) {
		return comparator.compare(o1, o2);
	}
	
	public int changeDirection(){
		int direction = comparator.getDirection();
		if (direction == TableListComparator.ASC) {
			return comparator.setDirection(TableListComparator.DESC);
		} else if (direction == TableListComparator.DESC) {
			return comparator.setDirection(TableListComparator.ASC);
		}
		return direction;
	}
}
