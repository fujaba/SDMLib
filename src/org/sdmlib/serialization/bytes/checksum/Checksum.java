package org.sdmlib.serialization.bytes.checksum;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or – as soon they
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
