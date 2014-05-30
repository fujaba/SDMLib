package org.sdmlib.logger.util;

import org.sdmlib.logger.LogEntry;
import org.sdmlib.logger.Logger;
import org.sdmlib.logger.PeerProxy;
import org.sdmlib.logger.TaskFlow;

import de.uniks.networkparser.json.JsonIdMap;

public class LoggerCreator extends TaskFlowCreator
{
   private final String[] properties = new String[]
   {
      TaskFlow.PROPERTY_TASKNO,
      TaskFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_SUBFLOW,
      Logger.PROPERTY_ENTRIES,
      Logger.PROPERTY_STARTPEER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Logger();
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

      if (Logger.PROPERTY_ENTRIES.equalsIgnoreCase(attrName))
      {
         return ((Logger)target).getEntries();
      }

      if (Logger.PROPERTY_STARTPEER.equalsIgnoreCase(attribute))
      {
         return ((Logger)target).getStartPeer();
      }

      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {

      if (Logger.PROPERTY_ENTRIES.equalsIgnoreCase(attrName))
      {
         ((Logger)target).addToEntries((LogEntry) value);
         return true;
      }

      if ((Logger.PROPERTY_ENTRIES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Logger)target).removeFromEntries((LogEntry) value);
         return true;
      }
      if (Logger.PROPERTY_STARTPEER.equalsIgnoreCase(attrName))
      {
         ((Logger)target).setStartPeer((PeerProxy) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }

   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Logger) entity).removeYou();
   }
}



