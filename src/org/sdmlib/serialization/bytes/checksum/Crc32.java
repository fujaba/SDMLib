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

public class Crc32 extends CRCTable {
	/** Make the table for a fast CRC. */
	public int[] getGenTable() {
		int[] crc_table = new int[256];
		for (int n = 0; n < 256; n++) {
			int c = n;
			for (int k = 8; --k >= 0;) {
				if ((c & 1) != 0)
					c = 0xedb88320 ^ (c >>> 1);
				else
					c = c >>> 1;
			}
			crc_table[n] = c;
		}
		return crc_table;
	}

	public void update(int bval) {
		super.update(bval);
		int c = (int) ~value;
		c = crc_table[(c ^ bval) & 0xff] ^ (c >>> 8);
		value = ~c;
	}

	@Override
	public int getPolynom() {
		return 0;
	}

	@Override
	public boolean isReflect() {
		return false;
	}

	@Override
	public int getOrder() {
		return 32;
	}
}
