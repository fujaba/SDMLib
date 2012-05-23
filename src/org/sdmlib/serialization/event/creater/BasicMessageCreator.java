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
import org.sdmlib.serialization.event.BasicMessage;
import org.sdmlib.serialization.interfaces.ByteEntityCreator;

/**
 * The Class BasicMessageCreator.
 */
public class BasicMessageCreator implements ByteEntityCreator {
	
	/** The properties. */
	protected final String[] properties = new String[] { BasicMessage.PROPERTY_VALUE };

	/* 
	 * return the Properties
	 */
	public String[] getProperties() {
		return properties;
	}

	/* 
	 * Create new Instance of BasicMessage
	 */
	public Object getSendableInstance(boolean reference) {
		return new BasicMessage();
	}

	/* 
	 * Get the EventTyp of BasicMessage (0x42)
	 */
	public byte getEventTyp() {
		return 0x42;
	}

	/*
	 * Getter for BasicMessage
	 */
	public Object getValue(Object entity, String attribute) {
		return ((BasicMessage) entity).get(attribute);
	}

	/*
	 * Setter for BasicMessage
	 */
	public boolean setValue(Object entity, String attribute, Object value) {
		return ((BasicMessage) entity).set(attribute, value);
	}
}
