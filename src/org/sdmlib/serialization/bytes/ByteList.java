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
