package org.sdmlib.model.taskflows.creators;

import org.sdmlib.model.taskflows.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.model.taskflows.LogEntry;

public class LogEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      LogEntry.PROPERTY_NODENAME,
      LogEntry.PROPERTY_TASKNAME,
      LogEntry.PROPERTY_LOGGER,
      LogEntry.PROPERTY_CHILDREN,
      LogEntry.PROPERTY_PARENT,
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


