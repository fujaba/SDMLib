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

public class HTMLRegExRule extends RegExRule{
	/**
	   * Common HTML script RegExp.
	   */
	  public static final HTMLRegExRule phpScriptTags = new HTMLRegExRule("(?:&lt;|<)\\?=?", "\\?(?:&gt;|>)");
	  /**
	   * Common HTML script RegExp.
	   */
	  public static final HTMLRegExRule aspScriptTags = new HTMLRegExRule("(?:&lt;|<)%=?", "%(?:&gt;|>)");
	  /**
	   * Common HTML script RegExp.
	   */
	  public static final HTMLRegExRule scriptScriptTags = new HTMLRegExRule("(?:&lt;|<)\\s*script.*?(?:&gt;|>)", "(?:&lt;|<)\\/\\s*script\\s*(?:&gt;|>)");
	  /**
	   * The regular expression of the left tag.
	   */
	  protected String left;
	  /**
	   * The regular expression of the right tag.
	   */
	  protected String right;

	  /**
	   * Constructor.
	   * @param left the regular expression of the left tag, cannot be null
	   * @param right the regular expression of the right tag, cannot be null
	   */
	  public HTMLRegExRule(String left, String right) {
	    this.left = left;
	    this.right = right;
	  }

	  /**
	   * Get the pattern of this HTML script RegExp.
	   * It is a combination of left and right tag and some pattern to match the 
	   * in-between content. Group 1 is the left tag, group 2 is the inner content, 
	   * group 3 is the right tag.
	   * 
	   * @return the pattern with flags: CASE_INSENSITIVE and DOTALL
	   */
	  public Pattern getPattern() {
	    return Pattern.compile("(" + left + ")(.*?)(" + right + ")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	  }
	  
	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();

	    sb.append(getClass().getName());
	    sb.append(":[");
	    sb.append("left: ");
	    sb.append(left);
	    sb.append("right: ");
	    sb.append(right);
	    sb.append("]");

	    return sb.toString();
	  }
}
