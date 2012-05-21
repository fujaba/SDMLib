package org.sdmlib.serialization.bytes;

import java.nio.ByteBuffer;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ByteEntityMessage extends ByteEntity{
	protected boolean isFixed;
	
	public ByteEntityMessage(){	
	}

	public ByteEntityMessage(boolean dynamic, boolean checkLen){
		this.setDynamic(dynamic);
		this.setLenCheck(checkLen);
	}

	public boolean setLenCheck(boolean isLenCheck) {
		if(!isFixed){
			this.isLenCheck = isLenCheck;
			return true;
		}
		return false;
	}


	public void setMsgTyp(byte msgTyp) {
		this.typ=msgTyp;
	}
	public void setMsgTyp(String className) {
		this.typ=ByteIdMap.DATATYPE_CLAZZ;
		values=className.getBytes();
	}
	public void setFullMsg(String message){
		this.values=message.getBytes();
		isFixed=true;
	}
	
	@Override
	public void addChild(SendableEntityCreator creator, Object entity,
			Object referenceObject, String property, ByteIdMap parent) {
		if(!isFixed){
			super.addChild(creator, entity, referenceObject, property,parent);
		}
	}
	
	public ByteBuffer getBytes(){
		ByteBuffer bytes = super.getBytes();
		bytes.flip();
		return bytes;
	}
}
