package org.sdmlib.serialization.bytemsg;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.serialization.event.BasicMessage;
import org.sdmlib.serialization.event.creater.BasicMessageCreator;
import org.sdmlib.serialization.interfaces.ByteEntityCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class Encoding  {
	private int lastType;
	// private int pos;
	private int maxpos;
	private boolean lenCheck;
	private ByteIdMap parent;

	public Encoding(ByteIdMap parent, boolean lenCheck) {
		this.lenCheck = lenCheck;
		this.parent=parent;
	}

	public void setCheckLen(boolean value) {
		this.lenCheck = value;
	}

	public int getValueLen(Object valueTyp) {
		return getValueLen(valueTyp, null, null);
	}

	public int getValueLen(Object valueTyp, Integer pos, Object referenceTyp) {
		int length = 0;
		if (valueTyp == null) {
			lastType++;
			length = 1;
		} else if (valueTyp.equals(referenceTyp)) {
			lastType++;
			length = 1;
		} else {
			if (pos != null) {
				maxpos = pos;
			}
			if (valueTyp instanceof Integer) {
				lastType = 0;
				length = 5;
			} else if (valueTyp instanceof Byte) {
				lastType = 0;
				length = 2;
			} else if (valueTyp instanceof Short) {
				length = 3;
			} else if (valueTyp instanceof Float) {
				length = 5;
			} else if (valueTyp instanceof Double) {
				length = 9;
			} else if (valueTyp instanceof Long) {
				length = 9;
			} else if (valueTyp instanceof String) {
				int len = ((String) valueTyp).getBytes().length;
				if (len > 32767) {
					lastType = 4;
				} else if (len > 250) {
					lastType = 2;
				} else {
					lastType = 1;
				}
				length = len + 1 + lastType;
			} else if (valueTyp instanceof Date) {
				lastType = 0;
				length = 5;
			} else if (valueTyp instanceof byte[]) {
				int len = ((byte[]) valueTyp).length;
				if (len > 32767) {
					lastType = 4;
				} else if (len > 250) {
					lastType = 2;
				} else {
					lastType = 1;
				}
				length = len + 1 + lastType;
			} else if (valueTyp instanceof List<?>) {
				lastType = 0;
				length = 3;
				for (Object entity : (List<?>) valueTyp) {
					length += getValueLen(entity, pos, null);
				}
				lastType = 0;
			} else if (valueTyp instanceof Map<?, ?>) {
				length = 3;
				@SuppressWarnings("unchecked")
				Map<Object, Object> map = (Map<Object, Object>) valueTyp;
				for (Iterator<Entry<Object, Object>> i = map.entrySet()
						.iterator(); i.hasNext();) {
					Entry<Object, Object> entity = i.next();
					length += getValueLen(entity.getKey(), pos, null);
					length += getValueLen(entity.getValue(), pos, null);
				}
				lastType = 0;
			} else {
				ByteBuffer subTyp = encode(valueTyp);
				if (subTyp != null) {
					length = subTyp.limit() + 1;
				}
			}
		}
		return length;
	}
	public byte byteToUnsignedByte(int n) {
		if (n < 128) return (byte) n;
		return (byte) (n - 256);
	}
	public ByteBuffer encode(Object entity) {
		SendableEntityCreator creater = parent.getCreatorClass(entity);
		if (creater == null) {
			return null;
		}
//		ByteEntityCreator creater = (ByteEntityCreator) temp;

		if (creater instanceof BasicMessageCreator) {
			BasicMessage basicEvent = (BasicMessage) entity;
			String msg = basicEvent.getValue();
			ByteBuffer message = ByteBuffer.allocate(msg.length());
			message.put(msg.getBytes());
			message.flip();
			return message;
		}

		int length=0;
		if(creater instanceof ByteEntityCreator){
			length++;
		}else{
			Object reference = creater.getSendableInstance(true);
			int len=reference.getClass().getName().length();
			length+=2+len;
		}
		if (lenCheck) {
			length += 5;
		}
		maxpos = 0;
		String[] properties = creater.getProperties();
		Object referenceObject = creater.getSendableInstance(true);
		if (properties != null) {
			int pos = 0;
			for (String property : properties) {
				Object valueTyp = creater.getValue(entity, property);
				Object referenceTyp = creater.getValue(referenceObject,
						property);
				length += getValueLen(valueTyp, pos, referenceTyp);
				pos++;
			}
			length -= lastType;
			ByteBuffer message = ByteBuffer.allocate(length);
			if(creater instanceof ByteEntityCreator){
				message.put(((ByteEntityCreator)creater).getEventTyp());
			}else{
				Object reference = creater.getSendableInstance(true);
				String name = reference.getClass().getName();
				int len=name.length();
				message.put(ByteIdMap.STDID);
				message.put((byte)len);
				message.put(name.getBytes());
			}
			
			pos = 0;
			if (lenCheck) {
				message.put(ByteConst.DATATYPE_CHECK);
				message.putInt(length - 6);
			}
			for (String property : properties) {
				Object value = creater.getValue(entity, property);

				encodingDataType(message, value, pos);
				if (pos == maxpos) {
					break;
				}
				pos++;
			}
			message.flip();
			return message;
		}
		return null;
	}

	private void encodingDataType(ByteBuffer message, Object value, int pos) {
		if (value instanceof Short) {
			message.put(ByteConst.DATATYPE_SHORT);
			message.putShort(((Short) value).shortValue());
		} else if (value instanceof Integer) {
			message.put(ByteConst.DATATYPE_INTEGER);
			message.putInt(((Integer) value).intValue());
		} else if (value instanceof Long) {
			message.put(ByteConst.DATATYPE_LONG);
			message.putLong(((Long) value).longValue());
		} else if (value instanceof Float) {
			message.put(ByteConst.DATATYPE_FLOAT);
			message.putFloat(((Float) value).floatValue());
		} else if (value instanceof Double) {
			message.put(ByteConst.DATATYPE_DOUBLE);
			message.putDouble(((Double) value).doubleValue());
		} else if (value instanceof Byte) {
			message.put(ByteConst.DATATYPE_BYTE);
			message.put((Byte) value);
		} else if (value instanceof String) {
			String newValue = (String) value;
			if (pos == maxpos) {
				message.put(ByteConst.DATATYPE_LASTSTRING);
			} else if (newValue.length() > 32767) {
				message.put(ByteConst.DATATYPE_BIGSTRING);
				message.putInt(newValue.length());
			} else if (newValue.length() > 250) {
				message.put(ByteConst.DATATYPE_MIDSTRING);
				message.putShort(Short.valueOf("" + newValue.length()));
			} else {
				message.put(ByteConst.DATATYPE_STRING);
				message.put((byte) newValue.length());
			}
			message.put(newValue.getBytes());
		} else if (value instanceof Date) {
			message.put(ByteConst.DATATYPE_DATE);
			Date newValue = (Date) value;
			int integer = (int) newValue.getTime();
			message.putInt(integer);
		} else if (value instanceof byte[]) {
			byte[] newValue = (byte[]) value;

			if (pos == maxpos) {
				message.put(ByteConst.DATATYPE_LASTBYTEARRAY);
			} else if (newValue.length > 32767) {
				message.put(ByteConst.DATATYPE_BIGBYTEARRAY);
				message.putInt(newValue.length);
			} else if (newValue.length > 250) {
				message.put(ByteConst.DATATYPE_MIDBYTEARRAY);
				message.putShort(Short.valueOf("" + newValue.length));
			} else {
				message.put(ByteConst.DATATYPE_BYTEARRAY);
				message.put((byte) newValue.length);
			}
			message.put(newValue);
		} else if (value instanceof List<?>) {
			message.put(ByteConst.DATATYPE_LIST);
			short len = 0;
			for (Object listentity : (List<?>) value) {
				len += getValueLen(listentity, pos, null);
			}
			message.putShort(len);
			for (Object listentity : (List<?>) value) {
				encodingDataType(message, listentity, 0);
			}
		} else if (value instanceof Map<?, ?>) {
			message.put(ByteConst.DATATYPE_MAP);
			short len = 0;
			@SuppressWarnings("unchecked")
			Map<Object, Object> map = (Map<Object, Object>) value;
			for (Iterator<Entry<Object, Object>> i = map.entrySet().iterator(); i
					.hasNext();) {
				Entry<Object, Object> entity = i.next();
				len += getValueLen(entity.getKey());
				len += getValueLen(entity.getValue());
			}
			message.putShort(len);
			for (Iterator<Entry<Object, Object>> i = map.entrySet().iterator(); i
					.hasNext();) {
				Entry<Object, Object> entity = i.next();
				encodingDataType(message, entity.getKey(), 0);
				encodingDataType(message, entity.getValue(), 0);
			}
		} else if (value == null) {
			message.put(ByteConst.DATATYPE_NULL);
		}
	}

}
