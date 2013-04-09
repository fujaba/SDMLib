package org.sdmlib.serialization.bytes;
/*
Copyright (c) 2012 Stefan Lindel

Permission is hereby granted,	 free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
import java.nio.ByteBuffer;

import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.interfaces.ByteItem;
import org.sdmlib.serialization.interfaces.JSIMEntity;

public class ByteList extends EntityList implements ByteItem {
	/** The children of the ByteEntity. */
	private byte typ = 0;
	private boolean isGroupable = true;

	@Override
	public EntityList getNewArray() {
		return new ByteList();
	}

	@Override
	public JSIMEntity getNewObject() {
		return new ByteEntity();
	}

	@Override
	public String toString(int indentFactor) {
		return toString(null);
	}

	@Override
	public String toString(int indentFactor, int intent) {
		return toString(null);
	}

	@Override
	public String toString() {
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

	public ByteBuffer getBytes(boolean isDynamic) {
		int len = calcLength(isDynamic);
		ByteBuffer buffer = ByteUtil.getBuffer(len, getTyp(), isGroupable());
		if (buffer == null) {
			return null;
		}
		for (Object value : values) {
			ByteBuffer child = null;
			if (value instanceof ByteItem) {
				child = ((ByteItem) value).getBytes(isDynamic);
			}
			if (child == null) {
				buffer.put(ByteIdMap.DATATYPE_NULL);
			} else {
				byte[] array = new byte[child.limit()];
				child.get(array);
				buffer.put(array);
			}
		}
		buffer.flip();
		return buffer;
	}

	public int calcLength(boolean isDynamic) {
		int length = 0;
		if (size() == 0 ) {
			return 0;
		}
		if (typ != 0) {
			length = ByteUtil.getTypLen(typ) + ByteEntity.TYPBYTE;
		}
		Object[] valueList = this.values
				.toArray(new Object[this.values.size()]);
		boolean notLast = true;
		for (int i = valueList.length - 1; i >= 0; i--) {
			if (notLast) {
				int len = 0;
				if (valueList[i] instanceof ByteList) {
					len = ((ByteList) valueList[i]).calcLength(isDynamic);
					if (len < 1) {
						this.values.remove(valueList[i]);
					} else {
						notLast = false;
						length += len;
					}
				} else if (valueList[i] instanceof ByteEntity) {
					ByteEntity entity = (ByteEntity) valueList[i];
					len = entity.calcLength(isDynamic);
					if (len == 1) {
						this.values.remove(valueList[i]);
					} else {
						// SET the LastEntity
						notLast = false;
						if (entity.setLenCheck(false)) {
							len = entity.calcLength(isDynamic);
						}
						length += len;
					}
				}
			} else {
				if (valueList[i] instanceof ByteItem) {
					length += ((ByteItem) valueList[i]).calcLength(isDynamic);
				}
			}
		}
		return length;
	}

	public Byte getTyp() {
		return typ;
	}

	public void setTyp(Byte typ) {
		this.typ = typ;
	}

	public void setTyp(Byte typ, boolean isGroupable) {
		this.typ = typ;
		this.isGroupable = isGroupable;
	}

	public boolean isGroupable() {
		return isGroupable;
	}
}
