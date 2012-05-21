package org.sdmlib.serialization.bytes;

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

public class ByteEntity extends Entity{
	public final static int BITOFBYTE=8;
	protected ArrayList<ByteEntity> children;
	protected byte typ;
	protected byte[] values;
	private int len;
	private boolean isDynamic;
	protected boolean isLenCheck;
	
	public ByteEntity(){
		
	}
	
	public ByteEntity(byte typ){
		this.setTyp(typ);
	}
	
	public ByteEntity(boolean isDynamic){
		this.isDynamic=isDynamic;
	}
	
	public ByteEntity(byte typ, byte[] value){
		this.setTyp(typ);
		this.setValue(value);
	}
	
	@Override
	public EntityList getNewArray() {
		return null;
	}

	@Override
	public Entity getNewObject() {
		return new ByteEntity();
	}
	public ArrayList<ByteEntity> getChildren() {
		if(children==null){
			children=new ArrayList<ByteEntity>();
		}
		return children;
	}

	public byte[] getValue() {
		return values;
	}

	public void setValue(byte[] value) {
		this.values = value;
	}

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
	
	private byte getTyp(byte group, byte subgroup){
		byte returnValue=(byte) ((group/16)*16);
		return (byte) (returnValue+(subgroup%16));
	}
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

	public int getLength() {
		return len;
	}
	
	public boolean isLenCheck() {
		return isLenCheck;
	}

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
	public byte byteToUnsignedByte(int n) {
		if (n < 128) return (byte) n;
		return (byte) (n - 256);
	}
	
	@Override
	public String toString() {
		StringBuffer returnValue = new StringBuffer();
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
//		if(children!=null){
//			for(ByteEntity item : children){
//				returnValue.append(item.toString());
//			}
//		}
		return returnValue.toString();
	}
	public String toString(int indentFactor) {
		return toString();
	}

	public String toString(int indentFactor, int intent) {
		return  toString();
	}
	
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

//					ByteEntity child = new ByteEntity(isDynamic());
//					child.setTyp(ByteIdMap.DATATYPE_ENTITY);
					getChildren().add(encodingDataType(entity.getKey(), parent));
					getChildren().add(encodingDataType(entity.getValue(), parent));
//					getChildren().add(child);
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

	public byte getTyp() {
		return typ;
	}

	public void setTyp(byte typ) {
		this.typ = typ;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

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
				// Zurueckformen nicht möglich da keine Infos vorliegen
			}
		}
	}
}
