/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.simpleEnumModel.model.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.simpleEnumModel.model.Alex;

import de.uniks.networkparser.list.SimpleSet;

public class AlexSet extends SimpleSet<Alex>
{


   public AlexPO hasAlexPO()
   {
      return new AlexPO(this.toArray(new Alex[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public AlexSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Alex>)value);
      }
      else if (value != null)
      {
         this.add((Alex) value);
      }
      
      return this;
   }
   
   public AlexSet without(Alex value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Alex obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public AlexSet hasName(String value)
   {
      AlexSet result = new AlexSet();
      
      for (Alex obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AlexSet hasName(String lower, String upper)
   {
      AlexSet result = new AlexSet();
      
      for (Alex obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AlexSet withName(String value)
   {
      for (Alex obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   public static final AlexSet EMPTY_SET = new AlexSet().withFlag(AlexSet.READONLY);


   public AlexPO filterAlexPO()
   {
      return new AlexPO(this.toArray(new Alex[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.simpleEnumModel.model.Alex";
   }

   /**
    * Loop through the current set of Alex objects and collect those Alex objects where the Name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Alex objects that match the parameter
    */
   public AlexSet filterName(String value)
   {
      AlexSet result = new AlexSet();
      
      for (Alex obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Alex objects and collect those Alex objects where the Name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Alex objects that match the parameter
    */
   public AlexSet filterName(String lower, String upper)
   {
      AlexSet result = new AlexSet();
      
      for (Alex obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
