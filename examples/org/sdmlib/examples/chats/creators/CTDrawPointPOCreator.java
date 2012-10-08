package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CTDrawPointPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CTDrawPointPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CTDrawPointPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CTDrawPointPO) target).set(attrName, value);
   }
}

