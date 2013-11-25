package org.sdmlib.examples.adamandeve.creators;

import org.sdmlib.examples.adamandeve.UpdateAdamFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class UpdateAdamFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      UpdateAdamFlow.PROPERTY_ADAM,
      UpdateAdamFlow.PROPERTY_EVE,
      UpdateAdamFlow.PROPERTY_IDMAP,
      UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED,
      TaskFlow.PROPERTY_TASKNO,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new UpdateAdamFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((UpdateAdamFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((UpdateAdamFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((UpdateAdamFlow) entity).removeYou();
   }
}

