package org.sdmlib.serialization;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or - as soon they
 will be approved by the European Commission - subsequent
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

	public ReferenceObject withId(String id){
		this.jsonId = id;
		return this;
	}
	
	public ReferenceObject withCreator(SendableEntityCreator value){
		this.creator = value;
		return this;
	}
	
	public ReferenceObject withProperty(String value){
		this.property = value;
		return this;
	}
	
	public ReferenceObject withEntity(Object value){
		this.entity = value;
		return this;
	}
	
	/**
	 * Execute.
	 * 
	 * @return true, if successful
	 */
	public boolean execute(IdMap map) {
		Object assoc = map.getObject(this.jsonId);
		if (assoc != null) {
			this.creator.setValue(this.entity, this.property, assoc, IdMap.NEW);
			return true;
		}
		return false;
	}

	/**
	 * Gets the creater.
	 * 
	 * @return the creater
	 */
	public SendableEntityCreator getCreater() {
		return this.creator;
	}

	/**
	 * Gets the entity.
	 * 
	 * @return the entity
	 */
	public Object getEntity() {
		return this.entity;
	}

	/**
	 * Gets the property.
	 * 
	 * @return the property
	 */
	public String getProperty() {
		return this.property;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (entity == null) {
			return property;
		}
		return this.property + ":" + this.entity.getClass().getName();
	}
}
