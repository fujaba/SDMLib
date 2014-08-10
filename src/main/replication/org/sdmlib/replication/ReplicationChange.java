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

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.ReplicationChangeSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;

public class ReplicationChange extends Task implements PropertyChangeInterface,
      Comparable<ReplicationChange>
{
   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_TARGETOBJECTID.equalsIgnoreCase(attrName))
      {
         return getTargetObjectId();
      }

      if (PROPERTY_TARGETPROPERTY.equalsIgnoreCase(attrName))
      {
         return getTargetProperty();
      }

      if (PROPERTY_CHANGEMSG.equalsIgnoreCase(attrName))
      {
         return getChangeMsg();
      }

      if (PROPERTY_HISTORY.equalsIgnoreCase(attrName))
      {
         return getHistory();
      }

      if (PROPERTY_ISTOMANYPROPERTY.equalsIgnoreCase(attrName))
      {
         return getIsToManyProperty();
      }

      if (PROPERTY_HISTORYIDPREFIX.equalsIgnoreCase(attrName))
      {
         return getHistoryIdPrefix();
      }

      if (PROPERTY_HISTORYIDNUMBER.equalsIgnoreCase(attrName))
      {
         return getHistoryIdNumber();
      }

      if (PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         return getLogEntries();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TARGETOBJECTID.equalsIgnoreCase(attrName))
      {
         setTargetObjectId((String) value);
         return true;
      }

      if (PROPERTY_TARGETPROPERTY.equalsIgnoreCase(attrName))
      {
         setTargetProperty((String) value);
         return true;
      }

      if (PROPERTY_CHANGEMSG.equalsIgnoreCase(attrName))
      {
         setChangeMsg((String) value);
         return true;
      }

      if (PROPERTY_HISTORY.equalsIgnoreCase(attrName))
      {
         setHistory((ChangeHistory) value);
         return true;
      }

      if (PROPERTY_ISTOMANYPROPERTY.equalsIgnoreCase(attrName))
      {
         setIsToManyProperty((Boolean) value);
         return true;
      }

      if (PROPERTY_HISTORYIDPREFIX.equalsIgnoreCase(attrName))
      {
         setHistoryIdPrefix((String) value);
         return true;
      }

      if (PROPERTY_HISTORYIDNUMBER.equalsIgnoreCase(attrName))
      {
         setHistoryIdNumber(Long.parseLong(value.toString()));
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
      setHistory(null);
      removeAllFromLogEntries();
      withoutLogEntries(this.getLogEntries().toArray(new LogEntry[this.getLogEntries().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public String toString()
   {
      String changeMsg = new JsonObject().withValue(this.getChangeMsg()).toString(3);
      StringBuilder buf = new StringBuilder();

      buf.append(" ").append(this.getHistoryIdPrefix());
      buf.append(" ").append(this.getHistoryIdNumber());
      buf.append(" ").append(this.getTargetObjectId());
      buf.append(" ").append(this.getTargetProperty());
      buf.append("\n").append(changeMsg);
      // _.append(" ").append(this.getChangeMsg());

      for (LogEntry entry : this.getLogEntries())
      {
         buf.append("\n").append(entry);
      }

      return buf.substring(1);
   }

   // ==========================================================================

   public static final String PROPERTY_TARGETOBJECTID = "targetObjectId";

   private String targetObjectId;

   public String getTargetObjectId()
   {
      return this.targetObjectId;
   }

   public void setTargetObjectId(String value)
   {
      if (!StrUtil.stringEquals(this.targetObjectId, value))
      {
         String oldValue = this.targetObjectId;
         this.targetObjectId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETOBJECTID,
            oldValue, value);
      }
   }

   public ReplicationChange withTargetObjectId(String value)
   {
      setTargetObjectId(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_TARGETPROPERTY = "targetProperty";

   private String targetProperty;

   public String getTargetProperty()
   {
      return this.targetProperty;
   }

   public void setTargetProperty(String value)
   {
      if (!StrUtil.stringEquals(this.targetProperty, value))
      {
         String oldValue = this.targetProperty;
         this.targetProperty = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETPROPERTY,
            oldValue, value);
      }
   }

   public ReplicationChange withTargetProperty(String value)
   {
      setTargetProperty(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_CHANGEMSG = "changeMsg";

   private String changeMsg = "{}";

   public String getChangeMsg()
   {
      return this.changeMsg;
   }

   public void setChangeMsg(String value)
   {
      if (!StrUtil.stringEquals(this.changeMsg, value))
      {
         String oldValue = this.changeMsg;
         this.changeMsg = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANGEMSG,
            oldValue, value);
      }
   }

   public ReplicationChange withChangeMsg(String value)
   {
      setChangeMsg(value);
      return this;
   }

   public static final ReplicationChangeSet EMPTY_SET = new ReplicationChangeSet();

   /********************************************************************
    * <pre>
    *              many                       one
    * ReplicationChange ----------------------------------- ChangeHistory
    *              changes                   history
    * </pre>
    */

   public static final String PROPERTY_HISTORY = "history";

   private ChangeHistory history = null;

   public ChangeHistory getHistory()
   {
      return this.history;
   }

   public boolean setHistory(ChangeHistory value)
   {
      boolean changed = false;

      if (this.history != value)
      {
         ChangeHistory oldValue = this.history;

         if (this.history != null)
         {
            this.history = null;
            oldValue.withoutChanges(this);
         }

         this.history = value;

         if (value != null)
         {
            value.withChanges(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_HISTORY,
            oldValue, value);
         changed = true;
      }

      return changed;
   }

   public ReplicationChange withHistory(ChangeHistory value)
   {
      setHistory(value);
      return this;
   }

   public ChangeHistory createHistory()
   {
      ChangeHistory value = new ChangeHistory();
      withHistory(value);
      return value;
   }

   @Override
   public int compareTo(ReplicationChange o)
   {
      if (this.getHistoryIdNumber() < o.getHistoryIdNumber())
      {
         return -1;
      }
      else if (this.getHistoryIdNumber() > o.getHistoryIdNumber())
      {
         return 1;
      }

      return this.getHistoryIdPrefix().compareTo(o.getHistoryIdPrefix());
   }

   // ==========================================================================

   public static final String PROPERTY_ISTOMANYPROPERTY = "isToManyProperty";

   private boolean isToManyProperty = false;

   public boolean getIsToManyProperty()
   {
      return this.isToManyProperty;
   }

   public void setIsToManyProperty(boolean value)
   {
      if (this.isToManyProperty != value)
      {
         boolean oldValue = this.isToManyProperty;
         this.isToManyProperty = value;
         getPropertyChangeSupport().firePropertyChange(
            PROPERTY_ISTOMANYPROPERTY, oldValue, value);
      }
   }

   public ReplicationChange withIsToManyProperty(boolean value)
   {
      setIsToManyProperty(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_HISTORYIDPREFIX = "historyIdPrefix";

   private String historyIdPrefix = "42";

   public String getHistoryIdPrefix()
   {
      return this.historyIdPrefix;
   }

   public void setHistoryIdPrefix(String value)
   {
      if (!StrUtil.stringEquals(this.historyIdPrefix, value))
      {
         String oldValue = this.historyIdPrefix;
         this.historyIdPrefix = value;
         getPropertyChangeSupport().firePropertyChange(
            PROPERTY_HISTORYIDPREFIX, oldValue, value);
      }
   }

   public ReplicationChange withHistoryIdPrefix(String value)
   {
      setHistoryIdPrefix(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_HISTORYIDNUMBER = "historyIdNumber";

   private long historyIdNumber;

   public long getHistoryIdNumber()
   {
      return this.historyIdNumber;
   }

   public void setHistoryIdNumber(long value)
   {
      if (this.historyIdNumber != value)
      {
         long oldValue = this.historyIdNumber;
         this.historyIdNumber = value;
         getPropertyChangeSupport().firePropertyChange(
            PROPERTY_HISTORYIDNUMBER, oldValue, value);
      }
   }

   public ReplicationChange withHistoryIdNumber(long value)
   {
      setHistoryIdNumber(value);
      return this;
   }

   public void withLog(String string, String name)
   {
      this.createLogEntries().withStepName(string).withExecutedBy(name)
         .withTimeStamp(System.currentTimeMillis());
   }
}

