package org.sdmlib.serialization.event;
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
import org.sdmlib.serialization.interfaces.PeerMessage;

/**
 * The Class ByteMessage.
 */
public class ByteMessage implements PeerMessage {
	
	/** The Constant PROPERTY_VALUE. */
	public static final String PROPERTY_VALUE = "value";
	
	/** The value. */
	private byte[] value = new byte[] {};

	/**
	 * Instantiates a new byte message.
	 */
	public ByteMessage() {
	}

	/**
	 * Instantiates a new byte message.
	 *
	 * @param message the message
	 */
	public ByteMessage(byte[] message) {
		setValue(message);
	}

	/*
	 * Generic Getter for Attributes
	 */
	public Object get(String attrName) {
		String attribute;
		int pos = attrName.indexOf(".");
		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		} else {
			attribute = attrName;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_VALUE)) {
			return value;
		}
		return null;
	}

	/*
	 * Generic Setter for Attributes
	 */
	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_VALUE)) {
			setValue((byte[]) value);
			return true;
		}
		return false;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public byte[] getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(byte[] value) {
		this.value = value;
	}
}
