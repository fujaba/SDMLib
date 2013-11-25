package org.sdmlib.serialization.bytes.converter;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
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
