package org.sdmlib.serialization;
/*
Copyright (c) 2012 Stefan Lindel

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

/**
 * The Class ReferenceObject.
 */
public class ReferenceObject {
	
	/** The json id. */
	private String jsonId;
	
	/** The creator. */
	private SendableEntityCreator creator;
	
	/** The property. */
	private String property;
	
	/** The entity. */
	private Object entity;
	
	/** The map. */
	private IdMap map;
	
	/**
	 * Instantiates a new reference object.
	 *
	 * @param creator the creator
	 * @param property the property
	 * @param map the map
	 * @param entity the entity
	 */
	public ReferenceObject(SendableEntityCreator creator, String property, IdMap map, Object entity){
		this.creator=creator;
		this.property=property;
		this.entity=entity;
		this.map=map;
	}	
	
	/**
	 * Instantiates a new reference object.
	 *
	 * @param jsonId the json id
	 * @param creator the creator
	 * @param property the property
	 * @param map the map
	 * @param entity the entity
	 */
	public ReferenceObject(String jsonId, SendableEntityCreator creator, String property, IdMap map, Object entity){
		this.jsonId=jsonId;
		this.creator=creator;
		this.property=property;
		this.entity=entity;
		this.map=map;
	}
	
	/**
	 * Execute.
	 *
	 * @return true, if successful
	 */
	public boolean execute(){
		Object assoc = map.getObject(jsonId);
		if(assoc!=null){
			creator.setValue(entity, property, assoc);
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the creater.
	 *
	 * @return the creater
	 */
	public SendableEntityCreator getCreater(){
		return creator;
	}
	
	/**
	 * Gets the entity.
	 *
	 * @return the entity
	 */
	public Object getEntity(){
		return entity;
	}
	
	/**
	 * Gets the property.
	 *
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return property+":"+entity.getClass().getName();
	}
}
