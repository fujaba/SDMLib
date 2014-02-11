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

public class SumBSD extends Checksum {
	/** implemented from original GNU C source */
	public void update(int b) {
		super.update(b);
		value = (value >> 1) + ((value & 1) << 15);
		value += b & 0xFF;
		value &= 0xffff;
	}

	public int getOrder() {
		return 16;
	}
}
