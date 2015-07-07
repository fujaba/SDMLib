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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.simpleModel.model.MacList;

public class MacListSet extends SDMSet<MacList>
{

   public static final MacListSet EMPTY_SET = new MacListSet().withReadOnly(true);


   public MacListPO hasMacListPO()
   {
      return new MacListPO(this.toArray(new MacList[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.simpleModel.model.MacList";
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

}
