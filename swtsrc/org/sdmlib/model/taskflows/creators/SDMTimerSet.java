package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import java.util.TimerTask;

import org.sdmlib.model.taskflows.SDMTimer;
import org.sdmlib.models.modelsets.StringList;

public class SDMTimerSet extends LinkedHashSet<SDMTimer>
{
   
   //==========================================================================
   
   public SDMTimerSet schedule(TimerTask p0)
   {
      for (SDMTimer obj : this)
      {
         obj.schedule( p0);
      }
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (SDMTimer elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.SDMTimer";
   }


   public SDMTimerSet with(SDMTimer value)
   {
      this.add(value);
      return this;
   }
   
   public SDMTimerSet without(SDMTimer value)
   {
      this.remove(value);
      return this;
   }
}


