package org.sdmlib.scenarios.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.scenarios.LogEntry;

public class LogEntryCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      LogEntry.PROPERTY_KANBANENTRY,
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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((LogEntry) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}




