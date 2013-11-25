package org.sdmlib.serialization.bytes;

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
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.bytes.converter.ByteConverter;
import org.sdmlib.serialization.bytes.converter.ByteConverterHTTP;
import org.sdmlib.serialization.bytes.converter.ByteConverterString;
import org.sdmlib.serialization.interfaces.BufferedBytes;
import org.sdmlib.serialization.interfaces.ByteItem;

public class ByteList extends EntityList implements ByteItem {
	/** The children of the ByteEntity. */
	private byte typ = 0;

	@Override
	public ByteList getNewArray() {
		return new ByteList();
	}

	@Override
	public ByteEntity getNewObject() {
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

	public BufferedBytes getBytes(boolean isDynamic) {
		int len = calcLength(isDynamic);
		BufferedBytes buffer = ByteUtil.getBuffer(len);
		writeBytes(buffer, isDynamic, false);
		buffer.flip();
		return buffer;
	}
	

	public void writeBytes(BufferedBytes buffer, boolean isDynamic, boolean last){
		int size=calcChildren(isDynamic);
		
		byte typ = ByteUtil.getTyp(getTyp(), size, last);
		ByteUtil.writeByteHeader(buffer, typ, size);
	
		for(int i=0;i<values.size();i++){
			((ByteItem) values.get(i)).writeBytes(buffer, isDynamic, i==values.size()-1);
		}
	}

	public int calcLength(boolean isDynamic) {
		if (size() == 0 ) {
			return 1;
		}
		int length = calcChildren(isDynamic);
		// add The Headerlength
		if (typ != 0) {
			length += ByteEntity.TYPBYTE + ByteUtil.getTypLen(typ, length);
		}
		return length;
	}
	
	public int calcChildren(boolean isDynamic) {
		int length, size=size();
		if(size<1){
			return 0;
		}
		Object[] valueList = this.values.toArray(new Object[size()]);
		
		// SonderFall Last Entity
		int typLen;
		int len;
		if(valueList[size-1] instanceof ByteEntity){
			ByteEntity entity =(ByteEntity) valueList[size-1];
			len=entity.getValue().length;
			typLen=ByteUtil.getTypLen(((ByteEntity)entity).getTyp(), len);
		}else{
			ByteList list = (ByteList) valueList[size-1];
			len=list.calcChildren(isDynamic);
			typLen=ByteUtil.getTypLen(((ByteList)list).getTyp(), len);
		}
		if(typLen>0){
			// Must be a Group and not the optimize LastEntity
			length=len+1;
		}else{
			length=((ByteItem)valueList[size-1]).calcLength(isDynamic);
		}
		for (int i = size - 2; i >= 0; i--) {
			length += ((ByteItem)valueList[i]).calcLength(isDynamic);
 		}
		return length;
	}

	public byte getTyp() {
		return typ;
	}

	public void setTyp(Byte value) {
		this.typ = value;
	}

	@Override
	public EntityList withValue(String value) {
		ByteConverterString	converter = new ByteConverterString();
		this.add(getNewObject().withValue(ByteIdMap.DATATYPE_FIXED, converter.decode(value)));
		return this;
	}
}
