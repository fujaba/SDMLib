package org.sdmlib.serialization.bytes;

/*
 Json Id Serialisierung Map
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
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;
import org.sdmlib.serialization.interfaces.ByteItem;

public class BitEntity implements BaseEntityList, ByteItem {
	public static final String BIT_STRING = "string";
	public static final String BIT_NUMBER = "number";
	public static final String BIT_BYTE = "byte";
	public static final String BIT_REFERENCE = "reference";

	private boolean isVisible = true;
	private ArrayList<BitValue> values = new ArrayList<BitValue>();

	// Can be a Typ
	protected String property;
	protected String typ = BIT_BYTE;
	protected int orientation = 1;
	public static final String PROPERTY_PROPERTY = "property";
	public static final String PROPERTY_TYP = "typ";
	public static final String PROPERTY_ORIENTATION = "orientation";

	public BitEntity() {

	}

	public BitEntity(Object value) {
		if (value instanceof Byte) {
			this.typ = BIT_BYTE;
			this.property = "" + value;
		} else if (value instanceof Integer) {
			this.typ = BIT_NUMBER;
			this.property = "" + value;
		} else {
			this.typ = BIT_STRING;
			this.property = "" + value;
		}
	}

	public BitEntity(String property, String typ, int start, int len) {
		this.property = property;
		this.typ = typ;
		this.values.add(new BitValue(start, len));
	}

	public BitEntity(String property, String typ) {
		this.property = property;
		this.typ = typ;
	}

	public BitEntity(String field, String typ, int start, int len,
			int orientation) {
		this.property = field;
		this.typ = typ;
		this.orientation = orientation;
		this.values.add(new BitValue(start, len));
	}

	public boolean addValue(BitValue value) {
		return this.values.add(value);
	}

	public String getPropertyName() {
		return property;
	}

	public String getTyp() {
		return typ;
	}

	public boolean isTyp(String... referenceTyp) {
		for (String typ : referenceTyp) {
			if (this.typ.equals(typ)) {
				return true;
			}
		}
		return false;
	}

	public Iterator<BitValue> valueIterator() {
		return values.iterator();
	}

	public boolean set(String attribute, Object value) {
		if (PROPERTY_PROPERTY.equalsIgnoreCase(attribute)) {
			this.property = "" + value;
			return true;
		} else if (PROPERTY_TYP.equalsIgnoreCase(attribute)) {
			this.typ = "" + value;
			return true;
		} else if (PROPERTY_ORIENTATION.equalsIgnoreCase(attribute)) {
			this.orientation = Integer.valueOf("" + value);
			return true;
		}
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
		if (PROPERTY_PROPERTY.equalsIgnoreCase(attribute)) {
			return this.property;
		} else if (PROPERTY_TYP.equalsIgnoreCase(attribute)) {
			return this.typ;
		} else if (PROPERTY_ORIENTATION.equalsIgnoreCase(attribute)) {
			return this.orientation;
		}
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

	public BaseEntityList getNewArray() {
		return new BitEntity();
	}

	public BaseEntity getNewObject() {
		return null;
	}

	public String toString() {
		return super.toString();
	}

	public String toString(ByteConverter converter) {
		return toString();
	}

	public String toString(ByteConverter converter, boolean isDynamic) {
		return toString();
	}

	public String toString(int indentFactor) {
		return toString();
	}

	public String toString(int indentFactor, int intent) {
		return toString();
	}

	public void setVisible(boolean value) {
		this.isVisible = value;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public BaseEntityList initWithMap(Collection<?> value) {
		for (Iterator<?> i = value.iterator(); i.hasNext();) {
			values.add((BitValue) i.next());
		}
		return this;
	}

	public BaseEntityList put(Object value) {
		values.add((BitValue) value);
		return this;
	}

	public int size() {
		return values.size();
	}

	public boolean add(Object value) {
		return values.add((BitValue) value);
	}

	public Object get(int index) {
		return values.get(index);
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
}
