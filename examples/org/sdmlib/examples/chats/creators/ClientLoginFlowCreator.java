package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.ClientLoginFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ClientLoginFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ClientLoginFlow.PROPERTY_IDMAP,
      ClientLoginFlow.PROPERTY_SERVER,
      TaskFlow.PROPERTY_TASKNO,
      ClientLoginFlow.PROPERTY_CLIENTIP,
      ClientLoginFlow.PROPERTY_CLIENTPORT,
      ClientLoginFlow.PROPERTY_ALLMESSAGESTEXT,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ClientLoginFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ClientLoginFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ClientLoginFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ClientLoginFlow) entity).removeYou();
   }
}




