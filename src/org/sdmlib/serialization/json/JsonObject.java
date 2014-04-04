package org.sdmlib.serialization.json;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
 */
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.interfaces.LocalisationEntity;

/**
 * A JsonObject is an unordered collection of name/value pairs. Its external
 * form is a string wrapped in curly braces with colons between the names and
 * values, and commas between the values and names. The internal form is an
 * object having <code>get</code> and <code>opt</code> methods for accessing the
 * values by name, and <code>put</code> methods for adding or replacing values
 * by name. The values can be any of these types: <code>Boolean</code>,
 * <code>JsonArray</code>, <code>JsonObject</code>, <code>Number</code>,
 * <code>String</code>, or the <code>JsonObject.NULL</code> object. A JsonObject
 * constructor can be used to convert an external form JSON text into an
 * internal form whose values can be retrieved with the <code>get</code> and
 * <code>opt</code> methods, or to convert values into a JSON text using the
 * <code>put</code> and <code>toString</code> methods. A <code>get</code> method
 * returns a value if one can be found, and throws an exception if one cannot be
 * found. An <code>opt</code> method returns a default value instead of throwing
 * an exception, and so is useful for obtaining optional values.
 * <p>
 * The generic <code>get()</code> and <code>opt()</code> methods return an
 * object, which you can cast or query for type. There are also typed
 * <code>get</code> and <code>opt</code> methods that do type checking and type
 * coercion for you. The opt methods differ from the get methods in that they do
 * not throw. Instead, they return a specified value, such as null.
 * <p>
 * The <code>put</code> methods add or replace values in an object. For example,
 * 
 * <pre>
 * myString = new JsonObject().put(&quot;JSON&quot;, &quot;Hello, World!&quot;).toString();
 * </pre>
 * 
 * produces the string <code>{"JSON": "Hello, World"}</code>.
 * <p>
 * The texts produced by the <code>toString</code> methods strictly conform to
 * the JSON syntax rules. The constructors are more forgiving in the texts they
 * will accept:
 * <ul>
 * <li>An extra <code>,</code>&nbsp;<small>(comma)</small> may appear just
 * before the closing brace.</li>
 * <li>Strings may be quoted with <code>'</code>&nbsp;<small>(single
 * quote)</small>.</li>
 * <li>Strings do not need to be quoted at all if they do not begin with a quote
 * or single quote, and if they do not contain leading or trailing spaces, and
 * if they do not contain any of these characters:
 * <code>{ } [ ] / \ : , = ; #</code> and if they do not look like numbers and
 * if they are not the reserved words <code>true</code>, <code>false</code>, or
 * <code>null</code>.</li>
 * <li>Keys can be followed by <code>=</code> or <code>=></code> as well as by
 * <code>:</code>.</li>
 * <li>Values can be followed by <code>;</code> <small>(semicolon)</small> as
 * well as by <code>,</code> <small>(comma)</small>.</li>
 * </ul>
 * 
 * @author JSON.org
 * @version 2011-11-24
 */
public class JsonObject extends Entity implements LocalisationEntity
{
   /**
    * Produce a string from a double. The string "null" will be returned if the
    * number is not finite.
    * 
    * @param d
    *           A double.
    * @return A String.
    */
   public static String doubleToString(double d)
   {
      if (Double.isInfinite(d) || Double.isNaN(d))
      {
         return "null";
      }
      // Shave off trailing zeros and decimal point, if possible.
      String string = Double.toString(d);
      if (string.indexOf('.') > 0 && string.indexOf('e') < 0
         && string.indexOf('E') < 0)
      {
         while (string.endsWith("0"))
         {
            string = string.substring(0, string.length() - 1);
         }
         if (string.endsWith("."))
         {
            string = string.substring(0, string.length() - 1);
         }
      }
      return string;
   }

   /**
    * Get the JsonArray value associated with a key.
    * 
    * @param key
    *           A key string.
    * @return A JsonArray which is the value.
    * @throws RuntimeExpetion
    *            if the key is not found or if the value is not a JsonArray.
    */
   public JsonArray getJsonArray(String key)
   {
      Object object = this.get(key);
      if (object instanceof JsonArray)
      {
         return (JsonArray) object;
      }
      throw new RuntimeException("JsonObject[" + EntityUtil.quote(key)
         + "] is not a JsonArray.");
   }

   /**
    * Get the JsonObject value associated with a key.
    * 
    * @param key
    *           A key string.
    * @return A JsonObject which is the value.
    * @throws RuntimeException
    *            if the key is not found or if the value is not a JsonObject.
    */
   public JsonObject getJsonObject(String key)
   {
      Object object = this.get(key);
      if (object instanceof JsonObject)
      {
         return (JsonObject) object;
      }
      throw new RuntimeException("JsonObject[" + EntityUtil.quote(key)
         + "] is not a JsonObject.");
   }

   /**
    * Make a JSON text of this JsonObject. For compactness, no whitespace is
    * added. If this would not result in a syntactically correct JSON text, then
    * null will be returned instead.
    * <p>
    * Warning: This method assumes that the data structure is acyclical.
    * 
    * @return a printable, displayable, portable, transmittable representation
    *         of the object, beginning with <code>{</code>&nbsp;<small>(left
    *         brace)</small> and ending with <code>}</code>&nbsp;<small>(right
    *         brace)</small>.
    */
   @Override
   public String toString()
   {
      int length = this.size();
      if (length == 0)
      {
         return "{}";
      }
      Map<String, Object> map = getMap();

      if (!isVisible())
      {
         return "{Item with " + map.size() + " values}";
      }

      StringBuilder sb = new StringBuilder("{");
      Iterator<Entry<String, Object>> i = map.entrySet().iterator();
      Entry<String, Object> item = i.next();
      sb.append(EntityUtil.quote(item.getKey().toString()));
      sb.append(":");
      sb.append(EntityUtil.valueToString(item.getValue(), false, this));
      while (i.hasNext())
      {
         item = i.next();
         sb.append(",");
         sb.append(EntityUtil.quote(item.getKey().toString()));
         sb.append(":");
         sb.append(EntityUtil.valueToString(item.getValue(), false, this));
      }
      sb.append("}");
      return sb.toString();
   }

   /**
    * Make a prettyprinted JSON text of this JsonObject.
    * <p>
    * Warning: This method assumes that the data structure is acyclical.
    * 
    * @param indentFactor
    *           The number of spaces to add to each level of indentation.
    * @return a printable, displayable, portable, transmittable representation
    *         of the object, beginning with <code>{</code>&nbsp;<small>(left
    *         brace)</small> and ending with <code>}</code>&nbsp;<small>(right
    *         brace)</small>.
    */
   @Override
   public String toString(int indentFactor)
   {
      return toString(indentFactor, 0);
   }

   @Override
   public String toString(int indentFactor, int indent)
   {
      int length = this.size();
      if (length == 0)
      {
         return "{}";
      }

      Map<String, Object> map = getMap();

      if (!isVisible())
      {
         return "{" + map.size() + " values}";
      }

      Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();

      int newindent = indent + indentFactor;
      String prefix = "";
      StringBuilder sb;
      String step = EntityUtil.repeat(' ', indentFactor);
      if (indent > 0)
      {
         sb = new StringBuilder();
         for (int i = 0; i < indent; i += indentFactor)
         {
            sb.append(step);
         }
         prefix = CRLF + sb.toString();
      }
      else if (indentFactor > 0)
      {
         prefix = CRLF;
      }

      Entry<String, Object> item = iterator.next();
      Object value = item.getValue();
      if (length == 1 && !(value instanceof Entity))
      {
         sb = new StringBuilder("{");
      }
      else
      {
         sb = new StringBuilder("{" + prefix + step);
      }

      sb.append(EntityUtil.quote(item.getKey().toString()));
      sb.append(":");
      sb.append(EntityUtil.valueToString(value, indentFactor, newindent, false,
         this));
      while (iterator.hasNext())
      {
         item = iterator.next();
         sb.append("," + prefix + step);
         sb.append(EntityUtil.quote(item.getKey().toString()));
         sb.append(":");
         sb.append(EntityUtil.valueToString(item.getValue(), indentFactor,
            newindent, false, this));
      }
      if (length == 1 && !(value instanceof Entity))
      {
         sb.append("}");
      }
      else
      {
         sb.append(prefix + "}");
      }
      return sb.toString();
   }

   /**
    * Set the value to Tokener or pairs of values
    * 
    * @param values
    *           a simple String of Value or pairs of key-values
    */
   public JsonObject withValue(String... values)
   {
      this.getMap().clear();
      if (values.length % 2 == 0)
      {
         for (int z = 0; z < values.length; z += 2)
         {
            put(values[z], values[z + 1]);
         }
         return this;
      }
      if (values.length > 0)
      {
         new JsonTokener().withText(values[0]).parseToEntity(this);
      }
      return this;
   }

   /**
    * add the Values of the map to JsonObjectmap
    * 
    * @param map
    *           a map of key-values
    */
   public JsonObject withMap(Map<String, Object> map)
   {
      getMap();
      if (map != null)
      {
         Iterator<Entry<String, Object>> i = map.entrySet().iterator();
         while (i.hasNext())
         {
            Entry<String, Object> e = i.next();
            Object value = e.getValue();
            if (value != null)
            {
               this.put(e.getKey(), EntityUtil.wrap(value, this));
            }
         }
      }
      return this;
   }

   /**
    * Tokener to init the JsonObject
    * 
    * @param x
    *           tokener to add values with the tokener
    */
   public JsonObject withTokener(Tokener x)
   {
      x.parseToEntity(this);
      return this;
   }

   /**
    * Tokener to init the JsonObject
    * 
    * @param entity
    *           entity to add values with the tokener
    */
   public JsonObject withEntity(Entity entity)
   {
      new JsonTokener().parseToEntity(this, entity);
      return this;
   }

   /**
    * Get a new Instance of JsonArray
    */
   @Override
   public JsonArray getNewArray()
   {
      return new JsonArray();
   }

   /**
    * Get a new Instance of JsonObject
    */
   @Override
   public JsonObject getNewObject()
   {
      return new JsonObject();
   }
}
