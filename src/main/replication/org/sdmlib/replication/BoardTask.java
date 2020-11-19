/*
   Copyright (c) 2013 zuendorf 
   
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

package org.sdmlib.replication;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.BoardTaskSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;


   public class BoardTask extends Task implements PropertyChangeInterface, SendableEntity
{
   
   public BoardTask()
   {
      this(false);
   }
   
   /**
    * 
    * @param manualExecution boolean for Execution 
    */
   public BoardTask(boolean manualExecution) {
      this(null, manualExecution);
   }
   
   /**
    * 
    * @param name The Board name
    */
   public BoardTask(String name) {
      this(name, false);
   }
   
   /**
    * 
    * @param name The Board name
    * @param manualExecution boolean for Execution 
    */
   public BoardTask(String name, boolean manualExecution)
   {
      setName(name);
      setManualExecution(manualExecution);
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
      removeAllFromLogEntries();
      setLane(null);
      removeAllFromNext();
      removeAllFromPrev();
      withoutLogEntries(this.getLogEntries().toArray(new LogEntry[this.getLogEntries().size()]));
      withoutNext(this.getNext().toArray(new BoardTask[this.getNext().size()]));
      withoutPrev(this.getPrev().toArray(new BoardTask[this.getPrev().size()]));
      setProxy(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   // ==========================================================================

   public static final String PROPERTY_NAME = "name";

   private String name;

   public String getName()
   {
      return this.name;
   }

   public void setName(String value)
   {
      if (!StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue,
            value);
      }
   }

   public BoardTask withName(String value)
   {
      setName(value);
      return this;
   }

   public String toString()
   {
      StringBuilder s = new StringBuilder();

      s.append(" ").append(this.getName());
      s.append(" ").append(this.getStatus());
      return s.substring(1);
   }

   public static final BoardTaskSet EMPTY_SET = new BoardTaskSet();

   /********************************************************************
    * <pre>
    *              many                       one
    * BoardTask ----------------------------------- Lane
    *              tasks                   lane
    * </pre>
    */

   public static final String PROPERTY_LANE = "lane";

   private Lane lane = null;

   public Lane getLane()
   {
      return this.lane;
   }

   public boolean setLane(Lane value)
   {
      boolean changed = false;

      if (this.lane != value)
      {
         Lane oldValue = this.lane;

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

         stashedPropertyChangeEvent = new PropertyChangeEvent(this, PROPERTY_LANE, oldValue, value);

         if (!isManualExecution())
         {
            execute();
         }
         changed = true;
      }

      return changed;
   }

   public BoardTask withLane(Lane value)
   {
      setLane(value);
      return this;
   }

   public Lane createLane()
   {
      Lane value = new Lane();
      withLane(value);
      return value;
   }

   // ==========================================================================
   
   public static final String PROPERTY_SOURCE = "source";
   
   private Lane source;
   
   public Lane getSource() 
   {
	  return this.source;   
   }
   
   public void setSource(Lane value)
   {
	   this.source = value;
   }
   
   public BoardTask withSource(Lane value) 
   {
	   setSource(value);
	   return this;
   }
   
   // ==========================================================================

   public static final String PROPERTY_STATUS = "status";

   private String status;

   public String getStatus()
   {
      return this.status;
   }

   public void setStatus(String value)
   {
      if (!StrUtil.stringEquals(this.status, value))
      {
         String oldValue = this.status;
         this.status = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STATUS,
            oldValue, value);
      }
   }

   public BoardTask withStatus(String value)
   {
      setStatus(value);
      return this;
   }

   /********************************************************************
    * <pre>
    *              many                       many
    * BoardTask ----------------------------------- BoardTask
    *              prev                   next
    * </pre>
    */

   public static final String PROPERTY_NEXT = "next";

   private BoardTaskSet next = null;

   public BoardTaskSet getNext()
   {
      if (this.next == null)
      {
         return BoardTask.EMPTY_SET;
      }

      return this.next;
   }

   public boolean addToNext(BoardTask value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.next == null)
         {
            this.next = new BoardTaskSet();
         }

         changed = this.next.add(value);

         if (changed)
         {
            value.withPrev(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, null,
               value);
         }
      }

      return changed;
   }

   public boolean removeFromNext(BoardTask value)
   {
      boolean changed = false;

      if ((this.next != null) && (value != null))
      {
         changed = this.next.remove(value);

         if (changed)
         {
            value.withoutPrev(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, value,
               null);
         }
      }

      return changed;
   }

     /**
      * @param value The next BoardTask(s)
    * @return Self 
 */
   public BoardTask withNext(BoardTask... value)
   {
      for (BoardTask item : value)
      {
         addToNext(item);
      }
      return this;
   }

   public BoardTask withoutNext(BoardTask... value)
   {
      for (BoardTask item : value)
      {
         removeFromNext(item);
      }
      return this;
   }

   public void removeAllFromNext()
   {
      LinkedHashSet<BoardTask> tmpSet = new LinkedHashSet<BoardTask>(
            this.getNext());

      for (BoardTask value : tmpSet)
      {
         this.removeFromNext(value);
      }
   }

   public BoardTask createNext()
   {
      BoardTask value = new BoardTask();
      withNext(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              many                       many
    * BoardTask ----------------------------------- BoardTask
    *              next                   prev
    * </pre>
    */

   public static final String PROPERTY_PREV = "prev";

   private BoardTaskSet prev = null;

   public static final String START = "start";

   public BoardTaskSet getPrev()
   {
      if (this.prev == null)
      {
         return BoardTask.EMPTY_SET;
      }

      return this.prev;
   }

   public boolean addToPrev(BoardTask value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.prev == null)
         {
            this.prev = new BoardTaskSet();
         }

         changed = this.prev.add(value);

         if (changed)
         {
            value.withNext(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, null,
               value);
         }
      }

      return changed;
   }

   public boolean removeFromPrev(BoardTask value)
   {
      boolean changed = false;

      if ((this.prev != null) && (value != null))
      {
         changed = this.prev.remove(value);

         if (changed)
         {
            value.withoutNext(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, value,
               null);
         }
      }

      return changed;
   }

   public BoardTask withPrev(BoardTask... value)
   {
      for (BoardTask item : value)
      {
         addToPrev(item);
      }
      return this;
   }

   public BoardTask withoutPrev(BoardTask... value)
   {
      for (BoardTask item : value)
      {
         removeFromPrev(item);
      }
      return this;
   }

   public void removeAllFromPrev()
   {
      LinkedHashSet<BoardTask> tmpSet = new LinkedHashSet<BoardTask>(
            this.getPrev());

      for (BoardTask value : tmpSet)
      {
         this.removeFromPrev(value);
      }
   }

   public BoardTask createPrev()
   {
      BoardTask value = new BoardTask();
      withPrev(value);
      return value;
   }

   public void start()
   {
      this.setStatus(BoardTask.START);
   }

   public BoardTaskSet getNextTransitive()
   {
      BoardTaskSet result = new BoardTaskSet().with(this);
      return result.getNextTransitive();
   }

   public BoardTaskSet getPrevTransitive()
   {
      BoardTaskSet result = new BoardTaskSet().with(this);
      return result.getPrevTransitive();
   }

   public static final String PROPERTY_TASKOBJECTS = "taskObjects";

   private HashMap<String, Object> taskObjects = new HashMap<String, Object>();

   public Object putTaskObject(String name, Object o)
   {
      return getTaskObjects().put(name, o);
   }

   public HashMap<String, Object> getTaskObjects()
   {
      return taskObjects;
   }

   public void setTaskObjects(HashMap<String, Object> taskObjects)
   {
      this.taskObjects = taskObjects;
   }

   public BoardTask withTaskObjects(HashMap<String, Object> taskObjects)
   {
      setTaskObjects(taskObjects);
      return this;
   }

   public BoardTask withTaskObject(String name, Object o)
   {
      getTaskObjects().put(name, o);
      return this;
   }

   /********************************************************************
    * <pre>
    *              many                       one
    * BoardTask ----------------------------------- SeppelSpaceProxy
    *              tasks                   proxy
    * </pre>
    */

   public static final String PROPERTY_PROXY = "proxy";

   private SeppelSpaceProxy proxy = null;

   public SeppelSpaceProxy getProxy()
   {
      return this.proxy;
   }

   public boolean setProxy(SeppelSpaceProxy value)
   {
      boolean changed = false;

      if (this.proxy != value)
      {
         SeppelSpaceProxy oldValue = this.proxy;

         if (this.proxy != null)
         {
            this.proxy = null;
            oldValue.withoutTasks(this);
         }

         this.proxy = value;

         if (value != null)
         {
            value.withTasks(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_PROXY, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public BoardTask withProxy(SeppelSpaceProxy value)
   {
      setProxy(value);
      return this;
   }

   public SeppelSpaceProxy createProxy()
   {
      SeppelSpaceProxy value = new SeppelSpaceProxy();
      withProxy(value);
      return value;
   }

   public Object getFromTaskObjects(String key)
   {
      return this.taskObjects.get(key);

   }

   // ==========================================================================

   public static final String PROPERTY_MANUALEXECUTION = "manualExecution";

   private boolean manualExecution;

   public boolean isManualExecution()
   {
      return this.manualExecution;
   }

   public void setManualExecution(boolean value)
   {
      if (this.manualExecution != value)
      {
         boolean oldValue = this.manualExecution;
         this.manualExecution = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MANUALEXECUTION, oldValue, value);
      }
   }

   public BoardTask withManualExecution(boolean value)
   {
      setManualExecution(value);
      return this;
   }

   
   //==========================================================================
   public void execute(  )
   {
      getPropertyChangeSupport().firePropertyChange(stashedPropertyChangeEvent);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_STASHEDPROPERTYCHANGEEVENT = "stashedPropertyChangeEvent";
   
   private PropertyChangeEvent stashedPropertyChangeEvent;

   public PropertyChangeEvent getStashedPropertyChangeEvent()
   {
      return this.stashedPropertyChangeEvent;
   }
   
   public void setStashedPropertyChangeEvent(PropertyChangeEvent value)
   {
      if (this.stashedPropertyChangeEvent != value)
      {
         PropertyChangeEvent oldValue = this.stashedPropertyChangeEvent;
         this.stashedPropertyChangeEvent = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STASHEDPROPERTYCHANGEEVENT, oldValue, value);
      }
   }
   
   public BoardTask withStashedPropertyChangeEvent(PropertyChangeEvent value)
   {
      setStashedPropertyChangeEvent(value);
      return this;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }
}
