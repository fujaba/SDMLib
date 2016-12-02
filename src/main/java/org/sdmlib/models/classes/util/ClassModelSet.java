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

import java.util.Collection;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.modelsets.SDMSet;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.StringList;

public class ClassModelSet extends SDMSet<ClassModel>
{

   public static final ClassModelSet EMPTY_SET = new ClassModelSet().withFlag(ClassModelSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.ClassModel";
   }


   @SuppressWarnings("unchecked")
   public ClassModelSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ClassModel>)value);
      }
      else if (value != null)
      {
         this.add((ClassModel) value);
      }
      
      return this;
   }
   
   public ClassModelSet without(ClassModel value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public ClassModelSet filter(Condition<ClassModel> newValue) {
      ClassModelSet filterList = new ClassModelSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of ClassModel objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (ClassModel obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of ClassModel objects and collect those ClassModel objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ClassModel objects that match the parameter
    */
   public ClassModelSet filterName(String value)
   {
      ClassModelSet result = new ClassModelSet();
      
      for (ClassModel obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ClassModel objects and collect those ClassModel objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ClassModel objects that match the parameter
    */
   public ClassModelSet filterName(String lower, String upper)
   {
      ClassModelSet result = new ClassModelSet();
      
      for (ClassModel obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ClassModel objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of ClassModel objects now with new attribute values.
    */
   public ClassModelSet withName(String value)
   {
      for (ClassModel obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }

}
