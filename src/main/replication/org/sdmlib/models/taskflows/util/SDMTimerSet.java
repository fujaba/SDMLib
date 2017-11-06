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
   
package org.sdmlib.models.taskflows.util;

import java.util.Collection;

import org.sdmlib.models.taskflows.SDMTimer;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;

public class SDMTimerSet extends SimpleSet<SDMTimer>
{


   public SDMTimerPO hasSDMTimerPO()
   {
      return new SDMTimerPO(this.toArray(new SDMTimer[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public SDMTimerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SDMTimer>)value);
      }
      else if (value != null)
      {
         this.add((SDMTimer) value);
      }
      
      return this;
   }
   
   public SDMTimerSet without(SDMTimer value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public SDMTimerSet schedule(java.util.TimerTask p0)
   {
      for (SDMTimer obj : this)
      {
         obj.schedule(p0);
      }
      return this;
   }


   public static final SDMTimerSet EMPTY_SET = new SDMTimerSet().withFlag(SDMTimerSet.READONLY);


   public SDMTimerPO filterSDMTimerPO()
   {
      return new SDMTimerPO(this.toArray(new SDMTimer[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.taskflows.SDMTimer";
   }

   public SDMTimerSet()
   {
      // empty
   }

   public SDMTimerSet(SDMTimer... objects)
   {
      for (SDMTimer obj : objects)
      {
         this.add(obj);
      }
   }

   public SDMTimerSet(Collection<SDMTimer> objects)
   {
      this.addAll(objects);
   }


   public SDMTimerPO createSDMTimerPO()
   {
      return new SDMTimerPO(this.toArray(new SDMTimer[this.size()]));
   }


   @Override
   public SDMTimerSet getNewList(boolean keyValue)
   {
      return new SDMTimerSet();
   }


   public SDMTimerSet filter(Condition<SDMTimer> condition) {
      SDMTimerSet filterList = new SDMTimerSet();
      filterItems(filterList, condition);
      return filterList;
   }}
