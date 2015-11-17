package org.sdmlib.models.classes;

import de.uniks.networkparser.list.SimpleKeyValueList;

public class MapDataType extends DataType {

	private DataType genericKey;
	
	private DataType genericValue;
	
	MapDataType() {
		super(SimpleKeyValueList.class.getName());
		this.clazzValue.withExternal(true);
	}
	
	public static MapDataType ref(Clazz key, Clazz value) {
		MapDataType result = new MapDataType().withGenericKey(DataType.ref(key)).withGenericValue(DataType.ref(value));
		return result;
	}
	public static MapDataType ref(String key, String value) {
		MapDataType result = new MapDataType().withGenericKey(DataType.ref(key)).withGenericValue(DataType.ref(value));
		return result;
	}
	
	public static MapDataType ref(DataType key, DataType value) {
		MapDataType result = new MapDataType().withGenericKey(key).withGenericValue(value);
		return result;
	}

	private MapDataType withGenericKey(DataType key) {
		this.genericKey = key;
		return this;
	}

	private MapDataType withGenericValue(DataType value) {
		this.genericValue = value;
		return this;
	}
	
	public DataType getGenericKey() {
		return genericKey;
	}
	
	public DataType getGenericValue() {
		return genericValue;
	}
	
	@Override
	public String getValue() {
		if (this.clazzValue == null) {
			return null;
		}
		return this.clazzValue.getName() + "<" + genericKey.getValue() + "," + genericValue.getValue() + ">";
	}

}
