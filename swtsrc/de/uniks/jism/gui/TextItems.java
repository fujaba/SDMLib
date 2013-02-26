package de.uniks.jism.gui;
/*
Copyright (c) 2013, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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

import java.util.TreeMap;

import org.sdmlib.serialization.interfaces.PeerMessage;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;


public class TextItems implements SendableEntityCreator, PeerMessage {
	public static final String PROPERTY_VALUE="value";
	private TreeMap<String, String> values=new TreeMap<String, String>();
	@Override
	public Object getValue(Object entity, String attribute) {
		return ((TextItems)entity).get(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		return ((TextItems)entity).set(attribute, value);
	}
	
	
	@Override
	public Object get(String attribute) {
		if(values.containsKey(attribute)){
			return values.get(attribute);
		}
		return attribute;
	}
	
	public String getText(String label){
		if(values.containsKey(label)){
			return values.get(label);
		}
		return label;		
	}

	@Override
	public boolean set(String attribute, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getProperties() {
		return new String[]{PROPERTY_VALUE};
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		// TODO Auto-generated method stub
		return null;
	}


}
