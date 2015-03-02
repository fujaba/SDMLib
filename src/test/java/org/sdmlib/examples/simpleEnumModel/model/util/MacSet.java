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

import java.util.Collection;

import org.sdmlib.examples.simpleEnumModel.model.Mac;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;

public class MacSet extends SDMSet<Mac>
{


   public MacPO hasMacPO()
   {
      return new MacPO(this.toArray(new Mac[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.simpleEnumModel.model.Mac";
   }


   @SuppressWarnings("unchecked")
   public MacSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Mac>)value);
      }
      else if (value != null)
      {
         this.add((Mac) value);
      }
      
      return this;
   }
   
   public MacSet without(Mac value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Mac obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public MacSet hasName(String value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MacSet hasName(String lower, String upper)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MacSet withName(String value)
   {
      for (Mac obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   public static final MacSet EMPTY_SET = new MacSet().withReadOnly(true);
}
