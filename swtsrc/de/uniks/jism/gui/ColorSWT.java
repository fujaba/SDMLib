package de.uniks.jism.gui;

/*
 Json Id Serialisierung Map
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

import java.util.LinkedHashMap;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorSWT {
	protected LinkedHashMap<String, Color> colors = new LinkedHashMap<String, Color>();
	private RGB getRGB(String value) {
		
		String hexVal = "0123456789ABCDEF";
		if(value.length()==8){
			value=value.substring(2).toUpperCase();
		}else if(value.length()==7){
			value=value.substring(1).toUpperCase();
		}else if(value.length()==6){
			value=value.toUpperCase();
		}else{
			return null;
		}
		int data[] = new int[value.length() / 2];
		for (int i = 0; i < value.length(); i += 2) {
			data[i / 2] = hexVal.indexOf(value.charAt(i)) * 16
					+ hexVal.indexOf(value.charAt(i + 1));
		}
		return new RGB(data[0], data[1], data[2]);
	}

	public Color getColor(String value) {
		if(value==null){
			return null;
		}
		Color color = colors.get(value);
		if (color != null) {
			return color;
		}
		Display display = Display.getDefault();
		color = new Color(display, getRGB(value));
		colors.put(value, color);
		return color;
	}
}
