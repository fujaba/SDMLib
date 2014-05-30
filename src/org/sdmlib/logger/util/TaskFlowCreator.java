package org.sdmlib.logger.util;

import org.sdmlib.logger.TaskFlow;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public abstract class TaskFlowCreator extends EntityFactory
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
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (TaskFlow.PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return ((TaskFlow)target).getTaskNo();
      }

      if (TaskFlow.PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return ((TaskFlow)target).getIdMap();
      }

      if (TaskFlow.PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         return ((TaskFlow)target).getSubFlow();
      }

      if (TaskFlow.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return ((TaskFlow)target).getParent();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (TaskFlow.PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         ((TaskFlow)target).setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }
      if (TaskFlow.PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         ((TaskFlow)target).setIdMap((org.sdmlib.serialization.SDMLibJsonIdMap) value);
         return true;
      }

      if (TaskFlow.PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         ((TaskFlow)target).setSubFlow((TaskFlow) value);
         return true;
      }

      if (TaskFlow.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((TaskFlow)target).setParent((TaskFlow) value);
         return true;
      }
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}
