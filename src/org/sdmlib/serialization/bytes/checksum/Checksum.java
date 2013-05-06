package org.sdmlib.serialization.bytes.checksum;

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
 * Checksum Abstract Class
 */
public abstract class Checksum {
/** The crc data checksum so far. */
	protected long value;
	protected long length;

	/**
	 * Creates an AbstractChecksum.
	 */
	public Checksum() {
		reset();
	}

	/**
	 * Resets the checksum to its initial value for further use.
	 */
	public void reset() {
		value = 0;
		length = 0;
	}

	/**
	 * Updates the checksum with the specified byte.
	 */
	public void update(int b) {
		length++;
	}

	/**
	 * Updates the checksum with the specified byte.
	 */
	public void update(byte b) {
		update((int) (b & 0xFF));
	}

	/**
	 * Updates the current checksum with the specified array of bytes.
	 * 
	 * @param bytes
	 *            the byte array to update the checksum with
	 * @param offset
	 *            the start offset of the data
	 * @param length
	 *            the number of bytes to use for the update
	 */
	public void update(byte[] bytes, int offset, int length) {
		for (int i = offset; i < length + offset; i++) {
			update(bytes[i]);
		}
	}

	/**
	 * Updates the current checksum with the specified array of bytes.
	 */
	public void update(byte[] bytes) {
		update(bytes, 0, bytes.length);
	}

	/**
	 * Returns the value of the checksum.
	 * 
	 * @see #getByteArray()
	 */
	public long getValue() {
		int len = getOrder() / 8;
		int max = 1;
		for (int i = 0; i < len; i++) {
			max *= 256;
		}
		max--;
		return value & max;
	}

	/**
	 * Returns the length of the processed bytes.
	 */
	public long getLength() {
		return length;
	}

	/**
	 * Returns the result of the computation as byte array.
	 */
	public byte[] getByteArray() {
		long value = getValue();

		int maxlen = Integer.SIZE / 8;
		byte[] test = new byte[maxlen];
		int count = 0;
		while (value > 0) {
			test[maxlen - 1 - count++] = (byte) (value % 256);
			value = value >> 8; // um 1 Byte shiften
		}
		if (count == 0) {
			return new byte[] { 0x00 };
		}
		byte[] result = new byte[count];
		for (int z = 0; z < count; z++) {
			result[z] = test[(maxlen - count) + z];
		}
		return result;
	}

	public abstract int getOrder();
}
