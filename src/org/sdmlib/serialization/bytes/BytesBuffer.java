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
import org.sdmlib.serialization.interfaces.Buffer;
import org.sdmlib.serialization.interfaces.BufferedBytes;

public class BytesBuffer implements BufferedBytes {
	/** The buffer. */
	protected byte[] buffer;

	/** The index. */
	protected int index;
	
	@Override
	public int length() {
		return buffer.length;
	}

	@Override
	public char charAt(int index) {
		return (char) buffer[index];
	}
	
	@Override
	public byte byteAt(int index) {
		return buffer[index];
	}

	@Override
	public String substring(int startTag, int length) {
		byte[] sub=new byte[length];
		for(int i=0;i<length;i++){
			sub[i]=buffer[startTag+length];
		}
		return String.valueOf(sub);
	}

	@Override
	public Buffer withLength(int length) {
		this.buffer=new byte[length];
		return this;
	}

	@Override
	public int position() {
		return index;
	}

	@Override
	public int remaining() {
		return buffer.length - index;
	}

	@Override
	public void back() {
		if(index>0){
			index--;
		}
	}

	@Override
	public boolean isEnd() {
		return buffer.length <= index;
	}

	@Override
	public Buffer withPosition(int value) {
		this.index = value;
		return this;
	}

	@Override
	public String toText() {
		return String.valueOf(buffer);
	}

	@Override
	public byte[] toArray() {
		return buffer;
	}

	@Override
	public byte getByte() {
		return this.buffer[index++];
	}

	
	private byte[] converter(int bits){
		int len = bits/8;
		byte[] buffer=new byte[len];
		
		for(int i=0;i<len;i++){
			buffer[i] = this.buffer[index++];
		}
		return buffer;
	}
	
	@Override
	public char getChar() {
		byte[] bytes= converter(Character.SIZE);
		char result=(char)bytes[0];
		result = (char) (result<<8 + (char)bytes[1]);
		return result;
	}

	@Override
	public short getShort() {
		byte[] bytes= converter(Short.SIZE);
		short result = bytes[0];
		result = (short) (result << 8 + bytes[1]);
		return result;
	}

	@Override
	public long getLong() {
		byte[] bytes= converter(Long.SIZE);
		long result=bytes[0];
		result = result<<8+bytes[1];
		result = result<<8+bytes[2];
		result = result<<8+bytes[3];
		result = result<<8+bytes[4];
		result = result<<8+bytes[5];
		result = result<<8+bytes[6];
		result = result<<8+bytes[7];
		return result;
	}

	@Override
	public int getInt() {
		byte[] bytes= converter(Integer.SIZE);
		return (int) (
				(bytes[0]<<24)+
				(bytes[1]<<16)+
				(bytes[2]<<8)+
				bytes[3]);
	}

	@Override
	public float getFloat() {
		int asInt = getInt();
		return Float.intBitsToFloat(asInt);
	}

	@Override
	public double getDouble() {
		long asLong=getLong();
		return Double.longBitsToDouble(asLong);
	}

	public byte[] getValue(int len) {
		byte[] array = new byte[len];
		for(int i=0;i<len;i++){
			array[i]=getByte();
		}
		return array;
	}
	
	
	@Override
	public byte[] getValue(int start, int len) {
		this.withPosition(start);

		byte[] array = new byte[len];
		for(int i=0;i<len;i++){
			array[i]=getByte();
		}
		return array;
	}

	@Override
	public byte[] array() {
		return buffer;
	}

	@Override
	public void put(byte value) {
		this.buffer[index++] = value;
	}
	
	@Override
	public void put(short value) {
		this.buffer[index++] = (byte) (value & 0xff<<8);
		this.buffer[index++] = (byte) (value & 0xff);
	}
	
	@Override
	public void put(int value) {
		this.buffer[index++] = (byte) (value & 0xff<<24);
		this.buffer[index++] = (byte) (value & 0xff<<16);
		this.buffer[index++] = (byte) (value & 0xff<<8);
		this.buffer[index++] = (byte) (value & 0xff);
	}

	@Override
	public void put(long value) {
		this.buffer[index++] = (byte) (value & 0xff<<56);
		this.buffer[index++] = (byte) (value & 0xff<<48);
		this.buffer[index++] = (byte) (value & 0xff<<40);
		this.buffer[index++] = (byte) (value & 0xff<<32);
		this.buffer[index++] = (byte) (value & 0xff<<24);
		this.buffer[index++] = (byte) (value & 0xff<<16);
		this.buffer[index++] = (byte) (value & 0xff<<8);
		this.buffer[index++] = (byte) (value & 0xff);
	}
	
	@Override
	public void put(char value) {
		this.buffer[index++] = (byte) ((value&0xFF00)>>8);
		this.buffer[index++] = (byte) (value&0x00FF);
	}
	
	@Override
	public void put(float value) {
		int bits = Float.floatToIntBits(value);
		this.buffer[index++] = (byte)(bits & 0xff);
		this.buffer[index++] = (byte)((bits >> 8) & 0xff);
		this.buffer[index++] = (byte)((bits >> 16) & 0xff);
		this.buffer[index++] = (byte)((bits >> 24) & 0xff);
	}

	@Override
	public void put(double value) {
		long bits = Double.doubleToLongBits(value);
		this.buffer[index++] = (byte)((bits >> 56) & 0xff);
		this.buffer[index++] = (byte)((bits >> 48) & 0xff);
		this.buffer[index++] = (byte)((bits >> 40) & 0xff);
		this.buffer[index++] = (byte)((bits >> 32) & 0xff);
		this.buffer[index++] = (byte)((bits >> 24) & 0xff);
		this.buffer[index++] = (byte)((bits >> 16) & 0xff);
		this.buffer[index++] = (byte)((bits >> 8) & 0xff);
		this.buffer[index++] = (byte)((bits >> 0) & 0xff);
	}


	@Override
	public void put(byte[] value) {
		for(int i=0;i<value.length;i++){
			put(value[i]);
		}
	}

	@Override
	public void put(byte[] value, int offset, int length) {
		for(int i=0;i<length;i++){
			put(value[offset+i]);
		}
	}

	@Override
	public void flip() {
		this.index=0;
	}

	@Override
	public BufferedBytes getNewBuffer(int capacity) {
		new BytesBuffer().withLength(capacity);
		return this;
	}
	
	public static BytesBuffer allocate(int len){
		BytesBuffer bytesBuffer = new BytesBuffer();
		bytesBuffer.withLength(len);
		return bytesBuffer;
	}

	@Override
	public BufferedBytes getNewBuffer(byte[] array) {
		return new BytesBuffer().withValue(array);
	}
	
	public BytesBuffer withValue(byte[] array){
		this.buffer=array.clone();
		return this;
	}
}
