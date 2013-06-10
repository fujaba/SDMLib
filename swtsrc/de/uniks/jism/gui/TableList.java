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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import org.sdmlib.serialization.EntityValueFactory;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.PeerMessage;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.sort.EntityComparator;
import org.sdmlib.serialization.sort.SortingDirection;

public class TableList implements Collection<Object>, PeerMessage, SendableEntity {
	public static final String PROPERTY_ITEMS = "items";
	protected EntityComparator comparator;
	protected TreeSet<Object> list;
	protected HashSet<Object> index;
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	
	public TableList(){
		comparator=new EntityComparator();
	}

	public TableList(Collection<? extends Object> items){
		this();
		addAll(items);
	}
	public TableList(Iterator<? extends Object> items){
		this();		
		addAll(items);
	}
	private TreeSet<Object> getItemSet(boolean always){
		if(list==null||always){
			list=new TreeSet<Object>(comparator);
		}
		if(index==null){
			index=new HashSet<Object>();
		}
		return list;
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
		return list;
	}
	

	public void setItems(LinkedHashSet<Object> items) {
		addAll(items);
	}

	@Override
	public boolean add(Object value){
		if (!contains(value)) 
		{
			
			getItemSet(false).add(value);
			index.add(value);
	
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, null, value);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Object value) {
		if (contains(value)) {
			getItemSet(false).remove(value);
			index.remove(value);

			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, value,null);
			return true;
		}
		return false;
	}
	
	@Override
	public void clear() {
		removeAll(iterator());
	}
	
	@Override
	public boolean removeAll(Collection<?> list) {
		Iterator<?> i=list.iterator();
		return removeAll(i);
	}
	
	public boolean removeAll(Iterator<?> i) {
		while(i.hasNext()){
			Object item = i.next();
			if(item!=null){
				if(!remove(item)){
					return false;
				}	
			}
		}
		return true;
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


	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}

	// ==========================================================================

	public boolean addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
		return true;
	}

	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(listener);
		return true;
	}
	
	public boolean removePropertyChangeListener(String name,
			PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(name, listener);
		return true;
	}

	public boolean addPropertyChangeListener(String name,
			PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(name, listener);
		return true;
	
	}
	public boolean removeAllFromItems() {
		Object[] array = toArray(new Object[size()]);
		for(Object item : array){
			if(!remove(item)){
				return false;
			}
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, item, null);
		}
		return true;
	}
	
	public void setSort(String field, SortingDirection direction, EntityValueFactory cellValueCreator) {
		comparator.setColumn(field);
		comparator.setDirection(direction);
		comparator.setCellCreator(cellValueCreator);
		refreshSort();
	}
	public void setSort(String field, SortingDirection direction) {
		comparator.setColumn(field);
		comparator.setDirection(direction);
		refreshSort();
	}

	public void refreshSort(){
		TreeSet<Object> oldValue = list;
		
		list = getItemSet(true);
		int size = oldValue.size();
		Object[] array = oldValue.toArray(new Object[size]);
		for(int i=0;i<size;i++){
			list.add(array[i]);
		}
	}
		
	
	public void setSort(String field) {
		comparator.setColumn(field);
		refreshSort();
	}
	
	public int compare(Object o1, Object o2) {
		return comparator.compare(o1, o2);
	}
	
	public SortingDirection changeDirection(){
		return comparator.changeDirection();
	}

	@Override
	public boolean contains(Object item) {
		if(list!=null&&index!=null){
			return index.contains(item);
		}
		return false;
	}
	
	public boolean containsIntern(Object item) {
		if(list!=null){
			return new HashSet<Object>(list).contains(item);
		}
		return false;
	}
	
	public Object[] getSortedIndex(){
		IdMap map = comparator.getMap();
		Iterator<Object> iterator = iterator();
		SendableEntityCreator creator = null; 
		if(iterator.hasNext()){
			creator = map.getCreatorClass(iterator.next());
		}
		String column = comparator.getColumn();
		if(creator != null &&  column != null){
			Object[] returnValues=new Object[list.size()];
			EntityValueFactory cellCreator = comparator.getCellCreator();
			if(comparator.getDirection()==SortingDirection.ASC){
				int pos=0;
				for(Iterator<Object> i = iterator();i.hasNext();){
					Object item = i.next();
					returnValues[pos++] = cellCreator.getCellValue(item, creator, column);
				}
			}else{
				int pos=list.size()-1;
				for(Iterator<Object> i = iterator();i.hasNext();){
					Object item = i.next();
					returnValues[pos--] = cellCreator.getCellValue(item, creator, column);
				}
			}
			return returnValues;
		}
		
		return null;
	}

	@Override
	public int size() {
		if(list!=null){
			return list.size();
		}
		return 0;
	}

	@Override
	public boolean isEmpty() {
		if(list!=null){
			return true;
		}
		return false;
	}

	@Override
	public Iterator<Object> iterator() {
		return getItemSet(false).iterator();
	}
	

	@Override
	public Object[] toArray() {
		if(list!=null){
			return list.toArray();
		}
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if(list!=null){
			return list.toArray(a);
		}
		return null;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if(list!=null){
			return list.containsAll(c);
		}
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if(list!=null){
			return list.retainAll(c);
		}
		return false;
	}
}
