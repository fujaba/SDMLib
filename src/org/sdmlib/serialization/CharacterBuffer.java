package org.sdmlib.serialization;

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
import org.sdmlib.serialization.interfaces.Buffer;
/**
 * Buffer of String for alternative for StringBuffer.
 *
 */

public class CharacterBuffer implements Buffer {
	/** The buffer. */
	private char[] buffer;

	/** The length. */
	private int length;

	/** The index. */
	private int position;

	/** The line. */
	private int line;

	/** The character. */
	private int character;

	@Override
	public int length() {
		return length;
	}

	@Override
	public char charAt(int index) {
		return buffer[index];
	}

	@Override
	public byte byteAt(int index) {
		return (byte) buffer[index];
	}

	@Override
	public String substring(int start, int len) {
		if (start + len > buffer.length) {
			len = buffer.length - start;
		}
		return new String(buffer, start, len);
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

	/**
	 * @param value String of Value
	 * @return the CharacterBuffer
	 */
	public CharacterBuffer withValue(String value) {
		this.buffer = value.toCharArray();
		this.length = buffer.length;
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

	@Override
	public String toText() {
		return new String(buffer);
	}

	@Override
	public byte[] toArray() {
		byte[] result = new byte[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			result[i] = (byte) buffer[i];
		}
		return result;
	}

	@Override
	public char getChar() {
		this.position++;
		if (this.position == this.buffer.length) {
			return 0;
		}
		char c = this.buffer[this.position];
		if (c == '\r') {
			this.line += 1;
			if (this.buffer[this.position] == '\n') {
				this.character = 1;
				this.position++;
				c = '\n';
			} else {
				this.character = 0;
			}
		} else if (c == '\n') {
			this.line += 1;
			this.character = 0;
		} else {
			this.character += 1;
		}
		return c;
	}
}
