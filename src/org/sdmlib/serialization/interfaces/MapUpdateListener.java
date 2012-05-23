package org.sdmlib.serialization.interfaces;
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
import org.sdmlib.serialization.json.JsonObject;


/**
 * The listener interface for receiving mapUpdate events.
 * The class that is interested in processing a mapUpdate
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addMapUpdateListener<code> method. When
 * the mapUpdate event occurs, that object's appropriate
 * method is invoked.
 *
 * @see MapUpdateEvent
 */
public interface MapUpdateListener {
	
	/**
	 * Send update msg.
	 *
	 * @param jsonObject the json object
	 * @return true, if successful
	 */
	public boolean sendUpdateMsg(JsonObject jsonObject);
}
