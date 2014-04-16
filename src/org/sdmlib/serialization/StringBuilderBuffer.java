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
/**
 * Buffer of String for alternative for StringBuffer.
 *
 */

public class StringBuilderBuffer extends TextBuffer {
	/** The buffer. */
	private StringBuilder buffer;

	@Override
	public char charAt(int index) {
		return buffer.charAt(index);
	}

	@Override
	public byte byteAt(int index) {
		return (byte) buffer.charAt(index);
	}

	@Override
	public String substring(int start, int len) {
		if (start + len > buffer.length()) {
			len = buffer.length() - start;
		}
		return buffer.substring(start, start+len);
	}

	/**
	 * @param value String of Value
	 * @return the CharacterBuffer
	 */
	public StringBuilderBuffer withValue(StringBuilder value) {
		this.buffer = value;
		this.length = buffer.length();
		return this;
	}

	@Override
	public String toText() {
		return new String(buffer);
	}

	@Override
	public byte[] toArray() {
		byte[] result = new byte[buffer.length()];
		for (int i = 0; i < buffer.length(); i++) {
			result[i] = (byte) buffer.charAt(i);
		}
		return result;
	}

	@Override
	public char getChar() {
		this.position++;
		if (this.position == this.buffer.length()) {
			return 0;
		}
		char c = this.buffer.charAt(this.position);
		if (c == '\r') {
			this.line += 1;
			if (this.buffer.charAt(this.position) == '\n') {
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

   @Override
   public boolean isCache()
   {
      return true;
   }
}
