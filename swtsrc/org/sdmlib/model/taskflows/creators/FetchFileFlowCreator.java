package org.sdmlib.model.taskflows.creators;

import org.sdmlib.model.taskflows.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.model.taskflows.FetchFileFlow;
import org.sdmlib.model.taskflows.TaskFlow;

public class FetchFileFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      FetchFileFlow.PROPERTY_FILESERVER,
      FetchFileFlow.PROPERTY_IDMAP,
      FetchFileFlow.PROPERTY_FILENAME,
      TaskFlow.PROPERTY_TASKNO,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new FetchFileFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((FetchFileFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((FetchFileFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((FetchFileFlow) entity).removeYou();
   }
}

