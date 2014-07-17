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
   
package org.sdmlib.model.taskflows.util;

import java.util.Collection;

import org.sdmlib.model.taskflows.SDMTimer;
import org.sdmlib.models.modelsets.SDMSet;

public class SDMTimerSet extends SDMSet<SDMTimer>
{


   public SDMTimerPO hasSDMTimerPO()
   {
      return new SDMTimerPO(this.toArray(new SDMTimer[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.SDMTimer";
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

}
