package org.sdmlib.serialization;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BidiHashMap<K, V> {
	private Map<K, V> keyValue;
	private Map<V, K> valueKey;
	
	public BidiHashMap(){
		this.keyValue = getNewKeyValueMap();
		this.valueKey = getNewValueKeyMap();
	}
	private Map<K, V> getNewKeyValueMap(){
		return new HashMap<K, V>();
	}
	private Map<V, K> getNewValueKeyMap(){
		return new HashMap<V, K>();
	}
	
	public V getValue(K key){
		return keyValue.get(key);
	}
	
	public K getKey(V value){
		return valueKey.get(value);
	}
	public BidiHashMap<K, V> with(K key, V value){
		this.valueKey.put(value, key);
		this.keyValue.put(key, value);
		return this;
	}

	public boolean containsKey(String key){
		return keyValue.containsKey(key);
	}

	public boolean containsValue(Object value){
		return valueKey.containsKey(value);
	}

	
	public BidiHashMap<K, V> remove(K key, V value){
		this.valueKey.remove(value);
		this.keyValue.remove(key);
		return this;
	}
	
	public Collection<V> getValues(){
		return keyValue.values();
	}
	
	public Set<K> getKeys(){
		return keyValue.keySet();
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return keyValue.entrySet();
	}
	
	public int size(){
		return keyValue.size();
	}
	public void clear() {
		this.keyValue.clear();
		this.valueKey.clear();
	}
}
