package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.TestChatMessageFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class TestChatMessageFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      TestChatMessageFlow.PROPERTY_MSG,
      TaskFlow.PROPERTY_TASKNO,
      TaskFlow.PROPERTY_SUBFLOW, 
      TaskFlow.PROPERTY_IDMAP,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new TestChatMessageFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TestChatMessageFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((TestChatMessageFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((TestChatMessageFlow) entity).removeYou();
   }
}

