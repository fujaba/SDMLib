package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.LogEntry;

public class LogEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      LogEntry.PROPERTY_STEPNAME,
      LogEntry.PROPERTY_EXECUTEDBY,
      LogEntry.PROPERTY_TIMESTAMP,
      LogEntry.PROPERTY_TASK,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new LogEntry();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LogEntry) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((LogEntry) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LogEntry) entity).removeYou();
   }
}

