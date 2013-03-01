package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.ChatServer;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ChatServerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ChatServer.PROPERTY_ALLMESSAGES,
      ChatServer.PROPERTY_ALLPEERS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ChatServer();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChatServer) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChatServer) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ChatServer) entity).removeYou();
   }
}


