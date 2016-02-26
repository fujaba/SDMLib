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
   
package org.sdmlib.simple.model.enums_d.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.enums_d.TestEnum;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import java.lang.Integer;

public class TestEnumSet extends SDMSet<TestEnum>
{

   public static final TestEnumSet EMPTY_SET = new TestEnumSet().withFlag(TestEnumSet.READONLY);


   public TestEnumPO filterTestEnumPO()
   {
      return new TestEnumPO(this.toArray(new TestEnum[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.enums_d.TestEnum";
   }


   @SuppressWarnings("unchecked")
   public TestEnumSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TestEnum>)value);
      }
      else if (value != null)
      {
         this.add((TestEnum) value);
      }
      
      return this;
   }
   
   public TestEnumSet without(TestEnum value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public TestEnumSet filter(Condition<TestEnum> newValue) {
      TestEnumSet filterList = new TestEnumSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of TestEnum objects and collect a list of the value0 attribute values. 
    * 
    * @return List of java.lang.Integer objects reachable via value0 attribute
    */
   public IntegerSet getValue0()
   {
      IntegerSet result = new IntegerSet();
      
      for (TestEnum obj : this)
      {
         result.add(obj.getValue0());
      }
      
      return result;
   }


   /**
    * Loop through the current set of TestEnum objects and collect those TestEnum objects where the value0 attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TestEnum objects that match the parameter
    */
   public TestEnumSet filterValue0(Integer value)
   {
      TestEnumSet result = new TestEnumSet();
      
      for (TestEnum obj : this)
      {
         if (value == obj.getValue0())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TestEnum objects and assign value to the value0 attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of TestEnum objects now with new attribute values.
    */
   public TestEnumSet withValue0(Integer value)
   {
      for (TestEnum obj : this)
      {
         obj.setValue0(value);
      }
      
      return this;
   }

}
