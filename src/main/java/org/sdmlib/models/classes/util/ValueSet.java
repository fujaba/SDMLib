/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.models.classes.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.classes.Value;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import de.uniks.networkparser.graph.DataType;

public class ValueSet extends SDMSet<Value>
{

   public static final ValueSet EMPTY_SET = new ValueSet().withFlag(ValueSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Value";
   }


   @SuppressWarnings("unchecked")
   public ValueSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Value>)value);
      }
      else if (value != null)
      {
         this.add((Value) value);
      }
      
      return this;
   }
   
   public ValueSet without(Value value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public ValueSet filter(Condition<Value> newValue) {
      ValueSet filterList = new ValueSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Value objects and collect a list of the initialization attribute values. 
    * 
    * @return List of String objects reachable via initialization attribute
    */
   public StringList getInitialization()
   {
      StringList result = new StringList();
      
      for (Value obj : this)
      {
         result.add(obj.getInitialization());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Value objects and collect those Value objects where the initialization attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Value objects that match the parameter
    */
   public ValueSet filterInitialization(String value)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (value.equals(obj.getInitialization()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Value objects and collect those Value objects where the initialization attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Value objects that match the parameter
    */
   public ValueSet filterInitialization(String lower, String upper)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (lower.compareTo(obj.getInitialization()) <= 0 && obj.getInitialization().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Value objects and assign value to the initialization attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Value objects now with new attribute values.
    */
   public ValueSet withInitialization(String value)
   {
      for (Value obj : this)
      {
         obj.setInitialization(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Value objects and collect a list of the type attribute values. 
    * 
    * @return List of de.uniks.networkparser.graph.DataType objects reachable via type attribute
    */
   public DataTypeSet getType()
   {
      DataTypeSet result = new DataTypeSet();
      
      for (Value obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Value objects and collect those Value objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Value objects that match the parameter
    */
   public ValueSet filterType(DataType value)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Value objects and assign value to the type attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Value objects now with new attribute values.
    */
   public ValueSet withType(DataType value)
   {
      for (Value obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Value objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Value obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Value objects and collect those Value objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Value objects that match the parameter
    */
   public ValueSet filterName(String value)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Value objects and collect those Value objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Value objects that match the parameter
    */
   public ValueSet filterName(String lower, String upper)
   {
      ValueSet result = new ValueSet();
      
      for (Value obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Value objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Value objects now with new attribute values.
    */
   public ValueSet withName(String value)
   {
      for (Value obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

}
