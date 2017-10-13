/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.simple.model.enums_d.util;

import de.uniks.networkparser.list.SimpleSet;
import java.lang.Integer;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;

public class IntegerSet extends SimpleSet<Integer>
{
	public Class<?> getTypClass() {
		return Integer.class;
	}

   public IntegerSet()
   {
      // empty
   }

   public IntegerSet(Integer... objects)
   {
      for (Integer obj : objects)
      {
         this.add(obj);
      }
   }

   public IntegerSet(Collection<Integer> objects)
   {
      this.addAll(objects);
   }

   public static final IntegerSet EMPTY_SET = new IntegerSet().withFlag(IntegerSet.READONLY);


   public IntegerPO createIntegerPO()
   {
      return new IntegerPO(this.toArray(new Integer[this.size()]));
   }


   public String getEntryType()
   {
      return "java.lang.Integer";
   }


   @Override
   public IntegerSet getNewList(boolean keyValue)
   {
      return new IntegerSet();
   }


   public IntegerSet filter(Condition<Integer> condition) {
      IntegerSet filterList = new IntegerSet();
      filterItems(filterList, condition);
      return filterList;
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

}
