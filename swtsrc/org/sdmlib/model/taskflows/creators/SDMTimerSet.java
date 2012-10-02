package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import java.util.TimerTask;

import org.sdmlib.model.taskflows.SDMTimer;

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

}

