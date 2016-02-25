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
import org.sdmlib.models.classes.Clazz;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.StringList;

public class ClazzSet extends SDMSet<Clazz>
{

   public static final ClazzSet EMPTY_SET = new ClazzSet().withFlag(ClazzSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Clazz";
   }


   @SuppressWarnings("unchecked")
   public ClazzSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Clazz>)value);
      }
      else if (value != null)
      {
         this.add((Clazz) value);
      }
      
      return this;
   }
   
   public ClazzSet without(Clazz value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public ClazzSet filter(Condition<Clazz> newValue) {
      ClazzSet filterList = new ClazzSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Clazz objects and collect a list of the interfaze attribute values. 
    * 
    * @return List of boolean objects reachable via interfaze attribute
    */
   public booleanList getInterfaze()
   {
      booleanList result = new booleanList();
      
      for (Clazz obj : this)
      {
         result.add(obj.isInterfaze());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Clazz objects and collect those Clazz objects where the interfaze attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Clazz objects that match the parameter
    */
   public ClazzSet filterInterfaze(boolean value)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (value == obj.isInterfaze())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Clazz objects and assign value to the interfaze attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Clazz objects now with new attribute values.
    */
   public ClazzSet withInterfaze(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.setInterfaze(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Clazz objects and collect a list of the external attribute values. 
    * 
    * @return List of boolean objects reachable via external attribute
    */
   public booleanList getExternal()
   {
      booleanList result = new booleanList();
      
      for (Clazz obj : this)
      {
         result.add(obj.isExternal());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Clazz objects and collect those Clazz objects where the external attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Clazz objects that match the parameter
    */
   public ClazzSet filterExternal(boolean value)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (value == obj.isExternal())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Clazz objects and assign value to the external attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Clazz objects now with new attribute values.
    */
   public ClazzSet withExternal(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.setExternal(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Clazz objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Clazz obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Clazz objects and collect those Clazz objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Clazz objects that match the parameter
    */
   public ClazzSet filterName(String value)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Clazz objects and collect those Clazz objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Clazz objects that match the parameter
    */
   public ClazzSet filterName(String lower, String upper)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Clazz objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Clazz objects now with new attribute values.
    */
   public ClazzSet withName(String value)
   {
      for (Clazz obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

}
