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
 * Delphi brush.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class BrushDelphi extends Brush {
	public BrushDelphi() {
    super();

    String keywords = "abs addr and ansichar ansistring array as asm begin boolean byte cardinal "
            + "case char class comp const constructor currency destructor div do double "
            + "downto else end except exports extended false file finalization finally "
            + "for function goto if implementation in inherited int64 initialization "
            + "integer interface is label library longint longword mod nil not object "
            + "of on or packed pansichar pansistring pchar pcurrency pdatetime pextended "
            + "pint64 pointer private procedure program property pshortstring pstring "
            + "pvariant pwidechar pwidestring protected public published raise real real48 "
            + "record repeat set shl shortint shortstring shr single smallint string then "
            + "threadvar to true try type unit until uses val var varirnt while widechar "
            + "widestring with word write writeln xor";

    addRule(new RegExpRule("\\(\\*[\\s\\S]*?\\*\\)", Pattern.MULTILINE, "comments")); // multiline comments (* *)
    addRule(new RegExpRule("\\{(?!\\$)[\\s\\S]*?\\}", Pattern.MULTILINE, "comments")); // multiline comments { }
    addRule(new RegExpRule(RegExpRule.singleLineCComments, "comments")); // one line
    addRule(new RegExpRule(RegExpRule.singleQuotedString, "string")); // strings
    addRule(new RegExpRule("\\{\\$[a-zA-Z]+ .+\\}", "color1")); // compiler Directives and Region tags
    addRule(new RegExpRule("\\b[\\d\\.]+\\b", "value")); // numbers 12345
    addRule(new RegExpRule("\\$[a-zA-Z0-9]+\\b", "value")); // numbers $F5D3
    addRule(new RegExpRule(getKeywords(keywords), Pattern.MULTILINE | Pattern.CASE_INSENSITIVE, "keyword")); // keyword

    setCommonFileExtensionList("pas");
  }
}
