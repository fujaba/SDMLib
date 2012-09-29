package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ClearDrawingFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ClearDrawingFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ClearDrawingFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ClearDrawingFlowPO) target).set(attrName, value);
   }
}

