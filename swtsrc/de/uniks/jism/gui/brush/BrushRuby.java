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
import org.sdmlib.serialization.Style;
/**
 * Ruby brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class BrushRuby extends Brush {
	public BrushRuby() {
    super();

    // Contributed by Erik Peterson.

    String keywords = "alias and BEGIN begin break case class def define_method defined do each else elsif "
            + "END end ensure false for if in module new next nil not or raise redo rescue retry return "
            + "self super then throw true undef unless until when while yield";
    String builtins = "Array Bignum Binding Class Continuation Dir Exception FalseClass File::Stat File Fixnum Fload "
            + "Hash Integer IO MatchData Method Module NilClass Numeric Object Proc Range Regexp String Struct::TMS Symbol "
            + "ThreadGroup Thread Time TrueClass";

    addRule(new RegExpRule(RegExpRule.singleLinePerlComments, "comments")); // one line comments
    addRule(new RegExpRule(RegExpRule.doubleQuotedString, "string")); // double quoted strings
    addRule(new RegExpRule(RegExpRule.singleQuotedString, "string")); // single quoted strings
    addRule(new RegExpRule("\\b[A-Z0-9_]+\\b", "constants")); // constants
    addRule(new RegExpRule(":[a-z][A-Za-z0-9_]*", "color2")); // symbols
    addRule(new RegExpRule("(\\$|@@|@)\\w+", "variable", new Style().withBold(true))); // $global, @instance, and @@class variables
    addRule(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE, "keyword")); // keywords
    addRule(new RegExpRule(getKeywords(builtins), Pattern.MULTILINE, "color1")); // builtins

    addRule(HTMLRegExRule.phpScriptTags);

    setCommonFileExtensionList("rb", "rbw");
  }
}
