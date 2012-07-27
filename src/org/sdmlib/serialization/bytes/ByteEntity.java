package org.sdmlib.serialization.bytes;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

/**
 * The Class ByteEntity.
 */
public class ByteEntity extends Entity{
	/** The Constant BIT OF A BYTE. */
	public final static int BITOFBYTE=8;
	
	/** The children of the ByteEntity. */
	protected ArrayList<ByteEntity> children;
	
	/** The Byte Typ. */
	protected byte typ;
	
	/** The values. */
	protected byte[] values;
	
	/** The length of the Entity. */
	private int len;
	
	/** The is dynamic Transform integer to short etc.. */
	private boolean isDynamic;
	
	/** The is len check. */
	protected boolean isLenCheck;
	
	
	/**
	 * Instantiates a new byte entity.
	 */
	public ByteEntity(){
		
	}
	
	/**
	 * Instantiates a new byte entity.
	 *
	 * @param typ the typ
	 */
	public ByteEntity(byte typ){
		this.setTyp(typ);
	}
	
	/**
	 * Instantiates a new byte entity.
	 *
	 * @param isDynamic the is dynamic
	 */
	public ByteEntity(boolean isDynamic){
		this.isDynamic=isDynamic;
	}
	
	/**
	 * Instantiates a new byte entity.
	 *
	 * @param typ the typ
	 * @param value the value
	 */
	public ByteEntity(byte typ, byte[] value){
		this.setTyp(typ);
		this.setValue(value);
	}
	
	/*
	 * @see de.uni.kassel.peermessage.BaseEntity#getNewArray()
	 */
	@Override
	public EntityList getNewArray() {
		return null;
	}

	/*
	 * @see de.uni.kassel.peermessage.BaseEntity#getNewObject()
	 */
	@Override
	public Entity getNewObject() {
		return new ByteEntity();
	}
	
	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public ArrayList<ByteEntity> getChildren() {
		if(children==null){
			children=new ArrayList<ByteEntity>();
		}
		return children;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public byte[] getValue() {
		return values;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(byte[] value) {
		this.values = value;
	}

	/**
	 * Clean up.
	 *
	 * @return the length
	 */
	public int cleanUp() {
		int length=0;
		if(children!=null){
			ByteEntity[] valueList=children.toArray(new ByteEntity[children.size()]);
			boolean notLast=true;
			for(int i=valueList.length-1;i>=0;i--){
				int len=0;
				if(notLast){
					len=valueList[i].cleanUp();
					if(len==1){
						children.remove(valueList[i]);
						len=0;
					}else{
						// SET the LastEntity
						notLast=false;
						if(valueList[i].setLenCheck(false)){
							len=valueList[i].cleanUp();
						}
					}
				}else{
					len=valueList[i].cleanUp();
				}
				length+=len;
			}
		}
		length+=calcLength();
		this.len=length;
		return length;
	}
	
	/**
	 * Gets the typ.
	 *
	 * @param group the group
	 * @param subgroup the subgroup
	 * @return the typ
	 */
	private byte getTyp(byte group, byte subgroup){
		byte returnValue=(byte) ((group/16)*16);
		return (byte) (returnValue+(subgroup%16));
	}
	
	/**
	 * Calc length.
	 *
	 * @return the Length
	 */
	private int calcLength(){
		// Length calculate Sonderfaelle ermitteln
		int len=1;
		
		if(getTyp()==ByteIdMap.DATATYPE_STRINGSHORT||getTyp()==ByteIdMap.DATATYPE_BYTEARRAYSHORT||getTyp()==ByteIdMap.DATATYPE_LISTSHORT||getTyp()==ByteIdMap.DATATYPE_MAPSHORT){
			len+=1;
		}else if(getTyp()==ByteIdMap.DATATYPE_STRING||getTyp()==ByteIdMap.DATATYPE_BYTEARRAY||getTyp()==ByteIdMap.DATATYPE_LIST||getTyp()==ByteIdMap.DATATYPE_MAP){
			len+=1;
		}else if(getTyp()==ByteIdMap.DATATYPE_STRINGMID||getTyp()==ByteIdMap.DATATYPE_BYTEARRAYMID||getTyp()==ByteIdMap.DATATYPE_LISTMID||getTyp()==ByteIdMap.DATATYPE_MAPMID){
			len+=2;
		}else if(getTyp()==ByteIdMap.DATATYPE_STRINGBIG||getTyp()==ByteIdMap.DATATYPE_BYTEARRAYBIG||getTyp()==ByteIdMap.DATATYPE_LISTBIG||getTyp()==ByteIdMap.DATATYPE_MAPBIG){
			len+=4;
		}else if(getTyp()==ByteIdMap.DATATYPE_CLAZZ){
			len+=1;
		}
		if(values!=null){
			len+=values.length;
		}
		return len;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength() {
		return len;
	}
	
	/**
	 * Checks if is len check.
	 *
	 * @return true, if is len check
	 */
	public boolean isLenCheck() {
		return isLenCheck;
	}

	/**
	 * Sets the len check.
	 *
	 * @param isLenCheck the is len check
	 * @return true, if successful
	 */
	public boolean setLenCheck(boolean isLenCheck) {
		boolean oldValue=this.isLenCheck;

		this.isLenCheck = isLenCheck;
		
		if(oldValue!=this.isLenCheck){
			if(!isLenCheck){
				byte ref=getTyp(getTyp(), ByteIdMap.DATATYPE_STRINGLAST);
				if(ref==ByteIdMap.DATATYPE_BYTEARRAYLAST||
						ref==ByteIdMap.DATATYPE_STRINGLAST||
						ref==ByteIdMap.DATATYPE_LISTLAST||
						ref==ByteIdMap.DATATYPE_MAPLAST){
					setTyp(ref);
				}
			}else{
				int size=len-1;
				if (size > 32767) {
					setTyp(getTyp(getTyp(), ByteIdMap.DATATYPE_STRINGBIG));
				} else if (size > 250) {
					setTyp(getTyp(getTyp(), ByteIdMap.DATATYPE_STRINGMID));
				} else if (size <= ByteIdMap.SPLITTER) {
					setTyp(getTyp(getTyp(), ByteIdMap.DATATYPE_STRINGSHORT));
				}else{
					setTyp(getTyp(getTyp(), ByteIdMap.DATATYPE_STRING));
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Byte to unsigned byte.
	 *
	 * @param n the Byte
	 * @return the Byte
	 */
	public byte byteToUnsignedByte(int n) {
		if (n < 128) return (byte) n;
		return (byte) (n - 256);
	}
	
	/*
	 * @see de.uni.kassel.peermessage.Entity#toString()
	 */
	@Override
	public String toString() {
		StringBuilder returnValue = new StringBuilder();
		ByteBuffer byteBuffer = getBytes();
		if(byteBuffer!=null){
			for (int i = 0; i < byteBuffer.limit(); i++) {
				byte value = byteBuffer.get(i);
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
	
	/*
	 * @see de.uni.kassel.peermessage.Entity#toString(int)
	 */
	public String toString(int indentFactor) {
		return toString();
	}

	/*
	 * @see de.uni.kassel.peermessage.Entity#toString(int, int)
	 */
	public String toString(int indentFactor, int intent) {
		return  toString();
	}
	
	/**
	 * Gets the bytes.
	 *
	 * @return the bytes
	 */
	public ByteBuffer getBytes(){
		int len=calcLength();
		if(children!=null){
			for(ByteEntity item : children){
				len+=item.getLength();
			}
		}
		ByteBuffer message = ByteBuffer.allocate(len);
		message.put(typ);
		if(isLenCheck){
			// Save the Len
			if(getTyp()==getTyp(getTyp(), ByteIdMap.DATATYPE_STRINGSHORT)){
				message.put((byte) (len-2+ByteIdMap.SPLITTER));
			} else if(getTyp()==getTyp(getTyp(), ByteIdMap.DATATYPE_STRING)){
				message.put((byte) (len-2));
			}else if(getTyp()==getTyp(getTyp(), ByteIdMap.DATATYPE_STRINGMID)){
				message.putShort((short) (len-3));
			}else if(getTyp()==getTyp(getTyp(), ByteIdMap.DATATYPE_STRINGBIG)){
				message.putInt(len-5);
			}else{
				message.put(ByteIdMap.DATATYPE_CHECK);
				message.putInt(len-5);
			}
		}
		if(getTyp()==ByteIdMap.DATATYPE_CLAZZ){
			message.put((byte) (values.length));
		}

		if(values!=null){
			message.put(values);
		}
		if(children!=null){
			for(ByteEntity item : children){
				ByteBuffer child=item.getBytes();
				child.flip();
				message.put(child);
			}
		}
		return message;
	}
	
	/**
	 * Adds the child.
	 *
	 * @param creator the creator
	 * @param entity the entity
	 * @param referenceObject the reference object
	 * @param property the property
	 * @param parent the parent
	 */
	public void addChild(SendableEntityCreator creator, Object entity,
			Object referenceObject, String property, ByteIdMap parent) {
		Object valueTyp = creator.getValue(entity, property);
		if (valueTyp == null) {
			getChildren().add(new ByteEntity(ByteIdMap.DATATYPE_NULL));
		} else{
			Object referenceTyp = creator.getValue(referenceObject,
					property);
			if (valueTyp.equals(referenceTyp)) {
				getChildren().add(new ByteEntity(ByteIdMap.DATATYPE_NULL));
			}else{
				getChildren().add(encodingDataType(valueTyp, parent));
				
			}
		}
	}

	/**
	 * Encoding data type.
	 *
	 * @param value the value
	 * @param parent the parent
	 * @return the byte entity
	 */
	private ByteEntity encodingDataType(Object value, ByteIdMap parent) {
		ByteEntity entity=new ByteEntity(isDynamic());
		if (value instanceof Short) {
			entity.setValues(ByteIdMap.DATATYPE_SHORT, ((Short) value).shortValue(), parent);
		} else if (value instanceof Integer) {
			entity.setValues(ByteIdMap.DATATYPE_INTEGER, ((Integer) value).intValue(), parent);
		} else if (value instanceof Long) {
			entity.setValues(ByteIdMap.DATATYPE_LONG, ((Long) value).longValue(), parent);
		} else if (value instanceof Float) {
			entity.setValues(ByteIdMap.DATATYPE_FLOAT, ((Float) value).floatValue(), parent);
		} else if (value instanceof Double) {
			entity.setValues(ByteIdMap.DATATYPE_DOUBLE, ((Double) value).doubleValue(), parent);
		} else if (value instanceof Byte) {
			entity.setValues(ByteIdMap.DATATYPE_BYTE, (Byte) value, parent);
		} else if (value instanceof String) {
			entity.setValues(ByteIdMap.DATATYPE_STRING, (String) value, parent);
		} else if (value instanceof Date) {
			entity.setValues(ByteIdMap.DATATYPE_DATE, value, parent);
		} else if (value instanceof byte[]) {
			entity.setValues(ByteIdMap.DATATYPE_BYTEARRAY, value, parent);
		} else if (value instanceof List<?>) {
			entity.setValues(ByteIdMap.DATATYPE_LIST, value, parent);
		} else if (value instanceof Map<?, ?>) {
			entity.setValues(ByteIdMap.DATATYPE_MAP, value, parent);
		} else if (value == null) {
			entity.setValues(ByteIdMap.DATATYPE_NULL, value, parent);
		} else {
			entity=parent.encode(value);
		}
		return entity;
	}

	/**
	 * Sets the values.
	 *
	 * @param typ the typ
	 * @param value the value
	 * @param parent the parent
	 */
	public void setValues(byte typ, Object value, ByteIdMap parent){
		ByteBuffer buffer=null;
		if(isDynamic()){
			if(typ==ByteIdMap.DATATYPE_SHORT){
				short bufferValue = (Short) value;
				if(bufferValue>=Byte.MIN_VALUE&&bufferValue<=Byte.MAX_VALUE){
					typ=ByteIdMap.DATATYPE_BYTE;
					value=(byte)bufferValue;
				}
			}else if(typ==ByteIdMap.DATATYPE_INTEGER){
				int bufferValue = (Integer) value;
				if(bufferValue>=Byte.MIN_VALUE&&bufferValue<=Byte.MAX_VALUE){
					typ=ByteIdMap.DATATYPE_BYTE;
					value=(byte)bufferValue;
				}else if(bufferValue>=Short.MIN_VALUE&&bufferValue<=Short.MAX_VALUE){
					typ=ByteIdMap.DATATYPE_SHORT;
					value=(short)bufferValue;
				}
			}else if(typ==ByteIdMap.DATATYPE_LONG){
				long bufferValue = (Long) value;
				if(bufferValue>=Byte.MIN_VALUE&&bufferValue<=Byte.MAX_VALUE){
					typ=ByteIdMap.DATATYPE_BYTE;
					value=(byte)bufferValue;
				}else if(bufferValue>=Short.MIN_VALUE&&bufferValue<=Short.MAX_VALUE){
					typ=ByteIdMap.DATATYPE_SHORT;
					value=(short)bufferValue;
				}
			}
		}
		
		if (typ==ByteIdMap.DATATYPE_SHORT) {
			buffer = ByteBuffer.allocate(Short.SIZE/BITOFBYTE);
			buffer.putShort((Short) value);
		} else if (typ==ByteIdMap.DATATYPE_INTEGER) {
			buffer = ByteBuffer.allocate(Integer.SIZE/BITOFBYTE);
			buffer.putInt((Integer) value);
		} else if (typ==ByteIdMap.DATATYPE_LONG) {
			buffer = ByteBuffer.allocate(Long.SIZE/BITOFBYTE);
			buffer.putLong((Long) value);
		} else if (typ==ByteIdMap.DATATYPE_FLOAT) {
			buffer = ByteBuffer.allocate(Float.SIZE/BITOFBYTE);
			buffer.putFloat((Float) value);
		} else if (typ==ByteIdMap.DATATYPE_DOUBLE) {
			buffer = ByteBuffer.allocate(Double.SIZE/BITOFBYTE);
			buffer.putDouble((Float) value);
		} else if (typ==ByteIdMap.DATATYPE_BYTE) {
			buffer = ByteBuffer.allocate(Byte.SIZE/BITOFBYTE);
			buffer.put((Byte) value);
		} else if (typ==ByteIdMap.DATATYPE_CHAR) {
			buffer = ByteBuffer.allocate(Character.SIZE/BITOFBYTE);
			buffer.putChar((Character) value);
		} else if (typ==ByteIdMap.DATATYPE_STRING) {
			String newValue = (String) value;
			buffer = ByteBuffer.allocate(newValue.length());
			buffer.put(newValue.getBytes());
			if (newValue.length() > 32767) {
				typ=ByteIdMap.DATATYPE_STRINGBIG;
			} else if (newValue.length() > 250) {
				typ=ByteIdMap.DATATYPE_STRINGMID;
			} else if(newValue.length()>ByteIdMap.SPLITTER){
				typ=ByteIdMap.DATATYPE_STRING;
			}else{
				typ=ByteIdMap.DATATYPE_STRINGSHORT;
			}
		} else if (typ==ByteIdMap.DATATYPE_DATE) {
			buffer = ByteBuffer.allocate(Integer.SIZE/BITOFBYTE);
			Date newValue = (Date) value;
			buffer.putInt((int) newValue.getTime());
		} else if (typ==ByteIdMap.DATATYPE_BYTEARRAY) {
			byte[] newValue = (byte[]) value;
			buffer = ByteBuffer.allocate(newValue.length);
			buffer.put(newValue);
			if (newValue.length> 32767) {
				typ=ByteIdMap.DATATYPE_BYTEARRAYBIG;
			} else if (newValue.length > 250) {
				typ=ByteIdMap.DATATYPE_BYTEARRAYMID;
			} else if(newValue.length>ByteIdMap.SPLITTER){
				typ=ByteIdMap.DATATYPE_BYTEARRAY;
			}else{
				typ=ByteIdMap.DATATYPE_BYTEARRAYSHORT;
			}
		} else if (typ==ByteIdMap.DATATYPE_LIST) {
			this.typ=typ;
			List<?> list=(List<?>)value;
			for (Object childValue : list) {
				ByteEntity child = encodingDataType(childValue, parent);
				if(child!=null){
					getChildren().add(child);
				}
			}
			this.isLenCheck=true;
		} else if (typ==ByteIdMap.DATATYPE_MAP) {
			this.typ=typ;
			if(value instanceof Map<?, ?>){
				Map<?, ?> map=(Map<?,?>)value;
				for (Iterator<?> i = map.entrySet().iterator(); i
						.hasNext();) {
					java.util.Map.Entry<?,?> entity = (Entry<?, ?>) i.next();

					getChildren().add(encodingDataType(entity.getKey(), parent));
					getChildren().add(encodingDataType(entity.getValue(), parent));
				}
			}
			this.isLenCheck=true;
		} else if(typ==ByteIdMap.DATATYPE_ASSOC){
			ByteEntity child = parent.encode(value);
			if(child!=null){
				getChildren().add(child);
			}
			this.typ=typ;
			this.setLenCheck(true);
		}
		if(buffer!=null){
			this.typ=typ;
			// Check for group
			if(typ/16==ByteIdMap.DATATYPE_BYTEARRAY/16){
				this.isLenCheck=true;
			}else if(typ/16==ByteIdMap.DATATYPE_STRING/16){
				this.isLenCheck=true;
			}
			buffer.flip();
			values=buffer.array();
		}
	}

	/**
	 * Gets the typ.
	 *
	 * @return the typ
	 */
	public byte getTyp() {
		return typ;
	}

	/**
	 * Sets the typ.
	 *
	 * @param typ the new typ
	 */
	public void setTyp(byte typ) {
		this.typ = typ;
	}

	/**
	 * Checks if is dynamic.
	 *
	 * @return true, if is dynamic
	 */
	public boolean isDynamic() {
		return isDynamic;
	}

	/**
	 * Sets the dynamic.
	 *
	 * @param isDynamic the new dynamic
	 */
	public void setDynamic(boolean isDynamic) {
		boolean oldValue=this.isDynamic;
		this.isDynamic = isDynamic;
		if(oldValue!=this.isDynamic){
			if(isDynamic&&values!=null){
				ByteBuffer buffer=ByteBuffer.wrap(values);
				if(typ==ByteIdMap.DATATYPE_SHORT){
					setValues(typ, buffer.getShort(), null);
				}else if(typ==ByteIdMap.DATATYPE_INTEGER){
					setValues(typ, buffer.getInt(), null);
				}else if(typ==ByteIdMap.DATATYPE_LONG){
					setValues(typ, buffer.getLong(), null);
				}
			}else{
				// Zurueckformen nicht m�glich da keine Infos vorliegen
			}
		}
	}
}