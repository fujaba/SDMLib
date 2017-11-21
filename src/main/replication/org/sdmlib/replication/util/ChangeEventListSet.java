/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.replication.util;

import java.util.Collection;

import org.sdmlib.replication.ChangeEventList;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;

public class ChangeEventListSet extends SimpleSet<ChangeEventList>
{

   public static final ChangeEventListSet EMPTY_SET = new ChangeEventListSet().withFlag(ChangeEventListSet.READONLY);


   public ChangeEventListPO hasChangeEventListPO()
   {
      return new ChangeEventListPO(this.toArray(new ChangeEventList[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.ChangeEventList";
   }


   @SuppressWarnings("unchecked")
   public ChangeEventListSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChangeEventList>)value);
      }
      else if (value != null)
      {
         this.add((ChangeEventList) value);
      }
      
      return this;
   }
   
   public ChangeEventListSet without(ChangeEventList value)
   {
      this.remove(value);
      return this;
   }



   public ChangeEventListPO filterChangeEventListPO()
   {
      return new ChangeEventListPO(this.toArray(new ChangeEventList[this.size()]));
   }

   public ChangeEventListSet()
   {
      // empty
   }

   public ChangeEventListSet(ChangeEventList... objects)
   {
      for (ChangeEventList obj : objects)
      {
         this.add(obj);
      }
   }

   public ChangeEventListSet(Collection<ChangeEventList> objects)
   {
      this.addAll(objects);
   }


   public ChangeEventListPO createChangeEventListPO()
   {
      return new ChangeEventListPO(this.toArray(new ChangeEventList[this.size()]));
   }


   @Override
   public ChangeEventListSet getNewList(boolean keyValue)
   {
      return new ChangeEventListSet();
   }


   public ChangeEventListSet filter(Condition<ChangeEventList> condition) {
      ChangeEventListSet filterList = new ChangeEventListSet();
      filterItems(filterList, condition);
      return filterList;
   }}
