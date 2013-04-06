package org.sdmlib.examples.replication.creators;

import org.sdmlib.examples.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.replication.ChatMsg;

public class ChatMsgCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ChatMsg.PROPERTY_TEXT,
      ChatMsg.PROPERTY_TIME,
      ChatMsg.PROPERTY_SENDER,
      ChatMsg.PROPERTY_ROOT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ChatMsg();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChatMsg) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChatMsg) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ChatMsg) entity).removeYou();
   }
}

