package org.sdmlib.serialization.event.creator;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.event.StyleFormat;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
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

public class StyleFormatCreator implements SendableEntityCreator {
	/** The properties. */
	private final String[] properties = new String[] { StyleFormat.PROPERTY_FONTFAMILY,  StyleFormat.PROPERTY_FONTSIZE,  StyleFormat.PROPERTY_BOLD,  StyleFormat.PROPERTY_ITALIC };
	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new StyleFormat();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return ((StyleFormat)entity).get(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		return ((StyleFormat)entity).set(attribute, value);
	}

	public StyleFormat getNewFormat(StyleFormat format, String tag, Tokener value) {
		if("b".equalsIgnoreCase(tag)){
			if(!format.isBold()){
				StyleFormat newFormat = clone(format);
				newFormat.setBold(true);
				return newFormat;
			}
		}
		return null;
	}
	public StyleFormat clone(StyleFormat format){
		StyleFormat newFormat = (StyleFormat) getSendableInstance(false);
		for(String property : getProperties()){
			newFormat.set(property, format.get(property));
		}
		return newFormat;
	}
}
