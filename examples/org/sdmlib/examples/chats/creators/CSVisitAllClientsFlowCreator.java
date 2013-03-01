package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.CSVisitAllClientsFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class CSVisitAllClientsFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      CSVisitAllClientsFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_TASKNO,
      CSVisitAllClientsFlow.PROPERTY_TGTTASK,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new CSVisitAllClientsFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSVisitAllClientsFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSVisitAllClientsFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CSVisitAllClientsFlow) entity).removeYou();
   }
}


