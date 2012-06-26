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

/**
 * The Class IdMapFilter.
 */
public class IdMapFilter {
	/** The Constant ALLDEEP. */
	public final static int ALLDEEP = -1;
	
	/** The Constant LASTDEEP. */
	public final static int LASTDEEP = 0;
	
	/** The Constant DEEPER. */
	public final static int DEEPER = -2;
	
	/** The deep. */
	protected int deep = ALLDEEP;

	/**
	 * Checks if is convertable.
	 *
	 * @param map the map
	 * @param entity the entity
	 * @param property the property
	 * @param value the value
	 * @return true, if is convertable
	 */
	public boolean isConvertable(IdMap map, Object entity, String property, Object value){
		if (getDeep() == LASTDEEP){
			return false;
		}
		return true;
	}

	public boolean isRegard(IdMap map, Object entity, String property, Object value){
		return true;
	}
	
	/**
	 * Gets the deep.
	 *
	 * @return the deep
	 */
	public int getDeep() {
		return deep;
	}
	
	/**
	 * Sets the deep.
	 *
	 * @param value the value
	 * @return the int
	 */
	public int setDeep(int value) {
		int oldValue = deep;
		if(value==DEEPER){
			if (deep != ALLDEEP) {
				deep = deep - 1;
			}
		}else{
			deep=value;
		}
		return oldValue;
	}
}
