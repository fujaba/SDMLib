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

import java.util.regex.Pattern;
/**
 * Action Script brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class BrushAS3 extends Brush {
	public BrushAS3() {
    super();

    // Created by Peter Atoria @ http://iAtoria.com

    String inits = "class interface function package";
    String keywords = "-Infinity ...rest Array as AS3 Boolean break case catch const continue Date decodeURI "
            + "decodeURIComponent default delete do dynamic each else encodeURI encodeURIComponent escape "
            + "extends false final finally flash_proxy for get if implements import in include Infinity "
            + "instanceof int internal is isFinite isNaN isXMLName label namespace NaN native new null "
            + "Null Number Object object_proxy override parseFloat parseInt private protected public "
            + "return set static String super switch this throw true try typeof uint undefined unescape "
            + "use void while with";

    addRule(new RegExpRule(RegExpRule.singleLineCComments, "comments")); // one line comments
    addRule(new RegExpRule(RegExpRule.multiLineCComments, "comments")); // multiline comments
    addRule(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // double quoted strings
    addRule(new RegExpRule(RegExpRule.singleQuotedString, "string")); // single quoted strings
    addRule(new RegExpRule("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", Pattern.CASE_INSENSITIVE, "value")); // numbers
    addRule(new RegExpRule(getKeywords(inits), Pattern.MULTILINE, "color3")); // initializations
    addRule(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // keywords
    addRule(new RegExpRule("var", Pattern.MULTILINE, "variable")); // variable
    addRule(new RegExpRule("trace", Pattern.MULTILINE, "color1")); // trace

    addRule(HTMLRegExRule.scriptScriptTags);

    setCommonFileExtensionList("as");
  }
}
