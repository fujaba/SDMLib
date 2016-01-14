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
   
package org.sdmlib.modelspace;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.modelspace.util.TaskLaneSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.json.JsonIdMap;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/modelspace/ModelSpaceModel.java'>ModelSpaceModel.java</a>
*/
   public  class TaskBoard implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   private ModelCloud modelCloud;

   private JsonIdMap taskIdMap;
   
   public TaskBoard(ModelCloud modelCloud, JsonIdMap taskIdMap)
   {
      this.modelCloud = modelCloud;
      this.taskIdMap = taskIdMap;
   }

   public TaskBoard()
   {
      // empty
   }

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      withoutLanes(this.getLanes().toArray(new TaskLane[this.getLanes().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * TaskBoard ----------------------------------- TaskLane
    *              board                   lanes
    * </pre>
    */
   
   public static final String PROPERTY_LANES = "lanes";

   private TaskLaneSet lanes = null;
   
   public TaskLaneSet getLanes()
   {
      if (this.lanes == null)
      {
         return TaskLaneSet.EMPTY_SET;
      }
   
      return this.lanes;
   }

   public TaskBoard withLanes(TaskLane... value)
   {
      if(value==null){
         return this;
      }
      for (TaskLane item : value)
      {
         if (item != null)
         {
            if (this.lanes == null)
            {
               this.lanes = new TaskLaneSet();
            }
            
            boolean changed = this.lanes.add (item);

            if (changed)
            {
               item.withBoard(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_LANES, null, item);
            }
         }
      }
      return this;
   } 

   public TaskBoard withoutLanes(TaskLane... value)
   {
      for (TaskLane item : value)
      {
         if ((this.lanes != null) && (item != null))
         {
            if (this.lanes.remove(item))
            {
               item.setBoard(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_LANES, item, null);
            }
         }
      }
      return this;
   }

   public TaskLane createLanes()
   {
      TaskLane value = new TaskLane();
      withLanes(value);
      return value;
   }

   public TaskLane getOrCreateLane(String hostName, long portNo)
   {
      for (TaskLane lane : this.getLanes())
      {
         if (lane.getHostName().equals(hostName) && lane.getPortNo() == portNo)
         {
            return lane;
         }
      }
      
      TaskLane lane = new TaskLane();
      
      taskIdMap.put(hostName + portNo, lane);
      
      lane.withHostName(hostName).withPortNo(portNo);
      
      this.withLanes(lane);
      
      return lane;
   }
}
