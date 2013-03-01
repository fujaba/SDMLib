package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class PeerToPeerChatCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new PeerToPeerChat();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PeerToPeerChat) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PeerToPeerChat) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((PeerToPeerChat) entity).removeYou();
   }
}

