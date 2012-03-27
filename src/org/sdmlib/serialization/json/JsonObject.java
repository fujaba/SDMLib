package org.sdmlib.serialization.json;

/*
Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.EntityUtil;

/**
 * A JsonObject is an unordered collection of name/value pairs. Its
 * external form is a string wrapped in curly braces with colons between the
 * names and values, and commas between the values and names. The internal form
 * is an object having <code>get</code> and <code>opt</code> methods for
 * accessing the values by name, and <code>put</code> methods for adding or
 * replacing values by name. The values can be any of these types:
 * <code>Boolean</code>, <code>JsonArray</code>, <code>JsonObject</code>,
 * <code>Number</code>, <code>String</code>, or the <code>JsonObject.NULL</code>
 * object. A JsonObject constructor can be used to convert an external form
 * JSON text into an internal form whose values can be retrieved with the
 * <code>get</code> and <code>opt</code> methods, or to convert values into a
 * JSON text using the <code>put</code> and <code>toString</code> methods.
 * A <code>get</code> method returns a value if one can be found, and throws an
 * exception if one cannot be found. An <code>opt</code> method returns a
 * default value instead of throwing an exception, and so is useful for
 * obtaining optional values.
 * <p>
 * The generic <code>get()</code> and <code>opt()</code> methods return an
 * object, which you can cast or query for type. There are also typed
 * <code>get</code> and <code>opt</code> methods that do type checking and type
 * coercion for you. The opt methods differ from the get methods in that they
 * do not throw. Instead, they return a specified value, such as null.
 * <p>
 * The <code>put</code> methods add or replace values in an object. For example,
 * <pre>myString = new JsonObject().put("JSON", "Hello, World!").toString();</pre>
 * produces the string <code>{"JSON": "Hello, World"}</code>.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to
 * the JSON syntax rules.
 * The constructors are more forgiving in the texts they will accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just
 *     before the closing brace.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single
 *     quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a quote
 *     or single quote, and if they do not contain leading or trailing spaces,
 *     and if they do not contain any of these characters:
 *     <code>{ } [ ] / \ : , = ; #</code> and if they do not look like numbers
 *     and if they are not the reserved words <code>true</code>,
 *     <code>false</code>, or <code>null</code>.</li>
 * <li>Keys can be followed by <code>=</code> or <code>=></code> as well as
 *     by <code>:</code>.</li>
 * <li>Values can be followed by <code>;</code> <small>(semicolon)</small> as
 *     well as by <code>,</code> <small>(comma)</small>.</li>
 * </ul>
 * @author JSON.org
 * @version 2011-11-24
 */
public class JsonObject extends Entity{
	public static final char CRLF='\n';
    /**
     * Construct an empty JsonObject.
     */
    public JsonObject() {
    }

    /**
     * Construct a JsonObject from a JSONTokener.
     * @param x A JSONTokener object containing the source string.
     *  or a duplicated key.
     */
    public JsonObject(JsonTokener x) {
        this();
        char c;
        String key;

        if (x.nextClean() != '{') {
            throw x.syntaxError("A JsonObject text must begin with '{'");
        }
        for (;;) {
            c = x.nextClean();
            switch (c) {
            case 0:
                throw x.syntaxError("A JsonObject text must end with '}'");
            case '}':
                return;
            default:
                x.back();
                key = x.nextValue().toString();
            }

// The key is followed by ':'. We will also tolerate '=' or '=>'.

            c = x.nextClean();
            if (c == '=') {
                if (x.next() != '>') {
                    x.back();
                }
            } else if (c != ':') {
                throw x.syntaxError("Expected a ':' after a key");
            }
            this.put(key, x.nextValue());

// Pairs are separated by ','. We will also tolerate ';'.

            switch (x.nextClean()) {
            case ';':
            case ',':
                if (x.nextClean() == '}') {
                    return;
                }
                x.back();
                break;
            case '}':
                return;
            default:
                throw x.syntaxError("Expected a ',' or '}'");
            }
        }
    }


    /**
     * Construct a JsonObject from a Map.
     *
     * @param map A map object that can be used to initialize the contents of
     *  the JsonObject.
     */
    public JsonObject(Map<String, Object> map) {
    	getMap();
        if (map != null) {
        	Iterator<Entry<String, Object>> i = map.entrySet().iterator();
            while (i.hasNext()) {
            	Entry<String, Object> e = i.next();
                Object value = e.getValue();
                if (value != null) {
                    this.put(e.getKey(), EntityUtil.wrap(value, this));
                }
            }
        }
    }


    /**
     * Construct a JsonObject from a source JSON text string.
     * This is the most commonly used JsonObject constructor.
     * @param source    A string beginning
     *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *  with <code>}</code>&nbsp;<small>(right brace)</small>.
     */
    public JsonObject(String source) {
        this(new JsonTokener(source));
    }

    /**
     * Produce a string from a double. The string "null" will be returned if
     * the number is not finite.
     * @param  d A double.
     * @return A String.
     */
    public static String doubleToString(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return "null";
        }
// Shave off trailing zeros and decimal point, if possible.
        String string = Double.toString(d);
        if (string.indexOf('.') > 0 && string.indexOf('e') < 0 &&
                string.indexOf('E') < 0) {
            while (string.endsWith("0")) {
                string = string.substring(0, string.length() - 1);
            }
            if (string.endsWith(".")) {
                string = string.substring(0, string.length() - 1);
            }
        }
        return string;
    }


    /**
     * Get the JsonArray value associated with a key.
     *
     * @param key   A key string.
     * @return      A JsonArray which is the value.
     * @throws      RuntimeExpetion if the key is not found or
     *  if the value is not a JsonArray.
     */
    public JsonArray getJsonArray(String key) {
        Object object = this.get(key);
        if (object instanceof JsonArray) {
            return (JsonArray)object;
        }
        throw new RuntimeException("JsonObject[" + EntityUtil.quote(key) +
                "] is not a JsonArray.");
    }


    /**
     * Get the JsonObject value associated with a key.
     *
     * @param key   A key string.
     * @return      A JsonObject which is the value.
     * @throws      RuntimeException if the key is not found or
     *  if the value is not a JsonObject.
     */
    public JsonObject getJsonObject(String key) {
        Object object = this.get(key);
        if (object instanceof JsonObject) {
            return (JsonObject)object;
        }
        throw new RuntimeException("JsonObject[" + EntityUtil.quote(key) +
                "] is not a JsonObject.");
    }

    /**
     * Make a JSON text of this JsonObject. For compactness, no whitespace
     * is added. If this would not result in a syntactically correct JSON text,
     * then null will be returned instead.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     *
     * @return a printable, displayable, portable, transmittable
     *  representation of the object, beginning
     *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *  with <code>}</code>&nbsp;<small>(right brace)</small>.
     */
    public String toString() {
        try {
        	Iterator<String> keys = this.keys();
            StringBuffer sb = new StringBuffer("{");

            while (keys.hasNext()) {
                if (sb.length() > 1) {
                    sb.append(',');
                }
                Object o = keys.next();
                sb.append(EntityUtil.quote(o.toString()));
                sb.append(':');
                sb.append(EntityUtil.valueToString(getMap().get(o), this));
            }
            sb.append('}');
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Make a prettyprinted JSON text of this JsonObject.
     * <p>
     * Warning: This method assumes that the data structure is acyclical.
     * @param indentFactor The number of spaces to add to each level of
     *  indentation.
     * @return a printable, displayable, portable, transmittable
     *  representation of the object, beginning
     *  with <code>{</code>&nbsp;<small>(left brace)</small> and ending
     *  with <code>}</code>&nbsp;<small>(right brace)</small>.
     */
    public String toString(int indentFactor){
    	return toString(indentFactor, 0);
    }
    public String toString(int indentFactor, int indent) {
        int i;
        int length = this.length();
        if (length == 0) {
            return "{}";
        }
        Map<String, Object> map = getMap();
        Iterator<String> keys = map.keySet().iterator();
        int newindent = indent + indentFactor;
        Object object;
        StringBuffer sb = new StringBuffer("{");
        if (length == 1) {
            object = keys.next();
            sb.append(EntityUtil.quote(object.toString()));
            sb.append(": ");
            sb.append(EntityUtil.valueToString(map.get(object), indentFactor,
                    indent, false, this));
        } else {
            while (keys.hasNext()) {
                object = keys.next();
                if (sb.length() > 1) {
                    sb.append(",");
                }
            	if(indentFactor>0){
            		sb.append(CRLF);
            		for (i = 0; i < newindent; i += 1) {
                        sb.append(' ');
                    }
            	}

                sb.append(EntityUtil.quote(object.toString()));
                sb.append(":");
                sb.append(EntityUtil.valueToString(map.get(object), indentFactor,
                        newindent, false, this));
            }
            if(indentFactor>0&&sb.length() > 1){
                sb.append(CRLF);
                for (i = 0; i < indent; i += 1) {
                    sb.append(' ');
                }
            }
        }
        sb.append('}');
        return sb.toString();
    }

	@Override
	public EntityList getNewArray() {
		return new JsonArray();
	}

	@Override
	public Entity getNewObject() {
		return new JsonObject();
	}
	/**
	 * Write the contents of the JsonObject as JSON text to a writer.
	 * For compactness, no whitespace is added.
	 * <p>
	 * Warning: This method assumes that the data structure is acyclical.
	 *
	 * @return The writer.
	 */
	public Writer write(Writer writer)  {
		try {
			boolean  commanate = false;
			Map<String, Object> map = getMap();
			Iterator<String> keys = map.keySet().iterator();
			writer.write('{');

			while (keys.hasNext()) {
				if (commanate) {
					writer.write(',');
				}
				Object key = keys.next();
				writer.write(EntityUtil.quote(key.toString()));
				writer.write(':');
				Object value = map.get(key);
				if (value instanceof Entity) {
					((Entity)value).write(writer);
				} else if (value instanceof EntityList) {
					((EntityList)value).write(writer);
				} else {
					writer.write(EntityUtil.valueToString(value, this));
				}
				commanate = true;
			}
			writer.write('}');
			return writer;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}