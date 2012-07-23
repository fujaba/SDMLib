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
	 * Change IdMap to not give a ID and dont add UpdateListener
	 * @return boolean
	 */
	public boolean isTemporary(){
		return false;
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
