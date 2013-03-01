package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.P2PChatMessageFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class P2PChatMessageFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      P2PChatMessageFlow.PROPERTY_MSG,
      P2PChatMessageFlow.PROPERTY_POS,
      TaskFlow.PROPERTY_TASKNO,
      TaskFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new P2PChatMessageFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((P2PChatMessageFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((P2PChatMessageFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((P2PChatMessageFlow) entity).removeYou();
   }
}

