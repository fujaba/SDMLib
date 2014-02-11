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
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.bytes.converter.ByteConverter;
import org.sdmlib.serialization.bytes.converter.ByteConverterHTTP;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BufferedBytes;
import org.sdmlib.serialization.interfaces.ByteItem;
/**
 * The Class ByteEntity.
 */

public class ByteEntity implements BaseEntity, ByteItem {
	/** The Constant BIT OF A BYTE. */
	public final static int BITOFBYTE = 8;
	public final static int TYPBYTE = 1;

	/** The Byte Typ. */
	protected byte typ;

	/** The values. */
	protected byte[] values;
	private boolean visible;

	/*
	 * @see de.uni.kassel.peermessage.BaseEntity#getNewArray()
	 */
	@Override
	public EntityList getNewArray() {
		return new ByteList();
	}

	/*
	 * @see de.uni.kassel.peermessage.BaseEntity#getNewObject()
	 */
	@Override
	public BaseEntity getNewObject() {
		return new ByteEntity();
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public byte[] getValue() {
		return this.values.clone();
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public ByteEntity withValue(byte typ, byte[] value) {
		this.typ = typ;
		this.values = value.clone();
		return this;
	}

	/**
	 * Byte to unsigned byte.
	 * 
	 * @param n
	 *            the Byte
	 * @return the Byte
	 */
	public byte byteToUnsignedByte(int n) {
		if (n < 128)
			return (byte) n;
		return (byte) (n - 256);
	}

	/*
	 * @see de.uni.kassel.peermessage.Entity#toString()
	 */
	@Override
	public String toString() {
		return toString(null);
	}

	public String toString(int indentFactor) {
		return toString(null);
	}

	/**
	 * Convert the bytes to a String
	 * 
	 * @param converter
	 *            Grammar
	 * @return converted bytes as String
	 */
	public String toString(ByteConverter converter) {
		return toString(converter, false);
	}

	/**
	 * Convert the bytes to a String
	 * 
	 * @param converter
	 *            Grammar
	 * @param dynamic
	 *            if byte is dynamic
	 * @return converted bytes as String
	 */
	public String toString(ByteConverter converter, boolean dynamic) {
		if (converter == null) {
			converter = new ByteConverterHTTP();
		}
		return converter.toString(this, dynamic);
	}

	/*
	 * @see de.uni.kassel.peermessage.Entity#toString(int, int)
	 */
	@Override
	public String toString(int indentFactor, int intent) {
		return toString(null);
	}

	/**
	 * Gets the bytes.
	 * 
	 * @return the bytes
	 */
	public void writeBytes(BufferedBytes buffer, boolean isDynamic, boolean last){
//		int len = calcLength(isDynamic);
		byte[] value = this.values;
		int valueLen=0;
		byte typ=getTyp();

		if (isDynamic && value != null) {
			BytesBuffer bb = new BytesBuffer();
			bb.withValue(value);
			if (typ == ByteIdMap.DATATYPE_SHORT) {
				short bufferValue = bb.getShort();
				if (bufferValue >= Byte.MIN_VALUE
						&& bufferValue <= Byte.MAX_VALUE) {
					typ = ByteIdMap.DATATYPE_BYTE;
					value = new byte[] { (byte) bufferValue };
				}
			} else if (typ == ByteIdMap.DATATYPE_INTEGER
					|| typ == ByteIdMap.DATATYPE_LONG) {
				int bufferValue = bb.getInt();
				if (bufferValue >= Byte.MIN_VALUE
						&& bufferValue <= Byte.MAX_VALUE) {
					typ = ByteIdMap.DATATYPE_BYTE;
					value = new byte[] { (byte) bufferValue };
				} else if (bufferValue >= Short.MIN_VALUE
						&& bufferValue <= Short.MAX_VALUE) {
					typ = ByteIdMap.DATATYPE_BYTE;
					BytesBuffer bbShort = BytesBuffer.allocate(Short.SIZE / BITOFBYTE);
					bbShort.put((short) bufferValue);
					bbShort.flip();
					value = bbShort.array();
				}
			}
		}
		if(value!=null){
			valueLen= value.length;
		}
		typ = ByteUtil.getTyp(typ, valueLen, last);
		
		ByteUtil.writeByteHeader(buffer, typ, valueLen);
		
		// SAVE Length
		if (valueLen>0) {
			buffer.put(value);
		}
	}
	
	public BufferedBytes getBytes(boolean isDynamic) {
		int len = calcLength(isDynamic);
		BufferedBytes buffer = ByteUtil.getBuffer(len);
		writeBytes(buffer, isDynamic, false);		
		buffer.flip();
		return buffer;
	}

	public boolean setValues(Object value) {
		byte typ = 0;
		BytesBuffer msgValue = new BytesBuffer();
		if (value == null) {
			typ = ByteIdMap.DATATYPE_NULL;
		}
		if (value instanceof Short) {
			typ = ByteIdMap.DATATYPE_SHORT;
			msgValue.withLength(Short.SIZE / BITOFBYTE);
			msgValue.put((Short) value);
		} else if (value instanceof Integer) {
			typ = ByteIdMap.DATATYPE_INTEGER;
			msgValue.withLength(Integer.SIZE / BITOFBYTE);
			msgValue.put((Integer) value);
		} else if (value instanceof Long) {
			typ = ByteIdMap.DATATYPE_LONG;
			msgValue.withLength(Long.SIZE / BITOFBYTE);
			msgValue.put((Long) value);
		} else if (value instanceof Float) {
			typ = ByteIdMap.DATATYPE_FLOAT;
			msgValue.withLength(Float.SIZE / BITOFBYTE);
			msgValue.put((Float) value);
		} else if (value instanceof Double) {
			typ = ByteIdMap.DATATYPE_DOUBLE;
			msgValue.withLength(Double.SIZE / BITOFBYTE);
			msgValue.put((Double) value);
		} else if (value instanceof Byte) {
			typ = ByteIdMap.DATATYPE_BYTE;
			msgValue.withLength(Byte.SIZE / BITOFBYTE);
			msgValue.put((Byte) value);
		} else if (value instanceof Character) {
			typ = ByteIdMap.DATATYPE_CHAR;
			msgValue.withLength(Character.SIZE / BITOFBYTE);
			msgValue.put((Character) value);
		} else if (value instanceof String) {
			typ = ByteIdMap.DATATYPE_STRING;
			String newValue = (String) value;
			msgValue.withLength(newValue.length());
			try {
				msgValue.put(newValue.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
			}
		} else if (value instanceof Date) {
			typ = ByteIdMap.DATATYPE_DATE;
			msgValue.withLength(Integer.SIZE / BITOFBYTE);
			Date newValue = (Date) value;
			msgValue.put((int) newValue.getTime());
		} else if (value instanceof Byte[] || value instanceof byte[]) {
			typ = ByteIdMap.DATATYPE_BYTEARRAY;
			byte[] newValue = (byte[]) value;
			msgValue.withLength(newValue.length);
			msgValue.put(newValue);
		}
		if (typ != 0) {
			this.typ = typ;
			// Check for group
			if (msgValue != null) {
				msgValue.flip();
				this.values = msgValue.array();
			}
			return true;
		}
		return false;
	}

	/**
	 * Gets the typ.
	 * 
	 * @return the typ
	 */
	public byte getTyp() {
		return this.typ;
	}

	/**
	 * calculate the length of value
	 * 
	 * @return the length
	 */
	public int calcLength(boolean isDynamic) {
		// Length calculate Sonderfaelle ermitteln
		if (isDynamic && this.values != null) {
			if (typ == ByteIdMap.DATATYPE_SHORT) {
				Short bufferValue = new BytesBuffer().withValue(values).getShort();
				if (bufferValue >= Byte.MIN_VALUE
						&& bufferValue <= Byte.MAX_VALUE) {
					return TYPBYTE + Byte.SIZE / BITOFBYTE;
				}
			} else if (typ == ByteIdMap.DATATYPE_INTEGER
					|| typ == ByteIdMap.DATATYPE_LONG) {
				Integer bufferValue = new BytesBuffer().withValue(values).getInt();
				if (bufferValue >= Byte.MIN_VALUE
						&& bufferValue <= Byte.MAX_VALUE) {
					return TYPBYTE + Byte.SIZE / BITOFBYTE;
				} else if (bufferValue >= Short.MIN_VALUE
						&& bufferValue <= Short.MAX_VALUE) {
					return TYPBYTE + Short.SIZE / BITOFBYTE;
				}
			}
		}
 		int len = TYPBYTE;

		if (this.values != null) {
			len += ByteUtil.getTypLen(typ, values.length);
			len += this.values.length;
		}
		return len;
	}

	public ByteEntity withVisible(boolean value) {
		this.visible = value;
		return this;
	}

	public boolean isVisible() {
		return visible;
	}
}
