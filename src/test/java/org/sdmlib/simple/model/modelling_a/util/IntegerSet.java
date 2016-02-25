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
   
package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.modelsets.SDMSet;
import java.lang.Integer;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;

public class IntegerSet extends SDMSet<Integer>
{

   public static final IntegerSet EMPTY_SET = new IntegerSet().withFlag(IntegerSet.READONLY);


   public IntegerPO filterIntegerPO()
   {
      return new IntegerPO(this.toArray(new Integer[this.size()]));
   }


   public String getEntryType()
   {
      return "java.lang.Integer";
   }


   @SuppressWarnings("unchecked")
   public IntegerSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Integer>)value);
      }
      else if (value != null)
      {
         this.add((Integer) value);
      }
      
      return this;
   }
   
   public IntegerSet without(Integer value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public IntegerSet filter(Condition<Integer> newValue) {
      IntegerSet filterList = new IntegerSet();
      filterItems(filterList, newValue);
      return filterList;
   }
}
