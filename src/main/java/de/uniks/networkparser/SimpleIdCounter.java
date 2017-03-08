package de.uniks.networkparser;


import de.uniks.networkparser.interfaces.IdMapCounter;
/*
NetworkParser
The MIT License
Copyright (c) 2010-2016 Stefan Lindel https://github.com/fujaba/NetworkParser/

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
/**
 * The Class SimpleIdCounter.
 */

public class SimpleIdCounter implements IdMapCounter {
	/** The prefix id. */
	protected String prefixId = "J1";

	/** The number. */
	protected long number = 1;

	
	public IdMapCounter withNumber(long number) {
		this.number = number;
		return this;
	}

	private char splitter = '.';

//	/** The prio Object mostly a Timestamp or int value. */
//	private Object prio;
	

	/**
	 * Set the Session Prefix for a Peer
	 */
	@Override
	public IdMapCounter withSession(String value) {
		this.prefixId = value;
		return this;
	}

	/**
	 * Set the Session Prefix for a Peer
	 */
	@Override
	public String getSession() {
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
			return "";
		}
		String className = obj.getClass().getName();
		int pos = className.lastIndexOf(".");
		String shortClassName = className.substring(pos+1); 
//		char firstChar = className.charAt(className.lastIndexOf(".") + 1);
		if (this.prefixId != null) {
			key = this.prefixId + this.splitter + shortClassName + this.splitter + this.number;
//			+this.splitter+this.timeStamp;
		} else {
			key = "" + shortClassName + this.number;
		}
		this.number++;
		return key;
	}

	/**
	 * Read a Id from jsonString
	 */
	public void readId(String jsonId) {

		String key = null;

		if (prefixId != null) {
			String[] split = jsonId.split("\\" + this.splitter);

			key = split[split.length - 1];
		} else {
			key = jsonId;
		}

		if (key != null) {
			try {
				String oldNumber = key.substring(1);
				long oldInt = Long.parseLong(oldNumber);
				if (oldInt >= this.number) {
					this.number = oldInt + 1;
				}
			} catch (Exception e) {
				// this id does not end with a number, thus it is set by the
				// user
				// so we do not try to keep track of the highest number used so
				// far.
				// This means, do nothing
			}
		}
	}

	public char getSplitter() {
		return this.splitter;
	}

	public IdMapCounter withSplitter(char splitter) {
		this.splitter = splitter;
		return this;
	}

	@Override
	public IdMapCounter withTimeStamp(int newValue) {
		return null;
	}

//	/**
//	 * Gets the prio.
//	 *
//	 * @return the prio
//	 */
//	@Override
//	public Object getPrio() {
//		return this.prio;
//	}
//
//	/**
//	 * Sets the prio.
//	 *
//	 * @param prio		the new prio
//	 * @return 			Itself
//	 */
//	public SimpleIdCounter withPrio(Object prio) {
//		this.prio = prio;
//		return this;
//	}
}
