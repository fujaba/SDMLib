package de.uniks.jism.gui.brush;

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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.sdmlib.serialization.Style;

public class RegExRule {
	/**
	 * The compiled pattern.
	 */
	protected Pattern pattern;
	/**
	 * Override Style
	 */
	protected Style style;

	protected HashMap<Integer, Object> groupOperations = new HashMap<Integer, Object>();
	
	public RegExRule(){
		groupOperations.put(0, null);
	}

	/**
	 * Get the compiled pattern
	 * 
	 * @return the pattern
	 */
	public Pattern getPattern() {
		return pattern;
	}

	public Style getStyle() {
		return style;
	}
	
	public void setStyleKey(String value) {
		this.groupOperations.put(0, value);
	}
	
	public String getStyleKey() {
		if (groupOperations.size() == 1) {
			return (String) groupOperations.get(0);
		}
		return null;
	}

	/**
	 * Get the map of group operations. For more details, see
	 * {@link #groupOperations}.
	 * 
	 * @return a copy of the group operations map
	 */
	public Map<Integer, Object> getGroupOperations() {
		return new HashMap<Integer, Object>(groupOperations);
	}
	
	public void addToGroupOperation(Object... values){
		int startIndex=groupOperations.size();
		for(int i=0;i<values.length;i++){
			this.groupOperations.put(startIndex++, values[i]);
		}
	}
}
