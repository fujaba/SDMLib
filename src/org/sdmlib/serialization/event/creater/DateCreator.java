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
import java.util.Date;

import org.sdmlib.serialization.interfaces.NoIndexCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

/**
 * The Class DateCreator.
 */
public class DateCreator implements SendableEntityCreator, NoIndexCreator{
	
	/** The Constant VALUE. */
	public static final String VALUE="value";
	
	/*
	 * return the Properties
	 */
	public String[] getProperties() {
		return new String[]{VALUE};
	}

	/*
	 * Create new Instance of Date
	 */
	public Object getSendableInstance(boolean reference) {
		return new Date();
	}

	/*
	 * Getter for java.util.Date
	 */
	public Object getValue(Object entity, String attribute) {
		if(VALUE.equals(attribute)){
			return ((Date)entity).getTime();
		}
		return null;
	}

	/*
	 * Setter for java.util.Date
	 */
	public boolean setValue(Object entity, String attribute, Object value) {
		if(VALUE.equals(attribute)){
			((Date)entity).setTime((Long) value);;
			return true;
		}
		return false;
	}
}
