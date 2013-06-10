package de.uniks.jism.gui.theme;

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

import org.sdmlib.serialization.Style;
/**
 * Emacs theme.
 * @author Chan Wai Shing <cws1989@gmail.com>
 */

public class ThemeEmacs extends Theme {
	public ThemeEmacs() {
//	    setGutterText(Color.decode("0xd3d3d3"));
//	    setGutterBorderColor(Color.decode("0x990000"));
//	    setGutterBorderWidth(3);
//	    setGutterTextFont(new Font("Verdana", Font.PLAIN, 11));
//	    setGutterTextPaddingLeft(7);
//	    setGutterTextPaddingRight(7);

    // Emacs SyntaxHighlighter theme based on theme by Joshua Emmons http://www.skia.net/
    addStyle(StyleConstants.STYLE_PLAIN, new Style().withFontFamily("Consolas").withFontSize("12").withBackground("d3d3d3").withForground("FFFFFF"));
    addStyle(StyleConstants.STYLE_HIGHTLIGHTED, new Style().withBackground("2A3133"));
    addStyle(StyleConstants.STYLE_BOLD, new Style().withBold(true));
    addStyle(StyleConstants.STYLE_COMMENTS, new Style().withForground("ff7d27"));
    addStyle(StyleConstants.STYLE_STRING, new Style().withForground("ff9e7b"));
    addStyle(StyleConstants.STYLE_KEYWORD, new Style().withForground("00ffff"));
    addStyle(StyleConstants.STYLE_PREPROCESSOR, new Style().withForground("aec4de"));
    addStyle(StyleConstants.STYLE_VARIABLE, new Style().withForground("ffaa3e"));
    addStyle(StyleConstants.STYLE_VALUE, new Style().withForground("009900"));
    addStyle(StyleConstants.STYLE_FUNCTIONS, new Style().withForground("81cef9"));
    addStyle(StyleConstants.STYLE_CONSTANTS, new Style().withForground("ff9e7b"));
    addStyle(StyleConstants.STYLE_SCRIPT, new Style().withForground("00ffff").withBold(true));
    addStyle(StyleConstants.STYLE_COLOR1, new Style().withForground("ebdb8d"));
    addStyle(StyleConstants.STYLE_COLOR2, new Style().withForground("ff7d27"));
    addStyle(StyleConstants.STYLE_COLOR3, new Style().withForground("aec4de"));
  }
}
