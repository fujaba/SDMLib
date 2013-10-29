package org.sdmlib.serialization;

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
