package org.sdmlib.serialization;

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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public abstract class AbstractIdMap {
/** The creators. */
	protected HashMap<String, SendableEntityCreator> creators;

	public AbstractIdMap() {
		this.creators = new HashMap<String, SendableEntityCreator>();
	}

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
		return getCreatorClasses(reference.getClass().getName());
	}

	public SendableEntityCreator getCreatorClassName(String clazz) {
		clazz = "." + clazz;
		for (Iterator<Entry<String, SendableEntityCreator>> i = this.creators
				.entrySet().iterator(); i.hasNext();) {
			Entry<String, SendableEntityCreator> entry = i.next();
			if (entry.getKey().endsWith(clazz)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Gets the creator classes.
	 * 
	 * @param className
	 *            the class name
	 * @return the creator classes
	 */
	public SendableEntityCreator getCreatorClasses(String className) {
		return this.creators.get(className);
	}

	/**
	 * Adds the creator.
	 * 
	 * @param Set
	 *            <createrClass> the creater class
	 * @return true, if successful
	 */
	public AbstractIdMap withCreator(Set<SendableEntityCreator> creatorSet) {
		for (SendableEntityCreator sendableEntityCreator : creatorSet) {
			withCreator(sendableEntityCreator);
		}
		return this;
	}

	/**
	 * add a Creator to list
	 * 
	 * @param className
	 *            the class name
	 * @param creator
	 *            the creator
	 */
	public AbstractIdMap withCreator(String className, SendableEntityCreator creator) {
		this.creators.put(className, creator);
		return this;
	}

	/**
	 * Adds the creator.
	 * 
	 * @param createrClass
	 *            the creater class
	 * @return true, if successful
	 */
	public AbstractIdMap withCreator(SendableEntityCreator createrClass) {
		Object reference = createrClass.getSendableInstance(true);
		if (reference != null) {
			withCreator(reference.getClass().getName(), createrClass);
		}
		return this;
	}

	/**
	 * Clone object.
	 * 
	 * @param reference
	 *            the reference
	 * @return the object
	 */
	public Object cloneObject(Object reference) {
		return cloneObject(reference, new CloneFilter(CloneFilter.SIMPLE));
	}

	/**
	 * Clone object.
	 * 
	 * @param reference
	 *            the reference
	 * @param filter
	 *            the filter
	 * @return the object
	 */
	public abstract Object cloneObject(Object reference, CloneFilter filter);
}
