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
 * C# brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class BrushCSharp extends Brush {
	public BrushCSharp() {
    super();

    String keywords = "abstract as base bool break byte case catch char checked class const "
            + "continue decimal default delegate do double else enum event explicit "
            + "extern false finally fixed float for foreach get goto if implicit in int "
            + "interface internal is lock long namespace new null object operator out "
            + "override params private protected public readonly ref return sbyte sealed set "
            + "short sizeof stackalloc static string struct switch this throw true try "
            + "typeof uint ulong unchecked unsafe ushort using virtual void while var";

    addRule(new RegExpRule("\\/\\/\\/.*$", Pattern.MULTILINE, "color1")); // documents
    addRule(new RegExpRule(RegExpRule.singleLineCComments, "comments")); // one line comments
    addRule(new RegExpRule(RegExpRule.multiLineCComments, "comments")); // multiline comments
    addRule(new RegExpRule("@\"(?:[^\"]|\"\")*\"", "string")); // @-quoted strings
    addRule(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // strings
    addRule(new RegExpRule(RegExpRule.singleQuotedString, "string")); // strings
    addRule(new RegExpRule("^\\s*#.*", Pattern.MULTILINE, "preprocessor")); // preprocessor tags like #region and #endregion
    addRule(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // c# keyword
    addRule(new RegExpRule("\\bpartial(?=\\s+(?:class|interface|struct)\\b)", "keyword")); // contextual keyword: 'partial'
    addRule(new RegExpRule("\\byield(?=\\s+(?:return|break)\\b)", "keyword")); // contextual keyword: 'yield'

    addRule(HTMLRegExRule.aspScriptTags);

    setCommonFileExtensionList("cs");
  }
}
