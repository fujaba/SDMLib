package org.sdmlib.examples.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ChatRootPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ChatRootPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChatRootPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChatRootPO) target).set(attrName, value);
   }
}

