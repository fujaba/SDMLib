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
   
package org.sdmlib.examples.simpleEnumModel.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.simpleEnumModel.model.Alex;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;

public class AlexSet extends SDMSet<Alex>
{


   public AlexPO hasAlexPO()
   {
      return new AlexPO(this.toArray(new Alex[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.simpleEnumModel.model.Alex";
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

}
