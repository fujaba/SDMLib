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
		return new String(sub);
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
		return new String(buffer);
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
		return (char) ((bytes[0]<<8)+bytes[1]);
	}

	@Override
	public short getShort() {
		byte[] bytes= converter(Short.SIZE);
		return (short) ((bytes[0]<<8)+bytes[1]);
	}

	@Override
	public long getLong() {
		byte[] bytes= converter(Long.SIZE);
		return (long) (
					(bytes[0]<<56)+
					(bytes[1]<<48)+
					(bytes[2]<<40)+
					(bytes[3]<<32)+
					(bytes[4]<<24)+
					(bytes[5]<<16)+
					(bytes[6]<<8)+
					bytes[7]);
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
		this.buffer=array;
		return this;
	}
}
