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

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/kanban/ProjectBoard.java'>ProjectBoard.java</a>
*/
   @Deprecated
   public  class LogEntryStoryBoard implements PropertyChangeInterface, SendableEntity
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
   
      setKanbanEntry(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LogEntryStoryBoard ----------------------------------- KanbanEntry
    *              logEntries                   kanbanEntry
    * </pre>
    */
   
   public static final String PROPERTY_KANBANENTRY = "kanbanEntry";

   private KanbanEntry kanbanEntry = null;

   public KanbanEntry getKanbanEntry()
   {
      return this.kanbanEntry;
   }

   public boolean setKanbanEntry(KanbanEntry value)
   {
      boolean changed = false;
      
      if (this.kanbanEntry != value)
      {
         KanbanEntry oldValue = this.kanbanEntry;
         
         if (this.kanbanEntry != null)
         {
            this.kanbanEntry = null;
            oldValue.withoutLogEntries(this);
         }
         
         this.kanbanEntry = value;
         
         if (value != null)
         {
            value.withLogEntries(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_KANBANENTRY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public LogEntryStoryBoard withKanbanEntry(KanbanEntry value)
   {
      setKanbanEntry(value);
      return this;
   } 

   public KanbanEntry createKanbanEntry()
   {
      KanbanEntry value = new KanbanEntry();
      withKanbanEntry(value);
      return value;
   } 
}
