package org.sdmlib.serialization.gui.table;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;
import org.sdmlib.serialization.EntityValueFactory;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.PeerMessage;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.sort.EntityComparator;
import org.sdmlib.serialization.sort.SortingDirection;

public class TableList implements List<Object>, PeerMessage, SendableEntity {
	public static final String PROPERTY_ITEMS = "items";
	protected EntityComparator comparator;
	protected LinkedHashSet<Object> list;
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	public EntityComparator getComparator(){
		if(comparator==null){
			comparator = new EntityComparator();
			comparator.withTableList(this);
		}
		return comparator;
	}
	
	public LinkedHashSet<Object> getItems(){
		return getItems(false);
	}
	public LinkedHashSet<Object> getItems(boolean always){
		if(list==null||always){
			list=new LinkedHashSet<Object>( );
		}
		return list;
	}
	
	public void setIdMap(IdMap map){
		getComparator().withMap(map);
	}
	
	public Object get(String attrName) {
		int pos = attrName.indexOf('.');
		String attribute = attrName;

		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		}
		if (PROPERTY_ITEMS.equalsIgnoreCase(attribute)) {
			return getItems(false);
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

	public void setItems(LinkedHashSet<Object> items) {
		addAll(items);
	}

	@Override
	public boolean add(Object value){
		if (!contains(value)) 
		{
			TreeSet<Object> items = new TreeSet<Object>(getComparator());
			items.addAll(getItems(false));
			items.add(value);
			
			getItems(true).addAll(items);
			getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, null, value);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Object value) {
		if (contains(value)) {
			LinkedHashSet<Object> items = getItems(false);
			if(items.remove(value)){
				getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, value,null);
				return true;
			}
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
				i.remove();
				getPropertyChangeSupport().firePropertyChange(PROPERTY_ITEMS, item,null);
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
		for(Object item : list.getItems(false)){
			if(!add(item)){
				return false;
			}
		}
		return true;
	}
	public List<Object> subList(int fromIndex, int toIndex) {
		TableList subList=new TableList();
		int count=0;
		Iterator<Object> iterator = iterator();
		while(iterator.hasNext()){
			if(count==fromIndex){
				break;
			}
			iterator.next();
			count++;
		}
		while(iterator.hasNext() && count<toIndex){
			subList.add(iterator.next());
			if(count==toIndex){
				break;
			}
			count++;
		}
		return subList;
	}

	public boolean addAll(Object... arg0) {
		for(Object item : list){
			if(!add(item)){
				return false;
			}
		}
		return true;
	}

	public boolean setAll(Collection<? extends Object> list) {
		this.clear();

		for(Iterator<? extends Object> iterator = list.iterator();iterator.hasNext();){
			if(!add(iterator.next())){
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public boolean addAll(int index, Collection<? extends Object> c) {
		for(Iterator<? extends Object> iterator = list.iterator();iterator.hasNext();){
			if(!add(iterator.next())){
				return false;
			}
		}
		return true;
	}

	@Override
	public Object get(int index) {
		if(index>=0&&index<size()){
			Iterator<Object> iterator = iterator();
			while(index>0){
				iterator.next();
				index--;
			}
			return iterator.next();
		}
		return null;
	}

	@Override
	public Object set(int index, Object element) {
		if(index>=0&&index<size()){
			Iterator<Object> iterator = iterator();
			while(index>0){
				iterator.next();
				index--;
			}
			iterator.remove();
			add(element);
		}
		return null;
	}

	@Override
	public void add(int index, Object element) {
		add(element);
	}

	@Override
	public Object remove(int index) {
		if(index>=0&&index<size()){
			Object[] array = toArray(new Object[size()]);
			Object item = array[index];
			remove(item);
			return item;
		}
		return null;
	}
	
	@Override
	public int indexOf(Object element) {
		int count=0;
		for(Iterator<Object> iterator = iterator();iterator.hasNext();){
			if(iterator.next()==element){
				return count;
			}
			count++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object obj) {
		return indexOf(obj);
	}

	@Override
	public ListIterator<Object> listIterator() {
		return new ListIteratorImpl<Object>(this);
	}

	@Override
	public ListIterator<Object> listIterator(int index) {
		ListIterator<Object> iterator = listIterator();
		if(index>=0&&index<=size()){
			for(int z=0;z<index;z++){
				iterator.next();
			}
		}
		return iterator;
	}

	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
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
	
	public TableList withSort(String field, SortingDirection direction, EntityValueFactory cellValueCreator) {
		EntityComparator comparator = getComparator();
		comparator.withColumn(field);
		comparator.withDirection(direction);
		comparator.withCellCreator(cellValueCreator);
		refreshSort();
		return this;
	}
	
	public TableList withSort(EntityComparator comparator) {
		this.comparator = comparator;
		refreshSort();
		return this;
	}
	
	public TableList withSort(String field, SortingDirection direction) {
		EntityComparator comparator = getComparator();
		comparator.withColumn(field);
		comparator.withDirection(direction);
		refreshSort();
		return this;
	}

	public void refreshSort(){
		LinkedHashSet<Object> oldValue = list;
		
		list = getItems(true);
		int size = oldValue.size();
		Object[] array = oldValue.toArray(new Object[size]);
		for(int i=0;i<size;i++){
			list.add(array[i]);
		}
	}
		
	
	public void setSort(String field) {
		getComparator().withColumn(field);
		refreshSort();
	}
	
	public int compare(Object o1, Object o2) {
		return getComparator().compare(o1, o2);
	}
	
	public SortingDirection changeDirection(){
		return getComparator().changeDirection();
	}

	@Override
	public boolean contains(Object item) {
		return getItems(false).contains(item);
	}
	
	public Object[] getSortedIndex(){
		EntityComparator comparator = getComparator();
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
		return getItems(false).iterator();
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
}
