package org.sdmlib.serialization.bytes;

/*
 Json Id Serialisierung Map
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
import java.nio.ByteBuffer;
import java.util.Date;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.interfaces.ByteItem;
import org.sdmlib.serialization.interfaces.JSIMEntity;
/**
 * The Class ByteEntity.
 */

public class ByteEntity implements JSIMEntity, ByteItem {
	/** The Constant BIT OF A BYTE. */
	public final static int BITOFBYTE = 8;
	public final static int TYPBYTE = 1;

	/** The Byte Typ. */
	protected byte typ;

	/** The values. */
	protected byte[] values;
	private boolean visible;

	/**
	 * Instantiates a new byte entity.
	 */
	public ByteEntity() {

	}

	/**
	 * Instantiates a new byte entity.
	 * 
	 * @param typ
	 *            the typ
	 * @param value
	 *            the value
	 */
	public ByteEntity(byte typ, byte[] value) {
		this.setValue(typ, value);
	}

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
	public JSIMEntity getNewObject() {
		return new ByteEntity();
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public byte[] getValue() {
		return this.values;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(byte typ, byte[] value) {
		this.typ = typ;
		this.values = value;
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
	public ByteBuffer getBytes(boolean isDynamic) {
		int len = calcLength(isDynamic);
		byte typ = getTyp();
		byte[] value = this.values;

		if (isDynamic && value != null) {
			ByteBuffer bb = ByteBuffer.wrap(value);
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
					ByteBuffer buffer = ByteBuffer.allocate(Short.SIZE
							/ BITOFBYTE);
					buffer.putShort((short) bufferValue);
					buffer.flip();
					value = buffer.array();
				}
			}
		}
		ByteBuffer buffer = ByteUtil.getBuffer(len, typ);

		// Save the Len
		if (value != null) {
			buffer.put(value);
		}
		buffer.flip();
		return buffer;
	}

	public boolean setValues(Object value) {
		byte typ = 0;
		ByteBuffer msgValue = null;
		if (value == null) {
			typ = ByteIdMap.DATATYPE_NULL;
		}
		if (value instanceof Short) {
			typ = ByteIdMap.DATATYPE_SHORT;
			msgValue = ByteBuffer.allocate(Short.SIZE / BITOFBYTE);
			msgValue.putShort((Short) value);
		} else if (value instanceof Integer) {
			typ = ByteIdMap.DATATYPE_INTEGER;
			msgValue = ByteBuffer.allocate(Integer.SIZE / BITOFBYTE);
			msgValue.putInt((Integer) value);
		} else if (value instanceof Long) {
			typ = ByteIdMap.DATATYPE_LONG;
			msgValue = ByteBuffer.allocate(Long.SIZE / BITOFBYTE);
			msgValue.putLong((Long) value);
		} else if (value instanceof Float) {
			typ = ByteIdMap.DATATYPE_FLOAT;
			msgValue = ByteBuffer.allocate(Float.SIZE / BITOFBYTE);
			msgValue.putFloat((Float) value);
		} else if (value instanceof Double) {
			typ = ByteIdMap.DATATYPE_DOUBLE;
			msgValue = ByteBuffer.allocate(Double.SIZE / BITOFBYTE);
			msgValue.putDouble((Double) value);
		} else if (value instanceof Byte) {
			typ = ByteIdMap.DATATYPE_BYTE;
			msgValue = ByteBuffer.allocate(Byte.SIZE / BITOFBYTE);
			msgValue.put((Byte) value);
		} else if (value instanceof Character) {
			typ = ByteIdMap.DATATYPE_CHAR;
			msgValue = ByteBuffer.allocate(Character.SIZE / BITOFBYTE);
			msgValue.putChar((Character) value);
		} else if (value instanceof String) {
			typ = ByteIdMap.DATATYPE_STRING;
			String newValue = (String) value;
			msgValue = ByteBuffer.allocate(newValue.length());
			msgValue.put(newValue.getBytes());
		} else if (value instanceof Date) {
			typ = ByteIdMap.DATATYPE_DATE;
			msgValue = ByteBuffer.allocate(Integer.SIZE / BITOFBYTE);
			Date newValue = (Date) value;
			msgValue.putInt((int) newValue.getTime());
		} else if (value instanceof Byte[] || value instanceof byte[]) {
			typ = ByteIdMap.DATATYPE_BYTEARRAY;
			byte[] newValue = (byte[]) value;
			msgValue = ByteBuffer.allocate(newValue.length);
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
			ByteBuffer bb = ByteBuffer.wrap(values);
			if (typ == ByteIdMap.DATATYPE_SHORT) {
				Short bufferValue = bb.getShort();
				if (bufferValue >= Byte.MIN_VALUE
						&& bufferValue <= Byte.MAX_VALUE) {
					return TYPBYTE + Byte.SIZE / BITOFBYTE;
				}
			} else if (typ == ByteIdMap.DATATYPE_INTEGER
					|| typ == ByteIdMap.DATATYPE_LONG) {
				Integer bufferValue = bb.getInt();
				if (bufferValue >= Byte.MIN_VALUE
						&& bufferValue <= Byte.MAX_VALUE) {
					return TYPBYTE + Byte.SIZE / BITOFBYTE;
				} else if (bufferValue >= Short.MIN_VALUE
						&& bufferValue <= Short.MAX_VALUE) {
					return TYPBYTE + Short.SIZE / BITOFBYTE;
				}
			}
		}
		int len = TYPBYTE + ByteUtil.getTypLen(getTyp());

		if (this.values != null) {
			len += this.values.length;
		}
		return len;
	}

	/**
	 * Sets the len check.
	 * 
	 * @param isLenCheck
	 *            the is len check
	 * @return true, if successful
	 */
	public boolean setLenCheck(boolean isLenCheck) {
		if (!isLenCheck) {
			if (typ / 16 == (ByteIdMap.DATATYPE_CHECK / 16)) {
			} else if (ByteUtil.isGroup(typ)) {
				this.typ = ByteUtil.getTyp(typ, ByteIdMap.DATATYPE_STRINGLAST);
			}
		} else {
			int size = this.values.length - 1;
			this.typ = ByteUtil.getTyp(getTyp(), size);
		}
		return true;
	}

	public void setVisible(boolean value) {
		this.visible = value;
	}

	public boolean isVisible() {
		return visible;
	}
}
