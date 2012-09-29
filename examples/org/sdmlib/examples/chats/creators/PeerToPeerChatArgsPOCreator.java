package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class PeerToPeerChatArgsPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PeerToPeerChatArgsPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PeerToPeerChatArgsPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PeerToPeerChatArgsPO) target).set(attrName, value);
   }
}

