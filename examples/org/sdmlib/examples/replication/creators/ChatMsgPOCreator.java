package org.sdmlib.examples.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ChatMsgPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ChatMsgPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChatMsgPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChatMsgPO) target).set(attrName, value);
   }
}

