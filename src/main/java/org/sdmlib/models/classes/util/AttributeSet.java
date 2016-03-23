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
import org.sdmlib.models.classes.Attribute;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import de.uniks.networkparser.graph.DataType;

public class AttributeSet extends SDMSet<Attribute>
{

   public static final AttributeSet EMPTY_SET = new AttributeSet().withFlag(AttributeSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Attribute";
   }


   @SuppressWarnings("unchecked")
   public AttributeSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Attribute>)value);
      }
      else if (value != null)
      {
         this.add((Attribute) value);
      }
      
      return this;
   }
   
   public AttributeSet without(Attribute value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public AttributeSet filter(Condition<Attribute> newValue) {
      AttributeSet filterList = new AttributeSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Attribute objects and collect a list of the initialization attribute values. 
    * 
    * @return List of String objects reachable via initialization attribute
    */
   public StringList getInitialization()
   {
      StringList result = new StringList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getInitialization());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and collect those Attribute objects where the initialization attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Attribute objects that match the parameter
    */
   public AttributeSet filterInitialization(String value)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (value.equals(obj.getInitialization()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and collect those Attribute objects where the initialization attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Attribute objects that match the parameter
    */
   public AttributeSet filterInitialization(String lower, String upper)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (lower.compareTo(obj.getInitialization()) <= 0 && obj.getInitialization().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and assign value to the initialization attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Attribute objects now with new attribute values.
    */
   public AttributeSet withInitialization(String value)
   {
      for (Attribute obj : this)
      {
         obj.setInitialization(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Attribute objects and collect a list of the type attribute values. 
    * 
    * @return List of de.uniks.networkparser.graph.DataType objects reachable via type attribute
    */
   public DataTypeSet getType()
   {
      DataTypeSet result = new DataTypeSet();
      
      for (Attribute obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and collect those Attribute objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Attribute objects that match the parameter
    */
   public AttributeSet filterType(DataType value)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and assign value to the type attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Attribute objects now with new attribute values.
    */
   public AttributeSet withType(DataType value)
   {
      for (Attribute obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Attribute objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and collect those Attribute objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Attribute objects that match the parameter
    */
   public AttributeSet filterName(String value)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and collect those Attribute objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Attribute objects that match the parameter
    */
   public AttributeSet filterName(String lower, String upper)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Attribute objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Attribute objects now with new attribute values.
    */
   public AttributeSet withName(String value)
   {
      for (Attribute obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

}
