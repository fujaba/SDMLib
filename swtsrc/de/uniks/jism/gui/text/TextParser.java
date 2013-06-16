package de.uniks.jism.gui.text;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import javax.swing.text.Segment;
import org.sdmlib.serialization.Style;
import de.uniks.jism.gui.brush.Brush;
import de.uniks.jism.gui.brush.HTMLRegExRule;
import de.uniks.jism.gui.brush.RegExRule;
import de.uniks.jism.gui.brush.RegExpRule;
import de.uniks.jism.gui.theme.StyleConstants;
import de.uniks.jism.gui.theme.Theme;

public class TextParser {
	private Theme theme;
	private Brush brush;

	public void setDefaultColors(Theme theme, Brush brush) {
		this.theme = theme;
		this.brush = brush;
	}
	
	public Theme getTheme() {
		return this.theme;
	}
	
	
	public Brush getBrush() {
		return this.brush;
	}

	public List<ParsePosition> parse(String value) {
		return null;
	}

	public Style getStyle(String key) {
		return theme.getStyle(key);
	}
	
	/**
	 * Apply the list of style to the script text pane. See
	 * {@link syntaxhighlighter.parser.Parser#parse(syntaxhighlighter.brush.Brush, boolean, char[], int, int)}
	 * .
	 */
	public List<ParsePosition> parse(String content, Brush brush) {
		ArrayList<ParsePosition> returnList = new ArrayList<ParsePosition>();
		Map<Integer, List<ParsePosition>> matches = parse(brush,
				content.toCharArray(), 0, content.length());
		for (List<ParsePosition> resultList : matches.values()) {
			for (ParsePosition result : resultList) {
				if(result.getStyleTyp()!=null&&result.getStyleTyp().length()>0){
					returnList.add(result);
				}
			}
		}
		return returnList;
	}

	/**
	 * Parse the content start from {@code offset} with {@code length} with the
	 * brush and return the result. All new matches will be added to
	 * {@code matches}.
	 * 
	 * @param matches		the list of matches
	 * @param brush			the brush to use
	 * @param htmlScript	turn HTML-Script on or not
	 * @param content		the content to parse in char array
	 * @param offset		the offset
	 * @param length		the length
	 * 
	 * @return the parsed result, the key of the map is style key
	 */
	protected Map<Integer, List<ParsePosition>> parse(Brush brush,
			char[] content, int offset, int length) {
		if (brush == null || content == null) {
			return null;
		}
		// parse the RegExpRule in the brush first
		List<RegExRule> regExRuleList = brush.getRuleList();
		TreeMap<Integer, List<ParsePosition>> matches = new TreeMap<Integer, List<ParsePosition>>();
		for (RegExRule regExpRule : regExRuleList) {
			parse(matches, regExpRule, content, offset, length);
		}
		
		return matches;
	}

	/**
	 * Parse the content start from {@code offset} with {@code length} using the
	 * {@code regExpRule}. All new matches will be added to {@code matches}.
	 * 
	 * @param matches
	 *            the list of matches
	 * @param regExpRule
	 *            the RegExp rule to use
	 * @param content
	 *            the content to parse in char array
	 * @param offset
	 *            the offset
	 * @param length
	 *            the length
	 */
	protected void parse(TreeMap<Integer, List<ParsePosition>> matches,
			RegExRule regExRule, char[] content, int offset, int length) {

		Map<Integer, Object> groupOperations = regExRule.getGroupOperations();

		java.util.regex.Pattern regExpPattern = regExRule.getPattern();
		Matcher matcher = regExpPattern.matcher(new Segment(content, offset,
				length));
		if(regExRule instanceof HTMLRegExRule){
			 // HTML-Script brush has superior priority, so remove all previous matches within the matched range
          removeMatches(matches, matcher.start() + offset, matcher.end() + offset);

          // the left tag of HTML-Script
          int start = matcher.start(1) + offset, end = matcher.end(1) + offset;
          addMatch(matches, new ParsePosition(start, end - start, StyleConstants.STYLE_SCRIPT, null));

          // the content of HTML-Script, parse it using the HTML-Script brush
          start = matcher.start(2) + offset;
          end = matcher.end(2) + offset;
          parse(matches, regExRule, content, start, end - start);

          // the right tag of HTML-Script
          start = matcher.start(3) + offset;
          end = matcher.end(3) + offset;
          addMatch(matches, new ParsePosition(start, end - start, StyleConstants.STYLE_SCRIPT, null));
		}else{
			while (matcher.find()) {
				// deal with the matched result
				for (int groupId : groupOperations.keySet()) {
					Object operation = groupOperations.get(groupId);
	
					// the start and end position of the match
					int start = matcher.start(groupId), end = matcher.end(groupId);
					if (start == -1 || end == -1) {
						continue;
					}
					start += offset;
					end += offset;
	
					if (operation instanceof String) {
						// add the style to the match
						// , regExpRule.getBold()
						addMatch(matches, new ParsePosition(start, end - start,
								(String) operation, regExRule.getStyle()));
					} else if(operation!=null){
						// parse the result using the <code>operation</code>
						// RegExpRule
						parse(matches, (RegExpRule) operation, content, start, end
								- start);
					}
				}
			}
		}
	}
	
	/**
	   * Remove those matches that fufil the condition from {@code matches}.
	   * @param matches the list of matches
	   * @param start the start position in the document
	   * @param end the end position in the document
	   */
	  protected void removeMatches(TreeMap<Integer, List<ParsePosition>> matches, int start, int end) {
		  for (Iterator<Entry<Integer, List<ParsePosition>>> iterator = matches.entrySet().iterator();iterator.hasNext();) {
			  Entry<Integer, List<ParsePosition>> position = iterator.next();
			  if(position.getKey()>end){
				  break;
			  }
			  for(Iterator<ParsePosition> i = position.getValue().iterator();i.hasNext();){
				  ParsePosition parseResult = i.next();
				  int endPos = parseResult.getOffset()+parseResult.getLength();
				  
				  // Skip
				  if( parseResult.getOffset()>end ){
					  continue;
				  }
				  if(parseResult.getOffset() <= start ){
					  // change 
					  parseResult.setFixEnd(start);
					  if(endPos>end){
						//added rest
						  addMatch(matches, new ParsePosition(end+1, endPos-end-1, parseResult.getStyleTyp(), parseResult.getStyle()));
					  }
				  }else if(endPos>end){
					  // change
					  addMatch(matches, new ParsePosition(end+1, endPos-end-1, parseResult.getStyleTyp(), parseResult.getStyle()));
					  i.remove();
				  }else{
					  //remove
				 		 // fit or within range
			          i.remove();
				  }
			  }
		  }
	  }

	/**
	 * Add matched result to {@code matches}.
	 * 
	 * @param matches
	 *            the list of matches
	 * @param match
	 *            the matched result
	 */
	protected void addMatch(Map<Integer, List<ParsePosition>> matches,
			ParsePosition match) {
		if (matches == null || match == null) {
			return;
		}
		List<ParsePosition> matchList = matches.get(match.getOffset());
		if (matchList == null) {
			matchList = new ArrayList<ParsePosition>();
			matches.put(match.getOffset(), matchList);
		}
		matchList.add(match);
	}
}
