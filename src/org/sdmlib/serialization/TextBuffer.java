package org.sdmlib.serialization;

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
import org.sdmlib.serialization.interfaces.Buffer;
/**
 * Buffer of String for alternative for StringBuffer.
 *
 */

public abstract class TextBuffer implements Buffer {
	/** The length. */
	protected int length;

	/** The index. */
	protected int position;

	/** The line. */
	protected int line;

	/** The character. */
	protected int character;

	@Override
	public int length() {
		return length;
	}

	@Override
	public Buffer withPosition(int value) {
		this.position = value;
		return this;
	}

	@Override
	public Buffer withLength(int value) {
		this.length = value;
		return this;
	}

	@Override
	public int position() {
		return position;
	}

	@Override
	public void back() {
		this.position -= 1;
		this.character -= 1;
	}

	@Override
	public boolean isEnd() {
		return length <= this.position;
	}

	@Override
	public int remaining() {
		return length - position;
	}

	@Override
	public String toString() {
		return " at " + this.position + " [character " + this.character + " line "
				+ this.line + "]";
	}
}
