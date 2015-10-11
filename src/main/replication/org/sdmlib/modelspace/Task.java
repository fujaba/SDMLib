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

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/modelspace/ModelSpaceModel.java'>ModelSpaceModel.java</a>
*/
   public  class Task implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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
   
      setLane(null);
      setFileTargetCloud(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getState());
      result.append(" ").append(this.getSpaceName());
      result.append(" ").append(this.getFileName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * Task ----------------------------------- TaskLane
    *              tasks                   lane
    * </pre>
    */
   
   public static final String PROPERTY_LANE = "lane";

   private TaskLane lane = null;

   public TaskLane getLane()
   {
      return this.lane;
   }

   public boolean setLane(TaskLane value)
   {
      boolean changed = false;
      
      if (this.lane != value)
      {
         TaskLane oldValue = this.lane;
         
         if (this.lane != null)
         {
            this.lane = null;
            oldValue.withoutTasks(this);
         }
         
         this.lane = value;
         
         if (value != null)
         {
            value.withTasks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LANE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Task withLane(TaskLane value)
   {
      setLane(value);
      return this;
   } 

   public TaskLane createLane()
   {
      TaskLane value = new TaskLane();
      withLane(value);
      return value;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_STATE = "state";
   
   private String state;

   public String getState()
   {
      return this.state;
   }
   
   public void setState(String value)
   {
      if ( ! StrUtil.stringEquals(this.state, value)) {
      
         String oldValue = this.state;
         this.state = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STATE, oldValue, value);
      }
   }
   
   public Task withState(String value)
   {
      setState(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_SPACENAME = "spaceName";
   
   private String spaceName;

   public String getSpaceName()
   {
      return this.spaceName;
   }
   
   public void setSpaceName(String value)
   {
      if ( ! StrUtil.stringEquals(this.spaceName, value)) {
      
         String oldValue = this.spaceName;
         this.spaceName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SPACENAME, oldValue, value);
      }
   }
   
   public Task withSpaceName(String value)
   {
      setSpaceName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_FILENAME = "fileName";
   
   private String fileName;

   public String getFileName()
   {
      return this.fileName;
   }
   
   public void setFileName(String value)
   {
      if ( ! StrUtil.stringEquals(this.fileName, value)) {
      
         String oldValue = this.fileName;
         this.fileName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FILENAME, oldValue, value);
      }
   }
   
   public Task withFileName(String value)
   {
      setFileName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_LASTMODIFIED = "lastModified";
   
   private long lastModified;

   public long getLastModified()
   {
      return this.lastModified;
   }
   
   public void setLastModified(long value)
   {
      if (this.lastModified != value) {
      
         long oldValue = this.lastModified;
         this.lastModified = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTMODIFIED, oldValue, value);
      }
   }
   
   public Task withLastModified(long value)
   {
      setLastModified(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Task ----------------------------------- TaskLane
    *              myRequests                   fileTargetCloud
    * </pre>
    */
   
   public static final String PROPERTY_FILETARGETCLOUD = "fileTargetCloud";

   private TaskLane fileTargetCloud = null;

   public TaskLane getFileTargetCloud()
   {
      return this.fileTargetCloud;
   }

   public boolean setFileTargetCloud(TaskLane value)
   {
      boolean changed = false;
      
      if (this.fileTargetCloud != value)
      {
         TaskLane oldValue = this.fileTargetCloud;
         
         if (this.fileTargetCloud != null)
         {
            this.fileTargetCloud = null;
            oldValue.withoutMyRequests(this);
         }
         
         this.fileTargetCloud = value;
         
         if (value != null)
         {
            value.withMyRequests(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FILETARGETCLOUD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Task withFileTargetCloud(TaskLane value)
   {
      setFileTargetCloud(value);
      return this;
   } 

   public TaskLane createFileTargetCloud()
   {
      TaskLane value = new TaskLane();
      withFileTargetCloud(value);
      return value;
   } 
}
