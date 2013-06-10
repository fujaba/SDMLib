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
 * Groovy brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class BrushGroovy extends Brush {
	public BrushGroovy() {
    super();

    // Contributed by Andres Almiray
    // http://jroller.com/aalmiray/entry/nice_source_code_syntax_highlighter

    String keywords = "as assert break case catch class continue def default do else extends finally "
            + "if in implements import instanceof interface new package property return switch "
            + "throw throws try while public protected private static";
    String types = "void boolean byte char short int long float double";
    String constants = "null";
    String methods = "allProperties count get size "
            + "collect each eachProperty eachPropertyName eachWithIndex find findAll "
            + "findIndexOf grep inject max min reverseEach sort "
            + "asImmutable asSynchronized flatten intersect join pop reverse subMap toList "
            + "padRight padLeft contains eachMatch toCharacter toLong toUrl tokenize "
            + "eachFile eachFileRecurse eachB yte eachLine readBytes readLine getText "
            + "splitEachLine withReader append encodeBase64 decodeBase64 filterLine "
            + "transformChar transformLine withOutputStream withPrintWriter withStream "
            + "withStreams withWriter withWriterAppend write writeLine "
            + "dump inspect invokeMethod print println step times upto use waitForOrKill "
            + "getText";

    addRule(new RegExpRule(RegExpRule.singleLineCComments, "comments")); // one line comments
    addRule(new RegExpRule(RegExpRule.multiLineCComments, "comments")); // multiline comments
    addRule(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // strings
    addRule(new RegExpRule(RegExpRule.singleQuotedString, "string")); // strings
    addRule(new RegExpRule("\"\"\".*\"\"\"", "string")); // GStrings
    addRule(new RegExpRule("\\b([\\d]+(\\.[\\d]+)?|0x[a-f0-9]+)\\b", Pattern.CASE_INSENSITIVE, "value")); // numbers
    addRule(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // goovy keyword
    addRule(new RegExpRule(getKeywords(types), Pattern.MULTILINE, "color1")); // goovy/java type
    addRule(new RegExpRule(getKeywords(constants), Pattern.MULTILINE, "constants")); // constants
    addRule(new RegExpRule(getKeywords(methods), Pattern.MULTILINE, "functions")); // methods

    addRule(HTMLRegExRule.aspScriptTags);

    setCommonFileExtensionList("groovy");
  }
}
