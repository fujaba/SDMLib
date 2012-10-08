package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CSClientTaskPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CSClientTaskPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSClientTaskPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSClientTaskPO) target).set(attrName, value);
   }
}

