package org.sdmlib.serialization;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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

/**
 * The Class Tokener.
 */
public class Tokener {
    
    /** The index. */
    private int 	index;
    
    /** The line. */
    private int 	line;
    
    /** The character. */
    private int 	character;
    
    /** The buffer. */
    private String 	buffer;
    
    /** The first char. */
    private char 	firstChar;
	
	/** The creator. */
	private BaseEntity creator;

    /**
     * Construct a Tokener from a string.
     *
     * @param s     A source string.
     */
    public Tokener(String s) {
    	this.buffer=s;
    	this.index=0;
    	this.line=0;
    	this.firstChar=nextClean();
    	back();
    }


    /**
     * Back up one character. This provides a sort of lookahead capability,
     * so that you can test for a digit or letter before attempting to parse
     * the next number or identifier.
     */
    public void back()  {
        if (index <= 0) {
            throw new RuntimeException("Stepping back two steps is not supported");
        }
        this.index -= 1;
        this.character -= 1;
    }


    /**
     * Get the hex value of a character (base16).
     * @param c A character between '0' and '9' or between 'A' and 'F' or
     * between 'a' and 'f'.
     * @return  An int between 0 and 15, or -1 if c was not a hex digit.
     */
    public static int dehexchar(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - ('A' - 10);
        }
        if (c >= 'a' && c <= 'f') {
            return c - ('a' - 10);
        }
        return -1;
    }
    
    /**
     * End.
     *
     * @return true, if successful
     */
    public boolean end() {
    	return buffer.length()<=index;
    }


    /**
     * Get the next character in the source string.
     *
     * @return The next character, or 0 if past the end of the source string.
     */
    public char next()  {
    	char c;
    	if(index>=buffer.length())
        {
    		c=0;
        }else{
        	c= buffer.charAt(index);
        }
    	this.index += 1;
    	char last = 0;
    	if(index<buffer.length()){
    		last=buffer.charAt(index-1);
    	}
    	if (last == '\r') {
    		this.line += 1;
    		this.character = c == '\n' ? 0 : 1;
    	} else if (c == '\n') {
    		this.line += 1;
    		this.character = 0;
    	} else {
    		this.character += 1;
    	}
        return c;
    }
    
    /**
     * Next pos.
     *
     * @return the int
     */
    public int nextPos()  {
    	next();
    	return index;
    }

    /**
     * Get the next n characters.
     *
     * @param n     The number of characters to take.
     * @return      A string of n characters.
     *   Substring bounds error if there are not
     *   n characters remaining in the source string.
     */
     public String next(int n)  {
    	 if (n == -1) {
    		n=buffer.length()-index; 
    	 } else if (n == 0) {
             return "";
         }

         char[] chars = new char[n];
         int pos = 0;

         while (pos < n) {
             chars[pos] = next();
             if (end()) {
                 throw syntaxError("Substring bounds error");                 
             }
             pos += 1;
         }
         return new String(chars);
     }


    /**
     * Get the next char in the string, skipping whitespace.
     * @return  A character, or 0 if there are no more characters.
     */
    public char nextClean()  {
        for (;;) {
            char c = next();
            if (c == 0 || c > ' ') {
                return c;
            }
        }
    }


    /**
     * Return the characters up to the next close quote character.
     * Backslash processing is done. The formal JSON format does not
     * allow strings in single quotes, but an implementation is allowed to
     * accept them.
     * @param quote The quoting character, either
     *      <code>"</code>&nbsp;<small>(double quote)</small> or
     *      <code>'</code>&nbsp;<small>(single quote)</small>.
     * @return      A String.
     */
    public String nextString(char quote)  {
        char c;
        StringBuffer sb = new StringBuffer();
        for (;;) {
            c = next();
            switch (c) {
            case 0:
            case '\n':
            case '\r':
                throw syntaxError("Unterminated string ("+sb.substring(0, 20)+")");
            case '\\':
                c = next();
                switch (c) {
                case 'b':
                    sb.append('\b');
                    break;
                case 't':
                    sb.append('\t');
                    break;
                case 'n':
                    sb.append('\n');
                    break;
                case 'f':
                    sb.append('\f');
                    break;
                case 'r':
                    sb.append('\r');
                    break;
                case 'u':
                    sb.append((char)Integer.parseInt(next(4), 16));
                    break;
                case '"':
                case '\'':
                case '\\':
                case '/':
                	sb.append(c);
                	break;
                default:
                    throw syntaxError("Illegal escape.");
                }
                break;
            default:
                if (c == quote) {
                    return sb.toString();
                }
                sb.append(c);
            }
        }
    }


    /**
     * Get the text up but not including the specified character or the
     * end of line, whichever comes first.
     *
     * @param delimiter the delimiter
     * @return   A string.
     */
    public String nextTo(char delimiter)  {
        StringBuffer sb = new StringBuffer();
        for (;;) {
            char c = next();
            if (c == delimiter || c == 0 || c == '\n' || c == '\r') {
                if (c != 0) {
                    back();
                }
                return sb.toString().trim();
            }
            sb.append(c);
        }
    }

    /**
     * Get the text up but not including one of the specified delimiter
     * characters or the end of line, whichever comes first.
     * @param delimiters A set of delimiter characters.
     * @return A string, trimmed.
     */
    public String nextTo(String delimiters)  {
        char c;
        StringBuffer sb = new StringBuffer();
        for (;;) {
            c = next();
            if (delimiters.indexOf(c) >= 0 || c == 0 ||
                    c == '\n' || c == '\r') {
                if (c != 0) {
                    back();
                }
                return sb.toString().trim();
            }
            sb.append(c);
        }
    }


    /**
     * Get the next value. The value can be a Boolean, Double, Integer,
     * JSONArray, JSONObject, Long, or String, or the JSONObject.NULL object.
     *
     * @return An object.
     */
    public Object nextValue()  {
        char c = nextClean();
        String string;

        if(firstChar=='{'||firstChar=='['){
	        switch (c) {
	            case '"':
	            case '\'':
	                return nextString(c);
	            case '{':
	                back();
	                BaseEntity element = creator.getNewObject();
	                element.setTokener(this);
	                return element; 
	            case '[':
	                back();
	                EntityList elementList = creator.getNewArray();
	                elementList.setTokener(this);
	                return elementList;
	        }
        }else if(firstChar=='<'){
	        switch (c) {
            case '"':
            case '\'':
                return nextString(c);
            case '<':
                back();
                BaseEntity element = creator.getNewObject();
                element.setTokener(this);
                return element;
	        }
        }

        /*
         * Handle unquoted text. This could be the values true, false, or
         * null, or it can be a number. An implementation (such as this one)
         * is allowed to also accept non-standard forms.
         *
         * Accumulate characters until we reach the end of the text or a
         * formatting character.
         */

        StringBuffer sb = new StringBuffer();
        while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
            sb.append(c);
            c = next();
        }
        back();

        string = sb.toString().trim();
        if (string.equals("")) {
            throw syntaxError("Missing value");
        }
        return EntityUtil.stringToValue(string);
    }


    /**
     * Skip.
     *
     * @param pos the pos
     * @return true, if successful
     */
    public boolean skip(int pos){
    	while(pos>0){
    		if(next()==0){
    			return false;
    		}
    		pos--;
    	}
    	return true;
    }
    
    /**
     * Step pos.
     *
     * @param character the character
     * @return true, if successful
     */
    public boolean stepPos(char... character) {
		boolean exit = false;
		while (index < buffer.length() && !exit) {
			for (char zeichen : character) {
				if (buffer.charAt(index) == zeichen) {
					exit = true;
					break;
				}
			}
			if (!exit) {
				next();
			}
		}
		return exit;
	}
    
	/**
	 * Step pos.
	 *
	 * @param searchString the search string
	 * @return true, if successful
	 */
	public boolean stepPos(String searchString) {
		boolean exit = false;
		int strLen=searchString.length();
		int z=0;
		while (index < buffer.length() && !exit) {
			if (buffer.charAt(index) == searchString.charAt(z)) {
					z++;
					if(z>=strLen){
						exit = true;
						break;
					}
			}else{
				z=0;
			}
			if (!exit) {
				next();
			}
		}
		return exit;
	}
	
	/**
	 * Step pos but not.
	 *
	 * @param not the not
	 * @param character the character
	 * @return true, if successful
	 */
	public boolean stepPosButNot(char not, char... character) {
		boolean exit = false;
		while (index < buffer.length() && !exit) {
			for (char zeichen : character) {
				if (buffer.charAt(index) == zeichen&& buffer.charAt(index-1)!=not) {
					exit = true;
					break;
				}
			}
			if (!exit) {
				next();
			}
		}
		return exit;
	}

    /**
     * Syntax error.
     *
     * @param message the message
     * @return the runtime exception
     */
    public RuntimeException syntaxError(String message) {
        return new RuntimeException(message + toString());
    }
    
    /**
     * Gets the index.
     *
     * @return the index
     */
    public int getIndex(){
    	return index;
    }
    
    /**
     * Gets the length.
     *
     * @return the length
     */
    public int getLength(){
    	return buffer.length();
    }


    /**
     * Make a printable string of this JSONTokener.
     *
     * @return " at {index} [character {character} line {line}]"
     */
    public String toString() {
        return " at " + index + " [character " + this.character + " line " + 
        	this.line + "]";
    }
    
    /**
     * Char at.
     *
     * @param pos the pos
     * @return the char
     */
    public char charAt(int pos){
    	return buffer.charAt(pos);
    }
    
    /**
     * Gets the current char.
     *
     * @return the current char
     */
    public char getCurrentChar(){
    	if(index<buffer.length()){
    		return buffer.charAt(index);
    	}
    	return 0;
    }
    
    /**
     * Previous.
     *
     * @param start the start
     * @return the string
     */
    public String previous(int start){
    	return buffer.substring(start, index);
    }
    
    /**
     * Substring.
     *
     * @param start the start
     * @param end the end
     * @return the string
     */
    public String substring(int start, int end){
    	if(end==-1){
    		return buffer.substring(start);
    	}
    	return buffer.substring(start, end);
    }
    
    /**
     * Check values.
     *
     * @param items the items
     * @return true, if successful
     */
    public boolean checkValues(char... items){
    	char current=buffer.charAt(index);
    	for(char item : items){
    		if(current==item){
    			return false;
    		}
    	}
    	return true;
    }

	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(int index) {
		this.index=index;
	}


	/**
	 * Sets the creator.
	 *
	 * @param creator the new creator
	 */
	public void setCreator(BaseEntity creator) {
		this.creator=creator;
	}
}