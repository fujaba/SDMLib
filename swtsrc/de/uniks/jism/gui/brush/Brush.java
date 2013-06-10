package de.uniks.jism.gui.brush;

/*
 java-syntax-highlighter
 Copyright (c) 2011 Chan Wai Shing

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.util.ArrayList;
import java.util.List;
/**
 * Brush for syntax highlighter.
 * 
 * In syntax highlighter, every supported programming language has its own 
 * brush. Brush contain a set of rules, the highlighter/parser will use these 
 * rules to determine the structure of the code and apply different color to 
 * different group of component.
 * 
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class Brush {
	/**
   * Regular expression rules list. It will be executed in sequence.
   */
  protected List<RegExRule> rules;
  /**
   * The list of common file extension for this language. It is no use so far, 
   * just for reference.
   */
  protected List<String> commonFileExtensionList;

  /**
   * Constructor.
   */
  public Brush() {
    rules = new ArrayList<RegExRule>();
    commonFileExtensionList = new ArrayList<String>();
  }

  /**
   * Get the regular expression rule list.
   * @return a copy of the list
   */
  public List<RegExRule> getRuleList() {
    return new ArrayList<RegExRule>(rules);
  }

  /**
   * Get the common file extension list.
   * @return a copy of the list
   */
  public List<String> getCommonFileExtensionList() {
    return new ArrayList<String>(commonFileExtensionList);
  }

  /**
   * Set the common file extension list.
   * @param commonFileExtensionList the list, cannot be null
   */
  public void setCommonFileExtensionList(String... value) {
    if (commonFileExtensionList == null) {
      this.commonFileExtensionList = new ArrayList<String>();
    }
    this.commonFileExtensionList = new ArrayList<String>();
    for(String extension : value){
    	this.commonFileExtensionList.add(extension);
    }
  }

  /**
   * Similar function in JavaScript SyntaxHighlighter for making string of 
   * keywords separated by space into regular expression.
   * @param str the keywords separated by space
   * @return the treated regexp string
   */
  protected static String getKeywords(String str) {
    if (str == null) {
      throw new NullPointerException("argument 'str' cannot be null");
    }
    return "\\b(?:" + str.replaceAll("^\\s+|\\s+$", "").replaceAll("\\s+", "|") + ")\\b";
  }
  
  public void addRule(RegExRule rule){
	  this.rules.add(rule);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(getClass().getName());
    sb.append("\n");
    sb.append("rule count: ");
    sb.append(rules.size());
    for (int i = 0, iEnd = rules.size(); i < iEnd; i++) {
      RegExRule rule = rules.get(i);
      sb.append("\n");
      sb.append(i);
      sb.append(": ");
      sb.append(rule.toString());
    }
    sb.append("\n");
    sb.append("common file extension list: ");
    sb.append(commonFileExtensionList);
    sb.append("\n");
    sb.append("HTML Script RegExp: ");
    return sb.toString();
  }
}
