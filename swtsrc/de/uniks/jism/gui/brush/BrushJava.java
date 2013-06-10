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
 * Java brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class BrushJava extends Brush {
	public BrushJava() {
    super();

    String keywords = "abstract assert boolean break byte case catch char class const "
            + "continue default do double else enum extends "
            + "false final finally float for goto if implements import "
            + "instanceof int interface long native new null "
            + "package private protected public return "
            + "short static strictfp super switch synchronized this throw throws true "
            + "transient try void volatile while";

    addRule(new RegExpRule(RegExpRule.singleLineCComments, "comments")); // one line comments
    addRule(new RegExpRule("\\/\\*([^\\*][\\s\\S]*?)?\\*\\/", Pattern.MULTILINE, "comments")); // multiline comments
    addRule(new RegExpRule("\\/\\*(?!\\*\\/)\\*[\\s\\S]*?\\*\\/", Pattern.MULTILINE, "preprocessor")); // documentation comments
    addRule(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // strings
    addRule(new RegExpRule(RegExpRule.singleQuotedString, "string")); // strings
    addRule(new RegExpRule("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", Pattern.CASE_INSENSITIVE, "value")); // numbers
    addRule(new RegExpRule("(?!\\@interface\\b)\\@[\\$\\w]+\\b", "color1")); // annotation @anno
    addRule(new RegExpRule("\\@interface\\b", "color2")); // @interface keyword
    addRule(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // java keyword
    addRule(new HTMLRegExRule("(?:&lt;|<)%[@!=]?", "%(?:&gt;|>)"));

    setCommonFileExtensionList("java");
  }
}
