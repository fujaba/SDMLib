package de.uniks.jism.gui;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.TypList;


public class TableListTypList implements TypList{
	private TableList values=new TableList();
	private Class<?> property;

	public TableListTypList(Class<?> property){
		this.property = property;
	}
	public TableListTypList(SendableEntityCreator creator){
		this.property = creator.getSendableInstance(true).getClass();
	}
	
	public boolean isInstance(Object obj){
		return this.property.isInstance(obj);
	}
	
	public TableList getValues() {
		return values;
	}

	public boolean addObject(Object obj) {
		if(isInstance(obj)){
			this.values.add(obj);
			return true;
		}
		return false;
	}

	public boolean removeObject(Object obj) {
		if(isInstance(obj)){
			this.values.remove(obj);
			return true;
		}
		return false;
	}
}
