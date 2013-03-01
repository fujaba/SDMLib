package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.PeerToPeerChatArgs;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class PeerToPeerChatArgsCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PeerToPeerChatArgs.PROPERTY_USERNAME,
      PeerToPeerChatArgs.PROPERTY_LOCALPORT,
      PeerToPeerChatArgs.PROPERTY_PEERIP,
      PeerToPeerChatArgs.PROPERTY_PEERPORT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new PeerToPeerChatArgs();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PeerToPeerChatArgs) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PeerToPeerChatArgs) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((PeerToPeerChatArgs) entity).removeYou();
   }
}

