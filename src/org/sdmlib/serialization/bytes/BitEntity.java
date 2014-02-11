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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.sdmlib.serialization.bytes.converter.ByteConverter;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;
import org.sdmlib.serialization.interfaces.BufferedBytes;
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

	public BitEntity withValue(Object value) {
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
		return this;
	}

	public BitEntity withValue(String property, String typ) {
		this.property = property;
		this.typ = typ;
		return this;
	}
	
	public BitEntity withStartLen(int start, int len){
		this.values.add(new BitValue(start, len));
		return this;
	}

	public BitEntity withOrientation(int orientation) {
		this.orientation = orientation;
		return this;
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
			this.orientation = Integer.parseInt(""+value);
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
	public BufferedBytes getBytes(boolean isDynamic) {
		return null;
	}
	@Override
	public void writeBytes(BufferedBytes buffer, boolean isDynamic, boolean last) {
		// FIXME
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

	public BitEntity withVisible(boolean value) {
		this.isVisible = value;
		return this;
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
}
