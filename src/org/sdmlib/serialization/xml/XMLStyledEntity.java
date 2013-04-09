package org.sdmlib.serialization.xml;
import org.sdmlib.serialization.Style;
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

THIS SOFTWARE 'Json Id Serialisierung Map' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
import org.sdmlib.serialization.interfaces.PeerMessage;

public class XMLStyledEntity extends XMLEntity implements PeerMessage{
	private Style style=new Style();
	
	@Override
	protected String toStringValue(int indentFactor) {
		StringBuilder sb = new StringBuilder();

		// Starttag
		if (style.isBold()) {
			sb.append("<b>");
		}
		if (style.isItalic()) {
			sb.append("<i>");
		}
		sb.append(super.toStringValue(indentFactor));

		// EndTag
		if (style.isItalic()) {
			sb.append("</i>");
		}
		if (style.isBold()) {
			sb.append("</b>");
		}
		return sb.toString();
	}


	@Override
	public boolean set(String attribute, Object value) {
		if(style.set(attribute, value)){
			return true;
		}
		return false;
	}
	@Override
	public Object get(String key) {
		Object attrValue=style.get(key);
		if(attrValue!=null){
			return attrValue;
		}
		return super.get(key);
	}


	public boolean isBold() {
		return style.isBold();
	}


	public void setBold(boolean value) {
		style.setBold(value);
	}
}
