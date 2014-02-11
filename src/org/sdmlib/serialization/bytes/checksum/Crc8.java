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
 * A class that can be used to compute the Crc8 of a data stream.
 */

public class Crc8 extends CRCTable {
	// CRC-8, poly = x^8 + x^2 + x^1 + 1, init = 0
	// 1 0000 0111
	// 0111 0000 1
	public int getPolynom() {
		return 0x107;
	}

	/**
	 * Update the CRC value with a byte data.
	 * 
	 * @param data
	 *            The byte data
	 * @param crc
	 *            The starting CRC value
	 * @return The updated CRC value
	 */
	public void update(int data) {
		super.update(data);
		value = crc_table[((int) value ^ (byte) data) & 0xFF];
	}

	@Override
	public boolean isReflect() {
		return false;
	}

	@Override
	public int getOrder() {
		return 8;
	}
}
