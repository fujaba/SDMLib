package org.sdmlib.serialization.event.creator;

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
import java.util.Date;
import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
/**
 * The Class DateCreator.
 */

public class DateCreator implements SendableEntityCreator, NoIndexCreator {
	/** The Constant VALUE. */
	public static final String VALUE = "value";

	/*
	 * return the Properties
	 */
	@Override
	public String[] getProperties() {
		return new String[] {VALUE};
	}

	/*
	 * Create new Instance of Date
	 */
	@Override
	public Object getSendableInstance(boolean reference) {
		return new Date();
	}

	/*
	 * Getter for java.util.Date
	 */
	@Override
	public Object getValue(Object entity, String attribute) {
		if (VALUE.equals(attribute)) {
			return Long.valueOf(((Date) entity).getTime());
		}
		return null;
	}

	/*
	 * Setter for java.util.Date
	 */
	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String typ) {
		if (VALUE.equals(attribute)) {
			((Date) entity).setTime((Long) value);
			return true;
		}
		return false;
	}
}
