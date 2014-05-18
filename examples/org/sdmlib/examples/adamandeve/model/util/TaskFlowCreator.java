package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.logger.TaskFlow;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class TaskFlowCreator extends EntityFactory
{
   public static final String PROPERTY_TASKNO = "taskNo";
   private final String[] properties = new String[]
   {
      PROPERTY_TASKNO,
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
      if (TaskFlowCreator.PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         return ((TaskFlow) target).getTaskNo();
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

      if (TaskFlowCreator.PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).setTaskNo(Integer.parseInt(value.toString()));
         return true;
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

