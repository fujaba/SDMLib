package org.sdmlib.serialization;

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

public class TextParsingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private String tokenerMsg;
	private int index;

	public TextParsingException(String message, int index) {
		this.message = message;
		this.index = index;
	}

	public TextParsingException(String message, Tokener tokener) {
		this.message = message;
		this.tokenerMsg = tokener.toString();
		this.index = tokener.position();
	}

	public String getMessage() {
		return message;
	}

	public String getTokenerMsg() {
		return tokenerMsg;
	}

	public int getIndex() {
		return index;
	}
}
