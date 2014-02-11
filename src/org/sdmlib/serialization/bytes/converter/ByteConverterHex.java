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

public class ByteConverterHex extends ByteConverter {
	/**
	 * To hex string.
	 * 
	 * @param bytes the bytes
	 * @return the string
	 */
	@Override
	public String toString(byte[] values, int size) {
		String hexVal = "0123456789ABCDEF";

		StringBuilder returnValue = new StringBuilder(size << 1 );
		if (values != null) {
			for (int i = 0; i < size; i++) {
				int value = values[i];
				if (value < 0) {
					value += 256;
				}
				returnValue.append("" + hexVal.charAt(value / 16)
						+ hexVal.charAt(value % 16));
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
		String hexVal = "0123456789ABCDEF";
		byte[] out = new byte[value.length() / 2];

		int n = value.length();

		for (int i = 0; i < n; i += 2) {
			// make a bit representation in an int of the hex value
			int hn = hexVal.indexOf(value.charAt(i));
			int ln = hexVal.indexOf(value.charAt(i + 1));

			// now just shift the high order nibble and add them together
			out[i / 2] = (byte) ((hn << 4) | ln);
		}
		return out;
	}

}
