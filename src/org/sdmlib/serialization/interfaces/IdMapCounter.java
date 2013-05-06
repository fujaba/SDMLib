package org.sdmlib.serialization.interfaces;

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

/**
 * The Interface IdMapCounter.
 */
public interface IdMapCounter {
/**
	 * Sets the prefix for The Id of Elements
	 * 
	 * @param sessionId
	 *            the new prefix id
	 */
	public void setPrefixId(String sessionId);

	/**
	 * @return the current sessionid
	 */
	public String getPrefixId();

	/**
	 * Sets the splitter for The session id
	 * 
	 * @param Character
	 *            the new splitter character for the session id
	 */
	public void setSplitter(char splitter);

	/**
	 * @return the current splitterString
	 */
	public char getSplitter();

	/**
	 * Gets the id.
	 * 
	 * @param obj
	 *            the obj
	 * @return the id
	 */
	public String getId(Object obj);

	/**
	 * Read id.
	 * 
	 * @param id
	 *            the last id from Message
	 */
	public void readId(String id);

	/**
	 * @return if Id must seriasable
	 */
	public boolean isId();

	/**
	 * @return the Prio Object for checking errors
	 */
	public Object getPrio();

	/**
	 * @param Enable
	 *            or disable the ID generating
	 */
	public void enableId(boolean value);
}
