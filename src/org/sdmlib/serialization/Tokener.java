package org.sdmlib.serialization;

import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonObject;

public class Tokener {
    private int 	index;
    private int 	line;
    private int 	character;
    private String 	buffer;

    /**
     * Construct a Tokener from a string.
     *
     * @param s     A source string.
     */
    public Tokener(String s) {
    	this.buffer=s;
    	this.index=0;
    	this.line=0;
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
                throw syntaxError("Unterminated string");
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
     * @param  delimiter A delimiter character.
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

        switch (c) {
            case '"':
            case '\'':
                return nextString(c);
            case '{':
                back();
                return new JsonObject(this);
            case '[':
                back();
                return new JsonArray(this);
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


    public boolean skip(int pos){
    	while(pos>0){
    		if(next()==0){
    			return false;
    		}
    		pos--;
    	}
    	return true;
    }
    
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

    public RuntimeException syntaxError(String message) {
        return new RuntimeException(message + toString());
    }
    
    public int getIndex(){
    	return index;
    }
    
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
    
    public char charAt(int pos){
    	return buffer.charAt(pos);
    }
    public char getCurrentChar(){
    	if(index<buffer.length()){
    		return buffer.charAt(index);
    	}
    	return 0;
    }
    
    public String previous(int start){
    	return buffer.substring(start, index);
    }
    
    public String substring(int start, int end){
    	return buffer.substring(start, end);
    }
    public boolean checkValues(char... items){
    	char current=buffer.charAt(index);
    	for(char item : items){
    		if(current==item){
    			return false;
    		}
    	}
    	return true;
    }

	public void setIndex(int index) {
		this.index=index;
	}
}