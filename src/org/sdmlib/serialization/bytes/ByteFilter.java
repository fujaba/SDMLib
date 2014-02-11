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
import org.sdmlib.serialization.Filter;

public class ByteFilter extends Filter {
	private boolean isDynamic;
	private boolean isLenCheck;

	public boolean isLenCheck() {
		return isLenCheck;
	}

	public ByteFilter withLenCheck(boolean value) {
		this.isLenCheck = value;
		return this;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public ByteFilter withDynamic(boolean value) {
		this.isDynamic = value;
		return this;
	}
	
	@Override
	public Filter cloneObj() {
		ByteFilter newInstance = (ByteFilter) super.clone(new ByteFilter());
		newInstance.withDynamic(this.isDynamic);
		newInstance.withLenCheck(this.isLenCheck);
		return newInstance;
	}

	public String getCharset() {
		return "UTF-8";
	}
}
