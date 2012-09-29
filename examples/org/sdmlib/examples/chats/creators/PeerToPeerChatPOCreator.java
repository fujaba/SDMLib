package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class PeerToPeerChatPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PeerToPeerChatPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PeerToPeerChatPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PeerToPeerChatPO) target).set(attrName, value);
   }
}

