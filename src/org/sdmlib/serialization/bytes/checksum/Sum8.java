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

public class Sum8 extends Checksum {
	public void update(int b) {
		super.update(b);
		value += b & 0xFF;
	}

	@Override
	public int getOrder() {
		return 8;
	}
}

/*
 * Testvectors from the PC Magazin 06/1996:
 * 
 * decimal: 36 211 163 4 109 192 58 247 47 92 => 135 hex: 24 D3 A3 04 6D C0 3A
 * F7 2F 5C => 87
 */
