package org.sdmlib.models.classes;

import de.uniks.networkparser.list.SimpleSet;

public class SetDataType extends DataType{
	private DataType generic;
	
	SetDataType() {
		super(SimpleSet.class.getName());
		this.clazzValue.withExternal(true);
	}
	
	public static SetDataType ref(Clazz value) {
		SetDataType result = new SetDataType().withGeneric(DataType.ref(value));
		return result;
	}
	public static SetDataType ref(String value) {
		SetDataType result = new SetDataType().withGeneric(DataType.ref(value));
		return result;
	}
	
	public static SetDataType ref(DataType value) {
		SetDataType result = new SetDataType().withGeneric(value);
		return result;
	}


	private SetDataType withGeneric(DataType value) {
		this.generic = value;
		return this;
	}

	public DataType getGeneric() {
		return generic;
	}
	
	@Override
	public String getValue() {
		if (this.clazzValue == null) {
			return null;
		}
		return this.clazzValue.getName() + "<" + generic.getValue() + ">";
	}

}
