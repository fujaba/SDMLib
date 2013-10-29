package org.sdmlib.serialization;

/*
 NetworkParser
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
