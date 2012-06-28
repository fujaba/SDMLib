package org.sdmlib.serialization;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
