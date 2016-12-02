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
   
package org.sdmlib.storyboards;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.util.LogEntryStoryBoardSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/kanban/ProjectBoard.java'>ProjectBoard.java</a>
*/
   public  class KanbanEntry implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
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
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   //==========================================================================
   
   
   public void removeYou()
   {
   
      withoutLogEntries(this.getLogEntries().toArray(new LogEntryStoryBoard[this.getLogEntries().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_OLDNOOFLOGENTRIES = "oldNoOfLogEntries";
   
   private int oldNoOfLogEntries;

   public int getOldNoOfLogEntries()
   {
      return this.oldNoOfLogEntries;
   }
   
   public void setOldNoOfLogEntries(int value)
   {
      if (this.oldNoOfLogEntries != value) {
      
         int oldValue = this.oldNoOfLogEntries;
         this.oldNoOfLogEntries = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OLDNOOFLOGENTRIES, oldValue, value);
      }
   }
   
   public KanbanEntry withOldNoOfLogEntries(int value)
   {
      setOldNoOfLogEntries(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getOldNoOfLogEntries());
      result.append(" ").append(this.getPhases());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_PHASES = "phases";
   
   private String phases;

   public String getPhases()
   {
      return this.phases;
   }
   
   public void setPhases(String value)
   {
      if ( ! StrUtil.stringEquals(this.phases, value)) {
      
         String oldValue = this.phases;
         this.phases = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PHASES, oldValue, value);
      }
   }
   
   public KanbanEntry withPhases(String value)
   {
      setPhases(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * KanbanEntry ----------------------------------- LogEntryStoryBoard
    *              kanbanEntry                   logEntries
    * </pre>
    */
   
   public static final String PROPERTY_LOGENTRIES = "logEntries";

   private LogEntryStoryBoardSet logEntries = null;
   
   public LogEntryStoryBoardSet getLogEntries()
   {
      if (this.logEntries == null)
      {
         return LogEntryStoryBoardSet.EMPTY_SET;
      }
   
      return this.logEntries;
   }

   public KanbanEntry withLogEntries(LogEntryStoryBoard... value)
   {
      if(value==null){
         return this;
      }
      for (LogEntryStoryBoard item : value)
      {
         if (item != null)
         {
            if (this.logEntries == null)
            {
               this.logEntries = new LogEntryStoryBoardSet();
            }
            
            boolean changed = this.logEntries.add (item);

            if (changed)
            {
               item.withKanbanEntry(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGENTRIES, null, item);
            }
         }
      }
      return this;
   } 

   public KanbanEntry withoutLogEntries(LogEntryStoryBoard... value)
   {
      for (LogEntryStoryBoard item : value)
      {
         if ((this.logEntries != null) && (item != null))
         {
            if (this.logEntries.remove(item))
            {
               item.setKanbanEntry(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGENTRIES, item, null);
            }
         }
      }
      return this;
   }

   public LogEntryStoryBoard createLogEntries()
   {
      LogEntryStoryBoard value = new LogEntryStoryBoard();
      withLogEntries(value);
      return value;
   } 
}
