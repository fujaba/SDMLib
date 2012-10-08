package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CSClearDrawingFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CSClearDrawingFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSClearDrawingFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSClearDrawingFlowPO) target).set(attrName, value);
   }
}

