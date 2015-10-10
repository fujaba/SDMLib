/*
   Copyright (c) 2012 zuendorf 
   
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

package org.sdmlib.models.taskflows;

import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeListener;
   /**
    * 
    * @see <a href='../../../../../../../src/main/replication/org/sdmlib/models/taskflows/TaskFlowObjectScenarioForCoverage.java'>TaskFlowObjectScenarioForCoverage.java</a>
*/
   public class SDMTimer extends Timer implements PropertyChangeInterface
{
   public SDMTimer(String name)
   {
      super(name);
   }

     /**
    * 
    * @see <a href='../../../../../../../src/main/replication/org/sdmlib/models/taskflows/TaskFlowObjectScenarioForCoverage.java'>TaskFlowObjectScenarioForCoverage.java</a>
*/
   public SDMTimer()
   {

   }

   private long lastScheduleTime = 0;

   // ==========================================================================

   public void schedule(TimerTask task)
   {
      long now = System.currentTimeMillis();
      long delay = 0;

      if (now <= lastScheduleTime)
      {
         // this milli second has already been used. Use next free to achieve
         // stable schedule
         delay = lastScheduleTime - now + 1;
         lastScheduleTime++;
      }
      else
      {
         delay = 0;
         lastScheduleTime = now;
      }

      super.schedule(task, delay);
   }

   // ==========================================================================

   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   // ==========================================================================

   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      // super.removeYou();
   }
   
}
