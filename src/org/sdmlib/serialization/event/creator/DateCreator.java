package org.sdmlib.serialization.event.creator;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
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

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
			return new Long(((Date) entity).getTime());
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
