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

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.replication.creators.ReplicationChangeSet;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import java.beans.PropertyChangeListener;

public class ChangeHistory implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_CHANGES.equalsIgnoreCase(attrName))
      {
         return getChanges();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_CHANGES.equalsIgnoreCase(attrName))
      {
         addToChanges((ReplicationChange) value);
         return true;
      }

      if ((PROPERTY_CHANGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromChanges((ReplicationChange) value);
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

   // ==========================================================================

   public void removeYou()
   {
      removeAllFromChanges();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   private LinkedHashMap<String, Object> changeMap = new LinkedHashMap<String, Object>();

   public LinkedHashMap<String, Object> getChangeMap()
   {
      return changeMap;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * ChangeHistory ----------------------------------- ReplicationChange
    *              history                   changes
    * </pre>
    */

   public static final String PROPERTY_CHANGES = "changes";

   private ReplicationChangeSet changes = null;

   public ReplicationChangeSet getChanges()
   {
      if (this.changes == null)
      {
         return ReplicationChange.EMPTY_SET;
      }

      return this.changes;
   }

   public boolean addToChanges(ReplicationChange value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.changes == null)
         {
            this.changes = new ReplicationChangeSet();
         }

         changed = this.changes.add(value);

         if (changed)
         {
            value.withHistory(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANGES,
               null, value);
         }
      }

      return changed;
   }

   public boolean removeFromChanges(ReplicationChange value)
   {
      boolean changed = false;

      if ((this.changes != null) && (value != null))
      {
         changed = this.changes.remove(value);

         if (changed)
         {
            value.setHistory(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANGES,
               value, null);
         }
      }

      return changed;
   }

   public ChangeHistory withChanges(ReplicationChange value)
   {
      addToChanges(value);
      return this;
   }

   public ChangeHistory withoutChanges(ReplicationChange value)
   {
      removeFromChanges(value);
      return this;
   }

   public void removeAllFromChanges()
   {
      LinkedHashSet<ReplicationChange> tmpSet = new LinkedHashSet<ReplicationChange>(
            this.getChanges());

      for (ReplicationChange value : tmpSet)
      {
         this.removeFromChanges(value);
      }
   }

   public ReplicationChange createChanges()
   {
      ReplicationChange value = new ReplicationChange();
      withChanges(value);
      return value;
   }

   public void addChange(ReplicationChange change)
   {
      // add to log
      this.addToChanges(change);

      if (obsoleteChanges == null)
      {
         obsoleteChanges = new LinkedHashMap<String, ReplicationChange>();
      }

      ReplicationChange obsoleteChange = obsoleteChanges.get(change
         .getHistoryIdPrefix());
      if (obsoleteChange != null)
      {
         obsoleteChanges.remove(change.getHistoryIdPrefix());
         changes.remove(obsoleteChange);
      }

      // add to map
      String fullKey = change.getTargetObjectId() + "|"
         + change.getTargetProperty();

      if (change.getIsToManyProperty())
      {
         // just add to TreeSet of changes
         ReplicationChangeSet changeList = (ReplicationChangeSet) changeMap
            .get(fullKey);
         if (changeList == null)
         {
            changeList = new ReplicationChangeSet();
            changeMap.put(fullKey, changeList);
         }
         changeList.add(change);
      }
      else
      {
         // to one property, remove old change before overriding it.
         ReplicationChange oldChange = (ReplicationChange) changeMap
            .get(fullKey);
         if (oldChange != null)
         {
            this.removeFromChanges(oldChange);
         }
         changeMap.put(fullKey, change);
      }

   }

   private LinkedHashMap<String, ReplicationChange> obsoleteChanges;

   public void addToObsoleteChanges(ReplicationChange change)
   {
      if (obsoleteChanges == null)
      {
         obsoleteChanges = new LinkedHashMap<String, ReplicationChange>();
      }

      ReplicationChange oldChange = obsoleteChanges.get(change
         .getHistoryIdPrefix());
      {
         if (oldChange != null)
         {
            changeMap.remove(oldChange);
            obsoleteChanges.remove(change.getHistoryIdPrefix());
         }

         obsoleteChanges.put(change.getHistoryIdPrefix(), change);
      }
   }

   public ChangeHistory withChanges(ReplicationChange... value)
   {
      for (ReplicationChange item : value)
      {
         addToChanges(item);
      }
      return this;
   }

   public ChangeHistory withoutChanges(ReplicationChange... value)
   {
      for (ReplicationChange item : value)
      {
         removeFromChanges(item);
      }
      return this;
   }
}

