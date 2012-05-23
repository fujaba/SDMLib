package org.sdmlib.serialization.event.creater;
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

import org.sdmlib.serialization.event.ByteMessage;
import org.sdmlib.serialization.interfaces.ByteEntityCreator;

/**
 * The Class ByteMessageCreator.
 */
public class ByteMessageCreator implements ByteEntityCreator {
	
	/** The properties. */
	private final String[] properties = new String[] { ByteMessage.PROPERTY_VALUE };

	/*
	 * return the Properties
	 */
	public String[] getProperties() {
		return properties;
	}

	/* 
	 * Create new Instance of ByteMessage
	 */
	public Object getSendableInstance(boolean reference) {
		return new ByteMessage();
	}

	/*
	 * Get the EventTyp of BasicMessage (0x01)
	 */
	public byte getEventTyp() {
		return 0x01;
	}

	/* 
	 * Getter for ByteMessage
	 */
	public Object getValue(Object entity, String attribute) {
		return ((ByteMessage) entity).get(attribute);
	}

	/* 
	 * Setter for ByteMessage
	 */
	public boolean setValue(Object entity, String attribute, Object value) {
		return ((ByteMessage) entity).set(attribute, value);
	}
}
