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

public class ByteConverterBinary extends ByteConverter {
	public static String toString(byte value) {
		ByteConverterBinary converter = new ByteConverterBinary();
		return converter.toString(new byte[] { value }, 1);
	}

	public static String toString(int value) {
		ByteConverterBinary converter = new ByteConverterBinary();
		return converter.toString(new byte[] { (byte) value }, 1);
	}

	/**
	 * To Binary string.
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the string
	 */
	@Override
	public String toString(byte[] values, int size) {
		StringBuilder sb = new StringBuilder();
		for (int z = 0; z < size; z++) {
			int number = values[z];
			char[] bits = new char[] { '0', '0', '0', '0', '0', '0', '0', '0' };
			int i = 7;
			if (number < 0) {
				number += 256;
			}
			while (number != 0) {
				bits[i] = (char) (48 + (number % 2));
				number = (byte) (number / 2);
				i--;
			}
			sb.append(new String(bits));
		}
		return sb.toString();
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
		byte[] out = new byte[value.length() / 8];

		int n = value.length();

		for (int i = 0; i < n;) {
			int charText = 0;
			for (int z = 0; z < 8; z++) {
				charText = charText << ((byte) (value.charAt(i++) - 48));
			}
			// now just shift the high order nibble and add them together
			out[i / 8] = (byte) charText;
		}
		return out;
	}

}
