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
 * The Class BaseEntity.
 */
/**
 * @author Stefan
 *
 */
/**
 * @author Stefan
 *
 */
public interface JSIMEntity {
public static final String CRLF = "\r\n";

	public BaseEntityList getNewArray();

	public JSIMEntity getNewObject();

	/**
	 * Make a Text of this Entity. For compactness, no whitespace is added. If
	 * this would not result in a syntactically correct Text, then null will be
	 * returned instead.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 * 
	 * @return a printable, displayable, portable, transmittable representation
	 *         of the object, beginning with <code>{</code>&nbsp;<small>(left
	 *         brace)</small> and ending with <code>}</code>&nbsp;<small>(right
	 *         brace)</small>.
	 */
	@Override
	public String toString();

	/**
	 * Make a prettyprinted Text of this Entity.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 * 
	 * @param indentFactor
	 *            The number of spaces to add to each level of indentation.
	 * @return a printable, displayable, portable, transmittable representation
	 *         of the object, beginning with <code>{</code>&nbsp;<small>(left
	 *         brace)</small> and ending with <code>}</code>&nbsp;<small>(right
	 *         brace)</small>.
	 */
	public String toString(int indentFactor);

	public String toString(int indentFactor, int intent);

	public void setVisible(boolean value);

	public boolean isVisible();
}
