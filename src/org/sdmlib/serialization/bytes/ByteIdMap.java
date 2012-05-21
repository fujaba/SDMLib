package org.sdmlib.serialization.bytes;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.event.BasicMessage;
import org.sdmlib.serialization.event.ByteMessage;
import org.sdmlib.serialization.event.UnknownMessage;
import org.sdmlib.serialization.event.creater.BasicMessageCreator;
import org.sdmlib.serialization.interfaces.ByteEntityCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ByteIdMap extends IdMap{
	public static char SPLITTER = ' ';
	/** The Constant CLASS-VALUE. */
	public static final byte DATATYPE_CLAZZ = 0x22;
	/** The Constant NULL-VALUE. */
	public static final byte DATATYPE_NULL = 0x23;

	/** The Constant CHECKTYPE. */
	public static final byte DATATYPE_CHECK = 0x24;

	/**
	 * SIMPLE TYPES 
	 * The Constant DATATYPE_BYTE.
	 */
	/** The Constant DATATYPE_INTEGER. */
	public static final byte DATATYPE_SHORT = 0x30;

	/** The Constant DATATYPE_INTEGER. */
	public static final byte DATATYPE_INTEGER = 0x31;

	/** The Constant DATATYPE_INTEGER. */
	public static final byte DATATYPE_LONG = 0x32;

	/** The Constant DATATYPE_FLOAT. */
	public static final byte DATATYPE_FLOAT = 0x33;

	/** The Constant DATATYPE_DOUBLE. */
	public static final byte DATATYPE_DOUBLE = 0x34;
	
	/** The Constant DATATYPE_BYTEARRAY. */
	public static final byte DATATYPE_DATE = 0x35;
		
	public static final byte DATATYPE_BYTE = 0x36;

	/** The Constant DATATYPE_BYTEARRAY. */
	public static final byte DATATYPE_UNSIGNEDBYTE = 0x37;

	/** The Constant DATATYPE_BYTEARRAY. */
	public static final byte DATATYPE_CHAR = 0x38;

//	public static final byte DATATYPE_ENTITY = 0x39;

	
	/** The Constant DATATYPE_MAP. */
	public static final byte DATATYPE_ASSOC = 0x3A;
	
		
	/** The Constant DATATYPE_BYTEARRAY. */
	public static final byte DATATYPE_BYTEARRAY = 0x42;

	/** The Constant DATATYPE_BYTEARRAY. */
	public static final byte DATATYPE_BYTEARRAYSHORT = 0x43;

	/** The Constant DATATYPE_BYTEARRAY. */
	public static final byte DATATYPE_BYTEARRAYMID = 0x44;

	/** The Constant DATATYPE_BYTEARRAY. */
	public static final byte DATATYPE_BYTEARRAYBIG = 0x45;

	/** The Constant DATATYPE_BYTEARRAY. */
	public static final byte DATATYPE_BYTEARRAYLAST = 0x46;


	/** The Constant DATATYPE_STRING. */
	public static final byte DATATYPE_STRING = 0x52;

	/** The Constant DATATYPE_STRING. */
	public static final byte DATATYPE_STRINGSHORT = 0x53;
	
	/** The Constant DATATYPE_MIDSTRING. */
	public static final byte DATATYPE_STRINGMID = 0x54;

	/** The Constant DATATYPE_STRING. */
	public static final byte DATATYPE_STRINGBIG = 0x55;

	/** The Constant DATATYPE_STRING. */
	public static final byte DATATYPE_STRINGLAST = 0x56;

	
	/** The Constant DATATYPE_LIST. */
	public static final byte DATATYPE_LIST = 0x62;

	/** The Constant DATATYPE_SHORTLIST. */
	public static final byte DATATYPE_LISTSHORT = 0x63;
	
	/** The Constant DATATYPE_MIDLIST. */
	public static final byte DATATYPE_LISTMID = 0x64;

	/** The Constant DATATYPE_BIGLIST. */
	public static final byte DATATYPE_LISTBIG = 0x65;

	/** The Constant DATATYPE_LASTLIST. */
	public static final byte DATATYPE_LISTLAST = 0x66;
	
	/** The Constant DATATYPE_MAP. */
	public static final byte DATATYPE_MAP = 0x72;

	/** The Constant DATATYPE_MAP. */
	public static final byte DATATYPE_MAPSHORT = 0x73;

	/** The Constant DATATYPE_MAP. */
	public static final byte DATATYPE_MAPMID = 0x74;

	/** The Constant DATATYPE_MAP. */
	public static final byte DATATYPE_MAPBIG = 0x75;

	/** The Constant DATATYPE_MAP. */
	public static final byte DATATYPE_MAPLAST = 0x76;

	protected HashMap<Byte, ByteEntityCreator> decoderMap;
	private boolean lenCheck;
	private boolean isDynamic;

	public ByteIdMap() {

	}

	public void setCheckLen(boolean checklen) {
		this.lenCheck = checklen;
	}

	@Override
	public boolean addCreator(SendableEntityCreator createrClass) {
		boolean result=super.addCreator(createrClass);
		if (decoderMap == null) {
			decoderMap = new HashMap<Byte, ByteEntityCreator>();
		}
		if(createrClass instanceof ByteEntityCreator){
			ByteEntityCreator byteCreator=(ByteEntityCreator) createrClass;
			if (decoderMap.containsKey(byteCreator.getEventTyp())) {
				return false;
			}
			decoderMap.put(byteCreator.getEventTyp(), byteCreator);
		}else{
			return false;
		}
		return result;
	}
	
	public ByteEntityMessage encode(Object entity) {
		SendableEntityCreator creator;
		if(parent!=null){
			creator = parent.getCreatorClass(entity);
		}else{
			creator=getCreatorClass(entity);
		}
		if (creator == null) {
			return null;
		}
		
		ByteEntityMessage msg=new ByteEntityMessage();
		
		if (creator instanceof BasicMessageCreator) {
			BasicMessage basicEvent = (BasicMessage) entity;
			String value = basicEvent.getValue();
			msg.setFullMsg(value);
			return msg;
		}
		
		msg.setLenCheck(lenCheck);
		
		if(creator instanceof ByteEntityCreator){
			msg.setMsgTyp(((ByteEntityCreator) creator).getEventTyp());
		}else{
			Object reference = creator.getSendableInstance(true);
			msg.setMsgTyp(reference.getClass().getName());
		}
		
		String[] properties = creator.getProperties();
		Object referenceObject = creator.getSendableInstance(true);
		msg.setDynamic(isDynamic);
		
		if (properties != null) {
			for (String property : properties) {
				msg.addChild(creator, entity, referenceObject, property, this);				
			}
		}
		
		
		msg.cleanUp();
		return msg;
	}

	public Object decodeHTTP(String bytes) throws RuntimeException {
		int len = bytes.length();
		ByteBuffer buffer = ByteBuffer.allocate(len);
		for (int i = 0; i < len; i++) {
			int value = bytes.charAt(i);
			if (value == SPLITTER) {
				value = bytes.charAt(++i);
				buffer.put((byte) (value - SPLITTER - 1));
			} else {
				buffer.put((byte) value);
			}
		}
		int limit = buffer.position();
		buffer.position(0);
		buffer.limit(limit);
		return decode(buffer);
	}

	public ByteEntityCreator getCreatorDecoderClass(byte typ) {
		return decoderMap.get(typ);
	}
	
	public static String toHexString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		String ret = "";
		for (int i = 0; i < bytes.length; i++) {

			char b = (char) (bytes[i] & 0xFF);
			if (b < 0x10) {
				ret = ret + "0";
			}
			ret = ret + (String) (Integer.toHexString(b)).toUpperCase();
		}
		return ret;

	}

	public static byte[] toByteString(String hexString) {
		String hexVal = "0123456789ABCDEF";
		byte[] out = new byte[hexString.length() / 2];

		int n = hexString.length();

		for (int i = 0; i < n; i += 2) {
			// make a bit representation in an int of the hex value
			int hn = hexVal.indexOf(hexString.charAt(i));
			int ln = hexVal.indexOf(hexString.charAt(i + 1));

			// now just shift the high order nibble and add them together
			out[i / 2] = (byte) ((hn << 4) | ln);
		}

		return out;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public void setDynamic(boolean isDynamic) {
		this.isDynamic = isDynamic;
	}
	
	public Object decode(Object value) throws RuntimeException {
		if (value instanceof ByteBuffer) {
			return decode((ByteBuffer) value);
		} else if (value instanceof byte[]) {
			return decode(ByteBuffer.wrap((byte[]) value));
		}
		return null;
	}

	public Object decode(ByteBuffer in) throws RuntimeException {
		if (in.remaining() < 1)
			throw new RuntimeException("DecodeExpeption - Remaining:" + in.remaining());

		Object entity = null;
		byte typ = in.get();
		SendableEntityCreator eventCreater=null;
		if(typ==ByteIdMap.DATATYPE_CLAZZ){
			String clazz = (String) getDecodeObject(ByteIdMap.DATATYPE_STRING, in);
			eventCreater = getCreatorClasses(clazz);
		}else{
			eventCreater = getCreatorDecoderClass(typ);
		}
		if (eventCreater == null) {
			UnknownMessage e = new UnknownMessage();
			e.set(ByteMessage.PROPERTY_VALUE, in.array());
			entity = e;
		} else {
			entity = eventCreater.getSendableInstance(false);
			String[] properties = eventCreater.getProperties();
			if (properties != null) {
				for (String property : properties) {
					if (in.remaining() < 1) {
						break;
					}
					Byte typValue = in.get();
					if (typValue == ByteIdMap.DATATYPE_CHECK) {
						int len = in.getInt();
						if (len > 0) {
							if (in.remaining() != len)
								throw new RuntimeException("DecodeExpeption - Remaining:" + in.remaining() + "!="+len);
						}
						typValue = in.get();
					}
					Object value = getDecodeObject(typValue, in);
					if (value != null) {
						eventCreater.setValue(entity, property, value);
					}
				}
			}
		}
		return entity;
	}

	public Object getDecodeObject(Byte typValue, ByteBuffer in) {
		if (in.remaining() < 1) {
			return null;
		} else if (typValue == ByteIdMap.DATATYPE_BYTE) {
			return in.get();
		} else if (typValue == ByteIdMap.DATATYPE_CHAR) {
			return in.getChar();
		} else if (typValue == ByteIdMap.DATATYPE_SHORT) {
			return in.getShort();
		} else if (typValue == ByteIdMap.DATATYPE_INTEGER) {
			return in.getInt();
		} else if (typValue == ByteIdMap.DATATYPE_LONG) {
			return in.getLong();
		} else if (typValue == ByteIdMap.DATATYPE_FLOAT) {
			return in.getFloat();
		} else if (typValue == ByteIdMap.DATATYPE_DOUBLE) {
			return in.getDouble();
		} else if (typValue == ByteIdMap.DATATYPE_DATE) {
			int value = in.getInt();
			Date newValue = new Date(value);
			return newValue;
		} else {
			byte group=getTyp(typValue, ByteIdMap.DATATYPE_STRING);
			if(group==ByteIdMap.DATATYPE_STRING||
					group==ByteIdMap.DATATYPE_BYTEARRAY||
					group==ByteIdMap.DATATYPE_LIST||
					group==ByteIdMap.DATATYPE_MAP){
				byte subgroup=getTyp(ByteIdMap.DATATYPE_STRING, typValue);
				byte[] values;
				int len=0;
				if(subgroup == ByteIdMap.DATATYPE_STRINGSHORT) {
					len = in.get()-ByteIdMap.SPLITTER;
				}else if(subgroup == ByteIdMap.DATATYPE_STRING) {
					len = in.get();
				} else if (subgroup == ByteIdMap.DATATYPE_STRINGMID) {
					len = in.getShort();
				} else if (subgroup == ByteIdMap.DATATYPE_STRINGBIG) {
					len = in.getInt();
				} else if (subgroup == ByteIdMap.DATATYPE_STRINGLAST) {
					len = in.remaining();
				}
				values = new byte[len];
				in.get(values);
				if(group==ByteIdMap.DATATYPE_STRING){
					return new String(values);
				} else if (group == ByteIdMap.DATATYPE_BYTEARRAY) {
					return values;
				} else if (group == ByteIdMap.DATATYPE_LIST) {
					ByteBuffer child=ByteBuffer.wrap((byte[]) values);
					ArrayList<Object> list = new ArrayList<Object>();
					while (child.remaining() > 0) {
						Byte subType = child.get();
						Object entity = getDecodeObject(subType, child);
						if (entity != null) {
							list.add(entity);
						}
					}
					return list;
				} else if (typValue == ByteIdMap.DATATYPE_MAP) {
					ByteBuffer child=ByteBuffer.wrap((byte[]) values);
					HashMap<Object, Object> map = new HashMap<Object, Object>();
					while (child.remaining() > 0) {
						Byte subType = child.get();
						Object key = getDecodeObject(subType, child);
						if (key != null) {
							subType = child.get();
							Object value = getDecodeObject(subType, child);
							if (key != null) {
								map.put(key, value);
							}
						}
					}
					return map;
				}
			}
		}
		return null;
	}
	private byte getTyp(byte group, byte subgroup){
		byte returnValue=(byte) ((group/16)*16);
		return (byte) (returnValue+(subgroup%16));
	}
}
