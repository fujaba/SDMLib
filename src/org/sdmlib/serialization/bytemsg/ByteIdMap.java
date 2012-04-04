package org.sdmlib.serialization.bytemsg;

import java.nio.ByteBuffer;
import java.util.HashMap;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.ByteEntityCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ByteIdMap extends IdMap{
	public static final byte STDID = 0x23;
	protected HashMap<Byte, ByteEntityCreator> decoderMap;
	private Encoding encoder;
	private Decoding decoder;
	public static char SPLITTER = ' ';
	private boolean lenCheck;

	public ByteIdMap() {

	}

	public void setCheckLen(boolean checklen) {
		this.lenCheck = checklen;
		if (encoder != null) {
			encoder.setCheckLen(checklen);
		}
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
	
	public ByteBuffer encode(Object entity) {
		if (encoder == null) {
			encoder = new Encoding(this, lenCheck);
		}
		return encoder.encode(entity);
	}

	public String encodeHTTP(Object element) {
		ByteBuffer bytes = encode(element);
		if (bytes == null) {
			return null;
		}
		StringBuffer returnValue = new StringBuffer();
		for (int i = 0; i < bytes.limit(); i++) {
			byte value = bytes.get(i);
			if (value <= 32 || value == 127) {
				returnValue.append(SPLITTER);
				returnValue.append((char) (value + SPLITTER + 1));
			} else {
				returnValue.append((char) value);
			}
		}
		return returnValue.toString();
	}

	public Object decode(Object bytes) throws RuntimeException {
		if (decoder == null) {
			decoder = new Decoding(this);
		}
		return decoder.decode(bytes);
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
}
