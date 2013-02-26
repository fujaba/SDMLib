package org.sdmlib.serialization.bytes;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;

import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.interfaces.ByteItem;
import org.sdmlib.serialization.interfaces.JSIMEntity;

/*
Copyright (c) 2013, Stefan Lindel
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

THIS SOFTWARE 'Json Id Serialisierung Map' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
public class BitEntity implements JSIMEntity, ByteItem{
	public static final String BIT_STRING="string";
	public static final String BIT_NUMBER="number";
	public static final String BIT_BYTE="byte";
	public static final String BIT_REFERENCE="reference";

	private boolean isVisible=true;
	private ArrayList<BitValue> values=new ArrayList<BitValue>();
	
	// Can be a Typ 
	protected String property;
	protected String typ=BIT_BYTE;
	public static final String PROPERTY_PROPERTY="property";
	public static final String PROPERTY_TYP="typ";
	
	public BitEntity(Object value){
		if(value instanceof Byte){
			this.typ = BIT_BYTE;
			this.property = ""+value;
		}else if(value instanceof Integer){
			this.typ = BIT_NUMBER;
			this.property = ""+value;
		}else{
			this.typ = BIT_STRING;
			this.property = ""+value;
		}
	}
	
	public BitEntity(String property, String typ, int start, int len){
		this.property=property;
		this.typ = typ;
		this.values.add(new BitValue(start, len));
	}
	public BitEntity(String property, String typ){
		this.property=property;
		this.typ = typ;
	}
	public boolean addValue(BitValue value){
		return this.values.add(value);
	}
	
	public String getPropertyName(){
		return property;
	}
	
	public String getTyp(){
		return typ;
	}
	
	public boolean isTyp(String... referenceTyp){
		for(String typ : referenceTyp){
			if(this.typ.equals(typ)){
				return true;
			}
		}
		return false;
	}
	
	public Iterator<BitValue> valueIterator(){
		return values.iterator();
	}
	public int valueSize(){
		return values.size();
	}
	
	
	public boolean set(String attribute, Object value){
		if (attribute.equalsIgnoreCase(PROPERTY_PROPERTY)) {
			
		}
//			this.property = (String) value;
//			return true;
//		}else if (attribute.equalsIgnoreCase(PROPERTY_START)) {
//			if(value instanceof Integer){
//				this.start = new BitEntity(TYP_VALUE, ""+value);
//			}else{
//				this.start = new BitEntity(TYP_REFERENCE, ""+value);
//			}
//			return true;
//		}else if (attribute.equalsIgnoreCase(PROPERTY_LEN)) {
//			if(value instanceof Integer){
//				this.len = new BitEntity(TYP_VALUE, ""+value);
//			}else{
//				this.len = new BitEntity(TYP_REFERENCE, ""+value);
//			}
//			return true;
//		}else if (attribute.equalsIgnoreCase(PROPERTY_PROPERTY)) {
//			this.property = (String)value;
//			return true;
//		}else if (attribute.equalsIgnoreCase(PROPERTY_TYP)) {
//			this.typ = (String)value;
//			return true;
//		}
		return false;
	}
	
	/*
	 * Generic Getter for Attributes
	 */
	public Object get(String attrName) {
		String attribute;
		int pos = attrName.indexOf(".");
		if (pos > 0) {
			attribute = attrName.substring(0, pos);
		} else {
			attribute = attrName;
		}
		
//FIXME		if (attribute.equalsIgnoreCase(PROPERTY_NAME)) {
//			return this.property;
//		}else if (attribute.equalsIgnoreCase(PROPERTY_START)) {
//			return this.start;
//		}else if (attribute.equalsIgnoreCase(PROPERTY_LEN)) {
//			return this.len;
//		}else if (attribute.equalsIgnoreCase(PROPERTY_PROPERTY)) {
//			return this.property;
//		}else if (attribute.equalsIgnoreCase(PROPERTY_TYP)) {
//			return this.typ;
//		}
		return null;
	}

	@Override
	public ByteBuffer getBytes(boolean isDynamic) {
		return null;
	}

	@Override
	public int calcLength(boolean isDynamic) {
		return 0;
	}

	@Override
	public EntityList getNewArray() {
		return null;
	}

	@Override
	public JSIMEntity getNewObject() {
		return null;
	}
	
	@Override
	public String toString(ByteConverter converter) {
		// TODO Auto-generated method stub
		return null;
	}	
	@Override
	public String toString(ByteConverter converter, boolean isDynamic) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(int indentFactor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(int indentFactor, int intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVisible(boolean value) {
		this.isVisible=isVisible;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}
}
