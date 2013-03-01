package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.P2PNetworkLoginFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class P2PNetworkLoginFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      P2PNetworkLoginFlow.PROPERTY_FIRSTPEER,
      P2PNetworkLoginFlow.PROPERTY_CLIENTNAME,
      P2PNetworkLoginFlow.PROPERTY_PEERLIST,
      TaskFlow.PROPERTY_TASKNO,
      TaskFlow.PROPERTY_IDMAP,
      P2PNetworkLoginFlow.PROPERTY_CLIENTPEER,
      P2PNetworkLoginFlow.PROPERTY_ALLMESSAGES,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new P2PNetworkLoginFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((P2PNetworkLoginFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((P2PNetworkLoginFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((P2PNetworkLoginFlow) entity).removeYou();
   }
}



