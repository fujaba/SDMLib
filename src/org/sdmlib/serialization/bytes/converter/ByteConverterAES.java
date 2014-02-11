package org.sdmlib.serialization.bytes.converter;

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
import org.sdmlib.serialization.bytes.checksum.AES;

public class ByteConverterAES extends ByteConverter{
	private AES aes;

	public void setKey(String value){
		if(this.aes==null){
			this.aes = new AES();
		}
		this.aes.setKey(value);
	}
	
	public String toString(String values) {
		return aes.encode(values);
	}
	
	@Override
	public String toString(byte[] values, int size) {
		return this.aes.encodeBytes(values);
	}

	@Override
	public byte[] decode(String value) {
		return aes.decodeString(value);
	}

	public String decodeString(String value) {
		return aes.decode(value);
	}
}
