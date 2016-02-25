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
   
package org.sdmlib.test.examples.simpleModel.model.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.simpleModel.model.MacList;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.models.modelsets.longList;

public class MacListSet extends SimpleSet<MacList>
{

   public static final MacListSet EMPTY_SET = new MacListSet().withFlag(MacListSet.READONLY);


   public MacListPO hasMacListPO()
   {
      return new MacListPO(this.toArray(new MacList[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public MacListSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MacList>)value);
      }
      else if (value != null)
      {
         this.add((MacList) value);
      }
      
      return this;
   }
   
   public MacListSet without(MacList value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (MacList obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public MacListSet hasName(String value)
   {
      MacListSet result = new MacListSet();
      
      for (MacList obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MacListSet hasName(String lower, String upper)
   {
      MacListSet result = new MacListSet();
      
      for (MacList obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MacListSet withName(String value)
   {
      for (MacList obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }



   public MacListPO filterMacListPO()
   {
      return new MacListPO(this.toArray(new MacList[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.simpleModel.model.MacList";
   }

   /**
    * Loop through the current set of MacList objects and collect a list of the serialVersionUID attribute values. 
    * 
    * @return List of long objects reachable via serialVersionUID attribute
    */
   public longList getSerialVersionUID()
   {
      longList result = new longList();
      
      for (MacList obj : this)
      {
         result.add(obj.getSerialVersionUID());
      }
      
      return result;
   }


   /**
    * Loop through the current set of MacList objects and collect those MacList objects where the serialVersionUID attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MacList objects that match the parameter
    */
   public MacListSet filterSerialVersionUID(long value)
   {
      MacListSet result = new MacListSet();
      
      for (MacList obj : this)
      {
         if (value == obj.getSerialVersionUID())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MacList objects and collect those MacList objects where the serialVersionUID attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MacList objects that match the parameter
    */
   public MacListSet filterSerialVersionUID(long lower, long upper)
   {
      MacListSet result = new MacListSet();
      
      for (MacList obj : this)
      {
         if (lower <= obj.getSerialVersionUID() && obj.getSerialVersionUID() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MacList objects and assign value to the serialVersionUID attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of MacList objects now with new attribute values.
    */
   public MacListSet withSerialVersionUID(long value)
   {
      for (MacList obj : this)
      {
         obj.setSerialVersionUID(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of MacList objects and collect those MacList objects where the Name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MacList objects that match the parameter
    */
   public MacListSet filterName(String value)
   {
      MacListSet result = new MacListSet();
      
      for (MacList obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MacList objects and collect those MacList objects where the Name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MacList objects that match the parameter
    */
   public MacListSet filterName(String lower, String upper)
   {
      MacListSet result = new MacListSet();
      
      for (MacList obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
