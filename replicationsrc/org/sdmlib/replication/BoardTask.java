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

import org.sdmlib.replication.Task;
import org.sdmlib.utils.PropertyChangeInterface;

import java.beans.PropertyChangeSupport;

import org.sdmlib.utils.StrUtil;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.creators.BoardTaskSet;

import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;

public class BoardTask extends Task implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         return getLogEntries();
      }

      if (PROPERTY_LANE.equalsIgnoreCase(attrName))
      {
         return getLane();
      }

      if (PROPERTY_STATUS.equalsIgnoreCase(attrName))
      {
         return getStatus();
      }

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         return getNext();
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         return getPrev();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         addToLogEntries((LogEntry) value);
         return true;
      }
      
      if ((PROPERTY_LOGENTRIES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLogEntries((LogEntry) value);
         return true;
      }

      if (PROPERTY_LANE.equalsIgnoreCase(attrName))
      {
         setLane((Lane) value);
         return true;
      }

      if (PROPERTY_STATUS.equalsIgnoreCase(attrName))
      {
         setStatus((String) value);
         return true;
      }

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         addToNext((BoardTask) value);
         return true;
      }
      
      if ((PROPERTY_NEXT + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromNext((BoardTask) value);
         return true;
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         addToPrev((BoardTask) value);
         return true;
      }
      
      if ((PROPERTY_PREV + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromPrev((BoardTask) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromLogEntries();
      setLane(null);
      removeAllFromNext();
      removeAllFromPrev();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public BoardTask withName(String value)
   {
      setName(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getStatus());
      return _.substring(1);
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
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LANE, oldValue, value);
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

   
   //==========================================================================
   
   public static final String PROPERTY_STATUS = "status";
   
   private String status;

   public String getStatus()
   {
      return this.status;
   }
   
   public void setStatus(String value)
   {
      if ( ! StrUtil.stringEquals(this.status, value))
      {
         String oldValue = this.status;
         this.status = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STATUS, oldValue, value);
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
         
         changed = this.next.add (value);
         
         if (changed)
         {
            value.withPrev(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromNext(BoardTask value)
   {
      boolean changed = false;
      
      if ((this.next != null) && (value != null))
      {
         changed = this.next.remove (value);
         
         if (changed)
         {
            value.withoutPrev(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, value, null);
         }
      }
         
      return changed;   
   }
   
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
      LinkedHashSet<BoardTask> tmpSet = new LinkedHashSet<BoardTask>(this.getNext());
   
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
         
         changed = this.prev.add (value);
         
         if (changed)
         {
            value.withNext(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromPrev(BoardTask value)
   {
      boolean changed = false;
      
      if ((this.prev != null) && (value != null))
      {
         changed = this.prev.remove (value);
         
         if (changed)
         {
            value.withoutNext(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, value, null);
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
      LinkedHashSet<BoardTask> tmpSet = new LinkedHashSet<BoardTask>(this.getPrev());
   
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

}

