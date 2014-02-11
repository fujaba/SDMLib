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
