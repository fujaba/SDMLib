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

import org.sdmlib.serialization.interfaces.IdMapCounter;


/**
 * The Class SimpleIdCounter.
 */
public class SimpleIdCounter implements IdMapCounter{
	/** The prefix id. */
	protected String prefixId="J1";
	
	/** The number. */
	protected long number = 1;
	
	private char splitter='.';
	
	/** The is id. */
	protected boolean isId = true;
	
	/** The prio Object mostly a Timestamp or int value. */
	private Object prio;
	
	/** 
	 * Set the Session Prefix for a Peer
	 */
	public void setPrefixId(String value) {
		this.prefixId = value;
	}

	/** 
	 * Set the Session Prefix for a Peer
	 */
	public String getPrefixId() {
		return this.prefixId;
	}

	/** 
	 * Get a new Id
	 */
	@Override
	public String getId(Object obj) {
		String key;

		// new object generate key and add to tables
		// <session id>.<first char><running number>
		if (obj == null) {
			Exception e=new Exception("NullPointer: " + obj);
			e.printStackTrace();
			return "";
		}
		String className = obj.getClass().getName();
		char firstChar = className.charAt(className.lastIndexOf(".") + 1);
		if (this.prefixId != null) {
			key = this.prefixId + this.splitter + firstChar + this.number;
		} else {
			key = "" + firstChar + this.number;
		}
		this.number++;
		return key;
	}

	/**
	 * Read a Id from jsonString
	 */
	@Override
	public void readId(String jsonId) {
		// adjust number to be higher than read numbers
		String key=null;
		if(prefixId!=null){
			String[] split = jsonId.split("\\"+this.splitter);
			if (split.length != 2) {
				throw new RuntimeException("jsonid " + jsonId
						+ " should have one "+this.splitter+" in its middle");
			}
			if (this.prefixId.equals(split[0])) {
				key=split[1];
			}
		}else{
			key=jsonId;
		}
		if(key!=null){
			String oldNumber = key.substring(1);
			long oldInt = Long.parseLong(oldNumber);
			if (oldInt >= this.number) {
				this.number = oldInt + 1;
			}
		}
	}

	public char getSplitter() {
		return this.splitter;
	}

	@Override
	public void setSplitter(char splitter) {
		this.splitter = splitter;
	}
	
	/**
	 * Gets the prio.
	 *
	 * @return the prio
	 */
	@Override
	public Object getPrio() {
		return this.prio;
	}

	/**
	 * Sets the prio.
	 *
	 * @param prio the new prio
	 */
	public void setPrio(Object prio) {
		this.prio = prio;
	}
	
	/**
	 * Checks if is serialisable id.
	 *
	 * @return true, if is id
	 */
	@Override
	public boolean isId() {
		return this.isId;
	}

	@Override
	public void enableId(boolean value) {
		this.isId=value;
	}
}
