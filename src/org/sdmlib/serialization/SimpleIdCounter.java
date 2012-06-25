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
	
	/** 
	 * Set the Session Prefix for a Peer
	 */
	public void setPrefixId(String sessionId) {
		this.prefixId = sessionId;
	}

	/** 
	 * Get a new Id
	 */
	public String getId(Object obj) {
		String key;

		// new object generate key and add to tables
		// <session id>.<first char><running number>
		if (obj == null) {
			try {
				throw new Exception("NullPointer: " + obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String className = obj.getClass().getName();
		char firstChar = className.charAt(className.lastIndexOf(splitter) + 1);
		if (prefixId != null) {
			key = prefixId + splitter + firstChar + number;
		} else {
			key = "" + firstChar + number;
		}
		number++;
		return key;
	}

	/**
	 * Read a Id from jsonString
	 */
	public void readId(String jsonId) {
		// adjust number to be higher than read numbers
		String[] split = jsonId.split("\\"+splitter);

		if (split.length != 2) {
			throw new RuntimeException("jsonid " + jsonId
					+ " should have one "+splitter+" in its middle");
		}
		if (prefixId.equals(split[0])) {
			String oldNumber = split[1].substring(1);
			long oldInt = Long.parseLong(oldNumber);
			if (oldInt >= number) {
				number = oldInt + 1;
			}
		}
	}

	public char getSplitter() {
		return splitter;
	}

	public void setSplitter(char splitter) {
		this.splitter = splitter;
	}
}
