package org.sdmlib.serialization.bytes;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.sdmlib.serialization.AbstractMap;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.bytes.creator.BitEntityCreator;
import org.sdmlib.serialization.interfaces.BufferedBytes;

public class ByteSimpleMap extends AbstractMap {
	public Object decode(BufferedBytes buffer, BitEntityCreator creator) {
		HashMap<String, Object> values = new HashMap<String, Object>();
		BitEntity[] bitProperties = creator.getBitProperties();
		Object newInstance = creator.getSendableInstance(false);
		for (BitEntity entity : bitProperties) {
			Object element = getEntity(buffer, entity, values);
			if (element != null) {
				creator.setValue(newInstance, entity.getPropertyName(),
						element, IdMap.NEW);
			}
		}
		return newInstance;
	}

	public Object getEntity(BufferedBytes buffer, BitEntity entry,
			HashMap<String, Object> values) {
		if (entry.size() < 1) {
			// Reference or Value
			if (entry.isTyp(BitEntity.BIT_REFERENCE)) {
				String propertyName = entry.getPropertyName();
				if (values.containsKey(propertyName)) {
					return values.get(propertyName);
				}
			} else if (entry.isTyp(BitEntity.BIT_BYTE, BitEntity.BIT_NUMBER,
					BitEntity.BIT_STRING)) {
				// Value
				return entry.getPropertyName();
			}
		}
		// Wert ermitteln

		// Init the Values
		ArrayList<BufferedBytes> results = new ArrayList<BufferedBytes>();
		ArrayList<Integer> resultsLength = new ArrayList<Integer>();

		for (Iterator<BitValue> i = entry.valueIterator(); i.hasNext();) {
			BitValue bitValue = i.next();

			int orientationSource = bitValue.getOrientation();
			int orientationTarget = entry.getOrientation();

			int temp = Integer.parseInt(""
					+ getEntity(buffer, bitValue.getStart(), values));
			int posOfByte = temp / 8;
			int posOfBit = (8 - ((temp + 1) % 8)) % 8;

			int length = Integer.parseInt(""
					+ getEntity(buffer, bitValue.getLen(), values));
			int noOfByte = length / 8;
			if (length % 8 > 0) {
				noOfByte++;
			}

			resultsLength.add(length);
			BytesBuffer result = BytesBuffer.allocate(noOfByte);

			int theByte = buffer.byteAt(posOfByte);
			if (theByte < 0) {
				theByte += 256;
			}

			int resultPos = 0;
			int number = 0;
			int sourceBit = (length < 8 - resultPos) ? length : 8 - resultPos;

			theByte = theByte >> (posOfBit - sourceBit + 1);
			while (length > 0) {
				sourceBit = (length < 8 - resultPos) ? length : 8 - resultPos;
				int sourceBits = (theByte & (0xff >> (8 - sourceBit)));

				if (orientationTarget > 0) {
					number = (number << (sourceBit));
					if (orientationSource > 0)
						// Source Target
						number += sourceBits;
					else {
						// Bits vertauschen
						for (int z = sourceBit; z > 0; z--) {
							number += sourceBits
									& (0x1 << sourceBit) << (sourceBit - z);
						}
					}
				} else {
					if (orientationSource > 0)
						// Source Target
						number += sourceBits << sourceBit;
					else {
						// Bits vertauschen
						for (int z = sourceBit; z > 0; z--) {
							number += sourceBits
									& (0x1 << sourceBit) << (sourceBit - z);
						}
					}
				}

				theByte = (byte) (theByte >> (sourceBit));
				resultPos += sourceBit;
				length -= sourceBit;
				if (resultPos == 8) {
					result.put((byte) number);
					resultPos = 0;
					number = 0;
					if (length > 0) {
						theByte = buffer.byteAt(posOfByte);
						if (theByte < 0) {
							theByte += 256;
						}
					}
				}
			}
			if (resultPos > 0) {
				result.put((byte) number);
			}

			// Save one Result to List
			result.flip();
			results.add(result);
		}

		// Merge all Results to one
		int length = 0;
		for (Integer item : resultsLength) {
			length += item;
		}
		int number = length / 8 + ((length % 8 > 0) ? 1 : 0);

		BytesBuffer result = new BytesBuffer();
		result.withLength(number);

		int resultPos = 0;
		number = 0;
		for (int i = 0; i < results.size(); i++) {
			BufferedBytes source = results.get(i);
			length = resultsLength.get(i);
			while (length > 0) {
				byte theByte = source.getByte();
				int sourceBit = (length < 8 - resultPos) ? length
						: 8 - resultPos;
				number = (number << (sourceBit))
						+ (theByte & (0xff >> (8 - sourceBit)));
				theByte = (byte) (theByte >> (sourceBit));
				resultPos += sourceBit;
				length -= sourceBit;
				if (resultPos == 8) {
					result.put((byte) number);
					resultPos = 0;
					number = 0;
					if (length > 0) {
						theByte = source.getByte();
					}
				}
			}
		}
		if (resultPos > 0) {
			result.put((byte) number);
		}

		result.flip();

		// Set the Typ
		Object element = null;

		if (entry.getTyp().equals(BitEntity.BIT_BYTE)) {
			byte[] array = result.array();
			if (array.length == 1) {
				element = Byte.valueOf(array[0]);
			} else {
				Byte[] item = new Byte[array.length];
				for (int i = 0; i < array.length; i++) {
					item[i] = array[i];
				}
				element = item;
			}
		} else if (entry.getTyp().equals(BitEntity.BIT_NUMBER)) {
			if (result.length() == Byte.SIZE / ByteEntity.BITOFBYTE) {
				element = result.getByte();
			} else if (result.length() == Short.SIZE / ByteEntity.BITOFBYTE) {
				element = result.getShort();
			} else if (result.length() == Integer.SIZE / ByteEntity.BITOFBYTE) {
				element = result.getInt();
			} else if (result.length() == Long.SIZE / ByteEntity.BITOFBYTE) {
				element = result.getLong();
			} else if (result.length() == Float.SIZE / ByteEntity.BITOFBYTE) {
				element = result.getFloat();
			} else if (result.length() == Double.SIZE / ByteEntity.BITOFBYTE) {
				element = result.getDouble();
			} else {
				element = result.getInt();
			}
		} else if (entry.getTyp().equals(BitEntity.BIT_STRING)) {
			result.flip();
			element = String.valueOf(result.array());

		}
		values.put(entry.getPropertyName(), element);
		return element;
	}
}
