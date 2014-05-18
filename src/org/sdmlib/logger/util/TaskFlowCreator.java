package org.sdmlib.logger.util;

import org.sdmlib.logger.TaskFlow;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class TaskFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
         TaskFlow.PROPERTY_IDMAP,
         TaskFlow.PROPERTY_PARENT,
         TaskFlow.PROPERTY_SUBFLOW,
         TaskFlow.PROPERTY_TASKNO
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
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      // wrapped object has no removeYou method
   }
}

