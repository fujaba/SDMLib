package org.sdmlib.scenarios.creators;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.scenarios.LogEntry;

public class LogEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
         LogEntry.PROPERTY_DATE,
         LogEntry.PROPERTY_HOURS_SPEND,
         LogEntry.PROPERTY_HOURS_REMAINING_IN_PHASE,
         LogEntry.PROPERTY_HOURS_REMAINING_IN_TOTAL,
         LogEntry.PROPERTY_KANBANENTRY,
         LogEntry.PROPERTY_DEVELOPER,
         LogEntry.PROPERTY_PHASE,
         LogEntry.PROPERTY_COMMENT,
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





