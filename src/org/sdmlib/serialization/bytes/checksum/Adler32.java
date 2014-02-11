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
 * A class that can be used to compute the Adler32 of a data stream.
 * This implementation uses the class java.util.zip.Adler32 from the Java Standard API.
 */

public class Adler32 extends Checksum {
	private static final int BASE = 65521;

	public void update(int b) {
		super.update(b);
		int s1 = (int) value & 0xffff;
		int s2 = (int) value >>> 16;
		s1 = (s1 + (b & 0xFF)) % BASE;
		s2 = (s1 + s2) % BASE;

		value = (s2 << 16) + s1;
	}

	@Override
	public int getOrder() {
		return 32;
	}
}
