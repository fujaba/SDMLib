package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.CSChatMessageFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class CSChatMessageFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      TaskFlow.PROPERTY_TASKNO,
      CSChatMessageFlow.PROPERTY_MSG,
      CSChatMessageFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new CSChatMessageFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSChatMessageFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSChatMessageFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CSChatMessageFlow) entity).removeYou();
   }
}




