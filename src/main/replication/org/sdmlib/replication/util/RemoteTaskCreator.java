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

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.RemoteTask;
import org.sdmlib.replication.Task;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class RemoteTaskCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      RemoteTask.PROPERTY_BOARDTASK,
      Task.PROPERTY_LOGENTRIES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return null;
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

      if (RemoteTask.PROPERTY_BOARDTASK.equalsIgnoreCase(attribute))
      {
         return ((RemoteTask) target).getBoardTask();
      }

      if (RemoteTask.PROPERTY_LOGENTRIES.equalsIgnoreCase(attribute))
      {
         return ((RemoteTask) target).getLogEntries();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (RemoteTask.PROPERTY_BOARDTASK.equalsIgnoreCase(attrName))
      {
         ((RemoteTask) target).withBoardTask((BoardTask) value);
         return true;
      }

      if (RemoteTask.PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         ((RemoteTask) target).withLogEntries((LogEntry) value);
         return true;
      }
      
      if ((RemoteTask.PROPERTY_LOGENTRIES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((RemoteTask) target).withoutLogEntries((LogEntry) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((RemoteTask) entity).removeYou();
   }
}
