/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.replication.util;

import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.Task;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class ReplicationChangeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReplicationChange.PROPERTY_HISTORYIDPREFIX,
      ReplicationChange.PROPERTY_HISTORYIDNUMBER,
      ReplicationChange.PROPERTY_TARGETOBJECTID,
      ReplicationChange.PROPERTY_TARGETPROPERTY,
      ReplicationChange.PROPERTY_ISTOMANYPROPERTY,
      ReplicationChange.PROPERTY_CHANGEMSG,
      Task.PROPERTY_LOGENTRIES,
      // ReplicationChange.PROPERTY_HISTORY,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationChange();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (ReplicationChange.PROPERTY_HISTORYIDPREFIX.equalsIgnoreCase(attribute))
      {
         return ((ReplicationChange) target).getHistoryIdPrefix();
      }

      if (ReplicationChange.PROPERTY_HISTORYIDNUMBER.equalsIgnoreCase(attribute))
      {
         return ((ReplicationChange) target).getHistoryIdNumber();
      }

      if (ReplicationChange.PROPERTY_TARGETOBJECTID.equalsIgnoreCase(attribute))
      {
         return ((ReplicationChange) target).getTargetObjectId();
      }

      if (ReplicationChange.PROPERTY_TARGETPROPERTY.equalsIgnoreCase(attribute))
      {
         return ((ReplicationChange) target).getTargetProperty();
      }

      if (ReplicationChange.PROPERTY_ISTOMANYPROPERTY.equalsIgnoreCase(attribute))
      {
         return ((ReplicationChange) target).getIsToManyProperty();
      }

      if (ReplicationChange.PROPERTY_CHANGEMSG.equalsIgnoreCase(attribute))
      {
         return ((ReplicationChange) target).getChangeMsg();
      }

      if (ReplicationChange.PROPERTY_LOGENTRIES.equalsIgnoreCase(attribute))
      {
         return ((ReplicationChange) target).getLogEntries();
      }

      if (ReplicationChange.PROPERTY_HISTORY.equalsIgnoreCase(attribute))
      {
         return ((ReplicationChange) target).getHistory();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (ReplicationChange.PROPERTY_HISTORYIDPREFIX.equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).withHistoryIdPrefix((String) value);
         return true;
      }

      if (ReplicationChange.PROPERTY_HISTORYIDNUMBER.equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).withHistoryIdNumber(Long.parseLong(value.toString()));
         return true;
      }

      if (ReplicationChange.PROPERTY_TARGETOBJECTID.equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).withTargetObjectId((String) value);
         return true;
      }

      if (ReplicationChange.PROPERTY_TARGETPROPERTY.equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).withTargetProperty((String) value);
         return true;
      }

      if (ReplicationChange.PROPERTY_ISTOMANYPROPERTY.equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).withIsToManyProperty((Boolean) value);
         return true;
      }

      if (ReplicationChange.PROPERTY_CHANGEMSG.equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).withChangeMsg((String) value);
         return true;
      }

      if (ReplicationChange.PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).withLogEntries((LogEntry) value);
         return true;
      }
      
      if ((ReplicationChange.PROPERTY_LOGENTRIES + REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).withoutLogEntries((LogEntry) value);
         return true;
      }

      if (ReplicationChange.PROPERTY_HISTORY.equalsIgnoreCase(attrName))
      {
         ((ReplicationChange) target).setHistory((ChangeHistory) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ReplicationChange) entity).removeYou();
   }
}
