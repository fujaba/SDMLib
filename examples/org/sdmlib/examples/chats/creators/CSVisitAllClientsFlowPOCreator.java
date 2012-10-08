package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CSVisitAllClientsFlowPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CSVisitAllClientsFlowPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSVisitAllClientsFlowPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSVisitAllClientsFlowPO) target).set(attrName, value);
   }
}

