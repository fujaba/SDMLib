package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ChatServerPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ChatServerPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChatServerPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChatServerPO) target).set(attrName, value);
   }
}

