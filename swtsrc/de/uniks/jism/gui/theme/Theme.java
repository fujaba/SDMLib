package de.uniks.jism.gui.theme;

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
import java.util.Map;
import org.sdmlib.serialization.Style;

public class Theme {
	/**
	 * The styles of this theme.
	 */
	protected Map<String, Style> styles=new LinkedHashMap<String, Style>();

	  /**
	   * Add style.
	   * @param styleKey the keyword of the style
	   * @param style the style
	   * @return see the return value of {@link Map#put(Object, Object)}
	   */
	  public Style addStyle(String styleKey, Style style) {
	    return styles.put(styleKey, style);
	  }
	  
	  /**
	   * Get the style by keyword.
	   * @param key the keyword
	   * @return the {@link syntaxhighlighter.theme.Style} related to the 
	   * {@code key}; if the style related to the {@code key} not exist, the 
	   * style of 'plain' will return.
	   */
	  public Style getStyle(String key) {
	    Style returnStyle = styles.get(key);
	    return returnStyle != null ? returnStyle : getStyle(StyleConstants.STYLE_PLAIN);
	  }
}
