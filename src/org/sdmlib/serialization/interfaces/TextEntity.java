package org.sdmlib.serialization.interfaces;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or higher as soon they
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

public interface TextEntity extends BaseEntity{
	/**
	 * @param key The Key for Item
	 * @param value The Vlaue of Item
	 */
	public void put(String key, Object value);
	
	/**
	 * Check if the Map has the key
	 * @param key for searching
	 * @return boolean if the Map has the key
	 */
	public boolean has(String key);
	
	public String getString(String key);
	
	public Object get(String key);
}
