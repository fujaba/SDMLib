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
import java.util.HashMap;
/**
 * The Class CloneFilter.
 */

public class CloneFilter extends Filter {
	/** The Constant OBJECT. */
	public static final int OBJECT = 0;

	/** The Constant SIMPLE. */
	public static final int SIMPLE = 1;

	/** The Constant FULL. */
	public static final int FULL = 2;

	/** The typ. */
	private int typ;

	/** The assocs. */
	private HashMap<Object, Object> assocs = new HashMap<Object, Object>();

	/**
	 * Adds the object.
	 *
	 * @param reference
	 *            the reference
	 * @param newObject
	 *            the new object
	 * @return CloneFilter-Object
	 */
	public CloneFilter withObject(Object reference, Object newObject) {
		this.assocs.put(reference, newObject);
		return this;
	}

	/**
	 * Checks for object.
	 *
	 * @param objects
	 *            the objects
	 * @return true, if successful
	 */
	public boolean hasObject(Object objects) {
		return this.assocs.containsKey(objects);
	}

	/**
	 * Gets the object.
	 *
	 * @param objects
	 *            the objects
	 * @return the object
	 */
	public Object getObject(Object objects) {
		return this.assocs.get(objects);
	}

	/**
	 * Gets the typ.
	 *
	 * @return the typ
	 */
	public int getTyp() {
		return this.typ;
	}

	/**
	 * Sets the typ.
	 *
	 * @param value
	 *            the new typ
	 * @return CloneFilter-Object
	 */
	public CloneFilter withTyp(int value) {
		this.typ = value;
		return this;
	}
}
