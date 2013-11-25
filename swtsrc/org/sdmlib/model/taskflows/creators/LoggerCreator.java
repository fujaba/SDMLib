package org.sdmlib.model.taskflows.creators;

import org.sdmlib.model.taskflows.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.TaskFlow;

public class LoggerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Logger.PROPERTY_STARTPEER,
      TaskFlow.PROPERTY_TASKNO,
      TaskFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
      Logger.PROPERTY_ENTRIES,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Logger();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Logger) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Logger) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Logger) entity).removeYou();
   }
}

