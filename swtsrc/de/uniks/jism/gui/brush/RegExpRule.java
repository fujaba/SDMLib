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

import java.util.regex.Pattern;
import org.sdmlib.serialization.Style;

public class RegExpRule extends RegExRule {
	/**
	 * Common regular expression rule.
	 */
	public static final Pattern multiLineCComments = Pattern.compile(
			"\\/\\*[\\s\\S]*?\\*\\/", Pattern.MULTILINE);
	/**
	 * Common regular expression rule.
	 */
	public static final Pattern singleLineCComments = Pattern.compile(
			"\\/\\/.*$", Pattern.MULTILINE);
	/**
	 * Common regular expression rule.
	 */
	public static final Pattern singleLinePerlComments = Pattern.compile(
			"#.*$", Pattern.MULTILINE);
	/**
	 * Common regular expression rule.
	 */
	public static final Pattern doubleQuotedString = Pattern
			.compile("\"([^\\\\\"\\n]|\\\\.)*\"");
	/**
	 * Common regular expression rule.
	 */
	public static final Pattern singleQuotedString = Pattern
			.compile("'([^\\\\'\\n]|\\\\.)*'");
	/**
	 * Common regular expression rule.
	 */
	public static final Pattern multiLineDoubleQuotedString = Pattern.compile(
			"\"([^\\\\\"]|\\\\.)*\"", Pattern.DOTALL);
	/**
	 * Common regular expression rule.
	 */
	public static final Pattern multiLineSingleQuotedString = Pattern.compile(
			"'([^\\\\']|\\\\.)*'", Pattern.DOTALL);
	/**
	 * Common regular expression rule.
	 */
	public static final Pattern xmlComments = Pattern
			.compile("\\w+:\\/\\/[\\w-.\\/?%&=:@;]*");

	/**
	 * Constructor.
	 * 
	 * @param regExp
	 *            the regular expression for this rule
	 * @param styleKey
	 *            the style key, the style to apply to the matched result
	 */
	public RegExpRule(String regExp, String styleKey) {
		this.pattern = Pattern.compile(regExp, 0);
		setStyleKey(styleKey);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param regExp
	 *            the regular expression for this rule
	 * @param styleKey
	 *            the style key, the style to apply to the matched result
	 */
	public RegExpRule(Pattern regExp, String styleKey) {
		this.pattern = regExp;
		setStyleKey(styleKey);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param regExp
	 *            the regular expression for this rule
	 * @param regFlags
	 *            the flags for the regular expression, see the flags in
	 *            {@link java.util.regex.Pattern}
	 * @param styleKey
	 *            the style key, the style to apply to the matched result
	 */
	public RegExpRule(String regExp, int regFlags) {
		this.pattern = Pattern.compile(regExp, regFlags);
	}

	/**
	 * Constructor.
	 * 
	 * @param regExp
	 *            the regular expression for this rule
	 * @param regFlags
	 *            the flags for the regular expression, see the flags in
	 *            {@link java.util.regex.Pattern}
	 * @param styleKey
	 *            the style key, the style to apply to the matched result
	 */
	public RegExpRule(String regExp, int regFlags, String styleKey) {
		this.pattern = Pattern.compile(regExp, regFlags);
		setStyleKey(styleKey);
	}
	
	  /**
	   * Constructor.
	   * @param regExp the regular expression for this rule
	   * @param styleKey the style key, the style to apply to the matched result
	   */
	public RegExpRule(String regExp, String styleKey, Style style) {
		this.pattern = Pattern.compile(regExp, 0);
		this.style = style;
		setStyleKey(styleKey);
	}

	/**
	 * Constructor.
	 * 
	 * @param regExp
	 *            the regular expression for this rule
	 * @param regFlags
	 *            the flags for the regular expression, see the flags in
	 *            {@link java.util.regex.Pattern}
	 * @param styleKey
	 *            the style key, the style to apply to the matched result
	 */
	public RegExpRule(String regExp, int regFlags, String styleKey, Style style) {
		this.pattern = Pattern.compile(regExp, regFlags);
		this.style = style;
		setStyleKey(styleKey);
	}

}
