package org.sdmlib.serialization.event;

import java.util.Map;
import java.util.Map.Entry;

public class MapEntry implements Entry<Object, Object>{
	private Object key;
	private Object value;
	
	public MapEntry(){
		
	}
	public MapEntry(Object key, Object value) {
		 this.key   = key;
		 this.value = value;
	}

	public Object setKey(Object key) {
		this.key=key;
		return key;
	}

	public Object getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public Object setValue(Object value) {
		this.value=value;
		return value;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Map.Entry))
			return false;
		Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
		return eq(key, e.getKey()) && eq(value, e.getValue());
	}

	public int hashCode() {
		return ((key == null) ? 0 : key.hashCode())
				^ ((value == null) ? 0 : value.hashCode());
	}

	public String toString() {
		return key + "=" + value;
	}

	private static boolean eq(Object o1, Object o2) {
		return (o1 == null ? o2 == null : o1.equals(o2));
	}
}

