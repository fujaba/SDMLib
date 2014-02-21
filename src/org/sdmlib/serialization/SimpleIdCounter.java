package org.sdmlib.serialization;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or higher as soon they
 will be approved by the European Commission - subsequent
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
import org.sdmlib.serialization.interfaces.IdMapCounter;
/**
 * The Class SimpleIdCounter.
 */

public class SimpleIdCounter implements IdMapCounter {
	/** The prefix id. */
	protected String prefixId = "J1";

	/** The number. */
	protected long number = 1;

	private char splitter = '.';

	/** The prio Object mostly a Timestamp or int value. */
	private Object prio;

	/**
	 * Set the Session Prefix for a Peer
	 */
	public IdMapCounter withPrefixId(String value) {
		this.prefixId = value;
		return this;
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

	@Override
	public IdMapCounter withSplitter(char splitter) {
		this.splitter = splitter;
		return this;
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
	 * @param prio
	 *            the new prio
	 */
	public SimpleIdCounter withPrio(Object prio) {
		this.prio = prio;
		return this;
	}
}
