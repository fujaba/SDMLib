package org.sdmlib.serialization.interfaces;

/*
 NetworkParser
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
	 * @param jsonObject
	 *            the json object
	 * @return true, if successful
	 */
	public boolean sendUpdateMsg(Object target, String property, Object oldObj, Object newObject,
			JsonObject jsonObject);

	public boolean isReadMessages(String key, Object element, JsonObject props, String type);
	public boolean readMessages(String key, Object element, Object value, JsonObject props, String type);

	public boolean skipCollision(Object masterObj, String key, Object value,
			JsonObject removeJson, JsonObject updateJson);
}
