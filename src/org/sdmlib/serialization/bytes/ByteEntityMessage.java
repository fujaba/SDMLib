package org.sdmlib.serialization.bytes;
/*
Copyright (c) 2012 Stefan Lindel

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.nio.ByteBuffer;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

/**
 * The Class ByteEntityMessage.
 */
public class ByteEntityMessage extends ByteEntity{
	
	/** The is fixed. */
	protected boolean isFixed;
	
	/**
	 * Instantiates a new byte entity message.
	 */
	public ByteEntityMessage(){	
	}

	/**
	 * Instantiates a new byte entity message.
	 *
	 * @param dynamic the dynamic
	 * @param checkLen the check len
	 */
	public ByteEntityMessage(boolean dynamic, boolean checkLen){
		this.setDynamic(dynamic);
		this.setLenCheck(checkLen);
	}

	/*
	 * @see de.uni.kassel.peermessage.bytes.ByteEntity#setLenCheck(boolean)
	 */
	public boolean setLenCheck(boolean isLenCheck) {
		if(!isFixed){
			this.isLenCheck = isLenCheck;
			return true;
		}
		return false;
	}


	/**
	 * Sets the msg typ.
	 *
	 * @param msgTyp the new msg typ
	 */
	public void setMsgTyp(byte msgTyp) {
		this.typ=msgTyp;
	}
	
	/**
	 * Sets the msg typ.
	 *
	 * @param className the new msg typ
	 */
	public void setMsgTyp(String className) {
		this.typ=ByteIdMap.DATATYPE_CLAZZ;
		values=className.getBytes();
	}
	
	/**
	 * Sets the full msg.
	 *
	 * @param message the new full msg
	 */
	public void setFullMsg(String message){
		this.values=message.getBytes();
		isFixed=true;
	}
	
	/* 
	 * @see de.uni.kassel.peermessage.bytes.ByteEntity#addChild(de.uni.kassel.peermessage.interfaces.SendableEntityCreator, java.lang.Object, java.lang.Object, java.lang.String, de.uni.kassel.peermessage.bytes.ByteIdMap)
	 */
	@Override
	public void addChild(SendableEntityCreator creator, Object entity,
			Object referenceObject, String property, ByteIdMap parent) {
		if(!isFixed){
			super.addChild(creator, entity, referenceObject, property,parent);
		}
	}
	
	/*
	 * @see de.uni.kassel.peermessage.bytes.ByteEntity#getBytes()
	 */
	public ByteBuffer getBytes(){
		ByteBuffer bytes = super.getBytes();
		bytes.flip();
		return bytes;
	}
}
