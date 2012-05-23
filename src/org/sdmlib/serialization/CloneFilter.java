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

import java.util.HashMap;

/**
 * The Class CloneFilter.
 */
public class CloneFilter extends IdMapFilter{
	
	/** The Constant OBJECT. */
	public static final int OBJECT=0;
	
	/** The Constant SIMPLE. */
	public static final int SIMPLE=1;
	
	/** The Constant FULL. */
	public static final int FULL=2;
	
	/** The typ. */
	private int typ;
	
	/** The assocs. */
	private HashMap<Object, Object> assocs=new HashMap<Object, Object>();
	
	/**
	 * Instantiates a new clone filter.
	 */
	public CloneFilter(){
		
	}
	
	/**
	 * Instantiates a new clone filter.
	 *
	 * @param typ the typ
	 */
	public CloneFilter(int typ){
		setTyp(typ);
	}
	
	/* (non-Javadoc)
	 * @see de.uni.kassel.peermessage.IdMapFilter#isConvertable(de.uni.kassel.peermessage.IdMap, java.lang.Object, java.lang.String, java.lang.Object)
	 */
	public boolean isConvertable(IdMap map, Object entity, String property,
			Object value) {
		if (!super.isConvertable(map, entity, property, value)){
			return false;
		}
		return true;
	}

	/**
	 * Adds the object.
	 *
	 * @param reference the reference
	 * @param newObject the new object
	 */
	public void addObject(Object reference, Object newObject){
		this.assocs.put(reference, newObject);
	}
	
	/**
	 * Checks for object.
	 *
	 * @param objects the objects
	 * @return true, if successful
	 */
	public boolean hasObject(Object objects){
		return assocs.containsKey(objects);
	}
	
	/**
	 * Gets the object.
	 *
	 * @param objects the objects
	 * @return the object
	 */
	public Object getObject(Object objects){
		return assocs.get(objects);
	}
	
	/**
	 * Gets the typ.
	 *
	 * @return the typ
	 */
	public int getTyp() {
		return typ;
	}

	/**
	 * Sets the typ.
	 *
	 * @param typ the new typ
	 */
	public void setTyp(int typ) {
		this.typ = typ;
		if(typ==OBJECT){
			this.deep=LASTDEEP;
		}
	}
}
