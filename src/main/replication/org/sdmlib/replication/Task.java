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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.replication.util.LogEntrySet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 */
   public class Task implements PropertyChangeInterface, SendableEntity
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         return getLogEntries();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         addToLogEntries((LogEntry) value);
         return true;
      }

      if ((PROPERTY_LOGENTRIES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLogEntries((LogEntry) value);
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

   // ==========================================================================

   public void removeYou()
   {
      removeAllFromLogEntries();
      withoutLogEntries(this.getLogEntries().toArray(new LogEntry[this.getLogEntries().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Task ----------------------------------- LogEntry
    *              task                   logEntries
    * </pre>
    */

   public static final String PROPERTY_LOGENTRIES = "logEntries";

   public static final int DO_LOG = 1;

   private LogEntrySet logEntries = null;

   public LogEntrySet getLogEntries()
   {
      if (this.logEntries == null)
      {
         return LogEntry.EMPTY_SET;
      }

      return this.logEntries;
   }

   public boolean addToLogEntries(LogEntry value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.logEntries == null)
         {
            this.logEntries = new LogEntrySet();
         }

         changed = this.logEntries.add(value);

         if (changed)
         {
            value.withTask(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGENTRIES,
               null, value);
         }
      }

      return changed;
   }

   public boolean removeFromLogEntries(LogEntry value)
   {
      boolean changed = false;

      if ((this.logEntries != null) && (value != null))
      {
         changed = this.logEntries.remove(value);

         if (changed)
         {
            value.setTask(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGENTRIES,
               value, null);
         }
      }

      return changed;
   }

   public Task withLogEntries(LogEntry value)
   {
      addToLogEntries(value);
      return this;
   }

   public Task withoutLogEntries(LogEntry value)
   {
      removeFromLogEntries(value);
      return this;
   }

   public void removeAllFromLogEntries()
   {
      LinkedHashSet<LogEntry> tmpSet = new LinkedHashSet<LogEntry>(
            this.getLogEntries());

      for (LogEntry value : tmpSet)
      {
         this.removeFromLogEntries(value);
      }
   }

   public LogEntry createLogEntries()
   {
      LogEntry value = new LogEntry();
      withLogEntries(value);
      return value;
   }

   public Task withLogEntries(LogEntry... value)
   {
      for (LogEntry item : value)
      {
         addToLogEntries(item);
      }
      return this;
   }

   public Task withoutLogEntries(LogEntry... value)
   {
      for (LogEntry item : value)
      {
         removeFromLogEntries(item);
      }
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
   }

