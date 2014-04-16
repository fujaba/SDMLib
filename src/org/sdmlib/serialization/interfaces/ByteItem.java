package org.sdmlib.serialization.interfaces;

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

public interface ByteItem extends BaseEntity{
	/**
	 * @return the ByteItem as String
	 */
	@Override
	public String toString();

	/**
	 * @param converter ByteConverter for Format
	 * @return the ByteItem as String with converter
	 */
	public String toString(ByteConverter converter);

	/**
	 * @param converter ByteConverter for Format
	 * @param isDynamic ByteStream for minimize output
	 * @return the ByteItem as String 
	 */
	public String toString(ByteConverter converter, boolean isDynamic);

	/**
	 * @param isDynamic ByteStream for minimize output
	 * @return ByteStream 
	 */
	public BufferedBytes getBytes(boolean isDynamic);
	
	public void writeBytes(BufferedBytes buffer, boolean isDynamic, boolean last);

	/**
	 * @param isDynamic ByteStream for minimize output
	 * @return the Size of Bytes
	 */
	public int calcLength(boolean isDynamic);
}
