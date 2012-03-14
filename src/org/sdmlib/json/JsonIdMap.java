/*
   Copyright (c) 2012 Albert Zündorf

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software.

   The Software shall be used for Good, not Evil.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.sdmlib.json;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class JsonIdMap
{

   private HashMap<String, Object> objects = new HashMap<String, Object>();

   private HashMap<Object, String> keys = new HashMap<Object, String>();
   private HashMap<String, JsonCreator> instances = new HashMap<String, JsonCreator>();

   private String sessionId;

   public void setSessionId(String sessionId)
   {
      this.sessionId = sessionId;
   }

   public JsonIdMap()
   {

   }

   public JsonIdMap(String sessionID)
   {
      this.sessionId = sessionID;
   }

   private long number = 1;

   public String getId(Object obj)
   {
      String key = keys.get(obj);

      if (key == null)
      {
         // new object generate key and add to tables
         // <session id>.<first char><running number>
         if (obj == null)
         {
            try
            {
               throw new Exception("NullPointer: " + obj);
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
         String className = obj.getClass().getName();
         char firstChar = className.charAt(className.lastIndexOf('.') + 1);
         if (sessionId != null)
         {
            key = sessionId + "." + firstChar + number;
         }
         else
         {
            key = "" + firstChar + number;
         }
         number++;

         objects.put(key, obj);
         keys.put(obj, key);
      }

      return key;
   }

   public Object getObject(String key)
   {
      return objects.get(key);
   }

   public void remove(Object oldValue)
   {
      String key = keys.get(oldValue);
      if (key != null)
      {
         keys.remove(oldValue);
         objects.remove(key);
      }

   }

   public int size()
   {
      return keys.size();
   }

   public void put(String jsonId, Object object)
   {
      // register object under old id
      // if (objects.get(jsonId) != null)
      // {
      // throw new RuntimeException("id already in use: " + jsonId);
      // }
      objects.put(jsonId, object);
      keys.put(object, jsonId);
   }

   public JsonObject toJsonObject(Object object)
   {
      JsonArray jsonArray;
      String id = getId(object);
      String className = object.getClass().getName();

      JsonObject jsonObject = new JsonObject();
      jsonObject.put(Const.JSON_ID, id).put(Const.CLASS, className);
      JsonCreator prototyp = instances.get(object.getClass().getName());

      String[] attributes = prototyp.getAttributesTypes();
      if (attributes != null)
      {
         for (String attribute : attributes)
         {
            jsonObject.put(attribute, prototyp.get(object, attribute)); // none
         }
      }

      String[] references = prototyp.getReferenceTypes();
      if (references != null)
      {
         for (String reference : references)
         {
            Object subObject = prototyp.get(object, reference);
            if (subObject != null)
            {
               if (subObject instanceof Collection)
               {
                  JsonArray refArray = new JsonArray();
                  for (Object containee : ((Collection) subObject))
                  {
                     refArray.put(this.getId(containee));
                  }
                  jsonObject.put(reference, refArray);
               }
               else
               {
                  jsonObject.put(reference, this.getId(subObject));
               }
            }
         }
      }

      String[] aggregationen = prototyp.getAggregationTypes();
      if (aggregationen != null)
      {
         for (String aggregation : aggregationen)
         {
            jsonArray = new JsonArray();
            Object value = prototyp.get(object, aggregation);
            if (value instanceof Collection<?>)
            {
               Collection<?> valueSet = (Collection<?>) value;
               if (valueSet != null)
               {
                  for (Iterator<?> iter = valueSet.iterator(); iter.hasNext();)
                  {

                     Object subObject = iter.next();
                     if (subObject != null)
                     {
                        jsonArray.put(this.toJsonObject(subObject));
                     }
                  }
               }
            }
            else if (value != null)
            {
               jsonArray.put(this.toJsonObject(value));
            }
            jsonObject.put(aggregation, jsonArray); // shared
         }
      }
      return jsonObject;
   }

   public Object readJson(Object jsonObject)
   {
      Object result = null;
      if (jsonObject instanceof JsonArray)
      {
         JsonArray jsonArray = (JsonArray) jsonObject;
         for (int i = 0; i < jsonArray.length(); i++)
         {
            JsonObject kidObject = jsonArray.getJSONObject(i);
            Object tmp = readJson(kidObject);
            if (i == 0)
            {
               result = tmp;
            }
         }
      }
      return result;
   }

   public Object readJson(JsonObject jsonObject)
   {
      Object result = null;

      Object className = jsonObject.get(Const.CLASS);

      if (className != null)
      {
         JsonCreator typeInfo = instances.get(className);

         if (typeInfo != null)
         {
            result = typeInfo.newInstance();
            readJson(result, jsonObject);
         }
      }

      return result;
   }

   public void readJson(Object target, JsonObject jsonObject)
   {
      // JSONArray jsonArray;
      String jsonId = (String) jsonObject.get(Const.JSON_ID);
      if (jsonId == null)
      {
         return;
      }
      this.put(jsonId, target);

      // adjust number to be higher than read numbers
      String[] split = jsonId.split("\\.");

      if (split.length != 2)
      {
         throw new RuntimeException("jsonid " + jsonId
               + " should have one . in its middle");
      }

      if (sessionId.equals(split[0]))
      {
         String oldNumber = split[1].substring(1);
         long oldInt = Long.parseLong(oldNumber);
         if (oldInt >= number)
         {
            number = oldInt + 1;
         }
      }

      readJsonAttribute(target, jsonObject);

      readJsonReference(target, jsonObject);

      readJsonAggregation(target, jsonObject);
   }

   private void readJsonAttribute(Object target, JsonObject jsonObject)
   {
      JsonCreator prototyp = instances.get(target.getClass().getName());
      String[] attributes = prototyp.getAttributesTypes();
      if (attributes != null)
      {
         for (String attribute : attributes)
         {
            Object obj = jsonObject.get(attribute);
            Object oldObj = null;

            if (obj == null)
            {
               oldObj = jsonObject.get(attribute + Const.REMOVE_SUFFIX);

               if (oldObj == null)
               {
                  // nothing known about this attribute
                  continue;
               }
            }
            prototyp.set(target, attribute, obj);
         }
      }
   }

   public JsonCreator getCreaterClass(Object reference)
   {
      return instances.get(reference.getClass().getName());
   }

   private void readJsonReference(Object target, JsonObject jsonObject)
   {
      JsonCreator prototyp = getCreaterClass(target);
      String[] references = prototyp.getReferenceTypes();
      if (references != null)
      {
         for (String reference : references)
         {
            Object obj = jsonObject.get(reference);
            Object oldObj = null;

            if (obj == null)
            {
               oldObj = jsonObject.get(reference + Const.REMOVE_SUFFIX);

               if (oldObj == null)
               {
                  // nothing known about this attribute
                  continue;
               }
            }

            if (obj != null)
            {
               if (obj instanceof JsonArray)
               {
                  JsonArray jsonArray = (JsonArray) obj;
                  for (int i = 0; i < jsonArray.length(); i++)
                  {
                     obj = jsonArray.get(i);

                     // got the id, ask the map
                     obj = this.getObject((String) obj);
                     prototyp.set(target, reference, obj);
                  }
               }
               else
               {
                  // got the id, ask the map
                  obj = getObject((String) obj);
                  prototyp.set(target, reference, obj);
               }
            }
            else
            {
               // oldObj != null thus remove
               if (oldObj instanceof JsonArray)
               {
                  JsonArray jsonArray = (JsonArray) oldObj;
                  for (int i = 0; i < jsonArray.length(); i++)
                  {
                     obj = jsonArray.get(i);

                     // got the id, ask the map
                     obj = getObject((String) obj);
                     prototyp.set(target, reference + Const.REMOVE_SUFFIX, obj);
                  }
               }
               else
               {
                  // just remove
                  prototyp.set(target, reference, null);
               }
            }
         }
      }
   }

   private void readJsonAggregation(Object target, JsonObject jsonObject)
   {
      JsonCreator prototyp = instances.get(target.getClass().getName());
      String[] aggregationen = prototyp.getAggregationTypes();
      if (aggregationen != null)
      {
         for (String aggregation : aggregationen)
         {
            Object obj = jsonObject.get(aggregation);
            Object oldObj = null;

            if (obj == null)
            {
               oldObj = jsonObject.get(aggregation + Const.REMOVE_SUFFIX);

               if (oldObj == null)
               {
                  // nothing known about this attribute
                  continue;
               }
            }

            if (obj != null)
            {
               if (obj instanceof JsonArray)
               {
                  JsonArray jsonArray = (JsonArray) obj;
                  for (int i = 0; i < jsonArray.length(); i++)
                  {
                     Object kidRef = jsonArray.get(i);

                     if (kidRef instanceof String)
                     {
                        // got a reference
                        // got the id, ask the map
                        Object kidObj = this.getObject((String) kidRef);
                        if (kidObj != null)
                        {
                           prototyp.set(target, aggregation, kidObj);
                        }
                     }
                     else
                     {
                        JsonObject jsonKid = (JsonObject) kidRef;

                        // got a new kid, create it
                        String className = jsonKid.getString(Const.CLASS);
                        JsonCreator jsonClass = instances.get(className);
                        Object kid = jsonClass.newInstance();
                        readJson(kid, jsonKid);
                        prototyp.set(target, aggregation, kid);
                     }
                  }
               }
               else
               {
                  // got a new kid, create it
                  if (obj instanceof String)
                  {

                     prototyp.set(target, aggregation, getObject((String) obj));
                  }
                  else
                  {
                     JsonObject jsonKid = (JsonObject) obj;
                     String className = jsonKid.getString(Const.CLASS);
                     JsonCreator jsonClass = instances.get(className);
                     Object kid = jsonClass.newInstance();
                     readJson(kid, jsonKid);
                     prototyp.set(target, aggregation, kid);
                  }

               }
            }
            else
            {
               // oldObj != null thus remove jsonId(s)
               if (oldObj instanceof JsonArray)
               {
                  JsonArray jsonArray = (JsonArray) oldObj;
                  for (int i = 0; i < jsonArray.length(); i++)
                  {
                     obj = jsonArray.get(i);

                     // got the id, ask the map
                     obj = getObject((String) obj);
                     prototyp.set(target, aggregation + Const.REMOVE_SUFFIX, obj);
                  }
               }
               else
               {
                  // just remove
                  obj = getObject((String) oldObj);
                  prototyp.set(target, aggregation + Const.REMOVE_SUFFIX, obj);
               }
            }
         }
      }
   }

   public void addCreater(JsonCreator createrClass)
   {
      instances.put(createrClass.getClassName(), createrClass);

   }

   public JsonIdMap withCreater(JsonCreator createrClass)
   {
      this.addCreater(createrClass);
      return this;
   }

   public JsonArray toJsonArray(Object object)
   {
      return toJsonArray(object, Long.MAX_VALUE);
   }

   public JsonArray toJsonArray(Object object, long depth)
   {
      JsonArray jsonArray = new JsonArray();

      toJsonArray(jsonArray, object, depth);

      return jsonArray;
   }

   public void toJsonArray(JsonArray jsonArray, Object object, long depth)
   {
      if (depth < 0)
         return; // <=============== only kids up to a certain depth
      String id = getId(object);
      String className = object.getClass().getName();

      JsonObject jsonObject = new JsonObject();
      jsonObject.put(Const.JSON_ID, id).put(Const.CLASS, className);
      JsonCreator prototyp = instances.get(object.getClass().getName());

      String[] attributes = prototyp.getAttributesTypes();
      if (attributes != null)
      {
         for (String attribute : attributes)
         {
            jsonObject.put(attribute, prototyp.get(object, attribute)); // none
         }
      }

      String[] references = prototyp.getReferenceTypes();
      if (references != null)
      {
         for (String reference : references)
         {
            Object subObject = prototyp.get(object, reference);
            if (subObject != null)
            {
               if (subObject instanceof Collection)
               {
                  JsonArray refArray = new JsonArray();
                  for (Object containee : ((Collection) subObject))
                  {
                     refArray.put(this.getId(containee));
                  }
                  jsonObject.put(reference, refArray);
               }
               else
               {
                  jsonObject.put(reference, this.getId(subObject));
               }
            }
         }
      }

      jsonArray.put(jsonObject);

      // add the kids recursively
      String[] aggregationen = prototyp.getAggregationTypes();
      if (aggregationen != null)
      {
         for (String aggregation : aggregationen)
         {
            Object value = prototyp.get(object, aggregation);
            if (value instanceof Collection<?>)
            {
               Collection<?> valueSet = (Collection<?>) value;
               if (valueSet != null)
               {
                  JsonArray refArray = new JsonArray();
                  for (Iterator<?> iter = valueSet.iterator(); iter.hasNext();)
                  {
                     Object subObject = iter.next();

                     if (subObject != null)
                     {
                        refArray.put(this.getId(subObject));
                        this.toJsonArray(jsonArray, subObject, depth - 1);
                     }
                  }
                  jsonObject.put(aggregation, refArray);
               }
            }
            else if (value != null)
            {
               jsonObject.put(aggregation, this.getId(value));
               this.toJsonArray(jsonArray, value, depth - 1);
            }
         }
      }
   }

}
