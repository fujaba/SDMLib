package org.sdmlib.serialization.xml;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
import java.util.ArrayList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XsdValidationLoggingErrorHandler implements ErrorHandler {
	private boolean isValid=true;
	private ArrayList<String> warnings=new ArrayList<String>();
	private ArrayList<String> errors=new ArrayList<String>();
	public void warning(SAXParseException ex) throws SAXException {
		isValid = false;
		warnings.add("Warnung: " + ex.getMessage());
	}

	public void error(SAXParseException ex) throws SAXException {
		isValid = false;
		errors.add("Fehler: " + ex.getMessage());
	}

	public void fatalError(SAXParseException ex) throws SAXException {
		isValid = false;
		errors.add("Fataler Fehler: " + ex.getMessage());
	}

	public boolean isValid() {
		return isValid;
	}
	
	public ArrayList<String> getErrors(){
		return errors;
	}
	public String getErrorText(){
		StringBuilder sb=new StringBuilder();
		for(String item : errors){
			sb.append(item+"\n");
		}
		sb.append("ERRORS: "+errors.size());
		return sb.toString();
	}
	public ArrayList<String> getWarnings(){
		return warnings;
	}
}
