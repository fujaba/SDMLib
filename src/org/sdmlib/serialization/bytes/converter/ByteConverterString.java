package org.sdmlib.serialization.bytes.converter;

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

public class ByteConverterString extends ByteConverter {
	/**
	 * To simple string.
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the string
	 */
	@Override
	public String toString(byte[] values, int size) {
		StringBuilder returnValue = new StringBuilder(size);
		if (values != null) {
			for (int i = 0; i < size; i++) {
				returnValue.append((char) values[i]);
			}
		}
		return returnValue.toString();
	}

	/**
	 * To byte string.
	 * 
	 * @param hexString
	 *            the hex string
	 * @return the byte[]
	 */
	@Override
	public byte[] decode(String value) {
		byte[] out = new byte[value.length()];
		int n = value.length();

		for (int i = 0; i < n; i++) {
			out[i] = (byte) value.charAt(i);
		}
		return out;
	}

}
