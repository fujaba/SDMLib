package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CTClearDrawingPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CTClearDrawingPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CTClearDrawingPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CTClearDrawingPO) target).set(attrName, value);
   }
}

