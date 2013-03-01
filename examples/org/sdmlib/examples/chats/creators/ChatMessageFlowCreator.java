package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.ChatMessageFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ChatMessageFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ChatMessageFlow.PROPERTY_MSG,
      ChatMessageFlow.PROPERTY_GUI,
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
      return new ChatMessageFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChatMessageFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChatMessageFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ChatMessageFlow) entity).removeYou();
   }
}




