package org.sdmlib.serialization.event;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or - as soon they
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
import org.sdmlib.serialization.interfaces.PeerMessage;
/**
 * The Class BasicMessage.
 */

public class BasicMessage implements PeerMessage {
	/** The Constant PROPERTY_VALUE. */
	public static final String PROPERTY_VALUE = "value";

	/** The value. */
	private String value;

	/*
	 * Generic Getter for Attribute
	 */
	@Override
	public Object get(String attrName) {
		String attribute;
		int pos = attrName.indexOf(".");
		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		} else {
			attribute = attrName;
		}
		if (attribute.equalsIgnoreCase(PROPERTY_VALUE)) {
			return getValue();
		}
		return null;
	}

	/*
	 * Generic Setter for Attribute
	 */
	@Override
	public boolean set(String attribute, Object value) {
		if (attribute.equalsIgnoreCase(PROPERTY_VALUE)) {
			withValue((String) value);
			return true;
		}
		return false;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 * @return 
	 */
	public BasicMessage withValue(String value) {
		this.value = value;
		return this;
	}
}
