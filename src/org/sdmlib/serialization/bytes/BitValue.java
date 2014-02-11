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

public class BitValue {
	private BitEntity start;
	private BitEntity len;
	private int orientation = 1;

	public BitValue(int start, int len) {
		this.start = new BitEntity().withValue(start);
		this.len = new BitEntity().withValue(len);
	}

	public BitValue(String startTyp, String startValue, String lentyp,
			String lenvalue) {
		this.start = new BitEntity().withValue(startValue, startTyp);
		this.len = new BitEntity().withValue(lenvalue, lentyp);
	}

	public BitEntity getStart() {
		return start;
	}

	public BitValue withStart(BitEntity start) {
		this.start = start;
		return this;
	}

	public BitEntity getLen() {
		return len;
	}

	public BitValue withLen(BitEntity len) {
		this.len = len;
		return this;
	}

	public BitValue withOrientation(int value) {
		this.orientation = value;
		return this;
	}

	public int getOrientation() {
		return orientation;
	}
}
