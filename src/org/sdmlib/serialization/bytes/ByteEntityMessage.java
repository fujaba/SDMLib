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
