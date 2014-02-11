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
import org.sdmlib.serialization.bytes.ByteIdMap;
import org.sdmlib.serialization.bytes.BytesBuffer;

public class ByteConverterHTTP extends ByteConverter {
	@Override
	public String toString(byte[] values, int size) {
		StringBuilder returnValue = new StringBuilder();

		if (values != null) {
			for (int i = 0; i < size; i++) {
				byte value = values[i];
				if (value <= 32 || value == 127) {
					returnValue.append(ByteIdMap.SPLITTER);
					returnValue.append((char) (value + ByteIdMap.SPLITTER + 1));
				} else {
					returnValue.append((char) value);
				}
			}
		}
		return returnValue.toString();
	}

	/**
	 * Decode http.
	 * 
	 * @param bytes
	 *            the bytes
	 * @return the object
	 */
	@Override
	public byte[] decode(String value) {
		int len = value.length();
		BytesBuffer buffer=BytesBuffer.allocate(len);
		for (int i = 0; i < len; i++) {
			int c = value.charAt(i);
			if (c == ByteIdMap.SPLITTER) {
				c = value.charAt(++i);
				buffer.put((byte) (c - ByteIdMap.SPLITTER - 1));
			} else {
				buffer.put((byte) c);
			}
		}
		buffer.flip();
		return buffer.getValue(buffer.length());
	}
}
