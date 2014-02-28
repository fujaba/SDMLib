package org.sdmlib.serialization.event.creator;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
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
import org.sdmlib.serialization.event.BasicMessage;
import org.sdmlib.serialization.interfaces.SendableEntityCreatorByte;
/**
 * The Class BasicMessageCreator.
 */

public class BasicMessageCreator implements SendableEntityCreatorByte {
	/** The properties. */
	protected final String[] properties = new String[] { BasicMessage.PROPERTY_VALUE };

	/*
	 * return the Properties
	 */
	@Override
	public String[] getProperties() {
		return this.properties;
	}

	/*
	 * Create new Instance of BasicMessage
	 */
	@Override
	public Object getSendableInstance(boolean reference) {
		return new BasicMessage();
	}

	/*
	 * Get the EventTyp of BasicMessage (0x42)
	 */
	@Override
	public byte getEventTyp() {
		return 0x42;
	}

	/*
	 * Getter for BasicMessage
	 */
	@Override
	public Object getValue(Object entity, String attribute) {
		return ((BasicMessage) entity).get(attribute);
	}

	/*
	 * Setter for BasicMessage
	 */
	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String typ) {
		return ((BasicMessage) entity).set(attribute, value);
	}
}
