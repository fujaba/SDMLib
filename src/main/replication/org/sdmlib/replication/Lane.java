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

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.BoardTaskSet;
import org.sdmlib.replication.util.LaneSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeListener;
import org.sdmlib.replication.RemoteTaskBoard;
import org.sdmlib.replication.BoardTask;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 */
   public class Lane implements PropertyChangeInterface, SendableEntity
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_BOARD.equalsIgnoreCase(attrName))
      {
         return getBoard();
      }

      if (PROPERTY_TASKS.equalsIgnoreCase(attrName))
      {
         return getTasks();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_BOARD.equalsIgnoreCase(attrName))
      {
         setBoard((RemoteTaskBoard) value);
         return true;
      }

      if (PROPERTY_TASKS.equalsIgnoreCase(attrName))
      {
         addToTasks((BoardTask) value);
         return true;
      }

      if ((PROPERTY_TASKS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromTasks((BoardTask) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }


   // ==========================================================================

   public void removeYou()
   {
      setBoard(null);
      removeAllFromTasks();
      withoutTasks(this.getTasks().toArray(new BoardTask[this.getTasks().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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

   public Lane withName(String value)
   {
      setName(value);
      return this;
   }

   public String toString()
   {
      StringBuilder s = new StringBuilder();

      s.append(" ").append(this.getName());
      return s.substring(1);
   }

   public static final LaneSet EMPTY_SET = new LaneSet();

   /********************************************************************
    * <pre>
    *              many                       one
    * Lane ----------------------------------- TaskFlowBoard
    *              lanes                   board
    * </pre>
    */

   public static final String PROPERTY_BOARD = "board";

   private RemoteTaskBoard board = null;

   public RemoteTaskBoard getBoard()
   {
      return this.board;
   }

   public boolean setBoard(RemoteTaskBoard value)
   {
      boolean changed = false;

      if (this.board != value)
      {
         RemoteTaskBoard oldValue = this.board;

         if (this.board != null)
         {
            this.board = null;
            oldValue.withoutLanes(this);
         }

         this.board = value;

         if (value != null)
         {
            value.withLanes(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_BOARD,
            oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Lane withBoard(RemoteTaskBoard value)
   {
      setBoard(value);
      return this;
   }

   public RemoteTaskBoard createBoard()
   {
      RemoteTaskBoard value = new RemoteTaskBoard();
      withBoard(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Lane ----------------------------------- BoardTask
    *              lane                   tasks
    * </pre>
    */

   public static final String PROPERTY_TASKS = "tasks";

   private BoardTaskSet tasks = null;

   public BoardTaskSet getTasks()
   {
      if (this.tasks == null)
      {
         return BoardTaskSet.EMPTY_SET;
      }

      return this.tasks;
   }

   public boolean addToTasks(BoardTask value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.tasks == null)
         {
            this.tasks = new BoardTaskSet();
         }

         changed = this.tasks.add(value);

         if (changed)
         {
            value.withLane(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKS, null,
               value);
         }
      }

      return changed;
   }

   public boolean removeFromTasks(BoardTask value)
   {
      boolean changed = false;

      if ((this.tasks != null) && (value != null))
      {
         changed = this.tasks.remove(value);

         if (changed)
         {
            value.setLane(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKS,
               value, null);
         }
      }

      return changed;
   }

   public Lane withTasks(BoardTask value)
   {
      addToTasks(value);
      return this;
   }

   public Lane withoutTasks(BoardTask value)
   {
      removeFromTasks(value);
      return this;
   }

   public void removeAllFromTasks()
   {
      LinkedHashSet<BoardTask> tmpSet = new LinkedHashSet<BoardTask>(
            this.getTasks());

      for (BoardTask value : tmpSet)
      {
         this.removeFromTasks(value);
      }
   }


   /**
    * @return The created BoardTask
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
    */
   public BoardTask createTasks()
   {
      BoardTask value = new BoardTask();
      withTasks(value);
      return value;
   }

   /**
    * Create a new BoardTask
    * @param taskName The Name of BoardTask
    * @return The created BoardTask
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
    */
   public BoardTask createTask(String taskName)
   {
      BoardTask task = new BoardTask().withName(taskName);

      this.addToTasks(task);

      return task;
   }

   public void startTask(String taskName)
   {
      BoardTask task = new BoardTask().withName(taskName);

      this.addToTasks(task);

      task.setStatus(BoardTask.START);
   }

   public Lane withTasks(BoardTask... value)
   {
      for (BoardTask item : value)
      {
         addToTasks(item);
      }
      return this;
   }

   public Lane withoutTasks(BoardTask... value)
   {
      for (BoardTask item : value)
      {
         removeFromTasks(item);
      }
      return this;
   }
}

