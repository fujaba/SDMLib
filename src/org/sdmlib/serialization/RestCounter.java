package org.sdmlib.serialization;

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

public class RestCounter extends SimpleIdCounter{
	public RestCounter(String path){
		if(path.endsWith("/")){
			this.prefixId = path;
		}else{
			this.prefixId = path+"/";
		}
	}
	
	@Override
	public String getId(Object obj) {
		String key;

		// new object generate key and add to tables
		// <session id>.<first char><running number>
		if (obj == null) {
			return "";
		}
		String className = obj.getClass().getName();
		key = this.prefixId + className.toLowerCase() +"/"+ this.number;
		this.number++;
		return key;
	}
}
