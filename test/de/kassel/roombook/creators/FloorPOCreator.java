package de.kassel.roombook.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class FloorPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new FloorPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((FloorPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((FloorPO) target).set(attrName, value);
   }
}

