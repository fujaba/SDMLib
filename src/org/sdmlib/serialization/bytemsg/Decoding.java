package org.sdmlib.serialization.bytemsg;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.sdmlib.serialization.event.ByteMessage;
import org.sdmlib.serialization.event.UnknownMessage;
import org.sdmlib.serialization.exception.DecodeException;
import org.sdmlib.serialization.interfaces.PrimaryEntityCreator;


public class Decoding {
	private ByteIdMap parent;

	public Decoding(ByteIdMap parent){
		this.parent=parent;
	}

	public Object decode(Object value) throws DecodeException {
		if (value instanceof ByteBuffer) {
			return decode((ByteBuffer) value);
		} else if (value instanceof byte[]) {
			return decode(ByteBuffer.wrap((byte[]) value));
		}
		return null;
	}

	public Object decode(ByteBuffer in) throws DecodeException {
		if (in.remaining() < 1)
			throw new DecodeException(in.remaining());

		Object entity = null;
		byte typ = in.get();
		PrimaryEntityCreator eventCreater = parent.getCreatorDecoderClass(typ);
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
					if (typValue == ByteConst.DATATYPE_CHECK) {
						int len = in.getInt();
						if (len > 0) {
							if (in.remaining() != len)
								throw new DecodeException(in.remaining());
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
		} else if (typValue == ByteConst.DATATYPE_BYTE) {
			return in.get();
		} else if (typValue == ByteConst.DATATYPE_SHORT) {
			return in.getShort();
		} else if (typValue == ByteConst.DATATYPE_INTEGER) {
			return in.getInt();
		} else if (typValue == ByteConst.DATATYPE_LONG) {
			return in.getLong();
		} else if (typValue == ByteConst.DATATYPE_FLOAT) {
			return in.getFloat();
		} else if (typValue == ByteConst.DATATYPE_DOUBLE) {
			return in.getDouble();
		} else if (typValue == ByteConst.DATATYPE_STRING) {
			byte len = in.get();
			byte[] value = new byte[len];
			in.get(value);
			return new String(value);
		} else if (typValue == ByteConst.DATATYPE_MIDSTRING) {
			short len = in.getShort();
			byte[] value = new byte[len];
			in.get(value);
			return new String(value);
		} else if (typValue == ByteConst.DATATYPE_BIGSTRING) {
			int len = in.getInt();
			byte[] value = new byte[len];
			in.get(value);
			return new String(value);
		} else if (typValue == ByteConst.DATATYPE_LASTSTRING) {
			byte[] value = new byte[in.remaining()];
			in.get(value);
			return new String(value);
		} else if (typValue == ByteConst.DATATYPE_DATE) {
			int value = in.getInt();
			Date newValue = new Date(value);
			return newValue;
		} else if (typValue == ByteConst.DATATYPE_BYTEARRAY) {
			byte len = in.get();
			byte[] value = new byte[len];
			in.get(value);
			return value;
		} else if (typValue == ByteConst.DATATYPE_MIDBYTEARRAY) {
			short len = in.getShort();
			byte[] value = new byte[len];
			in.get(value);
			return value;
		} else if (typValue == ByteConst.DATATYPE_BIGBYTEARRAY) {
			int len = in.getInt();
			byte[] value = new byte[len];
			in.get(value);
			return value;
		} else if (typValue == ByteConst.DATATYPE_LASTBYTEARRAY) {
			byte[] value = new byte[in.remaining()];
			in.get(value);
			return value;
		} else if (typValue == ByteConst.DATATYPE_LIST) {
			short len = in.getShort();
			int pos = in.position();
			ArrayList<Object> list = new ArrayList<Object>();
			while (in.remaining() > 0 && in.position() <= pos + len) {
				Byte subType = in.get();
				Object entity = getDecodeObject(subType, in);
				if (entity != null) {
					list.add(list);
				}
			}
			return list;
		} else if (typValue == ByteConst.DATATYPE_MAP) {
			short len = in.getShort();
			int pos = in.position();
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			while (in.remaining() > 0 && in.position() <= pos + len) {
				Byte subType = in.get();
				Object key = getDecodeObject(subType, in);
				if (key != null) {
					subType = in.get();
					Object value = getDecodeObject(subType, in);
					if (key != null) {
						map.put(key, value);
					}
				}
			}
			return map;
		}
		return null;
	}
}
