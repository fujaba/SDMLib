package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class RoomPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new RoomPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((RoomPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((RoomPO) target).set(attrName, value);
   }
}

