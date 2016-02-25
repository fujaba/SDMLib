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
   
package org.sdmlib.simple.model.attribute_i.util;

import org.sdmlib.models.modelsets.SDMSet;
import de.uniks.networkparser.list.SimpleKeyValueList;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;

public class SimpleKeyValueListSet extends SDMSet<SimpleKeyValueList>
{

   public static final SimpleKeyValueListSet EMPTY_SET = new SimpleKeyValueListSet().withFlag(SimpleKeyValueListSet.READONLY);


   public SimpleKeyValueListPO filterSimpleKeyValueListPO()
   {
      return new SimpleKeyValueListPO(this.toArray(new SimpleKeyValueList[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.networkparser.list.SimpleKeyValueList";
   }


   @SuppressWarnings("unchecked")
   public SimpleKeyValueListSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SimpleKeyValueList>)value);
      }
      else if (value != null)
      {
         this.add((SimpleKeyValueList) value);
      }
      
      return this;
   }
   
   public SimpleKeyValueListSet without(SimpleKeyValueList value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public SimpleKeyValueListSet filter(Condition<SimpleKeyValueList> newValue) {
      SimpleKeyValueListSet filterList = new SimpleKeyValueListSet();
      filterItems(filterList, newValue);
      return filterList;
   }
}
