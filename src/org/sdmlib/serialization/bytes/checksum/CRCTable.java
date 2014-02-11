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

public abstract class CRCTable extends CRC{
	/** The fast CRC table. Computed once when the CRC32 class is loaded. */
	protected int[] crc_table = getGenTable();

	/** Make the table for a fast CRC. */
	public int[] getGenTable() {
		int[] result = new int[256];

		int order = getOrder();
		long topBit = (long) 1 << (order - 1);
		long widthMask = (((1 << (order - 1)) - 1) << 1) | 1;
		int polynom = getPolynom();
		boolean isReflect = isReflect();

		for (int i = 0; i < 256; ++i) {
			result[i] = i;
			if (isReflect) {
				result[i] = Reflect(i, 8);
			}
			result[i] = result[i] << (order - 8);
			for (int j = 0; j < 8; ++j) {
				if ((result[i] & topBit) != 0) {
					result[i] = (result[i] << 1) ^ polynom;
				} else {
					result[i] <<= 1;
				}
			}
			if (isReflect) {
				result[i] = Reflect(result[i], order);
			}
			result[i] &= widthMask;
		}
		return result;
	}

	// / <summary>Reflects the lower bits of the value provided.</summary>
	// / <param name="data">The value to reflect.</param>
	// / <param name="numBits">The number of bits to reflect.</param>
	// / <returns>The reflected value.</returns>
	static private int Reflect(int data, int numBits) {
		int temp = data;

		for (int i = 0; i < numBits; i++) {
			long bitMask = (long) 1 << ((numBits - 1) - i);

			if ((temp & (long) 1) != 0) {
				data |= bitMask;
			} else {
				data &= ~bitMask;
			}

			temp >>= 1;
		}
		return data;
	}
}
