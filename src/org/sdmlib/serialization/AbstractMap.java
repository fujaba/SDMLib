package org.sdmlib.serialization;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import org.sdmlib.serialization.event.MapEntry;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
/**
 *AbstractIdMap embedded all methods for all formats.
 *
 */

public abstract class AbstractMap {
	/** The creators. */
	protected ArrayEntryList<String> creators = new ArrayEntryList<String>(); 

	/**
	 * Gets the creator class.
	 *
	 * @param reference
	 *            the reference
	 * @return the creator class
	 */
	public SendableEntityCreator getCreatorClass(Object reference) {
		if (reference == null) {
			return null;
		}
		return getCreatorClassName(reference.getClass().getName(), true);
	}

	/**
	 * Gets the creator classes.
	 * 
	 * @param clazz Clazzname for search
	 * @param fullName if the clazzName is the Fullname for search
	 * @return return a Creator class for a clazz name
	 */
	public SendableEntityCreator getCreatorClassName(String clazz, boolean fullName) {
		if(fullName){
			return (SendableEntityCreator) this.creators.get(clazz);
		}
		clazz = "." + clazz;
		for (Iterator<MapEntry<String>> i = this.creators
				.entrySet().iterator(); i.hasNext();) {
			Entry<String, Object> entry = i.next();
			if (entry.getKey().endsWith(clazz) && entry.getValue() instanceof SendableEntityCreator) {
				return (SendableEntityCreator)entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Adds the creator.
	 *
	 * @param creatorSet the creater class
	 * @return true, if successful
	 */
	public AbstractMap withCreator(Collection<SendableEntityCreator> creatorSet) {
		for (SendableEntityCreator sendableEntityCreator : creatorSet) {
			withCreator(sendableEntityCreator);
		}
		return this;
	}

	/**
	 * add a Creator to list of all creators.
	 *
	 * @param className
	 *            the class name
	 * @param creator
	 *            the creator
	 * @return AbstractIdMap to interlink arguments
	 */
	public AbstractMap withCreator(String className, SendableEntityCreator creator) {
		this.creators.put(className, creator);
		return this;
	}

	/**
	 * Adds the creator.
	 *
	 * @param createrClass
	 *            the creater class
	 * @return AbstractIdMap to interlink arguments
	 */
	public AbstractMap withCreator(SendableEntityCreator createrClass) {
		Object reference = createrClass.getSendableInstance(true);
		if (reference != null) {
			withCreator(reference.getClass().getName(), createrClass);
		}
		return this;
	}
	
	/**
	 * remove the creator.
	 *
	 * @param createrClass
	 *            the creater class
	 * @return true, if successful
	 */
	public boolean removeCreator(String className) {
		return this.creators.remove(className);
	}

	/**
	 * @return a Collection of All Creators
	 */
	public Collection<SendableEntityCreator> getCreators() {
		ArrayList<SendableEntityCreator> result=new ArrayList<SendableEntityCreator>();
		for (Iterator<MapEntry<String>> i = this.creators
				.entrySet().iterator(); i.hasNext();) {
			Object value = i.next().getValue();
			if(value instanceof SendableEntityCreator) {
				result.add((SendableEntityCreator) value);
			}
		}
		return result;
	}

}
